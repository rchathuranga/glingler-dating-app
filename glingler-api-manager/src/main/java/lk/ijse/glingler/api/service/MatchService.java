package lk.ijse.glingler.api.service;

import lk.ijse.glingler.dto.MatchRequestBean;
import lk.ijse.glingler.dto.MatchResponseBean;
import lk.ijse.glingler.dto.ProfileResponseBean;

public interface MatchService {
    public ProfileResponseBean getProfilesForMatch(int profileId) throws Exception;
    public MatchResponseBean matchReaction(MatchRequestBean matchRequestBean) throws Exception;
    public ProfileResponseBean getMatchedProfiles(int profileId) throws Exception;
}
