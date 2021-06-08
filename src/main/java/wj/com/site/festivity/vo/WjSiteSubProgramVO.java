package wj.com.site.festivity.vo;

import lombok.Data;
import lombok.ToString;

import static infomind.com.utils.web.ReplaceTag.ReplaceTag;

@Data
@ToString
public class WjSiteSubProgramVO {


    private String festivityId ="";
    private String festivityProgramSno="";
    private String title="";
    private String pageType="";
    private String atchFileId="";
    private String orderCnt="";
    private String subProgramTitle="";
    private String contentInfo="";
    private String langCode="";
    private String subProgramCnt="0";


    public String getContentInfoDecode(){

        String tempString =this.contentInfo;
        tempString=ReplaceTag(tempString,"decode");

        return tempString;
    }




}
