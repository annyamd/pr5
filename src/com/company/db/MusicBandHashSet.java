package com.company.db;

import com.company.model.MusicBand;
import com.company.commands.templer.Command;

import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

/**
 * Expanded {@code HashSet}, which stores {@code MusicBand} Objects
 * Receiver for Commands
 * @see MusicBand
 * @see Command
 */

public class MusicBandHashSet extends HashSet<MusicBand> {

    /**
     * The date of the initialization of {@code MusicBandHashSet}
     */
    private Date initTime;

    public MusicBandHashSet(){
        initTime = new Date();
    }

    public MusicBandHashSet(List<MusicBand> list){
        this();
        addAll(list);
    }

    /**
     * Removes the {@code MusicBand} in the set by received id. Returns true if the object was found by id and removed;
     * false - if {@code MusicBandHashSet} doesn't have an object with this id.
     *
     * @param id id of the removing object
     * @return {@code true} or {@code false}: if the operation finished successfully
     */
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

    /**
     * contains: collection type, count of elements, the date of the initialization
     * @return information of {@code MusicBandHashSet} object in a {@code String}
     */
    public String getInfo(){
        return "Collection type: " + getClass().toString() + ", count of elements: " + size()
                + ", init time: " + initTime;
    }

}
