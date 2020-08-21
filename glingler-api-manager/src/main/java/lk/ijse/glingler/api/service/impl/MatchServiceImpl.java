package lk.ijse.glingler.api.service.impl;

import lk.ijse.glingler.api.repository.FilterRepository;
import lk.ijse.glingler.api.repository.MatchRepository;
import lk.ijse.glingler.api.repository.ProfileRepository;
import lk.ijse.glingler.api.repository.UserRepository;
import lk.ijse.glingler.dto.MatchRequestBean;
import lk.ijse.glingler.dto.MatchResponseBean;
import lk.ijse.glingler.dto.ProfileDTO;
import lk.ijse.glingler.dto.ProfileResponseBean;
import lk.ijse.glingler.model.CommonUser;
import lk.ijse.glingler.model.Filter;
import lk.ijse.glingler.model.Match;
import lk.ijse.glingler.model.Profile;
import lk.ijse.glingler.util.ResponseCode;
import lk.ijse.glingler.util.StatusCode;
import lk.ijse.glingler.util.SysConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
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
    private ModelMapper modelMapper;

    @Override
    public MatchResponseBean match(String username, MatchRequestBean matchRequestBean) {
        LOGGER.debug("Enter to Match Process in Match Service | Username : {}", username);
        MatchResponseBean responseBean = new MatchResponseBean();

        LOGGER.debug("Validating the User Profile - {}", matchRequestBean.getUserProfileId());
        Profile profile = profileRepository.getProfileByProfileId(matchRequestBean.getUserProfileId());

        LOGGER.debug("Validating the matching Profile - {}", matchRequestBean.getMatchProfileId());
        Profile matchedProfile = profileRepository.getProfileByProfileId(matchRequestBean.getMatchProfileId());

        LOGGER.debug("Validating the matching process - {}", matchRequestBean.getMatchProfileId());
        int isBeforeMatched = matchRepository.countMatchesByProfileIdAndMatchProfileIdAndStatus(matchedProfile, profile, StatusCode.STATUS_MATCH_FIRST);
        String status;

        if (isBeforeMatched == 0) {
            status = StatusCode.STATUS_MATCH_FIRST;
        } else if (isBeforeMatched == 1) {
            status = StatusCode.STATUS_MATCH_LAST;
        } else {

            LOGGER.debug("Process Already Complete");
            responseBean.setResponseCode(ResponseCode.FAILED);
            responseBean.setResponseError("Process Already Complete");
            return responseBean;
        }

        Match match = new Match();
        match.setProfileId(profile);
        match.setMatchProfileId(matchedProfile);
        match.setStatus(status);
        match.setCreateTime(new Timestamp(System.currentTimeMillis()));

        LOGGER.debug("Saving Matching Details");
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

        return responseBean;
    }

    @Override
    public ProfileResponseBean getProfilesForMatch(int profileId) throws Exception {
        ProfileResponseBean responseBean = new ProfileResponseBean();

        Profile profile = new Profile();
        profile.setProfileId(profileId);
        Filter filter = filterRepository.getFilterByProfile(profile);

        List<Profile> list = profileRepository.getAllProfilesBySexAndAgeAfterAndAgeBefore(
                filter.getInterestedOn(),
                filter.getAgeRangeStart(),
                filter.getAgeRangeEnd()
        );

        System.out.println();
        System.out.println("list : "+list);
        System.out.println();

        List<ProfileDTO> dataList = modelMapper.map(list, new TypeToken<List<ProfileDTO>>() {}.getType());


        responseBean.setResponseCode(ResponseCode.SUCCESS);
        responseBean.setResponseError("");
        responseBean.setData(dataList);
        return responseBean;
    }
}
