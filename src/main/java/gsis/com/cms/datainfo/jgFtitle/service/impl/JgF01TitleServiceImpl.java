package gsis.com.cms.datainfo.jgFtitle.service.impl;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import gsis.com.cms.datainfo.jgFtitle.dao.JgF01TitleDAO;
import gsis.com.cms.datainfo.jgFtitle.service.JgF01TitleService;
import gsis.com.cms.datainfo.jgFtitle.vo.JewF01TiileDataVO;
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

@Service("JgF01TitleService")
public class JgF01TitleServiceImpl extends EgovAbstractServiceImpl implements JgF01TitleService {



    @Autowired
    private ResourceLoader resourceLoader;

    @Resource(name="JgF01TitleDAO")
    private JgF01TitleDAO jgF01TitleDAO;



    @Override
    public List<JewF01TiileDataVO> selectList(JewF01TiileDataVO vo) throws Exception {
        return jgF01TitleDAO.selectList(vo);
    }

    @Override
    public void insert(JewF01TiileDataVO vo) throws Exception {


        for(JewF01TiileDataVO tVo : vo.getListData()){
            jgF01TitleDAO.delete(tVo);
            jgF01TitleDAO.insert(tVo);
        }

    }

    @Override
    public void delete(JewF01TiileDataVO vo) throws Exception {


        jgF01TitleDAO.delete(vo);

    }

    @Override
    public void excelDownload(JewF01TiileDataVO searchVO, HttpServletResponse response) {

        try {


            InputStream templateStream = resourceLoader.getResource("classpath:/gsis/excel/jgFtitle/f01.xlsx").getInputStream();
            OutputStream targetStream = response.getOutputStream();

            Context context = new Context();
//            context.putVar("list", list);
//            context.putVar("equipMstList", equipMstList);



            SimpleDateFormat sdf = new SimpleDateFormat( "yyyyMMddHHmmss");
            String fileName = java.net.URLEncoder.encode("6-1(범죄위험에 대한 안전인식)_" +sdf.format(System.currentTimeMillis()), "UTF-8");
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
    public List<JewF01TiileDataVO> getSelectList(JewF01TiileDataVO vo) throws Exception {
        return jgF01TitleDAO.selectList(vo);
    }
}
