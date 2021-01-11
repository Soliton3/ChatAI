package com.example.demo.ServiceImp;

import com.example.demo.Entity.Student;
import com.example.demo.Mapper.StudentMapper;
import com.example.demo.Service.StudentService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImp implements StudentService {

    @Autowired
    private StudentMapper studentMapper;

    @Override
    public PageInfo<Student> findAllStudent(Integer pageIndex, Integer pageSize) {
        PageHelper.startPage(pageIndex,pageSize); //一个设置
        List<Student> lists = studentMapper.findAllStudent();
        PageInfo<Student> info =new PageInfo<Student>(lists);
        return  info;
    }

    @Override
    public Integer addStudentInfo(Student student) {
        return studentMapper.addStudentInfo(student);
    }
    @Override
    public Student findStudentByid(Integer stuid) {
        return studentMapper.findStudentByid(stuid);
    }

    @Override
    public Integer updateStudentByid(Student student) {
        return studentMapper.updateStudentByid(student);
    }

    @Override
    public Integer deleteStudentByid(Integer stuid) {
        return studentMapper.deleteStudentByid(stuid);
    }

    @Override
    public PageInfo<Student> findStudentByClsidStuName(Integer pageIndex, Integer pageSize,Integer clsid, String stu_name) {
        PageHelper.startPage(pageIndex,pageSize); //一个设置
        List<Student> lists=studentMapper.findStudentByClsidStuName(clsid,stu_name);
        PageInfo<Student> info =new PageInfo<Student>(lists);
        return info;
    }

    @Override
    public PageInfo<Student> findStudentByStuName(Integer pageIndex, Integer pageSize,String stu_name) {
        PageHelper.startPage(pageIndex,pageSize); //一个设置
        List<Student> lists=studentMapper.findStudentByStuName(stu_name);
        PageInfo<Student> info =new PageInfo<Student>(lists);
        return info;
    }
}
