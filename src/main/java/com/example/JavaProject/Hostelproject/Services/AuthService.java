package com.example.JavaProject.Hostelproject.Services;


import com.example.JavaProject.Hostelproject.Dto.LoginDto;
import com.example.JavaProject.Hostelproject.Dto.SessionDto;
import com.example.JavaProject.Hostelproject.Dto.SignUpDto;
import com.example.JavaProject.Hostelproject.Entity.SessionEntity;
import com.example.JavaProject.Hostelproject.Entity.UserEntity;
import com.example.JavaProject.Hostelproject.RepoSitory.SessionRepository;
import com.example.JavaProject.Hostelproject.RepoSitory.UserRepository;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Arrays;
import java.util.Optional;

@Slf4j
@Service
public class AuthService {

    @Autowired
    ModelMapper modelMapper;
    @Autowired
    SessionService sessionService;
    @Autowired
    UserService userService1;
    @Autowired
    JwtServices jwtServices;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    UserRepository userRepository;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    SessionRepository sessionRepository;
    @Autowired
    RedisTemplate redisTemplate;
//    @CachePut(cacheNames ="Users", key = "#result.id")
    public SignUpDto signUpService(SignUpDto signUpDto){
    try {
        Optional<UserEntity> isExist = userRepository.findByEmail(signUpDto.getEmail());
        if(isExist.isPresent()){
            throw new BadCredentialsException("User already exist with this email");
        }
        UserEntity userEntity = modelMapper.map(signUpDto, UserEntity.class);
        userEntity.setPassword(passwordEncoder.encode(signUpDto.getPassword()));
       UserEntity user = userRepository.save(userEntity);
        return modelMapper.map(user,SignUpDto.class);
    } catch (Exception e) {
        throw new RuntimeException(e);
    }

    }

    public SessionDto loginService(LoginDto loginDto, HttpServletResponse httpServletResponse){
        try {
            Authentication authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword()));

            UserEntity userEntity = (UserEntity)authentication.getPrincipal();
           String accessToken =   jwtServices.generateAccessKey(userEntity);
           String refreshToken = jwtServices.generateRefreshToken(userEntity);
//            List<SessionEntity> getUsers = sessionRepository.findByUserEntity(userEntity).orElseThrow(()->new RuntimeException("no token exit"));
                 sessionService.getSession(userEntity,refreshToken);
            Cookie cookie = new Cookie("refreshToken",refreshToken);
            cookie.setHttpOnly(true);
          httpServletResponse.addCookie(cookie);

            return SessionDto.builder().accessToken(accessToken)
                    .refreshToken(refreshToken).build();

        }catch(Exception e){
            throw new RuntimeException(e.getMessage());
        }

    }


    public SessionDto getRefreshToken( String token){
        try {
            Long id = jwtServices.getUserIdvalueFromToken(token);
            UserEntity userEntity = userService1.getUserById(id);
            sessionService.ValidateToken(token);
            String accessToken = jwtServices.generateAccessKey(userEntity);
            return SessionDto.builder().accessToken(accessToken).refreshToken(token).build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

//  @Cacheable(cacheNames = "Users",key = "#id")
    public SignUpDto getuserById(Long id) {
        try {
         return   modelMapper.map(userRepository.findById(id).orElseThrow(() -> new Exception("resource is not found")), SignUpDto.class);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
@Transactional
        public void logoutService(HttpServletRequest httpServletRequest){
        try{
            String accessToken = httpServletRequest.getHeader("Authorization").split(" ")[1];
//           String accessTokenJti = jwtServices.getJtiFromToken(accessToken);
//            log.info("accessToken is {}",accessTokenJti);
            Cookie[] cookie = httpServletRequest.getCookies();
       String refreshToken =     Arrays.stream(cookie).filter(e->"refreshToken".equals(e.getName())).findFirst().map(e->e.getValue()).orElseThrow();
       SessionEntity isExist = sessionRepository.findByRefreshToken(refreshToken);
       if( isExist != null){
           sessionRepository.deleteById(isExist.getId());
       }


            String accessTokenJti = jwtServices.getJtiFromToken(accessToken);
            String refreshTokenJti = jwtServices.getJtiFromToken(refreshToken);
       redisTemplate.opsForValue().set(refreshTokenJti,"refreshTokenJti", Duration.ofSeconds(1000L*60*24*30*6));
       redisTemplate.opsForValue().set(accessTokenJti,"accessTokenJti", Duration.ofSeconds(1000L*130));


        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
