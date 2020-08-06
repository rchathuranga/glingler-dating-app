package lk.ijse.glingler.service;

import lk.ijse.glingler.dto.ProfileResponseBean;

public interface ProfileService {
    public ProfileResponseBean getUserProfileDetails(int userId) throws Exception;
    public ProfileResponseBean getProfileDetails() throws Exception;
}
