package gsis.com.cms.datainfo.jgCtitle.service.impl;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import gsis.com.cms.datainfo.jgAtitle.dao.JgA04TitleDAO;
import gsis.com.cms.datainfo.jgAtitle.service.JgA04TitleService;
import gsis.com.cms.datainfo.jgAtitle.vo.JewA04TiileDataVO;
import gsis.com.cms.datainfo.jgCtitle.dao.JgC01TitleDAO;
import gsis.com.cms.datainfo.jgCtitle.service.JgC01TitleService;
import gsis.com.cms.datainfo.jgCtitle.vo.JewC01TiileDataVO;
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

@Service("JgC01TitleService")
public class JgC01TitleServiceImpl extends EgovAbstractServiceImpl implements JgC01TitleService {



    @Autowired
    private ResourceLoader resourceLoader;

    @Resource(name="JgC01TitleDAO")
    private JgC01TitleDAO jgC01TitleDAO;



    @Override
    public List<JewC01TiileDataVO> selectList(JewC01TiileDataVO vo) throws Exception {
        return jgC01TitleDAO.selectList(vo);
    }

    @Override
    public void insert(JewC01TiileDataVO vo) throws Exception {


        for(JewC01TiileDataVO tVo : vo.getListData()){
            jgC01TitleDAO.delete(tVo);
            jgC01TitleDAO.insert(tVo);
        }

    }

    @Override
    public void delete(JewC01TiileDataVO vo) throws Exception {


        jgC01TitleDAO.delete(vo);

    }

    @Override
    public void excelDownload(JewC01TiileDataVO searchVO, HttpServletResponse response) {

        try {


            InputStream templateStream = resourceLoader.getResource("classpath:/gsis/excel/jgCtitle/c01.xlsx").getInputStream();
            OutputStream targetStream = response.getOutputStream();

            Context context = new Context();
//            context.putVar("list", list);
//            context.putVar("equipMstList", equipMstList);



            SimpleDateFormat sdf = new SimpleDateFormat( "yyyyMMddHHmmss");
            String fileName = java.net.URLEncoder.encode("3-1(경제활동참가율)_" +sdf.format(System.currentTimeMillis()), "UTF-8");
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
    public List<JewC01TiileDataVO> getSelectList(JewC01TiileDataVO vo) throws Exception {
        return jgC01TitleDAO.selectList(vo);
    }
}
