package lk.ijse.glingler.api.service.impl;

import lk.ijse.glingler.api.repository.FilterRepository;
import lk.ijse.glingler.api.repository.MatchRepository;
import lk.ijse.glingler.api.repository.ProfileRepository;
import lk.ijse.glingler.dto.ProfileDTO;
import lk.ijse.glingler.dto.ProfileRequestBean;
import lk.ijse.glingler.dto.ProfileResponseBean;
import lk.ijse.glingler.model.CommonUser;
import lk.ijse.glingler.model.Filter;
import lk.ijse.glingler.model.Profile;
import lk.ijse.glingler.api.repository.UserRepository;
import lk.ijse.glingler.api.service.ProfileService;
import lk.ijse.glingler.util.ResponseCode;
import lk.ijse.glingler.util.StatusCode;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.Period;
import java.util.Collections;
import java.util.List;

@Service
public class ProfileServiceImpl implements ProfileService {

    private final Logger LOGGER = LogManager.getLogger(ProfileServiceImpl.class.getName());

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FilterRepository filterRepository;

    @Autowired
    private MatchRepository matchRepository;

    @Override
    public ProfileResponseBean getUserProfileDetails(String username) throws Exception {
        LOGGER.debug("Enter to Get User Profile Details By UserId - {} in Profile Service", username);
        ProfileResponseBean responseBean = new ProfileResponseBean();

        CommonUser commonUser = userRepository.getUserByUsername(username);

        LOGGER.debug("Getting Profile Details By UserId");
        Profile userProfile = profileRepository.getProfileByCommonUserAndStatus(commonUser, StatusCode.STATUS_PROFILE_NEW);

        if (userProfile != null) {
            LOGGER.debug("Loading Profile Data to DTOs");
            ProfileDTO profileDTO = modelMapper.map(userProfile, ProfileDTO.class);

            Filter filters = filterRepository.getFilterByProfile(userProfile);
            System.out.println(filters);
            profileDTO.setAge(filters.getAge());
            profileDTO.setLookingFor(filters.getInterestedOn());
            profileDTO.setLocation("Horana, Pokunuwita");// TODO: 8/20/2020 set location city
            profileDTO.setAgeRangeStart(filters.getAgeRangeStart());
            profileDTO.setAgeRangeEnd(filters.getAgeRangeEnd());

            int matchCount = matchRepository.countMatchesByProfileIdOrMatchProfileIdAndStatus(userProfile, userProfile, StatusCode.STATUS_MATCH_LAST);
            profileDTO.setMatchedCount(matchCount);

            LOGGER.debug("Process Getting Profile Details by UserId Success");
            responseBean.setData(Collections.singletonList(profileDTO));
            responseBean.setResponseCode(ResponseCode.SUCCESS);
            responseBean.setResponseError("");
        } else {

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

        if (profileDetails != null) {

            LOGGER.debug("Loading Profile Data to DTOs");
            responseBean.setData(modelMapper.map(profileDetails, new TypeToken<List<ProfileDTO>>() {
            }.getType()));
            responseBean.setResponseCode(ResponseCode.SUCCESS);
            responseBean.setResponseError("");
        } else {

            LOGGER.debug("Process Getting Profiles Failed");
            responseBean.setResponseCode(ResponseCode.FAILED);
            responseBean.setResponseError("Process Getting Profiles Failed");
        }

        return responseBean;
    }

    @Override
    @Transactional
    public ProfileResponseBean createProfile(ProfileRequestBean profileRequestBean) throws Exception {
        LOGGER.debug("Enter to Create User Profile in Profile Service");
        ProfileResponseBean responseBean = new ProfileResponseBean();

        CommonUser commonUser = new CommonUser();
        commonUser.setEmail(profileRequestBean.getEmail());
        commonUser.setPassword(profileRequestBean.getPassword());
        commonUser.setPasswordStatus(StatusCode.STATUS_PASSWORD_ACTIVE);
        commonUser.setUsername(profileRequestBean.getUsername());
        commonUser.setStatus(StatusCode.STATUS_USER_INITIATE);

        LOGGER.debug("Saving User Details | User : {}", commonUser);
        commonUser = userRepository.saveAndFlush(commonUser);
        System.out.println();
        System.out.println("commonUser : " + commonUser);
        System.out.println();
        if (null != commonUser) {

            Profile profile = new Profile();
            profile.setFirstName(profileRequestBean.getFirstName());
            profile.setLastName(profileRequestBean.getLastName());
            profile.setBio("");
            profile.setSex(profileRequestBean.getGender());
            profile.setBirthday(new Timestamp(profileRequestBean.getBirthday().getTime()));
            profile.setImageUrl(profileRequestBean.getImageUrl());

            profile.setCommonUser(commonUser);
            profile.setCreatedTime(new Timestamp(System.currentTimeMillis()));
            profile.setStatus(StatusCode.STATUS_PROFILE_NEW);

            LOGGER.debug("Saving Profile Details | Profile : {}", profile);
            profile = profileRepository.save(profile);

            if (null != profile) {

                LOGGER.debug("Process Creating Profile Success");
                responseBean.setUserId(commonUser.getUserId());
                responseBean.setResponseCode(ResponseCode.SUCCESS);
                responseBean.setResponseError("");

            } else {

                LOGGER.debug("Process Creating Profile Failed");
                responseBean.setResponseCode(ResponseCode.FAILED);
                responseBean.setResponseError("Failed to Save Profile Details");
            }

        } else {
            LOGGER.debug("Process Creating User Profile Failed");
            responseBean.setResponseCode(ResponseCode.FAILED);
            responseBean.setResponseError("Failed to Save User Details");
        }
        return responseBean;
    }

    @Override
    @Transactional
    public ProfileResponseBean updateFilterDetails(String username, Profile userProfil, ProfileRequestBean profileRequestBean) throws Exception {
        LOGGER.debug("Enter to Update Profile Filters in Profile Service");
        ProfileResponseBean responseBean = new ProfileResponseBean();

        CommonUser user = userRepository.getUserByUsername(username);
        Profile userProfile = profileRepository.getProfileByCommonUserAndStatus(user, StatusCode.STATUS_PROFILE_NEW);

        Filter filter = new Filter();
        filter.setProfile(userProfile);

        LocalDate birthDay = new Date(userProfile.getBirthday().getTime()).toLocalDate();
        LocalDate today = LocalDate.now();
        Period diff = Period.between(birthDay, today);

        filter.setAge(diff.getYears());
        //filter.setInterestedOn(SysConfig.USER_LOOKING_FOR_MEN);
        filter.setInterestedOn(profileRequestBean.getLookInFor());
        filter.setLocationLatitude(profileRequestBean.getLatitude());
        filter.setLocationLongitude(profileRequestBean.getLongitude());
        filter.setAgeRangeStart(profileRequestBean.getAgeRangeStart());
        filter.setAgeRangeEnd(profileRequestBean.getAgeRangeEnd());
        filter.setDistance(0.0);

        LOGGER.debug("Saving Profile Filter Details | Filter : {}", filter);
        filter = filterRepository.save(filter);

        if (null != filter) {

            LOGGER.debug("Process Updating Profile Filter Success");
            responseBean.setResponseCode(ResponseCode.SUCCESS);
            responseBean.setResponseError("");
        } else {

            LOGGER.debug("Unable to Save Filter for User Profile");
            responseBean.setResponseCode(ResponseCode.FAILED);
            responseBean.setResponseError("Unable to Update Profile Filter Failed");
        }


        return responseBean;
    }

    @Override
    @Transactional
    public void getMatchingProfiles() throws Exception {

    }
}
