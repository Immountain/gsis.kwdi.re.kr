package wj.com.site.festivity.vo;

import lombok.Data;
import lombok.ToString;

import static infomind.com.utils.web.ReplaceTag.ReplaceTag;

@Data
@ToString
public class WjSiteProgramInfoVO extends Object{


         private String programNm ="";
         private String programPlace="";
         private String programEtc="";
         private String contentInfo="";
         private String festivityId="";
         private String programSno="";
         private String atchFileId="";
         private String programDay="";
         private String programStrTime="";
         private String programEndTime="";
         private String langCode="";
         private String programCnt="0";
         private String pageType="";
         private String festivityYear="";


    public String getContentInfoDecode(){

        String tempString =this.contentInfo;
        tempString=ReplaceTag(tempString,"decode");

        return tempString;
    }



}
