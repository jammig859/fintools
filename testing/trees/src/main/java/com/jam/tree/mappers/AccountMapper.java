package com.jam.tree.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.jam.tree.entity.Account;

public class AccountMapper implements RowMapper<Account> {
	   public Account mapRow(ResultSet rs, int rowNum) throws SQLException {
		      Account account = new Account();
		      account.setAccountCode(rs.getString("ACCOUNT_CODE"));
		      account.setTitle(rs.getString("TITLE"));
		      account.setPred(rs.getString("PRED"));
		      account.setLevel(rs.getString("LEVEL"));
		      return account;
		   }
}
