package com.company.model;

import com.company.exceptions.InflateException;
import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvDate;
import com.opencsv.bean.CsvRecurse;
import com.opencsv.bean.validators.PreAssignmentValidator;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Formatter;

////пока сортирует только по id

public class MusicBand implements Comparable<MusicBand>{

    private static long idCounter = 0;

    public MusicBand() {
        id = idCounter++;
        LocalDateTime now = LocalDateTime.now();
    }

    @CsvBindByName
    private long id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    @CsvBindByName
    private String name; //Поле не может быть null, Строка не может быть пустой
    @CsvRecurse
    private Coordinates coordinates; //Поле не может быть null
    @CsvBindByName(column = "creation_date")
    @CsvDate("yyyy-MM-dd'T'HH:mm")
    private java.time.LocalDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    @CsvBindByName(column = "number_of_participants")
    private int numberOfParticipants; //Значение поля должно быть больше 0
    @CsvBindByName
    private MusicGenre genre; //Поле может быть null
    @CsvRecurse
    private Studio studio; //Поле может быть null

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public int getNumberOfParticipants() {
        return numberOfParticipants;
    }

    public MusicGenre getGenre() {
        return genre;
    }

    public Studio getStudio() {
        return studio;
    }

    public void setName(String name) throws InflateException{
        if (name == null || name.equals("")) throw new InflateException();
        this.name = name;
    }

    public void setCoordinates(Coordinates coordinates) throws InflateException{
        if (coordinates == null) throw new InflateException();
        this.coordinates = coordinates;
    }

    public void setId(long id) throws InflateException{
        if (id <= 0)  throw new InflateException();
        this.id = id;
    }

    public void setCreationDate(LocalDateTime creationDate) throws InflateException{
        if (creationDate == null)  throw new InflateException();
        this.creationDate = creationDate;
    }

    public void setNumberOfParticipants(int numberOfParticipants) throws InflateException{
        if (numberOfParticipants <= 0)  throw new InflateException();
        this.numberOfParticipants = numberOfParticipants;
    }

    public void setGenre(MusicGenre genre){
        this.genre = genre;
    }

    public void setStudio(Studio studio){
        this.studio = studio;
    }


    @Override
    public int compareTo(MusicBand o) {
        if (o == null) return 2;
        if ((id - o.id) > 0) return 1;
        else if (id == o.id) return 0;
        else return -1;
    }

    @Override
    public String toString() {
        return "MusicBand{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", coordinates=" + coordinates +
                ", creationDate=" + creationDate +
                ", numberOfParticipants=" + numberOfParticipants +
                ", genre=" + genre +
                ", studio=" + studio +
                '}';
    }
}
