package infomind.com.site.vo;

import infomind.com.cms.uss.umt.vo.InfoEntrprsManageVO;
import infomind.com.cms.uss.umt.vo.InfoMberManageVO;
import lombok.Data;


@Data
public class SiteJoinVO {

    private String id="";
    private String uniqId="";
    private String name="";
    private String passwd="";
    private String type="";
    private String phone1="000";
    private String phone2="0000";
    private String phone3="0000";
    private String tel1="000";
    private String tel2="0000";
    private String tel3="0000";
    private String email="1@a.com";
    private String orgNm="";
    private String orgCode="";
    private String ownerNm="";
    private String zipNo="";
    private String addr="";
    private String detailAdres="";
    private String indutyCode="";
    private String userTy="";

    private String mberTyCode="";
    private String authorCode="";
    private String moblphonNo="";
    private String jtpOrganCd="";
    private String di="";

    private String zeusCertiYn;
    private String zeusId;
    private String jtpCloudId;
    private String chngGb;

    public InfoMberManageVO getGnrUser(){

        InfoMberManageVO  gnrUser = new InfoMberManageVO();

        gnrUser.setUniqId(this.uniqId);
        gnrUser.setMberId(this.id);
        gnrUser.setMberNm(this.name);
        gnrUser.setPassword(this.passwd);
        gnrUser.setPasswordHint("P01");
        gnrUser.setPasswordCnsr("없음");
        gnrUser.setIhidnum("사용안함");
        gnrUser.setSexdstnCode("M");
        gnrUser.setZip("00000");
        gnrUser.setAdres("없음");
        //가입상태
        gnrUser.setMberSttus("P");
        gnrUser.setDetailAdres("없음");
        //지역번호
        gnrUser.setAreaNo(this.tel1);
        gnrUser.setMiddleTelno(this.tel2);
        gnrUser.setEndTelno(this.tel3);
        //핸드폰번호
        gnrUser.setMoblphonNo(this.phone1+"-"+this.phone2+"-"+this.phone3);
        //그룹
        gnrUser.setGroupId("GROUP_00000000000000");
        //패스워드
        gnrUser.setMberFxnum("사용안함");
       //이메일주음소
        gnrUser.setMberEmailAdres(this.email);

        //기관번호
        gnrUser.setJtpOrganCd(this.jtpOrganCd);
        //인증번호
        gnrUser.setDi(this.di);

        gnrUser.setZeusId(this.zeusId);
        gnrUser.setZeusCertiYn(this.zeusCertiYn);

        return gnrUser;
    }


    public InfoEntrprsManageVO getEntUser(){


        InfoEntrprsManageVO  entUser = new InfoEntrprsManageVO();
        //유니크
        entUser.setUniqId(this.uniqId);
        //아이디
        entUser.setEntrprsmberId(this.id);
        //기업구분 C0000001:대기업 ,C0000002:중소기업 ,C0000003:다국적기업
        entUser.setEntrprsSeCode("C0000002");
        //사업자등록번 organ_mst [BUSI_REGIST_NO]
        entUser.setBizrno(this.orgCode);
        //범인등록번호
        entUser.setJurirno("");
        //회사     organ_mst [ORGAN_NM]
        entUser.setCmpnyNm(this.orgNm);
        //대표이사   organ_mst [OWNER_NM]
        entUser.setCxfc(this.ownerNm);
        //우편번호   organ_mst [ZIP_NO]
        entUser.setZip(this.zipNo);
        //주소      organ_mst [ADDRESS1]
        entUser.setAdres(this.addr);
        //상세주소   organ_mst [ADDRESS2]
        entUser.setDetailAdres(this.detailAdres);
        //기업 지역번호
        entUser.setAreaNo(this.tel1);
        //기업중간전화번호
        entUser.setEntrprsMiddleTelno(this.tel2);
        //기업 마지막 전화번호
        entUser.setEntrprsEndTelno(this.tel3);
        //패스번호
        entUser.setFxnum("");
        //업종코드    organ_mst [CARRIER_TOB]
        entUser.setIndutyCode(this.indutyCode);
        //신청인명
        entUser.setApplcntNm(this.name);
        //신청자 주민번호
        entUser.setApplcntIhidnum("");
        //기업회원상태 A:회원가입 신청상태 ,D:회원가입 삭제상태 ,P:회원가입승인상태
        entUser.setEntrprsMberSttus("A");

        //질문
        entUser.setEntrprsMberPasswordHint("P01");
        //답변
        entUser.setEntrprsMberPasswordCnsr("없음");
        //패스워드
        entUser.setEntrprsMberPassword(this.passwd);
        //그룹아이디   GROUP_00000000000000 0번째 그룹
        entUser.setGroupId("GROUP_00000000000000");
        //신청자이메일 주소
        entUser.setApplcntEmailAdres(this.email);
        //신청자 전화번호
        entUser.setMbtlnum(this.phone1+"-"+this.phone2+"-"+this.phone3);

        entUser.setJtpOrganCd(this.jtpOrganCd);

        //인증번호
        entUser.setDi(this.di);

        entUser.setZeusId(this.zeusId);
        entUser.setZeusCertiYn(this.zeusCertiYn);
        entUser.setJtpCloudId(this.jtpCloudId);

    return entUser;

    }



    public void setMoblphonNo(String moblphonNo) {

        String[] phone = moblphonNo.split("-");
        for(int i=0; i<phone.length; i++){
            if(i==0){

                this.phone1 =phone[i];

            }else if(i==1){
                this.phone2 =phone[i];

            }else if(i==2){

                this.phone3  =phone[i];
            }

        }



        this.moblphonNo = moblphonNo;
    }
}
