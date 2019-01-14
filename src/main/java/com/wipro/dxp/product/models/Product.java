package com.wipro.dxp.product.models;

import java.util.Arrays;

import org.joda.time.DateTime;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import io.swagger.annotations.ApiModelProperty;

@Document
public class Product {

	@Id
	@ApiModelProperty(notes = "The database generated product ID")
	private String id;
	
	@ApiModelProperty(notes = "The product name")
	private String name;
	
	@ApiModelProperty(notes = "The product SKU")
	private String sku;
	
	@ApiModelProperty(notes = "The product type")
	private String type;
	
	@ApiModelProperty(notes = "The belonging category ID of the product")
	private String categoryId;
	
	@ApiModelProperty(notes = "The product price")
	private Double price;       
	
	@ApiModelProperty(notes = "The price currency")
	private String currency;
	
	@ApiModelProperty(notes = "The product description")
	private String description;
	
	@ApiModelProperty(notes = "The product Image id")
	private String productImageID;
	
	@ApiModelProperty(notes = "The product image content")
	private String productImgContent;
	
	@CreatedDate
    @ApiModelProperty(notes = "The product creation date")
    private DateTime createdDate;
	
	@LastModifiedDate
    @ApiModelProperty(notes = "The product record modified date")
    private DateTime lastModifiedDate;
	

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", sku=" + sku + ", type=" + type + ", categoryId=" + categoryId
				+ ", price=" + price + ", currency=" + currency + ", productImageID=" + productImageID
				+ ", productImgContent=" + productImgContent + ", createdDate=" + createdDate + ", lastModifiedDate="
				+ lastModifiedDate + "]";
	}

	public String getId() {
		return id;
	}

	public Product setId(String id) {
		this.id = id;
		return this;
	}

	public String getName() {
		return name;
	}

	public Product setName(String name) {
		this.name = name;
		return this;
	}

	public String getType() {
		return type;
	}

	public Product setType(String type) {
		this.type = type;
		return this;
	}

	public Double getPrice() {
		return price;
	}

	public Product setPrice(Double price) {
		this.price = price;
		return this;
	}

	public String getCurrency() {
		return currency;
	}

	public Product setCurrency(String currency) {
		this.currency = currency;
		return this;
	}

	public String getSku() {
		return sku;
	}

	public Product setSku(String sku) {
		this.sku = sku;
		return this;
	}

	public String getProductImageID() {
		return productImageID;
	}

	public Product setProductImageID(String productImageID) {
		this.productImageID = productImageID;
		return this;
	}

	public String getProductImgContent() {
		return productImgContent;
	}

	public Product setProductImgContent(String productImgContent) {
		this.productImgContent = productImgContent;
		return this;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public Product setCategoryId(String categoryId) {
		this.categoryId = categoryId;
		return this;
	}

	public DateTime getCreatedDate() {
		return createdDate;
	}

	public Product setCreatedDate(DateTime createdDate) {
		this.createdDate = createdDate;
		return this;
	}

	public DateTime getLastModifiedDate() {
		return lastModifiedDate;
	}

	public Product setLastModifiedDate(DateTime lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
		return this;
	}

	public String getDescription() {
		return description;
	}

	public Product setDescription(String description) {
		this.description = description;
		return this;
	}

}
