package com.jam.tree.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import com.jam.tree.entity.GrantSummary;


	public class CheckBookMapper implements RowMapper<GrantSummary> {
		   public GrantSummary mapRow(ResultSet rs, int rowNum) throws SQLException {
			   GrantSummary grant = new GrantSummary();
			      grant.setAgencyName(rs.getString("AGENCY_NAME"));
			      grant.setFrrgrnlTotal(rs.getString("FRRGRNL_TOTAL"));
			      grant.setAnalystName(rs.getString("ANALYST_NAME"));
			      grant.setFrrgrnlBudget(rs.getString("FRRGRNL_BUDGET"));
			      grant.setSponsorId(rs.getString("SPONSOR_ID"));
			      grant.setFrrgrnlEncumbrance(rs.getString("FRRGRNL_ENCUMBRANCE"));
			      grant.setFrrgrnlGrntCode(rs.getString("frrgrnl_grnt_code"));
			      grant.setStatus(rs.getString("status"));
			      grant.setInvestigatorName(rs.getString("INVESTIGATOR_NAME"));
			      grant.setFrrgrnlBudget(rs.getString("FRRGRNL_BUDGET"));
			      grant.setFrrgrnlYtd(rs.getString("FRRGRNL_YTD"));
			      grant.setFrrgrnlReservations(rs.getString("FRRGRNL_RESERVATIONS"));
			      grant.setResultCount(rs.getString("resultcount"));
			      grant.setRowNumber(rs.getString("rowNumber"));
			      grant.setGrantTitle(rs.getString("GRANT_TITLE"));
			      
			      
			      
			      return grant;
			   }
	}

	
	

