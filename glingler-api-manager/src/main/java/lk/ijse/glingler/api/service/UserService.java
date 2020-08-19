package lk.ijse.glingler.api.service;

import lk.ijse.glingler.dto.UserResponseBean;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    UserResponseBean getUserDetailByUserName(String username) throws Exception;
}
