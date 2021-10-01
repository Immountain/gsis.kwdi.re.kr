package gsis.com.cms.mdis.vo;

import infomind.com.ext.vo.CmsSearchVO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class JewMdisVO extends CmsSearchVO {

        private String jewMdisSno;  //int          not null comment '메타데이터 자동증가',
        private String mdisNum;  //varchar(20)  null comment '자료번호',
        private String mdisType;  //varchar(20)  null comment '자료유형',
        private String mdisKorNm;  //varchar(200) null comment '자료명(국문)',
        private String mdisEnNm;  //varchar(200) null comment '자료명(영문)',
        private String dataOfForm;  //varchar(500) null comment '자료인용서식',
        private String projectKorNm;  //varchar(200) null comment '연구과제명(국문)',
        private String projectEnNm;  //varchar(200) null comment '연구과제명(영문)',
        private String pi;  //varchar(20)  null comment '연구책임자',
        private String coPi;  //varchar(200) null comment '공동연구자',
        private String organization;  //varchar(50)  null comment '연구수행기관',
        private String supportingOrganization;  //varchar(50)  null comment '연구비지원기관',
        private String copyrightHolder;  //varchar(50)  null comment '저작권자',
        private String investigatePurpose;  //varchar(500) null comment '조사목적',
        private String investigateContent;  //varchar(500) null comment '조사내용',
        private String keyword;  //varchar(200) null comment '키워드',
        private String collectionStrDay;  //varchar(8)   null comment '자료수집시작일',
        private String collectionEndDay;  //varchar(8)   null comment '자료수집종료일',
        private String investigateArea;  //varchar(50)  null comment '조사지역',
        private String analysisUnit;  //varchar(50)  null comment '분석단위',
        private String subject;  //varchar(200) null comment '조사대상',
        private String mdisTime;  //varchar(50)  null comment '시간적차원',
        private String researchOrganization;  //varchar(50)  null comment '새 조사수행기관',
        private String interviewSurvey;  //varchar(20)  null comment '조사방법_면대면',
        private String selfAdministeredSurvey;  //varchar(20)  null comment '조사방법_자기기입식',
        private String mailSurvey;  //varchar(20)  null comment '조사방법_우편조사',
        private String phoneSurvey;  //varchar(20)  null comment '조사방법_전화조사',
        private String onlineSurvey;  //varchar(20)  null comment '조사방법_온라인조사',
        private String etcSurvey;  //varchar(100) null comment '조사방법_기타',
        private String extraction;  //varchar(100) null comment '표본추출방법',
        private String caseNumber;  //varchar(100) null comment '사례수',
        private String weight;  //varchar(50)  null comment '가중치',
        private String dataForm;  //varchar(50)  null comment '자료형식',
        private String dataStructure;  //varchar(50)  null comment '자료구성',
        private String mdisLanguage;  //varchar(50)  null comment '사용언어',
        private String publicYn;  //varchar(2)   null comment '자료공개여부',
        private String publicReasons;  //varchar(200) null comment '자료공개_사유',
        private String remark;  //varchar(500) null comment '비고',
        private String etc;  //varchar(20)  null comment '기타파일',
        private String dataFile;  //varchar(20)  null comment '데이터파일',
        private String useYn;  //varchar(2)   null comment '사용여부',
        private String deleteYn;  //varchar(2)   null comment '삭제여부',
        private String regDt;  //datetime     null comment '등록일',
        private String regId;  //varchar(20)  null comment '등록자',
        private String modDt;  //datetime     null comment '수정자',
        private String modId;  //varchar(20)  null comment '수정일'


        private String fileSn="";

        private String orignlFileNm="";
        private int cnt=0;

        private String tempStrDay;
        private String tempEndDay;

        public String getTempStrDay(){

                this.tempStrDay = new java.text.SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date());

                return this.tempStrDay;
        }

        public String getTempEndDay(){

                this.tempEndDay = new java.text.SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date());

                return this.tempEndDay;
        }

}
