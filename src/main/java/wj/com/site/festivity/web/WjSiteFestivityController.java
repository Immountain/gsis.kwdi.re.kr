package wj.com.site.festivity.web;

import egovframework.rte.fdl.property.EgovPropertyService;
import infomind.com.cmm.web.BaseController;
import infomind.com.cms.info.site.vo.InfoSiteMenuVO;
import infomind.com.cms.info.site.vo.InfoSiteVO;
import infomind.com.file.service.InfoFileService;
import infomind.com.file.vo.InfoFileDetailVO;
import infomind.com.utils.StreamUtils;
import infomind.com.utils.web.InfoViewUtils;
import infomind.com.utils.web.InfoWebUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import wj.com.cms.wj.festivity.vo.WjFestivityInfoVO;
import wj.com.site.festivity.service.WjSiteFestivityService;
import wj.com.site.festivity.service.WjSiteProgramService;
import wj.com.site.festivity.service.WjSiteSubProgramService;
import wj.com.site.festivity.vo.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

@RequestMapping(value = {"/festivity"})
@Controller
public class WjSiteFestivityController extends BaseController {

    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;

    @Resource(name = "WjSiteFestivityService")
    private WjSiteFestivityService wjSiteFestivityService;

    @Resource(name = "WjSiteProgramService")
    private WjSiteProgramService wjSiteProgramService;

    @Resource(name = "WjSiteSubProgramService")
    private WjSiteSubProgramService wjSiteSubProgramService;


    @Resource(name = "InfoFileService")
    private InfoFileService infoFileService;

    @RequestMapping("/list.do")
    public String list(HttpServletRequest request, ModelMap model
            , @RequestAttribute(value = "menuInfo", required = false) InfoSiteMenuVO menuInfo
            , @ModelAttribute("searchVO") WjSiteFestivityInfoVO searchVO
    ) throws Exception {

        InfoSiteVO infoSite = InfoWebUtils.getCurrentSiteInfo(request);

        if (StringUtils.isEmpty(searchVO.getFestivityYear())) {
            searchVO.setFestivityYear(wjSiteFestivityService.getLastFesitivityYear());
        }

        List<WjSiteFestivityInfoVO> festivityYearList = wjSiteFestivityService.getSelectSiteFestivityYearList(searchVO);
        searchVO.setLangCode(infoSite.getLangCd());

        String layout = "";
        if (menuInfo != null) {
            layout = menuInfo.getLayout();
        }

        List<WjSiteFestivityInfoVO> wjFestivityList = wjSiteFestivityService.getSelectSiteFestivityInfoList(searchVO);

        List<WjSiteFestivityApplicationVO> applicationVOList = wjSiteFestivityService.getSelectSiteWjSiteFestivityApplicationList(searchVO);


        model.addAttribute("wjFestivityList", wjFestivityList);
        model.addAttribute("festivityYearList", festivityYearList);
        model.addAttribute("applicationList", applicationVOList);

        return InfoViewUtils.wjPpageContentView("festivity", "festivityList", layout);
    }

    /**
     * 페스티벌 소개페이지
     *
     * @param request
     * @param model
     * @param menuInfo
     * @param searchVO
     * @return
     * @throws Exception
     */
    @RequestMapping(value = {"/", "/info.do"}, method = RequestMethod.GET)
    public String info(HttpServletRequest request, ModelMap model
            , @RequestAttribute(value = "menuInfo", required = false) InfoSiteMenuVO menuInfo
            , @ModelAttribute("searchVO") WjSiteFestivityInfoVO searchVO
    ) throws Exception {

        InfoSiteVO infoSite = InfoWebUtils.getCurrentSiteInfo(request);

        if (StringUtils.isEmpty(searchVO.getFestivityId())) {
            searchVO.setFestivityId(wjSiteFestivityService.getLastFesitivityId());
        }
        searchVO.setLangCode(infoSite.getLangCd());

        String layout = "";
        if (menuInfo != null) {
            layout = menuInfo.getLayout();
        }

        WjSiteFestivityInfoVO wjFestivityInfoVO = wjSiteFestivityService.getSelectSiteFestivityInfo(searchVO);
        List<WjSiteFestivityScheduleVO> scheduleList = wjSiteFestivityService.getSelectSiteWjFestivitySchedule(searchVO);

        HashMap<String, List<WjSiteFestivityScheduleVO>> scheduleDayGroup = scheduleList.stream()
                .collect(Collectors.groupingBy(WjSiteFestivityScheduleVO::getScheduleDay, LinkedHashMap::new, toList()));

        //참가신청 관련
        List<WjSiteFestivityApplicationVO> applicationVOList = wjSiteFestivityService.getSelectSiteWjSiteFestivityApplicationList(searchVO);


        List<WjSiteFestivityInfoVO> festivityYearList = wjSiteFestivityService.getSelectSiteFestivityYearList(searchVO);

        model.addAttribute("view", wjFestivityInfoVO);
        model.addAttribute("scheduleDayGroup", scheduleDayGroup);
        model.addAttribute("applicationList", applicationVOList);
        model.addAttribute("festivityYearList", festivityYearList);

        return InfoViewUtils.wjPpageContentView("festivity", "festivityInfo", layout);
    }

