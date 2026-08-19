package com.example.JavaProject.Hostelproject.Services;

import com.example.JavaProject.Hostelproject.Dto.MobileDto;
import com.example.JavaProject.Hostelproject.Dto.StudentDto;
import com.example.JavaProject.Hostelproject.Entity.MobileEntity;
import com.example.JavaProject.Hostelproject.Entity.StudentEntity;
import com.example.JavaProject.Hostelproject.Entity.UserEntity;
import com.example.JavaProject.Hostelproject.Filter.JwtFilter;
import com.example.JavaProject.Hostelproject.RepoSitory.StudentRepository;
import com.example.JavaProject.Hostelproject.RepoSitory.UserRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service

public class StudentService {
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    JwtServices jwtServices;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ModelMapper modelMapper;
    @Transactional
    public void saveStudent(StudentDto studentDto,String token){
        try {
//            validadte accesstoken
         Long id  =    jwtServices.getUserIdvalueFromToken(token);
//         find user by id
        UserEntity users = userRepository.findById(id).orElseThrow();

//            List<MobileEntity> mobileEntityList = new ArrayList<>();
//            if (studentDto.getMobileNo() != null) {
//                mobileEntityList = studentDto.getMobileNo().stream().map(e->modelMapper.map(e, MobileEntity.class)).toList();
//
//            }
            //creating student entity
            StudentEntity studentEntity = StudentEntity.builder().address(studentDto.getAddress()).collegeName(studentDto.getCollegeName())
                    .guardianName(studentDto.getGuardianName())
                    .pgLocation(studentDto.getPgLocation()).roomNo(studentDto.getRoomNo()).users(users)
                    .build();
//

            if( studentDto.getMobileNo() != null){
                studentDto.getMobileNo().forEach(e->{
                    MobileEntity mobile = modelMapper.map(e,MobileEntity.class);
                    System.out.println(mobile.getMobileNo());
                    studentEntity.addMobileNo(mobile);
                });
            }
            StudentEntity student = studentRepository.save(studentEntity);


        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
