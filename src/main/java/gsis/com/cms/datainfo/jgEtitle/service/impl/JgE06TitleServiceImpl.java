package gsis.com.cms.datainfo.jgEtitle.service.impl;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import gsis.com.cms.datainfo.jgEtitle.dao.JgE06TitleDAO;
import gsis.com.cms.datainfo.jgEtitle.service.JgE06TitleService;
import gsis.com.cms.datainfo.jgEtitle.vo.JewE06TiileDataVO;
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

@Service("JgE06TitleService")
public class JgE06TitleServiceImpl extends EgovAbstractServiceImpl implements JgE06TitleService {



    @Autowired
    private ResourceLoader resourceLoader;

    @Resource(name="JgE06TitleDAO")
    private JgE06TitleDAO jgE06TitleDAO;



    @Override
    public List<JewE06TiileDataVO> selectList(JewE06TiileDataVO vo) throws Exception {
        return jgE06TitleDAO.selectList(vo);
    }

    @Override
    public void insert(JewE06TiileDataVO vo) throws Exception {


        for(JewE06TiileDataVO tVo : vo.getListData()){
            jgE06TitleDAO.delete(tVo);
            jgE06TitleDAO.insert(tVo);
        }

    }

    @Override
    public void delete(JewE06TiileDataVO vo) throws Exception {


        jgE06TitleDAO.delete(vo);

    }

    @Override
    public void excelDownload(JewE06TiileDataVO searchVO, HttpServletResponse response) {

        try {


            InputStream templateStream = resourceLoader.getResource("classpath:/gsis/excel/jgEtitle/e06.xlsx").getInputStream();
            OutputStream targetStream = response.getOutputStream();

            Context context = new Context();
//            context.putVar("list", list);
//            context.putVar("equipMstList", equipMstList);



            SimpleDateFormat sdf = new SimpleDateFormat( "yyyyMMddHHmmss");
            String fileName = java.net.URLEncoder.encode("5-5(5급 이상 관리직 여성 공무원 현황)_" +sdf.format(System.currentTimeMillis()), "UTF-8");
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
    public List<JewE06TiileDataVO> getSelectList(JewE06TiileDataVO vo) throws Exception {
        return jgE06TitleDAO.selectList(vo);
    }
}
