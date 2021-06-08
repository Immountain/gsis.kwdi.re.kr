package infomind.com.cmm.web;

import infomind.com.cmm.api.ApiException;
import infomind.com.cmm.api.ApiResponse;
import infomind.com.cmm.api.ApiStatus;
import infomind.com.cmm.validator.CollectionValidator;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.dao.DataAccessException;
import org.springframework.security.access.AccessDeniedException;

import org.springframework.security.core.AuthenticationException;
import org.springframework.util.ClassUtils;
import org.springframework.validation.FieldError;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

    //@ControllerAdvice 상속 받아서 쓰기...
public class BaseController {



    // @RestController를 사용한 모든 controller 대상
    // @ControllerAdvice(annotations = RestController.class) public class ExampleAdvice1 {}
    // 해당 패키지 내 모든 controller 대상
    // @ControllerAdvice("org.example.controllers")
    // 해당 클래스 하위로 구현된 controller
    // 대상 @ControllerAdvice(assignableTypes = {ControllerInterface.class, AbstractController.class})

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }



}
