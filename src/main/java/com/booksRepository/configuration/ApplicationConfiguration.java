package com.booksRepository.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * This class is configuration class for application context.
 *
 * @author Igor Ivanov
 */
@Configuration
@ComponentScan({"com.booksRepository.model"})
public class ApplicationConfiguration
{
    /**
     * This field is environment in which the application is running.
     */
    @Autowired
    private Environment environment;

    /**
     * This method creates and configures {@link EntityManagerFactory}.
     *
     * @return {@link EntityManagerFactory}
     */
    @Bean
    public EntityManagerFactory entityManagerFactory()
    {
        return Persistence.createEntityManagerFactory("ogm-jpa");
    }

    /**
     * This method creates and returns {@link PersistenceExceptionTranslationPostProcessor} - bean post-processor
     * that automatically applies persistence exception translation to any bean marked with Spring's @Repository annotation
     *
     * @return {@link PersistenceExceptionTranslationPostProcessor}
     */
    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation()
    {
        return new PersistenceExceptionTranslationPostProcessor();
    }
}
