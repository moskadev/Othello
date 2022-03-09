/*
 * Plateau.java                     27 Avril 2021
 * IUT Rodez, pas de droits
 */
package othello;

import othello.caracteristiques.Couleur;
import othello.caracteristiques.Position;

import java.io.Serializable;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Définit le plateau de jeu constitué de lignes, de colonnes et de cases qui
 * peuvent être vides ou remplis
 *
 * @author SIMON LAUNAY
 *         YOHANN MAY
 *         THEO MICHELLON
 *         MAXIME MOSKALYK
 *         DAVID SIMONIN
 */
public class Plateau implements Serializable {

    /** Taille max du plateau (lignes et colonnes) */
    public static final int TAILLE_RANGEE = 8;

    /**
     * Le damier composé de case pouvant comporter un pion
     * Damier[colonne][ligne] soit [x][y]
     */
    private final Pion[][] damier = new Pion[TAILLE_RANGEE][TAILLE_RANGEE];

    /**
     * Initialise le plateau avec un damier prédéfini
     *
     * @param aPlacer les pions à placer sur le plateau
     */
    public Plateau(Pion[] aPlacer) {
        for (Pion pion : aPlacer) {
            remplirCase(pion);
        }
    }

    /**
     * Initialise le plateau avec les pions par défaut
     */
    public Plateau() {
        initialiseDefaut();
    }

    /**
     * Vérifie si il y a dépassement sur les abscisses et/ou ordonnées
     *
     * @param position coordonnées en X et Y
     * @return vrai si les positions indiqués sont valides
     */
    public static boolean isValide(Position position) {
        return position != null && position.x() >= 0 && position.y() >= 0 &&
                position.x() < Plateau.TAILLE_RANGEE &&
                position.y() < Plateau.TAILLE_RANGEE;
    }

    /**
     * Ajout des pions par défaut sur le plateau
     */
    public void initialiseDefaut() {
        remplirCase(new Pion(new Position(3, 4), Couleur.NOIR));
        remplirCase(new Pion(new Position(4, 3), Couleur.NOIR));

        remplirCase(new Pion(new Position(3, 3), Couleur.BLANC));
        remplirCase(new Pion(new Position(4, 4), Couleur.BLANC));
    }

    /**
     * Remplie une case par un pion à une position donnée
     *
     * @param pion le pion à poser
     * @throws IndexOutOfBoundsException si il y a un dépassement sur la taille
     *                                   du plateau
     */
    public void remplirCase(Pion pion) {
        if (!isValide(pion.getPosition())) {
            throw new IndexOutOfBoundsException(
                    "Dépassement sur un pion : " + pion.getPosition());
        }
        damier[pion.getPosition().x()][pion.getPosition().y()] = pion;
    }

    /**
     * Retourne un pion à une certaine position donnée
     *
     * @param aAnalyser la position en abscisse et ordonnée
     * @return le pion trouvé dans le damier
     */
    public Pion getPion(Position aAnalyser) {
        return damier[aAnalyser.x()][aAnalyser.y()];
    }

    /**
     * Vérifie si une case est vide à une certaine position donnée
     *
     * @param aAnalyser la position de la case à vérifier
     * @return vrai si la case n'a aucun pion positionné dessus
     */
    public boolean isCaseVide(Position aAnalyser) {
        return getPion(aAnalyser) == null;
    }

    /**
     * Vérifie si le plateau est remplie
     *
     * @return vrai si le plateau ne comporte plus de cases vide
     */
    public boolean isRempli() {
        return getCasesVides().isEmpty();
    }

    /**
     * Retourne tout les pions du plateau de la couleur indiquée
     *
     * @param couleurJoueur couleur des pions souhaités
     * @return tout les pions de la couleur indiquée
     */
    public List<Pion> getPions(Couleur couleurJoueur) {
        List<Pion> pions = new LinkedList<>();
        for (Pion[] pionsDamier : getDamier()) {
            for (Pion pion : pionsDamier) {
                if (pion != null && couleurJoueur == pion.getCouleur()) {
                    pions.add(pion);
                }
            }
        }
        return pions;
    }

