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

import javax.jws.WebParam;
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
        List<StudentClass> list = new ArrayList<>();
        for (Student student:students) {
            Class _class = classService.queryById(student.getClassId());
            list.add(new StudentClass(student.getId(),student.getName(),student.getGender(),student.getClassId(),_class.getGrade(),_class.getClassNumber()));
        }
        model.addAttribute("list",list);
        return "student/allStudent";
    }

    @RequestMapping("/queryByClass")
    public String queryByClass(Model model, String grade, int classNumber) {
        Class _class = classService.queryByGradeAndNumber(grade,classNumber);
        if(_class==null) {
            model.addAttribute("grade",grade);
            model.addAttribute("classNumber",classNumber);
            return "student/studentError";
        }
        List<Student> list = studentService.queryByClassId(_class.getId());
        model.addAttribute("list",list);
        return "student/resultStudent";
    }

    @RequestMapping("/addStudent")
    public String addStudent(StudentClass studentClass,Model model) {
        Class _class = classService.queryByGradeAndNumber(studentClass.getGrade(),studentClass.getClassNumber());
        if(_class==null){
            model.addAttribute("grade",studentClass.getGrade());
            model.addAttribute("classNumber",studentClass.getClassNumber());
            return "student/studentError";
        }
        else {
            studentService.addStudent(new Student(studentClass.getId(), studentClass.getName(), studentClass.getGender(), _class.getId()));
            return "redirect:/student/allStudent";
        }
    }

    @RequestMapping("/toAddStudent")
    public String toAddStudent() {
        return "student/addStudent";
    }

    @RequestMapping("/deleteStudent/{id}")
    public String deleteStudentById(@PathVariable("id")String id) {
        studentService.deleteStudent(id);
        return "redirect:/student/allStudent";
    }

    @RequestMapping("/updateStudent")
    public String updateStudent(Model model, StudentClass studentClass) {
        Class _class = classService.queryByGradeAndNumber(studentClass.getGrade(),studentClass.getClassNumber());
        if(_class==null){
            model.addAttribute("grade",studentClass.getGrade());
            model.addAttribute("classNumber",studentClass.getClassNumber());
            return "student/studentError";
        }
        else {

            studentService.updateStudent(new Student(studentClass.getId(), studentClass.getName(), studentClass.getGender(), _class.getId()));
//            Student student = studentService.queryById(studentClass.getId());
//            _class = classService.queryById(student.getClassId());

//            model.addAttribute("studentClass", new StudentClass(student.getId(), student.getName(), student.getGender(), student.getClassId(), _class.getGrade(), _class.getClassNumber()));
            return "redirect:/student/allStudent";
        }
    }

    @RequestMapping("/toUpdateStudent")
    public String toUpdateStudent(Model model, String id) {
        Student student = studentService.queryById(id);
        Class _class = classService.queryById(student.getClassId());

        model.addAttribute("studentClass",new StudentClass(student.getId(),student.getName(),student.getGender(),student.getClassId(),_class.getGrade(),_class.getClassNumber()));
        return "student/updateStudent";
    }
}
