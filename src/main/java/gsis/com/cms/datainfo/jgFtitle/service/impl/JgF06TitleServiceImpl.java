package gsis.com.cms.datainfo.jgFtitle.service.impl;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import gsis.com.cms.datainfo.jgFtitle.dao.JgF06TitleDAO;
import gsis.com.cms.datainfo.jgFtitle.service.JgF06TitleService;
import gsis.com.cms.datainfo.jgFtitle.vo.JewF06TiileDataVO;
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

@Service("JgF06TitleService")
public class JgF06TitleServiceImpl extends EgovAbstractServiceImpl implements JgF06TitleService {



    @Autowired
    private ResourceLoader resourceLoader;

    @Resource(name="JgF06TitleDAO")
    private JgF06TitleDAO jgF06TitleDAO;



    @Override
    public List<JewF06TiileDataVO> selectList(JewF06TiileDataVO vo) throws Exception {
        return jgF06TitleDAO.selectList(vo);
    }

    @Override
    public void insert(JewF06TiileDataVO vo) throws Exception {


        for(JewF06TiileDataVO tVo : vo.getListData()){
            jgF06TitleDAO.delete(tVo);
            jgF06TitleDAO.insert(tVo);
        }

    }

    @Override
    public void delete(JewF06TiileDataVO vo) throws Exception {


        jgF06TitleDAO.delete(vo);

    }

    @Override
    public void excelDownload(JewF06TiileDataVO searchVO, HttpServletResponse response) {

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
    public List<JewF06TiileDataVO> getSelectList(JewF06TiileDataVO vo) throws Exception {
        return jgF06TitleDAO.selectList(vo);
    }
}
