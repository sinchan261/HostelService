package com.example.JavaProject.Hostelproject.RepoSitory;


import com.example.JavaProject.Hostelproject.Dto.SignUpDto;
import com.example.JavaProject.Hostelproject.Dto.UserDto;
import com.example.JavaProject.Hostelproject.Enum.UserTypeEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.JavaProject.Hostelproject.Entity.UserEntity;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity,Long> {
    public Optional<UserEntity>  findByEmail(String username);
    @Query("""
            SELECT new  com.example.JavaProject.Hostelproject.Dto.UserDto(
            u.id, u.name,u.email,u.userRole,u.createdTimeStamp) FROM UserEntity u
            """)
    public List<UserDto> findAllUsersAsDto();
}
