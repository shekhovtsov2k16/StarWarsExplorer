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
import java.util.List;

import nikita.shekhovtsov.starwarsexplorer.Activities.BeingActivity;
import nikita.shekhovtsov.starwarsexplorer.Model.Being;
import nikita.shekhovtsov.starwarsexplorer.R;

public class BeingAdapter extends RecyclerView.Adapter<BeingAdapter.ViewHolder> {


    private List<Being> data;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView textView;
        public CardView cardView;

        public ViewHolder(View view) {
            super(view);
            textView = view.findViewById(R.id.name_text_view);
            cardView = view.findViewById(R.id.card_view);
        }
    }

    public BeingAdapter(List<Being> beings) {
        data = beings;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_recycle_list, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int index) {
        viewHolder.textView.setText(data.get(index).getName());
        viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = viewHolder.cardView.getContext();
                Intent intent = new Intent(context, BeingActivity.class);
                intent.putExtra(Being.class.getSimpleName(), data.get(index));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

}
