package br.com.appwebic.dao;

import java.sql.Connection;
import java.sql.DriverManager;
 

public class ConnectionFactory {

   public static Connection getConnection() throws Exception{
	   
	  Class.forName("com.mysql.jdbc.Driver");  
      String url = "jdbc:mysql://localhost:3306/appwebic?user=root";
      
      Connection connection = DriverManager.getConnection(url);
      return connection;
   }
   
   public static void closeConnection (Connection connection) throws Exception{
		   connection.close();
   }
 
}