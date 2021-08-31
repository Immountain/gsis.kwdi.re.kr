package gsis.com.cms.datainfo.jgAtitle.service.impl;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import gsis.com.cms.datainfo.jgAtitle.dao.JgA06TitleDAO;
import gsis.com.cms.datainfo.jgAtitle.service.JgA06TitleService;
import gsis.com.cms.datainfo.jgAtitle.vo.JewA06TiileDataVO;
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

@Service("JgA06TitleService")
public class JgA06TitleServiceImpl extends EgovAbstractServiceImpl implements JgA06TitleService {



    @Autowired
    private ResourceLoader resourceLoader;

    @Resource(name="JgA06TitleDAO")
    private JgA06TitleDAO jgA06TitleDAO;



    @Override
    public List<JewA06TiileDataVO> selectList(JewA06TiileDataVO vo) throws Exception {
        return jgA06TitleDAO.selectList(vo);
    }

    @Override
    public void insert(JewA06TiileDataVO vo) throws Exception {


        for(JewA06TiileDataVO tVo : vo.getListData()){
            jgA06TitleDAO.delete(tVo);
            jgA06TitleDAO.insert(tVo);
        }



    }

    @Override
    public void delete(JewA06TiileDataVO vo) throws Exception {


        jgA06TitleDAO.delete(vo);

    }

    @Override
    public void excelDownload(JewA06TiileDataVO searchVO, HttpServletResponse response) {

        try {


            InputStream templateStream = resourceLoader.getResource("classpath:/gsis/excel/jgAtitle/a06.xlsx").getInputStream();
            OutputStream targetStream = response.getOutputStream();

            Context context = new Context();
//            context.putVar("list", list);
//            context.putVar("equipMstList", equipMstList);



            SimpleDateFormat sdf = new SimpleDateFormat( "yyyyMMddHHmmss");
            String fileName = java.net.URLEncoder.encode("1-13(1인 가구의 성별 구성)_" +sdf.format(System.currentTimeMillis()), "UTF-8");
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
    public List<JewA06TiileDataVO> getSelectList(JewA06TiileDataVO vo) throws Exception {
        return jgA06TitleDAO.selectList(vo);
    }
}
