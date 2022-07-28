package com.example.a541j.Adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a541j.Models.Chat;
import com.example.a541j.Models.Room;
import com.example.a541j.R;
import com.google.android.material.imageview.ShapeableImageView;

import java.util.ArrayList;

public class ChatAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    Context context;
    ArrayList<Chat> items;

    int TYPE_ITEM_ROOM = 0;
    int TYPE_ITEM_MESSAGE = 1;

    public ChatAdapter( ArrayList<Chat> items, Context context){
        this.context = context;
        this.items = items;
    }

    @Override
    public int getItemViewType(int position) {
        Chat feed= items.get(position);

        if (feed.room.size()>0)
            return TYPE_ITEM_ROOM;
            return TYPE_ITEM_MESSAGE;
        }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == TYPE_ITEM_ROOM){
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chat_room, parent, false);
            return new RoomViewHolder( view);
        }
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chat_message, parent, false);
            return new MesageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Chat message = items.get(position);

        if (holder instanceof RoomViewHolder){
            RecyclerView recyclerView = ((RoomViewHolder) holder).recyclerView;
            refreshAdapter(message.room, recyclerView);
        }

        Chat chat = items.get(position);
        if (holder instanceof MesageViewHolder){

            TextView textView1 = ((MesageViewHolder)holder).tv_count;
            ShapeableImageView shapeableImageView = ((MesageViewHolder)holder).iv_profile;
            TextView textView = ((MesageViewHolder)holder).tv_fullname;

           textView.setText(chat.message.fullname);
            shapeableImageView.setImageResource(chat.message.profile);

            if (chat.message.isOnline){
                textView1.setVisibility(View.VISIBLE);
            }else{
                textView1.setVisibility(View.GONE);
            }
        }

    }

    void refreshAdapter(ArrayList<Room> rooms, RecyclerView recyclerView){
        RoomAdapter adapter = new RoomAdapter(context, rooms);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public int getItemCount() {return items.size();}

    static class RoomViewHolder extends RecyclerView.ViewHolder{

        RecyclerView recyclerView;

        public RoomViewHolder(@NonNull View itemView) {
            super(itemView);
           recyclerView = itemView.findViewById(R.id.rv);
           recyclerView.setLayoutManager(new LinearLayoutManager(itemView.getContext(), LinearLayoutManager.HORIZONTAL, false));

        }
    }

    static class MesageViewHolder extends RecyclerView.ViewHolder{

        ShapeableImageView iv_profile;
        TextView tv_fullname;
        TextView tv_count;

        public MesageViewHolder(@NonNull View itemView) {
            super(itemView);

            iv_profile = itemView.findViewById(R.id.iv_profile);
            tv_fullname = itemView.findViewById(R.id.fullname);
            tv_count = itemView.findViewById(R.id.tv_count);

        }
    }
}
