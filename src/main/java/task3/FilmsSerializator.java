package task3;

import lombok.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by anton on 23.10.17.
 */
public class FilmsSerializator {
    public static void main(String[] args) {
        ArrayList<Film> films = new ArrayList<>();
        ArrayList<Film> readFilms = new ArrayList<>();
        String pathToFilmsCollection = "src/main/resources/task3/films";
        Film f1 = new Film("Star wars |");
        Film f2 = new Film("Dragon way");
        Actor brusli = new Actor("Brus", "Li", 26, true);
        Actor unknownActor = new Actor("James", "-", 45, true);
        f1.addActor(brusli);
        f1.addActor(unknownActor);
        f2.addActor(unknownActor);
        films.add(f1);
        films.add(f2);
        writeFilmsToFile(films, pathToFilmsCollection);
        readFilms = readFilmsFromFile(pathToFilmsCollection);
        System.out.println("Before editing:\n" + readFilms);
        Film newFilm = new Film("Ancient times");
        newFilm.addActor(new Actor("Dagni", "Taggert", 27, false));
        readFilms.add(newFilm);
        System.out.println("After editing:\n" + readFilms);

    }

    public static void writeFilmsToFile(List<Film> films, String path){
        try( ObjectOutputStream outSt = new ObjectOutputStream(new FileOutputStream(path))){
            outSt.writeObject(films);
        }catch(IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static ArrayList<Film> readFilmsFromFile(String path){
        try(ObjectInputStream inSt = new ObjectInputStream(new FileInputStream(path))){
            ArrayList<Film> films = (ArrayList<Film>)inSt.readObject();
            return films;
        }catch(IOException ex){
            throw new RuntimeException(ex);
        }catch(ClassNotFoundException ex){
            throw new RuntimeException(ex);
        }

    }
}


class Film implements Serializable{
    private ArrayList<Actor> actors;
    private String name;

    Film(String name){
        this.name = name;
        actors = new ArrayList<>();
    }

    public void addActor(Actor actor){
        actors.add(actor);
    }

    public boolean removeActor(int ind){
        if (actors.contains(ind)){
            actors.remove(ind);
            return true;
        }
        else{
            return false;
        }
    }

    public Actor getActor(int ind){
        return actors.get(ind);
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Film: ");
        sb.append(name);
        sb.append("\n");
        sb.append("Actors: \n");
        actors.forEach(actor -> sb.append(actor + "\n"));
        return sb.toString();
    }
}

@Data
class Actor implements Serializable {
    private String firstName;
    private String lastName;
    private int age;
    private boolean sex;

    public Actor(String fName, String lName, int age, boolean sex){
        this.firstName = fName;
        this.lastName = lName;
        this.age = age;
        this.sex = sex;
    }// false - female, true - male
}