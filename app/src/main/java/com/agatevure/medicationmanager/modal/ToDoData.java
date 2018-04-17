package com.agatevure.medicationmanager.modal;

import java.util.Date;

/**
 * Created by deepmetha on 8/28/16.
 */
public class ToDoData {
    int ToDoID;
    String ToDoTaskDetails;
    String ToDoTaskPrority;
    String ToDoTaskStatus;
    String ToDoNotes;
    String interval;
    Date startDate1,endDate1;


    public int getToDoID() {
        return ToDoID;
    }

    public void setToDoID(int toDoID) {
        ToDoID = toDoID;
    }

    public String getToDoTaskDetails() {
        return ToDoTaskDetails;
    }

    public void setToDoTaskDetails(String toDoTaskDetails) {
        ToDoTaskDetails = toDoTaskDetails;
    }

    public String getToDoTaskPrority() {
        return ToDoTaskPrority;
    }

    public void setToDoTaskPrority(String toDoTaskPrority) {
        ToDoTaskPrority = toDoTaskPrority;
    }

    public String getToDoTaskStatus() {
        return ToDoTaskStatus;
    }

    public void setToDoTaskStatus(String toDoTaskStatus) {
        ToDoTaskStatus = toDoTaskStatus;
    }

    public String getToDoNotes() {
        return ToDoNotes;
    }

    public void setToDoNotes(String toDoNotes) {
        ToDoNotes = toDoNotes;
    }


    public String getInterval() {
        return interval;
    }

    public void setInterval(String interval) {
        this.interval = interval;
    }

    public Date getStartDate1() {
        return startDate1;
    }

    public void setStartDate1(Date startDate1) {
        this.startDate1 = startDate1;
    }

    public Date getEndDate1() {
        return endDate1;
    }

    public void setEndDate1(Date endDate1) {
        this.endDate1 = endDate1;
    }

    @Override
    public String toString() {
        return "ToDoData {id-" + ToDoID + ", taskDetails-" + ToDoTaskDetails + ", propity-" + ToDoTaskPrority + ", status-" + ToDoTaskStatus + ", notes-" + ToDoNotes + ", interval-" + interval + ", startDate1" + startDate1 + ", endDate1" + endDate1 + "}";
    }

}
