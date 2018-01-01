package co.molzol.model.flipkart;

import com.google.gson.annotations.Expose;

public class Price {

	@Expose(serialize = true, deserialize = true)
	public Double amount;
	@Expose(serialize = true, deserialize = true)
	public String currency;

}
