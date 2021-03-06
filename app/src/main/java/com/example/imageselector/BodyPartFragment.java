package com.example.imageselector;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

import data.AndroidImageAssets;

public class BodyPartFragment extends Fragment {
    private static final String IMAGE_ID_KEY = "mimage_ids";
    private static final String IMAGE_INDEX_KEY = "mimage_index";

    private List<Integer> mImageIds;
    private int mImageIndex;

    public void setmImageIds(List<Integer> mImageIds) {
        this.mImageIds = mImageIds;
    }

    public void setmImageIndex(int mImageIndex) {
        this.mImageIndex = mImageIndex;
    }


    public BodyPartFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(savedInstanceState != null){
            mImageIds = savedInstanceState.getIntegerArrayList(IMAGE_ID_KEY);
            mImageIndex = savedInstanceState.getInt(IMAGE_INDEX_KEY);
        }
        View rootView = inflater.inflate(R.layout.fragment_body_part, container, false);
        final ImageView imageView = (ImageView) rootView.findViewById(R.id.body_part_image_view);
        if(mImageIds != null){
            imageView.setImageResource(mImageIds.get(mImageIndex));

            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mImageIndex < mImageIds.size() - 1){
                        mImageIndex++;
                    }
                    else if(mImageIndex == mImageIds.size() - 1){
                        mImageIndex = 0;
                    }
                    imageView.setImageResource(mImageIds.get(mImageIndex));
                }

            });
        }
        return rootView;
       }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putIntegerArrayList(IMAGE_ID_KEY, (ArrayList<Integer>) mImageIds);
        outState.putInt(IMAGE_INDEX_KEY, mImageIndex);
    }
}
