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

    /**教师列表
     *
     * @param model
     * @return
     */
    @RequestMapping("/allTeacher")
    public String list(Model model) {
        List<Teacher> teachers = teacherService.queryAllTeacher();
        List<TeacherAdvisor> list = new ArrayList<>();

        //根据教师查询教授课程信息以及担任班主任的班级信息
        for(Teacher teacher:teachers) {
            int advisor;
            TeacherClass teacherClass = teacherClassService.queryByAdvisor(teacher.getId());
            if(teacherClass!=null) {
                //该教师担任班主任
                advisor=1;
                ClassInfo classInfo = classService.queryById(teacherClass.getClassId());
                list.add(new TeacherAdvisor(teacher.getId(),teacher.getName(),teacher.getGender(),advisor, classInfo.getId(), classInfo.getGrade(), classInfo.getClassNumber()));
            } else {
                //该教师未担任班主任
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

    /**添加教师
     *
     * @param model
     * @param teacher 教师对象
     * @return
     */
    @RequestMapping("/addTeacher")
    public String addTeacher(Model model, Teacher teacher) {
        //判断教师编号是否存在
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

    /**返回需要修改的教师信息
     *
     * @param model
     * @param teacherId 教师编号
     * @return
     */
    @RequestMapping("/toUpdateTeacher")
    public String toUpdateTeacher(Model model, @RequestParam("id") String teacherId) {
        model.addAttribute("teacher", teacherService.queryById(teacherId));
        return "teacher/updateTeacher";
    }

    /**修改教师信息
     *
     * @param teacher 教师对象
     * @return
     */
    @RequestMapping("/updateTeacher")
    public String updateTeacher(Teacher teacher) {
        teacherService.updateTeacher(teacher);
        return "redirect:/teacher/allTeacher";
    }

    /**删除教师
     *
     * @param teacherId 教师编号
     * @param model
     * @return
     */
    @RequestMapping("/deleteTeacher/{id}")
    public String deleteTeacherById(@PathVariable("id")String teacherId, Model model)  {
        //判断该教师是否在某个班级任课，如果该教师还在担任某个班级的任课任务，不能删除
        if(teacherClassService.queryByTeacher(teacherId).isEmpty() == false) {
            model.addAttribute("ErrorCode",1);
            return "teacher/teacherError";
        }
        teacherService.deleteTeacherById(teacherId);
        return "redirect:/teacher/allTeacher";
    }

    /**教师详细信息
     *
     * @param model
     * @param teacherId
     * @return
     */
    @RequestMapping("/teachInfo")
    public String teacherInfo(Model model, @RequestParam("id")String teacherId) {
        Teacher teacher = teacherService.queryById(teacherId);
        List<TeacherClass> teacherClasses = teacherClassService.queryByTeacher(teacherId);
        List<ClassCourse> list=new ArrayList<>();
        //根据该教师任课信息获取该教师教授课程以及教授班级的详细信息
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
