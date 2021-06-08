package infomind.com.cmm.exception;


import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ExceptionResolver extends SimpleMappingExceptionResolver {

    private String exceptionAttribute ="exception";


    @Override
    protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex){

            String viewName=determineViewName(ex,request);
            String getHeader ="";

      //  logger.debug("Ajax 호출 확인"+request.getHeader("AJAX"));

        System.out.println("Ajax 호출 확인"+request.getHeader("AJAX"));
        System.out.println("Ajax 호출 확인==>"+viewName);



            if(viewName!=null){

                System.out.println("Ajax  1");

                if(logger.isDebugEnabled()){
                    logger.debug("Ajax 호출 확인"+request.getHeader("AJAX"));

                }
                if(request.getHeader("AJAX")!=null&&request.getHeader("AJAX").equals("true")){

                    getHeader="AJAX";

                    Integer statusCode =determineStatusCode(request,viewName);
                    if(statusCode!=null){

                        applyStatusCodeIfPossible(request,response,statusCode);
                    }

                }
//
//
//                if("AJAX".equals(getHeader)){
//
//                    return  getModelAndView(viewName,ex,request);
//                }else{
//
//                    return  getModelAndView(viewName,ex,request);
//
//                    // return super.doResolveException(request, response, handler, ex);
//                }

                return  getModelAndView(viewName,ex,request);
            }else{

                return null;
            }
        }

     @Override
    protected ModelAndView getModelAndView(String viewName ,Exception ex,HttpServletRequest request){


        ModelAndView mv = new ModelAndView(viewName);
        if(this.exceptionAttribute!=null){
            if(logger.isDebugEnabled()){
                logger.debug("Exposing Exception as mdol atteibute'" +this.exceptionAttribute+"'");

            }
            mv.addObject(this.exceptionAttribute,ex);
            mv.addObject("url",request.getRequestURL());



        }
        return mv;
     }


}
