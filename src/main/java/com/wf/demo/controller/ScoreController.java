package com.wf.demo.controller;

import com.wf.demo.entity.ClassInfo;
import com.wf.demo.entity.Course;
import com.wf.demo.entity.Score;
import com.wf.demo.entity.Student;
import com.wf.demo.entity.RankInClass;
import com.wf.demo.entity.RankInGrade;
import com.wf.demo.entity.combine.StudentScore;
import com.wf.demo.service.ClassService;
import com.wf.demo.service.CourseService;
import com.wf.demo.service.ScoreService;
import com.wf.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;
import java.util.List;

@Controller
@RequestMapping("/score")
public class ScoreController {
    @Autowired
    ScoreService scoreService;

    @Autowired
    CourseService courseService;

    @Autowired
    StudentService studentService;

    @Autowired
    ClassService classService;

    public void rankAll(Model model) {
        List<Course> courses = courseService.queryAllCourse();
        List<Student> students = studentService.queryAllStudent();
        List<ClassInfo> classInfos = classService.queryAllClass();
        for(Course course:courses) {
            for(ClassInfo classInfo:classInfos){
                List<RankInClass> rankInClassList = scoreService.getRankInClass(course.getId(), classInfo.getId());
                int rank=1;
                for(RankInClass rankInClass:rankInClassList) {
                    List<Score> scores = scoreService.queryByCourseAndClassAndScore(course.getId(),classInfo.getId(),rankInClass.getScoreNumber());
                    for(Score score:scores) {
                        score.setRankInClass(rank);
                        scoreService.updateScore(score);
                    }
                    rank++;
                }
            }
        }
        List<String> grades = classService.queryAllGrade();
        for(Course course:courses) {
            for (String grade : grades) {
                List<RankInGrade> rankInGradeList = scoreService.getRankInGrade(course.getId(),grade);
                int rank=1;
                for(RankInGrade rankInGrade:rankInGradeList) {
                    List<Score> scores = scoreService.queryByCourseAndGradeAndScore(course.getId(),grade,rankInGrade.getScoreNumber());
                    for(Score score:scores) {
                        score.setRankInGrade(rank);
                        scoreService.updateScore(score);
                    }
                    rank++;
                }

            }
        }
    }

    @RequestMapping("/allScore")
    public String queryAllScore(Model model) {
        List<Score> scores = scoreService.queryAllScore();
        List<StudentScore> list = new ArrayList<>();
        for (Score score : scores) {
            Course course = courseService.queryById(score.getCourseId());
            Student student = studentService.queryById(score.getStudentId());
            list.add(new StudentScore(student.getId(), student.getName(), course.getId(), course.getName(), score.getScoreNumber(),score.getRankInClass(),score.getRankInGrade()));
        }
        model.addAttribute("list", list);
        model.addAttribute("courses", courseService.queryAllCourse());
        model.addAttribute("gradeList", classService.queryAllGrade());
        model.addAttribute("classNumberList", classService.queryAllClassNumber());
        return "score/allScore";
    }

    @RequestMapping("/toAddScore")
    public String toAddScore(Model model, int courseId, String grade, int classNumber) {
        ClassInfo classInfo = classService.queryByGradeAndNumber(grade,classNumber);
        if(classInfo==null) {
            model.addAttribute("ErrorCode",0);
            model.addAttribute("grade",grade);
            model.addAttribute("classNumber",classInfo);
            return "score/scoreError";
        }
        List<Student> students = studentService.queryByClassId(classInfo.getId());
        List<StudentScore> list = new ArrayList<>();
        for (Student student : students) {
            Score score = scoreService.queryByCourseAndStudent(courseId,student.getId());
            if(score.getScoreNumber()<0) {
                Course course = courseService.queryById(courseId);
                list.add(new StudentScore(student.getId(), student.getName(), course.getId(), course.getName(), score.getScoreNumber(),score.getRankInClass(), score.getRankInGrade()));
            }
        }
        model.addAttribute("selectedCourse",courseService.queryById(courseId));
        model.addAttribute("selectedGrade", grade);
        model.addAttribute("selectedClassNumber", classNumber);
        model.addAttribute("courses", courseService.queryAllCourse());
        model.addAttribute("gradeList", classService.queryAllGrade());
        model.addAttribute("classNumberList", classService.queryAllClassNumber());
        model.addAttribute("list", list);
        return "score/addScore";
    }

