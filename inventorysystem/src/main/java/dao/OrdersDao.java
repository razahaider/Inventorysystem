package dao;

public interface OrdersDao {
	
	public int createOrder(String product);
	public int deleteOrder(int orderId);
	public int availableStock(String product);

}
