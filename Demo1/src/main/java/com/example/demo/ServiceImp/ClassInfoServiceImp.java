package com.example.demo.ServiceImp;

import com.example.demo.Entity.ClassInfo;
import com.example.demo.Mapper.ClassInfoMapper;
import com.example.demo.Service.ClassInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ClassInfoServiceImp implements ClassInfoService {

    @Autowired
    private ClassInfoMapper classInfoMapper;

    @Override
    public List<ClassInfo> findAllclasssInfo() {
        return classInfoMapper.findAllclasssInfo();
    }
}
