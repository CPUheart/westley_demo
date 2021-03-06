package com.wf.demo.controller;

import com.wf.demo.entity.*;
import com.wf.demo.entity.combine.ClassTeacher;
import com.wf.demo.entity.combine.TeacherCourse;
import com.wf.demo.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/class")
public class ClassController {
    @Autowired
    ClassService classService;

    @Autowired
    StudentService studentService;

    @Autowired
    TeacherClassService teacherClassService;

    @Autowired
    TeacherService teacherService;

    @Autowired
    CourseService courseService;

    /**
     *获取班级列表
     * @param model
     * @return
     */
    @RequestMapping("/allClass")
    public String list(Model model) {
        List<ClassTeacher> list = new ArrayList<>();
        //获取班级列表
        List<ClassInfo> classInfos = classService.queryAllClass();

        for (ClassInfo c: classInfos) {
            //获取该班级的班主任
            TeacherClass teacherClass = teacherClassService.queryAdvisorByClassId(c.getId());
            //获取班级学生数量
            int studentAmount = studentService.countByClassId(c.getId());
            //判断班主任是否存在
            if(teacherClass == null)
                list.add(new ClassTeacher(c.getId(),c.getGrade(),c.getClassNumber(),"#","暂无班主任","男",studentAmount));
            else {
                Teacher teacher = teacherService.queryById(teacherClass.getTeacherId());
                list.add(new ClassTeacher(c.getId(), c.getGrade(), c.getClassNumber(), teacher.getId(), teacher.getName(), teacher.getGender(),studentAmount));
            }
        }
        model.addAttribute("list",list);
        return "class/allClass";
    }

    /**
     * 班级详情信息
     * @param model
     * @param id    班级id
     * @return
     */
    @RequestMapping("/classInfo")
    public String classInfo(Model model, int id) {
        ClassInfo classInfo = classService.queryById(id);
        List<Student> students = studentService.queryByClassId(id);
        Teacher advisor = teacherService.queryByLeadClass(id);
        int studentAmount = students.size();
        int maleAmount = 0;
        int femaleAmount = 0;
        //统计班级里的男女生人数
        for(Student student:students) {
            if(student.getGender().equals("男"))
                maleAmount++;
            else
                femaleAmount++;
        }
        List<Teacher> teachers = teacherService.queryByClass(id);
        List<TeacherCourse> teacherCourse = new ArrayList<>();

        //组合教师信息和教师的任课信息
        for(Teacher teacher:teachers) {
            teacherCourse.add(new TeacherCourse(teacher.getId(),teacher.getName(),teacher.getGender(),
                    courseService.queryById(teacherClassService.queryByTeacherAndClass(id,teacher.getId()).getCourseId()).getName()));
        }

        model.addAttribute("studentAmount", studentAmount);
        model.addAttribute("students", students);
        model.addAttribute("advisor", advisor);
        model.addAttribute("teacherCourse" ,teacherCourse);
        model.addAttribute("class", classInfo);
        model.addAttribute("maleAmount",maleAmount);
        model.addAttribute("femaleAmount",femaleAmount);
        return "class/classInfo";
    }

    /**
     * 根据年级查询班级
     * @param model
     * @param grade 年级
     * @return
     */
    @RequestMapping("/queryByGrade")
    public String queryByGrade(Model model, String grade) {
        //通过年级得到班级列表
        List<ClassInfo> classInfos = classService.queryByGrade(grade);
        List<ClassTeacher> list = new ArrayList<>();

        //通过班级列表查询班级的班主任信息
        for (ClassInfo c: classInfos) {
            TeacherClass teacherClass = teacherClassService.queryAdvisorByClassId(c.getId());
            int studentAmount = studentService.countByClassId(c.getId());
            if(teacherClass == null)
                list.add(new ClassTeacher(c.getId(),c.getGrade(),c.getClassNumber(),"#","暂无班主任","男",studentAmount));
            else {
                Teacher teacher = teacherService.queryById(teacherClass.getTeacherId());
                list.add(new ClassTeacher(c.getId(), c.getGrade(), c.getClassNumber(), teacher.getId(), teacher.getName(), teacher.getGender(),studentAmount));
            }
        }
        model.addAttribute("list",list);
        model.addAttribute("teacherName",null);
        model.addAttribute("grade",grade);
        return "class/resultTeacherClass";
    }

    /**
     * 根据教师姓名查询班级
     * @param model
     * @param name 教师姓名
     * @return
     */
    @RequestMapping("/queryByTeacherName")
    public String queryByTeacherName(Model model,String name) {
        //通过教师姓名查询教师列表（可能有重名）
        List<Teacher> teachers = teacherService.queryByName(name);
        List<ClassTeacher> list = new ArrayList<>();
        //通过教师查询教师所教授班级信息
        for (Teacher teacher:teachers) {
            List<TeacherClass> teacherClasses= teacherClassService.queryByTeacher(teacher.getId());
            for(TeacherClass teacherClass:teacherClasses) {
                ClassInfo classInfo = classService.queryById(teacherClass.getClassId());
                Teacher teacher1 = teacherService.queryById(teacherClassService.queryAdvisorByClassId(classInfo.getId()).getTeacherId());
                int studentAmount = studentService.countByClassId(classInfo.getId());
                list.add(new ClassTeacher(classInfo.getId(), classInfo.getGrade(), classInfo.getClassNumber(), teacher1.getId(), teacher1.getName(), teacher1.getGender(),studentAmount));
            }
        }
        model.addAttribute("list",list);
        model.addAttribute("teacherName", name);
        model.addAttribute("grade",null);
        return "class/resultTeacherClass";
    }

