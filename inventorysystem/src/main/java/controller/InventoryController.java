package controller;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import model.Products;
import service.InventoryService;

@Path("/myinventory")
public class InventoryController {
//please use test class with main methods to test functionality(dao)

	private InventoryService inService = new InventoryService();

	@GET
	@Path("/getproducts")
	@Produces(MediaType.TEXT_PLAIN)
	public String getProducts() {

		//return "hello";
		return inService.getProductsData().toString();
	}

	@GET
	@Path("/getproductsbycategory")
	@Produces(MediaType.TEXT_PLAIN)
	public String getProductsByCategoryorPrice(@QueryParam("product") String product,
			@QueryParam("price") Double price) {

		return inService.getProductsDataByCategoryorPrice(product, price).toString();
	}

	@GET

	@Path("/products/{product}")
	@Produces(MediaType.TEXT_PLAIN)
	public String getProductsDiscountedPrice(@PathParam("product") String product) {

		return inService.getProductsDiscountedPrice(product).toString();
	}

	@PUT
	@Path("/orders/create/{product}")
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.TEXT_PLAIN)
	public String createOrder(@PathParam("product") String product) {

		String status = inService.createOrder(product);

		return status;
	}

	@GET
	@Path("/orders/{product}")
	@Produces(MediaType.TEXT_PLAIN)
	public String availableStockData(@PathParam("product") String product) {

		return inService.availableStockData(product);
	}
}
