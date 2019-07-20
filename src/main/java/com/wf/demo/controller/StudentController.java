package com.wf.demo.controller;

import com.wf.demo.entity.Student;
import com.wf.demo.entity.Class;
import com.wf.demo.entity.combine.StudentClass;
import com.wf.demo.service.ClassService;
import com.wf.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import sun.security.krb5.SCDynamicStoreConfig;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/student")
public class StudentController {
    @Autowired
    StudentService studentService;

    @Autowired
    ClassService classService;

    @RequestMapping("/allStudent")
    public String list(Model model) {
        List<Student> students = studentService.queryAllStudent();
        List<StudentClass> list = new ArrayList<StudentClass>();
        for (Student student:students) {
            Class _class = classService.queryById(student.getClassId());
            list.add(new StudentClass(student.getId(),student.getName(),student.getGender(),student.getClassId(),_class.getGrade(),_class.getClassNumber()));
        }
        model.addAttribute("list",list);
        return "allStudent";
    }

    @RequestMapping("/addStudent")
    public String addStudent(Student student) {
        studentService.addStudent(student);
        return "redirect:/student/allStudent";
    }

    @RequestMapping("/toAddStudent")
    public String toAddStudent() {
        return "addStudent";
    }

    @RequestMapping("/deleteStudentById/{id}")
    public String deleteStudentById(@PathVariable("id")String id) {
        studentService.deleteStudent(id);
        return "redirect:/student/allStudent";
    }

    @RequestMapping("/updateStudent")
    public String updateStudent(Model model, Student student) {
        studentService.updateStudent(student);
        student = studentService.queryById(student.getId());
        model.addAttribute("student", student);
        return "redirect:/student/allStudent";
    }

    @RequestMapping("/toUpdateStudent")
    public String toUpdateStudent(Model model, String id) {
        model.addAttribute("class",studentService.queryById(id));
        return "updateStudent";
    }



}
