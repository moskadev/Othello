/*
 * TestOutilSauvegarde.java                                24 mai 2021
 * IUT info1 2020-2021, pas de copyright pas de droit d'auteur
 */

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import othello.joueur.*;
import othello.Plateau;
import othello.caracteristiques.Couleur;
import othello.Partie;
import othello.outils.OutilSauvegarde;

/** 
 * Tests unitaires de la classe OutilSauvegarde
 * @author SIMON LAUNAY
 *         YOHANN MAY
 *         THEO MICHELLON
 *         MAXIME MOSKALYK 
 *         DAVID SIMONIN 
 */
class TestOutilSauvegarde {
    
    /**
     * Jeu de données contenant des joueurs
     */
    public final Joueur[] JOUEURS = {
            new Joueur("moska", Couleur.NOIR),
            new Joueur("Mausaille", Couleur.BLANC)
    };
    
    /**
     * Jeu de donnée contenant un plateau 
     */
    public final Plateau DAMIER = new Plateau();
    
    /**
     * Jeu de donnée contenant des 
     */
    public final Partie PARTIE = new Partie(JOUEURS[0], JOUEURS[1], DAMIER);
    
    final String[] NOMS = new String[]{ "null", "Save1", "TestSave1", 
                                        "TestSave2" };

    @BeforeEach
    void setUp() throws Exception {
    }

    @AfterEach
    void tearDown() throws Exception {
    }

    /**
     * Test method for {@link othello.outils.OutilSauvegarde
     *                  #sauvegarder(othello.Partie, java.lang.String)}.
     */
    @Test
    void testSauvegarder() {
        /* Test avec un nom de fichier valide */
        assertTrue(OutilSauvegarde.sauvegarder(PARTIE, "TestSave2")
                   && OutilSauvegarde.charger("TestSave2").equals(PARTIE));
        
        /* Test avec un nom de fichier invalide */
        assertFalse(OutilSauvegarde.sauvegarder(PARTIE, ""));
        assertFalse(OutilSauvegarde.sauvegarder(PARTIE, null));
    }

    /**
     * Test method for {@link othello.outils.OutilSauvegarde
     * #sauvegardeExiste(java.lang.String)}.
     */
    @Test
    void testSauvegardeExiste() {
        /* Test avec un nom de fichier valide */
        assertTrue(OutilSauvegarde.sauvegardeExiste("TestSave1"));
        
        /* Test avec des noms de fichiers invalides */
        assertFalse(OutilSauvegarde.sauvegardeExiste("Save1484848"));
        assertFalse(OutilSauvegarde.sauvegardeExiste(null));
    }

    /**
     * Test method for {@link othello.outils.OutilSauvegarde
     *                  #charger(java.lang.String)}.
     */
    @Test
    void testCharger() {
        /* Test avec un nom de fichier valide */
        assertTrue(PARTIE.equals(OutilSauvegarde.charger("TestSave1")));
        
        /* Test avec des noms de fichiers invalides */
        assertTrue(OutilSauvegarde.charger("Save1484848") == null);
        assertTrue(OutilSauvegarde.charger(null) == null);
    }

    /**
     * Test method for {@link othello.outils.OutilSauvegarde
     *                  #getSauvegardes()}.
     */
    @Test
    void testGetSauvegardes() {
        OutilSauvegarde.getSauvegardes().forEach(fichier -> {
            if(Arrays.stream(NOMS).noneMatch(
                    nom -> fichier.getName().equals(nom))){
                fail();
            }
        });
    }

}
