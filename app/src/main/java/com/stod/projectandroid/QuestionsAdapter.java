package com.stod.projectandroid;

import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;

public class QuestionsAdapter extends RecyclerView.Adapter<QuestionsAdapter.ViewHolder>  {



    private final List<Questions> questions;

    public QuestionsAdapter(List<Questions> questions) {
        this.questions = questions;
    }



    static class ViewHolder extends RecyclerView.ViewHolder {
        final TextView symbol;
        final TextView rate;
        final ImageView flag;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            flag = itemView.findViewById(R.id.nameAnthoTextView);
            symbol = itemView.findViewById(R.id.nameAnthoTextView);
            rate = itemView.findViewById(R.id.nameApplicationTextView);
        }
    }

    /**
     * Méthode appelée 1x à l'init pour chaque item
     */
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // on converti le fichier XML d'item en objet Java
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_question, parent, false);

        // instancier le ViewHolder qui sera TOUJOURS lié à cette vue
        return new ViewHolder(view);
    }

    /**
     * appelé à chaque fois qu'un item doit être dessiné à l'écran
     */
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // on récupère la donnée associée à cet index
        Questions question = questions.get(position);

        // on met à jour l'UI en passant par le ViewHolder
        holder.flag.setImageResource(question.flagId);
        holder.symbol.setText(question.symbol);
        holder.rate.setText(question.rate + "");
    }

    @Override
    public int getItemCount() {
        return questions.size();
    }
}
