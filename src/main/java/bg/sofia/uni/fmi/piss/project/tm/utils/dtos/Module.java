package bg.sofia.uni.fmi.piss.project.tm.utils.dtos;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonAlias;

public class Module {
    private String uid;

    private String title;

    @JsonAlias(value = "duration_in_minutes")
    private String durationInMinutes;

    @JsonAlias(value = "icon_url")
    private String iconUrl;

    private String summary;

    private List<String> products;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getDurationInMinutes() {
        return durationInMinutes;
    }

    public void setDurationInMinutes(String durationInMinutes) {
        this.durationInMinutes = durationInMinutes;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public List<String> getProducts() {
        return products;
    }

    public void setProducts(List<String> products) {
        this.products = products;
    }
}
