package com.jam.tree;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

//import org.springframework.context.annotation.Bean;

import com.jam.tree.entity.Nodes;
import com.jam.tree.util.JList;

@Configuration
public class Tracker {

	private List<Nodes> elementsIndex = new JList<Nodes>();
	
	@Autowired
	public Tracker() {
		
	}
public Nodes findTreeNode(String searchNode) {
		
		for (Nodes snode : this.elementsIndex) {
		if (snode.getAcct().equals(searchNode) ) {
			return snode;
		}
	 }
		/*for (Nodes element : this.elementsIndex) {
			String elData = element.text;
			if (cmp.compareTo(elData) == 0)
				return element;
		}*/
		

		return null;
	}
	
public void registerChildForSearch(Nodes node) {
	this.elementsIndex.add(node);
	
}
public void clean() {
	this.elementsIndex.clear();
}
	
	
	
	
}


