package com.jam.tree.util;

import java.util.ArrayList;


	public class JList<E> extends ArrayList<E> {
		
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@Override
	    public void add(int index, E element){
	        if(index >= 0 && index <= size()){
	            super.add(index, element);
	            return;
	        }
	        int insertNulls = index - size();
	        for(int i = 0; i < insertNulls; i++){
	            super.add(null);
	        }
	        super.add(element);
	    }
		
		
		
	}


