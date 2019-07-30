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

    /**更新所有人排名
     *
     */
    public void rankAll() {
        List<Course> courses = courseService.queryAllCourse();
        List<Student> students = studentService.queryAllStudent();
        List<ClassInfo> classInfos = classService.queryAllClass();
        for(Course course:courses) {
            for(ClassInfo classInfo:classInfos){
                List<RankInClass> rankInClassList = scoreService.getRankInClass(course.getId(), classInfo.getId());
                int rank = 1;
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

    /**
     * 成绩列表
     * @param model
     * @return
     */
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

    /**将没有成绩的列表发送给页面
     *
     * @param model
     * @param courseId
     * @param grade
     * @param classNumber
     * @return
     */
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
            if(score.getScoreNumber() < 0) {
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

    /**添加成绩
     *
     * @param model
     * @param score
     * @return
     */
    @RequestMapping("/addScore")
    public String addScore(Model model,Score score ) {
        scoreService.updateScore(score);
        rankAll();
        Student student = studentService.queryById(score.getStudentId());
        ClassInfo classInfo = classService.queryById(student.getClassId());
        return this.toAddScore(model, score.getCourseId(),classInfo.getGrade(), classInfo.getClassNumber());
    }

    @RequestMapping("/queryScore")
    public String queryScore(Model model, int courseId, String grade, int classNumber){
        List<ClassInfo> classes = new ArrayList<>();
        if(classNumber == 0) {
            classes = classService.queryByGrade(grade);
        } else {
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
            List<Score> scores1 = scoreService.queryByCourseAndClass(courseId, classInfo.getId());
            if(scores1!=null) {
                scores.addAll(scores1);
            }
        }
        Collections.sort(scores, new Comparator<Score>() {
            @Override
            public int compare(Score o1, Score o2) {
                if(o1.getRankInGrade()>o2.getRankInGrade()) {
                    return 1;
                } else if(o1.getRankInGrade()==o2.getRankInGrade()) {
                    return 0;
                } else {
                    return -1;
                }
            }
        });
        List<StudentScore> list = new ArrayList<>();
        for (Score score : scores) {
            Student student = studentService.queryById(score.getStudentId());
            if(score.getScoreNumber() >= 0) {
                Course course = courseService.queryById(courseId);
                StudentScore studentScore=new StudentScore(student.getId(), student.getName(), course.getId(), course.getName(), score.getScoreNumber(),score.getRankInClass(), score.getRankInGrade());
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

    /**修改成绩
     *
     * @param model
     * @param
     * @return
     */
    @RequestMapping("/updateScore")
    public String updateScore(Model model,@RequestParam("studentId") String studentId, @RequestParam("courseId")int courseId,@RequestParam("scoreNumber")int scoreNumber) {
        System.out.println("123456");
        Course course = courseService.queryById(courseId);
        Score score = scoreService.queryByCourseAndStudent(course.getId(),studentId);
        score.setScoreNumber(scoreNumber);
        System.out.println(score.toString());
        scoreService.updateScore(score);
        rankAll();
        Student student = studentService.queryById(score.getStudentId());
        ClassInfo classInfo = classService.queryById(student.getClassId());
        return this.queryScore(model,score.getCourseId(),classInfo.getGrade(),classInfo.getClassNumber());
    }

    /**删除成绩
     *
     * @param model
     * @param studentId
     * @param courseName
     * @return
     */
    @RequestMapping("/deleteScore")
    public String deleteScore(Model model, @RequestParam("studentId")String studentId,@RequestParam("courseName")String courseName) {
        Course course = courseService.queryByName(courseName);
        Score score = scoreService.queryByCourseAndStudent(course.getId(),studentId);
        score.setScoreNumber(-1);
        scoreService.updateScore(score);
        rankAll();
        ClassInfo classInfo = classService.queryById(studentService.queryById(studentId).getClassId());
        return this.queryScore(model,course.getId(),classInfo.getGrade(),classInfo.getClassNumber());
    }

}
