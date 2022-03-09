/*
 * Direction.java                     9 mai 2021
 * IUT Rodez, pas de droits
 */
package othello.caracteristiques;

/**
 * Mouvements que le pion peut effectué
 *
 * @author SIMON LAUNAY
 *         YOHANN MAY
 *         THEO MICHELLON
 *         MAXIME MOSKALYK
 *         DAVID SIMONIN
 */
public enum Direction {
    /** Diagonale allant vers le haut à gauche */
    DIAGONALE_HAUT_GAUCHE,
    /** Horizontale allant vers le haut */
    HAUT,
    /** Diagonale allant vers le haut à droite */
    DIAGONALE_HAUT_DROITE,
    /** Verticale allant vers la droite */
    DROITE,
    /** Diagonale allant vers le bas à droite */
    DIAGONALE_BAS_DROITE,
    /** Horizontale allant vers le bas */
    BAS,
    /** Diagonale allant vers le bas à gauche */
    DIAGONALE_BAS_GAUCHE,
    /** Verticale allant vers la gauche */
    GAUCHE;

    /**
     * @return si l'abscisse va vers une valeur négative
     */
    public boolean isXNegatif() {
        return this.toString().contains("GAUCHE");
    }

    /**
     * @return si l'ordonnée va vers une valeur négative
     */
    public boolean isYNegatif() {
        return this.toString().contains("HAUT");
    }

    /**
     * @return si l'abscisse reste constante
     */
    public boolean isXConstant() {
        return this == HAUT || this == BAS;
    }

    /**
     * @return si l'ordonnée reste constante
     */
    public boolean isYConstant() {
        return this == DROITE || this == GAUCHE;
    }
}
