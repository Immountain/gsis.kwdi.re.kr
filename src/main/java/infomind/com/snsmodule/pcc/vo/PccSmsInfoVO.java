package infomind.com.snsmodule.pcc.vo;

import infomind.com.utils.StringUtil;
import lombok.Data;

@Data
public class PccSmsInfoVO {


    private String pccSmsSno ="0";
    // 본인확인 요청번호
    private String reqNum  ="";
    //DI
    private String di="";
    //성명
    private String name="";
    //생년월일
    private String birYmd="";
    //성별
    private String sex="";
    //CI
    private String ci1="";
    //CI
    private String ci2="";
    //CI Version
    private String civersion="";
    // 본인확인결과 (Y/N)
    private String result="";
    // 인증수단
    private String cerGb="";
    // 핸드폰 번호
    private String cellNo="";
    // 이동통신사
    private String cellCorp="";
    // 검증시간
    private String cerDate="";
    private String addVer="";
    private String srvNo="";
    private String returnUrl="";
    // 결과정보
    private String retInfo="";
    private String message="";
    private String ext1="";
    private String ext2="";
    private String ext3="";
    private String ext4="";
    private String ext5="";
    private String regDt="";
    private String fgnGbn="";
    private String checkYn="";




    private String phone1;
    private String phone2;
    private String phone3;

    public void setCellNo(String cellNo) {

        String[] phone = StringUtil.getPhoneNumberHyphenAdd(cellNo,"N").split("-");
        for(int i=0; i<phone.length; i++){
            if(i==0){
                this.phone1 =phone[i];
            }else if(i==1){
                this.phone2 =phone[i];
            }else if(i==2){
            this.phone3  =phone[i];
            }

        }
      this.cellNo = cellNo;
    }
}
