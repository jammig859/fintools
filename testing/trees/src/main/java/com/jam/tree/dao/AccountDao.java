package com.jam.tree.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.stereotype.Repository;

import com.jam.tree.entity.Account;

@Repository
public interface AccountDao {

	  public void setDataSource(DataSource ds);
	  public List<Account> listAccounts(); 
}
