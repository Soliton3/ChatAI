package com.example.demo.Controller;

import com.example.demo.Entity.ClassInfo;
import com.example.demo.Entity.Student;
import com.example.demo.Service.ClassInfoService;
import com.example.demo.Service.StudentService;
import com.example.demo.Utils.FileUtil;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequestMapping("stu")
public class StudentController {

    @Autowired
    private StudentService studentService;
    @Autowired
    private ClassInfoService classInfoService;

//    @GetMapping("/list")
//    public String getAllInfo(Model model){
//        List<Student> studentList = studentService.findAllStudent();
//        List<ClassInfo> clist = classInfoService.findAllclasssInfo();
//        model.addAttribute("stus",studentList);
//        model.addAttribute("clsinfo",clist);
//        return "index";
//    }
    @RequestMapping("/list")
    public  String getStudentByIDName(@RequestParam(value = "pageIndex",defaultValue = "1") Integer pageIndex ,
                                      @RequestParam(value = "pageSize",defaultValue = "5") Integer pageSize ,
                                      @RequestParam(value="clsid",defaultValue = "0") Integer clsid ,
                                      @RequestParam(value="stuName",defaultValue = "") String stuName ,Model model){
        PageInfo<Student> listInfo=null;
        if(clsid==0&&stuName.isEmpty()){//无条件查询
            listInfo=studentService.findAllStudent(pageIndex,pageSize);
        }else if(clsid==0&&!stuName.isEmpty()){//只根据学生姓名模糊查询
            listInfo=studentService.findStudentByStuName(pageIndex,pageSize,stuName);
        }else{ //根据年纪编号与学生姓名模糊查询
            listInfo=studentService.findStudentByClsidStuName(pageIndex,pageSize,clsid,stuName);
        }

        List<ClassInfo> clsList = classInfoService.findAllclasssInfo();
        model.addAttribute("stus",listInfo);
        model.addAttribute("clsinfo",clsList);

        model.addAttribute("clsid",clsid);
        model.addAttribute("stuName",stuName);
        return "index";
    }

//    @PostMapping("/list")
//    public String getStudentByIDName(@RequestParam("clsid") Integer clsid,@RequestParam("stuName") String stuName,Model model){
//        List<Student> lists = null;
//        if (clsid==0 && stuName.isEmpty()){
//            lists = studentService.findAllStudent();
//        }else if (clsid==0 && !stuName.isEmpty()){
//            lists = studentService.findStudentByStuName(stuName);
//        }else {
//            lists = studentService.findStudentByClsidStuName(clsid,stuName);
//        }
//        List<ClassInfo> clist = classInfoService.findAllclasssInfo();
//        model.addAttribute("stus",lists);
//        model.addAttribute("clsinfo",clist);
//        model.addAttribute("clsid",clsid);
//        model.addAttribute("stuname",stuName);
//        return "index";
//    }

    @GetMapping("/add")
    public String addStudentInfo(Model model){
        List<ClassInfo> lists = classInfoService.findAllclasssInfo();
        model.addAttribute("clsinfo",lists);
        return "addInfo";
    }

    @PostMapping("/add")
    public String addStudentInfo(Student student, @RequestParam("filepic")MultipartFile file){
        String fileName = file.getOriginalFilename();
        String filePath = FileUtil.getUpLoadFilePath();
        fileName = System.currentTimeMillis()+fileName;

        try {
            FileUtil.uploadFile(file.getBytes(),filePath,fileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        student.setStu_image_url(fileName);
        studentService.addStudentInfo(student);
        return "redirect:/stu/list";
    }

    @GetMapping("/update/{id}")
    public String updateStudent(@PathVariable("id") Integer stuid,Model model){
        Student student = studentService.findStudentByid(stuid);
        List<ClassInfo> lists = classInfoService.findAllclasssInfo();
        model.addAttribute("clsinfo",lists);
        model.addAttribute("stu",student);
        return "updatestu";
    }

    @PostMapping("/update")
    public String updateStudent(Student student,@RequestParam("filepic")MultipartFile file){
        String fileName = file.getOriginalFilename();
        String filePath = FileUtil.getUpLoadFilePath();
        fileName = System.currentTimeMillis()+fileName;

        try {
            FileUtil.uploadFile(file.getBytes(),filePath,fileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        student.setStu_image_url(fileName);
        studentService.updateStudentByid(student);
        return "redirect:/stu/list";
    }
    @GetMapping("/delete/{id}")
    public String deleteStudent(@PathVariable("id") Integer stuid){
        studentService.deleteStudentByid(stuid);
        return "redirect:/stu/list";
    }

}
