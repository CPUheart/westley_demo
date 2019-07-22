package com.wf.demo.controller;

import com.wf.demo.entity.Teacher;
import com.wf.demo.entity.TeacherClass;
import com.wf.demo.entity.combine.TeacherAdvisor;
import com.wf.demo.service.TeacherClassService;
import com.wf.demo.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/teacher")
public class TeacherController {
    @Autowired
    TeacherService teacherService;

    @Autowired
    TeacherClassService teacherClassService;

    @RequestMapping("/allTeacher")
    public String list(Model model) {
        List<Teacher> teachers = teacherService.queryAllTeacher();
        List<TeacherAdvisor> list = new ArrayList<>();
        for(Teacher teacher:teachers) {
            int advisor=0;
            if(teacherClassService.queryByAdvisor(teacher.getId())!=null) {
                advisor=1;
            }
            list.add(new TeacherAdvisor(teacher.getId(),teacher.getName(),teacher.getGender(),advisor));
        }
        model.addAttribute("list",list);
        return "teacher/allTeacher";
    }
}
