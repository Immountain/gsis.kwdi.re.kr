package gsis.com.cms.datainfo.jgEtitle.service.impl;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import gsis.com.cms.datainfo.jgEtitle.dao.JgE01TitleDAO;
import gsis.com.cms.datainfo.jgEtitle.service.JgE01TitleService;
import gsis.com.cms.datainfo.jgEtitle.vo.JewE01TiileDataVO;
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

@Service("JgE01TitleService")
public class JgE01TitleServiceImpl extends EgovAbstractServiceImpl implements JgE01TitleService {



    @Autowired
    private ResourceLoader resourceLoader;

    @Resource(name="JgE01TitleDAO")
    private JgE01TitleDAO jgE01TitleDAO;



    @Override
    public List<JewE01TiileDataVO> selectList(JewE01TiileDataVO vo) throws Exception {
        return jgE01TitleDAO.selectList(vo);
    }

    @Override
    public void insert(JewE01TiileDataVO vo) throws Exception {


        for(JewE01TiileDataVO tVo : vo.getListData()){
            jgE01TitleDAO.delete(tVo);
            jgE01TitleDAO.insert(tVo);
        }

    }

    @Override
    public void delete(JewE01TiileDataVO vo) throws Exception {


        jgE01TitleDAO.delete(vo);

    }

    @Override
    public void excelDownload(JewE01TiileDataVO searchVO, HttpServletResponse response) {

        try {


            InputStream templateStream = resourceLoader.getResource("classpath:/gsis/excel/jgEtitle/e01.xlsx").getInputStream();
            OutputStream targetStream = response.getOutputStream();

            Context context = new Context();
//            context.putVar("list", list);
//            context.putVar("equipMstList", equipMstList);



            SimpleDateFormat sdf = new SimpleDateFormat( "yyyyMMddHHmmss");
            String fileName = java.net.URLEncoder.encode("5-1(투표율)_" +sdf.format(System.currentTimeMillis()), "UTF-8");
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
    public List<JewE01TiileDataVO> getSelectList(JewE01TiileDataVO vo) throws Exception {
        return jgE01TitleDAO.selectList(vo);
    }
}
