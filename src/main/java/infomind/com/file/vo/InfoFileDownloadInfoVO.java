package infomind.com.file.vo;
import infomind.com.ext.vo.CmsSearchVO;
import lombok.Data;

@Data
public class InfoFileDownloadInfoVO extends CmsSearchVO {


    private Integer fileDownloadSno;
    private String atchFileId="";
    private String fileSn="";
    private String ip="";
    private String os="";
    private String browser="";
    private String loc="";
    private String userAgent="";
    private String regDt="";
    private String regId="";

    private String browserS="";
    private String refererUrl="";
    private String isDeviec="";

}
