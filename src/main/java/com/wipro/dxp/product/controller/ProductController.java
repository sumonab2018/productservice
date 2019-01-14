package com.wipro.dxp.product.controller;



import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.ws.rs.NotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.wipro.dxp.product.models.Product;
import com.wipro.dxp.product.repository.ProductRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@Api(value="Product API", description="Product service API")
public class ProductController {

	@Autowired
	ProductRepository productRepository;

	@ApiOperation(value = "Add a product")
	@PostMapping("/products")
	public Map<String, Object> createProduct(@RequestBody Product product) {
		ProductImage productImage = new ProductImage();
    	if(product.getProductImgContent() != null) {
    		String productImageID = productImage.createImageInMongo(product);
    		System.out.println("---productImageID---"+productImageID);
    		product.setProductImgContent("");
    		product.setProductImageID(productImageID);
    	}
		product = productRepository.save(product);
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("message", "product created successfully");
		dataMap.put("status", "1");
		dataMap.put("product", product);
		return dataMap;
	}

	@ApiOperation(value = "View a list of available products",response = Iterable.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    }
    )
	@GetMapping("/products")
	public List<Product> getAllProducts(){
		List<Product> productList = productRepository.findAll();
		System.out.println("---productList---"+productList);
		List<Product> newProductList = new ArrayList<Product>();
    	if(productList != null && productList.size() > 0) {
    		ProductImage productImage = new ProductImage();
    		for(Product p:productList) {
    			try {
					productImage.fetchImageFromMongo(p);
					newProductList.add(p);
				} catch (IOException e) {
					e.printStackTrace();
				}
    		}
    	}
        return newProductList;
	}
	
	@ApiOperation(value = "Search products by category",response = Product.class)
	@GetMapping("/products/{categoryid}") 
	public List<Product> getProductsForCategory(@PathVariable String categoryid){
		List<Product> productList = productRepository.findAll();
    	List<Product> newProductList = new ArrayList<Product>();
    	if(productList != null && productList.size() > 0) {
    		ProductImage productImage = new ProductImage();
    		for(Product p:productList) {
    			try {
    				if(p.getCategoryId().equals(categoryid)) {
						productImage.fetchImageFromMongo(p);
						newProductList.add(p);
    				}
				} catch (IOException e) {
					e.printStackTrace();
				}
    		}
    	}
        return newProductList;
	}

//	@ApiOperation(value = "Search a product with an ID",response = Product.class)
//	@GetMapping("/products/{id}") 
//	public Optional<Product> getProductById(@PathVariable String id) {
//		// Product product = productRepository.findOne(id);
//		Optional<Product> product = productRepository.findById(id);
//		if (product == null) {
//            throw new NotFoundException();
//        }else {
//        	ProductImage productImage = new ProductImage();
//        	try {
//				Product tmpProduct = productImage.fetchImageFromMongo(product.get());
//				product = Optional.of(tmpProduct);
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//        }
//        return product;
//	}
	
	@ApiOperation(value = "Update a product")
	@PutMapping("/products/{id}") 
	public void updateProduct(@RequestBody Product product,@PathVariable String id) {
		System.out.println("---updateProduct----"+product.toString());
		System.out.println(productRepository.save(product));
	}
	
	@ApiOperation(value = "Delete a product")
	@DeleteMapping("/products/{id}")
	public void deleteProduct(@PathVariable String id) {
		productRepository.deleteById(id);
	}
}
