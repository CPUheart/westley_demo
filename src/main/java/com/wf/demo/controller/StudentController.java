package com.wf.demo.controller;

import com.wf.demo.entity.ClassInfo;
import com.wf.demo.entity.Course;
import com.wf.demo.entity.Score;
import com.wf.demo.entity.Student;
import com.wf.demo.entity.combine.StudentClass;
import com.wf.demo.service.ClassService;
import com.wf.demo.service.CourseService;
import com.wf.demo.service.ScoreService;
import com.wf.demo.service.StudentService;
import org.apache.ibatis.annotations.Param;
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

    @Autowired
    CourseService courseService;

    @Autowired
    ScoreService scoreService;

    /**获取学生列表
     *
     * @param model
     * @return
     */
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

    /**通过姓名查找学生
     *
     * @param model
     * @param name
     * @return
     */
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

    /**通过班级返回学生列表
     *
     * @param model
     * @param grade
     * @param classNumber
     * @param ra
     * @return
     */
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
        return "redirect:../class/classInfo";
    }

    /**添加学生
     *
     * @param studentClass
     * @param model
     * @return
     */
    @RequestMapping("/addStudent")
    public String addStudent(StudentClass studentClass,Model model) {
        ClassInfo classInfo = classService.queryByGradeAndNumber(studentClass.getGrade(),studentClass.getClassNumber());
        if(classInfo ==null) {
            model.addAttribute("grade", studentClass.getGrade());
            model.addAttribute("classNumber", studentClass.getClassNumber());
            model.addAttribute("ErrorCode", 1);
            return "student/studentError";
        } else if(studentService.queryById(studentClass.getId())!=null) {
            model.addAttribute("ErrorCode",2);
            Student student = studentService.queryById(studentClass.getId());
            ClassInfo classInfo1 = classService.queryById(student.getClassId());
            model.addAttribute("id",student.getId());
            model.addAttribute("name",student.getName());
            model.addAttribute("gender",student.getGender());
            model.addAttribute("grade", classInfo1.getGrade());
            model.addAttribute("classNumber", classInfo1.getClassNumber());
            return "student/studentError";
        } else {
            studentService.addStudent(new Student(studentClass.getId(), studentClass.getName(), studentClass.getGender(), classInfo.getId()));
            List<Course> courses = courseService.queryAllCourse();
            for (Course course:courses) {
                scoreService.insertScore(new Score(course.getId(),studentClass.getId(),-1,0,0));
            }
            return "redirect:/student/allStudent";
        }
    }

    /**准备添加学生
     *
     * @param model
     * @return
     */
    @RequestMapping("/toAddStudent")
    public String toAddStudent(Model model) {
        model.addAttribute("gradeList", classService.queryAllGrade());
        model.addAttribute("classNumberList", classService.queryAllClassNumber());
        model.addAttribute("message","成功");
        return "student/addStudent";
    }

    /**删除学生
     *
     * @param id
     * @return
     */
    @RequestMapping("/deleteStudent/{id}")
    public String deleteStudentById(@PathVariable("id")String id) {
        studentService.deleteStudent(id);
        return "redirect:/student/allStudent";
    }

    /**修改学生信息
     *
     * @param model
     * @param studentClass
     * @return
     */
    @RequestMapping("/updateStudent")
    public String updateStudent(Model model, StudentClass studentClass) {
        ClassInfo classInfo = classService.queryByGradeAndNumber(studentClass.getGrade(),studentClass.getClassNumber());
        if(classInfo ==null){
            model.addAttribute("grade",studentClass.getGrade());
            model.addAttribute("classNumber",studentClass.getClassNumber());
            model.addAttribute("ErrorCode",1);
            return "student/studentError";
        } else {
            studentService.updateStudent(new Student(studentClass.getId(), studentClass.getName(), studentClass.getGender(), classInfo.getId()));
            return "redirect:/student/allStudent";
        }
    }

    /**返回被修改的学生的原本信息
     *
     * @param model
     * @param id
     * @return
     */
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
