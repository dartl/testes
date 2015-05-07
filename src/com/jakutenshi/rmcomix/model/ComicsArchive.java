package com.jakutenshi.rmcomix.model;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

/**
 * Archive with comics in runtime. Create when program started.
 * Created by Akutenshi on 05.05.2015.
 */
public class ComicsArchive {
    /** HashMap with comics from Home directory.
     *  If comics exists in XML-file from the last time, write tags from XML
     * */
    HashMap<String, Comics> comicsMap = new HashMap<String, Comics>();

    public void setNameTag(String urlKey, String newName) {
        if (isComicsExistsByURL(urlKey)) {
            comicsMap.get(urlKey).setName(newName);
        }
    }

    public void setAuthorTag(String urlKey, String newAuthor) {
        if (isComicsExistsByURL(urlKey)) {
            comicsMap.get(urlKey).setName(newAuthor);
        }
    }

    public void setYearTag(String urlKey, String newYear) {
        if (isComicsExistsByURL(urlKey)) {
            comicsMap.get(urlKey).setName(newYear);
        }
    }

    public void setPublishingHouseTag(String urlKey, String newPublishingHouse) {
        if (isComicsExistsByURL(urlKey)) {
            comicsMap.get(urlKey).setName(newPublishingHouse);
        }
    }

    public void setGenreTag(String urlKey, String newGenre) {
        if (isComicsExistsByURL(urlKey)) {
            comicsMap.get(urlKey).setName(newGenre);
        }
    }

    public void setLanguageTag(String urlKey, String newLanguage) {
        if (isComicsExistsByURL(urlKey)) {
            comicsMap.get(urlKey).setName(newLanguage);
        }
    }

    public boolean isComicsExistsByURL(String urlKey) {
        if (comicsMap.containsKey(urlKey)) {
            return true;
        } else {
            return false;
        }
    }

    public void parseHomeDir(String url) {
        File homeDir = new File(url);
        File[] filesList = homeDir.listFiles();
        try {
            for (int i = 0; i < filesList.length; i++) {
                if (filesList[i].isDirectory()) {
                    comicsMap.put(filesList[i].getCanonicalPath(),
                            new Comics(filesList[i].getCanonicalPath(), filesList[i].getName()));
                }
            }

            int i = 1;
            for (String key : comicsMap.keySet()) {
                System.out.println(i + ". " + comicsMap.get(key).getName() + " " + key);
                i++;
            }
            System.out.println(getArchiveSize());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Comics getComicsByURLKey(String urlKey) {
        if (isComicsExistsByURL(urlKey)) {
            return comicsMap.get(urlKey);
        } else {
            return null;
        }
    }

    public int getArchiveSize() {
        return comicsMap.size();
    }

    /* Загрузка архива комиксов из xml */
    public void loadPersonDataFromFile() {
        try {
            File file = new File("res/ComicsArchive.xml");
            JAXBContext context = JAXBContext
                    .newInstance(ComicsWrapper.class);
            Unmarshaller um = context.createUnmarshaller();


            ComicsWrapper wrapper = (ComicsWrapper) um.unmarshal(file);

            comicsMap.clear();
            comicsMap = wrapper.getComicses();

        } catch (Exception e) { // catches ANY exception
        }
    }
    /* Конец */

    /* Сохранение архива комиксов в xml */
    public void savePersonDataToFile() {
        try {
            File file = new File("res/ComicsArchive.xml");
            JAXBContext context = JAXBContext
                    .newInstance(ComicsWrapper.class);
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            // Wrapping our person data.
            ComicsWrapper wrapper = new ComicsWrapper();
            wrapper.setComicses(comicsMap);

            // Marshalling and saving XML to the file.
            m.marshal(wrapper, file);

        } catch (Exception e) { // catches ANY exception
        }
    }
    /* Конец */

    @Override
    protected void finalize() throws Throwable {
        //TODO: Parse comicsMap to XML
    }

    @Override
    public String toString() {
        return "ComicsArchive{" +
                "comicsMap=" + comicsMap.toString() +
                '}';
    }
}
