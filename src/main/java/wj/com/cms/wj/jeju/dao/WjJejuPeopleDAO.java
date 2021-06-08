package wj.com.cms.wj.jeju.dao;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import org.springframework.stereotype.Repository;
import wj.com.cms.wj.jeju.vo.WjJejuPeoplePicVO;
import wj.com.cms.wj.jeju.vo.WjJejuPeopleVO;

import java.util.List;


@Repository("WjJejuPeopleDAO")
public class WjJejuPeopleDAO extends EgovComAbstractDAO {

    public WjJejuPeopleVO selectJejuPeople(WjJejuPeopleVO vo)throws Exception{
        return selectOne("WjJejuPeopleDAO.selectJejuPeople", vo);
    }

    public Integer selectJejuPeopleTotalCount(WjJejuPeopleVO vo)throws Exception{
        return selectOne("WjJejuPeopleDAO.selectJejuPeopleTotalCount",vo);
    }

    public List<?> selectJejuPeopleList(WjJejuPeopleVO vo) throws Exception{
        return selectList("WjJejuPeopleDAO.selectJejuPeopleList",vo);
    }

    public void insertJejuPeople(WjJejuPeopleVO vo)throws Exception{
        insert("WjJejuPeopleDAO.insertJejuPeople", vo);
    }

    public void updateJejuPeople(WjJejuPeopleVO vo)throws Exception{
        insert("WjJejuPeopleDAO.updateJejuPeople",vo);
    }

    public void getDeleteWjJejuPeoplePic(WjJejuPeoplePicVO vo)throws Exception{
        delete("WjJejuPeopleDAO.getDeleteWjJejuPeoplePic",vo);
    }

    public void getInsertWjJejuPeoplePic(WjJejuPeoplePicVO vo)throws Exception{
        insert("WjJejuPeopleDAO.getInsertWjJejuPeoplePic",vo);
    }


    public WjJejuPeoplePicVO getSelectWjJejuPeoplePic(String userId)throws Exception{
        return selectOne("WjJejuPeopleDAO.getSelectWjJejuPeoplePic", userId);
    }


}
