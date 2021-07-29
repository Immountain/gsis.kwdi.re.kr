package gsis.com.cms.data.web;

import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.annotation.IncludedInfo;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import gsis.com.cms.data.service.JejuDataService;
import gsis.com.cms.data.vo.JejuDataVO;
import gsis.com.cms.data.vo.JewThemaFileHisVO;
import gsis.com.cms.datainfo.jgAtitle.vo.JewA03TiileDataVO;
import gsis.com.cms.thema.service.JewThemaInfoService;
import gsis.com.cms.thema.vo.JewThemaGroupVO;
import gsis.com.cms.thema.vo.JewThemaInfoVO;
import infomind.com.cmm.api.ApiResponse;
import infomind.com.cmm.web.BaseAjaxController;
import infomind.com.utils.web.InfoViewUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

@Controller
public class JejuDataController  extends BaseAjaxController {



    @Resource(name="JewThemaInfoService")
    private JewThemaInfoService jewThemaInfoService;

    @Resource(name="JejuDataService")
    private JejuDataService jejuDataService;



    private String pagePath = "datainfo/";

    /**목록*/
    @IncludedInfo(name="여성가족", listUrl = "/cms/gsis/data/List.do", order = 1111, gid = 60)
    @RequestMapping(value="/cms/gsis/data/List.do")
    public String List(@ModelAttribute("searchVO") JejuDataVO searchVO, ModelMap model)throws Exception{




        JewThemaInfoVO jewThemaInfoVO = new JewThemaInfoVO();
        jewThemaInfoVO.setThemaId(searchVO.getBoardId());
        jewThemaInfoVO =jewThemaInfoService.selectThemaInfo(jewThemaInfoVO);
        String url = pagePath +jewThemaInfoVO.getThemaGroupId()+"/"+jewThemaInfoVO.getThemaId()+"/";

        model.addAttribute("view",jewThemaInfoVO);

        return InfoViewUtils.adminJsView(url,"List","ax5ui");
    }



    /**엑셀업로드 */
    @RequestMapping(value="/cms/gsis/data/excel.do")
    public String excel(@ModelAttribute("searchVO") JewThemaInfoVO searchVO, ModelMap model) throws Exception{


        JewThemaInfoVO jewThemaInfoVO =jewThemaInfoService.selectThemaInfo(searchVO);


        String url = pagePath +jewThemaInfoVO.getThemaGroupId()+"/"+jewThemaInfoVO.getThemaId()+"/";

        model.addAttribute("view",jewThemaInfoVO);

        return InfoViewUtils.adminJsView(url,"Excel","axmodal");
    }


    /**등록화면 */
    @RequestMapping(value="/cms/gsis/data/View.do")
    public String View(@ModelAttribute("searchVO") JewThemaInfoVO searchVO, ModelMap model) throws Exception{


        JewThemaInfoVO jewThemaInfoVO =jewThemaInfoService.selectThemaInfo(searchVO);


       // String url = pagePath +jewThemaInfoVO.getThemaGroupId()+"/"+jewThemaInfoVO.getThemaId()+"/";

        model.addAttribute("view",jewThemaInfoVO);

        return InfoViewUtils.adminJsView(pagePath,"TitleInfoView","axmodal");
    }




    /**
     * 리스트
     * @param searchVO
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/cms/gsis/data/fileHisList.do")
    public ModelAndView List(@RequestBody JewThemaFileHisVO searchVO) throws Exception{
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("jsonView");
        modelAndView.addObject("list",jejuDataService.selectJewThemaFileHis(searchVO));

        return modelAndView;
    }



    @RequestMapping(value="/cms/gsis/data/fileHisSave.do")
    @ResponseBody
    public ApiResponse fileHisSave(JewThemaFileHisVO jewThemaFileHisVO) throws Exception{

        LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
        jewThemaFileHisVO.setRegId((user == null || user.getUniqId() == null) ? "" : user.getUniqId());

        jejuDataService.insertJewThemaFileHis(jewThemaFileHisVO);

        return ok();
    }





}
