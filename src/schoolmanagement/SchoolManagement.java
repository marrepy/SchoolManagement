/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package schoolmanagement;

import CRUD.CourseDAO;
import CRUD.EducationDAO;
import CRUD.StudentDAO;
import CRUD.TeacherDAO;
import java.util.Scanner;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import schoolmanagement.domain.Course;
import schoolmanagement.domain.Education;
import schoolmanagement.domain.Student;

public class SchoolManagement {

    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("PU");

    static Scanner sc = new Scanner(System.in);

    static boolean loop = true;

public static void main(String[] args) {

        EntityManager em = emf.createEntityManager();
        while (loop) {
            MainMenu();
        }
//        StudentDAO.updateStudent(3l);
//        Education e = em.find(Education.class,1l);
//        System.out.println(e);
////        
//        Education ed = new Education("Java Developer BackEnd");
////        Course c = new Course("Database management");
////        Course c2 = new Course("HTML");
////        
////        ed.addCourse(c);
////        ed.addCourse(c2);
////        
//        em.getTransaction().begin();
//        
////        em.remove(e);
//        em.persist(ed);
//        
//        em.getTransaction().commit();
    }

    private static void MainMenu() {

        System.out.println("1.StudentManagement");
        System.out.println("2.TeacherManagement");
        System.out.println("3.EducationManagement");
        System.out.println("4.CourseManagement");
        System.out.println("0. Exit");

        int choice = sc.nextInt();
        sc.nextLine();

        switch (choice) {
            case 0:
                loop = false;
                break;
            case 1:
                StudentManagementMenu();
                break;

            case 2:
                TeacherManagementMenu();
                break;

            case 3:
                EducationMagementMenu();
                break;

            case 4:
                
                CourseManagementMenu();

                break;

            default:

                System.out.println("No such option");
        }

    }

    private static void StudentManagementMenu() {

        System.out.println("1. Create student");
        System.out.println("2. Update student");
        System.out.println("3. Delete student");
        System.out.println("4. Show student");
        System.out.println("5. Add student to education");
        System.out.println("6. Show all students");

        int choice = sc.nextInt();
        sc.nextLine();

        EntityManager em = emf.createEntityManager();

        switch (choice) {
            case 1:
                System.out.println("What is the name of the student?");
                String name = sc.nextLine();

                Student student = new Student(name);

                StudentDAO.createStudent(student);
                break;

            case 2:
                StudentDAO.showAll();
                System.out.println("Choose student");

                Long studentId = sc.nextLong();
                sc.nextLine();

                StudentDAO.updateStudent(studentId);

                break;

            case 3:
                StudentDAO.showAll();
                studentId = sc.nextLong();
                sc.nextLine();

                em.getTransaction().begin();
                em.remove(em.find(Student.class, studentId));
                em.getTransaction().commit();

                break;

            case 4:
                StudentDAO.showAll();
                studentId = sc.nextLong();
                sc.nextLine();

                Student foundStudent = em.find(Student.class, studentId);
                System.out.println(foundStudent);

                break;
            case 5:

                StudentDAO.showAll();
                EducationDAO.showAll();

                System.out.println("Choose student");
                studentId = sc.nextLong();
                sc.nextLine();

                foundStudent = em.find(Student.class, studentId);

                System.out.println("Choose education");
                Long educationtId = sc.nextLong();
                sc.nextLine();

                Education foundEducation = em.find(Education.class, educationtId);

                em.getTransaction().begin();
                foundEducation.addStudent(foundStudent);
                em.getTransaction().commit();

                System.out.println(foundStudent + " " + foundStudent.getEducation());

                break;

            case 6:
                StudentDAO.showAll();
                break;

            default:

                System.out.println("No such option");
        }

        em.close();
    }

    private static void TeacherManagementMenu() {

        System.out.println("1. Create teacher");
        System.out.println("2. Update teacher");
        System.out.println("3. Delete teacher");
        System.out.println("4. Show teacher");
        System.out.println("5. Add course to teacher");
        System.out.println("6. Show all teachers");
        
        int choice = sc.nextInt();
        sc.nextLine();
        
        EntityManager em = emf.createEntityManager();
        
        switch (choice) {
            case 1:
                TeacherDAO.createTeacher();
                
                break;
                
            case 2:
                TeacherDAO.showAll();
                System.out.println("Choose the teacher to update");
                
                Long teacherId = sc.nextLong();
                sc.nextLine();
                
                TeacherDAO.updateTeacher(teacherId);
                
                break;
                
            case 3:
                
                TeacherDAO.showAll();
                System.out.println("Which teacher do you want to delete?");
                teacherId = sc.nextLong();
                sc.nextLine();
                
                TeacherDAO.deleteTeacher(teacherId);
                
                break;
                
            case 4:
                
                TeacherDAO.showAll();
                System.out.println("Choose the id of the teacher that you want to show");
                teacherId = sc.nextLong();
                sc.nextLine();
                
                TeacherDAO.showTeacher(teacherId);
                
                break;
                
            case 5:
                CourseDAO.showAll();
                System.out.println("Choose which course to add to the teacher");
                Long courseId = sc.nextLong();
                sc.nextLine();
                
                TeacherDAO.showAll();
                System.out.println("Choose which teacher to add the course to");
                teacherId = sc.nextLong();
                sc.nextLine();
                
                TeacherDAO.addCourse(courseId, teacherId);
                
                break;
                
            case 6:
                TeacherDAO.showAll();
                break;
                
            default:
                System.out.println("No such option");
        }

    }

    private static void EducationMagementMenu() {
        
        System.out.println("1. Create education");
        System.out.println("2. Update education");
        System.out.println("3. Delete education");
        System.out.println("4. Show education");
        System.out.println("5. Add education to student");
        System.out.println("6. Show all educations");

        int choice = sc.nextInt();
        sc.nextLine();

        EntityManager em = emf.createEntityManager();
        
        switch (choice) {
            case 1:
                EducationDAO.createEducation();
                
                break;
                
            case 2:
                EducationDAO.showAll();
                System.out.println("Choose the ID of the education that you want to update");
                
                Long educationId = sc.nextLong();
                sc.nextLine();
                
                EducationDAO.updateEducation(educationId);
                
                break;
                
            case 3:
                
                EducationDAO.deleteEducation();
               
               
                
                break;
                
            case 4:
                
                EducationDAO.showAll();
                
                break;
                
            case 5:
                
                EducationDAO.addStudentToEducation();
                
                break;
                
            case 6:
                TeacherDAO.showAll();
                break;
                
            default:
                System.out.println("No such option");
        }
        
        
    }

    private static void CourseManagementMenu() {
        
        System.out.println("1. Create course");
        System.out.println("2. Update course");
        System.out.println("3. Delete course");
        System.out.println("4. Show course");
        System.out.println("5. Add course to student");
        System.out.println("6. Show all courses");

        int choice = sc.nextInt();
        sc.nextLine();

        EntityManager em = emf.createEntityManager();
        
        switch (choice) {
            case 1:
                
                CourseDAO.createCourse();
                break;
                
            case 2:
                
                CourseDAO.updateCourse();
                break;
                
            case 3:
                
                CourseDAO.deleteCourse();
                
                break;
                
            case 4:
                
                CourseDAO.showCourse();
                
                break;
                
            case 5:
                
                
                break;
                
            case 6:
                
                CourseDAO.showAll();
                break;
                
            default:
                System.out.println("No such option");
        }
    }

}
