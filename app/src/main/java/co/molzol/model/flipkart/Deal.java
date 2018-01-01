package co.molzol.model.flipkart;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hp on 14-02-2016.
 */
public class Deal {
    private String title;
    private String description;
    private String url;
    private List<ImageUrl> imageUrls = new ArrayList<ImageUrl>();
    private String availability;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<ImageUrl> getImageUrls() {
        return imageUrls;
    }

    public void setImageUrls(List<ImageUrl> imageUrls) {
        this.imageUrls = imageUrls;
    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }
}
