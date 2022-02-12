package com.cs633.team4.clouddatamanagementsystem.service;

import com.cs633.team4.clouddatamanagementsystem.mapper.UserMapper;
import com.cs633.team4.clouddatamanagementsystem.model.User;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;

@Service
public class UserService {

    private final UserMapper userMapper;
    private final HashService hashService;

    public UserService(UserMapper userMapper, HashService hashService) {
        this.userMapper = userMapper;
        this.hashService = hashService;
    }

    public boolean isUsernameAvailable(String username) {
        return userMapper.getUser(username) == null;
    }

    public Integer createUser(User user) {
        user.count += 1;
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        String encodedSalt = Base64.getEncoder().encodeToString(salt);
        String hashedPassword = hashService.getHashedValue(user.getPassword(), encodedSalt);
        return userMapper.insert(new User(user.count, user.getUsername(), encodedSalt, hashedPassword,
                                 user.getFirstName(), user.getLastName()));
    }

    public void update(User user) {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        String encodedSalt = Base64.getEncoder().encodeToString(salt);
        String hashedPassword = hashService.getHashedValue(user.getPassword(), encodedSalt);
        System.out.println(user.getPassword());
        User tempUser = userMapper.getUser(user.getUsername());
       // userMapper.delete(tempUser.getUserId());
        userMapper.updatePassword(hashedPassword,tempUser.getUsername(),encodedSalt);
       // userMapper.update(new User(tempUser.getUserId(), tempUser.getUsername(), encodedSalt, hashedPassword, tempUser.getFirstName(), tempUser.getLastName()));
    }

    public User getUser(String username) {
        return userMapper.getUser(username);
    }
}
