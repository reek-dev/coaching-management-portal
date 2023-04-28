package com.springboot.cmp.entity;

import org.hibernate.Session;

import com.springboot.cmp.util.HibernateUtil;

import jakarta.persistence.EntityManager;

public class Demo {
	public static void main(String[] args) {
		try (EntityManager entityManager = HibernateUtil.getSessionFactory().createEntityManager()) {
			
			entityManager.getTransaction().begin();
			Long lastId = 1L;
			try {
				lastId = entityManager
						.createQuery("select id " + "from Teacher " + "order by id desc " + "limit 1", Long.class)
						.getSingleResult();
				System.out.println(lastId);
			} catch (jakarta.persistence.NoResultException noResultException) {
				System.out.println(noResultException.getMessage() + ", initiating with id: `0001`");
			}
		}
		
	}
}
