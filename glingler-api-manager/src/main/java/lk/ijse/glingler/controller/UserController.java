package lk.ijse.glingler.controller;

import lk.ijse.glingler.dto.UserResponseBean;
import lk.ijse.glingler.service.UserService;
import lk.ijse.glingler.util.ResponseCode;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/{appType}")
public class UserController {

    private static final Logger LOGGER = LogManager.getLogger(UserController.class.getName());

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public ResponseEntity<UserResponseBean> getUserDetails(@PathVariable("appType") String appType) {
        LOGGER.debug("Enter to ");
        UserResponseBean responseBean = new UserResponseBean();
        try {
            responseBean = userService.getUserDetails();
            responseBean.setResponseCode(ResponseCode.SUCCESS);
            responseBean.setResponseError("");
        } catch (Exception e) {
            responseBean.setResponseCode(ResponseCode.EXCEPTION);
            responseBean.setResponseError("Error While Processing");
            LOGGER.debug("Error While Processing");
        }
        return new ResponseEntity<>(responseBean, HttpStatus.OK);
    }

}
