package Terraria.modele;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;

import java.util.Iterator;


//liste acteur
public class Environnement {
    private ArrayList<JSONObject> layers;

    private JSONObject map;
    private int hauteur;
    private int largeur;
    private ArrayList<Integer> terrain;
    private ArrayList<Tile> allTiles;
    private Joueur joueur1;
    private JSONArray tiles;

    public ArrayList<Tile> getAllTiles() {
        return allTiles;
    }

    public ArrayList<JSONObject> getLayers() {
        return layers;
    }


    public JSONObject getMap() {
        return map;
    }


    public Environnement(JSONObject map) {
        this.map = map;
        this.terrain = new ArrayList<Integer>();
        JSONArray layers = (JSONArray) map.get("layers");
        JSONObject layer = (JSONObject) layers.get(0);
        this.hauteur = ((Long) layer.get("height")).intValue();
        this.largeur = ((Long) layer.get("width")).intValue();
        JSONArray listeMonde = (JSONArray) layer.get("data");
        for (int i = 0; i < listeMonde.size(); i++) {
            Long valeurI = (Long) listeMonde.get(i);
            this.terrain.add((valeurI.intValue()));
        }
        JSONArray tileSets = (JSONArray) map.get("tilesets");
        JSONObject subtileSets = (JSONObject) tileSets.get(0);
        this.tiles = (JSONArray) subtileSets.get("tiles");
        this.loadTile();
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

    public void loadTile() {
        JSONArray tiles = this.tiles;
        this.allTiles = new ArrayList<>();

        for (int i = 0; i < tiles.size(); i++) {
            JSONObject a = (JSONObject) tiles.get(i);
            int id = ((Long) a.get("id")).intValue();
            String imagePath = (String) a.get("image");
            int height = ((Long) a.get("imageheight")).intValue();
            int width = ((Long) a.get("imagewidth")).intValue();
            JSONArray property = (JSONArray) a.get("properties");
            JSONObject subProperty = (JSONObject) property.get(0);
            boolean collide = (boolean) subProperty.get("value");
            JSONObject objectgroup = (JSONObject) a.get("objectgroup") ;
            JSONArray objects = (JSONArray) objectgroup.get("objects");
            JSONObject hitBox  = (JSONObject) objects.get(0) ;



            this.allTiles.add(new Tile(id, imagePath, height, width, new HitBox(0 , 0 , ((Long) hitBox.get("height")).intValue() , ((Long) hitBox.get("width")).intValue(), collide)));

        }
    }

        public int getHauteur () {
            return hauteur;
        }

        public int getLargeur () {
            return largeur;
        }

        public void setJoueur1 (Joueur joueur1){
            this.joueur1 = joueur1;
        }

        public Joueur getJoueur1 () {
            return joueur1;
        }
    }





