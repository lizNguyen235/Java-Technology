package org.example.springcommerce.Config;


import org.example.springcommerce.Service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;


@Configuration
@EnableWebSecurity
public class MySecurityConfig {

    @Autowired
    LoginService userDetailsService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .csrf(AbstractHttpConfigurer::disable) // tắt tính năng bảo vệ CSRF
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("Register/**", "Login/**","/register", "/register_account").permitAll() // cho phép truy cập vào CSS, JS, hình ảnh
                        .requestMatchers("/product/**","/odermanagement/**").hasRole("ADMIN") // chỉ cho phép người dùng có quyền ADMIN truy cập vào trang /product
                        .anyRequest().authenticated() // yêu cầu tất cả các yêu cầu đều cần xác thực
                )
                .formLogin(form -> form
                        .loginPage("/") // chỉ định trang đăng nhập tùy chỉnh
                        .loginProcessingUrl("/perform_login") // chỉ định endpoint entry để tíến hành xử lí
                        .permitAll() // cho phép mọi người truy cập vào trang đăng nhập
                        .successHandler(new SimpleUrlAuthenticationSuccessHandler("/home")) // sau khi đăng nhập thành công, chuyển hướng đến trang /login/home
                )
                .logout(LogoutConfigurer::permitAll); // cho phép mọi người thực hiện đăng xuất
        return http.build(); // trả về đối tượng SecurityFilterChain đã được cấu hình
    }

    @Bean
    // cái hàm này dùng để tiến hành xác thực người dùng
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }
}
