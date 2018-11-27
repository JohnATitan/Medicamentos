package android.movil.escom.proyectomedicamentos.ui.adapters;

import android.content.Context;
import android.movil.escom.proyectomedicamentos.R;
import android.movil.escom.proyectomedicamentos.interfaces.NuevoMedicamentoListener;
import android.movil.escom.proyectomedicamentos.interfaces.VerDetallesMedicamentoListener;
import android.movil.escom.proyectomedicamentos.model.Medicamento;
import android.movil.escom.proyectomedicamentos.util.Utilidades;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MedicamentoAdapter extends RecyclerView.Adapter<MedicamentoAdapter.ViewHolder> {

    private ArrayList<Medicamento> listaMedicamentos;
    private Context context;
    private VerDetallesMedicamentoListener listener;

    public void setListener(VerDetallesMedicamentoListener listener) {
        this.listener = listener;
    }

    public MedicamentoAdapter(Context context) {
        this.context = context;
        this.listaMedicamentos = new ArrayList<>();
    }

    public void setListaMedicamentos(ArrayList<Medicamento> listaMedicamentos) {
        this.listaMedicamentos = listaMedicamentos;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View root = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_medicamento_adapter, viewGroup, false);
        return new ViewHolder(root);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        Medicamento medicamento = listaMedicamentos.get(i);

        viewHolder.tvNombre.setText(medicamento.getNombre());
        viewHolder.tvNumeroDias.setText("Por ".concat(String.valueOf(medicamento.getNumeroDias())).concat(" días"));
        viewHolder.tvIntervaloHoras.setText("Cada ".concat(String.valueOf(medicamento.getInvervaloHoras())).concat(" horas"));
        viewHolder.ivFoto.setImageBitmap(Utilidades.base64ToBitmap(medicamento.getRutaFotoMedicamento()));

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.verMedicamento(i);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return listaMedicamentos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivFoto;
        TextView tvNombre, tvIntervaloHoras, tvNumeroDias;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivFoto = itemView.findViewById(R.id.ivFoto);
            tvNombre = itemView.findViewById(R.id.tvNombre);
            tvNumeroDias = itemView.findViewById(R.id.tvNumeroDias);
            tvIntervaloHoras = itemView.findViewById(R.id.tvIntervaloHoras);
        }
    }
}
