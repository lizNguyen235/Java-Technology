package org.example.springcommerce.Service;

import org.example.springcommerce.Model.Account;
import org.example.springcommerce.Repo.AccountRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

@Service
public class RegisterService {
    public RegisterService() {
    }

    @Autowired
    AccountRepo accountRepo;

    public boolean register(String username, String password) {
        if (accountRepo.findByUsername(username) != null) {
            return false;
        }
        return true;
    }
}
