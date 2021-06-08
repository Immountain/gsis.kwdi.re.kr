package infomind.com.cms.info.popup.service;


import infomind.com.cms.info.popup.vo.InfoPopupGroupVO;

import java.util.List;

public interface InfoPopupGroupService {

    public InfoPopupGroupVO selectPopupGroup(InfoPopupGroupVO vo) throws Exception;

    public Integer selectPopupGroupTotalCount(InfoPopupGroupVO vo) throws Exception;

    public List<?> selectPopupGroupList(InfoPopupGroupVO vo) throws Exception;

    public void insertPopupGroup(InfoPopupGroupVO vo) throws Exception;

    public void updatePopupGroup(InfoPopupGroupVO vo) throws Exception;

}
