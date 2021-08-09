package gsis.com.cms.datainfo.jgDtitle.service.impl;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import gsis.com.cms.datainfo.jgDtitle.dao.JgD01TitleDAO;
import gsis.com.cms.datainfo.jgDtitle.dao.JgD03TitleDAO;
import gsis.com.cms.datainfo.jgDtitle.service.JgD01TitleService;
import gsis.com.cms.datainfo.jgDtitle.service.JgD03TitleService;
import gsis.com.cms.datainfo.jgDtitle.vo.JewD01TiileDataVO;
import gsis.com.cms.datainfo.jgDtitle.vo.JewD03TiileDataVO;
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

@Service("JgD03TitleService")
public class JgD03TitleServiceImpl extends EgovAbstractServiceImpl implements JgD03TitleService {



    @Autowired
    private ResourceLoader resourceLoader;

    @Resource(name="JgD03TitleDAO")
    private JgD03TitleDAO jgD03TitleDAO;



    @Override
    public List<JewD03TiileDataVO> selectList(JewD03TiileDataVO vo) throws Exception {
        return jgD03TitleDAO.selectList(vo);
    }

    @Override
    public void insert(JewD03TiileDataVO vo) throws Exception {


        for(JewD03TiileDataVO tVo : vo.getListData()){
            jgD03TitleDAO.delete(tVo);
            jgD03TitleDAO.insert(tVo);
        }

    }

    @Override
    public void delete(JewD03TiileDataVO vo) throws Exception {


        jgD03TitleDAO.delete(vo);

    }

    @Override
    public void excelDownload(JewD03TiileDataVO searchVO, HttpServletResponse response) {

        try {


            InputStream templateStream = resourceLoader.getResource("classpath:/gsis/excel/jgDtitle/d03.xlsx").getInputStream();
            OutputStream targetStream = response.getOutputStream();

            Context context = new Context();
//            context.putVar("list", list);
//            context.putVar("equipMstList", equipMstList);



            SimpleDateFormat sdf = new SimpleDateFormat( "yyyyMMddHHmmss");
            String fileName = java.net.URLEncoder.encode("4-11(국민연금 가입자 수 및 여성 가입자 비율)_" +sdf.format(System.currentTimeMillis()), "UTF-8");
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
    public List<JewD03TiileDataVO> getSelectList(JewD03TiileDataVO vo) throws Exception {
        return jgD03TitleDAO.selectList(vo);
    }
}
