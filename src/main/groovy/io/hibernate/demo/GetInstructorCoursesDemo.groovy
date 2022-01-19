package io.hibernate.demo

import io.hibernate.config.HibernateConfig
import io.hibernate.entity.Course
import io.hibernate.entity.Instructor
import org.hibernate.Session
import org.hibernate.SessionFactory
import org.springframework.context.annotation.AnnotationConfigApplicationContext

class GetInstructorCoursesDemo {
    static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(HibernateConfig.class);

        SessionFactory sessionFactory = context.getBean("sessionFactory")


        Session session = sessionFactory.getCurrentSession()

        try {

            session.beginTransaction()

            int theId = 1

            Instructor tempInstructor = session.get(Instructor.class, theId)

            println "Instructor: ${tempInstructor}"

            println "Courses: ${tempInstructor.getCourses()}"

            session.getTransaction().commit()

            println "Done"
        } finally {
            session.close()
            sessionFactory.close()
        }
        context.close()
    }
}
