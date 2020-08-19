package lk.ijse.glingler.filters;

import lk.ijse.glingler.security.JwtUtil;
import lk.ijse.glingler.api.service.UserService;
import lk.ijse.glingler.util.SysConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@WebFilter
public class AuthenticationFilter extends OncePerRequestFilter {
    private static final Logger LOGGER = LogManager.getLogger(AuthenticationFilter.class.getName());

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserService userService;

    private void springAuthentication(HttpServletRequest request, String username){
        if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null){
            UserDetails userDetails = userService.loadUserByUsername(username);

            UsernamePasswordAuthenticationToken
                    usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                    userDetails, null, userDetails.getAuthorities());
            usernamePasswordAuthenticationToken
                    .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

        }

    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String requestTokenHeader = request.getHeader("Authorization");

        if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
            boolean isValidToken = jwtUtil.checkValidity(requestTokenHeader);
            if(isValidToken) {
                String userName = jwtUtil.getUserName(requestTokenHeader);
                jwtUtil.createToken(userName, SysConfig.APP_TYPE_USER, response);
                springAuthentication(request, userName);
                request.setAttribute("username",userName);
            }
        }
        filterChain.doFilter(request, response);
    }
}
