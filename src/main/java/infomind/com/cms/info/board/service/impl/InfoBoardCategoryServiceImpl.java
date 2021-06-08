package infomind.com.cms.info.board.service.impl;

import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import infomind.com.cms.info.board.dao.InfoBoardCategoryDAO;
import infomind.com.cms.info.board.service.InfoBoardCategoryService;
import infomind.com.cms.info.board.vo.InfoBoardCategoryVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Service("InfoBoardCategoryService")
public class InfoBoardCategoryServiceImpl extends EgovAbstractServiceImpl implements InfoBoardCategoryService {

    @Resource(name = "InfoBoardCategoryIdGnrService")
    private EgovIdGnrService idgenService;

    @Resource(name="InfoBoardCategoryDAO")
    private InfoBoardCategoryDAO infoBoardCategoryDAO;

    @Override
    public List<?> selectInfoBoardCategoryList(InfoBoardCategoryVO vo) throws Exception {
        return infoBoardCategoryDAO.selectBoardCategoryList(vo);
    }

    @Override
    public Integer selectInfoBoardCategoryTotalCount(InfoBoardCategoryVO vo) throws Exception {
        return infoBoardCategoryDAO.selectBoardCategoryTotalCount(vo);
    }

    @Override
    public void insertBoardCategory(InfoBoardCategoryVO vo) throws Exception {
        infoBoardCategoryDAO.insertBoardCategory(vo);
    }

    @Override
    public InfoBoardCategoryVO selectBoardCategory(InfoBoardCategoryVO vo) throws Exception {
        return infoBoardCategoryDAO.selectBoardCategory(vo);
    }

    @Override
    public void updateList(List<InfoBoardCategoryVO> list) throws Exception {


        LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
        String  userId =(user == null || user.getUniqId() == null) ? "" : user.getUniqId();




        List<InfoBoardCategoryVO> createList = list.stream()
                .filter(v -> {return v.is__created__();})
                .collect(Collectors.toList());
        for(InfoBoardCategoryVO v: createList) {
            v.setBoardCategoryId(idgenService.getNextIntegerId());

            v.setRegId(userId);
            v.setModId(userId);
            infoBoardCategoryDAO.insertBoardCategory(v);
        }
        List<InfoBoardCategoryVO> modifyList = list.stream()
                .filter(v -> {
                    return v.is__modified__() && !v.is__created__();
                })
                .collect(Collectors.toList());
        for(InfoBoardCategoryVO v: modifyList) {
            v.setModId(userId);
            infoBoardCategoryDAO.updateBoardCategory(v);
        }
        // List<InfoBoardCategoryVO> deleteList = list.stream().filter(v -> {return v.is__deleted__();}).collect(Collectors.toList());
        // for(InfoBoardCategoryVO v: deleteList) {
        //     infoBoardCategoryDAO.updateBoardCategory(v);
        // }
    }

    @Override
    public List<?> selectBoardCategoryDepthList(InfoBoardCategoryVO vo) throws Exception {
        return infoBoardCategoryDAO.selectBoardCategoryDepthList(vo);
    }
}
