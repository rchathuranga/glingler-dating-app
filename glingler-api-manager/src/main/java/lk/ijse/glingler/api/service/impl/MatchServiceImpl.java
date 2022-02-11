package lk.ijse.glingler.api.service.impl;

import lk.ijse.glingler.api.repository.*;
import lk.ijse.glingler.api.service.MatchService;
import lk.ijse.glingler.dto.MatchRequestBean;
import lk.ijse.glingler.dto.MatchResponseBean;
import lk.ijse.glingler.dto.ProfileDTO;
import lk.ijse.glingler.dto.ProfileResponseBean;
import lk.ijse.glingler.model.*;
import lk.ijse.glingler.util.ResponseCode;
import lk.ijse.glingler.util.StatusCode;
import lk.ijse.glingler.util.SysConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
public class MatchServiceImpl implements MatchService {

    private final Logger LOGGER = LogManager.getLogger(ProfileServiceImpl.class.getName());

    @Autowired
    private MatchRepository matchRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private FilterRepository filterRepository;

    @Autowired
    private MatchedRepository matchedRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    @Transactional
    public MatchResponseBean matchReaction(MatchRequestBean matchRequestBean) throws Exception {
        LOGGER.debug("Enter to Match Reaction Process in Match Service");
        MatchResponseBean responseBean = new MatchResponseBean();

        Profile profile = profileRepository.getProfileByProfileId(matchRequestBean.getUserProfileId());
        Profile matchedProfile = profileRepository.getProfileByProfileId(matchRequestBean.getMatchProfileId());

        LOGGER.debug("Validating the User Profile - {} And Matching Profile - {}", matchRequestBean.getUserProfileId(), matchRequestBean.getMatchProfileId());
        if (profile != null && matchedProfile != null) {

            LOGGER.debug("Getting Previous Match Details Related to Profile");
            Match previousMatch = matchRepository.getMatchByProfileIdAndMatchProfileId(matchedProfile, profile);

            int actionType = matchRequestBean.getActionType();
            Match match = new Match();
            match.setProfileId(profile);
            match.setMatchProfileId(matchedProfile);

            LOGGER.debug("Processing Match React Profiles to Action - {}", matchRequestBean.getActionType());
            if (actionType == SysConfig.MATCH_REACT_TYPE_SUPER_LIKE) {

                if (previousMatch != null && StatusCode.MATCH_REACT_TYPE_REJECT.equalsIgnoreCase(previousMatch.getStatus())) {

                    responseBean.setResponseCode(ResponseCode.MATCH_FAIL);
                    responseBean.setResponseError("User Profile Already Rejected");
                    return responseBean;

                } else {
                    match.setStatus(StatusCode.MATCH_REACT_TYPE_SUPER_LIKE);//todo send push
                }

            } else if (actionType == SysConfig.MATCH_REACT_TYPE_LIKE) {

                if (previousMatch == null) {
                    match.setStatus(StatusCode.MATCH_REACT_TYPE_LIKE);

                } else if (previousMatch.getStatus().equalsIgnoreCase(StatusCode.MATCH_REACT_TYPE_LIKE)) {
                    match.setStatus(StatusCode.MATCH_REACT_TYPE_LIKE);

                    Matched matched = new Matched();
                    matched.setProfileId(profile);
                    matched.setMatchProfileId(matchedProfile);
                    matched.setStatus(StatusCode.MATCH_REACT_TYPE_MATCHED);

                    matched = matchedRepository.save(matched);
                    if (matched != null) {
                        System.out.println("success");
                    } else {
                        System.out.println("failed");
                    }

                } else {
                    responseBean.setResponseCode(ResponseCode.MATCH_FAIL);
                    responseBean.setResponseError("User Profile Already Matched Or Rejected");
                    return responseBean;

                }

            } else if (actionType == SysConfig.MATCH_REACT_TYPE_REJECTED) {
                match.setStatus(StatusCode.MATCH_REACT_TYPE_REJECT);
            }


            System.out.println("Status : " + match.getStatus());

            match.setCreateTime(new Timestamp(System.currentTimeMillis()));

            LOGGER.debug("Saving Matching Details - {}", match);
            match = matchRepository.save(match);

            if (match != null) {

                LOGGER.debug("Processing Matching Success");
                responseBean.setResponseCode(ResponseCode.SUCCESS);
                responseBean.setResponseError("");
            } else {

                LOGGER.debug("Processing Matching Failed");
                responseBean.setResponseCode(ResponseCode.FAILED);
                responseBean.setResponseError("Process Matching Failed");
            }

        }
        return responseBean;
    }

