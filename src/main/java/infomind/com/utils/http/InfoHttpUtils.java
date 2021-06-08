package infomind.com.utils.http;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.net.InternetDomainName;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.entity.ContentType;
import org.springframework.web.util.UrlPathHelper;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.entity.ContentType;


public class InfoHttpUtils {
    private static final Log logger = LogFactory.getLog(InfoHttpUtils.class);

    public static String addQuery(String queryString, String appendQuery) {
        if (queryString.contains("?")) {
            queryString += "&" + appendQuery;
        } else {
            queryString += "?" + appendQuery;
        }

        return queryString;
    }

    public static URI appendUri(String uri, String appendQuery) throws URISyntaxException {
        URI oldUri = new URI(uri);

        String newQuery = oldUri.getQuery();

        if (newQuery == null) {
            newQuery = appendQuery;
        } else {
            newQuery += "&" + appendQuery;
        }

        return new URI(oldUri.getScheme(), oldUri.getAuthority(), oldUri.getPath(), newQuery, oldUri.getFragment());
    }

    public static String addQueryArgs(HttpServletRequest request, String param, String u) throws Exception {
        String queryString = request.getQueryString();

        Map<String, String> resultMap = new HashMap<String, String>();

        if (!StringUtils.isEmpty(queryString)) {
            try {
                Map<String, String> queryParamMap = Splitter.on("&").withKeyValueSeparator("=").split(queryString);
                resultMap.putAll(queryParamMap);
            } catch (Exception e) {
                logger.warn(e.getMessage());
            }
        }

        if (!StringUtils.isEmpty(param)) {
            Map<String, String> queryAppendParamMap = Splitter.on("&").withKeyValueSeparator("=").split(param);
            resultMap.putAll(queryAppendParamMap);
        }

        String params = Joiner.on("&").withKeyValueSeparator("=").join(resultMap);

        if (u == null) {
            u = "";
        }

        if (!u.startsWith("http") && !u.startsWith("https")) {
            if (StringUtils.isEmpty(u)) {
                URI uri = getCurrentUri(request);

                u = uri.getPath();
            } else {
                if (!StringUtils.isEmpty(request.getContextPath())) {
                    u = request.getContextPath() + "/" + u;
                }

            }
        }

        if (!StringUtils.isEmpty(params)) {
            return u + "?" + params;
        } else {
            return u;
        }
    }

    public static String addQueryArgs(HttpServletRequest request, String q) throws Exception {
        return addQueryArgs(request, q, "");
    }

    public static URI getCurrentUri(HttpServletRequest request) throws Exception {
        URL url = new URL(request.getRequestURL().toString());
        String host = url.getHost();
        String userInfo = url.getUserInfo();
        String scheme = url.getProtocol();
        int port = url.getPort();
        String path = new UrlPathHelper().getOriginatingRequestUri(request);
        String query_string = (String) request.getAttribute("javax.servlet.forward.query_string");

        return new URI(scheme, userInfo, host, port, path, query_string, null);
    }

    public static String getCurrentUrl(HttpServletRequest request) throws Exception {
        URI uri = getCurrentUri(request);

        return uri.toString();
    }

    public static String getHost(HttpServletRequest request) {
        String result = request.getScheme() + "://" + request.getServerName();

        // System.out.println("================================");
        // System.out.println(request.getServerName());
        // System.out.println(request.getServletPath());
        // System.out.println(request.getContextPath());
        // System.out.println(request.getLocalName());
        // System.out.println(request.getServletContext().getServletContextName());
        // System.out.println(request.getServletContext().getInitParameter("cpath"));
        // System.out.println("================================");


        if (request.getServerPort() != 80) {
            result += ":" + request.getServerPort();
        }

        return result;
    }

