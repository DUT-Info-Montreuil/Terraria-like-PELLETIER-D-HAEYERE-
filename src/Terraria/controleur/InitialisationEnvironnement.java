package Terraria.controleur;

import javafx.scene.image.Image;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class InitialisationEnvironnement {
    public static JSONObject loadMap(String path) {
        JSONParser jsonParser = new JSONParser();

        //  src/main/resources/Map/map.json
        try (FileReader reader = new FileReader(path)) {
            //Read JSON file
            Object obj = jsonParser.parse(reader);

            JSONObject map = (JSONObject) obj;
            System.out.println(map);
            System.out.println("Map loaded");
            return map;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }



}



