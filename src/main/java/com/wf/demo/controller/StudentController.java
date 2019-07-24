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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.enterprise.context.RequestScoped;
import javax.jws.WebParam;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

//    @RequestMapping("/queryByGrade")
//    public String queryByGrade(Model model, String grade) {
//        List<Class> list = classService.queryByGrade(grade);
//        model.addAttribute("class",list);
//        return "student/allStudent";
//    }

    @RequestMapping("/queryByName")
    public String queryByName(Model model, @RequestParam("name")String name) {
        List<Student> students = studentService.queryByName(name);
        List<StudentClass> list = new ArrayList<>();
        for(Student student:students) {
            Class _class = classService.queryById(student.getClassId());
            list.add(new StudentClass(student.getId(),student.getName(),student.getGender(),student.getClassId(),_class.getGrade(),_class.getClassNumber()));
        }
        model.addAttribute("list",list);
        return "student/resultStudent";
    }

    @RequestMapping("/queryByClass")
    public String queryByClass(Model model, @RequestParam("grade") String grade, @RequestParam("classNumber") int classNumber, RedirectAttributes ra) {
        Class _class = classService.queryByGradeAndNumber(grade,classNumber);
        if(_class==null) {
            model.addAttribute("grade",grade);
            model.addAttribute("classNumber",classNumber);
            model.addAttribute("ErrorCode",1);
            return "student/studentError";
        }
        ra.addAttribute("id",_class.getId());
//        List<Student> list = studentService.queryByClassId(_class.getId());
//        model.addAttribute("list",list);
//        model.addAttribute("grade",grade);
//        model.addAttribute("classNumber",classNumber);
        return "redirect:../class/classInfo";
    }

    @RequestMapping("/addStudent")
    public String addStudent(StudentClass studentClass,Model model) {
        Class _class = classService.queryByGradeAndNumber(studentClass.getGrade(),studentClass.getClassNumber());
        if(_class==null){
            model.addAttribute("grade",studentClass.getGrade());
            model.addAttribute("classNumber",studentClass.getClassNumber());
            model.addAttribute("ErrorCode",1);
            return "student/studentError";
        }
        else if(studentService.queryById(studentClass.getId())!=null) {
            model.addAttribute("ErrorCode",2);
            Student student = studentService.queryById(studentClass.getId());
            Class class1 = classService.queryById(student.getClassId());
            model.addAttribute("id",student.getId());
            model.addAttribute("name",student.getName());
            model.addAttribute("gender",student.getGender());
            model.addAttribute("grade",class1.getGrade());
            model.addAttribute("classNumber",class1.getClassNumber());
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
            model.addAttribute("ErrorCode",1);
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
        model.addAttribute("id",student.getId());
        model.addAttribute("name",student.getName());
        model.addAttribute("gender",student.getGender());
        model.addAttribute("classId",student.getClass());
        model.addAttribute("grade",_class.getGrade());
        model.addAttribute("classNumber",_class.getClassNumber());
        return "student/updateStudent";
    }
}
