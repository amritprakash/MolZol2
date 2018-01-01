package co.molzol.model.flipkart;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hp on 14-02-2016.
 */
public class DealsOfTheDay {
    @Expose(serialize = true, deserialize = true)
    public List<Deal> dotdList = new ArrayList<Deal>();

}
