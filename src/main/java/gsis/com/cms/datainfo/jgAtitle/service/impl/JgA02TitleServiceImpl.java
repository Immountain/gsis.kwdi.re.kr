package gsis.com.cms.datainfo.jgAtitle.service.impl;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import gsis.com.cms.datainfo.jgAtitle.dao.JgA02TitleDAO;
import gsis.com.cms.datainfo.jgAtitle.dao.JgA03TitleDAO;
import gsis.com.cms.datainfo.jgAtitle.service.JgA02TitleService;
import gsis.com.cms.datainfo.jgAtitle.service.JgA03TitleService;
import gsis.com.cms.datainfo.jgAtitle.vo.JewA02TiileDataVO;
import gsis.com.cms.datainfo.jgAtitle.vo.JewA03TiileDataVO;
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

@Service("JgA02TitleService")
public class JgA02TitleServiceImpl extends EgovAbstractServiceImpl implements JgA02TitleService {



    @Autowired
    private ResourceLoader resourceLoader;

    @Resource(name="JgA02TitleDAO")
    private JgA02TitleDAO jgA02TitleDAO;



    @Override
    public List<JewA02TiileDataVO> selectList(JewA02TiileDataVO vo) throws Exception {
        return jgA02TitleDAO.selectList(vo);
    }

    @Override
    public void insert(JewA02TiileDataVO vo) throws Exception {





        for(JewA02TiileDataVO tVo : vo.getListData()){

            jgA02TitleDAO.delete(tVo);
            jgA02TitleDAO.insert(tVo);

        }



    }

    @Override
    public void delete(JewA02TiileDataVO vo) throws Exception {


        jgA02TitleDAO.delete(vo);

    }

    @Override
    public void excelDownload(JewA02TiileDataVO searchVO, HttpServletResponse response) {

        try {


            InputStream templateStream = resourceLoader.getResource("classpath:/gsis/excel/jgAtitle/a02.xlsx").getInputStream();
            OutputStream targetStream = response.getOutputStream();

            Context context = new Context();
//            context.putVar("list", list);
//            context.putVar("equipMstList", equipMstList);



            SimpleDateFormat sdf = new SimpleDateFormat( "yyyyMMddHHmmss");
            String fileName = java.net.URLEncoder.encode("1-4(농가 및 어가 인구)_" +sdf.format(System.currentTimeMillis()), "UTF-8");
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
    public List<JewA02TiileDataVO> getSelectList(JewA02TiileDataVO vo) throws Exception {
        return jgA02TitleDAO.selectList(vo);
    }
}
