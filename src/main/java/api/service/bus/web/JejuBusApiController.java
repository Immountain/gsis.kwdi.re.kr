package api.service.bus.web;

import api.service.bus.service.JejuBusApiServcie;
import api.service.bus.vo.JejuBusLocationVO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;


@Controller
public class JejuBusApiController {




    @Resource(name = "JejuBusApiServcie")
    private JejuBusApiServcie jejuBusApiServcie;
    /**
     * 공통분류코드
     * @param route
     * @return
     * @throws Exception
     */
    @RequestMapping("/cms/api/busLocation.do")
    @ResponseBody
    public List<JejuBusLocationVO> busLocation(String route  , ModelMap model) throws Exception {

        List<JejuBusLocationVO> list =jejuBusApiServcie.busLocation(route);
        return list;

    }


}
