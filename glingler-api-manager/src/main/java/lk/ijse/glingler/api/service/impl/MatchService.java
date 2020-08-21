package lk.ijse.glingler.api.service.impl;

import lk.ijse.glingler.dto.MatchRequestBean;
import lk.ijse.glingler.dto.MatchResponseBean;
import lk.ijse.glingler.dto.ProfileResponseBean;

public interface MatchService {
    public MatchResponseBean match(String username, MatchRequestBean matchRequestBean) throws Exception;
    public ProfileResponseBean getProfilesForMatch(int profileId) throws Exception;
}
