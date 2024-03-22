package com.example.projectzeus.adapters;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectzeus.R;
import com.example.projectzeus.models.ReporteItem;
import com.example.projectzeus.ui.Detalles_activity;

import java.util.List;

public class ReporteAdapter extends RecyclerView.Adapter<ReporteAdapter.ViewHolder>{
    private List<ReporteItem> reportes;
    public ReporteAdapter(List<ReporteItem> reportes) {
        this.reportes = reportes;
    }
    @NonNull
    @Override
    public ReporteAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.reportes_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReporteAdapter.ViewHolder holder, int position) {
        ReporteItem reporte = reportes.get(position);
        holder.setData(reporte);
    }

    @Override
    public int getItemCount() {
        return reportes.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtdist, txtfecha, txthora;
        private ReporteItem reporte;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtdist = itemView.findViewById(R.id.txtdist);
            txtfecha = itemView.findViewById(R.id.txtfecha);
            txthora = itemView.findViewById(R.id.txthora);
        }

        public void setData(ReporteItem reporte) {
            this.reporte = reporte;
            txtdist.setText(reporte.getDistancia());
            txtfecha.setText(reporte.getFecha());
            txthora.setText(reporte.getHora());
        }
    }
}
