package com.fdmgroup.OneDayProject;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ShoppingCartDAO {

	@Autowired 
	private ShoppingCartRepo screp;
	
	public void addShoppingCart(ShoppingCart shoppingCart) {
		Optional<ShoppingCart> shoppingCarts = screp.findById(shoppingCart.getCartID());
		if (!shoppingCarts.isPresent()) {

			screp.save(shoppingCart);
		}
	}

	public ShoppingCart getShoppingCart(int shoppingCartId) {
		Optional<ShoppingCart> shoppingCarts = screp.findById(shoppingCartId);
		if (shoppingCarts.isPresent()) {

			return shoppingCarts.get();
		} else {
			System.out.print("No cart whith this id therefore is ");
			return null;
		}
	}

	public void removeShoppingCart(int shoppingCartId) {
		Optional<ShoppingCart> shoppingCarts = screp.findById(shoppingCartId);

		if (shoppingCarts.isPresent()) {
			screp.delete(shoppingCarts.get());
			System.out.println("shoppingCart removed");
		} else {
			System.out.println("No cart with this id");
		}

	}

	public void updateShoppingCart(ShoppingCart shoppingCart) {

		Optional<ShoppingCart> shoppingCarts = screp.findById(shoppingCart.getCartID());
		if (shoppingCarts.isPresent()) {
			screp.save(shoppingCart);
			System.out.println("cart changed");
		}
	}

	public List<ShoppingCart> listShoppingCarts() {
		List<ShoppingCart> allShoppingCarts = screp.findAll();
		return allShoppingCarts;
	}

	public ShoppingCartDAO() {

	}

}
