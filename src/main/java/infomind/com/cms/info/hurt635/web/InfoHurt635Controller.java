package infomind.com.cms.info.hurt635.web;

import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.annotation.IncludedInfo;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

import infomind.com.cms.info.hurt635.service.InfoHurt635Service;
import infomind.com.cms.info.hurt635.vo.InfoHurt635VO;
import infomind.com.cms.info.hurt635.vo.InfoHurt635Validator;
import infomind.com.utils.web.InfoViewUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Controller
public class InfoHurt635Controller {



    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;

    @Resource(name = "InfoHurt635Service")
    protected InfoHurt635Service infoHurt635Service;

    private String pagePath ="info/hurt635/";

    @IncludedInfo(name="아이피 권한 관리",listUrl="/cms/info/hurt635/hurt635List.do", order = 1111 ,gid = 60)
    @RequestMapping(value="/cms/info/hurt635/hurt635List.do")
    public String selectInfoList(@ModelAttribute("searchVO") InfoHurt635VO searchVO, ModelMap model) throws Exception {
        /** EgovPropertyService.sample */
        searchVO.setPageUnit(propertiesService.getInt("pageUnit"));
        searchVO.setPageSize(propertiesService.getInt("pageSize"));

        /** pageing */
        PaginationInfo paginationInfo = new PaginationInfo();
        paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
        paginationInfo.setRecordCountPerPage(searchVO.getPageUnit());
        paginationInfo.setPageSize(searchVO.getPageSize());

        searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
        searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
        searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

        List<?> list = infoHurt635Service.selectHurt635List(searchVO);

        model.addAttribute("list", list);
        model.addAttribute("searchVO", searchVO);

        int totalCnt = infoHurt635Service.selectHurt635TotalCount(searchVO);
        paginationInfo.setTotalRecordCount(totalCnt);
        model.addAttribute("paginationInfo", paginationInfo);

        return InfoViewUtils.adminTilesView(pagePath,"InfoHurt635List","sub");
    }

    @RequestMapping(value="/cms/info/hurt635/InfoHurt635View.do")
    public String registView(
            @RequestParam Map<?, ?> commandMap,
            @ModelAttribute("resultVO") InfoHurt635VO vo, BindingResult bindingResult, ModelMap model)throws Exception {

        return InfoViewUtils.adminTilesView(pagePath,"InfoHurt635Regist","sub");

    }
    @RequestMapping("/cms/info/hurt635/insertHurt635.do")
    public String insertSite(
            @ModelAttribute("resultVO") InfoHurt635VO infoHurt635VO,
            BindingResult bindingResult, ModelMap model) throws Exception {

        InfoHurt635Validator mValidator = new InfoHurt635Validator();
        mValidator.validate(infoHurt635VO, bindingResult);

        if(bindingResult.hasErrors()) {
            return InfoViewUtils.adminTilesView(pagePath,"InfoHurt635Regist","sub");
        }

        if(infoHurt635VO.getKeySeq() != null){
            InfoHurt635VO result = infoHurt635Service.selectHurt635(infoHurt635VO);
            if(result != null){
                model.addAttribute("message", "이미 등록된 아이디 입니다");
                return InfoViewUtils.adminTilesView(pagePath,"InfoHurt635Regist","sub");
            }
        }

        LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
        infoHurt635VO.setRegId((user == null || user.getUniqId() == null) ? "" : user.getUniqId());
        infoHurt635VO.setModId((user == null || user.getUniqId() == null) ? "" : user.getUniqId());

        infoHurt635Service.insertHurt635(infoHurt635VO);
        return "forward:/cms/info/hurt635/hurt635List.do";
    }
    @RequestMapping("/cms/info/hurt635/UpdateHurt635View.do")
    public String updateView(
            @ModelAttribute("loginVO") LoginVO loginVO,
            @ModelAttribute("searchVO") InfoHurt635VO searchVO, ModelMap model) throws Exception {

        InfoHurt635VO result = infoHurt635Service.selectHurt635(searchVO);
        model.addAttribute("resultVO", result);
        return InfoViewUtils.adminTilesView(pagePath,"InfoHurt635Updt","sub");
    }
    @RequestMapping("/cms/info/hurt635/updateHurt635.do")
    public String updateHurt635(
            @ModelAttribute("resultVO") InfoHurt635VO vo,
            BindingResult bindingResult, ModelMap model) throws Exception {
        //체크
        InfoHurt635Validator mValidator = new InfoHurt635Validator();
        mValidator.validate(vo, bindingResult);

        // 오류여부 확인
        if(bindingResult.hasErrors()) {
            return InfoViewUtils.adminTilesView(pagePath,"InfoHurt635Updt","sub");
        }

        LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
        vo.setModId((user == null || user.getUniqId() == null) ? "" : user.getUniqId());

        infoHurt635Service.updateHurt635(vo);
        return "forward:/cms/info/hurt635/hurt635List.do";
    }
}
