import dao.OrdersDaoImpl;
import dao.ProductsDaoImpl;
import service.InventoryService;

public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ProductsDaoImpl prodDao = new ProductsDaoImpl();

		OrdersDaoImpl orderDao = new OrdersDaoImpl();
		InventoryService inS= new InventoryService();
		
		prodDao.resetDB();
		prodDao.initDB();
		System.out.println(prodDao.getAllProducts().get(0).getCategory());
		

		System.out.println(prodDao.getAllProductsByCategoryorPrice("mobiles", 80000));
		

		System.out.println(prodDao.getProductsDiscountedPrice("sp1"));
		

		
		orderDao.createOrder("sp1");

		orderDao.createOrder("sp1");

		orderDao.createOrder("sp1");

		orderDao.createOrder("sp1");
		//only two orders will be created
		System.out.println(orderDao.getAllOrders());

		System.out.println(orderDao.availableStock("sp1"));

		//System.out.println(orderDao.);
		
		System.out.println(inS.availableStockData("sp1"));

		System.out.println(inS.createOrder("sp1"));
		System.out.println(inS.createOrder("sp1"));
		System.out.println(inS.createOrder("sp1"));
		System.out.println(inS.createOrder("sp1"));
		System.out.println(inS.createOrder("sp1"));
	}

}
