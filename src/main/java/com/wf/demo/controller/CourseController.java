package com.wf.demo.controller;

import com.wf.demo.entity.Course;
import com.wf.demo.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/course")
public class CourseController {
    @Autowired
    CourseService courseService;

    /**
     * 课程列表
     * @param model
     * @return
     */
    @RequestMapping("/allCourse")
    public String queryAllCourse(Model model){
        List<Course> list = courseService.queryAllCourse();
        model.addAttribute("list",list);
        return "course/allCourse";
    }
}
