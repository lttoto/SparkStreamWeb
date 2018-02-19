package com.lt.spark.controller;

import com.lt.spark.dao.CourseClickCountDAO;
import com.lt.spark.domian.CourseClickCount;

import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by taoshiliu on 2018/2/20.
 */
@RestController
public class LtStatApp {

    private static Map<String,String> courses = new HashMap<>();
    static {
        courses.put("","");
    }

    @Autowired
    CourseClickCountDAO courseClickCountDAO;

    @RequestMapping(value = "/course_clickcount_dynamic",method = RequestMethod.GET)
    @ResponseBody
    public List<CourseClickCount> courseClickCount() throws Exception {

        List<CourseClickCount> list =  courseClickCountDAO.query("20180219");


        for(CourseClickCount courseClickCount : list) {
            courseClickCount.setName(courseClickCount.getName() + "123123123");
        }

        return list;
    }

    @RequestMapping(value = "/echarts",method = RequestMethod.GET)
    public ModelAndView echarts() {
        return new ModelAndView("echarts");
    }

}
