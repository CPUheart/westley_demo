package com.wf.demo.controller;

import com.wf.demo.entity.Course;
import com.wf.demo.entity.Score;
import com.wf.demo.entity.Student;
import com.wf.demo.entity.combine.StudentScore;
import com.wf.demo.service.CourseService;
import com.wf.demo.service.ScoreService;
import com.wf.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
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

    @RequestMapping("/allScore")
    public String queryAllScore(Model model) {
        List<Score> scores = scoreService.queryAllScore();
        List<StudentScore> list = new ArrayList<>();
        for (Score score : scores) {
            Course course = courseService.queryById(score.getCourseId());
            Student student = studentService.queryById(score.getStudentId());
            list.add(new StudentScore(student.getId(), student.getName(), course.getId(), course.getName(), score.getScoreNumber(),0, 0));
        }
        model.addAttribute("list", list);
        return "score/allScore";
    }

    @RequestMapping("/toAddScore")
    public String toAddScore() {
       return null;
    }


}
