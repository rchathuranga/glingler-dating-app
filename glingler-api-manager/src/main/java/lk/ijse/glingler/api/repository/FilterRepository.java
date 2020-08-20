package lk.ijse.glingler.api.repository;

import lk.ijse.glingler.model.Filter;
import lk.ijse.glingler.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilterRepository extends JpaRepository<Filter,Integer> {
    public Filter getFilterByProfile(Profile profile);
}
