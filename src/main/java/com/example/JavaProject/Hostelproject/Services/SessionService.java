package com.example.JavaProject.Hostelproject.Services;


import com.example.JavaProject.Hostelproject.Entity.SessionEntity;
import com.example.JavaProject.Hostelproject.Entity.UserEntity;
import com.example.JavaProject.Hostelproject.RepoSitory.SessionRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
@Service
public class SessionService {
    @Autowired
    SessionRepository sessionRepository;
    private static  final int  MAXSESSION = 2;

    public void getSession(UserEntity userEntity, String refreshToken){
        List<SessionEntity> allSession = sessionRepository.findByUserEntity(userEntity);
        if( allSession.size() == 2){
          allSession.sort(Comparator.comparing(SessionEntity::getUpdationTimeStramp));
          SessionEntity sessionEntity  = allSession.get(0);
          sessionRepository.delete(sessionEntity);
        }
        SessionEntity sessionEntity =SessionEntity.builder().userEntity(userEntity)
                        .refreshToken(refreshToken).build();
        sessionRepository.save(sessionEntity);
    }
    @Transactional
    public void ValidateToken( String Token){
        SessionEntity token = sessionRepository.findByRefreshToken(Token);
                token.setUpdationTimeStramp(LocalDateTime.now());


    }
}
