package infomind.com.report.jasperreports.vo;

import com.google.gson.Gson;
import infomind.com.ext.vo.BaseVO;
import lombok.Data;
import lombok.ToString;

import java.util.*;

@Data
@ToString
public class ReportBaseVO{

    private String reportPath ="";
    private String reportFileNm ="";
    private String reportDownloadFileNm ="report";
    private String jsonParameterList ="";


    private  List<ReportBaseParameterVO> params = new ArrayList<>();


    public Map<String, Object> getParamsMap(){

        List<ReportBaseParameterVO> listParams = this.params;
        Map<String, Object> paramsMap = new HashMap<String, Object>();

        for(ReportBaseParameterVO mapVo :listParams){
            paramsMap.put(mapVo.getParamsKey(),mapVo.getParamsVal());

        }
        return paramsMap;

    }



}
