package co.molzol.model.flipkart;

import com.google.gson.annotations.Expose;

public class ProductShippingInfoV1 {

	@Expose(serialize = true, deserialize = true)
	public Price shippingCharges;
	@Expose(serialize = true, deserialize = true)
	public String estimatedDeliveryTime;
	@Expose(serialize = true, deserialize = true)
	public String sellerName;
	@Expose(serialize = true, deserialize = true)
	public Double sellerAverageRating;
	@Expose(serialize = true, deserialize = true)
	public int sellerNoOfRatings;
	@Expose(serialize = true, deserialize = true)
	public int sellerNoOfReviews;

}