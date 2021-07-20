package gsis.com.cms.data.web;

import egovframework.com.cmm.annotation.IncludedInfo;
import gsis.com.cms.data.vo.JejuDataVO;
import gsis.com.cms.thema.service.JewThemaInfoService;
import gsis.com.cms.thema.vo.JewThemaInfoVO;
import infomind.com.cmm.web.BaseAjaxController;
import infomind.com.utils.web.InfoViewUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@Controller
public class JejuDataController  extends BaseAjaxController {



    @Resource(name="JewThemaInfoService")
    private JewThemaInfoService jewThemaInfoService;



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



    /**등록화면 */
    @RequestMapping(value="/cms/gsis/data/View.do")
    public String View(@ModelAttribute("searchVO") JewThemaInfoVO searchVO, ModelMap model) throws Exception{


        JewThemaInfoVO jewThemaInfoVO =jewThemaInfoService.selectThemaInfo(searchVO);


        String url = pagePath +jewThemaInfoVO.getThemaGroupId()+"/"+jewThemaInfoVO.getThemaId()+"/";

        model.addAttribute("view",jewThemaInfoVO);

        return InfoViewUtils.adminJsView(url,"View","axmodal");
    }





}
