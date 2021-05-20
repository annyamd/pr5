package com.company.verifiers.id;

import java.util.ArrayList;
import java.util.List;

public class IdHandler<T extends Identifiable> {
    private List<Long> created;
    private long idPointer = 0;

    protected IdHandler(){
        created = new ArrayList<>();
    }

    public long getNewId(){
        boolean found = false;
        while (!found) {
            idPointer++;
            found = true;
            for (int i = 0; i < created.size(); i++) {
                if (created.get(i) == idPointer){
                    found = false;
                    break;
                }
            }
        }
        created.add(idPointer);
        return idPointer;
    }

    public boolean getReceivedIdIfAllowed(long id){ //предоставляет id, если такой еще не занят
        for (int i = 0; i < created.size(); i++) {
            if (created.get(i) == id) {
                return false;
            }
        }
        created.add(id);
        return true;
    }
}
