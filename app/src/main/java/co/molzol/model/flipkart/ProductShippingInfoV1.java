package co.molzol.model.flipkart;

public class ProductShippingInfoV1 {

	private Price shippingCharges;
	private String estimatedDeliveryTime;
	private String sellerName;
	private Double sellerAverageRating;
	private int sellerNoOfRatings;
	private int sellerNoOfReviews;

	public Price getShippingCharges() {
		return shippingCharges;
	}

	public void setShippingCharges(Price shippingCharges) {
		this.shippingCharges = shippingCharges;
	}

	public String getEstimatedDeliveryTime() {
		return estimatedDeliveryTime;
	}

	public void setEstimatedDeliveryTime(String estimatedDeliveryTime) {
		this.estimatedDeliveryTime = estimatedDeliveryTime;
	}

	public String getSellerName() {
		return sellerName;
	}

	public void setSellerName(String sellerName) {
		this.sellerName = sellerName;
	}

	public Double getSellerAverageRating() {
		return sellerAverageRating;
	}

	public void setSellerAverageRating(Double sellerAverageRating) {
		this.sellerAverageRating = sellerAverageRating;
	}

	public int getSellerNoOfRatings() {
		return sellerNoOfRatings;
	}

	public void setSellerNoOfRatings(int sellerNoOfRatings) {
		this.sellerNoOfRatings = sellerNoOfRatings;
	}

	public int getSellerNoOfReviews() {
		return sellerNoOfReviews;
	}

	public void setSellerNoOfReviews(int sellerNoOfReviews) {
		this.sellerNoOfReviews = sellerNoOfReviews;
	}
}