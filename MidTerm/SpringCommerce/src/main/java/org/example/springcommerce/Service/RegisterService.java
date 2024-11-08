package org.example.springcommerce.Service;

import org.example.springcommerce.Model.Account;
import org.example.springcommerce.Repo.AccountRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

@Service
public class RegisterService {
    public RegisterService() {
    }

    @Autowired
    AccountRepo accountRepo;
    @Autowired
    PasswordEncoder passwordEncoder;
    public boolean isRegister(String username) {
       return accountRepo.existsByUsername(username);
    }

    public void register(Account account) {
        String password = passwordEncoder.encode(account.getPassword());
        account.setPassword(password);
        accountRepo.save(account);
    }
}
