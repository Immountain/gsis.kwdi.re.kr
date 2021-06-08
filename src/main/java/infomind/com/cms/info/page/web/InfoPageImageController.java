package infomind.com.cms.info.page.web;


import egovframework.com.cmm.EgovMessageSource;

import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.annotation.IncludedInfo;

import egovframework.com.cmm.util.EgovUserDetailsHelper;

import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

import infomind.com.cms.info.page.service.InfoPageImageService;

import infomind.com.cms.info.page.vo.InfoPageImageGroupVO;

import infomind.com.cms.info.page.vo.InfoPageImageGroupValidator;
import infomind.com.cms.info.page.vo.InfoPageInfoValidator;
import infomind.com.utils.web.InfoViewUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.annotation.Resource;
import java.util.*;

@Controller
public class InfoPageImageController {


    /**
     * EgovPropertyService
     */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;

    @Resource(name = "InfoPageImageService")
    private InfoPageImageService infoPageImageService;

    /** EgovMessageSource */
    @Resource(name="egovMessageSource")
    EgovMessageSource egovMessageSource;

    private String pagePath = "info/pageimage/";

    @Autowired
    MappingJackson2JsonView jsonView;


    @IncludedInfo(name = "페이지 이미지 관리", listUrl = "/cms/info/pageimage/selectPageImageList.do", order = 1111, gid = 60)
    @RequestMapping(value = "/cms/info/pageimage/selectPageImageList.do")
    public String selectPageImageList(@ModelAttribute("searchVO") InfoPageImageGroupVO searchVO, ModelMap model) throws Exception {


        // 내역 조회
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

        List<?> list_progrmmanage = infoPageImageService.selectPageImageList(searchVO);

        model.addAttribute("list_progrmmanage", list_progrmmanage);
        model.addAttribute("searchVO", searchVO);

        int totCnt = infoPageImageService.selectPageImageListTotCnt(searchVO);
        paginationInfo.setTotalRecordCount(totCnt);
        model.addAttribute("paginationInfo", paginationInfo);
       return InfoViewUtils.adminTilesView(pagePath, "InfoPageImageList", "sub");
    }


    @RequestMapping(value = "/cms/info/pageimage/RegistPageImageView.do")
    public String registPageInfoView(
            @RequestParam Map<?, ?> commandMap,
            @ModelAttribute("infoPageImageGroupVO") InfoPageImageGroupVO infoPageImageGroupVO, BindingResult bindingResult, ModelMap model) throws Exception {


        return InfoViewUtils.adminTilesView(pagePath, "InfoPageImageRegist", "sub");
    }


    @RequestMapping(value = "/cms/info/pageimage/insertPageImage.do", method = RequestMethod.POST)
    public String insertPageImage(@ModelAttribute("infoPageImageGroupVO") InfoPageImageGroupVO infoPageImageGroupVO,
                                  BindingResult bindingResult, ModelMap model)throws Exception {
        String resultMsg = "";

        LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();

        //체크
        InfoPageImageGroupValidator mValidator = new InfoPageImageGroupValidator();
        mValidator.validate(infoPageImageGroupVO, bindingResult);

        // 오류여부 확인
        if(bindingResult.hasErrors()) {
            return InfoViewUtils.adminTilesView(pagePath, "InfoPageImageRegist", "sub");
        }



        if(infoPageImageGroupVO.getImageId() != null){
            InfoPageImageGroupVO result = infoPageImageService.selecPageImageDetail(infoPageImageGroupVO);
            if(result != null){
                model.addAttribute("message", "이미 등록된 아이디 입니다");
                return InfoViewUtils.adminTilesView(pagePath,"InfoPageImageRegist","sub");
            }
        }


        infoPageImageGroupVO.setRegId((user == null || user.getUniqId() == null) ? "" : user.getUniqId());
        infoPageImageService.insertPageImage(infoPageImageGroupVO);

        resultMsg = egovMessageSource.getMessage("success.common.insert");
        model.addAttribute("resultMsg", resultMsg);
        return "forward:/cms/info/pageimage/selectPageImageList.do";

    }



    @RequestMapping("/cms/info/pageimage/UpdatePageImageView.do")
    public String UpdatePageImageView(@ModelAttribute("loginVO") LoginVO loginVO,
                                           @ModelAttribute("searchVO") InfoPageImageGroupVO searchVO, ModelMap model)
            throws Exception {

        InfoPageImageGroupVO result = infoPageImageService.selecPageImageDetail(searchVO);

        model.addAttribute("infoPageImageGroupVO", result);
        return InfoViewUtils.adminTilesView(pagePath,"InfoPageImageUpdt","sub");
    }


    @RequestMapping("/cms/info/pageimage/updatePageImage.do")
    public String updatePageImage(@ModelAttribute("infoPageImageGroupVO") InfoPageImageGroupVO infoPageImageGroupVO,
                                 BindingResult bindingResult, ModelMap model) throws Exception {
        String resultMsg = "";
        LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();


        //체크
        InfoPageImageGroupValidator mValidator = new InfoPageImageGroupValidator();
        mValidator.validate(infoPageImageGroupVO, bindingResult);

        // 오류여부 확인
        if(bindingResult.hasErrors()) {
            return InfoViewUtils.adminTilesView(pagePath,"InfoPageImageUpdt","sub");
        }



        infoPageImageGroupVO.setModId((user == null || user.getUniqId() == null) ? "" : user.getUniqId());
        resultMsg = egovMessageSource.getMessage("success.common.update");
        model.addAttribute("resultMsg", resultMsg);
        infoPageImageService.updatePageImage(infoPageImageGroupVO);

        return "forward:/cms/info/pageimage/selectPageImageList.do";
    }


}
