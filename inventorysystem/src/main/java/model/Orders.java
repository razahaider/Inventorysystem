package model;

public class Orders {

	private int orderId;
	private String product;

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	@Override
	public String toString() {
		return "order [orderId=" + orderId + ", product=" + product + "]";
	}

}
