/*
 * Ordinateur.java                                     19 mai 2021
 * IUT, info1 2020-2021, aucun droit
 */
package othello.joueur;

import othello.caracteristiques.Couleur;

/**
 * Représentation d'un ordinateur par sa couleur et sa difficulté en jeu
 *
 * @author SIMON LAUNAY
 *         YOHANN MAY
 *         THEO MICHELLON
 *         MAXIME MOSKALYK
 *         DAVID SIMONIN
 */
public class Ordinateur extends Joueur {

    /** Le niveau de difficulté de l'ordinateur */
    private final Difficulte difficulte;

    /**
     * Initialise l'ordinateur
     *
     * @param couleur    couleur des pions de l'ordinateur
     * @param difficulte niveau de difficulté de l'ordinateur
     */
    public Ordinateur(Couleur couleur, Difficulte difficulte) {
        super("Ordinateur", couleur);
        this.difficulte = difficulte;
    }

    /**
     * @return le niveau de difficulté
     */
    public Difficulte getDifficulte() {
        return difficulte;
    }

    /** Les différents niveau de difficulté */
    public enum Difficulte {
        /** le niveau de difficulté facile */
        FACILE,
        /** le niveau de difficulté normale */
        NORMALE,
    }
}
