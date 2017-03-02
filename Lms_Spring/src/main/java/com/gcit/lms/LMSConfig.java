package com.gcit.lms;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LMSConfig {
	  private String driver = "com.mysql.jdbc.Driver";
	    private String url = "jdbc:mysql://localhost/library";
	    private String user =  "root";
	    private String password = "shashi";
	    
	    public BasicDataSource datasource()
	    {
	    	BasicDataSource ds = new BasicDataSource();
	    	ds.setDriverClassName(driver);
	    	ds.setUrl(url);
	    	ds.setUsername(user);
	    	ds.setPassword(password);
	    	return ds;
	    	
	    }
	
	

}
