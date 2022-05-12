package Terraria.modele;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.*;


public class Environnement {
    private  int world[];
    public Environnement() {
        JSONParser parser = new JSONParser();
        File resourceFile = new File("src/Terraria/vue/terrain.json");
        try (Reader reader = new FileReader(resourceFile)) {

            JSONObject jsonObject = (JSONObject) parser.parse(reader);
            System.out.println(jsonObject);
            System.out.println("-----");
            JSONObject name =  (JSONObject) jsonObject.get("conf");
            JSONObject material = (JSONObject) name.get("material");
            String test = (String) material.get("0") ;
            System.out.println(test);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        this.world = new int[4096];
    }
}
