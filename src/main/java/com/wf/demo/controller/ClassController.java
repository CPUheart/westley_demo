package com.wf.demo.controller;

import com.wf.demo.entity.Class;
import com.wf.demo.entity.Student;
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
        Class _class = classService.queryById(id);
        List<Student> students = studentService.queryByClassId(id);
        Teacher teacher = teacherService.queryByLeadClass(id);
        int studentAmount = students.size();
        int maleAmount = 0;
        int femaleAmount =0;
        for(Student student:students) {
            if(student.getGender().equals("男"))
                maleAmount++;
            else
                femaleAmount++;
        }
        model.addAttribute("studentAmount", studentAmount);
        model.addAttribute("students", students);
        model.addAttribute("teacher", teacher);
        model.addAttribute("class",_class);
        model.addAttribute("maleAmount",maleAmount);
        model.addAttribute("femaleAmount",femaleAmount);
        return "class/classInfo";
    }

    @RequestMapping("/queryByGrade")
    public String queryByGrade(Model model, String grade) {
        List<Class> classes = classService.queryByGrade(grade);
        List<ClassTeacher> list = new ArrayList<>();
        for (Class c:classes) {
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
                Class _class = classService.queryById(teacherClass.getClassId());
                Teacher teacher1 = teacherService.queryById(teacherClassService.queryAdvisorByClassId(_class.getId()).getTeacherId());
                int studentAmount=studentService.countByClassId(_class.getId());
                list.add(new ClassTeacher(_class.getId(), _class.getGrade(), _class.getClassNumber(), teacher1.getId(), teacher1.getName(), teacher1.getGender(),studentAmount));
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
            Class _class = classService.queryById(teacherClass.getClassId());
            int studentAmount = studentService.countByClassId(_class.getId());
            list.add(new ClassTeacher(_class.getId(),_class.getGrade(),_class.getClassNumber(),teacher.getId(),teacher.getName(),teacher.getGender(),studentAmount));
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
    public String toAddClass(Model model) {
        List<Teacher> advisors = teacherService.queryAllNotAdvisor();
        model.addAttribute("advisors", advisors);
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
            teacherClassService.deleteTeacherByClassId(id);
            return "redirect:/class/allClass";
        }
    }

    @RequestMapping("/updateClass")
    public String updateClass(Model model, Class _class,String advisorId) {
        if(teacherService.queryById(advisorId)==null) {
            model.addAttribute("grade",_class.getGrade());
            model.addAttribute("classNumber", _class.getClassNumber());
            model.addAttribute("advisorId",advisorId);
            model.addAttribute("ErrorCode",3);
            return "class/classError";
        }

        int tag;    //标明修改班级信息时班主任是否保持一致，tag=1表示班主任未修改
        String originAdvisorId = teacherClassService.queryAdvisorByClassId(_class.getId()).getTeacherId();
        if(originAdvisorId.equals(advisorId)) {
            tag = 1;
        }
        else {
            tag = 0;
        }
        if( !(classService.queryById(_class.getId()).getGrade().equals(_class.getGrade()) && classService.queryById(_class.getId()).getClassNumber()==_class.getClassNumber())
                && classService.queryByGradeAndNumber(_class.getGrade(),_class.getClassNumber())!=null) {
            model.addAttribute("grade",_class.getGrade());
            model.addAttribute("classNumber", _class.getClassNumber());
            model.addAttribute("ErrorCode",1);
            return "class/classError";
        }
        if(tag==0 && teacherClassService.queryByAdvisor(advisorId)!=null) {
            model.addAttribute("grade",_class.getGrade());
            model.addAttribute("classNumber", _class.getClassNumber());
            model.addAttribute("advisorId",advisorId);
            model.addAttribute("ErrorCode",4);
            return "class/classError";
        }
        classService.updateClass(_class);
        if(tag==0) {
            teacherClassService.deleteAdvisorByClassId(_class.getId());
            teacherClassService.addTeacherClass(new TeacherClass(advisorId, _class.getId(), 1));

        }
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
