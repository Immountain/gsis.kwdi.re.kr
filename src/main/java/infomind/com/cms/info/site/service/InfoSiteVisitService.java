package infomind.com.cms.info.site.service;
import infomind.com.cms.info.site.vo.InfoSiteVisitVO;
import java.util.List;
public interface InfoSiteVisitService {
    InfoSiteVisitVO selectSiteVisit(InfoSiteVisitVO vo) throws Exception;

    Integer selectSiteVisitTotalCount(InfoSiteVisitVO vo) throws Exception;

    List<?> selectSiteVisitList(InfoSiteVisitVO vo) throws Exception;

    void insertSiteVisit(InfoSiteVisitVO vo) throws Exception;
}
