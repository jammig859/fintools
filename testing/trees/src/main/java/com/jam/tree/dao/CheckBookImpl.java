package com.jam.tree.dao;

import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jam.tree.entity.Account;
import com.jam.tree.entity.GranDetailsParms;
import com.jam.tree.entity.GrantDetail;
import com.jam.tree.entity.GrantSummary;
import com.jam.tree.mappers.CheckBookMapper;

@Transactional
@Repository
public class CheckBookImpl implements CheckBook {

	private DataSource dataSource;
	private JdbcTemplate jdbcTemplateObject;
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	@Override
	   @Autowired
	   public void setDataSource(DataSource dataSource) {
	      this.dataSource = dataSource;
	      this.jdbcTemplateObject = new JdbcTemplate(dataSource); 
	      this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	   }
	
	@Override
	public List<Map<String, Object>>  getDetailPerFund(GranDetailsParms grantParms) {
		
		
		
		// TODO Auto-generated method stub
		String sql = "select  rnum,\r\n" + 
				"        FGBTRND_DOC_CODE,  \r\n" + 
				"        fgbtrnd_activity_date,\r\n" + 
				"        (to_char(fgbtrnd_activity_date,'Mon dd, YYYY HH12:MI AM'))dated,\r\n" + 
				"        FGBTRND_ACCT_CODE,\r\n" + 
				"        fgbtrnd_field_code,\r\n" + 
				"        FGBTRNH_TRANS_DESC,\r\n" + 
				"        FGBTRND_TRANS_AMT,\r\n" + 
				"        TOTAL,\r\n" + 
				"        FGBTRND_ORGN_CODE,\r\n" + 
				"        FGBTRND_FUND_CODE,\r\n" + 
				"        fgbtrnd_prog_code,\r\n" + 
				"        fgbtrnh_doc_seq_Code,\r\n" + 
				"        fgbtrnh_encb_type\r\n" + 
				"from(\r\n" + 
				"select /*+ FIRST_ROWS(10) */  rownum rnum ,\r\n" + 
				"        FGBTRND_DOC_CODE,  \r\n" + 
				"        fgbtrnd_activity_date,\r\n" + 
				"        (to_char(fgbtrnd_activity_date,'Mon dd, YYYY HH12:MI AM'))dated,\r\n" + 
				"        FGBTRND_ACCT_CODE,\r\n" + 
				"        fgbtrnd_field_code,\r\n" + 
				"        FGBTRNH_TRANS_DESC,\r\n" + 
				"        FGBTRND_TRANS_AMT,\r\n" + 
				"         FGBTRND_ORGN_CODE,\r\n" + 
				"         FGBTRND_FUND_CODE,\r\n" + 
				"         fgbtrnd_prog_code,\r\n" + 
				"         fgbtrnh_doc_seq_Code,\r\n" + 
				"         fgbtrnh_encb_type,\r\n" + 
				"         TOTAL \r\n" + 
				"         from(\r\n" + 
				"\r\n" + 
				"select rownum,\r\n" + 
				"      FGBTRND_DOC_CODE,  \r\n" + 
				"      fgbtrnd_activity_date,\r\n" + 
				"        (to_char(fgbtrnd_activity_date,'Mon dd, YYYY HH12:MI AM'))dated,\r\n" + 
				"        FGBTRND_ACCT_CODE,\r\n" + 
				"        fgbtrnd_field_code,\r\n" + 
				"        FGBTRNH_TRANS_DESC,\r\n" + 
				"        FGBTRND_TRANS_AMT,\r\n" + 
				"         FGBTRND_ORGN_CODE,\r\n" + 
				"         FGBTRND_FUND_CODE,\r\n" + 
				"         fgbtrnd_prog_code,\r\n" + 
				"        fgbtrnh_doc_seq_Code,\r\n" + 
				"        FGBTRNH_ENCB_TYPE,\r\n" + 
				"        sum(FGBTRND_TRANS_AMT)\r\n" + 
				"          over(\r\n" + 
				"          order by fgbtrnd_activity_date,\r\n" + 
				"                   FGBTRND_ACCT_CODE desc,\r\n" + 
				"                   fgbtrnd_doc_code	DESC,	\r\n" + 
				"                   fgbtrnd_trans_amt \r\n" + 
				"           ROWS 	UNBOUNDED PRECEDING\r\n" + 
				"\r\n" + 
				"          )TOTAL\r\n" + 
				"        from (\r\n" + 
				"SELECT \r\n" + 
				"                      GRANT_PORTAL_FUNCTIONS.F_Security_For_Web_Fnc(:Coas,\r\n" + 
				"                                                             FGBTRND_FUND_CODE,\r\n" + 
				"                                                             FGBTRND_ORGN_cODE,\r\n" + 
				"                                                             upper(:userName))SEC_IND, \r\n" + 
				"           \r\n" + 
				"                       ft.fgbtrnd_doc_Code,\r\n" + 
				"                       ft.fgbtrnd_activity_date,\r\n" + 
				"                       ft.fgbtrnd_acct_code,\r\n" + 
				"                       ft.fgbtrnd_field_code,\r\n" + 
				"                       --ftvacct.ftvacct_acct_code,\r\n" + 
				"                        FH.FGBTRNH_TRANS_DESC,\r\n" + 
				"                       (ft.fgbtrnd_trans_amt*-1) fgbtrnd_trans_amt,\r\n" + 
				"                       FGBTRND_ORGN_CODE,\r\n" + 
				"                       FT.FGBTRND_FUND_CODE,\r\n" + 
				"                       ft.fgbtrnd_prog_code,\r\n" + 
				"                       fgbtrnh_doc_seq_Code,\r\n" + 
				"                       NVL(FGBTRNH_ENCB_TYPE,'B')FGBTRNH_ENCB_TYPE\r\n" + 
				"                      \r\n" + 
				"         FROM FGBTRND ft,\r\n" + 
				"             FTVFUND F1,\r\n" + 
				"             FTVACCT FA,\r\n" + 
				"             FGBTRNH FH\r\n" + 
				"       WHERE FGBTRND_COAS_CODE = :Coas\r\n" + 
				"       AND FTVACCT_ACCT_CODE = FGBTRND_ACCT_CODE\r\n" + 
				"       AND FTVACCT_COAS_CODE = FGBTRND_COAS_CODE\r\n" + 
				"       AND F1.FTVFUND_FUND_CODE = FGBTRND_FUND_CODE\r\n" + 
				"       AND FTVFUND_GRNT_CODE = :grantNum --('202118')\r\n" + 
				"       --and fgbtrnd_doc_Code = 'F0192875'\r\n" + 
				"       --AND FGBTRND_ACCT_CODE = '6301'\r\n" + 
				"       AND FGBTRND_FIELD_CODE = '04'\r\n" + 
				"       AND FTVACCT_EFF_DATE <= sysdate\r\n" + 
				"       AND (FTVACCT_NCHG_DATE IS NULL\r\n" + 
				"            OR FTVACCT_NCHG_DATE > sysdate ) \r\n" + 
				"       AND F1.FTVFUND_EFF_DATE <= sysdate\r\n" + 
				"       AND (F1.FTVFUND_NCHG_DATE IS NULL\r\n" + 
				"           OR F1.FTVFUND_NCHG_DATE > sysdate)\r\n" + 
				"      and fgbtrnd_doc_code = fgbtrnh_doc_code\r\n" + 
				"      and fgbtrnd_doc_seq_code = fgbtrnh_doc_seq_code\r\n" + 
				"      and fgbtrnd_rucl_code = fgbtrnh_rucl_code\r\n" + 
				"      and fgbtrnd_seq_num = fgbtrnh_seq_num\r\n" + 
				"      and fgbtrnd_item_num = fgbtrnh_item_num\r\n" + 
				"      and fgbtrnd_submission_number = fgbtrnh_submission_number\r\n" + 
				"      AND FGBTRND_ENCD_NUM = FGBTRNH_ENCD_NUM\r\n" + 
				"      and fgbtrnd_acct_code \r\n" + 
				"      not in\r\n" + 
				"     ( select FTVACCT_ACCT_CODE\r\n" + 
				"      FROM  ftvatyp, ftvacct\r\n" + 
				"      WHERE ftvatyp_internal_Atyp_code = '50'\r\n" + 
				"      AND   FTVACCT_ATYP_CODE = FTVATYP_ATYP_CODE  \r\n" + 
				"      AND   FTVACCT_COAS_CODE = FTVATYP_COAS_CODE\r\n" + 
				"      AND   FTVACCT_EFF_DATE <= SYSDATE\r\n" + 
				"      AND   FTVACCT_NCHG_DATE > SYSDATE\r\n" + 
				"      AND   FTVATYP_EFF_DATE <= SYSDATE\r\n" + 
				"      AND   FTVATYP_NCHG_DATE > SYSDATE\r\n" + 
				"      AND   FTVACCT_COAS_CODE = :Coas\r\n" + 
				"     )\r\n" + 
				"    \r\n" + 
				"      AND NOT EXISTS\r\n" + 
				"    (SELECT 'X'\r\n" + 
				"    FROM ftvfsyr\r\n" + 
				"    WHERE fgbtrnd_coas_code = ftvfsyr_coas_code\r\n" + 
				"    AND fgbtrnd_fsyr_code   = SUBSTR(TO_CHAR(to_number(TO_CHAR(to_date(ftvfsyr_fsyr_code,'RR'),'YYYY'))+1),3,2)\r\n" + 
				"    AND TRUNC(fgbtrnh_trans_date) BETWEEN TRUNC(ftvfsyr_start_date) AND TRUNC(ftvfsyr_end_date)\r\n" + 
				"    )\r\n" + 
				"  AND NOT EXISTS\r\n" + 
				"    (SELECT 'X'\r\n" + 
				"    FROM fgbyrlm\r\n" + 
				"    WHERE ((fgbtrnd_doc_code >=fgbyrlm_balf_doc_code\r\n" + 
				"    AND fgbtrnd_doc_code     <=fgbyrlm_balf_doc_code_end\r\n" + 
				"    AND fgbtrnd_doc_seq_code  = '20'\r\n" + 
				"    AND fgbtrnd_coas_code     = fgbyrlm_coas_code\r\n" + 
				"    AND fgbtrnd_fsyr_code     = SUBSTR(TO_CHAR(to_number(TO_CHAR(to_date(fgbyrlm_fsyr_code,'RR'),'YYYY'))+1),3,2))\r\n" + 
				"    OR (fgbtrnd_doc_code     >=fgbyrlm_encb_doc_code\r\n" + 
				"    AND fgbtrnd_doc_code     <=fgbyrlm_encb_doc_code_end\r\n" + 
				"    AND fgbtrnd_doc_seq_code  = '20'\r\n" + 
				"    AND fgbtrnd_coas_code     = fgbyrlm_coas_code\r\n" + 
				"    AND fgbtrnd_fsyr_code     = SUBSTR(TO_CHAR(to_number(TO_CHAR(to_date(fgbyrlm_fsyr_code,'RR'),'YYYY'))+1),3,2))\r\n" + 
				"    OR (fgbtrnd_doc_code     >=fgbyrlm_budcf_doc_code\r\n" + 
				"    AND fgbtrnd_doc_code     <=fgbyrlm_budcf_doc_code_end\r\n" + 
				"    AND fgbtrnd_doc_seq_code  = '20'\r\n" + 
				"    AND fgbtrnd_coas_code     = fgbyrlm_coas_code\r\n" + 
				"    AND fgbtrnd_fsyr_code     = SUBSTR(TO_CHAR(to_number(TO_CHAR(to_date(fgbyrlm_fsyr_code,'RR'),'YYYY'))+1),3,2))\r\n" + 
				"    OR (fgbtrnd_doc_code     >=fgbyrlm_opr_doc_code\r\n" + 
				"    AND fgbtrnd_doc_code     <=fgbyrlm_opr_doc_code_end\r\n" + 
				"    AND fgbtrnd_doc_seq_code  = '20'\r\n" + 
				"    AND fgbtrnd_coas_code     = fgbyrlm_coas_code\r\n" + 
				"    AND fgbtrnd_fsyr_code     = fgbyrlm_fsyr_code))\r\n" + 
				"    )\r\n" + 
				"      AND GRANT_PORTAL_FUNCTIONS.F_Security_For_Web_Fnc(     :Coas,\r\n" + 
				"                                                             FGBTRND_FUND_CODE,\r\n" + 
				"                                                             FGBTRND_ORGN_cODE,\r\n" + 
				"                                                             upper(:userName))='Y'\r\n" + 
				"      \r\n" + 
				"        UNION All\r\n" + 
				"        SELECT\r\n" + 
				"        GRANT_PORTAL_FUNCTIONS.F_Security_For_Web_Fnc(       :Coas,\r\n" + 
				"                                                             FGBTRND_FUND_CODE,\r\n" + 
				"                                                             FGBTRND_ORGN_cODE,\r\n" + 
				"                                                             upper(:userName))SEC_IND,\r\n" + 
				"        FGBTRND_DOC_CODE,  \r\n" + 
				"        fgbtrnd_activity_date,\r\n" + 
				"        FGBTRND_ACCT_CODE,\r\n" + 
				"        fgbtrnd.fgbtrnd_field_code,\r\n" + 
				"        FGBTRNH_TRANS_DESC,\r\n" + 
				"        case when FGBTRND_FIELD_CODE = '03'\r\n" + 
				"        then FGBTRND_TRANS_AMT *-1\r\n" + 
				"        else FGBTRND_TRANS_AMT end FGBTRND_TRANS_AMT,\r\n" + 
				"        FGBTRND_ORGN_CODE,\r\n" + 
				"        FGBTRND_FUND_CODE,\r\n" + 
				"        fgbtrnd_prog_code,\r\n" + 
				"        fgbtrnh_doc_seq_Code,\r\n" + 
				"        NVL(FGBTRNH_ENCB_TYPE,'B')FGBTRNH_ENCB_TYPE\r\n" + 
				"    \r\n" + 
				"      FROM FGBTRND,\r\n" + 
				"           FTVFUND F1,\r\n" + 
				"           FTVFUND F2,\r\n" + 
				"           FTVACCT,\r\n" + 
				"           FIMSMGR.fgbtrnh\r\n" + 
				"     WHERE FGBTRND_COAS_CODE = :Coas\r\n" + 
				"       AND FTVACCT_ACCT_CODE = FGBTRND_ACCT_CODE\r\n" + 
				"       AND FTVACCT_COAS_CODE = FGBTRND_COAS_CODE\r\n" + 
				"       AND FTVACCT_EFF_DATE <= sysdate\r\n" + 
				"       AND (FTVACCT_NCHG_DATE IS NULL\r\n" + 
				"            OR FTVACCT_NCHG_DATE > sysdate\r\n" + 
				"            )\r\n" + 
				"       AND FTVACCT_ATYP_CODE = NVL(NULL,FTVACCT_ATYP_CODE)\r\n" + 
				"       AND F1.FTVFUND_FUND_CODE = FGBTRND_FUND_CODE\r\n" + 
				"       AND F1.FTVFUND_COAS_CODE = FGBTRND_COAS_CODE\r\n" + 
				"       AND F1.FTVFUND_EFF_DATE <= sysdate\r\n" + 
				"       AND (F1.FTVFUND_NCHG_DATE IS NULL\r\n" + 
				"            OR F1.FTVFUND_NCHG_DATE > sysdate\r\n" + 
				"            )\r\n" + 
				"       AND F1.FTVFUND_FTYP_CODE = NVL(NULL,F1.FTVFUND_FTYP_CODE)\r\n" + 
				"       AND FGBTRND_FIELD_CODE in ('06','03')--03 is actual 06 is budget.\r\n" + 
				"       AND FGBTRND_LEDGER_IND = 'O'\r\n" + 
				"       AND FGBTRND_FUND_CODE LIKE NVL(NULL,'%')\r\n" + 
				"       AND FGBTRND_COAS_CODE = F2.FTVFUND_COAS_CODE\r\n" + 
				"       AND FGBTRND_FUND_CODE = F2.FTVFUND_FUND_CODE\r\n" + 
				"       AND F2.FTVFUND_GRNT_CODE = :grantNum --('202118')\r\n" + 
				"       AND F2.FTVFUND_EFF_DATE <= sysdate\r\n" + 
				"       AND (F2.FTVFUND_NCHG_DATE IS NULL\r\n" + 
				"            OR F2.FTVFUND_NCHG_DATE > sysdate)\r\n" + 
				"       and fgbtrnd_acct_code   not in\r\n" + 
				" ( select FTVACCT_ACCT_CODE\r\n" + 
				"  FROM  ftvatyp, ftvacct\r\n" + 
				"  WHERE ftvatyp_internal_Atyp_code = '50'\r\n" + 
				"  AND   FTVACCT_ATYP_CODE = FTVATYP_ATYP_CODE  \r\n" + 
				"  AND   FTVACCT_COAS_CODE = FTVATYP_COAS_CODE\r\n" + 
				"  AND   FTVACCT_EFF_DATE <= SYSDATE\r\n" + 
				"  AND   FTVACCT_NCHG_DATE > SYSDATE\r\n" + 
				"  AND   FTVATYP_EFF_DATE <= SYSDATE\r\n" + 
				"  AND   FTVATYP_NCHG_DATE > SYSDATE\r\n" + 
				"  AND   FTVACCT_COAS_CODE = :Coas\r\n" + 
				" )\r\n" + 
				" and fgbtrnd_doc_code = fgbtrnh_doc_code\r\n" + 
				"      and fgbtrnd_doc_seq_code = fgbtrnh_doc_seq_code\r\n" + 
				"      and fgbtrnd_rucl_code = fgbtrnh_rucl_code\r\n" + 
				"      and fgbtrnd_seq_num = fgbtrnh_seq_num\r\n" + 
				"      and fgbtrnd_item_num = fgbtrnh_item_num\r\n" + 
				"      and fgbtrnd_submission_number = fgbtrnh_submission_number\r\n" + 
				"      and fgbtrnd_seq_num = fgbtrnh_seq_num\r\n" + 
				"  AND NOT EXISTS\r\n" + 
				"    (SELECT 'X'\r\n" + 
				"    FROM ftvfsyr\r\n" + 
				"    WHERE fgbtrnd_coas_code = ftvfsyr_coas_code\r\n" + 
				"    AND fgbtrnd_fsyr_code   = SUBSTR(TO_CHAR(to_number(TO_CHAR(to_date(ftvfsyr_fsyr_code,'RR'),'YYYY'))+1),3,2)\r\n" + 
				"    AND TRUNC(fgbtrnh_trans_date) BETWEEN TRUNC(ftvfsyr_start_date) AND TRUNC(ftvfsyr_end_date)\r\n" + 
				"    )\r\n" + 
				"  AND NOT EXISTS\r\n" + 
				"    (SELECT 'X'\r\n" + 
				"    FROM fgbyrlm\r\n" + 
				"    WHERE ((fgbtrnd_doc_code >=fgbyrlm_balf_doc_code\r\n" + 
				"    AND fgbtrnd_doc_code     <=fgbyrlm_balf_doc_code_end\r\n" + 
				"    AND fgbtrnd_doc_seq_code  = '20'\r\n" + 
				"    AND fgbtrnd_coas_code     = fgbyrlm_coas_code\r\n" + 
				"    AND fgbtrnd_fsyr_code     = SUBSTR(TO_CHAR(to_number(TO_CHAR(to_date(fgbyrlm_fsyr_code,'RR'),'YYYY'))+1),3,2))\r\n" + 
				"    OR (fgbtrnd_doc_code     >=fgbyrlm_encb_doc_code\r\n" + 
				"    AND fgbtrnd_doc_code     <=fgbyrlm_encb_doc_code_end\r\n" + 
				"    AND fgbtrnd_doc_seq_code  = '20'\r\n" + 
				"    AND fgbtrnd_coas_code     = fgbyrlm_coas_code\r\n" + 
				"    AND fgbtrnd_fsyr_code     = SUBSTR(TO_CHAR(to_number(TO_CHAR(to_date(fgbyrlm_fsyr_code,'RR'),'YYYY'))+1),3,2))\r\n" + 
				"    OR (fgbtrnd_doc_code     >=fgbyrlm_budcf_doc_code\r\n" + 
				"    AND fgbtrnd_doc_code     <=fgbyrlm_budcf_doc_code_end\r\n" + 
				"    AND fgbtrnd_doc_seq_code  = '20'\r\n" + 
				"    AND fgbtrnd_coas_code     = fgbyrlm_coas_code\r\n" + 
				"    AND fgbtrnd_fsyr_code     = SUBSTR(TO_CHAR(to_number(TO_CHAR(to_date(fgbyrlm_fsyr_code,'RR'),'YYYY'))+1),3,2))\r\n" + 
				"    OR (fgbtrnd_doc_code     >=fgbyrlm_opr_doc_code\r\n" + 
				"    AND fgbtrnd_doc_code     <=fgbyrlm_opr_doc_code_end\r\n" + 
				"    AND fgbtrnd_doc_seq_code  = '20'\r\n" + 
				"    AND fgbtrnd_coas_code     = fgbyrlm_coas_code\r\n" + 
				"    AND fgbtrnd_fsyr_code     = fgbyrlm_fsyr_code))\r\n" + 
				"    )\r\n" + 
				"    AND GRANT_PORTAL_FUNCTIONS.F_Security_For_Web_Fnc(:Coas,\r\n" + 
				"                                                             FGBTRND_FUND_CODE,\r\n" + 
				"                                                             FGBTRND_ORGN_cODE,\r\n" + 
				"                                                             upper(:userName))='Y' )\r\n" + 
				"       ORDER BY    fgbtrnd_activity_date desc,\r\n" + 
				"                   FGBTRND_ACCT_CODE,\r\n" + 
				"                   --FGBTRND_FUND_cODE,\r\n" + 
				"                   --FGBTRND_ORGN_CODE,\r\n" + 
				"                   fgbtrnd_trans_amt desc)\r\n" + 
				"                 where rownum <=:endNum) /*end point*/\r\n" + 
				"                   where rnum >= :startNum";
				//Gson gson = new GsonBuilder().create();
				
				SqlParameterSource namedParameters = new BeanPropertySqlParameterSource(grantParms);
				//List<GrantDetail> det =(List<GrantDetail>) this.namedParameterJdbcTemplate.queryForList(sql,  namedParameters);
				List<Map<String, Object>> grantDetail = this.namedParameterJdbcTemplate.queryForList(sql,  namedParameters);
				return grantDetail;
				/*String thisgson = gson.toJson(grantDetail);
				System.out.println(grantDetail);
				return null;*/
	}

	
	
	
	
