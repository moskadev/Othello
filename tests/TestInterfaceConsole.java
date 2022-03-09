/*
 * TestInterfaceConsole.java                                23 mai 2021
 * IUT info1 2020-2021, pas de copyright pas de droit d'auteur
 */

import othello.interfacejeu.console.CommandeConsole;
import othello.interfacejeu.console.InterfaceConsole;
import othello.Plateau;
import othello.caracteristiques.Couleur;
import othello.caracteristiques.Position;
import othello.joueur.Joueur;
import othello.Partie;
import othello.Pion;

import java.util.Scanner;

/**
 * Tests unitaires de la classe InterfaceConsole
 *
 * @author SIMON LAUNAY
 *         YOHANN MAY
 *         THEO MICHELLON
 *         MAXIME MOSKALYK 
 *         DAVID SIMONIN 
 */
public class TestInterfaceConsole {

    /**
     * Jeu de données contenant tous les pions d'un plateau
     */
    public static final Pion[] PLATEAU_REMPLI = {
            new Pion(new Position(0, 0), Couleur.NOIR),
            new Pion(new Position(0, 1), Couleur.NOIR),
            new Pion(new Position(0, 2), Couleur.NOIR),
            new Pion(new Position(0, 3), Couleur.NOIR),
            new Pion(new Position(0, 4), Couleur.NOIR),
            new Pion(new Position(0, 5), Couleur.NOIR),
            new Pion(new Position(0, 6), Couleur.NOIR),
            new Pion(new Position(0, 7), Couleur.NOIR),
            new Pion(new Position(1, 0), Couleur.NOIR),
            new Pion(new Position(1, 1), Couleur.NOIR),
            new Pion(new Position(1, 2), Couleur.NOIR),
            new Pion(new Position(1, 3), Couleur.NOIR),
            new Pion(new Position(1, 4), Couleur.NOIR),
            new Pion(new Position(1, 5), Couleur.NOIR),
            new Pion(new Position(1, 6), Couleur.NOIR),
            new Pion(new Position(1, 7), Couleur.NOIR),
            new Pion(new Position(2, 0), Couleur.NOIR),
            new Pion(new Position(2, 1), Couleur.NOIR),
            new Pion(new Position(2, 2), Couleur.NOIR),
            new Pion(new Position(2, 3), Couleur.NOIR),
            new Pion(new Position(2, 4), Couleur.NOIR),
            new Pion(new Position(2, 5), Couleur.NOIR),
            new Pion(new Position(2, 6), Couleur.NOIR),
            new Pion(new Position(2, 7), Couleur.NOIR),
            new Pion(new Position(3, 0), Couleur.NOIR),
            new Pion(new Position(3, 1), Couleur.NOIR),
            new Pion(new Position(3, 2), Couleur.NOIR),
            new Pion(new Position(3, 3), Couleur.NOIR),
            new Pion(new Position(3, 4), Couleur.NOIR),
            new Pion(new Position(3, 5), Couleur.NOIR),
            new Pion(new Position(3, 6), Couleur.NOIR),
            new Pion(new Position(3, 7), Couleur.NOIR),
            new Pion(new Position(4, 0), Couleur.NOIR),
            new Pion(new Position(4, 1), Couleur.NOIR),
            new Pion(new Position(4, 2), Couleur.NOIR),
            new Pion(new Position(4, 3), Couleur.NOIR),
            new Pion(new Position(4, 4), Couleur.NOIR),
            new Pion(new Position(4, 5), Couleur.NOIR),
            new Pion(new Position(4, 6), Couleur.NOIR),
            new Pion(new Position(4, 7), Couleur.NOIR),
            new Pion(new Position(5, 0), Couleur.NOIR),
            new Pion(new Position(5, 1), Couleur.NOIR),
            new Pion(new Position(5, 2), Couleur.NOIR),
            new Pion(new Position(5, 3), Couleur.NOIR),
            new Pion(new Position(5, 4), Couleur.NOIR),
            new Pion(new Position(5, 5), Couleur.NOIR),
            new Pion(new Position(5, 6), Couleur.NOIR),
            new Pion(new Position(5, 7), Couleur.NOIR),
            new Pion(new Position(6, 0), Couleur.NOIR),
            new Pion(new Position(6, 1), Couleur.NOIR),
            new Pion(new Position(6, 2), Couleur.NOIR),
            new Pion(new Position(6, 3), Couleur.NOIR),
            new Pion(new Position(6, 4), Couleur.NOIR),
            new Pion(new Position(6, 5), Couleur.NOIR),
            new Pion(new Position(6, 6), Couleur.NOIR),
            new Pion(new Position(6, 7), Couleur.NOIR),
            new Pion(new Position(7, 0), Couleur.NOIR),
            new Pion(new Position(7, 1), Couleur.NOIR),
            new Pion(new Position(7, 2), Couleur.NOIR),
            new Pion(new Position(7, 3), Couleur.NOIR),
            new Pion(new Position(7, 4), Couleur.NOIR),
            new Pion(new Position(7, 5), Couleur.NOIR),
            new Pion(new Position(7, 6), Couleur.NOIR),
            new Pion(new Position(7, 7), Couleur.NOIR)
    };

