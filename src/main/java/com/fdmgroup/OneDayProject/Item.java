package com.fdmgroup.OneDayProject;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity (name = "items")
public class Item {
	
	@Id
	@Column (name = "item_id", length=8)
	private int itemID;
	@Column (length=50)
	private String itemName;
	@Column (precision=10, scale=2)
	private BigDecimal price;
	@Column (length=50)
	private String description;
	@Column (length=5)
	private int stock;
	
	
	public int getItemID() {
		return itemID;
	}
	public void setItemID(int itemID) {
		this.itemID = itemID;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	@Override
	public String toString() {
		return "Item [itemID=" + itemID + ", itemName=" + itemName + ", price=" + price + ", description=" + description
				+ ", stock=" + stock + "]";
	}
	
}
