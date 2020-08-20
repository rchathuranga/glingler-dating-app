package lk.ijse.glingler.api.repository;

import lk.ijse.glingler.model.Match;
import lk.ijse.glingler.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MatchRepository extends JpaRepository<Match, Integer> {

    public List<Match> getAllByStatus(String status);
}
