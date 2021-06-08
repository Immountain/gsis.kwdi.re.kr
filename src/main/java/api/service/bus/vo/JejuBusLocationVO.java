package api.service.bus.vo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlRootElement;


@Getter
@Setter
@XmlRootElement(name="item")
public class JejuBusLocationVO {

   private float localX =0;
   private float localY =0;
   private String lowPlateTp="";
   private String mobiNum="";
   private String plateNo="";
   private String routeId="";
   private String routeNm="";
   private String routeSubNm="";
   private String routeTp="";
   private String stationId="";
   private String stationNm="";
   private String stationOrd="";
   private String updnDir="";


}
