package gsis.com.cms.datainfo.jgFtitle.service.impl;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import gsis.com.cms.datainfo.jgFtitle.dao.JgF05TitleDAO;
import gsis.com.cms.datainfo.jgFtitle.service.JgF05TitleService;
import gsis.com.cms.datainfo.jgFtitle.vo.JewF05TiileDataVO;
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

@Service("JgF05TitleService")
public class JgF05TitleServiceImpl extends EgovAbstractServiceImpl implements JgF05TitleService {



    @Autowired
    private ResourceLoader resourceLoader;

    @Resource(name="JgF05TitleDAO")
    private JgF05TitleDAO jgF05TitleDAO;



    @Override
    public List<JewF05TiileDataVO> selectList(JewF05TiileDataVO vo) throws Exception {
        return jgF05TitleDAO.selectList(vo);
    }

    @Override
    public void insert(JewF05TiileDataVO vo) throws Exception {


        for(JewF05TiileDataVO tVo : vo.getListData()){
            jgF05TitleDAO.delete(tVo);
            jgF05TitleDAO.insert(tVo);
        }

    }

    @Override
    public void delete(JewF05TiileDataVO vo) throws Exception {


        jgF05TitleDAO.delete(vo);

    }

    @Override
    public void excelDownload(JewF05TiileDataVO searchVO, HttpServletResponse response) {

        try {


            InputStream templateStream = resourceLoader.getResource("classpath:/gsis/excel/jgFtitle/f05.xlsx").getInputStream();
            OutputStream targetStream = response.getOutputStream();

            Context context = new Context();
//            context.putVar("list", list);
//            context.putVar("equipMstList", equipMstList);



            SimpleDateFormat sdf = new SimpleDateFormat( "yyyyMMddHHmmss");
            String fileName = java.net.URLEncoder.encode("6-5(환경체감 만족도)_" +sdf.format(System.currentTimeMillis()), "UTF-8");
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
    public List<JewF05TiileDataVO> getSelectList(JewF05TiileDataVO vo) throws Exception {
        return jgF05TitleDAO.selectList(vo);
    }
}
