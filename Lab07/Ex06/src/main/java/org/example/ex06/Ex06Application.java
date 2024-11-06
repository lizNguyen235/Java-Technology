package org.example.ex06;

import org.example.ex06.Model.Students;
import org.example.ex06.Repo.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

@SpringBootApplication
public class Ex06Application implements CommandLineRunner {
	@Autowired
	private StudentRepository studentRepository;
	public static void main(String[] args) {
		SpringApplication.run(Ex06Application.class, args);
	}
	public void run(String... args) throws Exception {

		Pageable pageable = PageRequest.of(0, 10, Sort.by("age").descending().and(Sort.by("score").ascending()));
		List<Students> students = studentRepository.findAll(pageable).toList();
		students.forEach(System.out::println);
		System.out.println("read data 4 5 6");
		readData(students);
	}

	public void readData(List<Students> students) {
		System.out.println("Student 4: " + students.get(3));
		System.out.println("Student 5: " + students.get(4));
		System.out.println("Student 6: " + students.get(5));
	}

}
