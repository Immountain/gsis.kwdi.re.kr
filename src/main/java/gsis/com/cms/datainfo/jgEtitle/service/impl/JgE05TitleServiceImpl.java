package gsis.com.cms.datainfo.jgEtitle.service.impl;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import gsis.com.cms.datainfo.jgEtitle.dao.JgE05TitleDAO;
import gsis.com.cms.datainfo.jgEtitle.service.JgE05TitleService;
import gsis.com.cms.datainfo.jgEtitle.vo.JewE05TiileDataVO;
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

@Service("JgE05TitleService")
public class JgE05TitleServiceImpl extends EgovAbstractServiceImpl implements JgE05TitleService {



    @Autowired
    private ResourceLoader resourceLoader;

    @Resource(name="JgE05TitleDAO")
    private JgE05TitleDAO jgE05TitleDAO;



    @Override
    public List<JewE05TiileDataVO> selectList(JewE05TiileDataVO vo) throws Exception {
        return jgE05TitleDAO.selectList(vo);
    }

    @Override
    public void insert(JewE05TiileDataVO vo) throws Exception {


        for(JewE05TiileDataVO tVo : vo.getListData()){
            jgE05TitleDAO.delete(tVo);
            jgE05TitleDAO.insert(tVo);
        }

    }

    @Override
    public void delete(JewE05TiileDataVO vo) throws Exception {


        jgE05TitleDAO.delete(vo);

    }

    @Override
    public void excelDownload(JewE05TiileDataVO searchVO, HttpServletResponse response) {

        try {


            InputStream templateStream = resourceLoader.getResource("classpath:/gsis/excel/jgEtitle/e05.xlsx").getInputStream();
            OutputStream targetStream = response.getOutputStream();

            Context context = new Context();
//            context.putVar("list", list);
//            context.putVar("equipMstList", equipMstList);



            SimpleDateFormat sdf = new SimpleDateFormat( "yyyyMMddHHmmss");
            String fileName = java.net.URLEncoder.encode("5-8(인구 연간 1인당 평균 독서량)_" +sdf.format(System.currentTimeMillis()), "UTF-8");
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
    public List<JewE05TiileDataVO> getSelectList(JewE05TiileDataVO vo) throws Exception {
        return jgE05TitleDAO.selectList(vo);
    }
}
