package co.molzol.model.flipkart;

import com.google.gson.annotations.Expose;

public class Product {

	@Expose(serialize = false, deserialize = false)
	public ProductBaseInfoV1 productBaseInfoV1;
	@Expose(serialize = true, deserialize = true)
	public ProductShippingInfoV1 ProductShippingInfoV1;

	//@Expose(serialize = false, deserialize = false)
	public CategorySpecificInfoV1 categorySpecificInfoV1;

}