package dao;

import java.util.ArrayList;

import model.Products;

public interface ProductsDao {
	
	public ArrayList<Products> getAllProducts();

	public ArrayList<Products> getAllProductsByCategoryorPrice(String category, double price);

	public ArrayList<Products> getAllProductsByCompanyorPrice(String category, double price);

	public Double getProductsDiscountedPrice(String product);

}
