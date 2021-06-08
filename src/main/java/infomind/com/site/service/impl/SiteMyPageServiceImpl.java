package infomind.com.site.service.impl;

import egovframework.com.utl.fcc.service.EgovStringUtil;
import egovframework.com.utl.sim.service.EgovFileScrty;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import infomind.com.cms.uss.umt.vo.InfoEntrprsManageVO;
import infomind.com.cms.uss.umt.vo.InfoMberManageVO;
import infomind.com.cms.uss.umt.vo.InfoStplatVO;
import infomind.com.site.dao.SiteMyPageDAO;
import infomind.com.site.service.SiteMyPageService;
import infomind.com.site.vo.SiteJoinVO;
import infomind.com.site.vo.SiteUserVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("SiteMyPageService")
public class SiteMyPageServiceImpl extends EgovAbstractServiceImpl implements SiteMyPageService {


    @Resource(name="SiteMyPageDAO")
    private SiteMyPageDAO siteMyPageDAO;


    /** egovUsrCnfrmIdGnrService */
    @Resource(name="egovUsrCnfrmIdGnrService")
    private EgovIdGnrService idgenService;


    @Override
    public int checkUserId(String userId) {

        return siteMyPageDAO.checkUserId(userId);
    }

    @Override
    public String insertMber(SiteJoinVO mberManageVO) throws Exception {

        //고유아이디 셋팅
        String uniqId = idgenService.getNextStringId();
        mberManageVO.setUniqId(uniqId);
        String pass = EgovFileScrty.encryptPassword(mberManageVO.getPasswd(), EgovStringUtil.isNullToString(mberManageVO.getId()));//KISA 보안약점 조치 (2018-10-29, 윤창원)
        mberManageVO.setPasswd(pass);


        String result ="0";


        //롤에다가 저장 해보자...
        if("GNR".equals(mberManageVO.getType())){

            InfoMberManageVO mVo = new InfoMberManageVO();
            mVo =mberManageVO.getGnrUser();
            mVo.setModType("I");
            result = siteMyPageDAO.insertMber(mVo);

            //일반회원 히스토리 저장
            siteMyPageDAO.insertMberHis(mVo);

            //일반 권한 정보 저장 처리
            mberManageVO.setMberTyCode("USR01");
            mberManageVO.setAuthorCode("ROLE_USER");
             siteMyPageDAO.insertUserAUthorCode(mberManageVO);
         }else{

            InfoEntrprsManageVO eVo = new InfoEntrprsManageVO();

            eVo =mberManageVO.getEntUser();
            siteMyPageDAO.insertEntrprs(eVo);
            //기업 권한 정보 저장 처리
            mberManageVO.setMberTyCode("USR02");
            mberManageVO.setAuthorCode("ROLE_ENTRPRS");
            siteMyPageDAO.insertUserAUthorCode(mberManageVO);
        }


        return result;
    }

    @Override
    public void updateMber(SiteJoinVO mberManageVO) throws Exception{

        //롤에다가 저장 해보자...
        if("GNR".equals(mberManageVO.getType())){

            InfoMberManageVO mVo = new InfoMberManageVO();
            mVo = mberManageVO.getGnrUser();
            siteMyPageDAO.updateMber(mVo);


        }else{

            InfoEntrprsManageVO eVo = new InfoEntrprsManageVO();
            eVo =mberManageVO.getEntUser();
            siteMyPageDAO.updateEntMber(eVo);

        }
        //siteMyPageDAO.updateMber(mberManageVO);
    }

    @Override
    public InfoStplatVO selectStplat(String stplatId) {

        return siteMyPageDAO.selectStplat(stplatId);
    }

    @Override
    public void updatePassword(InfoMberManageVO passVO) {

    }

    @Override
    public InfoMberManageVO selectPassword(InfoMberManageVO mberManageVO) {
        return null;
    }

    @Override
    public SiteJoinVO selectMber(SiteJoinVO mberManageVO) {

        SiteJoinVO sVo= new SiteJoinVO();

        if("GNR".equals(mberManageVO.getType())){

            sVo =siteMyPageDAO.selectMber(mberManageVO);
        }else{
            sVo =siteMyPageDAO.selectEntMber(mberManageVO);
         }
        return sVo;

    }

    @Override
    public List<SiteUserVO> getSiteUserIdFind(SiteUserVO searchVO) throws Exception {
        return siteMyPageDAO.getSiteUserIdFind(searchVO);
    }

    @Override
    public List<SiteUserVO> getSiteUserPwFind(SiteUserVO searchVO) throws Exception {
        return siteMyPageDAO.getSiteUserPwFind(searchVO);
    }

    @Override
    public List<SiteUserVO> getSiteUserPwChangeFind(SiteUserVO searchVO) throws Exception {
        return siteMyPageDAO.getSiteUserPwChangeFind(searchVO);
    }

    @Override
    public void getUserPw(SiteUserVO passVO) {

        if("ENT".equals(passVO.getUserSe())){
            siteMyPageDAO.getUpdateUserPwEnt(passVO);
        }else if("USR".equals(passVO.getUserSe())){

            siteMyPageDAO.getUpdateUserPwUsr(passVO);
        }

    }

    @Override
    public int getDiCount(String di) {
        return siteMyPageDAO.getDiCount(di);
    }

    @Override
    public List<SiteUserVO> getSiteUserAllFind(SiteUserVO searchVO) throws Exception {
        return siteMyPageDAO.getSiteUserAllFind(searchVO);
    }
}
