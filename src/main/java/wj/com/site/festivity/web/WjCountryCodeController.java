package wj.com.site.festivity.web;

import egovframework.rte.fdl.property.EgovPropertyService;
import infomind.com.cmm.web.BaseController;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import wj.com.site.festivity.service.WjCountryCodeService;
import wj.com.site.festivity.vo.WjCountryCodeVO;
import javax.annotation.Resource;
import java.util.List;


@Controller
public class WjCountryCodeController extends BaseController {



    /**
     * EgovPropertyService
     */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;

    @Resource(name = "WjCountryCodeService")
    private WjCountryCodeService wjCountryCodeService;


    /**
     * 제주인 출신지 코드
     * @param codeSearchVO
     * @return
     * @throws Exception
     */
    @RequestMapping("/site/country/code/List.do")
    @ResponseBody
    public  List<WjCountryCodeVO> selectComtccmmnclcodeList(WjCountryCodeVO codeSearchVO  , ModelMap model) throws Exception {

        List<WjCountryCodeVO> list =wjCountryCodeService.getSelectCountryCodeList(codeSearchVO);
        return list;

    }

}
