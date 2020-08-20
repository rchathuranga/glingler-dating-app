package lk.ijse.glingler.api.service;

import lk.ijse.glingler.dto.ProfileRequestBean;
import lk.ijse.glingler.dto.ProfileResponseBean;

public interface ProfileService {
    public ProfileResponseBean getUserProfileDetails(String username) throws Exception;
    public ProfileResponseBean getProfileDetails() throws Exception;
    public ProfileResponseBean createProfile(ProfileRequestBean profileRequestBean) throws Exception;
    public ProfileResponseBean updateFilterDetails(String username, ProfileRequestBean profileRequestBean) throws Exception;

}
