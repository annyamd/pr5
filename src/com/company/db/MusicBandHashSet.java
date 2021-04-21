package com.company.db;

import com.company.model.MusicBand;

import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

public class MusicBandHashSet extends HashSet<MusicBand> { //??? change name???

    private Date initTime;

    public MusicBandHashSet(){
        initTime = new Date();
    }

    public MusicBandHashSet(List<MusicBand> list){
        this();
        addAll(list);
    }

    public MusicBand findById(int id){
        Iterator<MusicBand> iterator = iterator();
        while (iterator.hasNext()){
            MusicBand m = iterator.next();
            if (m.getId() == id){
                return m;
            }
        }
        return null;
    }

    public boolean removeById(long id){
        Iterator<MusicBand> iterator = iterator();
        while (iterator.hasNext()){
            MusicBand m = iterator.next();
            if (m.getId() == id){
                iterator.remove();
                return true;
            }
        }
        return false;
    }

    public String getInfo(){
        return "collection type: " + getClass().toString() + ", count of elements: " + size()
                + ", init time: " + initTime;
    }

}
