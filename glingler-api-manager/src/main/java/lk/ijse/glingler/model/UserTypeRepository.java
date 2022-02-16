package lk.ijse.glingler.model;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserTypeRepository extends JpaRepository<UserType, Integer> {
    public UserType getUserTypeByUserTypeCode(String userType);
}