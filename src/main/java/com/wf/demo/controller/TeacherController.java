package com.wf.demo.controller;

import com.wf.demo.entity.ClassInfo;
import com.wf.demo.entity.Teacher;
import com.wf.demo.entity.TeacherClass;
import com.wf.demo.entity.combine.TeacherAdvisor;
import com.wf.demo.service.ClassService;
import com.wf.demo.service.TeacherClassService;
import com.wf.demo.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/teacher")
public class TeacherController {
    @Autowired
    TeacherService teacherService;

    @Autowired
    ClassService classService;

    @Autowired
    TeacherClassService teacherClassService;

    @RequestMapping("/allTeacher")
    public String list(Model model) {
        List<Teacher> teachers = teacherService.queryAllTeacher();
        List<TeacherAdvisor> list = new ArrayList<>();
        for(Teacher teacher:teachers) {
            int advisor;
            TeacherClass teacherClass = teacherClassService.queryByAdvisor(teacher.getId());
            if(teacherClass!=null) {
                advisor=1;
                ClassInfo classInfo = classService.queryById(teacherClass.getClassId());
                list.add(new TeacherAdvisor(teacher.getId(),teacher.getName(),teacher.getGender(),advisor, classInfo.getId(), classInfo.getGrade(), classInfo.getClassNumber()));
            }
            else {
                advisor=0;
                list.add(new TeacherAdvisor(teacher.getId(),teacher.getName(),teacher.getGender(),advisor,0L,"",0));
            }
        }
        model.addAttribute("list",list);
        return "teacher/allTeacher";
    }

    @RequestMapping("/toAddTeacher")
    public String toAddTeacher() {
        return "teacher/addTeacher";
    }

    @RequestMapping("/addTeacher")
    public String addTeacher(Model model, Teacher teacher) {
        if(teacherService.queryById(teacher.getId())!=null) {
            model.addAttribute("ErrorCode",0);
            model.addAttribute("id",teacher.getId());
            model.addAttribute("name",teacherService.queryById(teacher.getId()).getName());
            model.addAttribute("gender",teacherService.queryById(teacher.getId()).getGender());
            return "teacher/teacherError";
        }
        teacherService.addTeacher(teacher);
        return "redirect:/teacher/allTeacher";
    }

    @RequestMapping("/toUpdateTeacher")
    public String toUpdateTeacher(Model model, String id) {
        model.addAttribute("teacher", teacherService.queryById(id));
        return "teacher/updateTeacher";
    }

    @RequestMapping("/updateTeacher")
    public String updateTeacher(Teacher teacher) {
        teacherService.updateTeacher(teacher);
        return "redirect:/teacher/allTeacher";
    }


    @RequestMapping("/deleteTeacher/{id}")
    public String deleteClassById(@PathVariable("id")String id, Model model)  {
        teacherService.deleteTeacherById(id);
        return "redirect:/teacher/allTeacher";
    }

}
