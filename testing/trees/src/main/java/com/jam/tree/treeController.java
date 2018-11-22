package com.jam.tree;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.jam.tree.dao.AccountImpl;
import com.jam.tree.dao.CheckBookImpl;
import com.jam.tree.entity.Account;
import com.jam.tree.entity.GranDetailsParms;
import com.jam.tree.entity.Nodes;
import com.jam.tree.entity.TreeNode;



@Controller
public class treeController {
	
	@Autowired
	AccountImpl accountImp;
	@Autowired
	CheckBookImpl grantDetail;
	
	JsonSerializer<TreeNode> serializer = new JsonSerializer<TreeNode>() {  
	  

		@Override
		public JsonElement serialize(TreeNode src, Type typeOfSrc, JsonSerializationContext context) {
			// TODO Auto-generated method stub
			JsonObject jsonNode = new JsonObject();
			jsonNode.addProperty("id", src.id);
			return null;
		}
	};
	
	/*@Autowired
	Nodes myNode;*/
	
	@Autowired
	Tracker track;
	
	@RequestMapping(value = "/tree", method = RequestMethod.GET)
    public ModelAndView Getcommon(ModelMap model, HttpServletRequest request) throws FileNotFoundException 
	{
		/*=============retrieve grant details.*/
		GranDetailsParms parms = new GranDetailsParms();
		parms.setCoas("D");
		parms.setEndNum("50");
		parms.setStartNum("0");
		parms.setUserid("JMA337");
		parms.setGrantNum("202118");
		parms.setUserName("JMA337");
		grantDetail.getDetailPerFund(parms);
		grantDetail.getUserGrants(parms);
		
		List<Account> accounts = accountImp.listAccounts();
		
	/*===================using arraylist to make tree.===================*/
		track.clean();// clean the tracker object.
		PrintStream o = new PrintStream(new File("C:\\Users\\JAM\\Documents\\A.txt")); 
		Gson gson = new GsonBuilder().create();
		 //PrintStream console = System.out;
		 // Assign o to output stream 
	      //  System.setOut(o);
		String priorPred = "";
		Nodes root  = new Nodes();
		Nodes node = new Nodes();
		List<Nodes>tree = new ArrayList<Nodes>();
		int i=0;
		
		for (int x = 0; x<accounts.size(); x++) {
			i++;
			//step 1 --create parent;
			if (accounts.get(x).getPred() == null) {
				
				System.out.println(node.toString());
				//if ( i==1 ) {
				root = new Nodes(accounts.get(x).getAccountCode()+" - " + accounts.get(x).getTitle(),i,accounts.get(x).getAccountCode());
		    	//node =	root.findTreeNode( accounts.get(x).getAccountCode()+" - " + accounts.get(x).getTitle() );
				tree.add(root);
				priorPred = Optional.ofNullable(accounts.get(x).getPred()).orElse(""); 
				track.registerChildForSearch(root);

				continue;
				
			}else {
				
				Nodes parentNode = track.findTreeNode( accounts.get(x).getPred() );
				node = new Nodes(accounts.get(x).getAccountCode()+" - " + accounts.get(x).getTitle(),i  ,accounts.get(x).getAccountCode());
				node.setAcct(accounts.get(x).getAccountCode());
				node.setId(i);
				node.setText(  accounts.get(x).getAccountCode()+" - " + accounts.get(x).getTitle());
				parentNode.addChildNode(i, node);
				//root.addChildNode(i,parentNode);
				track.registerChildForSearch(node);
				//find parent and add child.
				continue;
			}
		
		}
		
  
		String hierTree = gson.toJson(tree);
		model.addAttribute("accounttree", hierTree);
		
		
		
		
	    
		

		 return new ModelAndView("tree", model);
   }
	
	
	public static void printTree(List<TreeNode<String>>tree) {
		
		for (TreeNode<String> node : tree) {
			//for (TreeNode<String> node: tree.)
			String indent = createIndent(node.getLevel());
			System.out.println(indent + node.data);
		}
	}

	private static String createIndent(int depth) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < depth; i++) {
			sb.append(' ');
		}
		return sb.toString();
	}

	public static void doit() {
		
	}
	
	
}
