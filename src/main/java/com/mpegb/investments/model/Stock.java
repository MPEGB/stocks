package com.mpegb.investments.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by bhushan on 23/12/2017.
 */
@Entity
@Table(name="APP_STOCK")
public class Stock implements Serializable{

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    @Column(name="NAME", nullable=false)
    private String name;

    @Column(name="LASTUPDATE", nullable=false)
    private String lastUpdate;

    @Column(name="CURRENTPRICE", nullable=false)
    private Double currentPrice;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(String lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public Double getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(Double currentPrice) {
        this.currentPrice = currentPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Stock user = (Stock) o;

        if (id != null ? !id.equals(user.id) : user.id != null) return false;
        if (name != null ? !name.equals(user.name) : user.name != null) return false;
        return currentPrice != null ? currentPrice.equals(user.currentPrice) : user.currentPrice == null;
    }

    @Override
    public int hashCode() {
        int result;
        result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (currentPrice != null ? currentPrice.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", name=" + name + ", currentPrice=" + currentPrice
                + ", lastUpdate=" + lastUpdate + "]";
    }
}




