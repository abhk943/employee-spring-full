package com.increff.employee.pojo;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
@SequenceGenerator(name = "port_gen", sequenceName = "port_gen", initialValue = 1000)
public class OrderPojo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "port_gen")
    private int id;

    private Long time;

    private Boolean complete;

    public OrderPojo() {
        this.time = new Date().getTime();
        this.complete = false;
    }

    public Boolean getComplete() {
        return complete;
    }

    public void setComplete(Boolean complete) {
        this.complete = complete;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }


}
