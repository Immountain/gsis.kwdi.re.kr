package infomind.com.utils.page;

import infomind.com.cmm.InfoConstants;
import infomind.com.cms.info.page.vo.InfoPageInfoVO;
import infomind.com.utils.http.InfoHttpUtils;
import infomind.com.utils.web.InfoWebUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.tiles.el.ELAttributeEvaluator;

import java.io.File;
import java.io.IOException;

public class InfoPageUtils {
    private static Log logger = LogFactory.getLog(InfoPageUtils.class);


    public static File createPageFile(String headerFilePath, String postId, String path, String pageContent, String group) {


        System.out.println("PAGE_DIR==>" + InfoConstants.PAGE_DIR());


        return InfoWebUtils.createJspFile(
                InfoConstants.PAGE_DIR() + "/" + group + "/" + path + "/info",
                postId,
                InfoConstants.DIR_PATH() + headerFilePath,
                pageContent);
    }


    public static File createLayoutFile(String headerFilePath, String postId, String path, String layoutContent) {


        System.out.println("PAGE_DIR_LAYOUT==>" + InfoConstants.PAGE_DIR_LAYOUT());


        return InfoWebUtils.createJspFile(
                InfoConstants.PAGE_DIR_LAYOUT() + "/" + path + "/info",
                postId,
                InfoConstants.DIR_PATH() + headerFilePath,
                layoutContent);
    }


    public static File createPageFile(String content, String path, String group) throws IOException {

        return createPageFile("/WEB-INF/jsp/page/header.jsp", "info", path, content, group);
    }


    public static File createLayoutFile(String content, String path) throws IOException {

        return createLayoutFile("/WEB-INF/jsp/page/header.jsp", "info-layouts", path, content);
    }

}
