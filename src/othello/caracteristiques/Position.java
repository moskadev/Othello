/*
 * Position.java                     9 mai 2021
 * IUT Rodez, pas de droits
 */
package othello.caracteristiques;

import java.io.Serializable;

/**
 * Définit les coordonnées en 2 dimensions sur les abscisses (x) et ordonnées
 * (y)
 *
 * @param x abscisse de la position
 * @param y ordonnée de la position
 *
 * @author SIMON LAUNAY
 *         YOHANN MAY
 *         THEO MICHELLON
 *         MAXIME MOSKALYK
 *         DAVID SIMONIN
 */
public record Position(int x, int y) implements Serializable {
    // méthodes x() et y() déjà initiées

    /** Les indexes correspondant au numéro aux colonne */
    public static final String COLONNE_INDEXES = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    /**
     * Méthode permettant de convertir la saisie de l'utilisateur en une
     * Position contenant deux indices<br> Exemple :<br> A1 -> Position(1,
     * 0)<br> B5 -> Position(2, 4)
     *
     * @param aConvertir la saisie de l'utilisateur sous la forme A1
     * @return une position en X et Y
     */
    public static Position convertir(String aConvertir) {
        return new Position(
                Integer.parseInt(String.valueOf(aConvertir.charAt(0))) - 1,
                COLONNE_INDEXES.indexOf(aConvertir.charAt(1)));
    }

    /**
     * @return la position au format visuel du plateau
     */
    @Override
    public String toString() {
        return x + 1 + String.valueOf(COLONNE_INDEXES.charAt(y));
    }
}
