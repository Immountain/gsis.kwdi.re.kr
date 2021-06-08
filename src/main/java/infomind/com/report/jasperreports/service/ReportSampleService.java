package infomind.com.report.jasperreports.service;


import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.HtmlExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleHtmlExporterOutput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.Map;


@Service
public class ReportSampleService {
    private static final Logger log = LoggerFactory.getLogger(ReportSampleService.class);


    public void exportReportToHtml(HttpServletResponse response, String inputFileName, JRBeanCollectionDataSource dataSource) throws Exception {
        InputStream inputStream = this.getClass().getResourceAsStream("/reports/" + inputFileName +".jrxml");



        try {
            response.setContentType("text/html");
            JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, dataSource);
            HtmlExporter exporter = new HtmlExporter(DefaultJasperReportsContext.getInstance());
            exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
            exporter.setExporterOutput(new SimpleHtmlExporterOutput(response.getWriter()));
            exporter.exportReport();
        } catch (JRException | IOException e) {
            e.printStackTrace();
        } finally {
            inputStream.close();
        }
    }


    public byte[] generatedToPdf(String inputFileName, Map<String, Object> params, JRBeanCollectionDataSource dataSource) throws Exception{
        log.info("****************generate PDF report****************");
        byte[] pdfReport = null;
        InputStream inputStream = this.getClass().getResourceAsStream("/infomind/reports/jasperreports/" + inputFileName +".jrxml");
        try {
            log.info("***infomind*** Start Compiling!!!!");
            JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);
            log.info("***infomind*** Done Compiling!!! ...");
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,
                    params, dataSource);
            if (jasperPrint != null) {
                 pdfReport = JasperExportManager
                        .exportReportToPdf(jasperPrint);
                log.info("******* inputStream Close *******");
                inputStream.close();
            }
        } catch (JRException | IOException e) {
            e.printStackTrace();
        }
        return pdfReport;
    }




    public static JRBeanCollectionDataSource getDataSource(Collection dataSource) {
        return new JRBeanCollectionDataSource(dataSource);
    }


}