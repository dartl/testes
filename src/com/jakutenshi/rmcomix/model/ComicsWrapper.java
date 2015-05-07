package com.jakutenshi.rmcomix.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.HashMap;

@XmlRootElement(name = "comicses")
public class ComicsWrapper {
    private HashMap<String,Comics> comics;

    @XmlElement(name = "person")
    public HashMap<String,Comics> getComicses() {
        return comics;
    }

    public void setComicses(HashMap<String,Comics> comics) {
        this.comics = comics;
    }
}
