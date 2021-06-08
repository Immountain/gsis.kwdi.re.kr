package wj.com.site.festivity.service;

import wj.com.site.festivity.vo.WjSiteProgramInfoVO;
import wj.com.site.festivity.vo.WjSiteProgramSubInfoVO;

import java.util.List;

public interface WjSiteProgramService {


    /**
     * 프로그램 정
     * @param vo
     * @return
     * @throws Exception
     */
    WjSiteProgramInfoVO getSelectSiteWJProgramInfo(WjSiteProgramInfoVO vo)throws Exception;


    /**
     * 프로그램 리스트
     * @param vo
     * @return
     * @throws Exception
     */
    List<WjSiteProgramInfoVO> getSelectSiteWJProgramList(WjSiteProgramInfoVO vo)throws Exception;

    /**
     * 프로그램 서브 내용 (포럼)
     * @param vo
     * @return
     * @throws Exception
     */
    List<WjSiteProgramSubInfoVO> getSelectSiteWJProgramSubList(WjSiteProgramInfoVO vo)throws Exception;
}
