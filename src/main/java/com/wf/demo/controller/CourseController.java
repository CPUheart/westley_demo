package com.wf.demo.controller;

import com.sun.org.apache.xpath.internal.operations.Mod;
import com.wf.demo.entity.Course;
import com.wf.demo.service.CourseService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/course")
public class CourseController {
    @Autowired
    CourseService courseService;

    @RequestMapping("/allCourse")
    public String queryAllCourse(Model model){
        List<Course> list = courseService.queryAllCourse();
        model.addAttribute("list",list);
        return "course/allCourse";
    }

    @RequestMapping("/addCourse")
    public String addCourse(Model model, Course course){
        if(courseService.queryByName(course.getName())!=null){
            model.addAttribute("ErrorCode",0);
            model.addAttribute("name",course.getName());
            return "course/courseError";
        }
        courseService.insertCourse(course);
        return "course/addCourse";
    }

    @RequestMapping("/deleteCourse")
    public String deleteCourse(Long id) {
        courseService.deleteByCourse(id);
        return "redirect:/course/allCourse";
    }

    @RequestMapping("/updateCourse")
    public String updateCourse(Model model, Course course) {
        if(courseService.queryByName(course.getName())!=null) {
            model.addAttribute("ErrorCode",1);
            model.addAttribute("name",course.getName());
            return "course/courseError";
        }
        courseService.updateCourse(course);
        return "redirect:/course/allCourse";
    }

}