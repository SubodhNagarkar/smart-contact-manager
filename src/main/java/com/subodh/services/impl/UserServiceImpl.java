package com.subodh.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.subodh.entities.User;
import com.subodh.helper.AppConstants;
import com.subodh.helper.ResourceNotFoundException;
import com.subodh.repositiries.UserRepo;
import com.subodh.services.UserService;
@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Override
    public User saveUser(User user) {
        String userID = UUID.randomUUID().toString();
            user.setUserId(userID);

            user.setPassword(passwordEncoder.encode(user.getPassword()));

            user.setRolesList(List.of(AppConstants.ROLE_USER));
            logger.info(user.getProvider().toString());
        return userRepo.save(user);
    }

    @Override
    public Optional<User> getUserById(String id) {
        return userRepo.findById(id);
    }

    @Override
    public Optional<User> updateUser(User user) {
        // TODO Auto-generated method stub
        User updatedUser = userRepo.findById(user.getUserId()).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        updatedUser.setName(user.getName());
        updatedUser.setEmail(user.getEmail());
        updatedUser.setPassword(user.getPassword());
        updatedUser.setAbout(user.getAbout());
        updatedUser.setProfilePic(user.getProfilePic());
        updatedUser.setPhoneNumber(user.getPhoneNumber());
        updatedUser.setEnabled(user.isEnabled());
        updatedUser.setEmailVerified(user.isEmailVerified());
        updatedUser.setPhoneVerified(user.isPhoneVerified());
        updatedUser.setProvider(user.getProvider());
        updatedUser.setPhoneVerified(user.getProviderUserId)
    
        user save = userRepo.save(updatedUser);
        return Optional.of(save);
    }

    @Override
    public void deleteUser(String id) {
        User user2 = userRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        userRepo.delete(user2);
    }

    @Override
    public boolean isUserExist(String userId) {
        User user2 = userRepo.findById(userId).orElse(null);
        return user2 != null? true : false;
    }

    @Override
    public boolean isUserExistByEmail(String email) {
        // TODO Auto-generated method stub
        User user = userRepo.findByEmail(email).orElse(null);
        return user != null ? true :false;
    }

    @Override
    public List<User> getAllUsers() {
        // TODO Auto-generated method stub
       return  userRepo.findAll();
    }

}
