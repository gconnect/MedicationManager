package com.agatevure.medicationmanager.adapters;

import android.app.Dialog;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.GradientDrawable;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;


import com.agatevure.medicationmanager.R;
import com.agatevure.medicationmanager.modal.ToDoData;
import com.agatevure.medicationmanager.sqlite.SqliteHelper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class ToDoListAdapter extends RecyclerView.Adapter<ToDoListAdapter.ToDoListViewHolder> {
    List<ToDoData> ToDoDataArrayList = new ArrayList<ToDoData>();
    Context context;


    public ToDoListAdapter(String details) {
        ToDoData toDoData = new ToDoData();
        toDoData.setToDoTaskDetails(details);
        ToDoDataArrayList.add(toDoData);
    }

    public ToDoListAdapter(ArrayList<ToDoData> toDoDataArrayList, Context context) {
        this.ToDoDataArrayList = toDoDataArrayList;
        this.context = context;
    }

    @Override
    public ToDoListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_cardlayout, parent, false);
        ToDoListViewHolder toDoListViewHolder = new ToDoListViewHolder(view, context);
        return toDoListViewHolder;

    }

    @Override
    public void onBindViewHolder(ToDoListViewHolder holder, final int position) {
        final ToDoData td = ToDoDataArrayList.get(position);
        holder.todoDetails.setText(td.getToDoTaskDetails());
        holder.todoNotes.setText(td.getToDoNotes());

        String tdStatus = td.getToDoTaskStatus();
        if (tdStatus.matches("Complete")) {
            holder.todoDetails.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        }
        String type = td.getToDoTaskPrority();
        int color = 0;
        if (type.matches("Normal")) {
            color = Color.parseColor("#009EE3");
        } else if (type.matches("Low")) {
            color = Color.parseColor("#33AA77");
        } else {
            color = Color.parseColor("#FF7799");
        }
        ((GradientDrawable) holder.proprityColor.getBackground()).setColor(color);

        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id = td.getToDoID();
                SqliteHelper mysqlite = new SqliteHelper(view.getContext());
                Cursor b = mysqlite.deleteTask(id);
                if (b.getCount() == 0) {
                    Toast.makeText(view.getContext(), "Deleted", Toast.LENGTH_SHORT).show();
                    new Handler().post(new Runnable() {
                        @Override
                        public void run() {
                            // Code here will run in UI thread
                            /* ToDoDataArrayList.remove(position);
                            notifyItemRemoved(position);
                            notifyItemRangeChanged(position,ToDoDataArrayList.size()); */
                            //notifyDataSetChanged();
                        }
                    });
                } else {
                    Toast.makeText(view.getContext(), "Deleted else", Toast.LENGTH_SHORT).show();
                }


            }
        });
        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(view.getContext());
                dialog.setContentView(R.layout.custom_dailog);
                dialog.show();
                EditText todoText = (EditText) dialog.findViewById(R.id.input_task_desc);
                EditText todoNote = (EditText) dialog.findViewById(R.id.input_task_notes);
                EditText interval = (EditText) dialog.findViewById(R.id.interval);
                EditText startDate1 = (EditText) dialog.findViewById(R.id.startDate1);
                EditText endDate1 = (EditText) dialog.findViewById(R.id.endDate1);

                CheckBox cb = (CheckBox) dialog.findViewById(R.id.checkbox);
                RadioButton rbHigh = (RadioButton) dialog.findViewById(R.id.high);
                RadioButton rbNormal = (RadioButton) dialog.findViewById(R.id.normal);
                RadioButton rbLow = (RadioButton) dialog.findViewById(R.id.low);
                LinearLayout lv = (LinearLayout) dialog.findViewById(R.id.linearLayout);
                TextView tv = (TextView) dialog.findViewById(R.id.Remainder);
                tv.setVisibility(View.GONE);
                lv.setVisibility(View.GONE);
                if (td.getToDoTaskPrority().matches("Normal")) {
                    rbNormal.setChecked(true);
                } else if (td.getToDoTaskPrority().matches("Low")) {
                    rbLow.setChecked(true);
                } else {
                    rbHigh.setChecked(true);
                }
                if (td.getToDoTaskStatus().matches("Complete")) {
                    cb.setChecked(true);
                }
                todoText.setText(td.getToDoTaskDetails());
                todoNote.setText(td.getToDoNotes());
                interval.setText(td.getInterval());
                startDate1.setText((td.getStartDate1().toString()));
                endDate1.setText ((td.getEndDate1().toString()));

                Button save = (Button) dialog.findViewById(R.id.btn_save);
                Button cancel = (Button) dialog.findViewById(R.id.btn_cancel);
                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
                save.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        EditText todoText = (EditText) dialog.findViewById(R.id.input_task_desc);
                        EditText todoNote = (EditText) dialog.findViewById(R.id.input_task_notes);

                       /* EditText interval = (EditText) dialog.findViewById(R.id.interval);
                        EditText startDate1 = (EditText) dialog.findViewById(R.id.startDate1);
                        EditText endDate1 = (EditText) dialog.findViewById(R.id.endDate1);*/
                        CheckBox cb = (CheckBox) dialog.findViewById(R.id.checkbox);

