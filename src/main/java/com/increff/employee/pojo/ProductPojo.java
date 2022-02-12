package com.increff.employee.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ProductPojo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(unique = true)
    private String barcode;

    // @ManyToOne(targetEntity = BrandPojo.class)
    // @JoinColumn(name = "brand_category", referencedColumnName = "id", nullable = false)
    private Integer brand_category;
    private String name;
    private Double mrp;

    // @ManyToOne
    // @JoinColumn(name = "fk_brandPojo")
    // private BrandPojo brandPojo;

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBarcode() {
        return this.barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public Integer getBrand_category() {
        return this.brand_category;
    }

    public void setBrand_category(Integer brand_category) {
        this.brand_category = brand_category;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getMrp() {
        return this.mrp;
    }

    public void setMrp(Double mrp) {
        this.mrp = mrp;
    }



}
