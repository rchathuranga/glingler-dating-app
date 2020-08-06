package lk.ijse.glingler.service.impl;

import lk.ijse.glingler.dto.ProfileDTO;
import lk.ijse.glingler.dto.ProfileResponseBean;
import lk.ijse.glingler.model.CommonUser;
import lk.ijse.glingler.model.Profile;
import lk.ijse.glingler.repository.ProfileRepository;
import lk.ijse.glingler.service.ProfileService;
import lk.ijse.glingler.util.ResponseCode;
import lk.ijse.glingler.util.StatusCode;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class ProfileServiceImpl implements ProfileService {

    private final Logger LOGGER = LogManager.getLogger(ProfileServiceImpl.class.getName());

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ProfileRepository profileRepository;

    @Override
    public ProfileResponseBean getUserProfileDetails(int userId) throws Exception {
        LOGGER.debug("Enter to Get User Profile Details By UserId - {} in Profile Service", userId);
        ProfileResponseBean responseBean = new ProfileResponseBean();

        CommonUser user = new CommonUser();
        user.setUserId(userId);

        LOGGER.debug("Getting Profile Details By UserId");
        Profile userProfile = profileRepository.getProfileByCommonUserAndStatus(user, StatusCode.STATUS_ACTIVE);

        if(userProfile!=null) {
            LOGGER.debug("Loading Profile Data to DTOs");
            ProfileDTO profileDTO = modelMapper.map(userProfile, ProfileDTO.class);

            LOGGER.debug("Process Getting Profile Details by UserId Success");
            responseBean.setData(Collections.singletonList(profileDTO));
            responseBean.setResponseCode(ResponseCode.SUCCESS);
            responseBean.setResponseError("");
        }else {

            LOGGER.debug("Process Getting Profile Details by UserId Failed Due to Profile not found");
            responseBean.setData(Collections.emptyList());
            responseBean.setResponseCode(ResponseCode.PROFILE_NOT_FOUND);
            responseBean.setResponseError("Profile Not Found to User ID");
        }

        return responseBean;
    }

    @Override
    public ProfileResponseBean getProfileDetails() throws Exception {
        LOGGER.debug("Enter to Get Profile Details in Profile Service");
        ProfileResponseBean responseBean = new ProfileResponseBean();

        LOGGER.debug("Getting Profile Details");
        List<Profile> profileDetails = profileRepository.findAll();

        if (profileDetails!=null) {

            LOGGER.debug("Loading Profile Data to DTOs");
            responseBean.setData(modelMapper.map(profileDetails, new TypeToken<List<ProfileDTO>>() {}.getType()));
            responseBean.setResponseCode(ResponseCode.SUCCESS);
            responseBean.setResponseError("");
        }else {

            LOGGER.debug("Process Getting Profiles Failed");
            responseBean.setResponseCode(ResponseCode.FAILED);
            responseBean.setResponseError("Process Getting Profiles Failed");
        }

        return responseBean;
    }
}
