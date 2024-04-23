package com.ensah.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.hibernate5.support.OpenSessionInViewInterceptor;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import com.ensah.core.bo.*;

//Configuration d'une application Spring MVC (@EnableWebMvc)
//Avec support des transactions (@EnableTransactionManagement)

@EnableWebMvc // Configuration d'une Application Spring MVC
@Configuration // Classe de configuration qui va contenir des beans à créer automatiquement par
				// Spring
@ComponentScan(basePackages = { "com.ensah" }) // Packages à scanner pour chercher les beans spring de type component
												// (càd @controller, @repository, @service)
@EnableTransactionManagement // Support des transactions
public class AppConfig implements WebMvcConfigurer {

	/**
	 * A utiliser si vous voulez faire la journalisation. Voir le fichier log4j.xml
	 * ou .properties
	 */
	private Logger LOGGER = Logger.getLogger(getClass().getName());

	public AppConfig() {

		// On enregistre une trace dans le journal
		LOGGER.debug(" configuration init...");
	}

	// Configuration du ViewResolver

	@Bean
	public ViewResolver internalResourceViewResolver() {
		InternalResourceViewResolver bean = new InternalResourceViewResolver();
		bean.setViewClass(JstlView.class);

		bean.setPrefix("/WEB-INF/view/");
		bean.setSuffix(".jsp");
		return bean;
	}

	// Configuration de la Session Factory de Hibernate
	@Bean // Nécessaire pour que Spring créra automatiquemnt la sessionFactory
	public LocalSessionFactoryBean sessionFactory() {

		// Code copié de la documentation

		final LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();

		sessionFactory.setDataSource(getDataSource());

		// Propriété Hibernate
		Properties hibernateProperties = new Properties();
		hibernateProperties.setProperty("hibernate.hbm2ddl.auto", "update");
		hibernateProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");
		hibernateProperties.setProperty("hibernate.show_sql", "true");

		sessionFactory.setHibernateProperties(hibernateProperties);

		// Indiquer vos classes annotées par @Entity ici
		sessionFactory.setAnnotatedClasses(Student.class);

		// Tracer dans le journal juste pour des raisons de débougage
		// que la session Factory a été bien crée
		if (sessionFactory != null) {
			LOGGER.debug(" sessionFactory created ...");
		}

		return sessionFactory;
	}

	// Configuration de la source de données
	@Bean // nécessaire car Spring va créer la datasource automatiquement et l'injecter
			// apres dans la session factory
	public DataSource getDataSource() {

		// Les informations de votre base de données

		BasicDataSource dataSource = new BasicDataSource();

		// A configurer en fonction de votre base de données
		dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver"); // Driver
		dataSource.setUrl("jdbc:mysql://localhost/gs_students"); // L'url d'accès à la base de données
		dataSource.setUsername("root"); // login
		dataSource.setPassword("aymane2002"); // mot de passe
		return dataSource;
	}

	// Permet de traduire toutes les exceptions de la couche persistance en une
	// seule exception de type PersistenceExceptionTranslationPostProcessor
	// (embalage des execptions
	// de la couche de persistance)
	@Bean
	public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
		return new PersistenceExceptionTranslationPostProcessor();
	}

	// Configuration du Gestionnaire des Transactions
	// Spring définit une API pour la gestion des Transactions
	// (PlatformTransactionManager) qui cache l'implémentation réelle du
	// gestionnaire de transactions pour que l'application ne sera pas dépendante avec 
	//les frameworks sous-jacents utilisés dans la couche dao.
	//Ici nous avons configué un gestionnaire de transactions pour Hibernate
	// (HibernateTransactionManager) mais que nous retournons sous forme de
	// PlatformTransactionManager (car HibernateTransactionManager est une
	// implémentation de HibernateTransactionManager)

	@Bean // Permet à Spring de créer automatiquement ce bean de gestion des transaction
			// et de l'injecter dans son bon endroit pour l'utilsier dans la couche service
			// dans les services annotées par @transactional et @service
	@Autowired // Permet d'injecter sessionFactory nécessaire pour le gestionnaire de
				// transaction
	public PlatformTransactionManager transactionManager(final SessionFactory sessionFactory) {

		// Création du gestionnaire de transaction de Hibernate
		final HibernateTransactionManager txManager = new HibernateTransactionManager();

		// On lui injecte la session Factory crée dans un autre bean un peu plus haut
		// dans cette classe
		txManager.setSessionFactory(sessionFactory);

		// Tracer si gestionnnaire de transaction est bien crée
		if (txManager != null) {
			LOGGER.debug(" Hibernate Transaction Manager created ...");

		}

		return txManager;

	}

	// Ajoute un intercepteur permettant de gérer la session Hibernate danns la
	// couche Web. Ceci permet d'éviter les exceptions Lazy
	public void addInterceptors(InterceptorRegistry registry) {
		OpenSessionInViewInterceptor openSessionInViewInterceptor = new OpenSessionInViewInterceptor();
		openSessionInViewInterceptor.setSessionFactory(sessionFactory().getObject());

		registry.addWebRequestInterceptor(openSessionInViewInterceptor).addPathPatterns("/**");
	}

}