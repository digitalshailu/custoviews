package com.example.customviews;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;

import customviews.CustomRippleLoader;

public class ProgressDialogActivity extends AppCompatActivity {

    static Dialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress_dialog);
        progressDialog = getProgressDialog(this, "", "");
        progressDialog.show();
    }

    @SuppressLint("InflateParams")
    public static Dialog getProgressDialog(Context context, String message, String title) {
        if (progressDialog == null) {
            progressDialog = new Dialog(context);
            progressDialog.setCancelable(false);
            View progressDialogView;
            LayoutInflater titleLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            progressDialogView = titleLayoutInflater.inflate(R.layout.custom_ripple_progress_view, null);
            CustomRippleLoader rippleBackground = progressDialogView.findViewById(R.id.rippleView);
            rippleBackground.startRippleAnimation();

            //Hide the dialog by setting background to transparent.
            progressDialog.getWindow().setBackgroundDrawable(context.getResources().getDrawable(R.drawable.progress_dialog_background));
            // titleText.setText(title);
            // messageText.setText(message);


            progressDialog.setContentView(progressDialogView);
        }
        return progressDialog;
    }
}
