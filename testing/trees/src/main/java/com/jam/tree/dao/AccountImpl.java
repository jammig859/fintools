package com.jam.tree.dao;

import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.object.SqlQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.jam.tree.entity.Account;
import com.jam.tree.mappers.AccountMapper;
@Transactional
@Repository
public class AccountImpl implements AccountDao{
       private DataSource dataSource;
	   private JdbcTemplate jdbcTemplateObject;
	   
	   @Override
	   @Autowired
	   public void setDataSource(DataSource dataSource) {
	      this.dataSource = dataSource;
	      this.jdbcTemplateObject = new JdbcTemplate(dataSource);  
	   }

	

	@Override
	public List<Account> listAccounts() {
		String sql = "WITH acc \r\n" + 
				"     AS (SELECT ftvacct_acct_code                              ACCOUNT_CODE, \r\n" + 
				"                Nvl(ftvacct_acct_code_pred, ftvacct_atyp_code) ACCOUNT_PRED, \r\n" + 
				"                ftvacct_title                                  ACCOUNT_TITLE \r\n" + 
				"         FROM   fimsmgr.ftvacct a \r\n" + 
				"         WHERE  A.ftvacct_coas_code = 'D' \r\n" + 
				"                AND a.ftvacct_nchg_date > SYSDATE \r\n" + 
				"         UNION ALL \r\n" + 
				"         SELECT ftvatyp_atyp_code ACCOUNT_CODE, \r\n" + 
				"                NULL              ACCOUNT_PRED, \r\n" + 
				"                ftvatyp_title \r\n" + 
				"         FROM   ftvatyp \r\n" + 
				"         WHERE  ftvatyp_atyp_code IN (SELECT ftvacct_atyp_code \r\n" + 
				"                                      FROM   ftvacct \r\n" + 
				"                                      WHERE  ftvacct_acct_code_pred IS NULL) \r\n" + 
				"                AND ftvatyp_nchg_date > SYSDATE \r\n" + 
				"                AND ftvatyp_coas_code = 'D')\r\n" + 
				"SELECT Lpad('',( LEVEL - 1 )* 3) \r\n" + 
				"       || account_code AS ACCOUNT_CODE, \r\n" + 
				"       account_title   TITLE, \r\n" + 
				"       account_pred    PRED ,\r\n" + 
				"       level\r\n" + 
				"FROM   acc \r\n" + 
				"CONNECT BY PRIOR account_code = account_pred \r\n" + 
				"START WITH account_pred is null \r\n";
				//"START WITH account_pred ='62'   /*IS NULL*/ \r\n" + 
				//"";
		SqlQuery<Account> sqlQuery = new SqlQuery<Account>() {
	         @Override
	         protected RowMapper<Account> newRowMapper(Object[] parameters, Map<?, ?> context){
	            
	            return new AccountMapper();
	         }

			
	      };
	      sqlQuery.setDataSource(dataSource);
	      sqlQuery.setSql(sql);
	      List <Account> accounts = sqlQuery.execute();
	      return accounts;
	}
}
