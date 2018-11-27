package android.movil.escom.proyectomedicamentos.ui.adapters;

import android.content.Context;
import android.movil.escom.proyectomedicamentos.R;
import android.movil.escom.proyectomedicamentos.interfaces.CalendarioMedicamentoListener;
import android.movil.escom.proyectomedicamentos.model.Medicamento;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class ItemCalendarioMedicamentoAdapter extends RecyclerView.Adapter<ItemCalendarioMedicamentoAdapter.ViewHolder> {

    private Context context;
    private CalendarioMedicamentoListener listener;
    private ArrayList<Medicamento> listaMedicamentos;

    public ItemCalendarioMedicamentoAdapter(Context context, CalendarioMedicamentoListener listener) {
        this.context = context;
        this.listener = listener;
        listaMedicamentos = new ArrayList<>();
    }


    public void setListaMedicamentos(ArrayList<Medicamento> listaMedicamentos) {
        this.listaMedicamentos = listaMedicamentos;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View root = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_calendario_medicamento, viewGroup, false);
        return new ViewHolder(root);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {

        Medicamento medicamento = listaMedicamentos.get(i);

        viewHolder.tvNombre.setText(medicamento.getNombre());

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.seleccionarDiasMedicamento(medicamento.getNumeroDias());
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return listaMedicamentos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvNombre;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNombre = itemView.findViewById(R.id.tvNombre);
        }
    }
}
