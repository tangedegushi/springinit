package com.springboot.springinit.controller;


import com.springboot.springinit.entity.Student;
import com.springboot.springinit.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class StudentController {

    @Resource
    private StudentService studentService;

    @RequestMapping(value = "/student",method = RequestMethod.GET)
    public Student getStudentById(@RequestParam(name = "id")int id){
        //内部通过 Jackson JSON 转化json格式数据
        Student student = studentService.selectStudentById(id);
        return student;
    }

    @RequestMapping(value = "/students",method = RequestMethod.GET)
    public List<Student> getStudentByAll(){
        //内部通过 Jackson JSON 转化json格式数据
        List<Student> student = studentService.selectStudentByAll();
        return student;
    }

    @RequestMapping(value = "filterStudent")
    public List<Student> getStudentByIdAndGender(@RequestParam(name = "age")int age, @RequestParam(name = "gender")String gerder) {
        return studentService.selectStudentByIdAndGender(age, gerder);
    }

    @RequestMapping("/gogo")
    public ModelAndView gogo() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("gogo");
        modelAndView.addObject("key", 12345);
        return modelAndView;
    }

}
