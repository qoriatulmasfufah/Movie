package id.sch.smktelkom_mlg.privateassignment.xirpl127.movie;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

/**
 * Created by Qoriatul Masfufah on 5/14/2017.
 */

public class PlaceAdapter extends RecyclerView.Adapter<PlaceAdapter.ViewHolder> {

    private final Context context;
    ArrayList<Place> fItem;

    public PlaceAdapter(ArrayList<Place> place, Context context) {
        this.fItem = place;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.place_list_item, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final Place place = fItem.get(position);
        holder.textViewHeadplace.setText(place.title);
        holder.textViewDescplace.setText(place.deskripsi);

        Glide
                .with(context)
                .load(place.urlgambar)
                .into(holder.imageViewOtofplace);

        holder.buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Place placeItem1 = fItem.get(position);
                fItem.remove(position);
                placeItem1.delete();
                PlaceAdapter.this.notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return fItem.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewHeadplace;
        public TextView textViewDescplace;
        public ImageView imageViewOtofplace;
        public Button buttonDelete;


        public ViewHolder(View itemView) {
            super(itemView);

            textViewHeadplace = (TextView) itemView.findViewById(R.id.textViewHeadplace);
            textViewDescplace = (TextView) itemView.findViewById(R.id.textViewDescplace);
            imageViewOtofplace = (ImageView) itemView.findViewById(R.id.imageViewOtofplace);
            buttonDelete = (Button) itemView.findViewById(R.id.buttonDelete);

        }
    }
}