    @RequestMapping("/addScore")
    public String addScore(Model model,Score score ) {
        scoreService.updateScore(score);
        Student student = studentService.queryById(score.getStudentId());
        ClassInfo classInfo = classService.queryById(student.getClassId());
        return this.toAddScore(model, score.getCourseId(),classInfo.getGrade(), classInfo.getClassNumber());
    }

    @RequestMapping("/queryScore")
    public String queryScore(Model model, int courseId, String grade, int classNumber){
        List<ClassInfo> classes = new ArrayList<>();
        if(classNumber==0) {
            classes = classService.queryByGrade(grade);
        }
        else {
            classes.add(classService.queryByGradeAndNumber(grade,classNumber));
        }
        if(classes.isEmpty()) {
            model.addAttribute("ErrorCode",0);
            model.addAttribute("grade",grade);
            model.addAttribute("classNumber",classNumber);
            return "score/scoreError";
        }
        List<Score> scores = new ArrayList<>();
        for(ClassInfo classInfo:classes) {
            scores.addAll(scoreService.queryByCourseAndClass(courseId,classInfo.getId()));
        }
        Collections.sort(scores, new Comparator<Score>() {
            @Override
            public int compare(Score o1, Score o2) {
                if(o1.getRankInGrade()>o2.getRankInGrade()) {
                    return 1;
                }
                else if(o1.getRankInGrade()==o2.getRankInGrade()) {
                    return 0;
                }
                else {
                    return -1;
                }
            }
        });
        //List<Score> scores= scoreService.queryByCourseAndClass(courseId,classInfo.getId());
        List<StudentScore> list = new ArrayList<>();
        for (Score score : scores) {
            Student student = studentService.queryById(score.getStudentId());
            if(score.getScoreNumber()>=0) {
                Course course = courseService.queryById(courseId);
                StudentScore studentScore=new StudentScore(student.getId(), student.getName(), course.getId(), course.getName(), score.getScoreNumber(),score.getRankInClass(), score.getRankInGrade());
                System.out.println(studentScore.toString());
                list.add(studentScore);
            }
        }
        model.addAttribute("selectedCourse",courseService.queryById(courseId));
        model.addAttribute("selectedGrade", grade);
        model.addAttribute("selectedClassNumber", classNumber);
        model.addAttribute("courses", courseService.queryAllCourse());
        model.addAttribute("gradeList", classService.queryAllGrade());
        model.addAttribute("classNumberList", classService.queryAllClassNumber());
        model.addAttribute("list", list);
        return "score/resultScore";
    }

    @RequestMapping("/updateScore")
    public String updateScore(Model model, Score score) {
        scoreService.updateScore(score);
        Student student = studentService.queryById(score.getStudentId());
        ClassInfo classInfo = classService.queryById(student.getClassId());
        return this.queryScore(model,score.getCourseId(),classInfo.getGrade(),classInfo.getClassNumber());
    }

    @RequestMapping("/deleteScore")
    public String deleteScore(Model model, @RequestParam("studentId")String studentId,@RequestParam("courseName")String courseName) {
        Course course = courseService.queryByName(courseName);
        Score score = scoreService.queryByCourseAndStudent(course.getId(),studentId);
        score.setScoreNumber(-1);
        scoreService.updateScore(score);
        ClassInfo classInfo = classService.queryById(studentService.queryById(studentId).getClassId());
        return this.queryScore(model,course.getId(),classInfo.getGrade(),classInfo.getClassNumber());
    }

}
