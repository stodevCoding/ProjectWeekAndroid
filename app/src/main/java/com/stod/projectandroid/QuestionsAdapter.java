package com.stod.projectandroid;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;

public class QuestionsAdapter extends RecyclerView.Adapter<QuestionsAdapter.ViewHolder> {


    private final List<Questions> questions;

    public QuestionsAdapter(List<Questions> questions) {
        this.questions = questions;
    }


    static class ViewHolder extends RecyclerView.ViewHolder {

        final TextView questionTextView;
        final ImageView pofpokemon;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            pofpokemon = itemView.findViewById(R.id.imageView);
            questionTextView = itemView.findViewById(R.id.questionTextView);
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
        String questionPurpose = "Quel est ce pokémon ?";

        // on met à jour l'UI en passant par le ViewHolder
        holder.pofpokemon.setImageResource(R.drawable.p_of_pokemon);
        holder.questionTextView.setText(questionPurpose);
    }

    @Override
    public int getItemCount() {
        return questions.size();
    }
}
