package com.example.projectzeus.viewmodel;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.projectzeus.models.response.CochesResponse;
import com.example.projectzeus.repository.UserRepository;

public class CocheViewModel extends ViewModel {
    private UserRepository userRepository;

    public CocheViewModel() {
        userRepository = new UserRepository();
    }

    public LiveData<CochesResponse> getCoches(Context context) {
        return userRepository.getCoches(context);
    }
}
