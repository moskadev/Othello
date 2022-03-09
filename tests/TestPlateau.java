/*
 * TestPlateau.java                     27 Avril 2021
 * IUT Rodez, pas de droits
 */

import junit.framework.TestCase;
import othello.Pion;
import othello.Plateau;
import othello.caracteristiques.Couleur;
import othello.caracteristiques.Position;
import othello.interfacejeu.console.InterfaceConsole;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static othello.Plateau.isValide;

public class TestPlateau extends TestCase {
    /** Plateau valide */
    final static Plateau damier = new Plateau();

    /** Tableau contenant des positions invalides */
    static final Position[] POSITIONS_INVALIDES = {
            new Position(-1, -1),
            new Position(-1, 5),
            new Position(4, -5),
            new Position(9, 9),
            new Position(10, 5),
            new Position(8, 12),
            new Position(74, -87),
            new Position(80, 32) };

    /** Tableau contenant des positions valides */
    static final Position[] POSITIONS_VALIDES = {
            new Position(7, 7),
            new Position(0, 0),
            new Position(3, 4),
            new Position(4, 3),
            new Position(3, 3),
            new Position(4, 4),
            new Position(4, 5),
            new Position(4, 7),
            new Position(2, 1) };

    public void testIsValide() {
        for (Position invalide : POSITIONS_INVALIDES) {
            assertFalse(isValide(invalide));
        }

        for (Position valide : POSITIONS_VALIDES) {
            assertTrue(isValide(valide));
        }
    }

    public void testInitialiseDefaut() {
        Plateau plateau = new Plateau();
        plateau.initialiseDefaut();
        assertEquals(damier, plateau);
    }

    public void testRemplirCase() {
        Plateau p = new Plateau();
        while (p.getPions().size() < 30) {
            p.remplirCase(new Pion(
                    new Position((int) (Math.random() * Plateau.TAILLE_RANGEE),
                            (int) (Math.random() * Plateau.TAILLE_RANGEE)),
                    Couleur.values()[(int) (Math.random() *
                            Couleur.values().length)]));
        }
        InterfaceConsole.afficherPlateau(p, new ArrayList<>());
    }

    public void testGetPion() {
        Plateau p = new Plateau();

        /* Insertion d'un pion en 8H pour réaliser un test ci dessous */
        p.remplirCase(new Pion(new Position(7, 7), Couleur.NOIR));
        /* Test sur le pion ajouté en 8H */
        assertEquals(new Pion(new Position(7, 7), Couleur.NOIR),
                p.getPion(new Position(7, 7)));

        /* Verification que le pion en 4D soit bien de couleur blanche */
        assertEquals(new Pion(new Position(3, 3), Couleur.BLANC),
                p.getPion(new Position(3, 3)));

        /* Verification que le pion en 3D soit bien inexistant (null) */
        assertNull(p.getPion(new Position(3, 2)));
    }

    public void testIsCaseVide() {
        /* Vérification sur des cases vides */
        assertTrue(damier.isCaseVide(new Position(3, 2)));
        assertTrue(damier.isCaseVide(new Position(4, 5)));
        assertTrue(damier.isCaseVide(new Position(0, 7)));
        assertTrue(damier.isCaseVide(new Position(1, 5)));

        /* Vérification sur des cases non vides */
        assertFalse(damier.isCaseVide(new Position(3, 3)));
        assertFalse(damier.isCaseVide(new Position(3, 4)));
        assertFalse(damier.isCaseVide(new Position(4, 3)));
        assertFalse(damier.isCaseVide(new Position(4, 4)));
    }

    public void testIsRempli() {
        Plateau p = new Plateau();
        /* Test sur le plateau de base */
        assertFalse(p.isRempli());

        /* Ajout d'un pion mais damier reste non rempli */
        p.remplirCase(new Pion(new Position(7, 7), Couleur.NOIR));
        assertFalse(p.isRempli());

        /* Test sur un plateau plein */

        /* On rempli en entier le tableau de pions noirs */
        for (Position c : p.getCasesVides()) {
            p.remplirCase(new Pion(c, Couleur.NOIR));

        }
        /* Verification que le tableau est bien plein */
        assertTrue(p.isRempli());
    }

    public void testGetPionsCouleur() {
        Plateau p = new Plateau();
        List<Pion> PIONS_ATTENDU_NOIR = new LinkedList<>();
        List<Pion> PIONS_ATTENDU_BLANC = new LinkedList<>();
        PIONS_ATTENDU_NOIR.add(new Pion(new Position(3, 4), Couleur.NOIR));
        PIONS_ATTENDU_NOIR.add(new Pion(new Position(4, 3), Couleur.NOIR));
        PIONS_ATTENDU_BLANC.add(new Pion(new Position(3, 3), Couleur.BLANC));
        PIONS_ATTENDU_BLANC.add(new Pion(new Position(4, 4), Couleur.BLANC));

        assertEquals(PIONS_ATTENDU_NOIR, p.getPions(Couleur.NOIR));
        assertEquals(PIONS_ATTENDU_BLANC, p.getPions(Couleur.BLANC));

    }

