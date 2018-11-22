package com.jam.tree.entity;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jam.tree.Tracker;
import com.jam.tree.util.JList;
@Component
public class Nodes {
	
	
	

	private String text;
	private String acct;
	private int id;
	private JList<Nodes> children = new JList<Nodes>();
	
	//private List<Nodes> elementsIndex; /*= new JList<Nodes>()*/;
	
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	//overloaded constructor.
	public Nodes() {
		
	}
	
	public Nodes(String text, int id, String acct ) {
		super();
		this.text = text;
		this.id = id;
		this.acct = acct;
		this.children = new JList<Nodes>();
		//track.registerChildForSearch(this);
	}

	public String getAcct() {
		return acct;
	}

	public void setAcct(String acct) {
		this.acct = acct;
	}

	@Override
	public String toString() {
		return "RootNode [text=" + text + ", id=" + id + ", children=" + children + "]";
	}

	public String getText() {
		return this.text;
	}

	public void setText(String text) {
		this.text = text;
	}


	public Nodes addChildNode (int index, Nodes node) {
		Nodes childNode = node;
		this.children.add(childNode);
		
		//track.registerChildForSearch(childNode);
		//this.registerChildForSearch(childNode);
		return childNode;
	}
	/*private void registerChildForSearch(Nodes node) {
		this.elementsIndex.add(node);
		
	}*/
	
	/*public Nodes findTreeNode(String searchNode) {
		
	return  track.findTreeNode(searchNode);
	 
		for (Nodes element : this.elementsIndex) {
			String elData = element.text;
			if (cmp.compareTo(elData) == 0)
				return element;
		}
		

	//	return null;
	}*/
	
	
	
	/*public ArrayList<ChildTreeNode> getChildTreeNode() {
		return this.children;
	}
*/
	
	
	
	
//	public void setChildrenNode(int index,JList<ChildTreeNode>  childNodeList) {
//		this.children.get(index).setChildren(childNodeList);
		//if this.children.contains(childNodeList)
		//ChildTreeNode thisNode = this.children.stream().filter(x -> 3.equals(getChildTreeNode() )).findAny().orElse(null);
	   //this.children.contains(ChildTreeNode.
		//System.out.println(doesItContain);
//	} 
	/*
	
	
	public void addChildToChild(int index,ChildTreeNode childNodeList) {
		//this.children.get(index)..setChildren(childNodeList);
	}*/
	
	
	
	
	
}
