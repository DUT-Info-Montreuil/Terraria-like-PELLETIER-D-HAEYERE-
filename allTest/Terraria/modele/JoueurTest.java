package Terraria.modele;

import Terraria.controleur.InitialisationEnvironnement;
import Terraria.modele.Acteur.Joueur;
import Terraria.modele.Item.Pioche;
import junit.framework.TestCase;
import org.junit.Test;

public class JoueurTest extends TestCase {

    Environnement e1 = new Environnement(InitialisationEnvironnement.loadMap("ress/terrain3.json"));
    Pioche piocheDep = new Pioche(250, 10, e1);
    Joueur hero = new Joueur(10, 5, 50, 50, e1,  new HitBox(50, 30, 24, 14, true), piocheDep);
    public void setUp(){
        e1.addActeur(hero);
    }

    @Test
    public void testCollideGaucheDroite() {

    }
    @Test
    public void testCollideHautBas() {

    }
    @Test
    public void testCheckDistanceInReach() {
        assertTrue(hero.checkDistanceInReach(60 , 50));
        assertFalse(hero.checkDistanceInReach(150 , 50));
    }

    @Test
    public void testTakeDomage() {
        assertEquals(10 , hero.getPv());
        hero.takeDomage(1);
        assertEquals(9 , hero.getPv());
        hero.takeDomage(4);
        assertEquals(5 , hero.getPv());
    }


    @Test
    public void testUpdateCoeur() {

    }
}