    public void testGetPions() {
        Plateau p = new Plateau();
        List<Pion> PIONS_ATTENDUS = new LinkedList<>();
        PIONS_ATTENDUS.add(new Pion(new Position(3, 3), Couleur.BLANC));
        PIONS_ATTENDUS.add(new Pion(new Position(4, 4), Couleur.BLANC));
        PIONS_ATTENDUS.add(new Pion(new Position(3, 4), Couleur.NOIR));
        PIONS_ATTENDUS.add(new Pion(new Position(4, 3), Couleur.NOIR));

        assertEquals(PIONS_ATTENDUS, p.getPions());

    }

    public void testGetCasesVides() {
        Plateau p = new Plateau();

        /* Test sur un plateau contenant 4 pions donc 60 cases vides */
        assertEquals(60, p.getCasesVides().size());

        /* Test sur un plateau contenant 6 pions donc 58 cases vides */
        p.remplirCase(new Pion(new Position(7, 7), Couleur.NOIR));
        p.remplirCase(new Pion(new Position(1, 1), Couleur.NOIR));
        assertEquals(58, p.getCasesVides().size());

        /* Test sur un plateau contenant 7 pions donc 57 cases vides */
        p.remplirCase(new Pion(new Position(0, 0), Couleur.NOIR));
        assertEquals(57, p.getCasesVides().size());
    }

    public void testGetCasesJouables() {
        Plateau p = new Plateau();
        List<Position> positionJouablesNoir = new LinkedList<>();
        List<Position> positionJouablesBlanc = new LinkedList<>();
        positionJouablesNoir.add(new Position(2, 3));
        positionJouablesNoir.add(new Position(3, 2));
        positionJouablesNoir.add(new Position(4, 5));
        positionJouablesNoir.add(new Position(5, 4));

        positionJouablesBlanc.add(new Position(2, 4));
        positionJouablesBlanc.add(new Position(3, 5));
        positionJouablesBlanc.add(new Position(4, 2));
        positionJouablesBlanc.add(new Position(5, 3));


        assertEquals(4, p.getCasesJouables(Couleur.NOIR).size());
        assertEquals(positionJouablesNoir, p.getCasesJouables(Couleur.NOIR));
        assertEquals(4, p.getCasesJouables(Couleur.BLANC).size());
        assertEquals(positionJouablesBlanc, p.getCasesJouables(Couleur.BLANC));
    }

    public void testGetMeilleureCaseJouable() {
        /* Initialisation d'un plateau normal */
        Plateau p = new Plateau();

        /* Ajout d'un pion blanc en 3D creant une possibilite de retourner 2
        pions en un seul coup pour les noirs */
        p.remplirCase(new Pion(new Position(2, 3), Couleur.BLANC));

        /* Initialisation de la meilleure position jouable par le joueur noir */
        Position meilleurePosition = new Position(1, 3);

        assertEquals(meilleurePosition,
                p.getMeilleureCaseJouable(Couleur.NOIR));
    }

    public void testIsCaseJouable() {
        Plateau p = new Plateau();
        List<Position> positionsPossibles = p.getCasesJouables(Couleur.NOIR);
        assertTrue(p.isCaseJouable(new Position(2, 3), positionsPossibles));
        assertTrue(p.isCaseJouable(new Position(3, 2), positionsPossibles));
        assertTrue(p.isCaseJouable(new Position(4, 5), positionsPossibles));
        assertTrue(p.isCaseJouable(new Position(5, 4), positionsPossibles));
    }

    public void testPlacerPion() {
        Plateau p = new Plateau();
        /* Pions possibles a plcaer : */
        Pion aTester = new Pion(new Position(2, 3), Couleur.NOIR);
        assertTrue(p.placerPion(aTester));
        aTester = new Pion(new Position(2, 4), Couleur.BLANC);
        assertTrue(p.placerPion(aTester));
        aTester = new Pion(new Position(4, 5), Couleur.NOIR);
        assertTrue(p.placerPion(aTester));
        aTester = new Pion(new Position(4, 2), Couleur.BLANC);
        assertTrue(p.placerPion(aTester));

        /* Pions impossibles a placer : */
        aTester = new Pion(new Position(1, 2), Couleur.BLANC);
        assertFalse(p.placerPion(aTester));
        aTester = new Pion(new Position(7, 2), Couleur.BLANC);
        assertFalse(p.placerPion(aTester));
    }

    public void testEquals() {
        Plateau premier = new Plateau();
        Plateau second = new Plateau();
        /* Test sur deux plateau initialisée par défaut : */
        assertTrue(premier.equals(second));
        assertTrue(second.equals(premier));

        /* Test avec deux plateaux différents */
        Pion aPlacer = new Pion(new Position(2, 3), Couleur.NOIR);
        premier.placerPion(aPlacer);
        assertFalse(premier.equals(second));
        assertFalse(second.equals(premier));

        /* Test sur deux plateaux avec un pion supplémentaire chacun : */
        second.placerPion(aPlacer);
        assertTrue(premier.equals(second));
        assertTrue(second.equals(premier));

    }
}