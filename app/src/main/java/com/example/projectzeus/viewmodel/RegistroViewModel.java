package com.example.projectzeus.viewmodel;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.projectzeus.models.ReporteItem;
import com.example.projectzeus.models.request.LedRequest;
import com.example.projectzeus.models.response.ChoqueResponse;
import com.example.projectzeus.models.response.CochesResponse;
import com.example.projectzeus.models.response.ControlResponse;
import com.example.projectzeus.models.response.LedCTResponse;
import com.example.projectzeus.models.response.LedPTResponse;
import com.example.projectzeus.models.response.ReporteResponse;
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
    public LiveData<ReporteResponse> getReporte(Context context, Long cocheId) {
        return registroRepository.getReporte(context, cocheId);
    }
    public LiveData<ControlResponse> setControl(Context context, Long cocheId, LedRequest ledRe) {
        return registroRepository.setControl(context, cocheId, ledRe);
    }
    public LiveData<ChoqueResponse> getChoque(Context context, Long userId) {
        return registroRepository.getChoque(context, userId);
    }
}
