package com.lt.spark.dao;

import com.lt.spark.domian.CourseClickCount;
import com.lt.spark.utils.HBaseUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by taoshiliu on 2018/2/20.
 */
@Component
public class CourseClickCountDAO {

    public List<CourseClickCount> query(String day) throws Exception {
        List<CourseClickCount> list = new ArrayList<>();

        Map<String,Long> map = HBaseUtils.getInstance().query("course_clickcount_lt",day);

        for(Map.Entry<String,Long> entry : map.entrySet()) {
            //System.out.println(entry.getKey() + " : " + entry.getValue());
            CourseClickCount courseClickCount = new CourseClickCount();
            courseClickCount.setName(entry.getKey());
            courseClickCount.setValue(entry.getValue());
            list.add(courseClickCount);
        }

        return list;
    }

}
