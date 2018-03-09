package com.example.sammy.churchapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sammy.churchapp.Streaming.LiveRadio;
import com.example.sammy.churchapp.bible.Bible;
import com.example.sammy.churchapp.notespad.NotesPad;

/**
 * Created by Sammy on 10/6/2017.
 */

public class WelcomeAdapter extends RecyclerView.Adapter<WelcomeAdapter.ViewHolder> {

    private String[] text = {"Contact us", "Live Radio",
            "Live Service", "Bible", "Notes", "About us", "Givings", "Pastor's Music"};
    private int[] image = {R.drawable.contactus, R.drawable.audiopodcast, R.drawable.livestream,
    R.drawable.bible, R.drawable.create_note, R.drawable.about, R.drawable.give, R.drawable.pastor};
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.welcome_page_menu, parent, false);
        ViewHolder viewHolder = new ViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.text.setText(text[position]);
        holder.image.setImageResource(image[position]);

    }

    @Override
    public int getItemCount() {
        return text.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private Context context;
        public TextView text;
        public ImageView image;

        public ViewHolder(View itemView) {
            super(itemView);
            context = itemView.getContext();
            text = (TextView) itemView.findViewById(R.id.title);
            image = (ImageView) itemView.findViewById(R.id.image);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //switch statement goes here
                  //  final Intent intent;

                    if (getAdapterPosition()==0){
                        Toast.makeText((context), "Contacts selected", Toast.LENGTH_LONG).show();
                    }
                    if (getAdapterPosition()==1){
                      Intent  radio = new Intent(context, LiveRadio.class);
                        context.startActivity(radio);
                        Toast.makeText((context), "Live Radio", Toast.LENGTH_LONG).show();
                    }

                    if (getAdapterPosition()==2){
                        Toast.makeText((context), "Live Service", Toast.LENGTH_LONG).show();
                    }


                    if (getAdapterPosition()==3){
                        Intent bible = new Intent(context, Bible.class);
                        context.startActivity(bible);
                        Toast.makeText((context), "Bible", Toast.LENGTH_LONG).show();
                    }

                    if (getAdapterPosition()==4){
                       Intent notes = new Intent(context, NotesPad.class);
                        context.startActivity(notes);
                        Toast.makeText((context), "Notes", Toast.LENGTH_LONG).show();
                    }

                    if (getAdapterPosition()==5){
                        Toast.makeText((context), "About us", Toast.LENGTH_LONG).show();
                    }

                    if (getAdapterPosition()==6){
                        Toast.makeText((context), "Givings", Toast.LENGTH_LONG).show();
                    }

                    if (getAdapterPosition()==7){
                        Toast.makeText((context), "Messages & Musics", Toast.LENGTH_LONG).show();
                    }



                }
            });
        }
    }
}
