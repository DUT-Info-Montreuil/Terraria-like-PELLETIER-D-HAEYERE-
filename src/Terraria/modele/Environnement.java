package Terraria.modele;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


public class Environnement {
    private ArrayList<JSONObject> layers;
    private Map<Long , String> infotiles = new HashMap<Long , String>();
    private JSONObject map ;

    public ArrayList<JSONObject> getLayers() {
        return layers;
    }

    public Map<Long, String> getInfotiles() {
        return infotiles;
    }

    public JSONObject getMap() {
        return map;
    }

    public ArrayList<Tile> getListeTiles() {
        return listeTiles;
    }

    private ArrayList<Tile> listeTiles = new ArrayList<>() ;

    public Environnement() {




    }


    public void loadMap (String path){
        //JSON parser object to parse read file
        JSONParser jsonParser = new JSONParser();

        //  src/main/resources/Map/map.json
        try (FileReader reader = new FileReader(path)) {
            //Read JSON file
            Object obj = jsonParser.parse(reader);

            map = (JSONObject) obj;
            System.out.println(map);
            System.out.println("Map loaded");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public void loadTileInfo() {
        JSONArray tilesets = (JSONArray) map.get("tilesets");
        JSONObject miniTiles = (JSONObject) tilesets.get(0) ;
        System.out.println(miniTiles.toJSONString());
        JSONArray superMiniTile = (JSONArray) miniTiles.get("tiles");
        for (int i = 0; i < superMiniTile.size(); i++) {
            JSONObject a = (JSONObject) superMiniTile.get(i);
            infotiles.put((Long) a.get("id"), (String) a.get("image")) ;

        }
        System.out.println("les tiles info sont charger");
    }

    public void loadLayers() {
        layers = new ArrayList<>();
        JSONArray layersData = (JSONArray) map.get("layers");

        Iterator<JSONObject> layersIterator =  layersData.iterator();

        while(layersIterator.hasNext()){
            JSONObject currentLayer = layersIterator.next();
            layers.add(currentLayer);
        }
        System.out.println("All layers loaded");
    }

    public void loadTile() {
        JSONArray tilesets = (JSONArray) map.get("tilesets");
        JSONObject miniTiles = (JSONObject) tilesets.get(0) ;
        System.out.println(miniTiles.toJSONString());
        JSONArray superMiniTile = (JSONArray) miniTiles.get("tiles");
        for (int i = 0; i < superMiniTile.size(); i++) {
            JSONObject a = (JSONObject) superMiniTile.get(i);
            Long b = (Long) a.get("id");
            String c = (String) a.get("image") ;
            Long d = (Long) a.get("imageheight") ;
            Long e = (Long) a.get("imageheight") ;

            listeTiles.add(new Tile(b ,c ,d, e)) ;
        }
        System.out.println("les tiles sont charger");
    }


    }




