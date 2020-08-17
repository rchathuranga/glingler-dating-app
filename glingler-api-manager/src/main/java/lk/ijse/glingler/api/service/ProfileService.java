package lk.ijse.glingler.api.service;

import lk.ijse.glingler.dto.ProfileRequestBean;
import lk.ijse.glingler.dto.ProfileResponseBean;

public interface ProfileService {
    public ProfileResponseBean getUserProfileDetails(int userId) throws Exception;
    public ProfileResponseBean getProfileDetails() throws Exception;
    public ProfileResponseBean createProfile(ProfileRequestBean profileRequestBean) throws Exception;

}
