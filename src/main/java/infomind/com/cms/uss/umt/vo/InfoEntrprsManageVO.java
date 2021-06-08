package infomind.com.cms.uss.umt.vo;

import com.google.gson.Gson;
import infomind.com.ext.vo.CmsSearchVO;
import lombok.Data;
import net.sf.json.JSONObject;


/**
 * 기업회원VO클래스로서 기업회원관리 비지니스로직 처리용 항목을 구성한다.
 * @author 공통서비스 개발팀 조재영
 * @since 2009.04.10
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.04.10  조재영          최초 생성
 *   2017.07.21  장동한 			로그인인증제한 작업
 *
 * </pre>
 */
@Data
public class InfoEntrprsManageVO extends CmsSearchVO {

	private static final long serialVersionUID = -6532736688851136256L;

	/** 이전비밀번호 - 비밀번호 변경시 사용*/
	private String oldPassword = "";

	/**
	 * 사용자고유아이디
	 */
	private String uniqId="";
	/**
	 * 사용자 유형
	 */
	private String userTy;
	/**
	 * 주소
	 */
	private String adres;
	/**
	 * 상세주소
	 */
	private String detailAdres;
	/**
	 * 신청자 주민등록번호
	 */
	private String applcntIhidnum;
	/**
	 * 신청자 명
	 */
	private String applcntNm;
	/**
	 * 사업자번호
	 */
	private String bizrno;
	/**
	 * 회사명
	 */
	private String cmpnyNm;
	/**
	 * 대표이사
	 */
	private String cxfc;
	/**
	 * 기업 회원 ID
	 */
	private String entrprsmberId;
	/**
	 * 기업 회원 비밀번호
	 */
	private String entrprsMberPassword;
	/**
	 * 기업 회원 비밀번호 정답
	 */
	private String entrprsMberPasswordCnsr;
	/**
	 * 기업 회원 비밀번호 힌트
	 */
	private String entrprsMberPasswordHint;
	/**
	 * 기업 회원 상태
	 */
	private String entrprsMberSttus;
	/**
	 * 기업구분코드
	 */
	private String entrprsSeCode;
	/**
	 * 팩스번호
	 */
	private String fxnum;
	/**
	 * 그룹 ID
	 */
	private String groupId;
	/**
	 * 업종코드
	 */
	private String indutyCode;
	/**
	 * 법인등록번호
	 */
	private String jurirno;
	/**
	 * 지역번호
	 */
	private String areaNo;
	/**
	 * 회사끝전화번호
	 */
	private String entrprsEndTelno;
	/**
	 * 회사중간전화번호
	 */
	private String entrprsMiddleTelno;
	/**
	 * 가입 일자
	 */
	private String sbscrbDe;
	/**
	 * 우편번호
	 */
	private String zip;
	/**
	 * 신청자 이메일주소
	 */
	private String applcntEmailAdres;
	/**
	 * 신청자 전화번
	 */
	private String mbtlnum;

	/** 기관번호	 */
	private String jtpOrganCd;

	/** 기관명	 */
	private String jtpOrganNm ="";

	/** 부서코드	 */
	private String jtpCloudId;

	/** 부서명	 */
	private String jtpCloudNm ="";

	/** 시작휴대폰번호 */
	private String startMoblno;

	/** 중간휴대폰번호 */
	private String middleMoblno;

	/** 끝휴대폰번호 */
	private String endMoblno;

	private String schJtpOrganCd;
	private String schJtpCloudId;

	private String lockAt;

	public String getLockAt() {return lockAt;}
	public void setLockAt(String lockAt) {this.lockAt = lockAt;}



	private String mberTyCode="";
	private String authorCode="";
	private String moblphonNo="";
	private String di="";

	private String zeusCertiYn;
	private String zeusCertiYnNm;
	private String zeusId;
	private String esntlId;

	public InfoEntrprsManageVO getEntUser(){


		InfoEntrprsManageVO  entUser = new InfoEntrprsManageVO();
		//유니크
		entUser.setUniqId(this.uniqId);
		//아이디
		entUser.setEntrprsmberId(this.entrprsmberId);
		//기업구분 C0000001:대기업 ,C0000002:중소기업 ,C0000003:다국적기업
		entUser.setEntrprsSeCode("C0000002");
		//사업자등록번 organ_mst [BUSI_REGIST_NO]
		entUser.setBizrno(this.bizrno);
		//범인등록번호
		entUser.setJurirno("");
		//회사     organ_mst [ORGAN_NM]
		entUser.setCmpnyNm(this.cmpnyNm);
		//대표이사   organ_mst [OWNER_NM]
		entUser.setCxfc(this.cxfc);
		//우편번호   organ_mst [ZIP_NO]
		entUser.setZip(this.zip);
		//주소      organ_mst [ADDRESS1]
		entUser.setAdres(this.adres);
		//상세주소   organ_mst [DETAIL_ADRES]
		entUser.setDetailAdres(this.detailAdres);
		//기업 지역번호
		entUser.setAreaNo(this.areaNo);
		//기업중간전화번호
		entUser.setEntrprsMiddleTelno(this.entrprsMiddleTelno);
		//기업 마지막 전화번호
		entUser.setEntrprsEndTelno(this.entrprsEndTelno);
		//패스번호
		entUser.setFxnum("");
		//업종코드    organ_mst [CARRIER_TOB]
		entUser.setIndutyCode(this.indutyCode);
		//신청인명
		entUser.setApplcntNm(this.applcntNm);
		//신청자 주민번호
		entUser.setApplcntIhidnum("");
		//기업회원상태 A:회원가입 신청상태 ,D:회원가입 삭제상태 ,P:회원가입승인상태
		entUser.setEntrprsMberSttus("A");

		//질문
		entUser.setEntrprsMberPasswordHint("P01");
		//답변
		entUser.setEntrprsMberPasswordCnsr("없음");
		//패스워드
		entUser.setEntrprsMberPassword(this.entrprsMberPassword);
		//그룹아이디   GROUP_00000000000000 0번째 그룹
		entUser.setGroupId("GROUP_00000000000000");
		//신청자이메일 주소
		entUser.setApplcntEmailAdres(this.applcntEmailAdres);
		//신청자 전화번호
		entUser.setMbtlnum(this.startMoblno+"-"+this.middleMoblno+"-"+this.endMoblno);

		entUser.setJtpOrganCd(this.jtpOrganCd);

		entUser.setDi(this.di);

		entUser.setZeusId(this.zeusId);
		entUser.setZeusCertiYn(this.zeusCertiYn);

		return entUser;
	}



	public void setMoblphonNo(String moblphonNo) {

		String[] phone = moblphonNo.split("-");
		for(int i=0; i<phone.length; i++){
			if(i==0){

				this.startMoblno =phone[i];

			}else if(i==1){
				this.middleMoblno =phone[i];

			}else if(i==2){

				this.endMoblno  =phone[i];
			}

		}



		this.moblphonNo = moblphonNo;
	}




	//해당값 json 으로
	public String getJsonString(){
		Gson gson = new Gson();
		JSONObject obj = JSONObject.fromObject(gson.toJson(this));
		return obj.toString();
	}

}
