package com.coachingproj.scheduler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@SpringBootApplication
public class SchedulerApplication {

	static final String JDBC_DRIVER = "org.postgresql.Driver";
	static final String DB_URL = "jdbc:postgresql://localhost:5432/schedulerapp";
	static final String USER  = "postgres";
	static final String PASS  = "nvmmy";

	public static void main(String[] args) {
		SpringApplication.run(SchedulerApplication.class, args);
		createDatabaseConnection();
	}

	@GetMapping
	public String sayHello(){
		System.out.println("I am in the Scheduler app!");
    	return "Hello Spring World!";
	}

	public static void createDatabaseConnection(){
		Connection c = null;
		try{
			Class.forName(JDBC_DRIVER);
			c = DriverManager.getConnection(DB_URL, USER, PASS);	
		}
		catch(Exception e){
			e.printStackTrace();
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		finally{
			try{
				if(c!=null)
					c.close();
			}catch(SQLException se){
				se.printStackTrace();
			}
   		}
		System.out.println("Opened database successfully");
	}

}
