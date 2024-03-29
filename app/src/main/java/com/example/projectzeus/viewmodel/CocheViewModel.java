package com.example.projectzeus.viewmodel;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.projectzeus.models.request.AgregarRequest;
import com.example.projectzeus.models.response.AgregarResponse;
import com.example.projectzeus.models.response.CochesResponse;
import com.example.projectzeus.repository.CocheRepository;
import com.example.projectzeus.repository.UserRepository;

public class CocheViewModel extends ViewModel {
    private UserRepository userRepository;
    private CocheRepository cocheRepository;

    public CocheViewModel() {
        userRepository = new UserRepository();
        cocheRepository = new CocheRepository();
    }

    public LiveData<CochesResponse> getCoches(Context context) {
        return userRepository.getCoches(context);
    }

    public LiveData<AgregarResponse> addCoches(Context context, AgregarRequest addre) {
        return cocheRepository.addCoche(context, addre);
    }
}
