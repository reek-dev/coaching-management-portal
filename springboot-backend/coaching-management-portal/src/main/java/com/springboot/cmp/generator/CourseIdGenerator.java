package com.springboot.cmp.generator;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import com.springboot.cmp.util.HibernateUtil;

import jakarta.persistence.EntityManager;

public class CourseIdGenerator implements IdentifierGenerator {

    @Override
    public Object generate(SharedSessionContractImplementor session, Object object) throws HibernateException {

        EntityManager em = HibernateUtil.getSessionFactory().createEntityManager();
		em.getTransaction().begin();

		final List<String> resultList = 
		em.createNativeQuery("select c.id from course c order by id desc", String.class)
		.setMaxResults(1).getResultList();


		String id = (resultList.size() == 0) ? 
		("C-" + String.format("%03d", 0)) : resultList.get(0);

		System.out.println("using id: " + id);

		em.getTransaction().commit();
		em.close();

		return
		(
			"C-" + String.format("%03d", Integer.valueOf(id.substring(id.length()-3))+1)
		);
    }
    
}
