package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "items")
public class Item {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id; // 商品ID(itemsのid)

	@Column(name = "category_id") // itemsのcategory_id
	private Integer categoryId; // カテゴリーID

	private String name; // 商品名

	private Integer price; // 価格

	@Transient // テーブルで管理しないフィールドにつけるアノテーション(Entityのみ)
	private Integer quantity; // 数量

	public Integer getId() {

		return id;
	}

	public Integer getCategoryId() {

		return categoryId;
	}

	public String getName() {

		return name;
	}

	public Integer getPrice() {

		return price;
	}

	public Integer getQuantity() {

		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

}
