package CRUD;

import java.util.List;
import java.util.Scanner;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import schoolmanagement.domain.Course;
import schoolmanagement.domain.Education;
import schoolmanagement.domain.Teacher;


public class EducationDAO {
        
    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("PU");

    static Scanner sc = new Scanner(System.in);
    
    
    public static void createEducation() {
     
        System.out.println("What is the name of the education?");
        String educationName = sc.nextLine();
        
        Education education = new Education(educationName);
        
        EntityManager em = emf.createEntityManager();
        
        em.getTransaction().begin();
        
        em.persist(education);
        
        em.getTransaction().commit();
        
        em.close();
        
    }
    
    public static void updateEducation(Long id) {
        
        EntityManager em = emf.createEntityManager();
        
        Education foundEducation = em.find(Education.class, id);
        
        System.out.println("What is the name of the new education?");
        String newName = sc.nextLine();
       
        foundEducation.setName(newName);
        
        em.getTransaction().begin();
        
        em.merge(foundEducation);
        
        em.getTransaction().commit();
        
        
    }
    
    public static void deleteEducation() {
        
        EducationDAO.showAll();
        System.out.println("Choose the ID of the education you want to delete");
        Long educationId = sc.nextLong();
        sc.nextLine();
        
        EntityManager em = emf.createEntityManager();
        
        Education education = em.find(Education.class, educationId);
        
        em.getTransaction().begin();
        
        em.remove(education);
        
        em.getTransaction().commit();
        
        em.close();
        
        
    }
    
    public static void addEducationToTeacher() {
        
        EducationDAO.showAll();
        System.out.println("What is the ID of the education that you want to add?");
        int educationId = sc.nextInt();
        sc.nextLine();
        
        TeacherDAO.showAll();
        System.out.println("Choose the ID of the teacher that you want the course /n"
                + "to be added to");
        int teacherId = sc.nextInt();
        sc.nextLine();
        
        EntityManager em = emf.createEntityManager();
        
        Education foundEducation = em.find(Education.class, educationId);
        
        Teacher foundTeacher = em.find(Teacher.class, teacherId);
                
                
        
        
        
    }

    public static void showAll() {
        EntityManager em = emf.createEntityManager();
        List<Education> educations = em.createQuery("SELECT s FROM Education s", Education.class).getResultList();
        for (Education ed : educations) {
            System.out.println(ed);
        }

    }
}
