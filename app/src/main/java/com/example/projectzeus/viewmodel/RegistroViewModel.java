package com.example.projectzeus.viewmodel;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.projectzeus.models.request.LedRequest;
import com.example.projectzeus.models.response.CochesResponse;
import com.example.projectzeus.models.response.LedCTResponse;
import com.example.projectzeus.models.response.LedPTResponse;
import com.example.projectzeus.models.response.UbicacionResponse;
import com.example.projectzeus.repository.RegistroRepository;
import com.example.projectzeus.repository.UserRepository;

public class RegistroViewModel extends ViewModel {
    private RegistroRepository registroRepository;

    public RegistroViewModel() {
        registroRepository = new RegistroRepository();
    }

    public LiveData<UbicacionResponse> getUbicacion(Context context, Long cocheId) {
        return registroRepository.getUbicacion(context, cocheId);
    }
    public LiveData<LedCTResponse> getLed(Context context, Long cocheId) {
        return registroRepository.getLed(context, cocheId);
    }
    public LiveData<LedPTResponse> setLed(Context context, Long cocheId, LedRequest ledRe) {
        return registroRepository.setLed(context, cocheId, ledRe);
    }
}
