package wj.com.cms.wj.jeju.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import infomind.com.cms.uss.umt.vo.InfoMberManageVO;
import infomind.com.ext.vo.CmsSearchVO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import wj.com.cms.wj.act.vo.WjActInfoVO;

import java.util.List;


@Getter
@Setter
@ToString
public class WjJejuPeopleVO extends CmsSearchVO {
    private String userId;   //varchar(20)   not null comment '등록아이디',
    private String korName;   //varchar(20)   null comment '성명_한글',
    private String enName;   //varchar(20)   null comment '성명_영문',
    private String cnName;   //varchar(20)   null comment '성명_한자',
    private String atchFileId;   //varchar(20)   null comment '사진첨부',
    private String sex;   //varchar(2)    null comment '성별',
    private String birth;   //varchar(8)    null comment '생년월일',
    private String city;   //varchar(2)    null comment '출신지_대',
    private String town;   //varchar(2)    null comment '출신지_중',
    private String village;   //varchar(2)    null comment '출신지_소',
    private String addressEtc;   //varchar(200)  null comment '출신지',
    private String country;   //varchar(2)    null comment '거주지_대',
    private String countryCity;   //varchar(2)    null comment '거주지_소',
    private String countryEtc;   //varchar(200)  null comment '거주지',
    private String phone;   //varchar(20)   null comment '휴대폰',
    private String tel1;   //varchar(20)   null comment '전화번호_자택',
    private String tel2;   //varchar(20)   null comment '전화번호_직장',
    private String emaill;   //varchar(20)   null comment '이메일',
    private String company;   //varchar(20)   null comment '소속기관',
    private String spot;   //varchar(20)   null comment '소속기관_직위',
    private String schoolE;   //varchar(20)   null comment '학력_초등학교',
    private String schoolEYear;   //varchar(4)    null comment '초등학교_졸업년도',
    private String schoolM;   //varchar(20)   null comment '학력_중학교',
    private String schoolMYear;   //varchar(4)    null comment '중학교_졸업년도',
    private String schoolH;   //varchar(20)   null comment '학력_고등학교',
    private String schoolHYear;   //varchar(4)    null comment '고등학교_졸업년도',
    private String schoolU;   //varchar(20)   null comment '학력_대학교',
    private String schoolUYear;   //varchar(4)    null comment '대학교_졸업년도',
    private String schoolG;   //varchar(20)   null comment '학력_대학원이상',
    private String schoolGYear;   //varchar(4)    null comment '대학원이상_졸업년도',
    private String edu;   //varchar(2)    null comment '최종학력',
    private String religion;   //varchar(2)    null comment '종교',
    private String religionEtc;   //varchar(200)  null comment '종교_기타',
    private String career;   //varchar(2000) null comment '경력사항',
    private String regIp;   //datetime      null comment '등록일',
    private String modIp;   //varchar(20)   null comment '등록자',
    private String password="";   //varchar(20)   null comment '등록자',
    private String uniqId;
    private String sendEmaillYn="N";
    private String langCode="";
    private String cityNm;
    private String townNm;
    private String villageNm;
    private String eduNm;
    private String religionNm;
    private String countryNm;

    private List<WjActInfoVO> wjActInfoList;

    @JsonIgnore
    public InfoMberManageVO getGnrUser(){

        InfoMberManageVO  gnrUser = new InfoMberManageVO();

        gnrUser.setUniqId(this.uniqId);
        gnrUser.setMberId(this.userId);
        gnrUser.setMberNm(this.korName);
        gnrUser.setPassword(this.password);
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
//        gnrUser.setAreaNo(this.tel1);
//        gnrUser.setMiddleTelno(this.tel2);
//        gnrUser.setEndTelno(this.tel3);

        gnrUser.setAreaNo("000");
        gnrUser.setMiddleTelno("0000");
        gnrUser.setEndTelno("0000");

        //핸드폰번호
        gnrUser.setMoblphonNo(this.phone);
        //그룹
        gnrUser.setGroupId("GROUP_00000000000000");
        //패스워드
        gnrUser.setMberFxnum("사용안함");
        //이메일주음소
        gnrUser.setMberEmailAdres(this.emaill);



        return gnrUser;
    }


}

