package com.example.demo.Mapper;


import com.example.demo.Entity.Student;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface StudentMapper {

    @Select("select stu.*,cls.clsName from classinfo cls join student_info stu on cls.clsid=stu.clsid")
    List<Student> findAllStudent();

    @Select("select stu.*,cls.clsName from classinfo cls join student_info stu on cls.clsid=stu.clsid where cls.clsid=#{clsid} and stu.stu_name like '%${stu_name}%'")
    List<Student> findStudentByClsidStuName(@Param("clsid") Integer clsid,@Param("stu_name")String stu_name);

    @Select("select stu.*,cls.clsName from classinfo cls join student_info stu on cls.clsid=stu.clsid where stu.stu_name like '%${stu_name}%'")
    List<Student> findStudentByStuName(@Param("stu_name")String stu_name);

    @Insert("insert into student_info(stuid,stu_image_url,stu_name,stu_sex,password,stu_phone,stu_address,stu_birthday,stu_email,stu_education,stu_interest,my_color,lucky_number,re_mark,create_date,clsid,power) values(#{stuid},#{stu_image_url},#{stu_name},#{stu_sex},#{password},#{stu_phone},#{stu_address},#{stu_birthday},#{stu_email},#{stu_education},#{stu_interest},#{my_color},#{lucky_number},#{re_mark},#{create_date},#{clsid},#{clsName})")
    Integer addStudentInfo(Student student);

    @Select("select * from student_info where stuid=#{stuid}")
    Student findStudentByid(Integer stuid);

    @Update("update student_info set stu_image_url=#{stu_image_url},stu_name=#{stu_name},stu_sex=#{stu_sex},password=#{password},stu_phone=#{stu_phone},stu_address=#{stu_address},stu_birthday=#{stu_birthday},stu_email=#{stu_email},stu_education=#{stu_education},stu_interest=#{stu_interest},my_color=#{my_color},lucky_number=#{lucky_number},re_mark=#{re_mark},clsid=#{clsid} where stuid=#{stuid}")
    Integer updateStudentByid(Student student);

    @Delete("delete from student_info where stuid=#{stuid}")
    Integer deleteStudentByid(Integer stuid);

}
