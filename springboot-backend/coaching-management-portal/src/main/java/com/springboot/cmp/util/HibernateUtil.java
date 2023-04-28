package com.springboot.cmp.util;

import java.util.HashMap;
import java.util.Map;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Environment;

public class HibernateUtil {
	private static StandardServiceRegistry standardServiceRegistry;
	private static SessionFactory sessionFactory;

	static {
		StandardServiceRegistryBuilder registryBuilder = new StandardServiceRegistryBuilder();
		Map<String, Object> settings = new HashMap<>();
		settings.put(Environment.URL,
				"jdbc:mysql://localhost:3306/demo?autoReconnect=true&useSSL=false&serverTimezone=UTC&useLegacyDatetimeCode=false");
		settings.put(Environment.USER, "coaching_admin");
		settings.put(Environment.PASS, "root");
		settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQLDialect");
		settings.put(Environment.SHOW_SQL, "true");

		// apply the database settings
		registryBuilder.applySettings(settings);

		// creating the registry
		standardServiceRegistry = registryBuilder.build();

		MetadataSources sources = new MetadataSources(standardServiceRegistry);
		Metadata metadata = sources.getMetadataBuilder().build();
		sessionFactory = metadata.getSessionFactoryBuilder().build();
	}

	// utility method to return SessionFactory
	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
}
