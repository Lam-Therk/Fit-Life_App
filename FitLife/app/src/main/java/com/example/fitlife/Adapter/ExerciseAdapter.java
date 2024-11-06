//package com.example.project_prm392;
//
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//import java.util.List;
//
//public class ExerciseAdapter extends RecyclerView.Adapter<ExerciseAdapter.ExerciseViewHolder> {
//    private List<Exercise> exerciseList;
//    private OnItemClickListener listener;
//
//    public interface OnItemClickListener {
//        void onItemClick(Exercise exercise);
//    }
//
//    public ExerciseAdapter(List<Exercise> exerciseList) {
//        this.exerciseList = exerciseList;
//    }
//    public ExerciseAdapter(List<Exercise> exerciseList, OnItemClickListener listener) {
//        this.exerciseList = exerciseList;
//        this.listener = listener;
//    }
//
//    @NonNull
//    @Override
//    public ExerciseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View itemView = LayoutInflater.from(parent.getContext())
//                .inflate(R.layout.activity_item, parent, false);
//        return new ExerciseViewHolder(itemView);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull ExerciseViewHolder holder, int position) {
//        Exercise exercise = exerciseList.get(position);
//        holder.imageView.setImageResource(exercise.getImageResId());
//        holder.textViewName.setText(exercise.getName());
//        holder.textViewRepeat.setText("Repeat: " + exercise.getRepeatTimes() + " times");
//        holder.textViewDuration.setText("Duration: " + exercise.getDuration());
//
//    }
//
//    @Override
//    public int getItemCount() {
//        return exerciseList.size();
//    }
//
//    public static class ExerciseViewHolder extends RecyclerView.ViewHolder {
//        public ImageView imageView;
//        public TextView textViewName;
//        public TextView textViewRepeat;
//        public TextView textViewDuration;
//
//        public ExerciseViewHolder(@NonNull View itemView) {
//            super(itemView);
//            imageView = itemView.findViewById(R.id.imageView);
//            textViewName = itemView.findViewById(R.id.textViewName);
//            textViewRepeat = itemView.findViewById(R.id.textViewRepeat);
//            textViewDuration = itemView.findViewById(R.id.textViewDuration);
//        }
//        public void bind(final Exercise exercise, final OnItemClickListener listener) {
//            imageView.setImageResource(exercise.getImageResId());
//            textViewName.setText(exercise.getName());
//            textViewRepeat.setText("Repeat: " + exercise.getRepeatTimes() + " times");
//            textViewDuration.setText("Duration: " + exercise.getDuration());
//
//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    listener.onItemClick(exercise);
//                }
//            });
//        }
//    }
//}
package com.example.fitlife.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fitlife.Model.Exercise;
import com.example.fitlife.R;

import java.util.List;

public class ExerciseAdapter extends RecyclerView.Adapter<ExerciseAdapter.ExerciseViewHolder> {
    private List<Exercise> exerciseList;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(Exercise exercise);
    }

    public ExerciseAdapter(List<Exercise> exerciseList, OnItemClickListener listener) {
        this.exerciseList = exerciseList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ExerciseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_item, parent, false);
        return new ExerciseViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ExerciseViewHolder holder, int position) {
        holder.bind(exerciseList.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return exerciseList.size();
    }

    public static class ExerciseViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public TextView textViewName;
        public TextView textViewRepeat;
        public TextView textViewDuration;

        public ExerciseViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            textViewName = itemView.findViewById(R.id.textViewName);
            textViewRepeat = itemView.findViewById(R.id.textViewRepeat);
            textViewDuration = itemView.findViewById(R.id.textViewDuration);
        }

        public void bind(final Exercise exercise, final OnItemClickListener listener) {
            imageView.setImageResource(exercise.getImageResId());
            textViewName.setText(exercise.getName());
            textViewRepeat.setText("Repeat: " + exercise.getRepeatTimes() + " times");
            textViewDuration.setText("Duration: " + exercise.getDuration());

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(exercise);
                }
            });
        }
    }
}
