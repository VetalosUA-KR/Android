package com.example.mynotes;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "notes")
public class Note {
    //Помечаем id как primary key и ставим автоинкремент true
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String title;
    private String description;
    //private int dayOfWeak;
    //private int priority;

    public Note(int id, String title, String description/*, int dayOfWeak, int priority*/) {
        this.id = id;
        this.title = title;
        this.description = description;
        //this.dayOfWeak = dayOfWeak;
        //this.priority = priority;
    }

    //Для использования данного класса в базе данных он должен содержать только  конструктор
    //в котором содержаться все поля чтобы конструктор который ниже ему не мешал нужно добавить
    // (@Ignore)
    @Ignore
    public Note(String title, String description/*, int dayOfWeak, int priority*/) {
        this.title = title;
        this.description = description;
        //this.dayOfWeak = dayOfWeak;
        //this.priority = priority;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

   /* public int getDayOfWeak() {
        return dayOfWeak;
    }*/

    /*public int getPriority() {
        return priority;
    }*/

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    /*public void setDayOfWeak(int dayOfWeak) {
        this.dayOfWeak = dayOfWeak;
    }*/

    /*public void setPriority(int priority) {
        this.priority = priority;
    }*/


}
