package co.molzol.model.flipkart;

public class Product {

	private ProductBaseInfoV1 productBaseInfoV1;
	private ProductShippingInfoV1 ProductShippingInfoV1;
	private CategorySpecificInfoV1 categorySpecificInfoV1;

	public ProductBaseInfoV1 getProductBaseInfoV1() {
		return productBaseInfoV1;
	}

	public void setProductBaseInfoV1(ProductBaseInfoV1 productBaseInfoV1) {
		this.productBaseInfoV1 = productBaseInfoV1;
	}

	public co.molzol.model.flipkart.ProductShippingInfoV1 getProductShippingInfoV1() {
		return ProductShippingInfoV1;
	}

	public void setProductShippingInfoV1(co.molzol.model.flipkart.ProductShippingInfoV1 productShippingInfoV1) {
		ProductShippingInfoV1 = productShippingInfoV1;
	}

	public CategorySpecificInfoV1 getCategorySpecificInfoV1() {
		return categorySpecificInfoV1;
	}

	public void setCategorySpecificInfoV1(CategorySpecificInfoV1 categorySpecificInfoV1) {
		this.categorySpecificInfoV1 = categorySpecificInfoV1;
	}
}