package co.molzol.model.flipkart;

import java.util.List;
import java.util.Map;

public class ProductBaseInfoV1 {

	private String productId;
	private String title;
	private String productDescription;
	private Map<String,String> imageUrls;
	private List<String> productFamily;
	private Price maximumRetailPrice;
	private Price flipkartSellingPrice;
	private Price flipkartSpecialPrice;
	private String productUrl;
	private String productBrand;
	private boolean inStock;
	private boolean codAvailable;
	private Double discountPercentage;
	private List<String> offers;
	private String categoryPath;
	private Attributes attributes;

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	public Map<String, String> getImageUrls() {
		return imageUrls;
	}

	public void setImageUrls(Map<String, String> imageUrls) {
		this.imageUrls = imageUrls;
	}

	public List<String> getProductFamily() {
		return productFamily;
	}

	public void setProductFamily(List<String> productFamily) {
		this.productFamily = productFamily;
	}

	public Price getMaximumRetailPrice() {
		return maximumRetailPrice;
	}

	public void setMaximumRetailPrice(Price maximumRetailPrice) {
		this.maximumRetailPrice = maximumRetailPrice;
	}

	public Price getFlipkartSellingPrice() {
		return flipkartSellingPrice;
	}

	public void setFlipkartSellingPrice(Price flipkartSellingPrice) {
		this.flipkartSellingPrice = flipkartSellingPrice;
	}

	public Price getFlipkartSpecialPrice() {
		return flipkartSpecialPrice;
	}

	public void setFlipkartSpecialPrice(Price flipkartSpecialPrice) {
		this.flipkartSpecialPrice = flipkartSpecialPrice;
	}

	public String getProductUrl() {
		return productUrl;
	}

	public void setProductUrl(String productUrl) {
		this.productUrl = productUrl;
	}

	public String getProductBrand() {
		return productBrand;
	}

	public void setProductBrand(String productBrand) {
		this.productBrand = productBrand;
	}

	public boolean isInStock() {
		return inStock;
	}

	public void setInStock(boolean inStock) {
		this.inStock = inStock;
	}

	public boolean isCodAvailable() {
		return codAvailable;
	}

	public void setCodAvailable(boolean codAvailable) {
		this.codAvailable = codAvailable;
	}

	public Double getDiscountPercentage() {
		return discountPercentage;
	}

	public void setDiscountPercentage(Double discountPercentage) {
		this.discountPercentage = discountPercentage;
	}

	public List<String> getOffers() {
		return offers;
	}

	public void setOffers(List<String> offers) {
		this.offers = offers;
	}

	public String getCategoryPath() {
		return categoryPath;
	}

	public void setCategoryPath(String categoryPath) {
		this.categoryPath = categoryPath;
	}

	public Attributes getAttributes() {
		return attributes;
	}

	public void setAttributes(Attributes attributes) {
		this.attributes = attributes;
	}
}