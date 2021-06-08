package wj.com.site.festivity.web;

import egovframework.rte.fdl.property.EgovPropertyService;
import infomind.com.cms.info.site.vo.InfoSiteMenuVO;
import infomind.com.file.service.InfoFileService;
import infomind.com.file.vo.InfoFileDetailVO;
import infomind.com.utils.web.InfoViewUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import wj.com.site.festivity.service.WjSiteProgramService;
import wj.com.site.festivity.vo.WjSiteFestivityInfoVO;
import wj.com.site.festivity.vo.WjSiteFestivityScheduleVO;
import wj.com.site.festivity.vo.WjSiteProgramInfoVO;
import wj.com.site.festivity.vo.WjSiteProgramSubInfoVO;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class WjSiteProgramController {


    /**
     * EgovPropertyService
     */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;

}
