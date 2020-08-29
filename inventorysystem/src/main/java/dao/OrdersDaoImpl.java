package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import configuration.InventoryConfiguration;
import model.Orders;
import model.Products;

public class OrdersDaoImpl implements OrdersDao{

	static Connection con = null;
	static PreparedStatement ps = null;
	static ResultSet rs = null;
	
	public void initDB() {
		InventoryConfiguration.initPopulateDBConfiguration();
	}

	public void resetDB() {
		InventoryConfiguration.exitDBConfiguration();
	}

	
	public int createOrder(String product) {
		//ArrayList<Products> plist = new ArrayList<Products>();
		String product1= new String();
		Integer itemstock = 0;
		Integer orderCount = 0;
		try {
			Class.forName("org.sqlite.JDBC");
			con = DriverManager.getConnection("jdbc:sqlite:testdatabase.db");
			String createOrder="SELECT product, itemstock FROM products where product=?";
			
			 ps=con.prepareStatement(createOrder);
			 ps.setString(1, product);
			  rs=ps.executeQuery();
			 
			while(rs.next()) {
				//product1 = rs.getString(1);
				itemstock = rs.getInt(2);
			}
			rs.close();
			createOrder="SELECT count(*) from orders where product=?";
			 ps=con.prepareStatement(createOrder);
			 ps.setString(1, product);
			  rs=ps.executeQuery();
			 while(rs.next()) {
				//	product1 = rs.getString(1);
					orderCount = rs.getInt(1);
				}
			if (itemstock==orderCount) {
				return 0;
			}
			else {
				createOrder="INSERT INTO orders(product) values(?)";
				 ps=con.prepareStatement(createOrder);
				 ps.setString(1, product);
				  ps.executeUpdate();
				return 1;
			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		return 0;
	}

	public int deleteOrder(int orderId) {
		
				try {
					Class.forName("org.sqlite.JDBC");
					con = DriverManager.getConnection("jdbc:sqlite:testdatabase.db");
					String deleteOrder="DELETE FROM orders where orderId=?";
					
					 ps=con.prepareStatement(deleteOrder);
					 ps.setInt(1, orderId);
					  ps.executeUpdate();
					return 1;
					
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				
				return 0;
	}

	public int availableStock(String product) {
		//ArrayList<Products> plist = new ArrayList<Products>();
				String product1= new String();
				Integer itemstock = 0;
				Integer orderCount = 0;
				try {
					Class.forName("org.sqlite.JDBC");
					con = DriverManager.getConnection("jdbc:sqlite:testdatabase.db");
					String createOrder="SELECT product, itemstock FROM products where product=?";
					
					 ps=con.prepareStatement(createOrder);
					 ps.setString(1, product);
					  rs=ps.executeQuery();
					 
					while(rs.next()) {
						//product1 = rs.getString(1);
						itemstock = rs.getInt(2);
					}
					rs.close();
					createOrder="SELECT count(*) from orders where product=?";
					 ps=con.prepareStatement(createOrder);
					 ps.setString(1, product);
					  rs=ps.executeQuery();
					 while(rs.next()) {
						//	product1 = rs.getString(1);
							orderCount = rs.getInt(1);
						}
					if (itemstock<=orderCount) {
						return (itemstock-orderCount);
					}
					else {
						return 0;
					}
					
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				
				return 0;
	}

	public ArrayList<Orders> getAllOrders() {
		ArrayList<Orders> olist = new ArrayList<Orders>();
		try {
			Class.forName("org.sqlite.JDBC");
			con = DriverManager.getConnection("jdbc:sqlite:testdatabase.db");
			String getorders="SELECT * FROM orders";
			
			 ps=con.prepareStatement(getorders);
			 ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				Orders o=new Orders();
				o.setOrderId(rs.getInt(1));
				o.setProduct(rs.getString(2));
			
				olist.add(o);
			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		return olist;

	}
}
