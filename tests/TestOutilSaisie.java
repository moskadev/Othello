import java.util.Arrays;
import java.util.Scanner;

import static othello.outils.OutilSaisie.lireChaine;
import static othello.outils.OutilSaisie.saisieCoordonnees;

/**
 * Tests unitaires de la classe OutilSaisie
 *
 * @author SIMON LAUNAY
 *         YOHANN MAY
 *         THEO MICHELLON
 *         MAXIME MOSKALYK 
 *         DAVID SIMONIN 
 */
public class TestOutilSaisie {

    /**
     * Constante du message d'entrÃ©e de la saisie manuelle
     */
    private static final String MESSAGE_SAISIE = "Veuillez saisir une " +
            "coordonnÃ©e : ";
    /**
     * Saisie clavier
     */
    private static Scanner clavier = new Scanner(System.in);
    /**
     * Constante contenant toutes les coordonnÃ©es valides
     */
    private static final String[] COORDONNEE_VALIDES = {
            "A1", "B1", "C1", "D1", "E1", "F1", "G1", "H1",
            "A2", "B2", "C2", "D2", "E2", "F2", "G2", "H2",
            "A3", "B3", "C3", "D3", "E3", "F3", "G3", "H3",
            "A4", "B4", "C4", "D4", "E4", "F4", "G4", "H4",
            "A5", "B5", "C5", "D5", "E5", "F5", "G5", "H5",
            "A6", "B6", "C6", "D6", "E6", "F6", "G6", "H6",
            "A7", "B7", "C7", "D7", "E7", "F7", "G7", "H7",
            "A8", "B8", "C8", "D8", "E8", "F8", "G8", "H8",

            "1A", "1B", "1C", "1D", "1E", "1F", "1G", "1H",
            "2A", "2B", "2C", "2D", "2E", "2F", "2G", "2H",
            "3A", "3B", "3C", "3D", "3E", "3F", "3G", "3H",
            "4A", "4B", "4C", "4D", "4E", "4F", "4G", "4H",
            "5A", "5B", "5C", "5D", "5E", "5F", "5G", "5H",
            "6A", "6B", "6C", "6D", "6E", "6F", "6G", "6H",
            "7A", "7B", "7C", "7D", "7E", "7F", "7G", "7H",
            "8A", "8B", "8C", "8D", "8E", "8F", "8G", "8H",
    };

    /**
     * Constante contenant des coordonnées invalides
     */
    private static final String[] COORDONNEE_INVALIDES = {
            "01", "11", "21", "31", "41", "51", "86", "71",
            "8z", "mB", "mC", "aH", "aa", "AA"

    };
    /**
     * Tests unitaires de la mÃ©thode lireChaine()
     */
    public static void testSaisieCoordonnees() {
        System.out.println("test de coordonnée valides :" +
            Arrays.toString(COORDONNEE_VALIDES));
        for (String valeur : COORDONNEE_VALIDES) {
            if (saisieCoordonnees(valeur) != null) {
                System.out.println(valeur + " est une coordonée valide.");
            } else {
                System.out.println(valeur + " est une coordonnée invalide.");
            }

        }
        System.out.println("****************************************");
        System.out.println("\tTest de coordonnnée invalides");

        for (String valeur : COORDONNEE_INVALIDES) {
            if (saisieCoordonnees(valeur) == null) {
                System.out.println(valeur + " est une coordonnée invalide.");
            } else {
                System.out.println(valeur + " est une coordonée valide.");

            }

        }
    }

    public static void main(String[] args) {

        testSaisieCoordonnees();
    }

}

