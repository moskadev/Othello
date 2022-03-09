/*
 * TestJoueur.java                 23/05/2021
 * IUT de Rodez, pas de droit d'auteur
 */

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import othello.caracteristiques.Couleur;
import othello.caracteristiques.Position;
import othello.joueur.Joueur;
import othello.Plateau;
import othello.Pion;

/**
 * Tests unitaires de la classe Joueur
 * @author SIMON LAUNAY
 *         YOHANN MAY
 *         THEO MICHELLON
 *         MAXIME MOSKALYK 
 *         DAVID SIMONIN 
 *
 */
class TestJoueur {
    
    /**
     * Test de joueur valide 
     * Jeu de données pour les méthodes
     */
    public final Joueur[] VALIDES = {
        new Joueur("CRAZYMat", Couleur.BLANC),
        new Joueur("Ghalhyus", Couleur.NOIR),
        new Joueur("Guepaz", Couleur.NOIR),
        new Joueur("Mausaille", Couleur.BLANC),
        new Joueur("moska", Couleur.BLANC)
    };
    
    /**
     * Jeu de données pour la construction de plateau
     */
    final Pion[] ENSEMBLE1 = {
        new Pion(new Position(2, 3), Couleur.NOIR),
        new Pion(new Position(3, 3), Couleur.NOIR),
        new Pion(new Position(3, 4), Couleur.NOIR),
        new Pion(new Position(4, 3), Couleur.NOIR),
        new Pion(new Position(4, 4), Couleur.BLANC)
    };
    
    /**
     * Jeu de données pour la construction de plateau
     */
    final Pion[] ENSEMBLE2 = {
        new Pion(new Position(2, 3), Couleur.BLANC),
        new Pion(new Position(3, 3), Couleur.BLANC),
        new Pion(new Position(3, 4), Couleur.BLANC),
        new Pion(new Position(4, 2), Couleur.BLANC),
        new Pion(new Position(4, 3), Couleur.BLANC),
        new Pion(new Position(4, 4), Couleur.BLANC)
    };
    
    /**
     * Jeu de données pour la méthode getScore(Plateau)
     */
    final Plateau[] DAMIERS = {
            new Plateau(),
            new Plateau(ENSEMBLE1),
            new Plateau(ENSEMBLE2)
    };
    
    /**
     * Taille du jeu de données VALIDES
     */
    public final int TAILLE = VALIDES.length;

    @BeforeEach
    void setUp() throws Exception {
    }

    @AfterEach
    void tearDown() throws Exception {
    }
    
    /**
     * Test du constructeur Joueur(String, Couleur)
     */
    @Test
    void testJoueur() {
        /* Jeu de données contenant les valeurs invalides */
        final String[] PSEUDOS_INVALIDES = {
            null, "    ", null, "", "Jean"
        };
        
        final Couleur[] COULEURS_INVALIDES = {
                Couleur.NOIR, Couleur.BLANC, null, null, null
            };
        
        /* Test avec un des données invalides */
        for (int jdd = 0 ; jdd < PSEUDOS_INVALIDES.length ; jdd++) {
            try {
                new Joueur(PSEUDOS_INVALIDES[jdd], COULEURS_INVALIDES[jdd]);
                fail();
            } catch (IllegalArgumentException echec) {
                
            }
        }
    }

    /**
     * Test de la méthode getPseudo()
     */
    @Test
    void testGetPseudo() {
        /* jeu de données avec les valeurs attendues */
        final String[] ATTENDUS= {
            "CRAZYMat",
            "Ghalhyus",
            "Guepaz",
            "Mausaille",
            "moska"
        };

        for (int jdd = 0; jdd < TAILLE ; jdd++) {
            assertEquals(ATTENDUS[jdd], VALIDES[jdd].getPseudo());
        }    
    }

    /**
     * Test de la méthode getCouleur()
     */
    @Test
    void testGetCouleur() {
        /* jeu de données avec les valeurs attendues */
        final Couleur[] ATTENDUS = { 
            Couleur.BLANC, Couleur.NOIR, Couleur.NOIR,
            Couleur.BLANC, Couleur.BLANC 
        };

        for (int jdd = 0; jdd < TAILLE ; jdd++) {
            assertEquals(ATTENDUS[jdd], VALIDES[jdd].getCouleur());
        }
    }

    /**
     * Test de la méthode PeutJouer
     */
    @Test
    void testPeutJouer() {
        assertTrue(VALIDES[0].peutJouer(DAMIERS[0]));
        assertTrue(VALIDES[1].peutJouer(DAMIERS[0]));
        assertTrue(VALIDES[2].peutJouer(DAMIERS[1]));
        assertTrue(VALIDES[3].peutJouer(DAMIERS[1]));
        assertFalse(VALIDES[4].peutJouer(DAMIERS[2]));
    }

    /**
     * Test de la méthode getScore(Plateau)
     */
    @Test
    void testGetScore() {
        /* jeu de données avec les valeurs attendues */
        final int[] ATTENDUS = { 2, 2, 4, 1, 6 };

        assertEquals(ATTENDUS[0], VALIDES[0].getScore(DAMIERS[0]));
        assertEquals(ATTENDUS[1], VALIDES[1].getScore(DAMIERS[0]));
        assertEquals(ATTENDUS[2], VALIDES[2].getScore(DAMIERS[1]));
        assertEquals(ATTENDUS[3], VALIDES[3].getScore(DAMIERS[1]));
        assertEquals(ATTENDUS[4], VALIDES[4].getScore(DAMIERS[2]));
    }

    /**
     * Test de la méthode toString()
     */
    @Test
    void testToString() {
        /* jeu de données avec les valeurs attendues */
        final String[] ATTENDUS = {
            "CRAZYMat",
            "Ghalhyus",
            "Guepaz",
            "Mausaille",
            "moska"
        };

        for (int jdd = 0; jdd < TAILLE ; jdd++) {
            assertEquals(ATTENDUS[jdd], VALIDES[jdd].toString());
        }
    }

    /**
     * Test de la méthode Equals()
     */
    @Test
    void testEqualsObject() {
        /* jeu de donnée copie de VALIDES */
        final Joueur[] COPIES_VALIDES = {
            new Joueur("CRAZYMat", Couleur.BLANC),
            new Joueur("Ghalhyus", Couleur.NOIR),
            new Joueur("Guepaz", Couleur.NOIR),
            new Joueur("Mausaille", Couleur.BLANC),
            new Joueur("moska", Couleur.BLANC)
        };

        for(int jdd = 0 ; jdd < TAILLE ; jdd++) {
            assertEquals(COPIES_VALIDES[jdd], VALIDES[jdd]);
        }
    }
}
