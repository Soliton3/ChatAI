package com.example.demo.Mapper;

import com.example.demo.Entity.ClassInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ClassInfoMapper {

    @Select("select * from classinfo")
    List<ClassInfo> findAllclasssInfo();

}
