package gsis.com.cms.datainfo.jgEtitle.service.impl;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import gsis.com.cms.datainfo.jgEtitle.dao.JgE02TitleDAO;
import gsis.com.cms.datainfo.jgEtitle.service.JgE02TitleService;
import gsis.com.cms.datainfo.jgEtitle.vo.JewE02TiileDataVO;
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

@Service("JgE02TitleService")
public class JgE02TitleServiceImpl extends EgovAbstractServiceImpl implements JgE02TitleService {



    @Autowired
    private ResourceLoader resourceLoader;

    @Resource(name="JgE02TitleDAO")
    private JgE02TitleDAO jgE02TitleDAO;



    @Override
    public List<JewE02TiileDataVO> selectList(JewE02TiileDataVO vo) throws Exception {
        return jgE02TitleDAO.selectList(vo);
    }

    @Override
    public void insert(JewE02TiileDataVO vo) throws Exception {


        for(JewE02TiileDataVO tVo : vo.getListData()){
            jgE02TitleDAO.delete(tVo);
            jgE02TitleDAO.insert(tVo);
        }

    }

    @Override
    public void delete(JewE02TiileDataVO vo) throws Exception {


        jgE02TitleDAO.delete(vo);

    }

    @Override
    public void excelDownload(JewE02TiileDataVO searchVO, HttpServletResponse response) {

        try {


            InputStream templateStream = resourceLoader.getResource("classpath:/gsis/excel/jgEtitle/e02.xlsx").getInputStream();
            OutputStream targetStream = response.getOutputStream();

            Context context = new Context();
//            context.putVar("list", list);
//            context.putVar("equipMstList", equipMstList);



            SimpleDateFormat sdf = new SimpleDateFormat( "yyyyMMddHHmmss");
            String fileName = java.net.URLEncoder.encode("5-2(제7회 전국동시지방선거 입후보자및 당선인현황)_" +sdf.format(System.currentTimeMillis()), "UTF-8");
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
    public List<JewE02TiileDataVO> getSelectList(JewE02TiileDataVO vo) throws Exception {
        return jgE02TitleDAO.selectList(vo);
    }
}
