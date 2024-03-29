package lk.ijse.glingler.api.controller;

import lk.ijse.glingler.dto.SignInRequestBean;
import lk.ijse.glingler.dto.SignInResponseBean;
import lk.ijse.glingler.dto.UserResponseBean;
import lk.ijse.glingler.security.JwtUtil;
import lk.ijse.glingler.api.service.UserService;
import lk.ijse.glingler.util.ResponseCode;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@CrossOrigin()
public class AuthenticationController {

    private static final Logger LOGGER = LogManager.getLogger(AuthenticationController.class.getName());

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/sign-in")
    public ResponseEntity<SignInResponseBean> signIn(@RequestBody SignInRequestBean requestBean) {
        LOGGER.debug("Enter to User Authentication Profiles");
        SignInResponseBean responseBean = new SignInResponseBean();

        try {
            authenticate(requestBean.getUsername(), requestBean.getPassword());
            responseBean.setToken(jwtUtil.createToken(requestBean.getUsername(),"user",null));
            UserResponseBean userData = userService.getUserDetailByUserName(requestBean.getUsername());

            LOGGER.debug("User Authentication Success");
            responseBean.setUserId(userData.getUser().getUserId());
            responseBean.setRouter("application");

        } catch (BadCredentialsException bce) {

            responseBean.setResponseCode(ResponseCode.FAILED);
            responseBean.setResponseError("Invalid Credentials");
            LOGGER.debug("Invalid Credentials - {}", bce.getMessage());
            return new ResponseEntity<>(responseBean, HttpStatus.UNAUTHORIZED);

        } catch (Exception e) {
e.printStackTrace();
            responseBean.setResponseCode(ResponseCode.EXCEPTION);
            responseBean.setResponseError("Login Failed");
            LOGGER.debug("Exception in Login Process -> {}", e.getMessage());
            return new ResponseEntity<>(responseBean, HttpStatus.UNAUTHORIZED);

        }

        responseBean.setResponseCode(ResponseCode.SUCCESS);
        responseBean.setResponseError("");
        return new ResponseEntity<>(responseBean, HttpStatus.OK);
    }

    private void authenticate(String username, String password) throws DisabledException, BadCredentialsException {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
    }

}
