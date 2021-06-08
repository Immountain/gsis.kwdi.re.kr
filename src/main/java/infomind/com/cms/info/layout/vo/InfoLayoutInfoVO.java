package infomind.com.cms.info.layout.vo;

import infomind.com.ext.vo.CmsSearchVO;
import lombok.Data;

import static infomind.com.utils.web.ReplaceTag.ReplaceTag;

@Data
public class InfoLayoutInfoVO extends CmsSearchVO {


    private String layoutId ="";
    private String layoutNm="";
    private String regDt="";
    private String modDt="";
    private String regId="";
    private String modId="";

    private String layoutType="";
    private String contentInfo="";
    private String modType="";
    private String layoutHisSno="0";




    public String getContentInfoDecode(){

        String tempString =this.contentInfo;
        tempString=ReplaceTag(tempString,"decode");

        return tempString;
    }



}
