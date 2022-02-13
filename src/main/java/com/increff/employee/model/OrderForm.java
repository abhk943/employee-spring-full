package com.increff.employee.model;


public class OrderForm {

    private Integer id;
    private Long time;
    private Boolean complete;

    public Integer getId() {
        return id;
    }

    public Boolean getComplete() {
        return complete;
    }

    public void setComplete(Boolean complete) {
        this.complete = complete;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long date) {
        this.time = date;
    }

}
