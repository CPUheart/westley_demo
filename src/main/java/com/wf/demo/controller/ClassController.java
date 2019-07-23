package com.wf.demo.controller;

import com.wf.demo.entity.Class;
import com.wf.demo.entity.Teacher;
import com.wf.demo.entity.TeacherClass;
import com.wf.demo.entity.combine.ClassTeacher;
import com.wf.demo.service.ClassService;
import com.wf.demo.service.StudentService;
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

    @RequestMapping("/allClass")
    public String list(Model model) {
        List<ClassTeacher> list = new ArrayList<>();
        List<Class> classes = classService.queryAllClass();
        for (Class c:classes) {
            TeacherClass teacherClass = teacherClassService.queryAdvisorByClassId(c.getId());
            if(teacherClass==null)
                list.add(new ClassTeacher(c.getId(),c.getGrade(),c.getClassNumber(),"#","暂无班主任","男"));
            else {
                Teacher teacher = teacherService.queryById(teacherClass.getTeacherId());
                list.add(new ClassTeacher(c.getId(), c.getGrade(), c.getClassNumber(), teacher.getId(), teacher.getName(), teacher.getGender()));
            }
        }
        model.addAttribute("list",list);
        return "class/allClass";
    }

    @RequestMapping("/queryByGrade")
    public String queryByGrade(Model model, String grade) {
        List<Class> list = classService.queryByGrade(grade);
        model.addAttribute("resultList",list);
        return "class/resultClass";
    }

    @RequestMapping("/queryByTeacherName")
    public String queryByTeacherName(Model model,String name) {
        List<Teacher> teachers = teacherService.queryByName(name);
        List<ClassTeacher> list = new ArrayList<>();
        for (Teacher teacher:teachers) {
            List<TeacherClass> teacherClasses= teacherClassService.queryByTeacher(teacher.getId());
            for(TeacherClass teacherClass:teacherClasses) {
                Class _class = classService.queryById(teacherClass.getClassId());
                Teacher teacher1 = teacherService.queryById(teacherClassService.queryAdvisorByClassId(_class.getId()).getTeacherId());
                list.add(new ClassTeacher(_class.getId(), _class.getGrade(), _class.getClassNumber(), teacher1.getId(), teacher1.getName(), teacher1.getGender()));
            }
        }
        model.addAttribute("list",list);
        return "class/resultTeacherClass";
    }

    @RequestMapping("/queryByAdvisor")
    public String queryByAdvisor(Model model, String name) {
        List<Teacher> teachers = teacherService.queryByName(name);
        List<ClassTeacher> list = new ArrayList<>();
        for (Teacher teacher:teachers) {
            TeacherClass teacherClass= teacherClassService.queryByAdvisor(teacher.getId());
            Class _class = classService.queryById(teacherClass.getClassId());
            list.add(new ClassTeacher(_class.getId(),_class.getGrade(),_class.getClassNumber(),teacher.getId(),teacher.getName(),teacher.getGender()));
        }
        model.addAttribute("list",list);
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
    public String addClass(Model model, Class _class, String advisorId) {
        if(classService.queryByGradeAndNumber(_class.getGrade(),_class.getClassNumber())!=null) {
            model.addAttribute("grade",_class.getGrade());
            model.addAttribute("classNumber", _class.getClassNumber());
            model.addAttribute("ErrorCode",1);
            return "class/classError";
        }
        if(teacherService.queryById(advisorId)==null) {
            model.addAttribute("grade",_class.getGrade());
            model.addAttribute("classNumber", _class.getClassNumber());
            model.addAttribute("advisorId",advisorId);
            model.addAttribute("ErrorCode",3);
            return "class/classError";
        }
        if(teacherClassService.queryByAdvisor(advisorId)!=null) {
            model.addAttribute("grade",_class.getGrade());
            model.addAttribute("classNumber", _class.getClassNumber());
            model.addAttribute("advisorId",advisorId);
            model.addAttribute("ErrorCode",4);
            return "class/classError";
        }
        classService.addClass(_class);
        Class c = classService.queryByGradeAndNumber(_class.getGrade(),_class.getClassNumber());
        teacherClassService.addTeacherClass(new TeacherClass(advisorId,c.getId(),1));
        return "redirect:/class/allClass";
    }

    @RequestMapping("/toAddClass")
    public String toAddClass() {
        return "class/addClass";
    }

    @RequestMapping("/deleteClassById/{classId}")
    public String deleteClassById(@PathVariable("classId")Long id,Model model)  {
        if(studentService.queryByClassId(id).size()>0) {
            Class _class = classService.queryById(id);
            model.addAttribute("grade",_class.getGrade());
            model.addAttribute("classNumber",_class.getClassNumber());
            model.addAttribute("ErrorCode",2);
            return "class/classError";
        }
        else {
            classService.deleteClassById(id);
            teacherClassService.deleteByClassId(id);
            return "redirect:/class/allClass";
        }
    }

    @RequestMapping("/updateClass")
    public String updateClass(Model model, Class _class) {
        classService.updateClass(_class);
        return "redirect:/class/allClass";
    }

    @RequestMapping("/toUpdateClass")
    public String toUpdateClass(Model model, Long id) {
        model.addAttribute("class",classService.queryById(id));
        return "class/updateClass";
    }

}
