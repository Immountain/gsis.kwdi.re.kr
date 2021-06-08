package wj.com.site.jejupeople.service.impl;

import egovframework.com.utl.fcc.service.EgovStringUtil;
import egovframework.com.utl.sim.service.EgovFileScrty;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import infomind.com.cms.uss.umt.vo.InfoMberManageVO;
import infomind.com.site.dao.SiteMyPageDAO;
import infomind.com.site.vo.SiteJoinVO;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;
import wj.com.cms.wj.act.dao.WjActInfoDAO;
import wj.com.cms.wj.act.vo.WjActInfoVO;
import wj.com.cms.wj.jeju.dao.WjJejuPeopleDAO;
import wj.com.cms.wj.jeju.vo.WjJejuPeoplePicVO;
import wj.com.cms.wj.jeju.vo.WjJejuPeopleVO;
import wj.com.site.jejupeople.dao.WjSiteJejuPeopleDAO;
import wj.com.site.jejupeople.service.WjSiteJejuPeopleService;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.sql.SQLException;

@Service("WjSiteJejuPeopleService")
public class WjSiteJejuPeopleServiceImpl extends EgovAbstractServiceImpl implements WjSiteJejuPeopleService {


    @Resource(name = "WjSiteJejuPeopleDAO")
    private WjSiteJejuPeopleDAO wjSiteJejuPeopleDAO;


    @Resource(name = "WjJejuPeopleDAO")
    private WjJejuPeopleDAO wjJejuPeopleDAO;


    /**
     * egovUsrCnfrmIdGnrService
     */
    @Resource(name = "egovUsrCnfrmIdGnrService")
    private EgovIdGnrService idgenService;


    @Resource(name = "SiteMyPageDAO")
    private SiteMyPageDAO siteMyPageDAO;

    @Resource(name = "WjActInfoDAO")
    private WjActInfoDAO wjActInfoDAO;

    @Transactional
    @Override
    public String saveMber(WjJejuPeopleVO mberManageVO, MultipartFile file) throws Exception {

        String result = "";

        if(this.selectJejuPeople(mberManageVO.getUserId()) == null) {

            //고유아이디 셋팅
            String uniqId = idgenService.getNextStringId();
            mberManageVO.setUniqId(uniqId);
            String pass = EgovFileScrty.encryptPassword(mberManageVO.getPassword(), EgovStringUtil.isNullToString(mberManageVO.getUserId()));//KISA 보안약점 조치 (2018-10-29, 윤창원)
            mberManageVO.setPassword(pass);

            InfoMberManageVO mVo = new InfoMberManageVO();
            mVo = mberManageVO.getGnrUser();
            mVo.setModType("I");
            siteMyPageDAO.insertMber(mVo);

            //일반회원 히스토리 저장
            siteMyPageDAO.insertMberHis(mVo);

            //일반 권한 정보 저장 처리
            SiteJoinVO siteJoinVO = new SiteJoinVO();
            siteJoinVO.setUniqId(uniqId);
            siteJoinVO.setMberTyCode("USR01");
            siteJoinVO.setAuthorCode("ROLE_USER");
            siteMyPageDAO.insertUserAUthorCode(siteJoinVO);

            //제주인 등록
            wjJejuPeopleDAO.insertJejuPeople(mberManageVO);
            result = "insert";
        }else {
            wjJejuPeopleDAO.updateJejuPeople(mberManageVO);
            result = "update";
        }


        wjActInfoDAO.deleteUserId(mberManageVO.getUserId());
        if(mberManageVO.getWjActInfoList() != null) {
            for (WjActInfoVO v : mberManageVO.getWjActInfoList()) {
                v.setUserId(mberManageVO.getUserId());
                if (StringUtils.isEmpty(v.getActCode())) continue;
                wjActInfoDAO.insertActInfo(v);
            }
        }

        if(!ObjectUtils.isEmpty(file)) {
            WjJejuPeoplePicVO peoplePicVO = WjJejuPeoplePicVO.builder().userId(mberManageVO.getUserId()).build();

            wjJejuPeopleDAO.getDeleteWjJejuPeoplePic(peoplePicVO);
            peoplePicVO.setPic(convertFileToByte(file));

            wjJejuPeopleDAO.getInsertWjJejuPeoplePic(peoplePicVO);
        }


        return result;
    }

    @Override
    public WjJejuPeopleVO selectJejuPeople(String id) throws Exception {
        WjJejuPeopleVO peopleVO = new WjJejuPeopleVO();
        peopleVO.setUserId(id);
        return wjJejuPeopleDAO.selectJejuPeople(peopleVO);
    }

    @Override
    public WjJejuPeoplePicVO getSelectWjJejuPeoplePic(String userId) throws Exception {
        return wjJejuPeopleDAO.getSelectWjJejuPeoplePic(userId);
    }


    private byte[] convertFileToByte(MultipartFile mfile) throws IOException, SQLException {
        File file = new File(mfile.getOriginalFilename());
        mfile.transferTo(file);

        final FileChannel channel = new FileInputStream(file).getChannel();
        MappedByteBuffer buffer = channel.map(FileChannel.MapMode.READ_ONLY, 0, channel.size());

        byte[] bytes = new byte[buffer.remaining()];
        buffer.get(bytes, 0, bytes.length);

        // when finished
        channel.close();

        return bytes;
    }
}