    /**
     * jeu de données contenant un plateau
     */
    public static Plateau[] DAMIER = {
            new Plateau(),
            new Plateau(PLATEAU_REMPLI)
    };

    /**
     * jeu de données contenant deux joueurs
     */
    public static Joueur[] joueurs = {
            new Joueur("Ghalhyus", Couleur.NOIR),
            new Joueur("Mausaille", Couleur.BLANC)
    };

    /**
     * jeu de données contenant des parties
     */
    public static Partie[] PARTIES = {
            new Partie(joueurs[0], joueurs[1], DAMIER[0]),
            new Partie(joueurs[0], joueurs[1], DAMIER[1])
    };

    /**
     * objet scanner pour la saisie
     */
    public static Scanner clavier = new Scanner(System.in);

    /**
     * Demande à l'utilisateur d'appuyer sur entrer pour continuer les tests
     */
    public static void continuer() {
        System.out.println("""
                        
                Appuyer sur entrée pour continuer les tests.
                """);
        clavier.nextLine();
    }

    /**
     * Test visuel de la méthode afficherCommandesUniverselles
     */
    public static void testAfficherCommandesUniverselles() {
        System.out.println("""
                                       
                TEST : afficherCommmandesUniverselles (test visuel)
                ---------------------------------------------------
                Assurez vous que les commandes universelles s'affichent 
                correctement :
                """);
        InterfaceConsole.afficherCommandesUniverselles();
        continuer();
    }

    /**
     * Test visuel de la méthode sauvegardePartie
     */
    public static void testSauvegardePartie() {
        System.out.println("""
                                       
                TEST : sauvegardePartie (test visuel)
                --------------------------------------
                Assurez vous que la sauvegarde s'effectue correctement :
                """);
        InterfaceConsole.sauvegardePartie(PARTIES[1]);
        continuer();
    }

    /**
     * Test visuel de la méthode chargerSauvegarde
     */
    public static void testChargerSauvegarde() {
        System.out.println("""
                                        
                TEST : chargerSauvegarde (test visuel)
                --------------------------------------
                Assurez vous que la charge s'effectue correctement, vous aurez
                d'abord à tester une sauvegarde invalide, puis une sauvegarde
                valide :
                """);
        InterfaceConsole.chargerSauvegarde();
        InterfaceConsole.chargerSauvegarde();
        continuer();
    }

    /**
     * Test visuel de la méthode choisirModeJeu
     */
    public static void testChoisirModeJeu() {
        final int NB_TEST = 3;

        System.out.println("""
                                        
                TEST : afficherMenuModeJeu (test visuel)
                ----------------------------------------
                Assurez vous que le menu de mode jeu s'affiche
                correctement et que vous puissiez bien lancer les
                3 modes de jeu :
                """);

        for (int test = 0; test < NB_TEST; test++) {
            InterfaceConsole.choisirModeJeu();
        }
        continuer();
    }

    /**
     * Test visuel de la méthode ajouterJoueur
     */
    public static void testAjouterJoueur() {
        final int NB_TEST = 2;

        System.out.println("""
                                        
                TEST : ajouterJoueur (test visuel)
                --------------------------------------
                Assurez vous que l'ajout du joueur s'effectue 
                correctement en entrant un pseudo court puis un trop 
                long pour les deux tests :
                """);

        /* ajoute un joueur puis affiche le joueur créé */
        for (int test = 0; test < NB_TEST; test++) {
            System.out.println("Le pseudo du joueur est : "
                    + InterfaceConsole.ajouterJoueur(Couleur.NOIR));
        }
        continuer();
    }

