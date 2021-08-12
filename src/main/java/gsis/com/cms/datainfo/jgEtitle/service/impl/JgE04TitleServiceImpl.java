package gsis.com.cms.datainfo.jgEtitle.service.impl;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import gsis.com.cms.datainfo.jgEtitle.dao.JgE03TitleDAO;
import gsis.com.cms.datainfo.jgEtitle.dao.JgE04TitleDAO;
import gsis.com.cms.datainfo.jgEtitle.service.JgE03TitleService;
import gsis.com.cms.datainfo.jgEtitle.service.JgE04TitleService;
import gsis.com.cms.datainfo.jgEtitle.vo.JewE03TiileDataVO;
import gsis.com.cms.datainfo.jgEtitle.vo.JewE04TiileDataVO;
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

@Service("JgE04TitleService")
public class JgE04TitleServiceImpl extends EgovAbstractServiceImpl implements JgE04TitleService {



    @Autowired
    private ResourceLoader resourceLoader;

    @Resource(name="JgE04TitleDAO")
    private JgE04TitleDAO jgE04TitleDAO;



    @Override
    public List<JewE04TiileDataVO> selectList(JewE04TiileDataVO vo) throws Exception {
        return jgE04TitleDAO.selectList(vo);
    }

    @Override
    public void insert(JewE04TiileDataVO vo) throws Exception {


        for(JewE04TiileDataVO tVo : vo.getListData()){
            jgE04TitleDAO.delete(tVo);
            jgE04TitleDAO.insert(tVo);
        }

    }

    @Override
    public void delete(JewE04TiileDataVO vo) throws Exception {


        jgE04TitleDAO.delete(vo);

    }

    @Override
    public void excelDownload(JewE04TiileDataVO searchVO, HttpServletResponse response) {

        try {


            InputStream templateStream = resourceLoader.getResource("classpath:/gsis/excel/jgEtitle/e04.xlsx").getInputStream();
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
    public List<JewE04TiileDataVO> getSelectList(JewE04TiileDataVO vo) throws Exception {
        return jgE04TitleDAO.selectList(vo);
    }
}
