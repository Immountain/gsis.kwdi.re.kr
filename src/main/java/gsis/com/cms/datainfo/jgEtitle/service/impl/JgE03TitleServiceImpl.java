package gsis.com.cms.datainfo.jgEtitle.service.impl;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import gsis.com.cms.datainfo.jgDtitle.dao.JgD06TitleDAO;
import gsis.com.cms.datainfo.jgDtitle.service.JgD06TitleService;
import gsis.com.cms.datainfo.jgDtitle.vo.JewD06TiileDataVO;
import gsis.com.cms.datainfo.jgEtitle.dao.JgE03TitleDAO;
import gsis.com.cms.datainfo.jgEtitle.service.JgE03TitleService;
import gsis.com.cms.datainfo.jgEtitle.vo.JewE03TiileDataVO;
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

@Service("JgE03TitleService")
public class JgE03TitleServiceImpl extends EgovAbstractServiceImpl implements JgE03TitleService {



    @Autowired
    private ResourceLoader resourceLoader;

    @Resource(name="JgE03TitleDAO")
    private JgE03TitleDAO jgE03TitleDAO;



    @Override
    public List<JewE03TiileDataVO> selectList(JewE03TiileDataVO vo) throws Exception {
        return jgE03TitleDAO.selectList(vo);
    }

    @Override
    public void insert(JewE03TiileDataVO vo) throws Exception {


        for(JewE03TiileDataVO tVo : vo.getListData()){
            jgE03TitleDAO.delete(tVo);
            jgE03TitleDAO.insert(tVo);
        }

    }

    @Override
    public void delete(JewE03TiileDataVO vo) throws Exception {


        jgE03TitleDAO.delete(vo);

    }

    @Override
    public void excelDownload(JewE03TiileDataVO searchVO, HttpServletResponse response) {

        try {


            InputStream templateStream = resourceLoader.getResource("classpath:/gsis/excel/jgEtitle/e03.xlsx").getInputStream();
            OutputStream targetStream = response.getOutputStream();

            Context context = new Context();
//            context.putVar("list", list);
//            context.putVar("equipMstList", equipMstList);



            SimpleDateFormat sdf = new SimpleDateFormat( "yyyyMMddHHmmss");
            String fileName = java.net.URLEncoder.encode("5-4(주요부서 여성공무원 현황)_" +sdf.format(System.currentTimeMillis()), "UTF-8");
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
    public List<JewE03TiileDataVO> getSelectList(JewE03TiileDataVO vo) throws Exception {
        return jgE03TitleDAO.selectList(vo);
    }
}
