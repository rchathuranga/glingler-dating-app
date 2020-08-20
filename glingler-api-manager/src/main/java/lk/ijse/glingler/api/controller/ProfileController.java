package lk.ijse.glingler.api.controller;

import lk.ijse.glingler.dto.ProfileRequestBean;
import lk.ijse.glingler.dto.ProfileResponseBean;
import lk.ijse.glingler.api.service.ProfileService;
import lk.ijse.glingler.security.JwtUtil;
import lk.ijse.glingler.util.ResponseCode;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;

@RestController
@RequestMapping("/api/v1/{appType}/profile")
@CrossOrigin
public class ProfileController {

    private final Logger LOGGER = LogManager.getLogger(ProfileController.class.getName());

    @Autowired
    private ProfileService profileService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private HttpServletRequest httpServletRequest;

    /*@GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProfileResponseBean> getProfileDetails(@PathVariable("appType") String appType) {
        LOGGER.debug("Enter to Get Profile Details Process : {}", appType);
        ProfileResponseBean responseBean = new ProfileResponseBean();

        try {
            responseBean = profileService.getProfileDetails();
        } catch (Exception e) {

            e.printStackTrace();
            LOGGER.debug("Process Getting User Profile Details Failed - {}", e.getMessage());
            responseBean.setData(Collections.emptyList());
            responseBean.setResponseCode(ResponseCode.EXCEPTION);
            responseBean.setResponseError("Process Getting User Profile Details Failed");
        }

        LOGGER.debug("Process Getting Profile Details Finished");
        return new ResponseEntity<>(responseBean, HttpStatus.OK);
    }*/

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProfileResponseBean> getProfileDetailsByUserId(@PathVariable("appType") String appType) {
        String username = httpServletRequest.getAttribute("username").toString();
        LOGGER.debug("Enter to Get User Profile Details Process by - {} : {}", username, appType);
        ProfileResponseBean responseBean = new ProfileResponseBean();

        try {
            responseBean = profileService.getUserProfileDetails(username);
        } catch (Exception e) {

            e.printStackTrace();
            LOGGER.debug("Process Getting Profile Details Failed - {}", e.getMessage());
            responseBean.setData(Collections.emptyList());
            responseBean.setResponseCode(ResponseCode.EXCEPTION);
            responseBean.setResponseError("Process Getting Profile Details Failed");
        }

        LOGGER.debug("Process Getting User Profile Details Finished");
        return new ResponseEntity<>(responseBean, HttpStatus.OK);
    }


    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProfileResponseBean> createProfile(@PathVariable("appType") String appType, @RequestBody ProfileRequestBean profileRequestBean) {
        LOGGER.debug("Enter to Create User Profile Process : {}", appType);
        ProfileResponseBean responseBean = new ProfileResponseBean();

        try {
            responseBean = profileService.createProfile(profileRequestBean);
            responseBean.setToken(jwtUtil.createToken(profileRequestBean.getUsername(),appType,null));
            responseBean.setRouter("application");
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.debug("Creating User Profile Failed - {}", e.getMessage());
            responseBean.setResponseCode(ResponseCode.EXCEPTION);
            responseBean.setResponseError("Exception throws while processing create Profile");
        }

        LOGGER.debug("Process Create User Profile Finished");
        return new ResponseEntity<>(responseBean, HttpStatus.OK);
    }

    @PutMapping(value = "update-filters", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProfileResponseBean> updateFilterDetails(@PathVariable("appType") String appType, @RequestBody ProfileRequestBean profileRequestBean){
        String username = httpServletRequest.getAttribute("username").toString();
        LOGGER.debug("Enter to Update Profile Filters Process : {}", appType);
        ProfileResponseBean responseBean = new ProfileResponseBean();
        try {
            responseBean = profileService.updateFilterDetails(username, profileRequestBean);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.debug("Updating Profile Filters Failed - {}", e.getMessage());
            responseBean.setResponseCode(ResponseCode.EXCEPTION);
            responseBean.setResponseError("Exception throws while processing Update Profile Filters");
        }
        return new ResponseEntity<>(responseBean, HttpStatus.OK);
    }
}
