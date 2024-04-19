package com.example.projectzeus.adapters;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectzeus.R;
import com.example.projectzeus.models.CocheItem;
import com.example.projectzeus.ui.Detalles_activity;

import java.util.List;

public class CocheAdapter extends RecyclerView.Adapter<CocheAdapter.ViewHolder>{
    private List<CocheItem> coches;
    public CocheAdapter(List<CocheItem> coches) {
        this.coches = coches;
    }
    @NonNull
    @Override
    public CocheAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.coches_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CocheAdapter.ViewHolder holder, int position) {
        CocheItem coche = coches.get(position);
        holder.setData(coche);
    }

    @Override
    public int getItemCount() {
        return coches.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView txtalias, txtdescripcion;
        private CocheItem coche;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtalias = itemView.findViewById(R.id.txtalias);
            txtdescripcion = itemView.findViewById(R.id.txtdescripcion);
            itemView.setOnClickListener(this);
        }

        public void setData(CocheItem coche) {
            this.coche = coche;
            txtalias.setText(coche.getAlias());
            txtdescripcion.setText(coche.getDescripcion());
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(v.getContext(), Detalles_activity.class);
            intent.putExtra("coche", coche);
            v.getContext().startActivity(intent);
        }
    }
}
