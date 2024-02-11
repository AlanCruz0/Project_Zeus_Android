package com.example.projectzeus.viewmodel;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.projectzeus.models.CocheItem;
import com.example.projectzeus.models.request.RegisterRequest;
import com.example.projectzeus.models.request.UserRequest;
import com.example.projectzeus.models.response.CochesResponse;
import com.example.projectzeus.models.response.LogoutResponse;
import com.example.projectzeus.models.response.UserResponse;
import com.example.projectzeus.models.response.ValidateResponse;
import com.example.projectzeus.repository.UserRepository;

import java.util.List;

public class LoginViewModel extends ViewModel {
    private UserRepository userRepository;

    public LoginViewModel() {
        userRepository = new UserRepository();
    }

    public LiveData<UserResponse> loginUser(UserRequest user) {
        return userRepository.loginUser(user);
    }
    public LiveData<UserResponse> registerUser(RegisterRequest user) {
        return userRepository.registerUser(user);
    }
    public LiveData<LogoutResponse> logoutUser(Context context) {
        return userRepository.logoutUser(context);
    }

    public LiveData<ValidateResponse> validateUser(Context context) {
        return userRepository.validateUser(context);
    }
}
