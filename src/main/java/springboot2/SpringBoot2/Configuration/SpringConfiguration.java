package springboot2.SpringBoot2.Configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SpringConfiguration  extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.authorizeRequests()
                .antMatchers("/home/**", "/**", "/css/**", "/js/**", "/fonts/**","/font/**", "/images/**", "/public/rest/**","/login*").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                    .loginPage("/")
                    .loginProcessingUrl("/authenticateTheUser")
                    .failureUrl("/goLoginFailure")
                    .permitAll()
                    .and()
                    .logout().permitAll();

    }
}
