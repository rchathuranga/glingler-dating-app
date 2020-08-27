package lk.ijse.glingler.api.service;

import lk.ijse.glingler.dto.ProfileRequestBean;
import lk.ijse.glingler.dto.ProfileResponseBean;
import lk.ijse.glingler.model.Profile;

public interface ProfileService {
    public ProfileResponseBean createProfile(ProfileRequestBean profileRequestBean) throws Exception;
    public ProfileResponseBean updateFilterDetails(String username, Profile userProfile, ProfileRequestBean profileRequestBean) throws Exception;
    public ProfileResponseBean getUserProfileDetails(String username) throws Exception;
    public ProfileResponseBean getProfileDetails() throws Exception;
}
