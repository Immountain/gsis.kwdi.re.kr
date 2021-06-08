package wj.com.cms.wj.existing.vo;

import infomind.com.ext.vo.CmsSearchVO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class WjExistingDataJejuVO extends CmsSearchVO {

        private String noSno;    //int          not null comment '자동증가
        private String id;    //varchar(100) null comment '등록아이디명',
        private String gubun;    //varchar(100) null comment '로그인구분',
        private String kname;    //varchar(100) null comment '한글이름',
        private String ename;    //varchar(100) null comment '영문이름',
        private String cname;    //varchar(100) null comment '한문이름',
        private String pic;    //varchar(255) null comment '사진경로',
        private String sex;    //varchar(10)  null comment '성별',
        private String birthYear;    //varchar(4)   null comment '생년월일_년도'
        private String birthMonth;    //varchar(2)   null comment '생년월일_월',
        private String birthDay;    //varchar(2)   null comment '생년월일_일',
        private String address;    //varchar(255) null comment '주소',
        private String addressEtc;    //varchar(255) null comment '주소기타',
        private String country;    //varchar(255) null comment '출신지',
        private String countryEtc;    //varchar(255) null comment '출신지기타',
        private String hp;    //varchar(100) null comment '핸드폰',
        private String tel1;    //varchar(100) null comment '전화번호_자택'
        private String tel2;    //varchar(100) null comment '전화번호_직장'
        private String email;    //varchar(255) null comment '이메일',
        private String company;    //varchar(255) null comment '소속기관',
        private String spot;    //varchar(255) null comment '소속기관_직위'
        private String schoolE;    //varchar(255) null comment '학력_초등학교'
        private String schoolEYear;    //varchar(10)  null comment '초등학교_졸업년
        private String schoolM;    //varchar(255) null comment '학력_중학교',
        private String schoolMYear;    //varchar(10)  null comment '중학교_졸업년도
        private String schoolH;    //varchar(255) null comment '학력_고등학교'
        private String schoolHYear;    //varchar(10)  null comment '고등학교_졸업년
        private String schoolU;    //varchar(255) null comment '학력_대학교',
        private String schoolUYear;    //varchar(10)  null comment '대학교_졸업년도
        private String schoolG;    //varchar(255) null comment '학력_대학원이상
        private String schoolGYear;    //varchar(10)  null comment '대학원이상_졸업
        private String edu;    //varchar(255) null comment '최종학력',
        private String activity;    //varchar(255) null comment '활동분야',
        private String religion;    //varchar(255) null comment '종교',
        private String religionEtc;    //varchar(255) null comment '종교_기타',
        private String career;    //text         null comment '경력사항',
        private String psswd;    //varchar(255) null comment '비밀번호',
        private String wdate;    //datetime     null comment '등록일',
        private String udate;    //datetime     null comment '수정일',
        private String etc1;    //varchar(255) null comment '기타1',
        private String etc2;    //varchar(255) null comment '기타2',
        private String etc3;    //varchar(255) null comment '기타3',
        private String etc4;    //varchar(255) null comment '기타4',
        private String etc5;    //varchar(255) null comment '기타5',
        private String etc6;    //varchar(255) null comment '기타6',
        private String etc7;    //varchar(255) null comment '기타7',
        private String etc8;    //varchar(255) null comment '기타8',
        private String etc9;    //varchar(255) null comment '기타9',
        private String etc10;    //varchar(255) null comment '기타10'

}
