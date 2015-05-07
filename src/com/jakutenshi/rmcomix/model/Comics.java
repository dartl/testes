package com.jakutenshi.rmcomix.model;

/**
 * Created 19.04.2015.
 */
public class Comics {
    private String name; /* Название комикса */
    private String author; /* Автор комикса */
    private int year; /* Год издания */
    private String publishingHouse; /* Издательство */
    private String genre; /* Жанр */
    private String language; /* Язык */
    private String url; /* Путь до комикса */

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getPublishingHouse() {
        return publishingHouse;
    }

    public void setPublishingHouse(String publishingHouse) {
        this.publishingHouse = publishingHouse;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Comics comics = (Comics) o;

        if (!name.equals(comics.name)) return false;
        if (!url.equals(comics.url)) return false;

        return true;
    }

    public Comics(String url, String name) {
        this.url = url;
        this.name = name;
    }

    public Comics() {
    }

    @Override
    public String toString() {
        return "Comics{" +
                "name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", year=" + year +
                ", publishingHouse='" + publishingHouse + '\'' +
                ", genre='" + genre + '\'' +
                ", language='" + language + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
