package com.ddr.drugtrace.controller;

import com.ddr.drugtrace.bean.dto.LoginDto;
import com.ddr.drugtrace.bean.dto.LoginForm;
import com.ddr.drugtrace.enums.UserRole;
import com.ddr.drugtrace.mapper.UsersMapper;
import com.ddr.drugtrace.model.Users;
import com.ddr.drugtrace.util.JwtTokenUtil;
import com.ddr.drugtrace.util.ResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/auth")
public class AuthController {
 
    @Autowired
    private AuthenticationManager authenticationManager;
 
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
 
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private UsersMapper usersMapper;

    @PostMapping("/login")
    public ResponseDto<LoginDto> login(@Valid @RequestBody LoginForm loginForm) {
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                loginForm.getUsername(),
                loginForm.getPassword()
            )
        );
 
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtTokenUtil.generateToken(authentication);
        redisTemplate.opsForValue().set(loginForm.getUsername(), jwt, 30, TimeUnit.MINUTES);

        LoginDto loginDto = new LoginDto();
        Users users = usersMapper.getByUserName(loginForm.getUsername());
        loginDto.setUsername(users.getUsername());
        loginDto.setNickname(users.getNickname());
        loginDto.setRole(UserRole.getByStatus(users.getRole()).name().toLowerCase());
        loginDto.setToken(jwt);
        return ResponseDto.success(loginDto);
    }
}