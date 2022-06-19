package Terraria.modele.Item;

import Terraria.controleur.InitialisationEnvironnement;
import Terraria.modele.Block;
import Terraria.modele.Environnement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class PiocheTest {
    Environnement e1;
    Pioche piocheDep;
    ArrayList<Integer> testDestructionAir;
    ArrayList<Integer> testDestructionTile;
    ArrayList<Integer> testIdIncorrect;
    ArrayList<Block> testBlockonAir;
    ArrayList<Block> testBlockonTile;
    ArrayList<Block> testBlockIdIncorrect;

    @BeforeEach
    void setUp() {
        e1 = new Environnement(InitialisationEnvironnement.loadMap("ress/terrain3.json"));
        piocheDep = new Pioche(250, 10, e1);
        testDestructionAir = e1.getTerrain();
        testDestructionTile = e1.getTerrain();
        testDestructionTile.remove(1499);
        testDestructionTile.add(1);
        testIdIncorrect = e1.getTerrain();

        testBlockonAir = e1.getAllBlock();
        testBlockonTile = e1.getAllBlock();
        for (Block b : e1.getAllBlock()
        ) {
            if (b.getId() == 1449) {

                b.getBox().setHitBoxFals();
            }


        }
        testBlockIdIncorrect = e1.getAllBlock();

    }

    @Test
    void action() {
        piocheDep.action(0);

        assertEquals(testDestructionAir, e1.getTerrain(), "La pioche à détruit du ciel");
        assertEquals(testBlockonAir,e1.getAllBlock(),"Changement hitbox");
        piocheDep.action(1499);

        assertEquals(testDestructionTile, e1.getTerrain(), "La pioche n'a pas détruit la tuile");
        assertEquals(testBlockonTile,e1.getAllBlock(),"Changement hitbox");

        piocheDep.action(-5);
        assertEquals(testIdIncorrect, e1.getTerrain(), "La pioche à détruit un élément en dehors du terrain");
        assertEquals(testBlockIdIncorrect,e1.getAllBlock(),"Changement hitbox");
    }

    @Test
    void cielEstModifiable() {
        assertTrue(piocheDep.cielEstModifiable(e1.getTerrain().get(1499))); // coin en bas a droite = lave
        assertFalse(piocheDep.cielEstModifiable(e1.getTerrain().get(0))); //coin en haut a gauche = air
    }
}