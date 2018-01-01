package co.molzol.model.flipkart;

import com.google.gson.annotations.Expose;

import java.util.List;

/**
 *
 * Created by hp on 30-12-2017.
 */

public class ProductInfoList {

    @Expose(serialize = true, deserialize = true)
    public List<Product> productInfoList;

}
