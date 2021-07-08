package com.example.demo.controlers;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.example.demo.dto.AppUserData;
import com.example.demo.dto.ResponseData;
import com.example.demo.models.entities.AppUser;
import com.example.demo.models.repos.AppUserRepo;
import com.example.demo.models.repos.AppUserRoleRepo;
import com.example.demo.payload.request.LoginRequest;
import com.example.demo.payload.response.JwtResponse;
import com.example.demo.security.jwt.JwtUtils;
import com.example.demo.services.AppUserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("api/users")
public class AppUsersController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    AppUserRepo appUserRepo;

    @Autowired
    AppUserRoleRepo AppUserRoleRepo;

    @Autowired
    AppUserService appUserService;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/register")
    public ResponseEntity<ResponseData<AppUser>> register(@Valid @RequestBody AppUserData appUserData) {

        ResponseData<AppUser> response = new ResponseData<>();
        AppUser appUser = modelMapper.map(appUserData, AppUser.class);
        response.setPayload(appUserService.registerAppUser(appUser));
        response.setStatus(true);
        response.getMessage().add("AppUser saved!");
        return ResponseEntity.ok(response);
    }

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        AppUser appUser = (AppUser) authentication.getPrincipal();
        List<String> roles = appUser.getAuthorities().stream().map(item -> item.getAuthority())
                .collect(Collectors.toList());
        return ResponseEntity
                .ok(new JwtResponse(jwt, appUser.getId(), appUser.getUsername(), appUser.getEmail(), roles));

    }

}
