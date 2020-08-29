package configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

public class InventoryConfiguration {

	static Logger logger = Logger.getLogger(InventoryConfiguration.class);

	static Connection con = null;
	static PreparedStatement ps = null;
	static ResultSet rs = null;

	@SuppressWarnings("deprecation")
	public static String initPopulateDBConfiguration() {

		try {
			Class.forName("org.sqlite.JDBC");
			con = DriverManager.getConnection("jdbc:sqlite:testdatabase.db");

			String createProducts = "CREATE TABLE products(" + " category VARCHAR(255) NOT NULL,"
					+ " company VARCHAR(255) ," + " product VARCHAR(255) NOT NULL PRIMARY KEY,"
					+ " color VARCHAR(255) , " + " description text," + " price double," + " discount double,"
					+ " itemstock int" + ")";
			String createOrders = "CREATE TABLE orders(" + " orderid INTEGER PRIMARY KEY NOT NULL  ,"
					+ " product VARCHAR(255))";

			String populateProducts = "INSERT INTO products(category, company, product, color, description, price, discount, itemstock)"
					+ " values( 'mobiles', 'apple', 'ap1', 'black','descrption', 70000, 13, 11),"
					+ "( 'mobiles', 'samsung', 'sp1', 'grey', 'descrption', 50000, 2, 2),"
					+ "( 'mobiles',' mi', 'mp1', 'black', 'descrption', 20000, 9, 35),"
					+ "( 'computer', 'intel', 'il1', 'grey', 'descrption', 67000, 0, 106),"
					+ "( 'computer', 'intel' , 'il2', 'black', 'descrption', 74000, 6, 300),"
					+ "( 'computer', 'lenovo', 'll1', 'black', 'descrption', 80000, 10, 138),"
					+ "( 'television', 'lg', 'lt1', 'black', 'descrption', 42500, 8, 62),"
					+ "( 'television', 'samsung', 'st1', 'grey', 'descrption', 58360, 16, 168);";

			ps = con.prepareStatement(createProducts);
			ps.execute();
			ps = con.prepareStatement(createOrders);
			ps.execute();

			ps = con.prepareStatement(populateProducts);
			int rowcount = ps.executeUpdate();
			System.out.println("data inserted" + rowcount);

			logger.info("Database created");

			System.out.println("Database created");

			return "success DB";
		} catch (SQLException s) {
			s.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {

				if(rs!=null)
				rs.close();
				if(con!=null)
				con.close();
				if(ps!=null)
				ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return "Database Initialisation complete";

	}

//	public static void main(String args[]) {
//		exitDBConfiguration();
//		initPopulateDBConfiguration();
//		// exitDBConfiguration();
//	}

	public static String exitDBConfiguration() {

		try {
			Class.forName("org.sqlite.JDBC");
			Connection con = DriverManager.getConnection("jdbc:sqlite:testdatabase.db");

			String dropDBExit = "DROP TABLE IF EXISTS products ;  ";
			String dropDBExit2 ="DROP TABLE IF EXISTS orders ";

			 ps = con.prepareStatement(dropDBExit);
		//	ps = con.prepareStatement(dropDBExit);
			ps.execute();

			System.out.println("Database dropped");
			// ps = con.prepareStatement(dropDBExit2);
			ps = con.prepareStatement(dropDBExit2);
			System.out.println("Database dropped");
			ps.execute();
			return "\"Database Clear complete\"";
		} catch (SQLException s) {
			s.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs!=null)
				rs.close();
				if(con!=null)
				con.close();
				if(ps!=null)
				ps.close();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return "Database Clear complete";

	}


}
