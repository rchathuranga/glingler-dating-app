package lk.ijse.glingler.repository;

import lk.ijse.glingler.model.CommonUser;
import lk.ijse.glingler.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile,Integer> {

    public Profile getProfileByCommonUserAndStatus(CommonUser user,String status);

}
