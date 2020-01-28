package CRUD;

import java.util.List;
import java.util.Scanner;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import schoolmanagement.domain.Course;

public class CourseDAO {
    
    
    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("PU");
    
    Scanner sc = new Scanner(System.in);
    
    
    public static void showAll() {
        
        EntityManager em = emf.createEntityManager();
        
        List<Course> courses = em.createQuery("SELECT c from  Course c", Course.class).getResultList();
        for (Course course : courses) {
            System.out.println(course);
        }
        
    }
    
}
