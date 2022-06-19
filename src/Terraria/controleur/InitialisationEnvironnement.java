package Terraria.controleur;


import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class InitialisationEnvironnement {
    public static JSONObject loadMap(String path) {
        JSONParser jsonParser = new JSONParser();


        try (FileReader reader = new FileReader(path)) {

            Object obj = jsonParser.parse(reader);

            JSONObject map = (JSONObject) obj;

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



