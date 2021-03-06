package com.example.imageselector;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import data.AndroidImageAssets;

public class Master_Fragment_Activity extends AppCompatActivity implements MasterListFragment.OnImageClickListener{
    private int headIndex, bodyIndex, legIndex;
    private boolean mTwoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_master__fragment_);
        if(findViewById(R.id.android_me_linear_layout) != null){
            mTwoPane = true;
            Button button = (Button) findViewById(R.id.next_button);
            button.setVisibility(View.GONE);

            GridView gridView = (GridView) findViewById(R.id.image_grid_view);
            gridView.setNumColumns(2);
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
        else{
            mTwoPane = false;
        }
    }

    @Override
    public void onImageSelected(int position) {
        Toast.makeText(this, "Image Clicked " + position, Toast.LENGTH_SHORT).show();

        int bodyPartNumber = position / 12;
        int listIndex = position - 12*bodyPartNumber;

        if(mTwoPane){
            BodyPartFragment newFragment = new BodyPartFragment();
            switch (bodyPartNumber){
                case 0:
                    newFragment.setmImageIds(AndroidImageAssets.getHeads());
                    newFragment.setmImageIndex(headIndex);
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    fragmentManager.beginTransaction()
                            .replace(R.id.headContainer, newFragment)
                            .commit();
                    break;
                case 1:
                    newFragment.setmImageIds(AndroidImageAssets.getBodies());
                    newFragment.setmImageIndex(bodyIndex);
                    FragmentManager bodyFragmentManager = getSupportFragmentManager();
                    bodyFragmentManager.beginTransaction()
                            .replace(R.id.bodyContainer, newFragment)
                            .commit();
                    break;
                case 2:
                    newFragment.setmImageIds(AndroidImageAssets.getLegs());
                    newFragment.setmImageIndex(legIndex);
                    FragmentManager legFragmentManager = getSupportFragmentManager();
                    legFragmentManager.beginTransaction()
                            .replace(R.id.legContainer, newFragment)
                            .commit();
                    break;
            }
        }

        switch (bodyPartNumber){
            case 0: headIndex = listIndex;
                break;
            case 1: bodyIndex = listIndex;
                break;
            case 2: legIndex = listIndex;
                break;
            default: break;
        }

        Bundle b = new Bundle();
        b.putInt("HeadIndex", headIndex);
        b.putInt("BodyIndex", bodyIndex);
        b.putInt("LegIndex", legIndex);

        final Intent intent = new Intent(this, MainActivity.class);
        intent.putExtras(b);

        Button nextButton = (Button) findViewById(R.id.next_button);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent);
            }
        });
    }
}