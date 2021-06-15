package gsis.com.cms.api.web;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Controller
public class ApiTestController implements InitializingBean {

    private final String API_KOSIS_SERVER_URL = "https://kosis.kr/openapi/statisticsData.do";

    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public void afterPropertiesSet() throws Exception {
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        // 모지리 코시스가 TEXT_HTML로 JSON을 전송해온다
        converter.setSupportedMediaTypes(Arrays.asList(MediaType.TEXT_HTML, MediaType.APPLICATION_JSON));
        restTemplate.getMessageConverters().add(converter);
    }

    /**
     * 마이크로데이터 grid ajax
     */
    @RequestMapping(value = "/cms/api/test/dataObject.do")
    public ModelAndView dataObject() throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("jsonView");

        try {
            HttpHeaders headers = new HttpHeaders();
            headers.set("Accept", "application/json");

            UriComponents url = UriComponentsBuilder.fromHttpUrl(API_KOSIS_SERVER_URL)
                    .queryParam("method", "getList")
                    .queryParam("apiKey", "ZTA0MGI4ZGIxMDVhYWEyZDNhM2I2YWM3NWUzOTkyMWM=")
                    .queryParam("format", "json")
                    .queryParam("jsonVD", "Y")
                    .queryParam("userStatsId", "jinz1004z/101/DT_1B81A17/2/1/20210615181049")
                    .queryParam("prdSe", "Y")
                    .queryParam("newEstPrdCnt", "1")
                    .build();

            HttpEntity<?> entity = new HttpEntity<>(headers);

            System.out.println(String.format("[IN_CMS_API_CALL] %s", url.toUriString()));

            ResponseEntity<List<Map<String, String>>> response = restTemplate.exchange(
                    url.toUriString(),
                    HttpMethod.GET,
                    entity,
                    new ParameterizedTypeReference<List<Map<String, String>>>() {
                    });

            List<Map<String,String>> body = response.getBody();

            modelAndView.addObject("list", body);
        }catch (Exception e) {
            modelAndView.addObject("list", "body");
            e.printStackTrace();
        }
        return modelAndView;
    }
}
