package com.jam.tree.entity;


import java.util.List;


import com.jam.tree.util.JList;

//import java.util.ArrayList;

public class ChildTreeNode  {
	 
	
	private int id;
	private JList<ChildTreeNode> children = new JList<ChildTreeNode>();
	
	private String text;
	
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	

	public String getText() {
		return this.text;
	}

	public void setText(String text) {
		this.text = text;
	}

	

	public List<ChildTreeNode> getChildren() {
		return this.children;
	}

	public void setChildren(JList<ChildTreeNode>childNodeList) {
		this.children = childNodeList;
	}
	
	
}