package com.wipro.dxp.product.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import com.mongodb.DB;
import com.mongodb.Mongo;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSInputFile;
import com.wipro.dxp.product.models.Product;

public class ProductImage {


	public String createImageInMongo(Product product) {
		Mongo mongo = new Mongo("127.0.0.1", 27017);
		DB db = mongo.getDB("product");
		GridFS gfsPhoto = new GridFS(db, "photo");


		byte[] byteArray = java.util.Base64.getDecoder().decode(product.getProductImgContent());
		GridFSInputFile gfsFile =gfsPhoto.createFile(byteArray);
		gfsFile.setFilename(product.getSku());
		gfsFile.save();

		// get image file by it's filename
		GridFSDBFile imageForOutput = gfsPhoto.findOne(product.getSku());
		String imageID = imageForOutput.getId().toString();
		System.out.println("---createImageInMongo imageID-----"+imageID);
		return imageID;
	}
	public Product fetchImageFromMongo(Product product) throws IOException {
		Mongo mongo = new Mongo("127.0.0.1", 27017);
		DB db = mongo.getDB("product");
		GridFS gfsPhoto = new GridFS(db, "photo");
		GridFSDBFile imageForOutput = gfsPhoto.findOne(product.getSku());
		if(imageForOutput != null) {
			InputStream is = imageForOutput.getInputStream();
			ByteArrayOutputStream buffer = new ByteArrayOutputStream();
			int nRead;
			byte[] data = new byte[1024];
			while ((nRead = is.read(data, 0, data.length)) != -1) {
				buffer.write(data, 0, nRead);
			}

			buffer.flush();
			byte[] productImageByteArray = buffer.toByteArray();
			String encodedString = java.util.Base64.getEncoder().encodeToString(productImageByteArray);
			product.setProductImgContent(encodedString);
		}
		return product;
	}

}
