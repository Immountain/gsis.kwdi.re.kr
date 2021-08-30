package gsis.com.cms.datainfo.jgFtitle.service.impl;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import gsis.com.cms.datainfo.jgFtitle.dao.JgF01TitleDAO;
import gsis.com.cms.datainfo.jgFtitle.dao.JgF02TitleDAO;
import gsis.com.cms.datainfo.jgFtitle.service.JgF01TitleService;
import gsis.com.cms.datainfo.jgFtitle.service.JgF02TitleService;
import gsis.com.cms.datainfo.jgFtitle.vo.JewF01TiileDataVO;
import gsis.com.cms.datainfo.jgFtitle.vo.JewF02TiileDataVO;
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

@Service("JgF02TitleService")
public class JgF02TitleServiceImpl extends EgovAbstractServiceImpl implements JgF02TitleService {



    @Autowired
    private ResourceLoader resourceLoader;

    @Resource(name="JgF02TitleDAO")
    private JgF02TitleDAO jgF02TitleDAO;



    @Override
    public List<JewF02TiileDataVO> selectList(JewF02TiileDataVO vo) throws Exception {
        return jgF02TitleDAO.selectList(vo);
    }

    @Override
    public List<JewF02TiileDataVO> selectList2(JewF02TiileDataVO vo) throws Exception {
        return jgF02TitleDAO.selectList2(vo);
    }

    @Override
    public void insert(JewF02TiileDataVO vo) throws Exception {


        for(JewF02TiileDataVO tVo : vo.getListData()){
            jgF02TitleDAO.delete(tVo);
            jgF02TitleDAO.insert(tVo);
        }

    }

    @Override
    public void delete(JewF02TiileDataVO vo) throws Exception {


        jgF02TitleDAO.delete(vo);

    }

    @Override
    public void excelDownload(JewF02TiileDataVO searchVO, HttpServletResponse response) {

        try {


            InputStream templateStream = resourceLoader.getResource("classpath:/gsis/excel/jgFtitle/f02.xlsx").getInputStream();
            OutputStream targetStream = response.getOutputStream();

            Context context = new Context();
//            context.putVar("list", list);
//            context.putVar("equipMstList", equipMstList);



            SimpleDateFormat sdf = new SimpleDateFormat( "yyyyMMddHHmmss");
            String fileName = java.net.URLEncoder.encode("6-2(야간보행에 대한 두려움)_" +sdf.format(System.currentTimeMillis()), "UTF-8");
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
    public List<JewF02TiileDataVO> getSelectList(JewF02TiileDataVO vo) throws Exception {
        return jgF02TitleDAO.selectList(vo);
    }

    @Override
    public List<JewF02TiileDataVO> getSelectList2(JewF02TiileDataVO vo) throws Exception {
        return jgF02TitleDAO.selectList2(vo);
    }
}
