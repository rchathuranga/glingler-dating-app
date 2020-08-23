package lk.ijse.glingler.api.repository;

import lk.ijse.glingler.model.CommonUser;
import lk.ijse.glingler.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProfileRepository extends JpaRepository<Profile, Integer> {

    public Profile getProfileByCommonUserAndStatus(CommonUser user, String status);

    public Profile getProfileByProfileId(int profileId);

    public List<Profile> getAllProfilesBySexAndAgeAfterAndAgeBefore(String sex, int ageBefore, int ageAfter);

    @Query("select p from Profile p, CommonUser u WHERE p.commonUser.username=:username")
    public Profile getProfileByUsername(@Param("username") String username);
}
