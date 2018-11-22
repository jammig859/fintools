package com.jam.tree.rest;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jam.tree.dao.CheckBookImpl;
import com.jam.tree.entity.DataTableObject;
import com.jam.tree.entity.GranDetailsParms;
import com.jam.tree.entity.GrantSummary;

@RestController
public class CheckBookRestController {

	@Autowired
	CheckBookImpl checkbook;
	@Cacheable("GrantSummary")
	@RequestMapping("/rest/api/getUserGrants")
	public String getUserGrants(@RequestBody ObjectNode json) {
		
		
		
		Number const1= 1;
		Gson gson = new GsonBuilder().create();
        
        
        
        
		String userId = Optional.ofNullable(json.get("userId").asText()).orElse("");
		//Number displLength   = NumberUtils.createNumber(json.get("length").asText());
		//Number dispStart   = NumberUtils.createNumber((json.get("start").asText()));
		
		//Number  firstrec =  dispStart.intValue()+1;
		//Number  lastrec  =  dispStart.intValue() + displLength.intValue();
		
		
		GranDetailsParms parms = new GranDetailsParms();
		
		parms.setFirstrec(0);
		parms.setLastrec(0);
		parms.setUserName(userId);
		List<GrantSummary>  listOfGrants = (List<GrantSummary>) checkbook.getUserGrants(parms);
		String data ="";
		if (!listOfGrants.isEmpty()) {
			DataTableObject fullResults = new DataTableObject();
			fullResults.setAaData(listOfGrants);
			//fullResults.setiTotalRecords( json.get("length").asText()    );
			fullResults.setiTotalDisplayRecords(listOfGrants.get(0).getResultCount().toString());
			 data = gson.toJson(fullResults);
		} else {
			/*List<listOfGrants>studentList = new ArrayList<listOfGrants>();
			DataTableObject thisResult = new DataTableObject();
			thisResult.setAaData(studentList);*/
			//data = gson.toJson(thisResult);
		}
		
		//String thisgson = gson.toJson(listOfGrants);
		//System.out.println(grantDetail);
		return data;
		
	}
	
}
