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

    @Query("select p from Match m, Profile p where p.profileId = m.profileId.profileId and not p.profileId =:profileId and p.profileId not in (select m.profileId from Matched m where m.profileId = :profileId or m.matchProfileId = :profileId) and p.profileId not in (select m.matchProfileId from Matched m where m.profileId =:profileId or m.matchProfileId=:profileId) and p.sex=:sex and p.age>:beforeAge and p.age<:afterAge")
    public List<Profile> getProfilesForMatch(@Param("profileId") int profile, @Param("sex") String sex, @Param("beforeAge") int beforeAge, @Param("afterAge") int afterAge);

    @Query("select p FROM Profile p WHERE p.profileId NOT IN (SELECT m.profileId FROM Match m) and p.profileId NOT IN (SELECT m.matchProfileId FROM Match m) and p.sex=:sex and p.age>:beforeAge and p.age<:afterAge")
    public List<Profile> getProfilesForMatchNotLinked(@Param("sex") String sex, @Param("beforeAge") int beforeAge, @Param("afterAge") int afterAge);
}
