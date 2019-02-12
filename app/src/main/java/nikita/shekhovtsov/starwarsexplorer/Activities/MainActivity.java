package nikita.shekhovtsov.starwarsexplorer.Activities;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import java.util.ArrayList;
import java.util.List;

import nikita.shekhovtsov.starwarsexplorer.Adapters.BeingAdapter;
import nikita.shekhovtsov.starwarsexplorer.Adapters.BeingSavedAdapter;
import nikita.shekhovtsov.starwarsexplorer.DataBases.BeingDataReader;
import nikita.shekhovtsov.starwarsexplorer.DataBases.BeingDataSource;
import nikita.shekhovtsov.starwarsexplorer.Model.Being;
import nikita.shekhovtsov.starwarsexplorer.Model.BeingList;
import nikita.shekhovtsov.starwarsexplorer.Queries.SearchByName;
import nikita.shekhovtsov.starwarsexplorer.R;
import nikita.shekhovtsov.starwarsexplorer.Queries.GetAllPeople;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager recycleLayoutManager;
    private BeingAdapter beingAdapter;
    private ImageButton searchButton;
    private ProgressBar progressBar;
    private EditText editText;
    private BeingDataReader beingDataReader;
    private BeingDataSource beingDataSource;
    private BeingSavedAdapter beingSavedAdapter;
    GetAllPeople getAllPeople;
    SearchByName searchByName;
    BeingList beingList;
    List<Being> dataset;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        initDataSource();
        initGui();

        initEvents();
    }

    private void initDataSource(){
        beingDataSource = new BeingDataSource(getApplicationContext());
        beingDataSource.open();
        beingDataReader = beingDataSource.getNoteDataReader();
    }


    private void initGui() {
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recycleLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(recycleLayoutManager);
        beingSavedAdapter = new BeingSavedAdapter(beingDataReader);
        recyclerView.setAdapter(beingSavedAdapter);
        searchButton = findViewById(R.id.button_search);
        progressBar = findViewById(R.id.progress_bar);
        editText = findViewById(R.id.edit_text);
        editText.setText("");
    }
    private void initEvents() {
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (editText.getText().toString().equals("")) {
                    progressBar.setVisibility(View.VISIBLE);
                    dataset = new ArrayList<>();
                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl("https://swapi.co/api/")
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();
                    getAllPeople = retrofit.create(GetAllPeople.class);
                    Call<BeingList> call = getAllPeople.getPeople();
                    call.enqueue(new Callback<BeingList>() {
                        @Override
                        public void onResponse(Call<BeingList> call, Response<BeingList> response) {
                            beingList = response.body();
                            beingAdapter = new BeingAdapter(beingList.getResults());
                            recyclerView.setAdapter(beingAdapter);
                            progressBar.setVisibility(View.GONE);
                        }

                        @Override
                        public void onFailure(Call<BeingList> call, Throwable t) {
                            Log.e(t.getMessage(), "Error");
                            progressBar.setVisibility(View.GONE);
                        }
                    });
                } else {
                    progressBar.setVisibility(View.VISIBLE);
                    dataset = new ArrayList<>();
                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl("https://swapi.co/api/")
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();
                    searchByName = retrofit.create(SearchByName.class);
                    Call<BeingList> call = searchByName.getSearchableNames(editText.getText().toString());
                    call.enqueue(new Callback<BeingList>() {
                        @Override
                        public void onResponse(Call<BeingList> call, Response<BeingList> response) {
                            beingList = response.body();
                            beingAdapter = new BeingAdapter(beingList.getResults());
                            recyclerView.setAdapter(beingAdapter);
                            progressBar.setVisibility(View.GONE);
                        }

                        @Override
                        public void onFailure(Call<BeingList> call, Throwable t) {
                            Log.e(t.getMessage(), "Error");
                            progressBar.setVisibility(View.GONE);
                        }
                    });
                }
            }
        });
    }

}
