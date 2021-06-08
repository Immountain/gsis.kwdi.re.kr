package infomind.com.cms.info.popup.service;

import infomind.com.cms.info.popup.vo.InfoPopupManageVO;

import java.util.List;

public interface InfoPopupManageService {
    public InfoPopupManageVO selectPopupManage(InfoPopupManageVO vo) throws Exception;

    public Integer selectPopupManageTotalCount(InfoPopupManageVO vo) throws Exception;

    public List<?> selectPopupManageList(InfoPopupManageVO vo) throws Exception;

    public void insertPopupManage(InfoPopupManageVO vo) throws Exception;

    public void updatePopupManage(InfoPopupManageVO vo) throws Exception;

    List<InfoPopupManageVO> getPopList(InfoPopupManageVO vo) ;
}
