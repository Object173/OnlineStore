package com.object173.shop.model;

public class OrderModel {
    private int id;
    private String username;
    private String status;
    private float cost;

    public OrderModel(int id, String username, String status, float cost)
    {
        this.id=id;
        this.username=username;
        this.status=status;
        this.cost=cost;
    }

    public OrderModel(String username, Order order)
    {
        this(order.getId(),username,order.getStatus(),order.getCost());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }
}
