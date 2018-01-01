package co.molzol.model.flipkart;

import com.google.gson.annotations.Expose;

/**
 * Created by hp on 14-02-2016.
 */
public class ImageUrl {
    @Expose(serialize = true, deserialize = true)
    public String url;
    @Expose(serialize = true, deserialize = true)
    public String resolutionType;

}
