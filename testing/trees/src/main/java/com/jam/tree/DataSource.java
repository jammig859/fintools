package com.jam.tree;

import java.sql.SQLException;

import javax.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import oracle.ucp.UniversalConnectionPoolAdapter;
import oracle.ucp.UniversalConnectionPoolException;
import oracle.ucp.admin.UniversalConnectionPoolManagerImpl;
import oracle.ucp.jdbc.PoolDataSource;
import oracle.ucp.jdbc.PoolDataSourceFactory;


@Configuration
@ConfigurationProperties("oracle")
public class DataSource {
	
	
	@Autowired
	  private PoolManager poolMgr;
	  @NotNull
	  private String username;

	  @NotNull
	  private String password;

	  @NotNull
	  private String url;

	  PoolDataSource poolDataSource;
	
	 @Bean  
	 public PoolDataSource ucpNonProxyDataSource() throws SQLException, UniversalConnectionPoolException {  
	 
	 poolMgr.setPoolManager( UniversalConnectionPoolManagerImpl.getUniversalConnectionPoolManager()); 
	 PoolDataSource poolDataSource =  PoolDataSourceFactory.getPoolDataSource();  
	 poolDataSource.setConnectionPoolName("mgr_pool");
	 poolDataSource.setURL("jdbc:oracle:thin:@//elver.irt.drexel.edu:2337/DUDEVL");  
	 poolDataSource.setUser("JMA337");  
	 poolDataSource.setPassword("Stefani28");  
	 poolDataSource.setConnectionFactoryClassName("oracle.jdbc.pool.OracleDataSource");  
	 poolDataSource.setMaxStatements(15);
	 poolDataSource.setMaxConnectionReuseCount(200);
	 //The abandoned connection timeout enables borrowed connections to be reclaimed back into the connection pool after a connection has not been used for a specific amount of time
	 poolDataSource.setAbandonedConnectionTimeout(180);
	 //how long an available connection can remain idle before it is closed and removed from the pool
	 poolDataSource.setInactiveConnectionTimeout(60);
	 poolDataSource.setInitialPoolSize(5);  
	 poolDataSource.setValidateConnectionOnBorrow(true);  
	 poolMgr.getPoolManager().createConnectionPool( (UniversalConnectionPoolAdapter) poolDataSource);
	 return poolDataSource;  
	 }
	
	
	
}
