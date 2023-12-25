package com.example.projectzeus.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.projectzeus.models.request.UserRequest;
import com.example.projectzeus.models.response.UserResponse;
import com.example.projectzeus.models.user;
import com.example.projectzeus.repository.UserRepository;

public class LoginViewModel extends ViewModel {
    private UserRepository userRepository;

    public LoginViewModel() {
        userRepository = new UserRepository();
    }

    public LiveData<UserResponse> loginUser(UserRequest user) {
        return userRepository.loginUser(user);
    }
}
