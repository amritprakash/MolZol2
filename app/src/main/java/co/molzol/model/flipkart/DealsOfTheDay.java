package co.molzol.model.flipkart;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hp on 14-02-2016.
 */
public class DealsOfTheDay {
    private List<Deal> dotdList = new ArrayList<Deal>();

    public List<Deal> getDotdList() {
        return dotdList;
    }

    public void setDotdList(List<Deal> dotdList) {
        this.dotdList = dotdList;
    }
}
