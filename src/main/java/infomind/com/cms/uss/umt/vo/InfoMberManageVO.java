package infomind.com.cms.uss.umt.vo;

import infomind.com.ext.vo.CmsSearchVO;
import lombok.Data;


/**
 * 일반회원VO클래스로서 일반회원관리 비지니스로직 처리용 항목을 구성한다.
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
public class InfoMberManageVO extends CmsSearchVO {

	private static final long serialVersionUID = -4255594107023139972L;

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
	 * 끝전화번호
	 */
	private String endTelno;
	/**
	 * 팩스번호
	 */
	private String mberFxnum;
	/**
	 * 그룹 ID
	 */
	private String groupId;
	/**
	 * 주민등록번호
	 */
	private String ihidnum;
	/**
	 * 성별코드
	 */
	private String sexdstnCode;
	/**
	 * 회원 ID
	 */
	private String mberId;
	/**
	 * 회원명
	 */
	private String mberNm;
	/**
	 * 회원상태
	 */
	private String mberSttus;
	/**
	 * 지역번호
	 */
	private String areaNo;
	/**
	 * 중간전화번호
	 */
	private String middleTelno;
	/**
	 * 핸드폰번호
	 */
	private String moblphonNo;

	/** 시작휴대폰번호 */
	private String startMoblno;

	/** 중간휴대폰번호 */
	private String middleMoblno;

	/** 끝휴대폰번호 */
	private String endMoblno;
	/**
	 * 비밀번호
	 */
	private String password;
	/**
	 * 비밀번호 정답
	 */
	private String passwordCnsr;
	/**
	 * 비밀번호 힌트
	 */
	private String passwordHint;
	/**
	 * 가입 일자
	 */
	private String sbscrbDe;
	/**
	 * 우편번호
	 */
	private String zip;
	/**
	 * 이메일주소
	 */
	private String mberEmailAdres;


	/**
	 * 기관번호
	 */
	private String jtpOrganCd ="";
	private String jtpOrganNm ="";

	private String mberTyCode="";
	private String authorCode="";
	private String di="";

	private String zeusCertiYn;
	private String zeusCertiYnNm;
	private String zeusId;

	private String modType="I";
	private String esntlId="";
	private String mbtlnum="";


	public InfoMberManageVO gnrUserBuilder(){

		InfoMberManageVO  gnrUser = new InfoMberManageVO();

		gnrUser.setUniqId(this.uniqId);
		gnrUser.setMberId(this.mberId);
		gnrUser.setMberNm(this.mberNm);
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
		gnrUser.setAreaNo(this.areaNo);
		gnrUser.setMiddleTelno(this.middleTelno);
		gnrUser.setEndTelno(this.endTelno);
		//핸드폰번호
		gnrUser.setMoblphonNo(this.startMoblno+"-"+this.middleMoblno+"-"+this.endMoblno);
		//그룹
		gnrUser.setGroupId("GROUP_00000000000000");
		//패스워드
		gnrUser.setMberFxnum("사용안함");
		//이메일주음소
		gnrUser.setMberEmailAdres(this.mberEmailAdres);

		//기관번호
		gnrUser.setJtpOrganCd(this.jtpOrganCd);

		gnrUser.setDi(this.di);
		gnrUser.setZeusCertiYn(this.getZeusCertiYn());
		gnrUser.setZeusId(this.getZeusId());
		return gnrUser;
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
}