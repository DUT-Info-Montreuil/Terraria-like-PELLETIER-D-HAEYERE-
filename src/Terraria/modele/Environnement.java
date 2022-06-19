package Terraria.modele;

import Terraria.modele.Acteur.Acteur;
import Terraria.modele.Acteur.Ennemi;
import Terraria.modele.Acteur.Joueur;
import Terraria.modele.Item.OnGroundItem;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
    private ArrayList<Block> allBlock;
    private ArrayList<Integer> terrain;
    private ArrayList<Tile> allTiles;

    private Joueur joueur1;
    private JSONArray tiles;

    private ObservableList<Acteur>listActeur;
    private ObservableList<OnGroundItem> OnGroundItemList ;
    private ArrayList<Ennemi> listEnnemi = new ArrayList<>();




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

        this.listActeur = FXCollections.observableArrayList();
        this.OnGroundItemList = FXCollections.observableArrayList();
        this.allBlock = new ArrayList<>();
    }

    public ArrayList<Block> getAllBlock() {
        return allBlock;
    }
    public void addBlock(Block b){
        this.allBlock.add(b);
    }

    public ObservableList<Acteur> getListActeur() {
        return listActeur;
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

    }

    public void addActeur(Acteur a) {
        listActeur.add(a);
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
            JSONObject objectgroup = (JSONObject) a.get("objectgroup");
            JSONArray objects = (JSONArray) objectgroup.get("objects");
            JSONObject hitBox = (JSONObject) objects.get(0);


            this.allTiles.add(new Tile(id, imagePath, height, width, new HitBox(0, 0, ((Long) hitBox.get("height")).intValue(), ((Long) hitBox.get("width")).intValue(), collide)));

        }
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

    public void changementTerrain(int tileSupr, int tileAdd) {
        this.terrain.remove(tileSupr);
        this.terrain.add(tileSupr, tileAdd);
    }

    @Override
    public String toString() {
        for (int i = 0; i < this.terrain.size(); i++) {
            if (i % 50 == 0) {
                System.out.print("\n");
            }
            System.out.print(terrain.get(i) + "\t");
        }
        return "Environnement{" +
                "layers=" + layers +
                ", map=" + map +
                ", hauteur=" + hauteur +
                ", largeur=" + largeur +
                ", allBlock=" + allBlock +
                ", terrain=" + terrain +
                ", allTiles=" + allTiles +
                ", joueur1=" + joueur1 +
                ", tiles=" + tiles +
                ", listActeur=" + listActeur +
                ", OnGroundItemList=" + OnGroundItemList +
                ", listEnnemi=" + listEnnemi +
                '}';

    }

  
    public ArrayList<Ennemi> getListEnnemi() {

        return listEnnemi;
    }

    public void addEnnemi(Ennemi e) {
        listEnnemi.add(e);
    }


    public ObservableList<OnGroundItem> getOnGroundItem(){
        return OnGroundItemList;
    }
}





