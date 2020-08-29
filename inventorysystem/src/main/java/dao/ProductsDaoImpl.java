package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import configuration.InventoryConfiguration;
import model.Products;;

public class ProductsDaoImpl implements ProductsDao {


	static Connection con = null;
	static PreparedStatement ps = null;
	static ResultSet rs = null;
	
	public void initDB() {
		InventoryConfiguration.initPopulateDBConfiguration();
	}

	public void resetDB() {
		InventoryConfiguration.exitDBConfiguration();
	}

	
	public ArrayList<Products> getAllProducts() {
		ArrayList<Products> plist = new ArrayList<Products>();
		try {
			Class.forName("org.sqlite.JDBC");
			con = DriverManager.getConnection("jdbc:sqlite:testdatabase.db");
			String getproducts="SELECT * FROM products";
			
			 ps=con.prepareStatement(getproducts);
			 ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				Products p=new Products();
				p.setCategory(rs.getString(1));
				p.setCompany(rs.getString(2));
			
				p.setProduct(rs.getString(3));
				p.setColor(rs.getString(4));
				p.setDescription(rs.getString(5));
				p.setPrice(rs.getDouble(6));
				p.setDiscount(rs.getDouble(7));
				p.setItemStock(rs.getInt(8));
				plist.add(p);
			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		return plist;

	}
 
	
	public ArrayList<Products> getAllProductsByCategoryorPrice(String category, double price) {

		ArrayList<Products> plist = new ArrayList<Products>();
		try {
			Class.forName("org.sqlite.JDBC");
			con = DriverManager.getConnection("jdbc:sqlite:testdatabase.db");
			String getproducts="SELECT * FROM products where category=? OR price<?";
			
			 ps=con.prepareStatement(getproducts);
			 ps.setString(1, category);
			 ps.setDouble(2, price);
			 ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				Products p=new Products();
				p.setCategory(rs.getString(1));
				p.setCompany(rs.getString(2));
			
				p.setProduct(rs.getString(3));
				p.setColor(rs.getString(4));
				p.setDescription(rs.getString(5));
				p.setPrice(rs.getDouble(6));
				p.setDiscount(rs.getDouble(7));
				p.setItemStock(rs.getInt(8));
				plist.add(p);
			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		return plist;
	}

	
	public ArrayList<Products> getAllProductsByCompanyorPrice(String company, double price) {

		ArrayList<Products> plist = new ArrayList<Products>();
		try {
			Class.forName("org.sqlite.JDBC");
			con = DriverManager.getConnection("jdbc:sqlite:testdatabase.db");
			String getproducts="SELECT * FROM products where company=? OR price<?";
			
			 ps=con.prepareStatement(getproducts);
			 ps.setString(1, company);
			 ps.setDouble(2, price);
			 ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				Products p=new Products();
				p.setCategory(rs.getString(1));
				p.setCompany(rs.getString(2));
			
				p.setProduct(rs.getString(3));
				p.setColor(rs.getString(4));
				p.setDescription(rs.getString(5));
				p.setPrice(rs.getDouble(6));
				p.setDiscount(rs.getDouble(7));
				p.setItemStock(rs.getInt(8));
				plist.add(p);
			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return plist;

	}

	
	public Double getProductsDiscountedPrice(String product) {

		double disc=0.0;
		double price=0.0;
		double discountedPrice=0.0;
		try {
			Class.forName("org.sqlite.JDBC");
			con = DriverManager.getConnection("jdbc:sqlite:testdatabase.db");
			String getproducts="SELECT discount, price FROM products where product=?";
			
			 ps=con.prepareStatement(getproducts);
			 ps.setString(1, product);
			 ResultSet rs=ps.executeQuery();
			while(rs.next()) {
			      disc=rs.getDouble(1);
			      price=rs.getDouble(2);
			      
			}
			discountedPrice = price-((price*disc)/100.00);
			
			return discountedPrice;
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return discountedPrice;

	}

}
