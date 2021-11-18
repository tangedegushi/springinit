package com.springboot.springinit.controller;


import com.springboot.springinit.entity.Student;
import com.springboot.springinit.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class HelloController {

    @Autowired
    private StudentService studentService;

    @RequestMapping("/hello")
    public Student hello(){
        //内部通过 Jackson JSON 转化json格式数据
        Student student = studentService.selectStudentById(1);
        return student;

    }

    @RequestMapping("/gogo")
    public ModelAndView gogo() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("gogo");
        modelAndView.addObject("key", 12345);
        return modelAndView;
    }

}
