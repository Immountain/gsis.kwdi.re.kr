package gsis.com.cms.datainfo.jgBtitle.service.impl;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import gsis.com.cms.datainfo.jgBtitle.dao.JgB01TitleDAO;
import gsis.com.cms.datainfo.jgBtitle.dao.JgB06TitleDAO;
import gsis.com.cms.datainfo.jgBtitle.service.JgB01TitleService;
import gsis.com.cms.datainfo.jgBtitle.service.JgB06TitleService;
import gsis.com.cms.datainfo.jgBtitle.vo.JewB01TiileDataVO;
import gsis.com.cms.datainfo.jgBtitle.vo.JewB06TiileDataVO;
import org.jxls.common.Context;
import org.jxls.util.JxlsHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.List;

@Service("JgB06TitleService")
public class JgB06TitleServiceImpl extends EgovAbstractServiceImpl implements JgB06TitleService {



    @Autowired
    private ResourceLoader resourceLoader;

    @Resource(name="JgB06TitleDAO")
    private JgB06TitleDAO jgB06TitleDAO;



    @Override
    public List<JewB06TiileDataVO> selectList(JewB06TiileDataVO vo) throws Exception {
        return jgB06TitleDAO.selectList(vo);
    }

    @Override
    public void insert(JewB06TiileDataVO vo) throws Exception {





        for(JewB06TiileDataVO tVo : vo.getListData()){

            jgB06TitleDAO.delete(tVo);
            jgB06TitleDAO.insert(tVo);

        }



    }

    @Override
    public void delete(JewB06TiileDataVO vo) throws Exception {


        jgB06TitleDAO.delete(vo);

    }

    @Override
    public void excelDownload(JewB06TiileDataVO searchVO, HttpServletResponse response) {

        try {


            InputStream templateStream = resourceLoader.getResource("classpath:/gsis/excel/jgBtitle/b06.xlsx").getInputStream();
            OutputStream targetStream = response.getOutputStream();

            Context context = new Context();
//            context.putVar("list", list);
//            context.putVar("equipMstList", equipMstList);



            SimpleDateFormat sdf = new SimpleDateFormat( "yyyyMMddHHmmss");
            String fileName = java.net.URLEncoder.encode("2-10(초‧중‧고등학교의 직위별 여성교원 비율)_" +sdf.format(System.currentTimeMillis()), "UTF-8");
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            response.setHeader("Content-Disposition", "attachment;filename=" +fileName +".xlsx");
            JxlsHelper.getInstance().processTemplate(templateStream, targetStream, context);
        }catch (IOException io) {
            io.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<JewB06TiileDataVO> getSelectList(JewB06TiileDataVO vo) throws Exception {
        return jgB06TitleDAO.selectList(vo);
    }
}