    @Override
    public ProfileResponseBean getProfilesForMatch(int profileId) throws Exception {
        ProfileResponseBean responseBean = new ProfileResponseBean();
        List<ProfileDTO> dataList = new ArrayList<>();

        Profile profile = new Profile();
        profile.setProfileId(profileId);
        Filter filter = filterRepository.getFilterByProfile(profile);

        if (filter != null) {

            List<String> statusList = new ArrayList<>();
            statusList.add(StatusCode.MATCH_REACT_TYPE_SUPER_LIKE);
            statusList.add(StatusCode.MATCH_REACT_TYPE_MATCHED);


            List<Profile> list = matchRepository.getProfilesForMatch(profile.getProfileId(), filter.getInterestedOn(), filter.getAgeRangeStart(), filter.getAgeRangeEnd());
            List<Profile> list2 = matchRepository.getProfilesForMatchNotLinked(filter.getInterestedOn(), filter.getAgeRangeStart(), filter.getAgeRangeEnd());


            list.addAll(list2);

            dataList = modelMapper.map(list, new TypeToken<List<ProfileDTO>>() {
            }.getType());

            responseBean.setResponseCode(ResponseCode.SUCCESS);
            responseBean.setResponseError("");
        }else {
            responseBean.setResponseCode(ResponseCode.USER_NOT_FOUND);
            responseBean.setResponseError("No Filter Details Found to the Profile");
        }
        responseBean.setData(dataList);
        return responseBean;
    }

    @Override
    public ProfileResponseBean getMatchedProfiles(int profileId) throws Exception {
        LOGGER.debug("Enter to Get Matched Profile Process in Match Service | Profile Id - {}", profileId);
        ProfileResponseBean responseBean = new ProfileResponseBean();
        Profile profile = new Profile();
        profile.setProfileId(profileId);

        List<Profile> list = new ArrayList<>();
        List<ProfileDTO> profileDTOList = new ArrayList<>();

        LOGGER.debug("Removing the requesting profile from the Matched List");
        List<Matched> matchedList = matchedRepository.getAllMatchedByProfileIdOrMatchProfileIdAndStatus(profile, profile, StatusCode.MATCH_REACT_TYPE_MATCHED);
        System.out.println();
        System.out.println(matchedList.size());
        System.out.println();
        for (Matched matched : matchedList) {
            ProfileDTO profileDTO = loadProfileToDTO(matched.getProfileId());
            profileDTO.setMatchedIdWithUser(matched.getMatchedId());
            ProfileDTO matchedDTO = loadProfileToDTO(matched.getMatchProfileId());
            matchedDTO.setMatchedIdWithUser(matched.getMatchedId());

            profileDTOList.add(profileDTO);
            profileDTOList.add(matchedDTO);
        }

        LOGGER.debug("Removing the requesting profile from the Matched List");
        List<ProfileDTO> removedList = new ArrayList<>();
        for (ProfileDTO prof : profileDTOList) {
            if (prof.getProfileId() == profileId) {
                removedList.add(prof);
            }
        }
        profileDTOList.removeAll(removedList);

        LOGGER.debug("Getting Matched Profile Process Success");
        responseBean.setData(profileDTOList);
        responseBean.setResponseCode(ResponseCode.SUCCESS);
        responseBean.setResponseError("");
        return responseBean;
    }

    private ProfileDTO loadProfileToDTO(Profile profile){
        ProfileDTO profileDTO = new ProfileDTO();
        profileDTO.setProfileId(profile.getProfileId());
        profileDTO.setFirstName(profile.getFirstName());
        profileDTO.setLastName(profile.getLastName());
        profileDTO.setBio(profile.getBio());
        profileDTO.setSex(profile.getSex());
        profileDTO.setAge(profile.getAge());
        profileDTO.setBirthDay(profile.getBirthday().toString());
        profileDTO.setImageUrl(profile.getImageUrl());
        return profileDTO;
    }
}
