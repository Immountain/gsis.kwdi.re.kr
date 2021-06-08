package infomind.com.cmm.interceptor;

import egovframework.com.cmm.LoginVO;
import egovframework.com.sym.mnu.mpm.service.EgovMenuManageService;
import egovframework.com.sym.mnu.mpm.service.MenuManageVO;

import infomind.com.cmm.visit.InfoVisitFactory;
import infomind.com.cms.mnu.auth.service.InfoCmsMenuAuthService;
import infomind.com.cms.mnu.auth.vo.InfoCmsMenuAuthVO;
import infomind.com.cms.mnu.mpm.service.InfoMenuManageService;
import infomind.com.cms.mnu.mpm.vo.InfoMenuManageVO;
import infomind.com.cms.visit.service.InfoCmsVisitService;
import infomind.com.cms.visit.vo.InfoCmsVisitVO;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class CmsInterceptor extends HandlerInterceptorAdapter {

    @Resource
    private InfoMenuManageService menuManageService;


    @Resource
    private InfoCmsMenuAuthService infoCmsMenuAuthService;

    @Resource
    private InfoCmsVisitService infoCmsVisitService;




    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //System.out.println("CmsInterceptor===>");

        InfoMenuManageVO manageVO = new InfoMenuManageVO();

        System.out.println("get menuNo 22Parameter===>"+request.getParameter("menuNo"));
        System.out.println("get menuNo Parameter===>"+request.getParameter("menuTargetNo"));

        String tempMenuTargetNo =request.getParameter("menuTargetNo") == null ? "" : (String) request.getParameter("menuTargetNo");



        if(!"".equals(tempMenuTargetNo)){

            String menuTargetNo = request.getParameter("menuTargetNo");

            manageVO =menuManageService.getSelectMenuInfo(menuTargetNo);

            InfoCmsMenuAuthVO authVO = new InfoCmsMenuAuthVO();
            authVO.setCmsMemuId(menuTargetNo);
            //로그인 체크
            LoginVO user = (LoginVO)((HttpServletRequest) request).getSession().getAttribute("loginVO");


            //메뉴 클릭 정보 저장 처리
            InfoCmsVisitVO infoCmsVisitVO = new InfoCmsVisitVO();
            infoCmsVisitVO =  InfoVisitFactory.fromRequest(request,menuTargetNo);
            infoCmsVisitVO.setVistUserId((user == null || user.getUniqId() == null) ? "" : user.getUniqId());



            infoCmsVisitService.getLogInfoCmsVisit(infoCmsVisitVO);
            if(user == null || user.getUniqId() == null) {
                authVO = new InfoCmsMenuAuthVO();
            }else{


//                //관리자 일때만...
//                if("USR".equals(user.getUserSe())){
//
//                    authVO.setCloudAllYn(1);
//                    authVO.setOrganAllYn(1);
//                    manageVO.setAuthVO(authVO);
//
//                }else{

                    authVO.setOrgnzt(user.getOrgnztId());
                    authVO.setGroup(user.getGroupId());
                    authVO.setUser(user.getUniqId());
                    authVO.setRole("");
                    authVO =infoCmsMenuAuthService.getCmsMenuAuth(authVO);
                    manageVO.setAuthVO(authVO);
            //    }




            }


        }else{

            manageVO = new InfoMenuManageVO();
        }
        request.setAttribute("menuInfo",manageVO);
        return super.preHandle(request, response, handler);
    }




}
