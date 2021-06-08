package infomind.com.cms.info.board.vo;
import infomind.com.ext.vo.CmsSearchVO;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class InfoBoardItemHisVO extends CmsSearchVO {

    private String itemIdHis="";
    private String modType="";
    private String itemId="";
    private String idCode="";
    private String boardId="";
    private String title="";
    private String boardContent="";
    private String memo="";
    private String noticeYn="";
    private String noticeStartDate="";
    private String noticeEndDate="";
    private String secretYn="";
    private String password="";
    private String inquireType="";
    private String commentYn="";
    private String boardFile="";
    private String file01="";
    private String file02="";
    private String file03="";
    private String file04="";
    private String file05="";
    private String category="";
    private String categories="";
    private String linkType="";
    private String linkUrl="";
    private String readCnt="";
    private String temp1="";
    private String temp2="";
    private String temp3="";
    private String temp4="";
    private String temp5="";
    private String use_yn="";
    private String deleteYn="";
    private String deleteType="";
    private String regId="";
    private String regDt="";
    private String regIp="";
    private String modId="";
    private String modDt="";
    private String modIp="";

    /** 서브 키 */
    private String subKey="";

}
