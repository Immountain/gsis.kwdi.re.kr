package infomind.com.cms.info.hurt635.service;


import infomind.com.cms.info.hurt635.vo.InfoHurt635VO;
import infomind.com.cms.info.site.vo.InfoSiteVO;

import java.util.List;

public interface InfoHurt635Service {

    public InfoHurt635VO selectHurt635(InfoHurt635VO vo) throws Exception;

    public Integer selectHurt635TotalCount(InfoHurt635VO vo) throws Exception;

    public List<?> selectHurt635List(InfoHurt635VO vo) throws Exception;

    public void insertHurt635(InfoHurt635VO vo) throws Exception;

    public void updateHurt635(InfoHurt635VO vo) throws Exception;
}
