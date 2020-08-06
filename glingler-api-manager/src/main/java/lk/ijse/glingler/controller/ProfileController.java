package lk.ijse.glingler.controller;

import lk.ijse.glingler.dto.ProfileResponseBean;
import lk.ijse.glingler.service.ProfileService;
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

import java.util.Collections;

@RestController
@RequestMapping("/api/v1/{appType}/profile")
public class ProfileController {

    private final Logger LOGGER = LogManager.getLogger(ProfileController.class.getName());

    @Autowired
    private ProfileService profileService;

    @GetMapping
    public ResponseEntity<ProfileResponseBean> getProfileDetails(@PathVariable("appType") String appType){
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
    }

    @GetMapping(value = "/{userId}")
    public ResponseEntity<ProfileResponseBean> getUserProfile(@PathVariable("appType") String appType, @PathVariable("userId") String userId){
        LOGGER.debug("Enter to Get User Profile Details Process by - {} : {}", userId, appType);
        ProfileResponseBean responseBean = new ProfileResponseBean();

        try {
            responseBean = profileService.getUserProfileDetails(Integer.parseInt(userId));
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

}
