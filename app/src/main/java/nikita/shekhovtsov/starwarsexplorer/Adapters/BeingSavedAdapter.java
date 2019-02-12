package nikita.shekhovtsov.starwarsexplorer.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import nikita.shekhovtsov.starwarsexplorer.Activities.BeingActivity;
import nikita.shekhovtsov.starwarsexplorer.DataBases.BeingDataReader;
import nikita.shekhovtsov.starwarsexplorer.Model.Being;
import nikita.shekhovtsov.starwarsexplorer.R;

public class BeingSavedAdapter extends RecyclerView.Adapter<BeingSavedAdapter.ViewHolderSaved> {

    private BeingDataReader beingDataReader;

    public BeingSavedAdapter(BeingDataReader beingDataReader) {
        this.beingDataReader = beingDataReader;
    }

    public static class ViewHolderSaved extends RecyclerView.ViewHolder {

        public TextView textView;
        public CardView cardView;

        public ViewHolderSaved(View view) {
            super(view);
            textView = view.findViewById(R.id.name_text_view);
            cardView = view.findViewById(R.id.card_view);
        }
    }


    @NonNull
    @Override
    public ViewHolderSaved onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_recycle_list, viewGroup, false);
        return new ViewHolderSaved(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolderSaved viewHolder, final int index) {
        viewHolder.textView.setText(beingDataReader.getPosition(index).getName());
        viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = viewHolder.cardView.getContext();
                Intent intent = new Intent(context, BeingActivity.class);
                intent.putExtra(Being.class.getSimpleName(), beingDataReader.getPosition(index));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return beingDataReader.getCount();
    }
}
