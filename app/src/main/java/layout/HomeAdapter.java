package layout;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import id.sch.smktelkom_mlg.privateassignment.xirpl127.movie.HomeListItem;
import id.sch.smktelkom_mlg.privateassignment.xirpl127.movie.MovieActivity;
import id.sch.smktelkom_mlg.privateassignment.xirpl127.movie.R;

/**
 * Created by Qoriatul Masfufah on 5/8/2017.
 */
public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {

    private List<HomeListItem> homeListItems;
    private Context context;

    public HomeAdapter(List<HomeListItem> homeListItems, Context context) {
        this.homeListItems = homeListItems;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.home_item, parent, false);
        return new ViewHolder(v);
    } //menyambungkan ke xmlnya

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final HomeListItem homeListItem = homeListItems.get(position);// yg homeListItem digunakan untuk .load

        holder.textViewHead.setText(homeListItem.getHead());
        holder.textViewDesc.setText(homeListItem.getDesc());

        Glide
                .with(context)
                .load(homeListItem.getImageUrl())
                .into(holder.imageViewOtof);

        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent singleBlogIntent = new Intent(context, MovieActivity.class);
                singleBlogIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK); //intent single.. var baru untuk membuka activity dari fragment
                singleBlogIntent.putExtra("blog_id", position);//put extra ngirim var, unutk mengirim position nya
                context.startActivity(singleBlogIntent);//menampilkan activity

            }
        });
    }//mau diapakan viewnya

    @Override
    public int getItemCount() {
        return homeListItems.size();
    }//mengambil data, brp banyak data yg akan ditampilkan

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView textViewHead;
        public TextView textViewDesc;
        public ImageView imageViewOtof;
        public FrameLayout linearLayout;//buat ini dulu baru bisa buat di linear layout pada line -->holer bawahnya glide

        public ViewHolder(View itemView) {
            super(itemView);

            textViewHead = (TextView) itemView.findViewById(R.id.textViewHead);
            textViewDesc = (TextView) itemView.findViewById(R.id.textViewDesc);
            imageViewOtof = (ImageView) itemView.findViewById(R.id.imageViewOtof);
            linearLayout = (FrameLayout) itemView.findViewById(R.id.LinearLayout);
        }
    }//inisialisasi
}
