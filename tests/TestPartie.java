/*
 * TestPartie.java                                9 mai 2021
 * 2020-2021, pas de copyright pas de droit d'auteur
 */
import othello.Partie;
import othello.Plateau;
import othello.caracteristiques.Couleur;
import othello.joueur.Joueur;
import othello.joueur.Ordinateur;

/**
 * Tests unitaires de la classe Partie
 *
 * @author SIMON LAUNAY
 *         YOHANN MAY
 *         THEO MICHELLON
 *         MAXIME MOSKALYK
 *         DAVID SIMONIN
 * @version 1.0
 */
public class TestPartie {

    /**
     * jeu d'essai avec un plateau initial
     */
    public static Plateau damier = new Plateau();
    static Joueur[] joueurs = {
        new Joueur("Ghalhyus", Couleur.NOIR),
        new Joueur("CRAZYMat", Couleur.BLANC)
    };
    /**
     * test du constructeur Partie Jeu de données pour les prochains test
     */
    public final static Partie VALIDE = new Partie(joueurs[0], joueurs[1],
        damier);

    /**
     * Test du constructeur Partie avec des valeurs invalides
     */
    public static void testPartieJoueursPlateau() {
        final Joueur[][] JOUEURS_INVALIDES = {
            null,
            { new Joueur("Jean", Couleur.NOIR),
                new Joueur("CRAZYMat", Couleur.BLANC) },
            null,
            { new Joueur("Ghalhyus", Couleur.NOIR),
                new Joueur("Mausaille", Couleur.NOIR) },
            { new Joueur("moska", Couleur.BLANC),
                new Joueur("Guepaz", Couleur.BLANC) }
        };

        final Plateau[] DAMIERS_INVALIDES = {
            new Plateau(),
            null,
            null,
            new Plateau(),
            new Plateau()
        };

        final int TAILLE = JOUEURS_INVALIDES.length;

        int test;

        System.out.print("""
            TEST : Partie(Joueur[], Plateau) (test visuel)
            ----------------------------------------------
            """);
        /* test avec des valeurs invalides */
        test = 0;
        for (int jdd = 0; jdd < TAILLE && jdd == test; jdd++) {
            try {
                new Partie(JOUEURS_INVALIDES[jdd][0],
                    JOUEURS_INVALIDES[jdd][1], DAMIERS_INVALIDES[jdd]);
            } catch (Exception echec) {
                test++;
            }

        }

        if (test == TAILLE) {
            System.out.println("Tous les tests du constructeurs Partie "
                + "ont réussi\n");
        } else {
            System.out.println("Erreur, l'un des tests a échoué\n");
        }
    }

    /**
     * Test getJoueur
     */
    public static void testGetJoueurs() {
        /* jeu de donnée */
        final Joueur[] COPIE_JOUEUR = {
            new Joueur("Ghalhyus", Couleur.NOIR),
            new Joueur("CRAZYMat", Couleur.BLANC)
        };

        final int TAILLE = VALIDE.getJoueurs().length;

        int jdd;

        System.out.print("""
            TEST : getJoueurs() (test automatique)
            -------------------------------------
            """);

        for (jdd = 0; jdd < TAILLE && COPIE_JOUEUR[jdd]
            .equals(VALIDE.getJoueurs()[jdd]);
             jdd++)
            ;  // corps vide

        if (jdd == TAILLE) {
            System.out.println("Le test de getJoueurs a réussi\n");
        } else {
            System.out.println("Erreur, le test a échoué\n");
        }

    }

    /**
     * Test getDamier
     */
    public static void testGetDamier() {
        /* jeu de donnée */
        final Plateau COPIE_DAMIER = new Plateau();

        System.out.print("""
            TEST : getDamier() (test automatique)
            -------------------------------------
            """);

        if (COPIE_DAMIER.equals(VALIDE.getDamier())) {
            System.out.println("Le test de getDamier a réussi\n");
        } else {
            System.out.println("Erreur, le test a échoué\n");
        }
    }

    /**
     * Test getTour
     */
    public static void testGetTour() {
        System.out.print("""
            TEST : getTour() (test automatique)
            -----------------------------------
            """);

        if (0 == VALIDE.getTour()) {
            System.out.println("Le test de getTour a réussi\n");
        } else {
            System.out.println("Erreur, le test a échoué\n");
        }
    }

    /**
     * Test prochainTour()
     */
    public static void testProchainTour() {
        System.out.print("""
            TEST : prochainTour() (test automatique)
            ----------------------------------------
            """);

        VALIDE.prochainTour();
        if (VALIDE.getTour() == 1) {
            System.out.println("Le test de prochainTour a réussi\n");
        } else {
            System.out.println("Erreur, le test a échoué\n");
        }
    }

    /**
     * test de la méthode jouer()
     */
    public static void testJouer() {
        System.out.println("""
            TEST : afficherJoueurAJouer() (test visuel)
            --------------------------------------------
            Assurez vous de tester chaque possibilité
            """);
    }

    /**
     * Probabilités d'efficacite des ordinateurs
     */
    public static void probaOrdinateur() {
        lancerPartie(Ordinateur.Difficulte.FACILE,
            Ordinateur.Difficulte.FACILE);


        lancerPartie(Ordinateur.Difficulte.FACILE,
            Ordinateur.Difficulte.NORMALE);


        lancerPartie(Ordinateur.Difficulte.NORMALE,
            Ordinateur.Difficulte.NORMALE);
    }

    /**
     * Lance une partie entre deux ordinateurs
     * @param diffOrdi1 difficulté du premier ordinateur
     * @param diffOrdi2 difficulté du second ordinateur
     */
    public static void lancerPartie(Ordinateur.Difficulte diffOrdi1,
                                    Ordinateur.Difficulte diffOrdi2) {
        int[] stats = {
            0, 0
        };
        double nbPartie = 1000;
        Ordinateur o1 = new Ordinateur(Couleur.random(), diffOrdi1);
        Ordinateur o2 = new Ordinateur(o1.getCouleur().opposee(), diffOrdi2);

        for (int i = 0; i < nbPartie; i++) {
            Plateau damier = new Plateau();
            Partie partie = new Partie(o1, o2, damier);
            do {
                partie.jouer(null, false);
                partie.prochainTour();
            } while (partie.isJouable());
            if (partie.getVainqueur().getCouleur() == Couleur.NOIR) {
                stats[0]++;
            } else {
                stats[1]++;
            }
        }

        System.out.println("\nRésultat au bout de " + (int) nbPartie
            + " parties");
        if (o1.getCouleur() == Couleur.NOIR) {
            System.out.println(o1.getPseudo() + " " + o1.getDifficulte() + " "
                + o1.getCouleur() + " " + stats[0] / nbPartie * 100 + "%");
            System.out.println(o2.getPseudo() + " " + o2.getDifficulte() + " "
                + o2.getCouleur() + " " + stats[1] / nbPartie * 100 + "%");
        } else {
            System.out.println(o2.getPseudo() + " " + o2.getDifficulte() + " "
                + o2.getCouleur() + " " + stats[0] / nbPartie * 100 + "%");
            System.out.println(o1.getPseudo() + " " + o1.getDifficulte() + " "
                + o1.getCouleur() + " " + stats[1] / nbPartie * 100 + "%");
        }
    }

    /**
     * Lance les tests unitaires
     *
     * @param args non utilisé
     */
    public static void main(String[] args) {
        testPartieJoueursPlateau();
        testGetJoueurs();
        testGetDamier();
        testGetTour();
        testProchainTour();
        testJouer();
        probaOrdinateur();
    }
}