package com.example.demo.Service;

import com.example.demo.Entity.ClassInfo;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ClassInfoService {
    List<ClassInfo> findAllclasssInfo();
}
