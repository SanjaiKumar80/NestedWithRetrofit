package com.example.myapplication;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Model.Android;
import com.example.myapplication.Model.Category;
import com.example.myapplication.Model.Model;
import com.example.myapplication.adapters.VerticalRecyclerViewAdapter;
import com.example.myapplication.network.Api;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    RecyclerView rvVertical;

    ArrayList<Category> mArrayList = new ArrayList<>();
    VerticalRecyclerViewAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        rvVertical = findViewById(R.id.rvVertical);

        rvVertical.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        mAdapter = new VerticalRecyclerViewAdapter(this, mArrayList);

        rvVertical.setAdapter(mAdapter);


        getValues();
    }

    private void getValues() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()) //Here we are using the GsonConverterFactory to directly convert json data to object
                .build();

        //creating the api interface
        Api api = retrofit.create(Api.class);

        //now making the call object
        //Here we are using the api method that we created inside the api interface
        Call<Model> call = api.getJson();

        call.enqueue(new Callback<Model>() {


            @Override
            public void onResponse(Call<Model> call, Response<Model> response) {

                if (response.body() != null){
                List<Category> heroList =  response.body().getCategory();



                int j = heroList.size();
                for (int i=0; i<j; i++) {

                    Category mVerticalModel = new Category();
                    String CatTitle = heroList.get(i).getName();
                    mVerticalModel.setName(CatTitle);

                    ArrayList<Android> arrayList = new ArrayList<>();

                    int k = heroList.get(i).getAndroid().size();
                    for (int s=0 ; s < k; s++) {
                        Android mHorizontalModel = new Android();

                        String mTitle = heroList.get(i).getAndroid().get(s).getTitle();
                        mHorizontalModel.setTitle(mTitle);

                        String mUrl = heroList.get(i).getAndroid().get(s).getImageUrl();
                        mHorizontalModel.setImageUrl(mUrl);

                        arrayList.add(mHorizontalModel);
                    }

                    mVerticalModel.setAndroid(arrayList);

                    mArrayList.add(mVerticalModel);

                }
                mAdapter.notifyDataSetChanged();}

            }

            @Override
            public void onFailure(Call<Model> call, Throwable t) {
                Log.i("TAG", "onFailure: " + t.getMessage());
            }


        });


    }


}