package gsis.com.cms.datainfo.jgAtitle.service.impl;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import gsis.com.cms.datainfo.jgAtitle.dao.JgA03TitleDAO;
import gsis.com.cms.datainfo.jgAtitle.dao.JgA04TitleDAO;
import gsis.com.cms.datainfo.jgAtitle.service.JgA03TitleService;
import gsis.com.cms.datainfo.jgAtitle.service.JgA04TitleService;
import gsis.com.cms.datainfo.jgAtitle.vo.JewA03TiileDataVO;
import gsis.com.cms.datainfo.jgAtitle.vo.JewA04TiileDataVO;
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

@Service("JgA04TitleService")
public class JgA04TitleServiceImpl extends EgovAbstractServiceImpl implements JgA04TitleService {



    @Autowired
    private ResourceLoader resourceLoader;

    @Resource(name="JgA04TitleDAO")
    private JgA04TitleDAO jgA04TitleDAO;



    @Override
    public List<JewA04TiileDataVO> selectList(JewA04TiileDataVO vo) throws Exception {
        return jgA04TitleDAO.selectList(vo);
    }

    @Override
    public void insert(JewA04TiileDataVO vo) throws Exception {


        for(JewA04TiileDataVO tVo : vo.getListData()){
            jgA04TitleDAO.delete(tVo);
            jgA04TitleDAO.insert(tVo);
        }



    }

    @Override
    public void delete(JewA04TiileDataVO vo) throws Exception {


        jgA04TitleDAO.delete(vo);

    }

    @Override
    public void excelDownload(JewA04TiileDataVO searchVO, HttpServletResponse response) {

        try {


            InputStream templateStream = resourceLoader.getResource("classpath:/gsis/excel/jgAtitle/a04.xlsx").getInputStream();
            OutputStream targetStream = response.getOutputStream();

            Context context = new Context();
//            context.putVar("list", list);
//            context.putVar("equipMstList", equipMstList);



            SimpleDateFormat sdf = new SimpleDateFormat( "yyyyMMddHHmmss");
            String fileName = java.net.URLEncoder.encode("1-7(출생과 사망)_" +sdf.format(System.currentTimeMillis()), "UTF-8");
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
    public List<JewA04TiileDataVO> getSelectList(JewA04TiileDataVO vo) throws Exception {
        return jgA04TitleDAO.selectList(vo);
    }
}
