package com.tangedegushi.model.controller;

import com.tangedegushi.model.entity.Student;
import com.tangedegushi.model.entity.TestBean;
import com.tangedegushi.model.service.StudentService;
import com.tangedegushi.model.util.RedisUtil;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

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
    @Resource
    private RabbitTemplate rabbitTemplate;

    @GetMapping("/admin/hello") // 需要匹配admin用户
    public String hello() {
        return "hello spring security";
    }

    @RequestMapping(value = "/product/student",method = RequestMethod.GET)
    public Student getStudentById(@RequestParam(name = "id")int id){
        //内部通过 Jackson JSON 转化json格式数据
        Student student = studentService.selectStudentById(id);
        System.out.println("bean value = "+testBean.toString());
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

    @RequestMapping(value = "rabbit/direct")
    public String sendDirectMessage(){
        String messageId = String.valueOf(UUID.randomUUID());
        String messageData = "test message, hello!";
        String createTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        Map<String,Object> map=new HashMap<>();
        map.put("messageId",messageId);
        map.put("messageData",messageData);
        map.put("createTime",createTime);
        //将消息携带绑定键值：TestDirectRouting 发送到交换机TestDirectExchange
        rabbitTemplate.convertAndSend("TestDirectExchange", "TestDirectRouting", map);
        return "ok";
    }

    @RequestMapping("/gogo")
    public ModelAndView gogo() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("gogo");
        modelAndView.addObject("key", 12345);
        return modelAndView;
    }

}
