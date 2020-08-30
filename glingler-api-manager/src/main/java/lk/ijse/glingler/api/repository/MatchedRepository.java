package lk.ijse.glingler.api.repository;

import lk.ijse.glingler.model.Matched;
import lk.ijse.glingler.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MatchedRepository extends JpaRepository<Matched, Integer> {

    public List<Matched> getAllMatchedByProfileIdOrMatchProfileIdAndStatus(Profile profileId, Profile matchedProfileId, String status);

}
