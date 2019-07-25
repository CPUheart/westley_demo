package com.wf.demo.controller;

import com.wf.demo.entity.ClassInfo;
import com.wf.demo.entity.Student;
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
            ClassInfo classInfo = classService.queryById(student.getClassId());
            list.add(new StudentClass(student.getId(),student.getName(),student.getGender(),student.getClassId(), classInfo.getGrade(), classInfo.getClassNumber()));
        }
        model.addAttribute("list",list);
        return "student/allStudent";
    }

//    @RequestMapping("/queryByGrade")
//    public String queryByGrade(Model model, String grade) {
//        List<ClassInfo> list = classService.queryByGrade(grade);
//        model.addAttribute("class",list);
//        return "student/allStudent";
//    }

    @RequestMapping("/queryByName")
    public String queryByName(Model model, @RequestParam("name")String name) {
        List<Student> students = studentService.queryByName(name);
        List<StudentClass> list = new ArrayList<>();
        for(Student student:students) {
            ClassInfo classInfo = classService.queryById(student.getClassId());
            list.add(new StudentClass(student.getId(),student.getName(),student.getGender(),student.getClassId(), classInfo.getGrade(), classInfo.getClassNumber()));
        }
        model.addAttribute("list",list);
        return "student/resultStudent";
    }

    @RequestMapping("/queryByClass")
    public String queryByClass(Model model, @RequestParam("grade") String grade, @RequestParam("classNumber") int classNumber, RedirectAttributes ra) {
        ClassInfo classInfo = classService.queryByGradeAndNumber(grade,classNumber);
        if(classInfo ==null) {
            model.addAttribute("grade",grade);
            model.addAttribute("classNumber",classNumber);
            model.addAttribute("ErrorCode",1);
            return "student/studentError";
        }
        ra.addAttribute("id", classInfo.getId());
//        List<Student> list = studentService.queryByClassId(classInfo.getId());
//        model.addAttribute("list",list);
//        model.addAttribute("grade",grade);
//        model.addAttribute("classNumber",classNumber);
        return "redirect:../class/classInfo";
    }

    @RequestMapping("/addStudent")
    public String addStudent(StudentClass studentClass,Model model) {
        ClassInfo classInfo = classService.queryByGradeAndNumber(studentClass.getGrade(),studentClass.getClassNumber());
        if(classInfo ==null){
            model.addAttribute("grade",studentClass.getGrade());
            model.addAttribute("classNumber",studentClass.getClassNumber());
            model.addAttribute("ErrorCode",1);
            return "student/studentError";
        }
        else if(studentService.queryById(studentClass.getId())!=null) {
            model.addAttribute("ErrorCode",2);
            Student student = studentService.queryById(studentClass.getId());
            ClassInfo classInfo1 = classService.queryById(student.getClassId());
            model.addAttribute("id",student.getId());
            model.addAttribute("name",student.getName());
            model.addAttribute("gender",student.getGender());
            model.addAttribute("grade", classInfo1.getGrade());
            model.addAttribute("classNumber", classInfo1.getClassNumber());
            return "student/studentError";
        }
        else {
            studentService.addStudent(new Student(studentClass.getId(), studentClass.getName(), studentClass.getGender(), classInfo.getId()));
            return "redirect:/student/allStudent";
        }
    }

    @RequestMapping("/toAddStudent")
    public String toAddStudent(Model model) {
       model.addAttribute("gradeList", classService.queryAllGrade());
       model.addAttribute("classNumberList", classService.queryAllClassNumber());
       model.addAttribute("message","成功");
        return "student/addStudent";
    }

    @RequestMapping("/deleteStudent/{id}")
    public String deleteStudentById(@PathVariable("id")String id) {
        studentService.deleteStudent(id);
        return "redirect:/student/allStudent";
    }

    @RequestMapping("/updateStudent")
    public String updateStudent(Model model, StudentClass studentClass) {
        ClassInfo classInfo = classService.queryByGradeAndNumber(studentClass.getGrade(),studentClass.getClassNumber());
        if(classInfo ==null){
            model.addAttribute("grade",studentClass.getGrade());
            model.addAttribute("classNumber",studentClass.getClassNumber());
            model.addAttribute("ErrorCode",1);
            return "student/studentError";
        }
        else {

            studentService.updateStudent(new Student(studentClass.getId(), studentClass.getName(), studentClass.getGender(), classInfo.getId()));
//            Student student = studentService.queryById(studentClass.getId());
//            classInfo = classService.queryById(student.getClassId());

//            model.addAttribute("studentClass", new StudentClass(student.getId(), student.getName(), student.getGender(), student.getClassId(), classInfo.getGrade(), classInfo.getClassNumber()));
            return "redirect:/student/allStudent";
        }
    }

    @RequestMapping("/toUpdateStudent")
    public String toUpdateStudent(Model model, String id) {
        model.addAttribute("gradeList", classService.queryAllGrade());
        model.addAttribute("classNumberList", classService.queryAllClassNumber());
        Student student = studentService.queryById(id);
        ClassInfo classInfo = classService.queryById(student.getClassId());
        model.addAttribute("id",student.getId());
        model.addAttribute("name",student.getName());
        model.addAttribute("gender",student.getGender());
        model.addAttribute("classId",student.getClass());
        model.addAttribute("grade", classInfo.getGrade());
        model.addAttribute("classNumber", classInfo.getClassNumber());
        return "student/updateStudent";
    }
}
