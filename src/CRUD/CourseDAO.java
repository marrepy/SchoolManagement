package CRUD;

import java.util.List;
import java.util.Scanner;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import schoolmanagement.domain.Course;

public class CourseDAO {

    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("PU");

    static Scanner sc = new Scanner(System.in);

    public static void createCourse() {

        System.out.println("What is the name of the name of the course that you \n"
                + "want to create");

        String newCourseName = sc.nextLine();

        Course course = new Course(newCourseName);

        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        em.persist(course);

        em.getTransaction().commit();
    }

    public static void showAll() {

        EntityManager em = emf.createEntityManager();

        List<Course> courses = em.createQuery("SELECT c from  Course c", Course.class).getResultList();
        for (Course course : courses) {
            System.out.println(course);
        }

    }

    public static void updateCourse() {

        showAll();
        System.out.println("Choose the ID of the course to update");
        Long courseId = sc.nextLong();
        sc.nextLine();

        EntityManager em = emf.createEntityManager();

        Course foundCourse = em.find(Course.class, courseId);

        System.out.println("What is the name of the new course");
        String newNameOfCourse = sc.nextLine();

        em.getTransaction().begin();

        foundCourse.setName(newNameOfCourse);

        em.getTransaction().commit();
    }

    public static void deleteCourse() {

        showAll();

        System.out.println("Choose the ID of the course you want to delete");
        Long educationId = sc.nextLong();

        EntityManager em = emf.createEntityManager();

        Course foundCourse = em.find(Course.class, educationId);

        em.getTransaction().begin();

        em.remove(foundCourse);

        em.getTransaction().commit();
    }

    public static void showCourse() {
        
        showAll();
        System.out.println("What is the ID of the course you want to show?");
        Long courseId = sc.nextLong();
        
        EntityManager em = emf.createEntityManager();
        
        Course foundCourse = em.find(Course.class, courseId);
        
        System.out.println(foundCourse);
        
        
    }

}
