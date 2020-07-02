package com.FIEK.raportoup.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.FIEK.raportoup.R;

import java.util.ArrayList;

public class AdminAdapter extends RecyclerView.Adapter<AdminAdapter.MyViewHolder> {

    private Context context;
    private Activity activity;
    private ArrayList username, koment, kategorite, selectedImage, koha, adresa;


    public AdminAdapter(Activity activity, Context context, ArrayList username,
                        ArrayList koment, ArrayList kategorite, ArrayList selectedImage, ArrayList koha, ArrayList adresa) {
        this.activity = activity;
        this.context = context;
        this.username = username;
        this.koment = koment;
        this.kategorite = kategorite;
        this.selectedImage = selectedImage;
        this.koha = koha;
        this.adresa = adresa;
    }

    @NonNull
    @Override
    public AdminAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);

        return new AdminAdapter.MyViewHolder(view);
    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull final AdminAdapter.MyViewHolder holder, final int position) {

        holder.username_txt.setText(String.valueOf(username.get(position)));
        holder.koment_txt.setText(String.valueOf(koment.get(position)));
        holder.kategorite_txt.setText(String.valueOf(kategorite.get(position)));

        byte[] image = (byte[]) selectedImage.get(position);
        holder.selectedImage_iv.setImageBitmap(BitmapFactory.decodeByteArray(image, 0, image.length));
        holder.koha_txt.setText(String.valueOf(koha.get(position)));
        holder.adresa_txt.setText(String.valueOf(adresa.get(position)));
    }

    @Override
    public int getItemCount() {
        return username.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView username_txt, koment_txt, kategorite_txt, koha_txt, adresa_txt;
        ImageView selectedImage_iv;
        LinearLayout mainLayout;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            username_txt = itemView.findViewById(R.id.username_txt);
            koment_txt = itemView.findViewById(R.id.koment_txt);
            kategorite_txt = itemView.findViewById(R.id.kategorite_txt);
            selectedImage_iv = itemView.findViewById(R.id.selectedImage_iv);
            koha_txt = itemView.findViewById(R.id.koha_txt);
            adresa_txt = itemView.findViewById(R.id.adresa_txt);

            mainLayout = itemView.findViewById(R.id.mainLayout);

            Animation translate_anim = AnimationUtils.loadAnimation(context, R.anim.translate_anim);
            mainLayout.setAnimation(translate_anim);
        }
    }
}
