import junit.framework.Assert;
import junit.framework.TestCase;
import othello.Pion;
import othello.Plateau;
import othello.caracteristiques.Couleur;
import othello.caracteristiques.Position;

import java.util.LinkedList;
import java.util.List;

public class TestPion extends TestCase {

    public void tearDown() throws Exception {
    }

    /** Pions valides pour realiser des tests */
    static final Pion[] PIONS_VALIDES = {
            new Pion(new Position(7, 7), Couleur.NOIR),
            new Pion(new Position(0, 0),Couleur.BLANC),
            new Pion(new Position(3, 4),Couleur.NOIR),
            new Pion(new Position(4, 3),Couleur.BLANC),
            new Pion(new Position(3, 3),Couleur.NOIR),
            new Pion(new Position(4, 4),Couleur.BLANC),
            new Pion(new Position(4, 5),Couleur.NOIR),
            new Pion(new Position(4, 7),Couleur.BLANC),
            new Pion(new Position(2, 1),Couleur.NOIR)
    };

    /** Tableau contenant des positions invalides */
    static final Position[] POSITIONS_INVALIDES = {
            new Position(-1, -1),
            new Position(-1, 5),
            new Position(4, -5),
            new Position(9, 9),
            new Position(10, 5),
            new Position(8, 12),
            new Position(74, -87),
            new Position(80, 32)
    };

    /**
     * Tests unitaires du constructeur Pion qui prend en paramètre :
     * une position et une couleur
     */
    public void testPionPositionCouleur() {
        new Pion(new Position(7, 7), Couleur.NOIR);
        new Pion(new Position(0, 0), Couleur.BLANC);
        new Pion(new Position(3, 4), Couleur.NOIR);
        new Pion(new Position(4, 3), Couleur.BLANC);
        new Pion(new Position(4, 4), Couleur.NOIR);
        new Pion(new Position(3, 3), Couleur.BLANC);
        new Pion(new Position(4, 5), Couleur.NOIR);
        new Pion(new Position(4, 7), Couleur.BLANC);
        new Pion(new Position(2, 1), Couleur.NOIR);

        /* test avec des dates invalides */
        for (Position positionsInvalide : POSITIONS_INVALIDES) {
            try {
                new Pion(positionsInvalide, Couleur.NOIR);
                fail();
            } catch (IndexOutOfBoundsException ignored) {
            }
        }
    }

    /**
     * Tests unitaires de getPosition()
     */
    public void testGetPosition() {
        final Position[] ATTENDU = {
                new Position(7, 7),
                new Position(0, 0),
                new Position(3, 4),
                new Position(4, 3),
                new Position(3, 3),
                new Position(4, 4),
                new Position(4, 5),
                new Position(4, 7),
                new Position(2, 1)
        };

        for (int i = 0; i < 8; i++) {
            assertEquals(ATTENDU[i], PIONS_VALIDES[i].getPosition());
        }
    }

    /**
     * Tests unitaires de getCouleur()
     */
    public void testGetCouleur() {
        final Couleur[] ATTENDU = {
                Couleur.NOIR, Couleur.BLANC, Couleur.NOIR, Couleur.BLANC,
                Couleur.NOIR, Couleur.BLANC, Couleur.NOIR, Couleur.BLANC,
                Couleur.NOIR
        };

        for (int i = 0; i < 9; i++) {
            assertEquals(ATTENDU[i], PIONS_VALIDES[i].getCouleur());
        }
    }

    /**
     * Tests unitaires de chercherAdversairesAutour()
     */
    public void testChercherAdversairesAutour() {
        List<Pion> listePion;
        Plateau damier = new Plateau();
        int compteur;
        Pion[] adversaireAttendu = {
                // Premiere possibilite
                new Pion(new Position(3, 3), Couleur.BLANC),
                // Deuxieme possibilite
                new Pion(new Position(4, 4), Couleur.BLANC)
        };

        Pion[] pionATester = {
                // Premiere possibilite
                new Pion(new Position(3, 2), Couleur.NOIR),
                new Pion(new Position(2, 3), Couleur.NOIR),
                // Deuxieme possibilite
                new Pion(new Position(5, 4), Couleur.NOIR),
                new Pion(new Position(4, 5), Couleur.NOIR)
        };


        compteur = 0;
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                listePion = pionATester[compteur]
                        .chercherAdversairesAutour(damier);
                compteur++;
                for (Pion aTester : listePion) {
                    assertEquals(adversaireAttendu[i].getPosition(),
                            aTester.getPosition());
                }
            }
        }



    }

    /**
     * Tests unitaires de toString()
     */
    public void testToString() {
        final String[] ATTENDU = {
                "x", "o", "x", "o", "x", "o", "x", "o", "x"
        };

        for (int i = 0; i < 9; i++) {
            assertEquals(ATTENDU[i], PIONS_VALIDES[i].toString());
        }
    }
}