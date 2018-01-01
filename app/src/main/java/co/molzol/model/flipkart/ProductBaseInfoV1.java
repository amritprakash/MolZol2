package co.molzol.model.flipkart;

import com.google.gson.annotations.Expose;

import java.util.List;
import java.util.Map;

public class ProductBaseInfoV1 {

	@Expose(serialize = true, deserialize = true)
	public String productId;
	@Expose(serialize = true, deserialize = true)
	public String title;
	@Expose(serialize = true, deserialize = true)
	public String productDescription;
	@Expose(serialize = true, deserialize = true)
	public Map<String,String> imageUrls;
	@Expose(serialize = true, deserialize = true)
	public List<String> productFamily;
	@Expose(serialize = true, deserialize = true)
	public Price maximumRetailPrice;
	@Expose(serialize = true, deserialize = true)
	public Price flipkartSellingPrice;
	@Expose(serialize = true, deserialize = true)
	public Price flipkartSpecialPrice;
	@Expose(serialize = true, deserialize = true)
	public String productUrl;
	@Expose(serialize = true, deserialize = true)
	public String productBrand;
	@Expose(serialize = true, deserialize = true)
	public boolean inStock;
	@Expose(serialize = true, deserialize = true)
	public boolean codAvailable;
	@Expose(serialize = true, deserialize = true)
	public Double discountPercentage;
	@Expose(serialize = true, deserialize = true)
	public List<String> offers;
	@Expose(serialize = true, deserialize = true)
	public String categoryPath;
	@Expose(serialize = true, deserialize = true)
	public Attributes attributes;

}