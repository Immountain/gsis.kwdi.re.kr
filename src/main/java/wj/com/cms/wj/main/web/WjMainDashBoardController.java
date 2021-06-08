package wj.com.cms.wj.main.web;

import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import infomind.com.utils.DateUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import wj.com.cms.wj.jeju.service.WjJejuPeopleService;
import wj.com.cms.wj.main.service.WjMainDashBoardServcie;
import wj.com.cms.wj.main.vo.WjMainDashBoardVO;

import javax.annotation.Resource;
import java.util.*;

@RequestMapping(value = {"/cms/wj"})
@Controller
public class WjMainDashBoardController {


    /**
     * EgovPropertyService
     */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;

    @Resource(name = "WjMainDashBoardServcie")
    private WjMainDashBoardServcie wjMainDashBoardServcie;


    @GetMapping(value = "/dashboard/getLastWeekDaysVisitCount.do")
    public ModelAndView getWeekDaySiteMain() throws Exception {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("jsonView");

        modelAndView.addObject("data", wjMainDashBoardServcie.getWeekDaySiteMain(new WjMainDashBoardVO()));

        return modelAndView;
    }
}
