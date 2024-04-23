package com.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Cart {

	
	private int quantity;
	private HashMap<Game,Integer> items=null;
	
	public Cart() {
		items=new HashMap<Game,Integer>();
		
		
	}
	public void addItem(Game g) {
		Iterator<Map.Entry<Game,Integer>>iterator=items.entrySet().iterator();
		boolean flag=false;
		while(iterator.hasNext()) {
			Map.Entry<Game,Integer>entry=iterator.next();
			Game g2=entry.getKey();
			if(g2.getTitle().equals(g.getTitle())) {
				int quantity=entry.getValue();
				quantity++;
				items.put(g2, quantity);
				flag=true;
				break;
			}
			
		}
		if(flag==false) {
			items.put(g, 1);
		}
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public HashMap<Game, Integer> getItems() {
		return items;
	}
	public void setItems(HashMap<Game, Integer> items) {
		this.items = items;
	}

	public void deleteItem(int gameId) {
		Iterator<Map.Entry<Game, Integer>> iterator=items.entrySet().iterator();
		while(iterator.hasNext()) {
			Map.Entry<Game,Integer> entry=iterator.next();
			Game g2=entry.getKey();
		   if(g2.getId()==gameId) {
			  items.remove(g2);
		   }			
		}
		
	}
}
