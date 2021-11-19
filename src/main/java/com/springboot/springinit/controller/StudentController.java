package com.springboot.springinit.controller;


import com.springboot.springinit.entity.Student;
import com.springboot.springinit.entity.TestBean;
import com.springboot.springinit.service.StudentService;
import com.springboot.springinit.util.RedisUtil;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class StudentController {

    @Resource
    private StudentService studentService;
    @Resource
    private RedisUtil redisUtil;
    @Resource
    private TestBean testBean;
    @Resource
    private TestBean testBean1;

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

    @GetMapping("/redis")
    public String updateRedis(@RequestParam(name = "redisKey")String redisKey, @RequestParam(name = "redisValue") String redisValue) {
        redisUtil.set(redisKey, redisValue);
        return "true";
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
