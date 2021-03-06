package com.example.imageselector;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;

import data.AndroidImageAssets;

public class MainActivity extends AppCompatActivity {
    private int headIndex, bodyIndex, legIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        if(intent != null)
        {
            Bundle bundle = intent.getExtras();
            headIndex = bundle.getInt("HeadIndex");
            bodyIndex = bundle.getInt("BodyIndex");
            legIndex = bundle.getInt("LegIndex");
        }
        if(savedInstanceState == null){
            BodyPartFragment headFragment = new BodyPartFragment();
            BodyPartFragment bodyFragment = new BodyPartFragment();
            BodyPartFragment legFragment = new BodyPartFragment();

            headFragment.setmImageIds(AndroidImageAssets.getHeads());
            headFragment.setmImageIndex(headIndex);

            bodyFragment.setmImageIds(AndroidImageAssets.getBodies());
            bodyFragment.setmImageIndex(bodyIndex);

            legFragment.setmImageIds(AndroidImageAssets.getLegs());
            legFragment.setmImageIndex(legIndex);

            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .add(R.id.headContainer, headFragment)
                    .add(R.id.bodyContainer, bodyFragment)
                    .add(R.id.legContainer, legFragment)
                    .commit();
        }

    }
}