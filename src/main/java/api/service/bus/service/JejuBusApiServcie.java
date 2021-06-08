package api.service.bus.service;

import api.service.bus.vo.JejuBusLocationVO;

import java.util.List;

public interface JejuBusApiServcie {

    List<JejuBusLocationVO> busLocation(String route);

}
