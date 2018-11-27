package android.movil.escom.proyectomedicamentos.ui.adapters;

import android.content.Context;
import android.movil.escom.proyectomedicamentos.R;
import android.movil.escom.proyectomedicamentos.interfaces.CalendarioTratamientoListener;
import android.movil.escom.proyectomedicamentos.model.Tratamiento;
import android.movil.escom.proyectomedicamentos.persistence.DBMedicamentos;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class ItemCalendarioTratamientoAdapter extends RecyclerView.Adapter<ItemCalendarioTratamientoAdapter.ViewHolder> {

    private Context context;
    private CalendarioTratamientoListener listener;
    private ArrayList<Tratamiento> listaTratamientos;

    public ItemCalendarioTratamientoAdapter(Context context, CalendarioTratamientoListener listener) {
        this.context = context;
        this.listener = listener;
        this.listaTratamientos = DBMedicamentos.getInstance().obtenerTratamientos();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View root = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_calendario_tratamiento, viewGroup, false);
        return new ViewHolder(root);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {

        Tratamiento tratamiento = listaTratamientos.get(i);

        viewHolder.tvNombre.setText("Tratamiento ".concat(String.valueOf(i + 1)));


        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.mostrarMedicamentos(tratamiento.getListaMedicamento());
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return listaTratamientos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvNombre;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNombre = itemView.findViewById(R.id.tvNombre);
        }
    }
}
