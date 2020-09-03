package lk.ijse.glingler.adapter;

import lk.ijse.glingler.dto.ExceptionResponseBean;
import lk.ijse.glingler.security.JwtUtil;
import lk.ijse.glingler.api.service.UserService;
import lk.ijse.glingler.util.ResponseCode;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ResponseBodyAdviceAdapter implements ResponseBodyAdvice<Object> {

    private final Logger LOGGER = LogManager.getLogger(ResponseBodyAdviceAdapter.class.getName());

    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        return o;
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<ExceptionResponseBean> handleMyException(Exception ex) {
        LOGGER.debug("Enter to Exception Handler in Response Body Advice Adapter - {}",ex.getMessage());
        ExceptionResponseBean responseBean = new ExceptionResponseBean();
        responseBean.setExceptionMessage(ex.getMessage());
        responseBean.setResponseCode(ResponseCode.INTERNAL_SERVER_ERROR);
        responseBean.setResponseError("Exception Generated While Processing");
        LOGGER.debug("Process Handling Exception Success");
        return new ResponseEntity<>(responseBean, HttpStatus.OK);
    }
}
