/*
 * Joueur.java                                28 avr. 2021
 * IUT info1 2020-2021, pas de copyright pas de droit d'auteur
 */
package othello.joueur;

import othello.Plateau;
import othello.caracteristiques.Couleur;

import java.io.Serializable;

/**
 * Représentation d'un joueur possédant un pseudo et une couleur
 *
 * @author SIMON LAUNAY
 *         YOHANN MAY
 *         THEO MICHELLON
 *         MAXIME MOSKALYK
 *         DAVID SIMONIN
 */
public class Joueur implements Serializable {

    /** Taille maximale du pseudo du joueur */
    public static final int PSEUDO_TAILLE_MAX = 25;

    /** Pseudo du joueur */
    private final String pseudo;

    /** Couleur des pions du joueur, 'x' pour noir et 'o' pour blanc */
    private final Couleur couleur;

    /**
     * Initialise le joueur si les données sont correctes
     *
     * @param pseudo  pseudo du joueur
     * @param couleur couleur des pions du joueur
     * @throws IllegalArgumentException Si le pseudo est null ou ne contient que
     *                                  des caractères d'espacement ou si la
     *                                  couleur est null
     */
    public Joueur(String pseudo, Couleur couleur) {
        if (pseudo == null || pseudo.isBlank() || couleur == null) {
            throw new IllegalArgumentException(
                    "Erreur le pseudo et la couleur " +
                            "ne doivent pas être vide");
        }
        if (pseudo.length() > PSEUDO_TAILLE_MAX) { // on coupe le pseudo
            pseudo = pseudo.substring(0, PSEUDO_TAILLE_MAX);
        }
        this.pseudo = pseudo;
        this.couleur = couleur;
    }

    /**
     * @return le pseudo
     */
    public String getPseudo() {
        return pseudo;
    }

    /**
     * @return la couleur
     */
    public Couleur getCouleur() {
        return couleur;
    }

    /**
     * Détermine si le joueur peut jouer sur la plateau en vérifiant si un de
     * ses pions a la possibilité d'effectuer une action
     *
     * @param plateau le damier
     * @return vrai si le joueur peut jouer
     */
    public boolean peutJouer(Plateau plateau) {
        return plateau.getCasesJouables(couleur).size() > 0;
    }

    /**
     * Retourne le score dépendant du nombre de pions de même couleur sur le
     * plateau indiqué en paramètre
     *
     * @param plateau le plateau à analyser les pions
     * @return le score en rapport au nombre de pions sur le plateau
     */
    public int getScore(Plateau plateau) {
        return plateau.getPions(couleur).size();
    }

    /* non javadoc
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return getPseudo();
    }

    /* non javadoc
     * @see java.lang.Object#equals(Object o)
     */
    @Override
    public boolean equals(Object o) {
        return o instanceof Joueur && pseudo.equals(((Joueur) o).getPseudo()) &&
                couleur.equals(((Joueur) o).getCouleur());
    }
}
