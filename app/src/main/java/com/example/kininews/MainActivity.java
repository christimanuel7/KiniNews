package com.example.kininews;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageSlider carousel=findViewById(R.id.carousel);

        List<SlideModel> slideModels=new ArrayList<>();
        slideModels.add(new SlideModel(R.drawable.cepat,"1", ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.akurat,"2", ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.kredibel,"3", ScaleTypes.FIT));
        carousel.setImageList(slideModels);
    }
}