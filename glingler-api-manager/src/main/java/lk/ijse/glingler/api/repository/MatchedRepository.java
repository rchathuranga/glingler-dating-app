package lk.ijse.glingler.api.repository;

import lk.ijse.glingler.model.Matched;
import lk.ijse.glingler.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface MatchedRepository extends JpaRepository<Matched, Integer> {

    public List<Matched> getAllMatchedByProfileIdOrMatchProfileIdAndStatus(Profile profileId, Profile matchedProfileId, String status);

    @Query("select m from Matched m where (m.profileId=:profileId or m.matchProfileId=:profileId) and (m.profileId=:matchedProfileId or m.matchProfileId=:matchedProfileId) and m.status=:status")
    public Matched getAllMatchedByProfileIdOrMatchProfileIdAndProfileIdOrMatchProfileIdAndStatus(@Param("profileId") Profile profileId, @Param("matchedProfileId") Profile matchedProfileId,@Param("status") String status);

}
