package lk.ijse.glingler.api.controller;

import lk.ijse.glingler.api.service.MatchService;
import lk.ijse.glingler.api.service.impl.ProfileServiceImpl;
import lk.ijse.glingler.dto.MatchRequestBean;
import lk.ijse.glingler.dto.MatchResponseBean;
import lk.ijse.glingler.dto.ProfileResponseBean;
import lk.ijse.glingler.model.CommonUser;
import lk.ijse.glingler.model.Profile;
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

    @PostMapping(value = "/react", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MatchResponseBean> matchReaction(@PathVariable(value = "appType") String appType, @RequestBody MatchRequestBean matchRequestBean) {
        LOGGER.debug("Enter to Process Match React Profiles | AppType : {}", appType);
        MatchResponseBean responseBean = new MatchResponseBean();
        try {
            responseBean = matchService.matchReaction(matchRequestBean);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.debug("Error While Processing Match React");
            responseBean.setResponseCode(ResponseCode.EXCEPTION);
            responseBean.setResponseError("Error in Process");
        }

        LOGGER.debug("Processing Match React Profiles Finished");
        return new ResponseEntity<>(responseBean, HttpStatus.OK);
    }

    @GetMapping(value = "/profiles", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProfileResponseBean> getProfilesForMatch(@PathVariable(value = "appType") String appType) {
        Profile profile = (Profile) httpServletRequest.getAttribute("userProfiles");
        LOGGER.debug("Enter to Process Matching Profiles | AppType : {}", appType);
        ProfileResponseBean responseBean = new ProfileResponseBean();
        try {
            responseBean = matchService.getProfilesForMatch(profile.getProfileId());
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.debug("Error While Processing Matching");
            responseBean.setResponseCode(ResponseCode.EXCEPTION);
            responseBean.setResponseError("Error in Process");
        }

        LOGGER.debug("Processing Matching Profiles Finished");
        return new ResponseEntity<>(responseBean, HttpStatus.OK);
    }


    @GetMapping(value = "/matched", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProfileResponseBean> getMatchedProfiles(@PathVariable String appType) {
        Profile profile = (Profile) httpServletRequest.getAttribute("userProfiles");
        LOGGER.debug("Enter to Process Matching Profiles | AppType : {}", appType);
        ProfileResponseBean responseBean = new ProfileResponseBean();

        try {
            responseBean = matchService.getMatchedProfiles(profile.getProfileId());
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.debug("Error While Getting Matched Profiles");
            responseBean.setResponseCode(ResponseCode.EXCEPTION);
            responseBean.setResponseError("Error in Process");
        }

        LOGGER.debug("Getting Matched Profiles Finished");
        return new ResponseEntity<>(responseBean, HttpStatus.OK);
    }
}
