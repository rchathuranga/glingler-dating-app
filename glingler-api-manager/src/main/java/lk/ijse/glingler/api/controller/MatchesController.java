package lk.ijse.glingler.api.controller;

import lk.ijse.glingler.api.service.impl.MatchService;
import lk.ijse.glingler.api.service.impl.ProfileServiceImpl;
import lk.ijse.glingler.dto.MatchRequestBean;
import lk.ijse.glingler.dto.MatchResponseBean;
import lk.ijse.glingler.dto.ProfileResponseBean;
import lk.ijse.glingler.util.ResponseCode;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/v1/{appType}/matches")
@CrossOrigin
public class MatchesController {

    private final Logger LOGGER = LogManager.getLogger(ProfileServiceImpl.class.getName());

    @Autowired
    private HttpServletRequest httpServletRequest;

    @Autowired
    private MatchService matchService;

    @PostMapping(value = "/profiles", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MatchResponseBean> matchProfiles(@PathVariable(value = "appType") String appType, @RequestBody MatchRequestBean matchRequestBean) {
        String username = httpServletRequest.getAttribute("username").toString();
        LOGGER.debug("Enter to Process Matching Profiles | Username : {} | AppType : {}", username, appType);
        MatchResponseBean responseBean = new MatchResponseBean();
        try {
            responseBean = matchService.match(username, matchRequestBean);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.debug("Error While Processing Matching");
            responseBean.setResponseCode(ResponseCode.EXCEPTION);
            responseBean.setResponseError("Error in Process");
        }

        LOGGER.debug("Processing Matching Profiles Finished");
        return new ResponseEntity<>(responseBean, HttpStatus.OK);
    }

    @GetMapping(value = "/profiles/{profileId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProfileResponseBean> getProfilesForMatch(@PathVariable(value = "appType") String appType,@PathVariable("profileId") int profileId) {

//        String username = httpServletRequest.getAttribute("username").toString();
        LOGGER.debug("Enter to Process Matching Profiles | AppType : {}", appType);
        ProfileResponseBean responseBean = new ProfileResponseBean();
        try {
            responseBean = matchService.getProfilesForMatch(profileId);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.debug("Error While Processing Matching");
            responseBean.setResponseCode(ResponseCode.EXCEPTION);
            responseBean.setResponseError("Error in Process");
        }

        LOGGER.debug("Processing Matching Profiles Finished");
        return new ResponseEntity<>(responseBean, HttpStatus.OK);
    }

}
