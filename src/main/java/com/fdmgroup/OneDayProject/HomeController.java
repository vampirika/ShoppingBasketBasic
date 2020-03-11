package com.fdmgroup.OneDayProject;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("shoppingCart")
public class HomeController {

	@Autowired
	private ItemRepo itemrep;
	@Autowired
	private ShoppingCartDAO SCDao = new ShoppingCartDAO();

	@GetMapping("/home")
	public String doHome() {
		return "home";
	}

	@ModelAttribute(name = "shoppingCart")
	public ShoppingCart newShoppingCart() {
		return new ShoppingCart();
	}

	@GetMapping("/")
	public String doHomeWithSlash() {

		return "home";
	}

	@GetMapping("/cart")
	public String doCart(Model model, @ModelAttribute(name = "shoppingCart") ShoppingCart sc) {

		Map<Item, Integer> cartMap = sc.getItemList();
		model.addAttribute("cartMap", cartMap);
		model.addAttribute("totalPrice", sc.getTotalPrice());

		return "cart";
	}

	@GetMapping("items")
	public String addItemsToCart(Model model) {

		System.out.println("first line of add to Cart method:");
		model.addAttribute("items", itemrep.findAll());
		System.out.println("model is " + model);

		return "items";
	}

	@GetMapping(value = "/addToCart")
	public String buyItem(Model model, @ModelAttribute(name = "shoppingCart") ShoppingCart sc,
			@RequestParam String ItemID, @RequestParam String quantity) {

		model.addAttribute("items", itemrep.findAll());
		Optional<Item> item = itemrep.findById(Integer.parseInt(ItemID));

		int itemStock = item.get().getStock();
		int newItemStock = 0;
		int quantityPurchase = Integer.parseInt(quantity);

		if (quantityPurchase > itemStock) {
			return "notEnoughItems";
		} else {
			newItemStock = itemStock - quantityPurchase;
			item.get().setStock(newItemStock);
			itemrep.save(item.get());
			sc.getItemList().put(item.get(), quantityPurchase); 
			System.out.println("map is : " + sc);
			//SCDao.addShoppingCart(sc);
			//SCDao.updateShoppingCart(sc);

			BigDecimal totalPrice = BigDecimal.ZERO;
			
			Set<Map.Entry<Item, Integer>> sm = sc.getItemList().entrySet();

			for (Map.Entry<Item, Integer> s : sm) {
				BigDecimal mult =  s.getKey().getPrice().multiply(new BigDecimal(s.getValue())); // item price * quantity
                totalPrice = totalPrice.add(mult);
			}

			sc.setTotalPrice(totalPrice);
			SCDao.addShoppingCart(sc);
			SCDao.updateShoppingCart(sc);

		}

		return "items";
	}

	@GetMapping("addToCart2")
	public String buyItem2(Model model, @ModelAttribute(name = "shoppingCart") ShoppingCart sc) {
		System.out.println("cart object:" + sc);

		model.addAttribute("items", itemrep.findAll());
		SCDao.addShoppingCart(sc);
		SCDao.updateShoppingCart(sc);

		return "items";
	}

}
