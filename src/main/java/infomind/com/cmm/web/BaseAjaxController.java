package infomind.com.cmm.web;

import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.fdl.property.impl.EgovPropertyServiceImpl;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import infomind.com.cmm.api.ApiException;
import infomind.com.cmm.api.ApiResponse;
import infomind.com.cmm.api.ApiStatus;
import infomind.com.cmm.support.ApplicationContextProvider;
import infomind.com.cms.info.layout.service.InfoLayoutInfoService;
import infomind.com.ext.vo.CmsSearchVO;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.context.ApplicationContext;
import org.springframework.dao.DataAccessException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.sql.SQLException;

public class BaseAjaxController {

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }

    //    @Inject
//    protected LocalValidatorFactoryBean validator;
    protected static final String APPLICATION_JSON = "application/json; charset=UTF-8";
    protected static final String TEXT_PLAIN_UTF_8 = "text/plain; charset=UTF-8";
    private static final Logger logger = LoggerFactory.getLogger(BaseController.class);

    public BaseAjaxController() {
    }

//    @InitBinder
//    public void initBinder(WebDataBinder binder) {
//        binder.addValidators(new Validator[]{new CollectionValidator(this.validator)});
//    }

    public PaginationInfo initPagination(CmsSearchVO searchVO, int totalRecordCount) {
        ApplicationContext applicationContext = ApplicationContextProvider.getApplicationContext();
        EgovPropertyService propertiesService = (EgovPropertyService) applicationContext.getBean("propertiesService");

        /** EgovPropertyService.sample */
        searchVO.setPageUnit(propertiesService.getInt("pageUnit"));
        searchVO.setPageSize(propertiesService.getInt("pageSize"));

        /** pageing */
        PaginationInfo paginationInfo = new PaginationInfo();
        paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
        paginationInfo.setRecordCountPerPage(searchVO.getPageUnit());
        paginationInfo.setPageSize(searchVO.getPageSize());
        paginationInfo.setTotalRecordCount(totalRecordCount);

        searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
        searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
        searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

        return paginationInfo;
    }

    public ApiResponse ok() {
        return ApiResponse.of(ApiStatus.SUCCESS, "SUCCESS");
    }

    public ApiResponse ok(String message) {
        return ApiResponse.of(ApiStatus.SUCCESS, message);
    }

    @ExceptionHandler({AccessDeniedException.class})
    @ResponseBody
    public ApiResponse handleForbidden(Exception e) {

        System.out.println("AccessDeniedException");

        return ApiResponse.error(ApiStatus.FORBIDDEN, e.getMessage());
    }

    //디비 에서 캐취
    @ExceptionHandler({DataAccessException.class})
    @ResponseBody
    public ApiResponse handleDataAccessException(Exception e) {
        System.out.println("DataAccessException");
        return ApiResponse.error(ApiStatus.SYSTEM_ERROR, e.getMessage());
    }

//    @ExceptionHandler({AuthenticationException.class})
//    @ResponseBody
//    public ApiResponse handleAuthenticationExceptionException(Exception e) {
//        System.out.println("handleAuthenticationExceptionException");
//        return ApiResponse.error(ApiStatus.FORBIDDEN, e.getMessage());
//    }
//


    @ExceptionHandler({TypeMismatchException.class})
    @ResponseBody
    public ApiResponse handleBadRequestException(Exception e) {
        System.out.println("TypeMismatchException");

        this.errorLogging(e);
        return ApiResponse.error(ApiStatus.BAD_REQUEST, "BAD_REQUEST");
    }

    @ExceptionHandler({MissingServletRequestParameterException.class})
    @ResponseBody
    public ApiResponse handleRequestParameterException(MissingServletRequestParameterException e) {

        System.out.println("MissingServletRequestParameterException");
        this.errorLogging(e);
        return ApiResponse.error(ApiStatus.BAD_REQUEST, e.getMessage());
    }

    @ExceptionHandler({ApiException.class})
    @ResponseBody
    public ApiResponse handleApiException(ApiException apiException) {
        System.out.println("ApiException");
        return ApiResponse.error(ApiStatus.getApiStatus(apiException.getCode()), apiException.getMessage());
    }


    @ExceptionHandler({Throwable.class})
    @ResponseBody
    public ApiResponse handleException(Throwable throwable) {
        this.errorLogging(throwable);
        ApiResponse apiResponse = ApiResponse.error(ApiStatus.SYSTEM_ERROR, throwable.getMessage());
        Throwable rootCause = ExceptionUtils.getRootCause(throwable);
        if (rootCause != null && rootCause instanceof SQLException) {
            String message = String.format("데이터 처리중 에러가 발생하였습니다.\n시스템 관리자에게 문의하세요.\n\n에러내용 : %s", rootCause.getLocalizedMessage());
            apiResponse = ApiResponse.error(ApiStatus.SYSTEM_ERROR, message);
        }

        return apiResponse;
    }

    protected void errorLogging(Throwable throwable) {
        if (logger.isErrorEnabled()) {
            Throwable rootCause = ExceptionUtils.getRootCause(throwable);
            if (rootCause != null) {
                throwable = rootCause;
            }

            if (throwable.getMessage() != null) {
                logger.error(throwable.getMessage(), throwable);
            } else {
                logger.error("API_ERROR", throwable);
            }
        }

    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    @ResponseBody
    public Object processValidationError(MethodArgumentNotValidException ex) {
        FieldError fieldError = ex.getBindingResult().getFieldErrors().get(0);
        ApiResponse error = ApiResponse.error(ApiStatus.SYSTEM_ERROR, fieldError.getDefaultMessage());
        error.getError().setRequiredKey(fieldError.getField());
        return error;
    }

}
