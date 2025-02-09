package com.example.onaffair.controller;

import com.example.onaffair.dto.UserLoginDto;
import com.example.onaffair.dto.UserRegisterDto;
import com.example.onaffair.dto.UserResponseDto;
import com.example.onaffair.entity.Collection;
import com.example.onaffair.entity.User;
import com.example.onaffair.service.CollectionService;
import com.example.onaffair.service.impl.UserServiceImpl;
import com.example.onaffair.utils.EncryptionUtil;
import com.example.onaffair.utils.Result;
import com.example.onaffair.utils.ResultCode;

import org.apache.ibatis.annotations.Param;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/user")
@Transactional
public class UserController {
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private CollectionService collectionService;

    @GetMapping("/all")
    @ResponseBody
    public Result<List<User>> getAllUsers(){
        List<User> res = userService.getAllUsers();
        System.out.println(res.toString());
        return Result.success(res);
    }
    @PostMapping("/login")
    @ResponseBody
    public Result<UserResponseDto> login(@RequestBody UserLoginDto userLoginDto) {
        String username = userLoginDto.getUsername();
        String password = EncryptionUtil.encodeBase64(userLoginDto.getPassword());

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);

        List<User> targets = userService.findUsersByCondition(user);

        System.out.println(targets);
        if (targets.isEmpty()){
            return Result.error(ResultCode.BAD_REQUEST.getCode(),"用户名或密码错误");
        }
        User target = targets.get(0);

        UserResponseDto res = new UserResponseDto(
                target.getUserId(),
                target.getUsername(),
                target.getNickname(),
                target.getAvatarUrl(),
                target.getEmail(),
                target.getPhoneNumber()
        );
        return Result.success(ResultCode.SUCCESS.getCode(),"登录成功",res);
    }
    @PostMapping("/register")
    @ResponseBody
    public Result<UserResponseDto> register(@RequestBody UserRegisterDto userRegisterDto){
        System.out.println(userRegisterDto);

        User user = new User();
        user.setUsername(userRegisterDto.getUsername());
        List<User> targets = userService.findUsersByCondition(user);
        if (!targets.isEmpty()){
            return Result.error(ResultCode.VALIDATION_ERROR.getCode(),"用户名已存在");
        }
        user.setPassword(EncryptionUtil.encodeBase64(userRegisterDto.getPassword()));
        user.setEmail(userRegisterDto.getEmail());
        user.setPhoneNumber(userRegisterDto.getPhone());
        user.setNickname(userRegisterDto.getNickname());
        user.setAvatarUrl(userRegisterDto.getAvatar());
        System.out.println("user:" + user);
        boolean flag = userService.addUser(user);

        if (!flag){
            return Result.error(ResultCode.ERROR.getCode(),"注册失败");
        }
        targets = userService.findUsersByCondition(user);
        User target = targets.get(0);
        System.out.println("target:"+target);
        UserResponseDto res = new UserResponseDto(
                target.getUserId(),
                target.getUsername(),
                target.getNickname(),
                target.getAvatarUrl(),
                target.getEmail(),
                target.getPhoneNumber()
        );
        return Result.success(ResultCode.SUCCESS.getCode(),"注册成功",res);

    }

    @PostMapping("/avatar")
    public Result<String> uploadAvatar(MultipartFile file){
        if (file.isEmpty()){
            return Result.error(ResultCode.ERROR.getCode(), "图片为空");
        }

        /*生成唯一文件名*/
        String originalFilename = file.getOriginalFilename();
        String fileNamePrefix = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        assert originalFilename != null;
        String fileNameSuffix = "." + originalFilename.split("\\.")[1];
        String fileName = fileNamePrefix + fileNameSuffix;

        /*定义文件保存路径*/
        ApplicationHome home = new ApplicationHome(this.getClass());
        String pre = home.getDir().getParentFile().getParentFile().getAbsolutePath() + "\\target\\classes\\static\\avatar\\";
        String path = pre  + fileName;
        try {
            File dir = new File(pre);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            file.transferTo(new File(path));
            String serverUrl = "http://localhost:721/static/avatar/" + fileName;
            System.out.println(serverUrl);
            return Result.success(ResultCode.SUCCESS.getCode(), "上传成功",serverUrl);
        } catch (IOException e){
            return Result.error(ResultCode.ERROR.getCode(), "上传失败");
        }

    }


}
