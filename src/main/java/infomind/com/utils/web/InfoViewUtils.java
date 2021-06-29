package infomind.com.utils.web;

import infomind.com.cms.info.site.vo.InfoSiteVO;
import org.apache.commons.lang.StringUtils;

public class InfoViewUtils {

    private static final String FORWARD_PATH = "forward:%s";
    private static final String REDIRECT_PATH = "redirect:%s";

    public static String adminTilesView(String path, String page, String type) {
        return "infomind/com/cms/" + path + page + "." + type;
    }

    public static String adminWjView(String path, String page, String type) {
        return "wj/com/cms/" + path + page + "." + type;
    }

    public static String adminJsView(String path, String page, String type) {
        return "gsis/com/cms/" + path + page + "." + type;
    }

    public static String adminBoardView(String path, String page, String type) {
        return "infomind/com/cms/info/board/skin/" + path.toLowerCase() + "/" + page + "." + type;
    }


    public static String adminLatesBoardView(String path, String type) {

        String view = "";
        if ("".equals(type) || type == null) {

            view = "";
        } else {

            view = "." + type;
        }


        return "infomind/com/cms/info/board/latest/" + path + "/latest" + view;
    }

    public static String pageTilesView(String path) {
        return path;
    }

    public static String pageContentView(String path, String view, String layout) {
        String tempPath = (path == null || "".equals(path)) ? "" : "/" + path;
        String tempView = (view == null || "".equals(view)) ? "" : "/" + view;
        String tempLayout = (layout == null || "".equals(layout)) ? "" : ".page";
        String pageUrl = "page" + tempPath + tempView + tempLayout;
        return pageUrl;
    }


    public static String jtPpageContentView(String path, String view, String layout) {
        String tempPath = (path == null || "".equals(path)) ? "" : "/" + path;
        String tempView = (view == null || "".equals(view)) ? "" : "/" + view;
        String tempLayout = (layout == null || "".equals(layout)) ? "" : ".page";
        String pageUrl = "ipp/jtp/site" + tempPath + tempView + tempLayout;
        return pageUrl;
    }


    public static String gsisPpageContentView(String path, String view, String layout) {
        String tempPath = (path == null || "".equals(path)) ? "" : "/" + path;
        String tempView = (view == null || "".equals(view)) ? "" : "/" + view;
        String tempLayout = (layout == null || "".equals(layout)) ? "" : ".page";
        String pageUrl = "gsis/com/site" + tempPath + tempView + tempLayout;
        return pageUrl;
    }


    public static String contentView(String path) {


        return path;
    }

    public static String normalView(String path) {

        return path;
    }


    public static String boardContentView(String path, String view, String layout) {

        String tempPath = (path == null || "".equals(path)) ? "" : "/" + path;
        String tempView = (view == null || "".equals(view)) ? "" : "/" + view;
        String tempLayout = (layout == null || "".equals(layout)) ? "" : ".page";
        String pageUrl = "board/skin" + tempPath + tempView + tempLayout;
        return pageUrl;
    }


    public static String errorContentView(String layout) {
        String tempLayout = (layout == null || "".equals(layout)) ? "" : ".page";
        String pageUrl = "board/skin/" + "error" + tempLayout;
        return pageUrl;
    }


    public static String boardLatesView(String board) {

        return "board/latest/" + board + "/latest";
    }

    public static String boardLatesView(String board, String path) {

        return "board/latest/" + board + "/" + path;
    }


    public static String bannerLatesView(String path) {

        return "banner/" + path + "/view";
    }

    public static String forwardSiteMainPage(InfoSiteVO infoSite) {
        // /page/{slug}
        StringBuilder mainPage = new StringBuilder("");

        //if(StringUtils.isNotEmpty(infoSite.getSubPath())) {
        //    mainPage.append("/");
        //    mainPage.append(infoSite.getSubPath());
        //}

        mainPage.append("/page/");
        mainPage.append(infoSite.getIndexPage());

        return String.format(FORWARD_PATH, mainPage);
    }
}
