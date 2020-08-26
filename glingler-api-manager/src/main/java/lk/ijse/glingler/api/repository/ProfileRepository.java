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

    @Query("SELECT p from Profile p WHERE p.sex=:sex AND p.age>:ageBefore AND p.age<:ageAfter AND NOT p.profileId =:profileId AND p NOT IN (SELECT m.profileId FROM Match  m)")
    public List<Profile> getAllProfilesBySexAndAgeAfterAndAgeBeforeAndNotInMatch(@Param("sex") String sex,@Param("ageBefore") int ageBefore,@Param("ageAfter") int ageAfter,@Param("profileId") int profileId);

    @Query("select p from Profile p, Match m where p.sex=:sex and p.age>:ageBefore and p.age<:ageAfter  and p.profileId=m.profileId.profileId AND NOT p.profileId =:profileId  and not m.status in (:statusList)")
    public List<Profile> getProfilesForMatch(@Param("sex") String sex,@Param("ageBefore") int ageBefore,@Param("ageAfter") int ageAfter, @Param("profileId") int profileId,@Param("statusList") List<String> statusList);

    @Query("select p from Profile p, CommonUser u WHERE p.commonUser.username=:username")
    public Profile getProfileByUsername(@Param("username") String username);
}