    /**
     * Test visuel de la méthode ajouterJoueur
     */
    public static void testAjouterOrdinateur() {
        final int NB_TEST = 3;

        System.out.println("""
                                        
                TEST : ajouterOrdinateur (test visuel)
                --------------------------------------
                Assurez vous que l'ajout de l'ordinateur s'effectue
                correctement en entrant une difficulté différente pour
                les trois tests
                N'hésitez pas à faire de erreurs:
                """);

        /* ajoute un joueur puis affiche le joueur créé */
        for (int test = 0; test < NB_TEST; test++) {
            System.out.println("Le niveau de l'ordinateur est : "
                    + InterfaceConsole
                    .ajouterOrdinateur(Couleur.NOIR)
                    .getDifficulte().toString());
        }
        continuer();
    }

    /**
     * Test visuel de la méthode afficherPlateau
     */
    public static void testAfficherPlateau() {
        System.out.println("""
                                        
                TEST : afficherPlateau (test visuel)
                --------------------------------------
                Assurez vous que le plateau s'affiche correctement :
                """);
        InterfaceConsole.afficherPlateau(PARTIES[0].getDamier(),
                PARTIES[0].getDamier()
                        .getCasesJouables(Couleur.NOIR));
        continuer();
    }

    /**
     * Test visuel de la méthode afficherScore
     */
    public static void testAfficherScore() {
        System.out.println("""
                                        
                TEST : afficherScore (test visuel)
                ---------------------------------------------------
                Assurez vous que les scores des deux joueurs s'affichent
                correctement, avec une partie gagnante pour les noirs:
                """);
        InterfaceConsole.afficherScore(PARTIES[1]);
        continuer();
    }

    /**
     * Test visuel de la méthode afficherResultat
     */
    public static void testAfficherResultat() {
        System.out.println("""
                                        
                TEST : afficherResultat (test visuel)
                ---------------------------------------------------
                Assurez vous que le résultat de la partie s'affiche
                correctement avec une partie gagnante pour les noirs :
                """);
        InterfaceConsole.afficherResultat(PARTIES[1]);
        continuer();
    }

    /**
     * Test de la méthode executerTour
     */
    public static void testExecuterTour() {
        /* affichage des commandes en jeu */
        final CommandeConsole[] CMD_EN_JEU = {
                CommandeConsole.PASSER_TOUR,
                CommandeConsole.SAUVEGARDER_PARTIE
        };

        final int NB_TEST = 3;

        System.out.println("""
                                        
                TEST : executerTour (test visuel)
                ----------------------------------
                Vous allez jouer 3 tours afin de vous assurez que les
                tours s'executent correctement :
                """);
        for (int test = 0; test < NB_TEST; test++) {
            /* tour des noirs */
            InterfaceConsole.afficherPlateau(PARTIES[0].getDamier(),
                    PARTIES[0].getDamier()
                            .getCasesJouables(Couleur.NOIR));
            System.out.println("C'est au tour des noirs de jouer :");
            InterfaceConsole.executerTour(PARTIES[0], CMD_EN_JEU);

            /* tours des blancs */
            InterfaceConsole.afficherPlateau(PARTIES[0].getDamier(),
                    PARTIES[0].getDamier()
                            .getCasesJouables(Couleur.BLANC));
            System.out.println("C'est au tour des blancs de jouer :");
            InterfaceConsole.executerTour(PARTIES[0], CMD_EN_JEU);
        }
        continuer();
    }

    /**
     * Test visuel de la méthode afficherPartie() et de recommencerPartie()
     */
    public static void testAfficherPartie() {
        System.out.println("""
                                        
                TEST : afficherPartie (test visuel)
                ----------------------------------
                Assurez vous que la partie avec un plateau rempli
                s'affiche correctement et que vous pouvez recommencer une 
                partie :
                """);
        InterfaceConsole.afficherPartie(PARTIES[1]);
        continuer();
    }

    /**
     * Lance les tests unitaires
     *
     * @param args non utilisé
     */
    public static void main(String[] args) {
        testAfficherCommandesUniverselles();
        testSauvegardePartie();
        testChargerSauvegarde();
        testChoisirModeJeu();
        testAjouterJoueur();
        testAjouterOrdinateur();
        testAfficherPlateau();
        testAfficherScore();
        testAfficherResultat();
        testExecuterTour();
        testAfficherPartie();
    }
}
