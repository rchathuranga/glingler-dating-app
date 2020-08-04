package lk.ijse.glingler.service;

import lk.ijse.glingler.dto.UserResponseBean;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    UserResponseBean getUserDetails() throws Exception;
}
