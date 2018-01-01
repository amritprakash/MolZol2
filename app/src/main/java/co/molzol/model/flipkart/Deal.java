package co.molzol.model.flipkart;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hp on 14-02-2016.
 */
public class Deal {
    @Expose(serialize = true, deserialize = true)
    public String title;
    @Expose(serialize = true, deserialize = true)
    public String description;
    @Expose(serialize = true, deserialize = true)
    public String url;
    @Expose(serialize = true, deserialize = true)
    public List<ImageUrl> imageUrls = new ArrayList<ImageUrl>();
    @Expose(serialize = true, deserialize = true)
    public String availability;

}
