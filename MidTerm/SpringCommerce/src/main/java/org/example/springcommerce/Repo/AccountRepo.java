package org.example.springcommerce.Repo;

import org.example.springcommerce.Model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepo extends JpaRepository<Account, String> {
     Account findByUsername(String username);
}
