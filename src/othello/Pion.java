/*
 * Pion.java                     27 Avril 2021
 * IUT Rodez, pas de droits
 */
package othello;

import othello.caracteristiques.Couleur;
import othello.caracteristiques.Direction;
import othello.caracteristiques.Position;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/**
 * Représentation d'un pion défini par une position (X, Y) et une couleur qui
 * est aussi associé avec un joueur
 *
 * @author SIMON LAUNAY
 *         YOHANN MAY
 *         THEO MICHELLON
 *         MAXIME MOSKALYK
 *         DAVID SIMONIN
 */
public class Pion implements Serializable {

    /** La position du pion sur le plateau */
    private final Position position;

    /** La couleur du pion */
    private final Couleur couleur;

    /**
     * Initialise la case du plateau à une certaine position
     *
     * @param position la position du pion
     * @param couleur  la couleur du pion
     */
    public Pion(Position position, Couleur couleur) {
        if (!Plateau.isValide(position)) {
            throw new IndexOutOfBoundsException("La ligne ou la colonne " +
                    "indiquée dépasse la limite");
        }
        this.couleur = couleur;
        this.position = position;
    }

    /**
     * @return position (X, Y) du pion
     */
    public Position getPosition() {
        return position;
    }

    /**
     * @return couleur du pion
     */
    public Couleur getCouleur() {
        return couleur;
    }

    /**
     * Recherche les pions adverses se trouvant à proximité et dont ils peuvent
     * se retrouver encadrés
     *
     * @param direction la direction souhaitée
     * @param plateau   le plateau où la recherche sera effectuée
     * @return les pions trouvées durant la recherche
     */
    public List<Pion> chercherAdversairesAutour(Plateau plateau,
                                                Direction direction) {
        List<Pion> pions = new LinkedList<>();

        int x = position.x();
        int y = position.y();

        for (int i = 0; i <= Plateau.TAILLE_RANGEE; i++) {
            x = direction.isXConstant() ? x :
                (direction.isXNegatif() ? x - 1 : x + 1);
            y = direction.isYConstant() ? y :
                (direction.isYNegatif() ? y - 1 : y + 1);

            /* évite ici tout les dépassements ou les cases sans pion */
            Position positionAnalyse = new Position(x, y);
            if (!Plateau.isValide(positionAnalyse) ||
                    plateau.isCaseVide(positionAnalyse)) {
                pions.clear(); // vidage des pions déjà trouvés
                break;
            }
            Pion pionAnalyse = plateau.getPion(positionAnalyse);
            if (!equals(pionAnalyse)) {
                /* Arrête la boucle lorsqu'un encadrement entre 2 pions
                 * de même couleur a été trouvé ou bien si le pion analysé
                 * est le même que l'avant-dernier pion déjà trouvé
                 */
                if (pionAnalyse.getCouleur() == couleur || (!pions.isEmpty() &&
                        pions.get(pions.size() - 1).equals(pionAnalyse))) {
                    break;
                }
                pions.add(pionAnalyse);
            }
        }
        return pions;
    }

    /**
     * Recherche les pions adverses dans toutes les directions possibles
     *
     * @param plateau le plateau où la recherche sera effectuée
     * @return les pions encadrés par un autre pion de même
     */
    public List<Pion> chercherAdversairesAutour(Plateau plateau) {
        List<Pion> pions = new LinkedList<>();
        for (Direction direction : Direction.values()) {
            pions.addAll(chercherAdversairesAutour(plateau, direction));
        }
        return pions;
    }


    /**
     * @param obj l'objet Pion
     * @return vrai si l'objet analysé et this sont égaux
     */
    @Override
    public boolean equals(Object obj) {
        return obj instanceof Pion &&
                position.equals(((Pion) obj).getPosition()) &&
                couleur == ((Pion) obj).getCouleur();
    }

    /**
     * @return l'icône du pion en jeu dépendant de sa couleur
     */
    @Override
    public String toString() {
        return String.valueOf(couleur.getIcone());
    }
}
