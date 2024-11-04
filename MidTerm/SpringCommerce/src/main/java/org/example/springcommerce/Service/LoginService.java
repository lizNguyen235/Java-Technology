package org.example.springcommerce.Service;

import org.example.springcommerce.Model.Account;
import org.example.springcommerce.Model.AccountPrincipal;
import org.example.springcommerce.Repo.AccountRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class LoginService implements UserDetailsService {

    @Autowired
    AccountRepo accountRepo;

    public LoginService() {
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountRepo.findByUsername(username);
        if (account == null) {
            System.out.println("User not found");
            throw new UsernameNotFoundException("User not found");
        }
        System.out.println("Login successful");
        System.out.println(account);
        return new AccountPrincipal(account);
    }


}
