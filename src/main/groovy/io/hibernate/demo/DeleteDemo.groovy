package io.hibernate.demo

import io.hibernate.config.HibernateConfig
import io.hibernate.entity.Instructor
import io.hibernate.entity.InstructorDetail
import org.hibernate.Session
import org.hibernate.SessionFactory
import org.springframework.context.annotation.AnnotationConfigApplicationContext

class DeleteDemo {
    static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(HibernateConfig.class);

        SessionFactory sessionFactory = context.getBean("sessionFactory")


        Session session = sessionFactory.getCurrentSession()

        try {

            session.beginTransaction()

            int theId = 1

            Instructor tempInstructor =
                    session.get(Instructor.class, theId)

            println "Found instructor: ${tempInstructor}"

            if (tempInstructor != null) {
                println "Deleting: ${tempInstructor}"
                session.delete(tempInstructor)
            }

            session.getTransaction().commit()

            println "Done"
        } finally {
            sessionFactory.close()
        }
        context.close()
    }
}
