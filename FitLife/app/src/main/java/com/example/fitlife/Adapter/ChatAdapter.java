package com.example.fitlife.Adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.fitlife.Activity.GPTActivity;
import com.example.fitlife.Model.MessageModel;
import com.example.fitlife.R;

import java.util.ArrayList;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ViewHolder> {
    private ArrayList<MessageModel> messageModelList;
    private static Context context;

    public ChatAdapter(ArrayList<MessageModel> messageModelList, GPTActivity context) {
        this.messageModelList = messageModelList;
        this.context = context;
    }

    @NonNull
    @Override
    public ChatAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.text_chat_item, parent, false);
        return new ChatAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ChatAdapter.ViewHolder holder, int position) {
        MessageModel messageModel = messageModelList.get(position);
        if ( messageModel.isType() ) {
            holder.itemChat.setText(messageModel.getMessage());
            holder.itemChat.setVisibility(View.VISIBLE);
            holder.itemQuestion.setVisibility(View.GONE);
        } else {
            holder.itemQuestion.setText(messageModel.getMessage());
            holder.itemChat.setVisibility(View.GONE);
            holder.itemQuestion.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return messageModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView itemChat;
        private TextView itemQuestion;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            bindingVIew();
        }

        private void bindingVIew() {
            itemChat = itemView.findViewById(R.id.tvItemChat);
            itemQuestion = itemView.findViewById(R.id.tvItemQuestion);
        }
    }
}