    /**
     * 根据班主任姓名查询班级
     * @param model
     * @param name 班主任姓名
     * @return
     */
    @RequestMapping("/queryByAdvisor")
    public String queryByAdvisor(Model model, String name) {
        //通过姓名查询教师
        List<Teacher> teachers = teacherService.queryByName(name);
        List<ClassTeacher> list = new ArrayList<>();
        //通过教师查询该教师担任班主任的班级信息
        for (Teacher teacher:teachers) {
            TeacherClass teacherClass= teacherClassService.queryByAdvisor(teacher.getId());
            if(teacherClass != null) {
                ClassInfo classInfo = classService.queryById(teacherClass.getClassId());
                int studentAmount = studentService.countByClassId(classInfo.getId());
                list.add(new ClassTeacher(classInfo.getId(), classInfo.getGrade(), classInfo.getClassNumber(), teacher.getId(), teacher.getName(), teacher.getGender(), studentAmount));
            }
        }

        model.addAttribute("list",list);
        model.addAttribute("teacherName", name);
        model.addAttribute("grade",null);
        return "class/resultTeacherClass";
    }

    /**
     * 添加班级
     * @param model
     * @param classInfo 班级
     * @param course1 课程1的教师编号
     * @param course2 课程2的教师编号
     * @param course3 课程3的教师编号
     * @param advisorCourse 班主任担任的课程名
     * @return
     */
    @RequestMapping("/addClass")
    public String addClass(Model model, ClassInfo classInfo, String course1, String course2, String course3, String advisorCourse) {
        String advisorId = null;
        //通过班主任教授的课程获取班主任编号
        switch (advisorCourse){
            case "1":advisorId = course1; break;
            case "2":advisorId = course2; break;
            case "3":advisorId = course3; break;
            default:break;
        }
        //查询添加的班级是否已经存在
        if(classService.queryByGradeAndNumber(classInfo.getGrade(), classInfo.getClassNumber())!=null) {
            model.addAttribute("grade", classInfo.getGrade());
            model.addAttribute("classNumber", classInfo.getClassNumber());
            model.addAttribute("ErrorCode",1);
            return "class/classError";
        }
        //判断班主任是否选择
        if("0".equals(advisorCourse)) {
            model.addAttribute("grade", classInfo.getGrade());
            model.addAttribute("classNumber", classInfo.getClassNumber());
            model.addAttribute("ErrorCode",3);
            return "class/classError";
        }
        //判断新增班级的班主任是否担任其他班级班主任职务
        if(teacherClassService.queryByAdvisor(advisorId)!=null) {
            model.addAttribute("grade", classInfo.getGrade());
            model.addAttribute("classNumber", classInfo.getClassNumber());
            model.addAttribute("advisorId",advisorId);
            model.addAttribute("ErrorCode",4);
            return "class/classError";
        }
        //判断该班级的任课教师是否重复
        if(course1.equals(course2) || course1.equals(course3) || course2.equals(course3)) {
            model.addAttribute("grade", classInfo.getGrade());
            model.addAttribute("classNumber", classInfo.getClassNumber());
            model.addAttribute("ErrorCode",7);
            return "class/classError";
        }
        //判断班主任是否在该班级任教
        if(!course1.equals(advisorId) && !course2.equals(advisorId) && !course3.equals(advisorId)) {
            model.addAttribute("grade", classInfo.getGrade());
            model.addAttribute("classNumber", classInfo.getClassNumber());
            model.addAttribute("ErrorCode",6);
            return "class/classError";
        }
        classService.addClass(classInfo);
        ClassInfo c = classService.queryByGradeAndNumber(classInfo.getGrade(), classInfo.getClassNumber());

        teacherClassService.addTeacherClass(new TeacherClass(course1, c.getId(), 1, 0));
        teacherClassService.addTeacherClass(new TeacherClass(course2, c.getId(), 2, 0));
        teacherClassService.addTeacherClass(new TeacherClass(course3, c.getId(), 3, 0));
        teacherClassService.setAdvisorByClassAndTeacher(c.getId(),advisorId);
        return "redirect:/class/allClass";
    }

    /**
     * 准备添加班级，将添加班级需要的信息传给页面
     * @param model
     * @return
     */
    @RequestMapping("/toAddClass")
    public String toAddClass(Model model) {
        model.addAttribute("courses", courseService.queryAllCourse());
        model.addAttribute("advisors", teacherService.queryAllNotAdvisor());
        model.addAttribute("teachers", teacherService.queryAllTeacher());
        return "class/addClass";
    }

