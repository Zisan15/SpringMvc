package com.tpe.controller;

import com.tpe.domain.Student;
import com.tpe.exception.ResourceNotFoundException;
import com.tpe.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller//bu class'ın controller olduğunu belli ettim @RestController
@RequestMapping("/students")//http://localhost:8080/SpringMvc/students
//class veya method level'da kullanabilir ==> hangi url'lerin bu class tarafından map'leneceğini belirtir
//classta kullanılırsa ==> class içindeki tüm methodlar için geçerli
//methodta kullanılırsa ==> sadece o method için mapping yapar
public class StudentController {

    @Autowired
    private StudentService service;


    //controller'dan request'e göre geriye ModelAndView(data+view name) veya
    //String data tipinde view name döndürülür.


    //sadece okuma işlemi yapar
    //@RequestMapping("/students/hi")
    @GetMapping("/hi")
    public ModelAndView sayHi() {
        ModelAndView mav = new ModelAndView();
        mav.addObject("message", "Hi; ");
        mav.addObject("messagebody", "I am a Student Management System");
        mav.setViewName("hi");//hi.jsp
        return mav;
    }

    //view resolver mav içindeki view name'e bakarak hi.jsp dosyasını bulur. mav içindeki model'ı (datayı) hi.jsp içerisine
    //yerleştirilir (bind) eder.

    //1-Student Creation(kaydetme)
    //kullanıcıdan bilgileri almak için form gösterelim, kaydetme işlemi yapabilmek için
    @GetMapping("/new")//http://localhost:8080/SpringMvc/students/new
    public String sendStudentForm(@ModelAttribute("student") Student student) {
        return "studentForm";
    }
    //@ModelAttribute annotation -> studentFormdaki bilgilerle Student tipinde bir obje oluşturur,
    //daha sonra bu objenin kullanılmasını sağlar. view ile controller arasında Data transferini sağlar

    //formun submit butonuna tıkladığımızda: /http://localhost:8080/SpringMvc/students/saveStudent, method:POST olması için
    //string döndürme sebebimiz tüm listeyi göstermek geriye bir sayfa döndürmek için
//    @PostMapping("/saveStudent")
//    public String creatStudent(@Valid @ModelAttribute Student student) {//("student") yazmaya gerek yok yine de ulaşıyor
//
//        //service'in save methodunu kullanarak kaydetme işlemini gercekleştirme
//        service.saveStudent(student);
//        return "redirect:/students";//http://localhost:8080/SpringMvc/students
//    }

    @PostMapping("/saveStudent")//http://localhost:8080/SpringMVC/students/saveStudent+POST
    public String createStudent(@Valid @ModelAttribute Student student, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "studentForm";
        }
        service.saveStudent(student);
        return "redirect:/students";//http://localhost:8080/SpringMVC/students
    }




    //tüm student'ları listeleyen request:http://localhost:8080/SpringMvc/students
    //2-read:tüm kayıtlar
    @GetMapping
    public ModelAndView listAllStudents() {
        List<Student> students = service.getAll();
        ModelAndView mav = new ModelAndView();
        mav.addObject("studentList", students);
        mav.setViewName("students");//students.jsp, hangi dosyanın içine koyacaksak onun ismini yaz
        return mav;
    }

    /*
    NOT: Eğer springboot'ta çalışıyor olsaydık update işlemi için PutMapping kullanırdık, fakat burada
    ekrana data göndereceğimiz için update de yapsak delete de yapsak GetMapping kullanırız.
     */


    //3-update:http://localhost:8080/SpringMVC/students/update?id=4
//    @GetMapping("/update")
//    public ModelAndView showFormForUpdate(@RequestParam("id") Long id) {//id sorgusuna karşılık gelecek olan değer
//
//        Student foundStudent = service.getStudentById(id);
//        ModelAndView mav = new ModelAndView();
//        mav.addObject("student", foundStudent);//studentForm'da student modelına foundStudent'ı bind etme işlemi
//        mav.setViewName("studentForm");
//        return mav;
//    }

    //update için 2.yöntem
    @GetMapping("/update")
    public String showFormForUpdate(@RequestParam("id") Long id, Model model){
        Student foundStudent=service.getStudentById(id);
        model.addAttribute("student",foundStudent);
        return "studentForm";
    }



    //4-delete:http://localhost:8080/SpringMVC/students/delete/1
    //NOT: Spring Framework'te genelde bir değişkeni dinamik olarak bir yerden alacaksak "{}" kullanırız.
    @GetMapping("/delete/{id}")
    public String deleteStudent(@PathVariable("id") Long id) {

        service.deleteStudent(id);
        return "redirect:/students";

    }

    //5-Exception Handling
    @ExceptionHandler(ResourceNotFoundException.class)
    public ModelAndView handleResourceNotFoundException(Exception ex){
        ModelAndView mav=new ModelAndView();
        mav.addObject("message",ex.getMessage());
        mav.setViewName("notFound");
        return mav;
    }
    //ExceptionHandler: belirtilen exception sınıfı için bir metod belirlememizi sağlar
    //bu metod exceptionı yakalar ve özel bir işlem(notFound.jsp gösterilmesi) uygular...

    //restful service:tüm kayıtları döndüren:http://localhost:8080/SpringMVC/students/restAll
    @GetMapping("/restAll")
    @ResponseBody//responseun doğrudan HTTPye json olarak yazılmasını sağlıyor.
    public List<Student> getAllStudents(){
        List<Student> studentList=service.getAll();
        return studentList;
    }



}
