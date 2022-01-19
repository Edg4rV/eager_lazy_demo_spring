package io.hibernate.demo

import io.hibernate.config.HibernateConfig
import io.hibernate.entity.Instructor
import io.hibernate.entity.InstructorDetail
import org.hibernate.Session
import org.hibernate.SessionFactory
import org.springframework.context.annotation.AnnotationConfigApplicationContext

class CreateInstructorDemo {
    static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(HibernateConfig.class);

        SessionFactory sessionFactory = context.getBean("sessionFactory")


        Session session = sessionFactory.getCurrentSession()

        try {

            Instructor tempInstructor =
                    new Instructor("Edgar", "Vardanyan", "e.vardan4an@gmail.com")

            InstructorDetail tempInstructorDetail =
                    new InstructorDetail(
                            "http://www.youtube.com",
                            "Video Games"
                    )

            tempInstructor.setInstructorDetail(tempInstructorDetail)

            session.beginTransaction()

            println "Saving instructor: ${tempInstructor}"

            session.save(tempInstructor)

            session.getTransaction().commit()

            println "Done"
        } finally {
            session.close()
            sessionFactory.close()
        }
        context.close()
    }
}