    /**
     * 删除班级
     * @param model
     * @param id 班级编号
     * @return
     */
    @RequestMapping(value = "/deleteClassById/{classId}",method = {RequestMethod.GET, RequestMethod.POST})
    public String deleteClassById(Model model, @PathVariable("classId")int id)  {
        //判断该班级是否有学生，如果还有学生，不能删除
        if(studentService.queryByClassId(id).size() > 0) {
            ClassInfo classInfo = classService.queryById(id);
            model.addAttribute("grade", classInfo.getGrade());
            model.addAttribute("classNumber", classInfo.getClassNumber());
            model.addAttribute("ErrorCode",2);
            return "class/classError";
        }
        else {
            classService.deleteClassById(id);
            teacherClassService.deleteTeacherByClassId(id);
            return "redirect:/class/allClass";
        }
    }

    /**
     * 修改班级信息
     * @param model
     * @param classInfo 班级
     * @param course1   课程1教师编号
     * @param course2   课程2教师编号
     * @param course3   课程3教师编号
     * @param advisorCourse 班主任教授课程
     * @return
     */
    @RequestMapping("/updateClass")
    public String updateClass(Model model, ClassInfo classInfo,  String course1, String course2, String course3, String advisorCourse) {
        String advisorId=null;
        //通过班主任教授的课程获取班主任编号
        switch (advisorCourse){
            case "1":advisorId=course1; break;
            case "2":advisorId=course2; break;
            case "3":advisorId=course3; break;
            default:break;
        }

        //判断修改后的班级是否已经存在
        ClassInfo classInfo1 =  classService.queryByGradeAndNumber(classInfo.getGrade(), classInfo.getClassNumber());
        if(classInfo1!=null && classInfo.getId()!=classInfo1.getId()) {
            model.addAttribute("grade", classInfo.getGrade());
            model.addAttribute("classNumber", classInfo.getClassNumber());
            model.addAttribute("ErrorCode",1);
            return "class/classError";
        }
        //判断修改后的班主任是否担任其他班级的班主任职位
        TeacherClass teacherClass = teacherClassService.queryByAdvisor(advisorId);
        if(teacherClass!=null && teacherClass.getClassId()!=classInfo.getId()) {
            model.addAttribute("grade", classInfo.getGrade());
            model.addAttribute("classNumber", classInfo.getClassNumber());
            model.addAttribute("advisorId",advisorId);
            model.addAttribute("ErrorCode",4);
            return "class/classError";
        }

        //判断该班级是否有任课教师重复
        if(course1.equals(course2) || course1.equals(course3) || course2.equals(course3)) {
            model.addAttribute("grade", classInfo.getGrade());
            model.addAttribute("classNumber", classInfo.getClassNumber());
            model.addAttribute("ErrorCode",7);
            return "class/classError";
        }

        //判断班主任是否在任课教师中
        if(!course1.equals(advisorId) && !course2.equals(advisorId) && !course3.equals(advisorId)) {
            model.addAttribute("grade", classInfo.getGrade());
            model.addAttribute("classNumber", classInfo.getClassNumber());
            model.addAttribute("ErrorCode",6);
            return "class/classError";
        }
        //添加班级和任课信息
        classService.updateClass(classInfo);
        ClassInfo c = classService.queryByGradeAndNumber(classInfo.getGrade(), classInfo.getClassNumber());
        teacherClassService.updateTeacherClass(new TeacherClass(course1, c.getId(), 1, 0));
        teacherClassService.updateTeacherClass(new TeacherClass(course2, c.getId(), 2, 0));
        teacherClassService.updateTeacherClass(new TeacherClass(course3, c.getId(), 3, 0));
        teacherClassService.updateAdvisorByClassAndTeacher(c.getId(),advisorId);
        return "redirect:/class/allClass";
    }

    /**
     * 准备修改班级，将要修改班级的详情信息传给页面
     * @param model
     * @param id 班级编号
     * @return
     */
    @RequestMapping("/toUpdateClass")
    public String toUpdateClass(Model model, int id) {
        List<Teacher> advisors = teacherService.queryAllNotAdvisor();
        List<Course> courses = courseService.queryAllCourse();
        //将课程列表、班主任列表、班级信息、班主任姓名、id、班主任教授课程、教师列表发送给前端
        model.addAttribute("courses", courses);
        model.addAttribute("advisors", advisors);
        model.addAttribute("class",classService.queryById(id));
        model.addAttribute("advisorName",teacherService.queryByLeadClass(id).getName());
        model.addAttribute("advisorId",teacherService.queryByLeadClass(id).getId());
        model.addAttribute("advisorCourse", teacherClassService.queryAdvisorByClassId(id).getCourseId());
        model.addAttribute("teachers",teacherService.queryAllTeacher());
        for(Course course:courses) {
            model.addAttribute("teacher"+course.getId(),teacherClassService.queryByClassAndCourse(id,course.getId()));
        }
        return "class/updateClass";
    }

}