    /**
     * Retourne tout les pions visibles sur le plateau
     *
     * @return tout les pions
     */
    public List<Pion> getPions() {
        List<Pion> pions = new LinkedList<>(getPions(Couleur.BLANC));
        pions.addAll(getPions(Couleur.NOIR));

        return pions;
    }

    /**
     * @return la damier contenant toutes les cases
     */
    public Pion[][] getDamier() {
        return damier;
    }

    /**
     * Affiche toutes les cases vides du plateau
     *
     * @return toutes les cases vide
     */
    public List<Position> getCasesVides() {
        List<Position> vides = new LinkedList<>();
        for (int x = 0; x < TAILLE_RANGEE; x++) {
            for (int y = 0; y < TAILLE_RANGEE; y++) {
                Position position = new Position(x, y);
                if (isCaseVide(position)) {
                    vides.add(position);
                }
            }
        }
        return vides;
    }

    /**
     * Permet de déterminer chaque position où l'on peut placer un pion à chaque
     * tour
     *
     * @param couleurJoueur couleur du joueur qui joue son tour
     * @return une liste contenant toutes les positions possibles de jouer
     */
    public List<Position> getCasesJouables(Couleur couleurJoueur) {
        List<Position> positionJouables = new LinkedList<>();

        /* On simule un pion sur chaque case vide du plateau et on
         * analyse si des pions adverses sont encadrables. Si c'est
         * le cas, on enregistre cette case vide comme "jouable"
         */
        getCasesVides().forEach(positionVide -> {
            Pion pionSimuler = new Pion(positionVide, couleurJoueur);

            if (!pionSimuler.chercherAdversairesAutour(this).isEmpty()) {
                positionJouables.add(positionVide);
            }
        });

        return positionJouables;
    }

    /**
     * Analyse toutes les cases jouables du plateau et détermine celles qui
     * encadrent le plus de pions
     *
     * @param couleurJoueur couleur du joueur qui joue son tour
     * @return la case encadrant le plus de pions sur le plateau
     */
    public Position getMeilleureCaseJouable(Couleur couleurJoueur) {
        int taille, tailleMax = 0;
        Position meilleurPos = null;

        for (Position caseJouable : getCasesJouables(couleurJoueur)) {
            Pion pion = new Pion(caseJouable, couleurJoueur);
            taille = pion.chercherAdversairesAutour(this).size();

            if (taille > tailleMax) {
                meilleurPos = caseJouable;
                tailleMax = taille;
            }
        }
        return meilleurPos;
    }

    /**
     * Vérifier si la position de la case sélectionnée par le joueur est
     * possible
     *
     * @param aAnalyser          choix du pion de l'utilisateur
     * @param positionsPossibles différentes positions possibles à jouer
     * @return true si le choix de l'utilisateur = position possible, false
     * sinon
     */
    public boolean isCaseJouable(Position aAnalyser,
                                 List<Position> positionsPossibles) {
        return positionsPossibles.contains(aAnalyser);
    }

    /**
     * Méthode permettant de placer un pion en position X, Y de la couleur du
     * joueur qui joue si la position qu'il renseigne est valide.
     *
     * @param aPlacer le pion à vérifier puis à placer sur le plateau
     * @return true si le pion a bien était placé, false sinon
     */
    public boolean placerPion(Pion aPlacer) {
        if (isCaseJouable(aPlacer.getPosition(),
                getCasesJouables(aPlacer.getCouleur()))) {

            remplirCase(aPlacer);
            aPlacer.chercherAdversairesAutour(this).forEach(ennemi -> {
                remplirCase(
                        new Pion(ennemi.getPosition(), aPlacer.getCouleur()));
            });
            return true;
        }
        return false;
    }

    /* non javadoc
     * @see java.lang.Object#equals(Object o)
     */
    @Override
    public boolean equals(Object o) {
        return o instanceof Plateau &&
                Arrays.deepEquals(damier, ((Plateau) o).getDamier());
    }
}
