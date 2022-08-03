package org.example.jaystuff;

import org.example.jaystuff.model.PrimaryTeacher;
import org.example.jaystuff.model.Student;
import org.example.shared.io.UserInputService;
import org.example.shared.io.UserOutputService;
import org.example.shared.io.console.ConsoleUserInputServiceImpl;
import org.example.shared.io.console.ConsoleUserOutputServiceImpl;
import org.example.shared.io.validation.NonBlankInputValidationRule;

import javax.persistence.*;
import java.time.Instant;
import java.util.Date;

public class Main {
    public static void main(String[] args) throws Exception {
        UserOutputService userOutputService = new ConsoleUserOutputServiceImpl();
        try (UserInputService userInputService = new ConsoleUserInputServiceImpl(userOutputService)) {
            userOutputService.print("WELCOME");
            String response = userInputService.getUserInput("What's the student name?",
                    new NonBlankInputValidationRule());

            // Part 1 - Get H2 database working
            // Part 1B - Add a row to STUDENT_DATA via the H2 console
            // Part 1C - Make a Student class that matches the STUDENT_DATA table

            // Part 1D - create a new student instance
            Student student1 = new Student();
            student1.setId(1);
            student1.setName(response);
            student1.setDob(Date.from(Instant.now()));
            student1.setPrimaryTeacher(PrimaryTeacher.JAY);

            // create EntityManager (copy paste this when needed)
            EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("example");
            EntityManager entityManager = entityManagerFactory.createEntityManager();

            // access transaction object (copy paste this when needed)
            EntityTransaction transaction = entityManager.getTransaction();

            // create and use transactions (copy paste this when needed)
            transaction.begin();

            // Part 1D - Insert
            entityManager.persist(student1);
        // IMPORTANT!


            // Part 2a - Find (by ID)
//            Student student = entityManager.find(Student.class, 1);
//            System.out.println(student.getName());

            // Part 2b - Find (with native query) (please note the strange HQL syntax)
            TypedQuery<Student> studentResult = entityManager.createQuery("select S from Student S where S.id " +
                            "= 1",
                    Student.class);
//            Student student = studentResult.getResultList().get(0);
//            System.out.println(student.getName());
            // Part 3 - Update
//            Student student = entityManager.find(Student.class, 1);
//            student.setName("updated name");
//            entityManager.persist(student);
            // Part 4 - Delete
//            Student student = entityManager.find(Student.class, 1);
//            entityManager.remove(student);


            // Part 5 - Exceptions?
            // Let's write code to insert a student but then do something to cause an exception


            // commit your transaction (copy paste this when needed)
            transaction.commit();
        }
    }
}

enum Example {
    YES, NO
}