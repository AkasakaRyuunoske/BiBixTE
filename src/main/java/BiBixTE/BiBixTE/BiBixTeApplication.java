package BiBixTE.BiBixTE;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Authors: Akasaka Ryuunosuke, Galasso Antonio.
 *
 * This is a simple example of an e-commerce site
 * using Java + Spring Boot 2.6.6 (More info about dependencies is in pom.xml)
 *
 * */

@SpringBootApplication
public class BiBixTeApplication {
	public static void main(String[] args) {
		SpringApplication.run(BiBixTeApplication.class, args);
	}
}

/*
*	Short info about most elements used, but better read real documentation and not this.
*
* 	Main class of the application, is used to start all others components
* 	because @SpringBootApplication annotation provides several others
* 	annotations like: @SpringBootConfiguration, @EnableAutoConfiguration, @ComponentScan.
*
* 	@ComponentScan - is used to implement all components annotated with @Component annotation.
*
* 	By default, classes annotated with @Component, @Repository, @Service, @Controller,
*   or a custom annotation that itself is annotated with @Component
* 	are the only detected candidate components.
*
* 	@SpringBootConfiguration - automatically configures the Spring application
*  	based on its included jar files, it sets up defaults or helper based
*  	on dependencies in pom.xml.
*
*   Auto-configuration is usually applied based
*  	on the classpath and the defined beans. Therefore, we do not need
*  	to define any of the DataSource, EntityManagerFactory, TransactionManager etc
*  	and magically based on the classpath, Spring Boot automatically creates proper
*  	beans and registers them for us. For example when there is a tomcat-embedded.jar
*  	on your classpath you likely need a TomcatEmbeddedServletContainerFactory
*  	(unless you have defined your own EmbeddedServletContainerFactory bean).
*  	@EnableAutoConfiguration has a exclude attribute to disable an auto-configuration
*  	explicitly otherwise we can simply exclude it from the pom.xml, for example
*  	if we do not want Spring to configure the tomcat then exclude
*  	spring-bootstarter-tomcat from spring-boot-starter-web.
*
*   @EnableAutoConfiguration - Enables @SpringBootConfiguration.
 */