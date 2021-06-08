package wj.com.cms.wj.apply.vo;
import infomind.com.ext.vo.CmsSearchVO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class WjApply2019VO extends CmsSearchVO {

    private String noSno;    //int          not null comment '자동증가',
    private String kname;    //varchar(100) null comment '한글명',
    private String ename;    //varchar(100) null comment '영문명',
    private String cname;    //varchar(100) null comment '한문',
    private String pic;    //varchar(255) null comment '사진',
    private String sex;    //varchar(10)  null comment '성별',
    private String birthYear;    //varchar(4)   null comment '생년월일_년도',
    private String birthMonth;    //varchar(2)   null comment '생년월일_월',
    private String birthDay;    //varchar(3)   null comment '생년월일_일',
    private String age;    //varchar(3)   null comment '연령대',
    private String nationality;    //varchar(50)  null comment '국적',
    private String korean;    //varchar(50)  null comment '한국어사용여부',
    private String language;    //varchar(50)  null comment '해해외거주기간',
    private String address;    //varchar(50)  null comment '출생지',
    private String addressEtc;    //varchar(50)  null comment '출생지_기타',
    private String country;    //varchar(255) null comment '거주지',
    private String countryEtc;    //varchar(255) null comment '거주지_기타',
    private String stayAbroad;    //varchar(255) null comment '해외거주기간',
    private String hp;    //varchar(100) null comment '휴대폰',
    private String tel1;    //varchar(100) null comment '자택전화번호',
    private String tel2;    //varchar(100) null comment '새 컬럼21',
    private String tel3;    //varchar(100) null comment '국내연락처',
    private String email;    //varchar(255) null comment '이메일',
    private String company;    //varchar(255) null comment '소속기관',
    private String spot;    //varchar(255) null comment '직위',
    private String arriveMonth;    //varchar(100) null comment '예상입도월',
    private String arriveDay;    //varchar(100) null comment '예상입도일',
    private String departureMonth;    //varchar(100) null comment '출도_월',
    private String departureDay;    //varchar(100) null comment '출도_일',
    private String hotelUse;    //varchar(50)  null comment '호텔 객실이용',
    private String guestsName;    //varchar(50)  null comment '동반 투숙객',
    private String day2Program;    //text         null comment '2일참가선택',
    private String day2ProgramDinner;    //text         null comment '화합의밤 참가여부'
    private String day3Program;    //text         null comment '3일차 참가선택',
    private String overseas;    //varchar(50)  null comment '재외도민증가입여부'
    private String overseasGroup;    //text         null comment '참여단체선택',
    private String career;    //text         null comment '특이섭취자',
    private String wdate;    //datetime     null comment '등록일',
    private String udate;    //datetime     null comment '수정일',
    private String etc1;    //varchar(255) null comment '기타1',
    private String agree;    //varchar(255) null comment '자기소개',
    private String etc3;    //varchar(255) null comment '기타2',
    private String etc4;    //varchar(255) null comment '기타3',
    private String etc5;    //varchar(255) null comment '기타4',
    private String etc6;    //varchar(255) null comment '기타5',
    private String etc7;    //varchar(255) null comment '기타6',
    private String etc8;    //varchar(255) null comment '기타7',
    private String etc9;    //varchar(255) null comment '기타8',
    private String etc10;    //varchar(255) null comment '기타9'

}
