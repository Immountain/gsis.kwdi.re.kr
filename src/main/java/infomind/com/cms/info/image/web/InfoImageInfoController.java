package infomind.com.cms.info.image.web;

import egovframework.com.cmm.annotation.IncludedInfo;
import infomind.com.cms.info.layout.vo.InfoLayoutInfoVO;
import infomind.com.utils.web.InfoViewUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class InfoImageInfoController {



    private String pagePath ="info/image/";




    @IncludedInfo(name="이미지 관리",listUrl="/cms/info/image/selectImageInfoList.do", order = 1111 ,gid = 60)
    @RequestMapping(value="/cms/info/image/selectImageInfoList.do")
    public String selectImageInfoList(@ModelAttribute("searchVO") InfoLayoutInfoVO searchVO, ModelMap model) throws Exception {

        return InfoViewUtils.adminTilesView(pagePath,"InfoImageInfoList","sub");
    }


    @RequestMapping(value="/cms/info/image/RegistImageInfoView.do")
    public String RegistImageInfoView(
            @RequestParam Map<?, ?> commandMap,
            @ModelAttribute("infoLayoutInfoVO") InfoLayoutInfoVO infoLayoutInfoVO, BindingResult bindingResult, ModelMap model)throws Exception {



        return InfoViewUtils.adminTilesView(pagePath,"InfoImageInfoRegist","sub");
    }


}
