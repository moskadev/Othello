/*
 * Partie.java                                        8 mai 2021
 * IUT, info1 2020-2021, aucun droit
 */
package othello;

import othello.caracteristiques.Couleur;
import othello.caracteristiques.Position;
import othello.joueur.Joueur;
import othello.joueur.Ordinateur;

import java.io.Serializable;
import java.util.List;

/**
 * Classe principale établissant toutes les caractéristiques d'une partie
 * d'Othello, c'est à dire :
 * <ul>
 *     <li>Les joueurs (associés à son pseudo et sa couleur)</li>
 *     <li>Le plateau</li>
 *     <li>Les pions du plateau (dont leur couleur est associé avec celle
 *     du joueur)</li>
 * </ul>
 * <p>
 * Chaque joueur peut jouer son tour et la partie se termine lorsqu'aucun pion
 * n'a la possibilité de jouer ou que le plateau est rempli
 *
 * @author SIMON LAUNAY
 *         YOHANN MAY
 *         THEO MICHELLON
 *         MAXIME MOSKALYK
 *         DAVID SIMONIN
 */
public class Partie implements Serializable {

    /** Le nombre total de joueurs qu'une partie peut posséder */
    public static final int NB_TOTAL_JOUEURS = 2;

    /** Les joueurs de la partie */
    private final Joueur[] joueurs;

    /** Le plateau de jeu */
    private final Plateau damier;

    /** Le numéro du tour */
    private int tour;

    /** La dernière position jouée */
    private Position dernierePositionJouee;

    /**
     * Initialise une partie en triant les joueurs de sorte que le premier soit
     * de couleur noire
     *
     * @param joueur1 joueur/ordinateur numéro 1 de la partie
     * @param joueur2 joueur/ordinateur numéro 2 de la partie
     * @param damier  Plateau de jeu
     * @throws IllegalArgumentException Si au moins l'une des références est
     *                                  null
     */
    public Partie(Joueur joueur1, Joueur joueur2, Plateau damier) {
        if (!isValide(joueur1, joueur2, damier)) {
            throw new IllegalArgumentException(
                    "Erreur la partie est " + "incorrectement configurée");
        }
        joueurs = new Joueur[NB_TOTAL_JOUEURS];
        joueurs[joueur1.getCouleur() == Couleur.NOIR ?
                0 :
                NB_TOTAL_JOUEURS - 1] = joueur1;
        joueurs[joueur2.getCouleur() == Couleur.NOIR ?
                0 :
                NB_TOTAL_JOUEURS - 1] = joueur2;

        this.damier = damier;
        tour = 0;
    }

    /**
     * Vérifie que les références ne sont pas null
     *
     * @param joueur1 joueur/ordinateur numéro 1 de la partie
     * @param joueur2 joueur/ordinateur numéro 2 de la partie
     * @param damier  Le plateau de jeu
     * @return true si les références sont non null
     */
    public static boolean isValide(Joueur joueur1, Joueur joueur2,
                                   Plateau damier) {
        return damier != null && !joueur1.equals(joueur2);
    }

    /**
     * @return les joueurs en jeu
     */
    public Joueur[] getJoueurs() {
        return joueurs;
    }

    /**
     * @return le joueur jouant la manche actuelle
     */
    public Joueur getTourJoueur() {
        return joueurs[tour % 2];
    }

    /**
     * @return le plateau de jeu
     */
    public Plateau getDamier() {
        return damier;
    }

    /**
     * @return le numéro du tour
     */
    public int getTour() {
        return tour;
    }

    /**
     * Incrémente d'un tour
     */
    public void prochainTour() {
        tour++;
    }

    /**
     * Détermine si la partie est jouable en vérifiant si le plateau est complet
     * ou si les 2 joueurs n'ont plus de mouvement possible
     *
     * @return vrai si la partie est encore jouable
     */
    public boolean isJouable() {
        boolean jouable = false;
        for (Joueur j : joueurs) {
            if (jouable = j.peutJouer(damier)) {
                break;
            }
        }
        return !damier.isRempli() && jouable;
    }

    /**
     * @return vrai s'il y a égalité entre les 2 joueurs
     */
    public boolean isEgalite() {
        return joueurs[0].getScore(damier) == joueurs[1].getScore(damier);
    }

    /**
     * @return le joueur ayant le plus de points
     */
    public Joueur getVainqueur() {
        Joueur vainqueur = joueurs[0];
        if (vainqueur.getScore(damier) < joueurs[1].getScore(damier)) {
            vainqueur = joueurs[1];
        }
        return vainqueur;
    }

    /**
     * Lors de son tour, le joueur joue à une certaine position donnée en
     * argument ou alors, pour l'ordinateur, sa position dépend de sa
     * difficulté
     *
     * @param position position à laquelle le pion sera posé, sa valeur peut
     *                 être null. Dans le cas de l'ordinateur, celle-ci sera
     *                 automatiquement redéfinie dépendant de sa difficulté
     * @param sleep    si sur vrai, alors le programme se mettra en "pause"
     *                 pendant quelques millisecondes
     * @return vrai si le joueur/ordinateur a pu jouer à cette position
     */
    public boolean jouer(Position position, boolean sleep) {
        if (!getTourJoueur().peutJouer(damier)) {
            return false;
        }
        Couleur c = getTourJoueur().getCouleur();
        if (getTourJoueur() instanceof Ordinateur ordi) {
            if (sleep) {
                try { // on fait genre de rendre l'ordi patient
                    Thread.sleep(600);
                } catch (InterruptedException e) {
                    //juste ignorer dans ce cas
                }
            }
            List<Position> positions = damier.getCasesJouables(c);
            if (ordi.getDifficulte() == Ordinateur.Difficulte.NORMALE) {
                position = damier.getMeilleureCaseJouable(c);
            } else { // facile et si non trouvé, le reste
                position = positions.get((int) (Math.random() *
                        positions.size())); //position choisie au hasard
            }
        }
        dernierePositionJouee = position;

        if (!Plateau.isValide(position)) {
            return false;
        }
        return damier.placerPion(new Pion(position, c));
    }

    /**
     * Lors de son tour, le joueur joue à une certaine position donnée en
     * argument ou alors, pour l'ordinateur, sa position dépend de sa
     * difficulté
     *
     * @param position position à laquelle le pion sera posé, sa valeur peut
     *                 être null. Dans le cas de l'ordinateur, celle-ci sera
     *                 automatiquement redéfinie dépendant de sa difficulté
     * @return vrai si le joueur/ordinateur a pu jouer à cette position
     */
    public boolean jouer(Position position) {
        return jouer(position, true);
    }

    /**
     * @return la dernière position jouée durant la partie
     */
    public Position getDernierePositionJouee() {
        return dernierePositionJouee;
    }

    /* non javadoc
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        // TODO Auto-generated method stub
        return obj instanceof Partie &&
                getJoueurs()[0].equals(((Partie) (obj)).getJoueurs()[0]) &&
                getJoueurs()[1].equals(((Partie) (obj)).getJoueurs()[1]) &&
                getDamier().equals(((Partie) (obj)).getDamier());
    }
}
