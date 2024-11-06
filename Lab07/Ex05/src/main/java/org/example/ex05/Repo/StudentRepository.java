package org.example.ex05.Repo;
import org.example.ex05.Model.Students;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Students, Integer> {

    @Query("SELECT s FROM Students s WHERE s.age >= ?1")
    List<Students> findAge(int age);
    @Query("SELECT count(*) FROM Students s WHERE s.score = ?1")
    int countStudent(double score);
    @Query("SELECT s FROM Students s WHERE LOWER(s.name) LIKE LOWER(CONCAT('%', ?1, '%'))")
    List<Students> findName(String name);
}
