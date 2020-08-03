package lk.ijse.glingler.security;

import lk.ijse.glingler.filters.AuthenticationFilter;
import lk.ijse.glingler.util.SysConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AuthenticationFilter authenticationFilter;

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        System.out.println("WebSecurityConfig.configure");

        // Not authenticate below requests
        httpSecurity.csrf().disable()
                .authorizeRequests()
                .antMatchers("/api/v1/" + SysConfig.APP_TYPE_ADMIN + "/sign-in").permitAll()
                .antMatchers("/api/v1/" + SysConfig.APP_TYPE_USER + "/sign-in").permitAll()
                .anyRequest().authenticated()
                .and()
//                .exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint).and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        httpSecurity.addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
//		return new BCryptPasswordEncoder();//  - Add Custom Password Encoder to Ignore Password Encode
        return new CustomPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
