package com.wf.demo.controller;

import com.wf.demo.entity.Class;
import com.wf.demo.service.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/class")
public class ClassController {
    @Autowired
    ClassService classService;

    @RequestMapping("/allClass")
    public String list(Model model) {
        List<Class> list = classService.queryAllClass();
        model.addAttribute("list",list);
        return "allClass";
    }

    @RequestMapping("/addClass")
    public String addClass(Class _class) {
        classService.addClass(_class);
        return "redirect:/class/allClass";
    }

    @RequestMapping("/toAddClass")
    public String toAddClass() {
        return "addClass";
    }

    @RequestMapping("/deleteClassById/{classId}")
    public String deleteClassById(@PathVariable("classId")Long id) {
        classService.deleteClassById(id);
        return "redirect:/class/allClass";
    }

    @RequestMapping("/updateClass")
    public String updateClass(Model model, Class _class) {
        classService.updateClass(_class);
        _class = classService.queryById(_class.getId());
        model.addAttribute("class", _class);
        return "redirect:/class/allClass";
    }

    @RequestMapping("/toUpdateClass")
    public String toUpdateClass(Model model, Long id) {
        model.addAttribute("class",classService.queryById(id));
        return "updateClass";
    }

}
