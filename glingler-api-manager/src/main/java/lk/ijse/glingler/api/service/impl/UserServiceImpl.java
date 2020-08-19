package lk.ijse.glingler.api.service.impl;

import lk.ijse.glingler.dto.UserDTO;
import lk.ijse.glingler.dto.UserResponseBean;
import lk.ijse.glingler.model.CommonUser;
import lk.ijse.glingler.api.repository.UserRepository;
import lk.ijse.glingler.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;


    @Override
    public UserResponseBean getUserDetailByUserName(String username) throws Exception {
        UserResponseBean responseBean = new UserResponseBean();
        CommonUser byUsername = userRepository.getUserByUsername(username);
        System.out.println();
        System.out.println(byUsername);
        System.out.println();
        UserDTO userDTO = new UserDTO();
        userDTO.setUserId(byUsername.getUserId());
        responseBean.setUser(userDTO);
        return responseBean;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        GrantedAuthority authority = null;

        CommonUser commonUser = userRepository.getUserByUsername(s);
        System.out.println("commonUser : " + commonUser);
        if (commonUser != null) {
            if (commonUser.getPasswordStatus().equalsIgnoreCase("ACT")) {
                authority = new SimpleGrantedAuthority("ROLE_ADMIN");
                return new User(commonUser.getUsername(), commonUser.getPassword(), Collections.singletonList(authority));
            } else {
                throw new UsernameNotFoundException("User has been already deactivated by admin");
            }
        } else {
            throw new UsernameNotFoundException("User not found");
        }
    }
}
