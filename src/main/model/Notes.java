package model;

import java.util.HashMap;
import java.util.Map;

public class Notes {
    private Map<String,Integer> notesMap;

    public void Notes() {
        notesMap = new HashMap<>();
        notesMap.put("e1",10);
        notesMap.put("e2",15);
    }
}