//                        Log.d("check", "save clicked");

//                        try {
                            if (todoText.getText().length() >= 2) {


                                RadioGroup proritySelection = (RadioGroup) dialog.findViewById(R.id.toDoRG);
                                String RadioSelection = new String();
                                if (proritySelection.getCheckedRadioButtonId() != -1) {
                                    int id = proritySelection.getCheckedRadioButtonId();
                                    View radiobutton = proritySelection.findViewById(id);
                                    int radioId = proritySelection.indexOfChild(radiobutton);
                                    RadioButton btn = (RadioButton) proritySelection.getChildAt(radioId);
                                    RadioSelection = (String) btn.getText();
                                }


                                ToDoData updateTd = new ToDoData();
                                updateTd.setToDoID(td.getToDoID());
                                updateTd.setToDoTaskDetails(todoText.getText().toString());
                                updateTd.setToDoTaskPrority(RadioSelection);
                                updateTd.setToDoNotes(todoNote.getText().toString());

                                /*updateTd.setInterval(interval.getText().toString());
                                updateTd.setStartDate1(startDate1.getText().toString());*/

                                /*updateTd.setStartDate1((Date)new SimpleDateFormat("dd/mm/yyyy").parse(startDate1.getText().toString()));
                                updateTd.setEndDate1((Date)new SimpleDateFormat("dd/mm/yyyy").parse(endDate1.getText().toString()));*/

                                if (cb.isChecked()) {
                                    updateTd.setToDoTaskStatus("Complete");
                                } else {
                                    updateTd.setToDoTaskStatus("Incomplete");
                                }

                                SqliteHelper mysqlite = new SqliteHelper(view.getContext());
                                Cursor b = mysqlite.updateTask(updateTd);
                                ToDoDataArrayList.set(position, updateTd);


//                                Log.d("check", "cursor count: " + b.getCount());

                                if (b.getCount() == 0) {
                                    //Toast.makeText(view.getContext(), "Some thing went wrong", Toast.LENGTH_SHORT).show();
                                    new Handler().post(new Runnable() {
                                        @Override
                                        public void run() {
                                            // Code here will run in UI thread
                                            notifyDataSetChanged();
                                        }
                                    });
                                    dialog.hide();
                                } else {
                                    dialog.hide();
                                }

                            } else {
                                Toast.makeText(view.getContext(), "Please enter medication", Toast.LENGTH_SHORT).show();
                            }

                        /*} catch (ParseException ex) {
                            ex.printStackTrace();
                        }*/
                    }
                });
            }
        });


    }


    @Override
    public int getItemCount() {
        return ToDoDataArrayList.size();
    }

    public class ToDoListViewHolder extends RecyclerView.ViewHolder {
        TextView todoDetails, todoNotes;
        EditText interval, startDate1, endDate1;
        ImageButton proprityColor;
        ImageView edit, deleteButton;
        ToDoData toDoData;

        public ToDoListViewHolder(View view, final Context context) {
            super(view);
            todoDetails = (TextView) view.findViewById(R.id.toDoTextDetails);
            todoNotes = (TextView) view.findViewById(R.id.toDoTextNotes);
           /* interval = (EditText)  view.findViewById(R.id.interval);
            startDate1 = (EditText)  view.findViewById(R.id.startDate1);
            endDate1 = (EditText)  view.findViewById(R.id.endDate1);*/
            proprityColor = (ImageButton) view.findViewById(R.id.typeCircle);
            edit = (ImageView) view.findViewById(R.id.edit);
            deleteButton = (ImageView) view.findViewById(R.id.delete);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // Locate the EditText in listview_main.xml
                }
            });
        }
    }
}