	@Override
	@Cacheable("GrantSummary")
	public Object getUserGrants(GranDetailsParms grantParms) {
		String sql = "SELECT q.*, resultcount  FROM ( \r\n" + 
				"SELECT \r\n" + 
				"COUNT(*) OVER() resultcount ,"+
				"m.frrgrnl_grnt_code,"+
				"sum(m.frrgrnl_14_adopt_bud + m.frrgrnl_14_bud_adjt - m.frrgrnl_14_ytd_actv - m.frrgrnl_14_bud_rsrv - m.frrgrnl_14_encumb) FRRGRNL_TOTAL, \r\n" + 
				"sum(m.frrgrnl_14_adopt_bud + m.frrgrnl_14_bud_adjt) FRRGRNL_BUDGET,"+
				"sum(m.frrgrnl_14_ytd_actv) FRRGRNL_YTD,sum(m.frrgrnl_14_bud_rsrv) FRRGRNL_RESERVATIONS, "+
				"sum(m.frrgrnl_14_encumb) FRRGRNL_ENCUMBRANCE, "+
				"f.agency_name, \r\n"+
				"f.sponsor_id,\r\n"+
				"f.status, \r\n "+
				"f.analyst_first_name || ' '||f.analyst_last_name ANALYST_NAME, \r\n   "+
				"GRANT_TITLE, \r\n"+
				"INVESTIGATOR_FIRST_NAME||' '||INVESTIGATOR_LAST_NAME INVESTIGATOR_NAME, \r\n"+
				"ROW_NUMBER() OVER("+
		                " ORDER BY " +
		                " m.frrgrnl_grnt_code," +
		                "  f.analyst_first_name || ' '||f.analyst_last_name ," +
		                "GRANT_TITLE," +
		                "INVESTIGATOR_FIRST_NAME||' '||INVESTIGATOR_LAST_NAME ," +
		                " f.agency_name " +
		               ") rownumber" +
				"\r\n" + 
				"FROM fimsmgr.frrgrnl m , fvganls f\r\n" + 
				"where m.frrgrnl_grnt_yr =\r\n" + 
				"(SELECT max(s.frrgrnl_grnt_yr)\r\n" + 
				"FROM frrgrnl s\r\n" + 
				"WHERE s.frrgrnl_grnt_code = m.frrgrnl_grnt_code \r\n" + 
				"and   s.frrgrnl_acct_Code = m.frrgrnl_acct_code\r\n" + 
				"and   s.frrgrnl_fund_code = m.frrgrnl_fund_code\r\n" + 
				"and   s.frrgrnl_orgn_code = m.frrgrnl_orgn_code\r\n" + 
				"and   s.frrgrnl_prog_code = m.frrgrnl_prog_code\r\n" + 
				"and   s.frrgrnl_locn_code = m.frrgrnl_locn_code\r\n" + 
				"and   s.frrgrnl_actv_code = m.frrgrnl_actv_code\r\n" + 
				")\r\n" + 
				"and  frrgrnl_acct_code not like '1%' \r\n" + 
				"and  m.frrgrnl_grnt_code = f.grant_code(+)\r\n" + 
				"and  substr(m.frrgrnl_grnt_code,1,1)!='6' \r\n" + 
				"AND  F_Security_For_Web_Fnc('D',\r\n" + 
				"                                                             frrgrnl_fund_code,\r\n" + 
				"                                                             frrgrnl_orgn_code,\r\n" + 
				"                                                             upper(:userName))='Y'\r\n" + 
				"GROUP BY F_Security_For_Web_Fnc('D',frrgrnl_fund_code,frrgrnl_orgn_code,upper(:userName)),\r\n" + 
				"m.frrgrnl_grnt_code,\r\n" + 
				"f.agency_name,\r\n" + 
				"f.sponsor_id,\r\n" + 
				"f.status, \r\n" + 
				"f.analyst_first_name || ' '||f.analyst_last_name,\r\n" + 
				"GRANT_TITLE,\r\n" + 
				"INVESTIGATOR_FIRST_NAME||' '||INVESTIGATOR_LAST_NAME\r\n" + 
				"  ) q --WHERE rownumber BETWEEN :firstrec and :lastrec";
				SqlParameterSource namedParameters = new BeanPropertySqlParameterSource(grantParms);
				//List<GrantDetail> det =(List<GrantDetail>) this.namedParameterJdbcTemplate.queryForList(sql,  namedParameters);
				return   this.namedParameterJdbcTemplate.query(sql,  namedParameters, new CheckBookMapper() );
				//System.out.println(userGrants.get(12).get("ResultCount"));
				//return null;
	}

	
	



}
