package org.example.ex02.Repo;
import org.example.ex02.Model.Students;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends CrudRepository<Students, Integer> {
}
