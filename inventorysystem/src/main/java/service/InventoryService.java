package service;

import java.util.ArrayList;

import dao.OrdersDaoImpl;
import dao.ProductsDaoImpl;
import model.Products;

public class InventoryService {

	private ProductsDaoImpl prodDao = new ProductsDaoImpl();
	private OrdersDaoImpl orderDao = new OrdersDaoImpl();

	public void initialiseDB() {

		prodDao.resetDB();
		prodDao.initDB();
	}

	public InventoryService(){
		initialiseDB();
		System.out.println("hello inventoryservice");
	}
	
	public ArrayList<Products> getProductsData() {

		return prodDao.getAllProducts();
	}

	public ArrayList<Products> getProductsDataByCategoryorPrice(String product, Double price) {

		return prodDao.getAllProductsByCategoryorPrice(product, price);
	}

	public Double getProductsDiscountedPrice(String product) {

		return prodDao.getProductsDiscountedPrice(product);
	}

	public String createOrder(String product) {

		int status = orderDao.createOrder(product);

		if (status == 0) {
			return "Item out of stock";
		} else {
			return "Item successfully created";
		}
	}

	public String availableStockData(String product) {

		int status = orderDao.availableStock(product);

		if (status == 0) {
			return "Item out of stock";
		} else {
			return "Item stock available is: " + status;
		}
	}
	public String deleteOrderData(int orderId) {
		// TODO Auto-generated method stub
		int status=orderDao.deleteOrder(orderId);
		if(status==1) {
			return "Item successfully deleted";
		}
		else
		return "Item unsuccessful in delete operation";
	}
}
