package app.data;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateUtil {
	private static final SessionFactory sessionFactory = buildSessionFactory();

	private static SessionFactory buildSessionFactory() {
		try {

			StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder().configure().build();

			Metadata metadata = new MetadataSources(standardRegistry).getMetadataBuilder().build();

			SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();
			return sessionFactory;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Error while creating SessionFactory");
		}
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

}
