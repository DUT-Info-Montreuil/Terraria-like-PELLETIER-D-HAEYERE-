package Terraria.modele;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

//liste acteur
public class Environnement {
    private ArrayList<JSONObject> layers;
    private Map<Long, String> infotiles = new HashMap<Long, String>();        // déplacer map dans le controllleur
    private JSONObject map;
    private int hauteur;
    private int largeur;
    private Joueur joueur1;

    public ArrayList<JSONObject> getLayers() {
        return layers;
    }

    public Map<Long, String> getInfotiles() {
        return infotiles;
    }

    public JSONObject getMap() {
        return map;
    }

    public ArrayList<Tile> getListeTiles() { //pas d'image dans l'environnement
        return listeTiles;
    }

    private ArrayList<Tile> listeTiles = new ArrayList<>();

    public Environnement() {

    }


    public int getHauteur() {
        return hauteur;
    }

    public int getLargeur() {
        return largeur;
    }

    public void loadMap(String path) {
        //JSON parser object to parse read file
        JSONParser jsonParser = new JSONParser();

        //  src/main/resources/Map/map.json
        try (FileReader reader = new FileReader(path)) {
            //Read JSON file
            Object obj = jsonParser.parse(reader);

            map = (JSONObject) obj;
            System.out.println(map);
            System.out.println("Map loaded");


            JSONArray layers = (JSONArray) map.get("layers");
            JSONObject layer = (JSONObject) layers.get(0);
            hauteur = ((Long) layer.get("height")).intValue();
            largeur = ((Long) layer.get("width")).intValue();


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public void loadTileInfo() {
        JSONArray tileSets = (JSONArray) map.get("tilesets");
        JSONObject confTilesSet = (JSONObject) tileSets.get(0);

        JSONArray tiles = (JSONArray) confTilesSet.get("tiles");
        for (int i = 0; i < tiles.size(); i++) {
            JSONObject a = (JSONObject) tiles.get(i);
            infotiles.put((Long) a.get("id") + 1, (String) a.get("image"));

        }
        System.out.println("les tiles info sont charger");
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
        JSONArray tilesets = (JSONArray) map.get("tilesets");
        JSONObject confTilesSet = (JSONObject) tilesets.get(0);

        JSONArray tiles = (JSONArray) confTilesSet.get("tiles");
        for (int i = 0; i < tiles.size(); i++) {
            JSONObject a = (JSONObject) tiles.get(i);
            int b = ((Long) a.get("id")).intValue();
            String c = (String) a.get("image");
            int d = ((Long) a.get("imageheight")).intValue();
            int e = ((Long) a.get("imageheight")).intValue();

            listeTiles.add(new Tile(b, c, d, e));
        }
        System.out.println("les tiles sont charger");
    }

    public void setJoueur1(Joueur joueur1) {
        this.joueur1 = joueur1;
    }

    public Joueur getJoueur1() {
        return joueur1;
    }
}




