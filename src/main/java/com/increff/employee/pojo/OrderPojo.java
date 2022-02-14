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
    private Integer id;

    private Long time;

    private Integer complete;

    public OrderPojo() {
        this.time = new Date().getTime();
        this.complete = 0;
    }

    public Integer getComplete() {
        return complete;
    }

    public void setComplete(Integer complete) {
        this.complete = complete;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }


}
