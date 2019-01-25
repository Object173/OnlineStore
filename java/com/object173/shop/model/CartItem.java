package com.object173.shop.model;

public class CartItem {
    private int id;
    private int articul;
    private String name;
    private String description;
    private float cost;
    private int count;
    private float sum;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public CartItem(Cart cart, Product product)
    {
        this.id=cart.getId();
        this.articul=product.getId();
        this.name=product.getName();
        this.description=product.getDescription();

        this.cost=product.getCost();
        this.count=cart.getCount();
        this.sum=cost*count;
    }

    public CartItem(OrderItem cart, Product product)
    {
        this.id=cart.getId();
        this.articul=product.getId();
        this.name=product.getName();
        this.description=product.getDescription();

        this.cost=product.getCost();
        this.count=cart.getCount();
        this.sum=cost*count;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getArticul() {
        return articul;
    }

    public void setArticul(int articul) {
        this.articul = articul;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public float getSum() {
        return sum;
    }

    public void setSum(float sum) {
        this.sum = sum;
    }
}
