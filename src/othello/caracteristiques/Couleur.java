/*
 * Couleur.java                     9 mai 2021
 * IUT Rodez, pas de droits
 */
package othello.caracteristiques;

import java.io.Serializable;

/**
 * Valeur de la couleur durant une partie
 *
 * @author SIMON LAUNAY
 *         YOHANN MAY
 *         THEO MICHELLON
 *         MAXIME MOSKALYK
 *         DAVID SIMONIN
 */
public enum Couleur implements Serializable {
    /** couleur noire avec l'icône associée */
    NOIR('x'),
    /** couleur blanche avec l'icône associée */
    BLANC('o');

    /** l'icône de la couleur */
    public char icone;

    /**
     * Initialise l'icône des couleurs pour la console
     *
     * @param icone l'icône qui sera associée à la couleur
     */
    Couleur(char icone) {
        this.icone = icone;
    }

    /**
     * @return aléatoirement une couleur
     */
    public static Couleur random() {
        return values()[(int) (Math.random() * values().length)];
    }

    /**
     * @return l'icône de la couleur
     */
    public char getIcone() {
        return icone;
    }

    /**
     * Détermine la couleur opposée
     *
     * @return La couleur opposée
     */
    public Couleur opposee() {
        return this == NOIR ? BLANC : NOIR;
    }
}
