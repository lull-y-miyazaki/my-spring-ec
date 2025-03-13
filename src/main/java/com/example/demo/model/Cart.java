package com.example.demo.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import com.example.demo.entity.Item;

@Component
@SessionScope
public class Cart {
	private List<Item> items = new ArrayList<>();

	// 商品リスト取得
	public List<Item> getItems() {

		return items;
	}

	// 合計金額取得
	public int getTotalPrice() {
		int total = 0;
		for (Item item : items) {
			total += item.getPrice() * item.getQuantity();
		}
		return total;
	}

	// カートに追加
	public void add(Item newItem) {
		Item existsItem = null;

		// 現在のカートの内容からIDが同じ商品を探す
		for (Item item : items) {
			if (item.getId() == newItem.getId()) {
				existsItem = item;
				break;
			}
		}

		// カート内に商品が存在しなかった場合はカート追加
		// 存在した場合は、個数の更新を行う
		if (existsItem == null) {
			items.add(newItem);
		} else {
			existsItem.setQuantity(existsItem.getQuantity() + newItem.getQuantity());
		}

	}

	// カートから商品を削除
	public void delete(int itemId) {
		// 現在のカートの内容からIDが同じ商品を探す
		for (Item item : items) {
			// 対象の商品IDが見つかった場合削除する
			if (item.getId() == itemId) {
				items.remove(item);
				break;
			}
		}
	}

	// カートの中身を全て削除
	public void clear() {
		items = new ArrayList<>();
	}

}
