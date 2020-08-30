package lk.ijse.glingler.api.repository;

import lk.ijse.glingler.model.Match;
import lk.ijse.glingler.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MatchRepository extends JpaRepository<Match, Integer> {

    public int countMatchesByMatchProfileIdAndStatus(Profile matchProfile, String status);
    public int countMatchesByProfileIdAndMatchProfileIdAndStatus(Profile profile, Profile matchProfile, String status);

    public Match getMatchByProfileIdAndMatchProfileId(Profile profile, Profile matchProfile);

//    @Query("SELECT p FROM Profile p, Match m where p.profileId=m.profileId.profileId and (m.profileId=:profile or m.matchProfileId=:matchProfileId) and m.status in (:status)")
//    public List<Profile> getAllMatchesByProfileIdOrMatchProfileIdAndStatus(@Param("profile") Profile profile,@Param("matchProfileId") Profile matchProfile,@Param("status") List<String> status);

    @Query("select p from Match m, Profile p  where p.profileId=m.profileId.profileId and not p.profileId=:profileId")
    public List<Profile> getProfilesForMatch(@Param("profileId") int profile);

    @Query("select p FROM Profile p WHERE p.profileId NOT IN (SELECT m.profileId FROM Match m)")
    public List<Profile> getProfilesForMatchNotLinked();
}
