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

    /**
     * 添加课程
     * @param model
     * @param course
     * @return
     */
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

    /**删除课程
     *
     * @param id
     * @return
     */
    @RequestMapping("/deleteCourse")
    public String deleteCourse(int id) {
        courseService.deleteByCourse(id);
        return "redirect:/course/allCourse";
    }

    /**更新课程
     *
     * @param model
     * @param course
     * @return
     */
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
