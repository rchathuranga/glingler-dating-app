package lk.ijse.glingler.api.controller;

import lk.ijse.glingler.dto.UserResponseBean;
import lk.ijse.glingler.api.service.UserService;
import lk.ijse.glingler.util.ResponseCode;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/v1/{appType}")
@CrossOrigin
public class UserController {

    private static final Logger LOGGER = LogManager.getLogger(UserController.class.getName());

    @Autowired
    private HttpServletRequest httpServletRequest;

    @Autowired
    private UserService userService;


    @GetMapping(value = "/user")
    public ResponseEntity<UserResponseBean> getUserDetailsByUsername(@PathVariable("appType") String appType) {
        String username = httpServletRequest.getAttribute("username").toString();
        LOGGER.debug("Enter to Get User Details By Username - {} | Process by - {}", username, appType);
        UserResponseBean responseBean = new UserResponseBean();

        try {
            responseBean = userService.getUserDetailByUserName(username);
        } catch (Exception e) {
            LOGGER.debug("Process Getting User Details By Username Failed - {}", e.getMessage());
            responseBean.setResponseCode(ResponseCode.EXCEPTION);
            responseBean.setResponseError("Process Getting User Details By Username Failed");
        }

        LOGGER.debug("Process Getting User Details By Username Finished");
        return new ResponseEntity<>(responseBean, HttpStatus.OK);
    }

}
