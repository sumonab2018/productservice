package com.wipro.dxp.product.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.wipro.dxp.product.models.Product;

public interface ProductRepository extends MongoRepository<Product, String>  {
	
	public List<Product> findByType(String productType);
	
	@Query("{ 'name' : ?0 }")
	public List<Product> findProductByName(String name);

}
