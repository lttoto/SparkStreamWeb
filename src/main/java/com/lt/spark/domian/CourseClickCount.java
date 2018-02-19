package com.lt.spark.domian;

import org.springframework.stereotype.Component;

/**
 * Created by taoshiliu on 2018/2/20.
 */
@Component
public class CourseClickCount {

    private String name;
    private Long value;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getValue() {
        return value;
    }

    public void setValue(Long value) {
        this.value = value;
    }
}