    /**
     * 주요프로그램
     *
     * @param request
     * @param model
     * @param menuInfo
     * @param searchVO
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/program.do", method = RequestMethod.GET)
    public String list(HttpServletRequest request, ModelMap model
            , @RequestAttribute(value = "menuInfo", required = false) InfoSiteMenuVO menuInfo
            , @ModelAttribute("searchVO") WjSiteProgramInfoVO searchVO
    ) throws Exception {

        InfoSiteVO infoSite = InfoWebUtils.getCurrentSiteInfo(request);

        if (StringUtils.isEmpty(searchVO.getFestivityId())) {
            searchVO.setFestivityId(wjSiteFestivityService.getLastFesitivityId());
        }
        searchVO.setLangCode(infoSite.getLangCd());

        String layout = "";
        if (menuInfo == null) {
            layout = "";
        } else {
            layout = menuInfo.getLayout();
        }

        List<WjSiteProgramInfoVO> categoryList = Collections.emptyList();
        int index = 0, prev = 0, next = 0;
        WjSiteProgramInfoVO programInfo;
        WjSiteProgramInfoVO prevProgramInfo;
        WjSiteProgramInfoVO nextProgramInfo;

        List<InfoFileDetailVO> fileLsit = Collections.emptyList();
        List<WjSiteProgramSubInfoVO> forumList = Collections.emptyList();


        //대회정보 가져온다
        WjSiteFestivityInfoVO wjSiteFestivityInfoVO = new WjSiteFestivityInfoVO();
        wjSiteFestivityInfoVO.setFestivityId(searchVO.getFestivityId());
        wjSiteFestivityInfoVO.setLangCode(infoSite.getLangCd());
        WjSiteFestivityInfoVO wjFestivityInfoVO = wjSiteFestivityService.getSelectSiteFestivityInfo(wjSiteFestivityInfoVO);

        //프로그램 카테고리 정보리스트
        categoryList = wjSiteProgramService.getSelectSiteWJProgramList(searchVO);

        if (StringUtils.isEmpty(searchVO.getProgramSno())) {
            searchVO.setProgramSno(categoryList.stream().findFirst().orElse(new WjSiteProgramInfoVO()).getProgramSno());
        }

        //프로그램 정보
        programInfo = wjSiteProgramService.getSelectSiteWJProgramInfo(searchVO);

        if (programInfo != null) {
            if (StringUtils.isNotEmpty(programInfo.getProgramSno())) {
                fileLsit = infoFileService.getInfoFileList(InfoFileDetailVO.builder()
                        .atchFileId(programInfo.getAtchFileId())
                        .build());

                //forum(포럼) 리스트
                forumList = wjSiteProgramService.getSelectSiteWJProgramSubList(searchVO);
            }

            List<WjSiteProgramInfoVO> finalCategoryList = categoryList;
            index = IntStream.range(0, categoryList.size())
                    .filter(i -> finalCategoryList.get(i).getProgramSno().equals(programInfo.getProgramSno()))
                    .findFirst().getAsInt();

            prev = index - 1 < 0 ? finalCategoryList.size() - 1 : index - 1;
            next = index + 1 > (finalCategoryList.size() - 1) ? 0 : index + 1;

            model.addAttribute("prevView", categoryList.get(prev));
            model.addAttribute("nextView", categoryList.get(next));
        }
        List<WjSiteFestivityInfoVO> festivityYearList = wjSiteFestivityService.getSelectSiteFestivityYearList(null);

        model.addAttribute("view", programInfo);

        model.addAttribute("wjFestivityInfoVO", wjFestivityInfoVO);
        model.addAttribute("categoryList", categoryList);
        model.addAttribute("categorySize", categoryList.size());
        model.addAttribute("fileLsit", fileLsit);
        model.addAttribute("forumList", forumList);
        model.addAttribute("festivityYearList", festivityYearList);

        return InfoViewUtils.wjPpageContentView("festivity", "programInfo", layout);
    }


    @RequestMapping(value = "/subProgram.do", method = RequestMethod.GET)
    public String subProgram(HttpServletRequest request, ModelMap model
            , @RequestAttribute(value = "menuInfo", required = false) InfoSiteMenuVO menuInfo
            , @ModelAttribute("searchVO") WjSiteSubProgramVO searchVO
    ) throws Exception {

        InfoSiteVO infoSite = InfoWebUtils.getCurrentSiteInfo(request);

        if (StringUtils.isEmpty(searchVO.getFestivityId())) {
            searchVO.setFestivityId(wjSiteFestivityService.getLastFesitivityId());
        }
        searchVO.setLangCode(infoSite.getLangCd());

        String layout = "";
        if (menuInfo == null) {
            layout = "";
        } else {
            layout = menuInfo.getLayout();
        }

        List<WjSiteSubProgramVO> categoryList = Collections.emptyList();
        int index = 0, prev = 0, next = 0;

        WjSiteSubProgramVO subProgramInfo;

        List<InfoFileDetailVO> fileLsit = Collections.emptyList();


        //해당 행사 내용을 가져온다 가져온다
        WjSiteFestivityInfoVO wjSiteFestivityInfoVO = new WjSiteFestivityInfoVO();
        wjSiteFestivityInfoVO.setFestivityId(searchVO.getFestivityId());
        wjSiteFestivityInfoVO.setLangCode(infoSite.getLangCd());
        WjSiteFestivityInfoVO wjFestivityInfoVO = wjSiteFestivityService.getSelectSiteFestivityInfo(wjSiteFestivityInfoVO);

        //프로그램 카테고리 정보리스트
        categoryList = wjSiteSubProgramService.getSelectSiteWjSubProgramList(searchVO);

        if (StringUtils.isEmpty(searchVO.getFestivityProgramSno())) {
            searchVO.setFestivityProgramSno(categoryList.stream().findFirst().orElse(new WjSiteSubProgramVO()).getFestivityProgramSno());
        }

        //부대프로그램 정보
        subProgramInfo = wjSiteSubProgramService.getSelectSiteWjSubProgramInfo(searchVO);

        if (subProgramInfo != null) {

            if (StringUtils.isNotEmpty(subProgramInfo.getFestivityProgramSno())) {
                fileLsit = infoFileService.getInfoFileList(InfoFileDetailVO.builder()
                        .atchFileId(subProgramInfo.getAtchFileId())
                        .build());
            }

            List<WjSiteSubProgramVO> finalCategoryList = categoryList;
            index = IntStream.range(0, categoryList.size())
                    .filter(i -> finalCategoryList.get(i).getFestivityProgramSno().equals(subProgramInfo.getFestivityProgramSno()))
                    .findFirst().getAsInt();

            prev = index - 1 < 0 ? finalCategoryList.size() - 1 : index - 1;
            next = index + 1 > (finalCategoryList.size() - 1) ? 0 : index + 1;

            model.addAttribute("prevView", categoryList.get(prev));
            model.addAttribute("nextView", categoryList.get(next));
        }
        List<WjSiteFestivityInfoVO> festivityYearList = wjSiteFestivityService.getSelectSiteFestivityYearList(null);

        model.addAttribute("view", subProgramInfo);
        model.addAttribute("wjFestivityInfoVO", wjFestivityInfoVO);
        model.addAttribute("categoryList", categoryList);
        model.addAttribute("categorySize", categoryList.size());
        model.addAttribute("fileLsit", fileLsit);
        model.addAttribute("festivityYearList", festivityYearList);

        return InfoViewUtils.wjPpageContentView("festivity", "subProgramInfo", layout);
    }


}
