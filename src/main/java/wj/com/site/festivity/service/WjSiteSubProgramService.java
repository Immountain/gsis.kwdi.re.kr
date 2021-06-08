package wj.com.site.festivity.service;

import wj.com.site.festivity.vo.WjSiteSubProgramVO;

import java.util.List;

public interface WjSiteSubProgramService {

    /**
     * 부대 프로그램 정보
     * @param vo
     * @return
     * @throws Exception
     */
    WjSiteSubProgramVO getSelectSiteWjSubProgramInfo(WjSiteSubProgramVO vo)throws Exception;


    /**
     * 부대프로그램 리스트
     * @param vo
     * @return
     * @throws Exception
     */
    List<WjSiteSubProgramVO> getSelectSiteWjSubProgramList(WjSiteSubProgramVO vo)throws Exception;
}
