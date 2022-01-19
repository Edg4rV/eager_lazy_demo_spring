package io.hibernate.demo

import io.hibernate.config.HibernateConfig
import io.hibernate.entity.InstructorDetail
import org.hibernate.Session
import org.hibernate.SessionFactory
import org.springframework.context.annotation.AnnotationConfigApplicationContext

class DeleteInstructorDetailDemo {
    static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(HibernateConfig.class);

        SessionFactory sessionFactory = context.getBean("sessionFactory")


        Session session = sessionFactory.getCurrentSession()

        try {

            session.beginTransaction()

            int theId = 3

            InstructorDetail tempInstructorDetail =
                    session.get(InstructorDetail.class, theId)

            println "tempInstructorDetail: ${tempInstructorDetail}"

            println "the associated instructor: ${tempInstructorDetail?.getInstructor()}"

            println "Deleting tempInstructorDetail: ${tempInstructorDetail}"

            tempInstructorDetail.getInstructor().setInstructorDetail(null)

            session.delete(tempInstructorDetail)

            session.getTransaction().commit()

            println "Done"
        } catch (Exception exc) {
          exc.printStackTrace()
        } finally {
            session.close()
            sessionFactory.close()
        }
        context.close()
    }
}
