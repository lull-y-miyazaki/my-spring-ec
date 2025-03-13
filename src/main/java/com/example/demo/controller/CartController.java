package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Item;
import com.example.demo.model.Cart;
import com.example.demo.repository.ItemRepository;

@Controller
public class CartController {

	@Autowired
	Cart cart;

	@Autowired
	ItemRepository itemRepository;

	// カートの内容の表示
	@GetMapping("/cart")
	public String index() {
		// カートの内容はsessionスコープ内のcartオブジェクトが保持しているので、Thymeleafから直接取得
		return "cart";
	}

	// カートに追加する処理(数量が未指定の場合は1がデフォルト)
	@PostMapping("cart/add")
	public String addCart(
			@RequestParam int itemId,
			@RequestParam(defaultValue = "1") Integer quantity) {

		// itemIdから商品情報の取得
		Item item = itemRepository.findById(itemId).get();
		// 商品オブジェクトに個数をセット
		item.setQuantity(quantity);
		cart.add(item);

		return "redirect:/cart";
	}

	// 指定した商品をカートから削除する処理
	@PostMapping("/cart/delete")
	public String deleteCart(
			@RequestParam int itemId) {

		// カートから削除
		cart.delete(itemId);

		return "redirect:/cart";
	}

}
