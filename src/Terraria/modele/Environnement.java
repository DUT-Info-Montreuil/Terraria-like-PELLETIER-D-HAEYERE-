package Terraria.modele;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;


public class Environnement {
    private JSONObject layers;
    private  ArrayList <Integer> world;

    public Environnement() {
        JSONParser parser = new JSONParser();
        File resourceFile = new File("src/Terraria/vue/terrain.json");
        try (Reader reader = new FileReader(resourceFile)) {

            JSONObject jsonObject = (JSONObject) parser.parse(reader);
            JSONArray conf = (JSONArray) jsonObject.get("layers");
            Iterator<JSONObject> confIterator= conf.iterator();
            this.layers = confIterator.next();
            this.world = (ArrayList<Integer>) layers.get("data");
            System.out.println(world);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }
}