    public static String getSubPath(HttpServletRequest request) {
        try {
            URL url = new URL(getHost(request) + new UrlPathHelper().getOriginatingRequestUri(request));
            String[] path = url.getPath().split("/");
            if (path.length > 0) {
                path = ArrayUtils.add(url.getPath().split("/"), "");
                String result = StringUtils.isNotEmpty(request.getContextPath()) ? path[2] : path[1];
                if (!result.contains(".")) {
                    return result;
                } else {
                    return "";
                }
            }

            return "";
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return "";
    }



    public static String getPath(HttpServletRequest request) {
        try {
            URL url = new URL(getHost(request) + new UrlPathHelper().getOriginatingRequestUri(request));
            String[] path = url.getPath().split("/");
            if (path.length > 0) {
                String result = path[2];
                if (!result.contains(".")) {
                    return result;
                } else {
                    return "";
                }
            }

            return "";
        } catch (MalformedURLException e) {
            return "";
        }
    }

    public static String getUrlPath(HttpServletRequest request) {
        try {
            URL url = new URL(getHost(request) + new UrlPathHelper().getOriginatingRequestUri(request));
            String[] path = url.getPath().split("/");
            if (path.length > 0) {

                String pathUrl ="";
                for(int i=1; i<path.length-1;i++){

                    pathUrl=pathUrl+"/"+path[i];

                    System.out.println("path==>"+path[i]);
                    System.out.println("pathUrl==>"+pathUrl);
                }
               return pathUrl;



            }

            return "";
        } catch (MalformedURLException e) {
            return "";
        }
    }


    public static String getUrl(HttpServletRequest request) {
        try {
            URL url = new URL(getHost(request) + new UrlPathHelper().getOriginatingRequestUri(request));
           String path = url.getPath();

            return path;
        } catch (MalformedURLException e) {
            return "";
        }
    }

    public static String getPageSlugPath(HttpServletRequest request) {
        Pattern pattern = Pattern.compile("\\/page\\/(.*)");
        Matcher matcher = pattern.matcher(request.getRequestURI());
        if(matcher.find()) {
            return matcher.group(1);
        }
        return null;
    }

    public static String getBoardSlugPath(HttpServletRequest request) {
        Pattern pattern = Pattern.compile("\\/board\\/(.*)\\/");
        Matcher matcher = pattern.matcher(request.getRequestURI());
        if(matcher.find()) {
            return matcher.group(1);
        }
        return null;
    }



    public static String getHostWithAppendWWW(HttpServletRequest request) {
        return getHostWithAppendWWW(getHost(request));
    }

    public static String getHostWithAppendWWW(String host) {
        try {
            URI uri = new URI(host);
            String port = "";
            if (uri.getPort() != -1 && uri.getPort() != 443) {
                port = ":" + uri.getPort();
            }

            String hostName = uri.getHost();

            InternetDomainName name = InternetDomainName.from(hostName).topPrivateDomain();

            if (name.toString().equals(hostName) && !hostName.startsWith("www")) {
                URI resultUri = new URI("www." + uri.getHost() + port);

                return resultUri.toString();
            } else {
                return uri.getHost() + port;
            }
        } catch (Exception e) {
            try {
                URI uri = new URI(host);
                String port = "";
                if (uri.getPort() != -1) {
                    port = ":" + uri.getPort();
                }

                return uri.getHost() + port;
            } catch (URISyntaxException e1) {
                e1.printStackTrace();
            }

            logger.error("getHostWithAppendWWW : " + e.getMessage());
        }

        return host;
    }

    public static String addQueryArgsAndContextPath(HttpServletRequest request, String q, String u) {
        try {
            String url = InfoHttpUtils.addQueryArgs(request, q, u);
            String cPath = String.valueOf(request.getAttribute("cPath"));

            if (!StringUtils.isEmpty(cPath) && !url.startsWith("http") && !url.startsWith("https")) {
                url = cPath + "/" + url;
            }

            return url;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static String getClientIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    //http://stackoverflow.com/a/18030465/1845894
    public static String getClientOS(HttpServletRequest request) {
        final String browserDetails = request.getHeader("User-Agent");

        final String lowerCaseBrowser = browserDetails.toLowerCase();

        if (lowerCaseBrowser.contains("windows")) {
            return "Windows";
        } else if (lowerCaseBrowser.contains("mac")) {
            return "Mac";
        } else if (lowerCaseBrowser.contains("x11")) {
            return "Unix";
        } else if (lowerCaseBrowser.contains("android")) {
            return "Android";
        } else if (lowerCaseBrowser.contains("iphone")) {
            return "IPhone";
        } else {
            return "UnKnown, More-Info: " + browserDetails;
        }
    }

    public static String getClientBrowser_s(HttpServletRequest request) {
        final String browserDetails = request.getHeader("User-Agent");
        final String user = browserDetails.toLowerCase();

        String browser_s = "";

        //===============Browser===========================
        if (user.contains("msie")) {
            browser_s = "IE";
        } else if (user.contains("safari") && user.contains("version")) {
            browser_s = "SAFARI";
        } else if (user.contains("opr") || user.contains("opera")) {
            if (user.contains("opera")) {
                browser_s = "OPERA";
            } else if (user.contains("opr")) {
                browser_s = "OPERA";
            }

        } else if (user.contains("chrome")) {
            browser_s = "CHROME";
        } else if ((user.contains("mozilla/7.0")) || (user.contains("netscape6")) || (user.contains("mozilla/4.7")) || (user.contains("mozilla/4.78")) || (user.contains("mozilla/4.08")) || (user.contains("mozilla/3"))) {
            browser_s = "NETSCAPE";
        } else if (user.contains("firefox")) {
            browser_s = "FIREFOX";
        } else if (user.contains("rv")) {
            browser_s = "RV";
        } else {
            browser_s = "UnKnown";
        }

        return browser_s;
    }

    public static String getClientBrowser(HttpServletRequest request) {
        final String browserDetails = request.getHeader("User-Agent");
        final String user = browserDetails.toLowerCase();

        String browser = "";

        //===============Browser===========================
        if (user.contains("msie")) {

            String substring = browserDetails.substring(browserDetails.indexOf("MSIE")).split(";")[0];
            browser = substring.split(" ")[0].replace("MSIE", "IE") + "-" + substring.split(" ")[1];
        } else if (user.contains("safari") && user.contains("version")) {
            browser = (browserDetails.substring(browserDetails.indexOf("Safari")).split(" ")[0]).split(
                    "/")[0] + "-" + (browserDetails.substring(
                    browserDetails.indexOf("Version")).split(" ")[0]).split("/")[1];
        } else if (user.contains("opr") || user.contains("opera")) {
            if (user.contains("opera")) {
                browser = (browserDetails.substring(browserDetails.indexOf("Opera")).split(" ")[0]).split(
                        "/")[0] + "-" + (browserDetails.substring(
                        browserDetails.indexOf("Version")).split(" ")[0]).split("/")[1];
            } else if (user.contains("opr")) {
                browser = ((browserDetails.substring(browserDetails.indexOf("OPR")).split(" ")[0]).replace("/",
                        "-")).replace(
                        "OPR", "Opera");
            }

        } else if (user.contains("chrome")) {
            browser = (browserDetails.substring(browserDetails.indexOf("Chrome")).split(" ")[0]).replace("/", "-");
        } else if ((user.contains("mozilla/7.0")) || (user.contains("netscape6")) || (user.contains("mozilla/4.7")) || (user.contains("mozilla/4.78")) || (user.contains("mozilla/4.08")) || (user.contains("mozilla/3"))) {
            browser = "Netscape-?";
        } else if (user.contains("firefox")) {
            browser = (browserDetails.substring(browserDetails.indexOf("Firefox")).split(" ")[0]).replace("/", "-");
        } else if (user.contains("rv")) {
            browser = "rv";
        } else {
            browser = "UnKnown, More-Info: " + browserDetails;
        }

        return browser;
    }

    public static String getUserAgent(HttpServletRequest request) {
        return request.getHeader("User-Agent");
    }



    /**
     * Request 에서 Body Data를 꺼냄
     * @param request
     * @return
     */
    public static String getBodyDataByRequest( HttpServletRequest request) {

        String charEncoding = request.getCharacterEncoding(); // 인코딩 설정
        Charset encoding = StringUtils.isBlank(charEncoding) ? StandardCharsets.UTF_8 : Charset.forName(charEncoding);

        String collect = null;
        try {

            InputStream is = request.getInputStream();
            byte[] rawData = IOUtils.toByteArray(is); // InputStream 을 별도로 저장한 다음 getReader() 에서 새 스트림으로 생성

            // body 파싱
            collect = new BufferedReader(new InputStreamReader( getInputStream( rawData), encoding)).lines().collect(Collectors.joining(System.lineSeparator()));

            if (StringUtils.isEmpty(collect)) { // body 가 없을경우 로깅 제외

                return null;
            }
            if (request.getContentType() != null && request.getContentType().contains(

                    ContentType.MULTIPART_FORM_DATA.getMimeType())) { // 파일 업로드시 로깅제외
                return null;
            }



        } catch( Exception e) {

            e.printStackTrace();

        }
        return collect;
    }





    private static ServletInputStream getInputStream(byte[] rawData) throws IOException {

        final ByteArrayInputStream bais = new ByteArrayInputStream( rawData);
        ServletInputStream sis = new ServletInputStream() {

            @Override
            public int read() throws IOException {
                return bais.read();
            }

            @Override
            public void setReadListener(ReadListener readListener) {
            }

            @Override
            public boolean isReady() {
                return false;
            }

            @Override
            public boolean isFinished() {
                return false;
            }
        };

        return sis;
    }
}
