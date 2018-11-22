package com.jam.tree.dao;

import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.stereotype.Repository;
import com.jam.tree.entity.GranDetailsParms;
import com.jam.tree.entity.GrantSummary;


@Repository
public  interface CheckBook {

 public void setDataSource(DataSource ds);
 public List<Map<String, Object>> getDetailPerFund(GranDetailsParms grantParms);



Object getUserGrants(GranDetailsParms grantParms);
}
