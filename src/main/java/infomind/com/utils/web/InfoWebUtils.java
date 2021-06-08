package infomind.com.utils.web;


import infomind.com.cmm.InfoConstants;
import infomind.com.cms.info.site.vo.InfoSiteVO;
import infomind.com.exception.SiteNotFoundException;
import org.apache.commons.io.FileUtils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.StreamUtils;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.nio.charset.Charset;


public class InfoWebUtils {
    private static Log logger = LogFactory.getLog(InfoWebUtils.class);


    public static File createFile(String dirName, String fileName) {
        File postDir = new File(InfoConstants.DIR_PATH() + dirName);

        if (!postDir.isDirectory()) {
            postDir.mkdirs();
        }

        return new File(postDir, fileName);
    }

    public static File createSourceFile(String dirName, String fileName, String content) {
        try {


            System.out.println("dirName===>"+dirName +"  fileName==>"+fileName);
            File postFile = createFile(dirName, fileName);
            FileUtils.writeStringToFile(postFile, content, Charset.forName("UTF-8"));
            System.out.println("write file " + postFile.getAbsolutePath());


            return postFile;
        } catch (Exception e) {
            logger.error(e.getMessage());

            return null;
        }
    }


    public static File createJspFile(String dirName, String fileName, String headerFilePath, String content) {
        try {
            File postDir = new File(InfoConstants.DIR_PATH() + dirName);

            if (!postDir.isDirectory()) {
                postDir.mkdirs();
            }

            String headerString = "";

            File postFile = new File(postDir, fileName + ".jsp");

            try {
                logger.debug("headerFilePath : " + headerFilePath);
                logger.debug(String.format("postDir : %s,fileName : %s", postDir, fileName));

                File headerFile = new File(headerFilePath);

                headerString = StreamUtils.copyToString(new FileInputStream(headerFile), Charset.defaultCharset());
            } catch (Exception e) {
                headerString = "<%@ page contentType=\"text/html;charset=UTF-8\" language=\"java\" %>\r\n" +
                        "<%@ taglib prefix=\"fmt\" uri=\"http://java.sun.com/jsp/jstl/fmt\" %>\r\n" +
                        "<%@ taglib prefix=\"form\" uri=\"http://www.springframework.org/tags/form\" %>\r\n" +
                        "<%@ taglib prefix=\"c\" uri=\"http://java.sun.com/jsp/jstl/core\" %>\r\n" +
                        "<%@ taglib prefix=\"info\" uri=\"http://infomind.com/info\" %>\r\n" +
                        content;
            }

            FileUtils.writeStringToFile(postFile, headerString+content, Charset.forName("UTF-8"));

            logger.debug("write post file " + postFile.getAbsolutePath());

            return postFile;
        } catch (Exception e) {
            logger.error(e.getMessage());

            return null;
        }
    }

    public static InfoSiteVO getCurrentSiteInfo(HttpServletRequest request) {
        InfoSiteVO infoSiteVO = (InfoSiteVO) WebUtils.getSessionAttribute(request, InfoConstants.SESSION_SITE_INFO_NAME);
        if(infoSiteVO == null) {
            throw new SiteNotFoundException("사이트 정보가 없습니다.");
        }
        return infoSiteVO;
    }

    public static InfoSiteVO getRefererSiteInfo(HttpServletRequest request) {
        InfoSiteVO infoSiteVO = (InfoSiteVO) WebUtils.getSessionAttribute(request, InfoConstants.REFERER_SITE_INFO_NAME);
        if(infoSiteVO == null) {
            throw new SiteNotFoundException("사이트 정보가 없습니다.");
        }
        return infoSiteVO;
    }

    public static Object getSessionUser(HttpServletRequest request) {
        return request.getSession().getAttribute("loginVO");
    }
}
