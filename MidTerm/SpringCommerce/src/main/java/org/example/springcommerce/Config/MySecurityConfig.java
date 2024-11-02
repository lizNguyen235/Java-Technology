package org.example.springcommerce.Config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class MySecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("Login/css/**", "Login/js/**", "Login/images/**","Login/fonts/**","Login/vendor/**").permitAll() // cho phép truy cập vào CSS, JS, hình ảnh
                        .anyRequest().authenticated() // yêu cầu tất cả các yêu cầu đều cần xác thực
                )
                .formLogin(form -> form
                        .loginPage("/") // chỉ định trang đăng nhập tùy chỉnh
                        .permitAll() // cho phép mọi người truy cập vào trang đăng nhập
                )
                .logout(LogoutConfigurer::permitAll); // cho phép mọi người thực hiện đăng xuất
        return http.build(); // trả về đối tượng SecurityFilterChain đã được cấu hình
    }
}
