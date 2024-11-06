package org.example.ex04.Repo;
import org.example.ex04.Model.Students;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Students, Integer> {
    List<Students> findByAgeGreaterThanEqual(int age);
    int countStudentsByScore(double score);
    List<Students> findByNameContainingIgnoreCase(String name);
}
