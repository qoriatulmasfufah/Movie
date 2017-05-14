package id.sch.smktelkom_mlg.privateassignment.xirpl127.movie;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MovieActivity extends AppCompatActivity {

    private static final String URL_DATA = "https://api.nytimes.com/svc/movies/v2/reviews/search.json?api-key=5756c54cdcc547bba5c1774aac661fa7";
    //private static final String URL_DATA = "https://api.themoviedb.org/3/movie/550?api_key=5df3612d2ed0eccd328404739b957054";
    public TextView textViewHeadet;
    public TextView textViewDescet;
    public TextView textViewReview;
    public ImageView imageViewDetail;
    public String urlGambar;
    public String url;
    boolean isPressed = true;
    Place place;
    FloatingActionButton fab;
    boolean isNew;
    ArrayList<Place> fItem;
    private Integer mPostkey = null;//diambl dari blog id

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mPostkey = getIntent().getExtras().getInt("blog_id");
        loadRecyclerViewData(); //menampilkan detail pada sesuatu yg dipilih

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isPressed) {
                    doSave();
                    Snackbar.make(view, "Berhasil ditambahkan ke favorit", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                } else {
                    Snackbar.make(view, "Artikel favorit Anda", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
                isPressed = !isPressed;

            }
        });


        textViewHeadet = (TextView) findViewById(R.id.textViewHeadet);
        textViewDescet = (TextView) findViewById(R.id.textViewDescet);
        textViewReview = (TextView) findViewById(R.id.textViewReview);
        imageViewDetail = (ImageView) findViewById(R.id.imageViewDetail);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void doSave() {
        String title = textViewHeadet.getText().toString();
        String deskripsi = textViewDescet.getText().toString();
        String urlgambar = urlGambar;
        place = new Place(title, deskripsi, urlgambar);
        place.save();

//        SharedPreferences.Editor editor = getSharedPreferences(title, MODE_PRIVATE).edit();
//        editor.putBoolean("isNew", true);
//        editor.commit();


    }


    private void loadRecyclerViewData() {

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading data...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                URL_DATA,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        progressDialog.dismiss();
                        try {
                            JSONObject jsonObject = new JSONObject(s);
                            JSONArray array = jsonObject.getJSONArray("results");
                            JSONObject o = array.getJSONObject(mPostkey);

                            setTitle(" ");

                            /*textViewHeadet.setText(o.getString("display_title"));
                            textViewDescet.setText(o.getString("byline"));
                            textViewReview.setText(o.getString("summary_short"));
                            url = o.getJSONObject("link").getString("url");
                            Glide
                                    .with(MovieActivity.this)
                                    .load(o.getJSONObject("multimedia").getString("src"))
                                    .into(imageViewDetail);*/

                            textViewHeadet.setText(o.getString("display_title"));
                            textViewDescet.setText(o.getString("byline"));
                            textViewReview.setText(o.getString("summary_short"));
                            url = o.getJSONObject("link").getString("url");

                            Glide
                                    .with(MovieActivity.this)
                                    .load(o.getJSONObject("multimedia").getString("src"))
                                    .into(imageViewDetail);

                            urlGambar = o.getJSONObject("multimedia").getString("src");


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        progressDialog.dismiss();

                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}


