package lk.ijse.glingler.filters;

import lk.ijse.glingler.security.JwtUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
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


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String requestTokenHeader = request.getHeader("Authorization");
        System.out.println("req auth : " + requestTokenHeader);

        String requestURI = request.getRequestURI();
        System.out.println("url : " + requestURI);

        if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
            boolean b = jwtUtil.checkValidity(requestTokenHeader);
            System.out.println("isTokenValid : " + b);
        }


        filterChain.doFilter(request, response);


        String token = jwtUtil.createToken("ravinduC");
        System.out.println("token : " + token);
        response.setHeader("Authorization", token);

        System.out.println("web filtered");
    }
}
