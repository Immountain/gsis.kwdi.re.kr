package infomind.com.report.jasperreports.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import egovframework.com.cmm.EgovMessageSource;
import infomind.com.cms.ccm.ccc.service.InfoCcmCmmnClCodeManageService;
import infomind.com.report.ReoprtConstants;
import infomind.com.report.ReoprtFactory;
import infomind.com.report.jasperreports.service.ReportSampleService;
import infomind.com.report.jasperreports.vo.ReportBaseParameterVO;
import infomind.com.report.jasperreports.vo.ReportBaseVO;
import infomind.com.report.jasperreports.vo.ReportSampleVO;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.jasperreports.JasperReportsPdfView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.*;

import static infomind.com.report.ReoprtFactory.reportPath;
import static infomind.com.report.ReoprtFactory.writeHeader;

@Controller
public class ReoprtController implements ReoprtConstants {


    /** reportSampleService */
    @Resource(name = "reportSampleService")
    ReportSampleService reportSampleService;

    private JRBeanCollectionDataSource dataSource;


    @Resource(name = "InfoCcmCmmnClCodeManageService")
    private InfoCcmCmmnClCodeManageService infoCcmCmmnClCodeManageService;


    @RequestMapping("/cms/report/test.do")
    @ResponseBody
    public ModelAndView generatedReportGet(ModelAndView modelAndView) throws JRException, IOException {


        System.out.println("generatedReportGet");


        Map<String,Object> parameterMap = new HashMap<String,Object>();

        List<ReportSampleVO> codeList = new ArrayList<ReportSampleVO>();

        ReportSampleVO vo = new ReportSampleVO();
        vo.setStdtNmEng("111");
        vo.setStdtNmKor("양진혁");
        codeList.add(vo);



        JasperReportsPdfView view = new JasperReportsPdfView();
        view.setUrl("/WEB-INF/reports/jasperreports/Receipt.jrxml");

        JRDataSource JRdataSource = new JRBeanCollectionDataSource(codeList);

        parameterMap.put( "datasource", JRdataSource);

         modelAndView = new ModelAndView(view , parameterMap);


//        JasperReportsPdfView view = new JasperReportsPdfView();
//        view.setJdbcDataSource(dataSource);
//
//        Map<String, Object> params = new HashMap<>();
//        params.put("param1", "param1 value");
//        view.setApplicationContext(appContext);
//        return new ModelAndView(view, params);



        return modelAndView;
    }

    @RequestMapping("/cms/report/test2.do")
    @ResponseBody
    public void generatedReportGet(ReportBaseVO parameterVo, HttpServletResponse response, HttpServletRequest request) throws JRException, IOException {




        Map<String, Object> params = new HashMap<String, Object>();
        params=parameterVo.getParamsMap();
        String imagePath = request.getSession().getServletContext().getRealPath("/") + "assets/report/images/";
        params.put("imagePath", imagePath);




        List<ReportSampleVO> codeList = new ArrayList<ReportSampleVO>();
        ReportSampleVO setVo = new ReportSampleVO();
        setVo.setStdtNmEng("111");
        setVo.setStdtNmKor("양진혁");
        codeList.add(setVo);




        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(codeList);
        InputStream inputStream = this.getClass().getResourceAsStream(reportPath(parameterVo.getReportPath(),parameterVo.getReportFileNm()));
        JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, dataSource);
        inputStream.close();

        ReoprtFactory.writeHeader(response,parameterVo.getReportDownloadFileNm());
        OutputStream out = response.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, out);
    }




}
