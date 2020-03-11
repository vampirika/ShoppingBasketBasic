package com.fdmgroup.OneDayProject;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapKeyJoinColumn;
import javax.persistence.SequenceGenerator;


@Entity (name = "shoppingCart")
public class ShoppingCart {
		
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "	SHOPPINGCART_SEQ")
	@SequenceGenerator(sequenceName = "shoppingcart_seq", allocationSize = 1, name = "SHOPPINGCART_SEQ")
	@Column (name = "cart_id", length=8)
	private int cartID;
	@ElementCollection
	@CollectionTable(name="cart_items",  // name of joining table
					joinColumns=@JoinColumn(name="cart_id")) // pk of table relating to this class
	@MapKeyJoinColumn(name="item_id") // pk of class used as Map key
	@Column(name="quantity") // name of value column from Map
	// must create reference to Map interface - cannot reference concrete class
	private Map<Item,Integer> itemList = new HashMap<Item,Integer>();
	@Column
	private BigDecimal totalPrice;
	
	public int getCartID() {
		return cartID;
	}
	public void setCartID(int cartID) {
		this.cartID = cartID;
	}
	public Map<Item, Integer> getItemList() {
		return itemList;
	}
	public void setItemList(Map<Item, Integer> itemList) {
		this.itemList = itemList;
	}
	
	
	public BigDecimal getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}
	@Override
	public String toString() {
		return "ShoppingCart [cartID=" + cartID + ", itemList=" + itemList + "]";
	}
	
}
