package sample.controller;

/*
Controller to handle the other controllers
 */

import javafx.scene.Scene;
import javafx.scene.layout.Pane;

import java.util.HashMap;

public class ViewController {

    private static HashMap<String, Pane> screenHolder = new HashMap<>(); //Hashmap holding all layouts with name and location of each resource
    private static Scene main; //The main scene Rootlayout

    //constructor
    public ViewController(Scene main) {
        this.main = main;
    }

    //Add to list
    public void addScreen(String name, Pane pane){
        screenHolder.put(name, pane);
    }

    //Activate layout
    public static void activate(String name){
        main.setRoot( screenHolder.get(name) );
    }




}