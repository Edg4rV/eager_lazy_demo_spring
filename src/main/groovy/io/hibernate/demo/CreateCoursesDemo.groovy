package io.hibernate.demo

import io.hibernate.config.HibernateConfig
import io.hibernate.entity.Course
import io.hibernate.entity.Instructor
import io.hibernate.entity.InstructorDetail
import org.hibernate.Session
import org.hibernate.SessionFactory
import org.springframework.context.annotation.AnnotationConfigApplicationContext

class CreateCoursesDemo {
    static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(HibernateConfig.class);

        SessionFactory sessionFactory = context.getBean("sessionFactory")


        Session session = sessionFactory.getCurrentSession()

        try {

            session.beginTransaction()

            int theId = 2

            Instructor tempInstructor = session.get(Instructor.class, theId)

            Course tempCourse1 = new Course('CI_CD_Engineer')
            Course tempCourse2 = new Course('API')

            tempInstructor.add(tempCourse1)
            tempInstructor.add(tempCourse2)

            session.save(tempCourse1)
            session.save(tempCourse2)

            session.getTransaction().commit()

            println "Done"
        } finally {
            session.close()
            sessionFactory.close()
        }
        context.close()
    }
}
