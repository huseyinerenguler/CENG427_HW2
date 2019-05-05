package com.huseyinerenguler.ceng427_hw2.Objects;

public class ObjectAnnouncement {

    private String title;
    private String href;

    public ObjectAnnouncement(String title, String href) {
        this.title = title;
        this.href = href;
    }

    public String getTitle() {
        return title;
    }

    public String getHref() {
        return href;
    }
}