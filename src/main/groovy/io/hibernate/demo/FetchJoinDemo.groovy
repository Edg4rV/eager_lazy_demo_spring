package io.hibernate.demo

import io.hibernate.config.HibernateConfig
import io.hibernate.entity.Instructor
import org.hibernate.Session
import org.hibernate.SessionFactory
import org.hibernate.query.Query
import org.springframework.context.annotation.AnnotationConfigApplicationContext


class FetchJoinDemo {
    static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(HibernateConfig.class);

        SessionFactory sessionFactory = context.getBean("sessionFactory")


        Session session = sessionFactory.getCurrentSession()

        try {

            session.beginTransaction()

            int theId = 1

            Query<Instructor> query =
                    session.createQuery("select i from Instructor i "
                                     + "JOIN FETCH i.courses "
                                     + "where i.id=:theInstructorId",
                                     Instructor.class)

            query.setParameter("theInstructorId", theId)

            Instructor tempInstructor = query.getSingleResult()

            println "Testing Eager: Instructor: ${tempInstructor}"


            session.getTransaction().commit()

            session.close()

            println "we are closing session"

            println "Testing Eager: Courses: ${tempInstructor.getCourses()}"

//            session.getTransaction().commit()

            println "Done"
        } finally {
            session.close()
            sessionFactory.close()
        }
        context.close()
    }
}
