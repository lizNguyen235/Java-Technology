package org.example.ex03;

import org.example.ex03.Model.Students;
import org.example.ex03.Repo.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class Ex03Application implements CommandLineRunner {
	@Autowired
	private StudentRepository studentRepository;
	public static void main(String[] args) {
		SpringApplication.run(Ex03Application.class, args);
	}
	@Override
	public void run(String... args) throws Exception {
		Students student1 = new Students();
		Students student2 = new Students();
		Students student3 = new Students();
		setStudent(student1,"Nguyen Vinh Hung", 20, "nguyenvinhhung2305@gmail.com", 6.0);
		setStudent(student2,"Dang Van Trong", 20, "dangvantrong@gmail.com", 8.0);
		setStudent(student3,"Ta Triet", 20, "tatriet@gmail.com", 9.0);

		studentRepository.save(student1);
		studentRepository.save(student2);
		studentRepository.save(student3);
		List<Students> students = studentRepository.findAll();
		students.forEach(System.out::println);
		studentRepository.delete(student1);
		students = studentRepository.findAll();
		students.forEach(System.out::println);
	}

	public static void setStudent(Students students, String name, int age, String email, double score) {
		students.setName(name);
		students.setAge(age);
		students.setEmail(email);
		students.setScore(score);
	}
}
