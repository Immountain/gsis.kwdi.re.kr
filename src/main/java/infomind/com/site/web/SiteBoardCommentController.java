package infomind.com.site.web;

import egovframework.com.cmm.LoginVO;
import infomind.com.cmm.api.ApiResponse;
import infomind.com.cmm.web.BaseAjaxController;
import infomind.com.cmm.web.BaseController;
import infomind.com.cms.info.board.service.InfoBoardCommentService;
import infomind.com.cms.info.board.vo.InfoBoardCommentVO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
public class SiteBoardCommentController extends BaseAjaxController {



    @Resource(name = "InfoBoardCommentService")
    private InfoBoardCommentService infoBoardCommentService;



    @RequestMapping("/comment/cmm/list.do")
    public String cmmList(InfoBoardCommentVO vo,ModelMap model, HttpServletRequest request) throws Exception {

         String skin ="";
         skin ="basic";

        model.addAttribute("commentList", infoBoardCommentService.selectInfoBoardComment(vo));
        String url ="board/skin/"+skin+"/comment";

        return  url;
    }



    @RequestMapping("/comment/cmm/save.do")
    @ResponseBody
    public ApiResponse cmmSave(InfoBoardCommentVO resultVO, ModelMap model,HttpServletRequest request) throws Exception {


        System.out.println("resultVO-=>"+resultVO.getInfoCommentSno());
        // 로그인VO에서  사용자 정보 가져오기
        LoginVO user = (LoginVO)((HttpServletRequest) request).getSession().getAttribute("loginVO");
        if(user == null || user.getUniqId() == null){

            return ok("로그인 권한이 필요합니다");

        }else{

            resultVO.setRegId((user == null || user.getUniqId() == null) ? "" : user.getUniqId());
            resultVO.setRegIp(request.getRemoteAddr());
            infoBoardCommentService.insertInfoBoardComment(resultVO);


        }



        return ok();

    }


    @RequestMapping("/comment/cmm/delete.do")
    @ResponseBody
    public ApiResponse cmmDelete(InfoBoardCommentVO resultVO, ModelMap model,HttpServletRequest request) throws Exception {

        // 로그인VO에서  사용자 정보 가져오기
        LoginVO user = (LoginVO)((HttpServletRequest) request).getSession().getAttribute("loginVO");
        resultVO.setRegId((user == null || user.getUniqId() == null) ? "" : user.getUniqId());
        resultVO.setRegIp(request.getRemoteAddr());

        if(user == null || user.getUniqId() == null){

            return  ok("로그인 권한이 필요합니다");

        }else{

            if(resultVO.getRegId().equals(user.getUniqId())){


                resultVO.setRegId((user == null || user.getUniqId() == null) ? "" : user.getUniqId());
                resultVO.setRegIp(request.getRemoteAddr());
                infoBoardCommentService.deleteInfoBoardComment(resultVO);

            }else{

                return ok("본인 글이 아닙니다.");
            }
        }





        return ok();
    }



}
