package Terraria.modele;

import Terraria.controleur.InitialisationEnvironnement;
import Terraria.modele.Acteur.Joueur;
import Terraria.modele.Item.Pioche;
import junit.framework.TestCase;
import org.junit.Test;

public class JoueurTest extends TestCase {

    Environnement e2 = new Environnement(InitialisationEnvironnement.loadMap("ress/terrainPlayerTest.json"));
    Pioche piocheDep = new Pioche(250, 10, e2);
    Joueur heroTest = new Joueur(10, 5, 2*16, 5*16+2, e2,  new HitBox(50, 30, 24, 14, true), piocheDep);
    public void setUp(){
        e2.addActeur(heroTest);
    }


    @Test
    public void testCheckDistanceInReach() {
        assertTrue(heroTest.checkDistanceInReach(60 , 50));
        assertFalse(heroTest.checkDistanceInReach(150 , 50));
    }

    @Test
    public void testTakeDomage() {
        assertEquals(10 , heroTest.getPv());
        heroTest.takeDomage(1);
        assertEquals(9 , heroTest.getPv());
        heroTest.takeDomage(4);
        assertEquals(5 , heroTest.getPv());
    }



}