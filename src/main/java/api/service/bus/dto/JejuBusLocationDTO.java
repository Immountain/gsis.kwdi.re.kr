package api.service.bus.dto;

import api.service.bus.vo.JejuBusLocationVO;
import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@Getter
@Setter
@XmlRootElement(name = "response")
public class JejuBusLocationDTO {



    private ResponseHeader header;
    private ResponseBody body;


    @Getter
    @Setter
    @XmlRootElement(name = "header")
    static public class ResponseHeader{
        private String resultCode="";
        private String resultMsg="";

    }


    @XmlRootElement(name = "body")
    static public class ResponseBody{


        private List<JejuBusLocationVO> items;

        @XmlElementWrapper(name = "items")
        @XmlElement(name="item")
        public List<JejuBusLocationVO> getItems() {
            return items;
        }

        public void setItems(List<JejuBusLocationVO> items) {
            this.items = items;
        }
    }


}
