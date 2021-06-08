package infomind.com.cms.info.page.service;

import infomind.com.cms.info.page.vo.InfoPageSatisfactionVO;

import java.util.List;

public interface InfoPageSatisfactionService {

     InfoPageSatisfactionVO selectPageSatisfaction(InfoPageSatisfactionVO vo) throws Exception;

     Integer selectPageSatisfactionTotalCount(InfoPageSatisfactionVO vo) throws Exception;

     List<?> selectPageSatisfactionList(InfoPageSatisfactionVO vo) throws Exception;

     void insertPageSatisfaction(InfoPageSatisfactionVO vo) throws Exception;
}

