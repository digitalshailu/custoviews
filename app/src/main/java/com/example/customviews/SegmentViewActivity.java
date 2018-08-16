package com.example.customviews;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import customviews.SegmentedView;

public class SegmentViewActivity extends AppCompatActivity {

    private SegmentedView segmentView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_segment_view);


        SegmentedView.CompletionCount completionCount = new SegmentedView.CompletionCount();
        completionCount.setCompletedCount(0);
        completionCount.setTotalCount(4);

        segmentView = findViewById(R.id.segmentView);
        segmentView.setSegmentColor(android.R.color.black);
        segmentView.setSegmentCount(completionCount);
    }




}
