package api.service.bus.service.impl;

import api.service.bus.dto.JejuBusLocationDTO;
import api.service.bus.service.JejuBusApiServcie;
import api.service.bus.vo.JejuBusLocationVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service("JejuBusApiServcie")
public class JejuBusApiServcieImpl extends EgovAbstractServiceImpl implements JejuBusApiServcie {


    @Override
    public List<JejuBusLocationVO> busLocation(String route) {


        RestTemplate restTemplate = new RestTemplate ();
        String url = "http://busopen.jeju.go.kr/OpenAPI/service/bis/BusLocation?route=" + route;
        JejuBusLocationDTO jsonResponse = restTemplate.getForObject ( url, JejuBusLocationDTO.class );






        return jsonResponse.getBody().getItems();
    }
}
