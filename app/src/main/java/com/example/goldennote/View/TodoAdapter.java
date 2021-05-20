package com.example.goldennote.View;

import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import com.example.goldennote.Model.Event;
import com.example.goldennote.R;

import java.util.Calendar;

import static com.example.goldennote.View.MainActivity.databaseReference;
import static com.example.goldennote.View.TodoFragment.todoAdapter;

public class TodoAdapter extends RecyclerView.Adapter<TodoAdapter.ViewHolder> {

    private Event event;
    private ArrayList<Event> eventList;
    private CardView cardView;


    public TodoAdapter(ArrayList<Event> eventList) {
        this.eventList = eventList;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.card_view, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TodoAdapter.ViewHolder holder, int position) {

        Event event = eventList.get(position);

//
        holder.title.setText(event.getTitle());
        holder.description.setText(event.getDescription());

            holder.date.setText(event.getDate());
//        }
        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteEvent(event.getId());
                System.out.println(event.getTitle() + "-------------" + event.getId());
                notifyItemRemoved(holder.getAdapterPosition());

            }
        });
    }

    public String format(String date) {
        String[] array = date.split("/");

        if (array[0].length() == 1) {
            array[0] = "0" + array[0];
            System.out.println(array[0]);
        }
        if (array[1].length() == 1) {
            array[1] = "0" + array[1];
            System.out.println(array[1]);
        }
        date = array[0] + "/" + array[1] + "/" + array[2];
        System.out.println(date);
        return date;
    }

    @Override
    public int getItemCount() {
        return eventList.size();
    }

    public void deleteEvent(String id) {
        databaseReference.child(id).removeValue();


    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        TextView date;
        TextView description;
        Button deleteButton;

        public ViewHolder(@NonNull View itemView) {

            super(itemView);
            title = itemView.findViewById(R.id.titleText);
            date = itemView.findViewById(R.id.dateText);
            description = itemView.findViewById(R.id.descriptionText);
            deleteButton = itemView.findViewById(R.id.deleteButton);

        }

    }
}



