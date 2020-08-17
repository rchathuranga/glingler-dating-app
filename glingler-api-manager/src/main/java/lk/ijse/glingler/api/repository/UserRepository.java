package lk.ijse.glingler.api.repository;

import lk.ijse.glingler.model.CommonUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<CommonUser, Integer> {
    CommonUser getUserByUsername(String username);
}
