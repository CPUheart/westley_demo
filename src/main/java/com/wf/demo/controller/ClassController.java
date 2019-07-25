package com.wf.demo.controller;

import com.wf.demo.entity.ClassInfo;
import com.wf.demo.entity.Student;
import com.wf.demo.entity.Teacher;
import com.wf.demo.entity.TeacherClass;
import com.wf.demo.entity.combine.ClassTeacher;
import com.wf.demo.entity.combine.TeacherCourse;
import com.wf.demo.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    @RequestMapping("/allClass")
    public String list(Model model) {
        List<ClassTeacher> list = new ArrayList<>();
        List<ClassInfo> classInfos = classService.queryAllClass();
        for (ClassInfo c: classInfos) {
            TeacherClass teacherClass = teacherClassService.queryAdvisorByClassId(c.getId());
            int studentAmount = studentService.countByClassId(c.getId());
            if(teacherClass==null)
                list.add(new ClassTeacher(c.getId(),c.getGrade(),c.getClassNumber(),"#","暂无班主任","男",studentAmount));
            else {
                Teacher teacher = teacherService.queryById(teacherClass.getTeacherId());
                list.add(new ClassTeacher(c.getId(), c.getGrade(), c.getClassNumber(), teacher.getId(), teacher.getName(), teacher.getGender(),studentAmount));
            }
        }
        model.addAttribute("list",list);
        return "class/allClass";
    }

    @RequestMapping("/classInfo")
    public String classInfo(Model model, Long id) {
        ClassInfo classInfo = classService.queryById(id);
        List<Student> students = studentService.queryByClassId(id);
        Teacher advisor = teacherService.queryByLeadClass(id);
        int studentAmount = students.size();
        int maleAmount = 0;
        int femaleAmount =0;
        for(Student student:students) {
            if(student.getGender().equals("男"))
                maleAmount++;
            else
                femaleAmount++;
        }
        List<Teacher> teachers = teacherService.queryByClass(id);
        List<TeacherCourse> teacherCourse = new ArrayList<>();
        for(Teacher teacher:teachers) {
            //To Be Fix
            teacherCourse.add(new TeacherCourse(teacher.getId(),teacher.getName(),teacher.getGender(),courseService.queryById(teacherClassService.queryByTeacherAndClass(id,teacher.getId()).getCourseId()).getName()));

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

    @RequestMapping("/queryByGrade")
    public String queryByGrade(Model model, String grade) {
        List<ClassInfo> classInfos = classService.queryByGrade(grade);
        List<ClassTeacher> list = new ArrayList<>();
        for (ClassInfo c: classInfos) {
            TeacherClass teacherClass = teacherClassService.queryAdvisorByClassId(c.getId());
            int studentAmount = studentService.countByClassId(c.getId());
            if(teacherClass==null)
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

    @RequestMapping("/queryByTeacherName")
    public String queryByTeacherName(Model model,String name) {
        List<Teacher> teachers = teacherService.queryByName(name);
        List<ClassTeacher> list = new ArrayList<>();
        for (Teacher teacher:teachers) {
            List<TeacherClass> teacherClasses= teacherClassService.queryByTeacher(teacher.getId());
            for(TeacherClass teacherClass:teacherClasses) {
                ClassInfo classInfo = classService.queryById(teacherClass.getClassId());
                Teacher teacher1 = teacherService.queryById(teacherClassService.queryAdvisorByClassId(classInfo.getId()).getTeacherId());
                int studentAmount=studentService.countByClassId(classInfo.getId());
                list.add(new ClassTeacher(classInfo.getId(), classInfo.getGrade(), classInfo.getClassNumber(), teacher1.getId(), teacher1.getName(), teacher1.getGender(),studentAmount));
            }
        }
        model.addAttribute("list",list);
        model.addAttribute("teacherName", name);
        model.addAttribute("grade",null);
        return "class/resultTeacherClass";
    }

    @RequestMapping("/queryByAdvisor")
    public String queryByAdvisor(Model model, String name) {
        List<Teacher> teachers = teacherService.queryByName(name);
        List<ClassTeacher> list = new ArrayList<>();
        for (Teacher teacher:teachers) {
            TeacherClass teacherClass= teacherClassService.queryByAdvisor(teacher.getId());
            ClassInfo classInfo = classService.queryById(teacherClass.getClassId());
            int studentAmount = studentService.countByClassId(classInfo.getId());
            list.add(new ClassTeacher(classInfo.getId(), classInfo.getGrade(), classInfo.getClassNumber(),teacher.getId(),teacher.getName(),teacher.getGender(),studentAmount));
        }

        model.addAttribute("list",list);
        model.addAttribute("teacherName", name);
        model.addAttribute("grade",null);
        return "class/resultTeacherClass";
    }

//    @RequestMapping("/queryByTeacher")
//    public String queryByTeacherId(Model model,String teacherName) {
//        List<Teacher> teachers = teacherService.queryByName(teacherName);
//        for (Teacher teacher:teachers) {
//            teacherClassService.queryByTeacherId(teacher.getId());
//        }
//
//    }


    @RequestMapping("/addClass")
    public String addClass(Model model, ClassInfo classInfo, String course1, String course2, String course3,String advisorId) {
        if(classService.queryByGradeAndNumber(classInfo.getGrade(), classInfo.getClassNumber())!=null) {
            model.addAttribute("grade", classInfo.getGrade());
            model.addAttribute("classNumber", classInfo.getClassNumber());
            model.addAttribute("ErrorCode",1);
            return "class/classError";
        }
        if(teacherService.queryById(advisorId)==null) {
            model.addAttribute("grade", classInfo.getGrade());
            model.addAttribute("classNumber", classInfo.getClassNumber());
            model.addAttribute("advisorId",advisorId);
            model.addAttribute("ErrorCode",3);
            return "class/classError";
        }
        if(teacherClassService.queryByAdvisor(advisorId)!=null) {
            model.addAttribute("grade", classInfo.getGrade());
            model.addAttribute("classNumber", classInfo.getClassNumber());
            model.addAttribute("advisorId",advisorId);
            model.addAttribute("ErrorCode",4);
            return "class/classError";
        }

        classService.addClass(classInfo);
        ClassInfo c = classService.queryByGradeAndNumber(classInfo.getGrade(), classInfo.getClassNumber());
        if(!course1.equals(advisorId) && !course2.equals(advisorId) && !course3.equals(advisorId)) {
            model.addAttribute("grade", classInfo.getGrade());
            model.addAttribute("classNumber", classInfo.getClassNumber());
            model.addAttribute("ErrorCode",6);
            return "class/classError";
        }
        teacherClassService.addTeacherClass(new TeacherClass(course1, c.getId(), 1L, 0));
        teacherClassService.addTeacherClass(new TeacherClass(course2, c.getId(), 2L, 0));
        teacherClassService.addTeacherClass(new TeacherClass(course3, c.getId(), 3L, 0));

//        teacherClassService.addTeacherClass(new TeacherClass(advisorId,c.getId(),1));
        return "redirect:/class/allClass";
    }

    @RequestMapping("/toAddClass")
    public String toAddClass(Model model) {
        model.addAttribute("courses", courseService.queryAllCourse());
        model.addAttribute("advisors", teacherService.queryAllNotAdvisor());
        model.addAttribute("teachers", teacherService.queryAllTeacher());

        return "class/addClass";
    }

    @RequestMapping("/deleteClassById/{classId}")
    public String deleteClassById(@PathVariable("classId")Long id,Model model)  {
        if(studentService.queryByClassId(id).size()>0) {
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

    @RequestMapping("/updateClass")
    public String updateClass(Model model, ClassInfo classInfo, String advisorId) {
        /*if(teacherService.queryById(advisorId)==null) {
            model.addAttribute("grade", classInfo.getGrade());
            model.addAttribute("classNumber", classInfo.getClassNumber());
            model.addAttribute("advisorId",advisorId);
            model.addAttribute("ErrorCode",3);
            return "class/classError";
        }

        int tag;    //标明修改班级信息时班主任是否保持一致，tag=1表示班主任未修改
        String originAdvisorId = teacherClassService.queryAdvisorByClassId(classInfo.getId()).getTeacherId();
        if(originAdvisorId.equals(advisorId)) {
            tag = 1;
        }
        else {
            tag = 0;
        }
        if( !(classService.queryById(classInfo.getId()).getGrade().equals(classInfo.getGrade()) && classService.queryById(classInfo.getId()).getClassNumber()== classInfo.getClassNumber())
                && classService.queryByGradeAndNumber(classInfo.getGrade(), classInfo.getClassNumber())!=null) {
            model.addAttribute("grade", classInfo.getGrade());
            model.addAttribute("classNumber", classInfo.getClassNumber());
            model.addAttribute("ErrorCode",5);
            return "class/classError";
        }
        if(tag==0 && teacherClassService.queryByAdvisor(advisorId)!=null) {
            model.addAttribute("grade", classInfo.getGrade());
            model.addAttribute("classNumber", classInfo.getClassNumber());
            model.addAttribute("advisorId",advisorId);
            model.addAttribute("ErrorCode",4);
            return "class/classError";
        }
        classService.updateClass(classInfo);
        if(tag==0) {
            teacherClassService.deleteAdvisorByClassId(classInfo.getId());
            teacherClassService.addTeacherClass(new TeacherClass(advisorId, classInfo.getId(), 1));

        }*/
        return "redirect:/class/allClass";
    }

    @RequestMapping("/toUpdateClass")
    public String toUpdateClass(Model model, Long id) {
        List<Teacher> advisors = teacherService.queryAllNotAdvisor();
        model.addAttribute("advisors", advisors);
        model.addAttribute("class",classService.queryById(id));
        model.addAttribute("advisorName",teacherService.queryByLeadClass(id).getName());
        model.addAttribute("advisorId",teacherService.queryByLeadClass(id).getId());
        return "class/updateClass";
    }

}
