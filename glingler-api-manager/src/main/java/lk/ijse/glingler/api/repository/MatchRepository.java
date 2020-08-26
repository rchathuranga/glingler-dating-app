package lk.ijse.glingler.api.repository;

import lk.ijse.glingler.model.Match;
import lk.ijse.glingler.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatchRepository extends JpaRepository<Match, Integer> {

    public int countMatchesByProfileIdOrMatchProfileIdAndStatus(Profile profile, Profile matchProfile, String status);
    public int countMatchesByProfileIdAndMatchProfileIdAndStatus(Profile profile, Profile matchProfile, String status);

    public Match getMatchByProfileIdAndMatchProfileId(Profile profile, Profile matchProfile);
}
