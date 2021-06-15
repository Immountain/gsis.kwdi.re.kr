package gsis.com.cms.api.web;

import gsis.com.cms.api.vo.ApiTestVO;
import infomind.com.cmm.web.BaseAjaxController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;
@Controller
public class ApiTestController extends BaseAjaxController {




    /** 마이크로데이터 grid ajax */
    @RequestMapping(value="/cms/api/test/dataObject.do")
    public ModelAndView dataObject(ApiTestVO searchVO) throws Exception{
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("jsonView");

        RestTemplate restTemplate = new RestTemplate ();
        String url = "https://kosis.kr/openapi/statisticsData.do?method=getList&apiKey=ZTA0MGI4ZGIxMDVhYWEyZDNhM2I2YWM3NWUzOTkyMWM=&format=json&jsonVD=Y&userStatsId=jinz1004z/101/DT_1B81A17/2/1/20210615181049&prdSe=Y&newEstPrdCnt=1";

        System.out.println("url===>"+url);

        HashMap<String, String> jsonResponse = restTemplate.getForObject ( url, HashMap.class );




        modelAndView.addObject("list",jsonResponse);

        return modelAndView;
    }


}
