package co.molzol.model.flipkart;

import com.google.gson.annotations.Expose;

public class Attributes {

    @Expose(serialize = true, deserialize = true)
    public String size;
    @Expose(serialize = true, deserialize = true)
    public String color;
    @Expose(serialize = true, deserialize = true)
    public String storage;
    @Expose(serialize = true, deserialize = true)
    public String sizeUnit;
    @Expose(serialize = true, deserialize = true)
    public String displaySize;

}