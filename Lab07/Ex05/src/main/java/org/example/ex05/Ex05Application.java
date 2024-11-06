package org.example.ex05;

import org.example.ex05.Model.Students;
import org.example.ex05.Repo.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class Ex05Application implements org.springframework.boot.CommandLineRunner {

	@Autowired
	private StudentRepository studentRepository;
	public static void main(String[] args) {
		SpringApplication.run(Ex05Application.class, args);
	}
	@Override
	public void run(String... args) throws Exception {
		Students student1 = new Students();
		Students student2 = new Students();
		Students student3 = new Students();
		setStudent(student1,"Vinh Nguyen Hung", 20, "nguyenvinhhung2305@gmail.com", 6.0);
		setStudent(student2,"Dang Van Trong", 20, "dangvantrong@gmail.com", 8.0);
		setStudent(student3,"Ta Triet", 20, "tatriet@gmail.com", 9.0);

		studentRepository.save(student1);
		studentRepository.save(student2);
		studentRepository.save(student3);
		System.out.println("List of students:");
		List<Students> students = studentRepository.findAll();
		students.forEach(System.out::println);

		studentRepository.delete(student2);
		System.out.println("List of students after deleting student1:");
		students = studentRepository.findAll();
		students.forEach(System.out::println);

		List<Students> students1 = studentRepository.findAge(20);
		System.out.println("List of students with age >= 20:");
		students1.forEach(System.out::println);
		int size = studentRepository.countStudent(8.0);
		System.out.println("Number of students with score 8.0: " + size);
		List<Students> students3 = studentRepository.findName("Nguyen");
		System.out.println("List of students with name containing 'nguyen':");
		students3.forEach(System.out::println);

	}

	public static void setStudent(Students students, String name, int age, String email, double score) {
		students.setName(name);
		students.setAge(age);
		students.setEmail(email);
		students.setScore(score);
	}
}
