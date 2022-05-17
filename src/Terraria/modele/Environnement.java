package Terraria.modele;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

//liste acteur
public class Environnement {
    private ArrayList<JSONObject> layers;

    private JSONObject map;
    private int hauteur;
    private int largeur;
    private ArrayList<Integer> terrain;
    private Joueur joueur1;


    public ArrayList<JSONObject> getLayers() {
        return layers;
    }


    public JSONObject getMap() {
        return map;
    }


    public Environnement(JSONObject map) {
        this.map = map;
        this.terrain=new ArrayList<Integer>();
        JSONArray layers = (JSONArray) map.get("layers");
        JSONObject layer = (JSONObject) layers.get(0);
        this.hauteur = ((Long) layer.get("height")).intValue();
        this.largeur = ((Long) layer.get("width")).intValue();
        JSONArray listeMonde =(JSONArray) layer.get("data");
        for (int i = 0; i < listeMonde.size(); i++) {
            Long valeurI=(Long) listeMonde.get(i);
            this.terrain.add(  (valeurI.intValue()) );
        }
    }

    public ArrayList<Integer> getTerrain() {
        return terrain;
    }

    public void loadLayers() {
        layers = new ArrayList<>();
        JSONArray layersData = (JSONArray) map.get("layers");

        Iterator<JSONObject> layersIterator = layersData.iterator();

        while (layersIterator.hasNext()) {
            JSONObject currentLayer = layersIterator.next();
            layers.add(currentLayer);
        }
        System.out.println("All layers loaded");
    }

    public int getHauteur() {
        return hauteur;
    }

    public int getLargeur() {
        return largeur;
    }

    public void setJoueur1(Joueur joueur1) {
        this.joueur1 = joueur1;
    }

    public Joueur getJoueur1() {
        return joueur1;
    }
    }




