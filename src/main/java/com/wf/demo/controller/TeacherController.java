package com.wf.demo.controller;

import com.wf.demo.entity.ClassInfo;
import com.wf.demo.entity.Course;
import com.wf.demo.entity.Teacher;
import com.wf.demo.entity.TeacherClass;
import com.wf.demo.entity.combine.ClassCourse;
import com.wf.demo.entity.combine.TeacherAdvisor;
import com.wf.demo.service.ClassService;
import com.wf.demo.service.CourseService;
import com.wf.demo.service.TeacherClassService;
import com.wf.demo.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    @Autowired
    CourseService courseService;

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
                list.add(new TeacherAdvisor(teacher.getId(),teacher.getName(),teacher.getGender(),advisor,0,"",0));
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
    public String toUpdateTeacher(Model model, @RequestParam("id") String teacherId) {
        model.addAttribute("teacher", teacherService.queryById(teacherId));
        return "teacher/updateTeacher";
    }

    @RequestMapping("/updateTeacher")
    public String updateTeacher(Teacher teacher) {
        teacherService.updateTeacher(teacher);
        return "redirect:/teacher/allTeacher";
    }


    @RequestMapping("/deleteTeacher/{id}")
    public String deleteTeacherById(@PathVariable("id")String teacherId, Model model)  {
        if(teacherClassService.queryByTeacher(teacherId).isEmpty() == false) {
            model.addAttribute("ErrorCode",1);
            return "teacher/teacherError";
        }
        teacherService.deleteTeacherById(teacherId);
        return "redirect:/teacher/allTeacher";
    }

    @RequestMapping("/teachInfo")
    public String teacherInfo(Model model, @RequestParam("id")String teacherId) {
        Teacher teacher = teacherService.queryById(teacherId);
        List<TeacherClass> teacherClasses = teacherClassService.queryByTeacher(teacherId);
        List<ClassCourse> list=new ArrayList<>();
        for(TeacherClass teacherClass:teacherClasses) {
            ClassInfo classInfo = classService.queryById(teacherClass.getClassId());
            Course course = courseService.queryById(teacherClass.getCourseId());
            list.add(new ClassCourse(classInfo,course));
        }
        model.addAttribute("teacherId", teacherId);
        model.addAttribute("name",teacher.getName());
        model.addAttribute("gender",teacher.getGender());
        model.addAttribute("list", list);
        return "teacher/teachInfo";
    }

}
