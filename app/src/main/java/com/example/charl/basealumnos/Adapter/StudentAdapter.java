package com.example.charl.basealumnos.Adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.charl.basealumnos.Data.Alumno;
import com.example.charl.basealumnos.R;

import java.util.ArrayList;

public abstract class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.StudentViewHolder>{
    private ArrayList<Alumno> student;


    public static class StudentViewHolder extends RecyclerView.ViewHolder {
        CardView card;
        TextView name;
        TextView nota;
        TextView carnet;
        Context cxt;




        public StudentViewHolder(View itemView){
            super(itemView);
            cxt=itemView.getContext();
            card=itemView.findViewById(R.id.card_view);
            name=itemView.findViewById(R.id.nameL);
            nota=itemView.findViewById(R.id.grade);
            carnet=itemView.findViewById(R.id.iden);



        }

    }

    public StudentAdapter(ArrayList<Alumno> student) {
        this.student = student;
    }

    @Override
    public StudentViewHolder onCreateViewHolder(ViewGroup parent, int ViewType){
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_cardview,parent,false);
        return (new StudentViewHolder(v));
    }

    @Override
    public void onBindViewHolder(StudentViewHolder holder, final int position){
        holder.carnet.setText(student.get(position).getCarnet());
        holder.name.setText(student.get(position).getNombre());
        holder.nota.setText(student.get(position).getNota());

    }



    @Override
    public int getItemCount(){
        return student.size();
    }

    public abstract void onVerClick(View v,int pos);
    public abstract void Contador(int cont);


}


