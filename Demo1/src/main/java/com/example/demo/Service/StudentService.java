package com.example.demo.Service;

import com.example.demo.Entity.Student;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;

public interface StudentService {
    PageInfo<Student> findAllStudent(Integer pageIndex, Integer pageSize);

    Integer addStudentInfo(Student student);

    Student findStudentByid(Integer sid);

    Integer updateStudentByid(Student student);

    Integer deleteStudentByid(Integer stuid);

    PageInfo<Student> findStudentByClsidStuName(Integer pageIndex, Integer pageSize,Integer clsid,String stu_name);

    PageInfo<Student> findStudentByStuName(Integer pageIndex, Integer pageSize, @Param("stu_name")String stu_name);
}
