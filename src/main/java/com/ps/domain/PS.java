package com.ps.domain;

import com.items.domain.Item;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.*;


@Document(collection = "ps")
public class PS {

    @Id
    private String id;
    public String userId;
    public Map<String, Double> quantity = new HashMap<String, Double>();
    public Date date;
    public List<Item> items = new ArrayList<Item>();
    public String status;

    public PS() {
    }

    public PS(String id, String userId, Map<String, Double> quantity, Date date, List<Item> items, String status) {
        this.id = id;
        this.userId = userId;
        this.quantity = quantity;
        this.date = new Date();
        this.items = items;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Map<String, Double> getQuantity() {
        return quantity;
    }

    public void setQuantity(Map<String, Double> quantity) {
        this.quantity = quantity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
