package com.example.JavaProject.Hostelproject.RepoSitory;


import org.springframework.data.jpa.repository.JpaRepository;

import com.example.JavaProject.Hostelproject.Entity.SessionEntity;
import com.example.JavaProject.Hostelproject.Entity.UserEntity;
import java.util.List;
import java.util.Optional;

public interface SessionRepository extends JpaRepository<SessionEntity,Long> {
    List<SessionEntity> findByUserEntity(UserEntity userEntity);
    SessionEntity findByRefreshToken( String token);
}
