package infomind.com.ext.service;

import infomind.com.cms.info.lang.vo.InfoLangCodeVO;
import infomind.com.ext.vo.LangPackVO;
import java.util.List;

public interface LangPackService {

    /**
     * 총언어 리스트
    * @return
     * @throws Exception
     */
    List<LangPackVO> getSelectLangPackList() throws Exception;


    /**
     * 인포마인드 코드 언어팩
      * @param searchVO
     * @return
     * @throws Exception
     */
     List<InfoLangCodeVO> getInfoLangCode(InfoLangCodeVO searchVO)throws Exception;



    /**
     * 제주인 활동분야 코드
     * @param searchVO
     * @return
     * @throws Exception
     */
    List<InfoLangCodeVO> getInfoActLangCode(InfoLangCodeVO searchVO)throws Exception;




}
