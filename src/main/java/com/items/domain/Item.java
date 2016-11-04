package com.items.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


@Document(collection = "items")
public class Item {

    @Id
    private String id;
    private String name;
    private String groupId;
    private String shortDescription;
    private String description;
    private Double price;
    private boolean archive;
    private Date date;

    public List<String> fileIds = new ArrayList<String>();

    public List<Map<String, String>> comments = new ArrayList<Map<String, String>>();

    public Item() {
    }

    public Item(String id) {
        this.id = id;
    }

    public Item(String id, String name, String groupId, String shortDescription, String description, Double price, boolean archive, Date date, List<String> fileIds, List<Map<String, String>> comments) {
        this.id = id;
        this.name = name;
        this.groupId = groupId;
        this.shortDescription = shortDescription;
        this.description = description;
        this.price = price;
        this.archive = archive;
        this.date = date;
        this.fileIds = fileIds;
        this.comments = comments;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public boolean isArchive() {
        return archive;
    }

    public void setArchive(boolean archive) {
        this.archive = archive;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<String> getFileIds() {
        return fileIds;
    }

    public void setFileIds(List<String> fileIds) {
        this.fileIds = fileIds;
    }

    public List<Map<String, String>> getComments() {
        return comments;
    }

    public void setComments(List<Map<String, String>> comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", groupId=" + groupId +
                ", shortDescription='" + shortDescription + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", archive=" + archive +
                ", date=" + date +
                ", fileIds=" + fileIds +
                '}';
    }
}
