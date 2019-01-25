package com.object173.shop.model;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;

@Entity
@Table(name="categories")
public class Category {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "title")
    private String title;

    @Column(name = "parent_id")
    private int parent_id;

    public Category() {
    }

    public Category(int parent_id, String title) {
        this.parent_id = parent_id;
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getParent_id() {
        return parent_id;
    }

    public void setParent_id(int parent_id) {
        this.parent_id = parent_id;
    }

    public boolean isValidate()
    {
        if(title==null || title.length()<1 || id < 0) return false;
        return true;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", parent_id=" + parent_id +
                '}';
    }

    public static final String FIRST = "Каталог";
}
