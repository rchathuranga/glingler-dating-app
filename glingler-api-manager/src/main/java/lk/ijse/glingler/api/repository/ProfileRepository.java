package lk.ijse.glingler.api.repository;

import lk.ijse.glingler.model.CommonUser;
import lk.ijse.glingler.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProfileRepository extends JpaRepository<Profile,Integer> {

    public Profile getProfileByCommonUserAndStatus(CommonUser user,String status);
    public Profile getProfileByProfileId(int profileId);

    public List<Profile> getAllProfilesBySexAndAgeAfterAndAgeBefore(String sex,int ageBefore, int ageAfter);

}
