package infomind.com.cmm.aspect;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.sym.log.lgm.service.EgovSysLogService;
import infomind.com.cmm.filter.CmsAjaxFilter;
import infomind.com.cms.log.service.InfoCmsLogService;
import infomind.com.cms.log.vo.InfoCmsLogVO;
import infomind.com.ext.vo.CmsSearchVO;
import infomind.com.utils.http.InfoHttpUtils;
import infomind.com.utils.web.InfoReadableRequestWrapper;
import net.sf.json.JSONObject;
import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StopWatch;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.IOException;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;


public class InfoCmsAOP {

    private static final Logger LOG = LoggerFactory.getLogger(InfoCmsAOP.class);


    @Resource(name = "InfoCmsLogService")
    private InfoCmsLogService infoCmsLogService;


    public Object methodLogger(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        try {

            Object result = proceedingJoinPoint.proceed();


            return result;

        } catch (Throwable throwable) {
            throw throwable;
        } finally {
            stopWatch.stop();

            saveLog(proceedingJoinPoint, "ALL", Long.toString(stopWatch.getTotalTimeMillis()));
        }
    }


    public Object logInsert(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        StopWatch stopWatch = new StopWatch();

        try {
            stopWatch.start();
            Object result = proceedingJoinPoint.proceed();


            return result;

        } catch (Throwable throwable) {
            throw throwable;
        } finally {
            stopWatch.stop();

            saveLog(proceedingJoinPoint, "I", Long.toString(stopWatch.getTotalTimeMillis()));
        }


    }


    public Object logUpdate(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        StopWatch stopWatch = new StopWatch();
        try {
            stopWatch.start();
            Object result = proceedingJoinPoint.proceed();


            return result;

        } catch (Throwable throwable) {
            throw throwable;
        } finally {
            stopWatch.stop();
            saveLog(proceedingJoinPoint, "U", Long.toString(stopWatch.getTotalTimeMillis()));
        }


    }


    public Object logDelete(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        StopWatch stopWatch = new StopWatch();

        try {
            stopWatch.start();
            Object result = proceedingJoinPoint.proceed();


            return result;

        } catch (Throwable throwable) {
            throw throwable;
        } finally {
            stopWatch.stop();
            saveLog(proceedingJoinPoint, "D", Long.toString(stopWatch.getTotalTimeMillis()));
        }


    }


    public Object logSelect(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        StopWatch stopWatch = new StopWatch();

        try {
            stopWatch.start();
            Object result = proceedingJoinPoint.proceed();
            return result;

        } catch (Throwable throwable) {
            throw throwable;
        } finally {
            stopWatch.stop();
            saveLog(proceedingJoinPoint, "L", Long.toString(stopWatch.getTotalTimeMillis()));

        }


    }


    private void saveLog(ProceedingJoinPoint proceedingJoinPoint, String type, String time) {
        try {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest(); // request 정보를 가져온다.
            String controllerName = proceedingJoinPoint.getSignature().getDeclaringType().getSimpleName();
            String methodName = proceedingJoinPoint.getSignature().getName();

            Map<String, Object> params = new HashMap<>();

            Gson gson = new Gson();

            String getParams = getParams(request).toString();
            CmsSearchVO object = gson.fromJson(getParams, CmsSearchVO.class);

            if (!"".equals(object.getMenuTargetNo())) {


                InfoCmsLogVO infoCmsLogVO = new InfoCmsLogVO();


                infoCmsLogVO.setLogUrl(request.getRequestURI());
                infoCmsLogVO.setLogController(controllerName);
                infoCmsLogVO.setLogHttpMethod(request.getMethod());
                infoCmsLogVO.setLogMethod(methodName);
                infoCmsLogVO.setLogProcessSeCode(type);
                infoCmsLogVO.setLogProcessTime(time);

                String uniqId = "";
                String ip = "";
                Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
                if (isAuthenticated.booleanValue()) {
                    LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
                    uniqId = (user == null || user.getUniqId() == null) ? "" : user.getUniqId();

                }


                infoCmsLogVO.setRqesterId(uniqId);
                infoCmsLogVO.setRqesterIp(request.getRemoteAddr());


                infoCmsLogVO.setLogSearchLcatId(object.getSearchlcatid());
                infoCmsLogVO.setLogSearchMcatCd(object.getSearchmcatcd());
                infoCmsLogVO.setLogSearchOrgan(object.getSearchOrgan());
                infoCmsLogVO.setLogSearchCondition(object.getSearchCondition());
                infoCmsLogVO.setLogSearchKeyword(object.getSearchKeyword());
                infoCmsLogVO.setLogSbscrbSttus(object.getSbscrbSttus());
                infoCmsLogVO.setLogSearchUseYn(object.getSearchUseYn());
                infoCmsLogVO.setLogSearchYear(object.getSearchYear());
                infoCmsLogVO.setLogSearchZeusApi(object.getSearchZeusApi());
                infoCmsLogVO.setLogSearcOrder(object.getSearcOrder());
                infoCmsLogVO.setLogMenuTargetNo(object.getMenuTargetNo());
                infoCmsLogVO.setLogSearchAllYn(object.getSearchAllYn());
                infoCmsLogVO.setLogBoardId(object.getBoardId());
                infoCmsLogVO.setLogPageIndex(object.getPageIndex() + "");
                infoCmsLogVO.setLogPageSize(object.getPageSize() + "");
                infoCmsLogVO.setLogPageUnit(object.getPageUnit() + "");
                infoCmsLogVO.setLogModId(object.getModId());
                infoCmsLogVO.setLogRegId(object.getRegId());
                infoCmsLogVO.setStrDay(object.getStrDay());
                infoCmsLogVO.setEndDay(object.getEndDay());
                infoCmsLogVO.setNowDay(object.getNowDay());
                infoCmsLogVO.setLogAllParams(getParams);


                infoCmsLogService.getLogInfoCmsLog(infoCmsLogVO);

                params.put("controller", controllerName);
                params.put("method", methodName);
                params.put("params", getParams);
                params.put("log_time", new Date());
                params.put("request_uri", request.getRequestURI());
                params.put("http_method", request.getMethod());

                System.out.println("params : {} ==>" + params);// param에 담긴 정보들을 한번에 로깅한다.
            }
        } catch (Exception e) {
            LOG.debug("Exception ==>" + e);
        }
    }


    /**
     * request 에 담긴 정보를 JSONObject 형태로 반환한다.
     *
     * @param request
     * @return
     */
    private static JSONObject getParams(HttpServletRequest request) {
        JSONObject jsonObject = new JSONObject();
        Enumeration<String> params = request.getParameterNames();
        while (params.hasMoreElements()) {
            String param = params.nextElement();
            String replaceParam = param.replaceAll("\\.", "-");
            jsonObject.put(replaceParam, request.getParameter(param));
        }
        return jsonObject;
    }

//    public Object jsonToObject(String jsonString) throws JsonParseException, JsonMappingException, IOException {
//        ObjectMapper mapper = new ObjectMapper();
//        return mapper.readValue(jsonString, this.getClass());
//    }
}

