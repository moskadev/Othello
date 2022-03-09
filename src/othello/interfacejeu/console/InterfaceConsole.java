/*
 * InterfaceConsole.java                            7 mai 2021
 * IUT info1 2020-2021, pas de copyright pas de droit d'auteur
 */
package othello.interfacejeu.console;

import othello.Partie;
import othello.Plateau;
import othello.caracteristiques.Couleur;
import othello.caracteristiques.Position;
import othello.joueur.Joueur;
import othello.joueur.Ordinateur;
import othello.outils.OutilSaisie;
import othello.outils.OutilSauvegarde;

import java.io.File;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Gère l'interface console de l'application Othello
 *
 * @author SIMON LAUNAY
 *         YOHANN MAY
 *         THEO MICHELLON
 *         MAXIME MOSKALYK
 *         DAVID SIMONIN
 */
public class InterfaceConsole {

    /**
     * Affichage du menu principale. L'utilisateur peut utiliser 2 commandes :
     * <ul>
     * <li>le lancer d'une partie</li>
     * <li>le chargement d'une sauvegarde enregistrée précédemment</li>
     * </ul>
     */
    public static void lancerOthello() {
        final CommandeConsole[] CMD_MENU_PRINCIPAL = new CommandeConsole[]{
                CommandeConsole.LANCER_PARTIE,
                CommandeConsole.CHARGER_PARTIE };

        System.out.print("""
                -----------------------------------------------------
                |                     Othello                       |
                -----------------------------------------------------
                """);
        afficherListeCommandes(CMD_MENU_PRINCIPAL);
        afficherCommandesUniverselles();

        switch (OutilSaisie.saisieCommande(CMD_MENU_PRINCIPAL)) {
            case LANCER_PARTIE -> afficherPartie(choisirModeJeu());
            case CHARGER_PARTIE -> {
                Partie sauvergarde = chargerSauvegarde();
                if (sauvergarde != null) {
                    afficherPartie(sauvergarde);
                } else {
                    lancerOthello();
                }
            }
        }
    }

    /**
     * Menu de recharges de sauvegardes. Si au moins une sauvegarde existe, une
     * liste de noms est affichée et l'utilisateur peut saisir l'un de ces nom
     * pour rependre une partie mise en pause.
     *
     * @return La partie chargée
     */
    public static Partie chargerSauvegarde() {
        String nomFichier;
        Partie p;
        List<File> sauvegardes = OutilSauvegarde.getSauvegardes();

        if (sauvegardes.size() > 0) {
            System.out.println("Liste des sauvegardes chargeables : ");
            sauvegardes.forEach(
                    fichier -> System.out.println("- " + fichier.getName()));

            nomFichier =
                    OutilSaisie.lireChaine("Entrez le nom de la sauvegarde : ");
            p = OutilSauvegarde.charger(nomFichier);

            if (p != null) {
                System.out.println("Chargement de la sauvegarde");
                return p;
            }
            System.out.println("Un problème est survenu. " +
                    "Chargement de la sauvegarde annulée");
        } else {
            System.out.println("Aucune sauvegarde n'existe");
        }
        return null;
    }

    /**
     * Affichage des commandes pouvant être utilisées n'importe où et n'importe
     * quand :
     * <ul>
     * <li>'menu' permet de retourner au menu principal</li>
     * <li>'quitter' permet de fermer l'application</li>
     * </ul>
     */
    public static void afficherCommandesUniverselles() {
        final CommandeConsole[] CMD_UNIVERSELLES = new CommandeConsole[]{
                CommandeConsole.RETOUR_MENU,
                CommandeConsole.QUITTER_JEU };

        System.out.println(
                "Vous pourrez utiliser à tout moment ces " + "commandes-ci :");
        afficherListeCommandes(CMD_UNIVERSELLES);
    }

    /**
     * Affichage du menu de sélection du mode de jeu souhaité.
     * <ul>
     * <li>Un mode de jeu joueur contre joueur</li>
     * <li>Un mode de jeu joueur contre ordinateur</li>
     * <li>Un mode de jeu ordinateur contre ordinateur</li>
     * </ul>
     *
     * @return La partie créée
     */
    public static Partie choisirModeJeu() {
        final CommandeConsole[] CMD_MODES_JEU = new CommandeConsole[]{
                CommandeConsole.MODE_JEU_JOUEUR_JOUEUR,
                CommandeConsole.MODE_JEU_JOUEUR_ORDI,
                CommandeConsole.MODE_JEU_ORDI_ORDI, };

        Joueur j1, j2;
        System.out.println("Veuillez sélectionner un mode de jeu");

        afficherListeCommandes(CMD_MODES_JEU);
        switch (OutilSaisie.saisieCommande(CMD_MODES_JEU)) {
            case MODE_JEU_JOUEUR_JOUEUR -> {
                j1 = ajouterJoueur(Couleur.random());
                j2 = ajouterJoueur(j1.getCouleur().opposee());
            }
            case MODE_JEU_JOUEUR_ORDI -> {
                j1 = ajouterJoueur(Couleur.random());
                j2 = ajouterOrdinateur(j1.getCouleur().opposee());
            }
            //default fait office de ordi VS ordi pour éviter tout problème
            default -> {
                j1 = ajouterOrdinateur(Couleur.random());
                j2 = ajouterOrdinateur(j1.getCouleur().opposee());
            }
        }
        System.out.println("Lancement de la partie...");

        return new Partie(j1, j2, new Plateau());
    }

    /**
     * Créer un joueur en initialisant son pseudo et sa couleur en jeu
     *
     * @param couleur la couleur attribuée au joueur
     * @return le joueur initialisé
     */
    public static Joueur ajouterJoueur(Couleur couleur) {
        System.out.println("Le pseudo sera coupé à partir de la limite de " +
                Joueur.PSEUDO_TAILLE_MAX + " caractères");

        return new Joueur(OutilSaisie.lireChaine(
                "Veuillez choisir un " + "pseudo pour le joueur (" + couleur +
                        ") : "), couleur);
    }

    /**
     * Créer un ordinateur en initialisant son numéro, sa couleur en jeu et son
     * niveau de difficulté (au choix entre facile et normale)
     *
     * @param couleur la couleur attribuée au joueur
     * @return l'ordinateur initialisé
     */
    public static Ordinateur ajouterOrdinateur(Couleur couleur) {
        final CommandeConsole[] CMD_DIFFICULTE = new CommandeConsole[]{
                CommandeConsole.DIFFICULTE_FACILE,
                CommandeConsole.DIFFICULTE_NORMALE };

        System.out.println(
                "Veuillez sélectionner le niveau de " + "l'ordinateur (" +
                        couleur + ")");
        afficherListeCommandes(CMD_DIFFICULTE);

        String niveau = OutilSaisie.saisieCommande(CMD_DIFFICULTE).toString()
                .toUpperCase();
        return new Ordinateur(couleur, Ordinateur.Difficulte.valueOf(niveau));
    }

    /**
     * Affiche le plateau de jeu et les pions s'y trouvant dessus
     *
     * @param plateau       Plateau à afficher
     * @param caseAModifier les cases où une icône différente sera utilisée
     */
    public static void afficherPlateau(Plateau plateau,
                                       List<Position> caseAModifier) {
        Position position;
        char icone;
        StringBuilder damier = new StringBuilder("\n  ");

        // numéro des colonnes
        for (int i = 1; i <= Plateau.TAILLE_RANGEE; i++) {
            damier.append(' ').append(i).append(' ');
        }
        damier.append('\n');

        //pour chaque ligne
        for (int x = 0; x < Plateau.TAILLE_RANGEE; ++x) {
            // lettre des lignes
            damier.append(Position.COLONNE_INDEXES.charAt(x)).append(' ');
            // pour chaque colonne
            for (int y = 0; y < Plateau.TAILLE_RANGEE; ++y) {
                // on inverse le x et le y pour corriger la visualisation
                // de l'ordre du plateau sur la console
                position = new Position(y, x);
                icone = caseAModifier.contains(position) ?
                        '-' :
                        plateau.isCaseVide(position) ?
                        ' ' :
                        plateau.getPion(position).toString().charAt(0);
                // l'icône de la case
                damier.append(' ').append(icone).append(' ');

            }
            damier.append('\n');
        }

        System.out.println(damier);
    }

    /**
     * Affiche le score de chaque joueur
     *
     * @param aAfficher la partie à renseigner
     */
    public static void afficherScore(Partie aAfficher) {
        for (Joueur j : aAfficher.getJoueurs()) {
            System.out.printf("%s (%s) : %d points    ", j.getPseudo(),
                    j.getCouleur(), j.getScore(aAfficher.getDamier()));
        }
        System.out.println();
    }

    /**
     * Affiche le vainqueur lors de la fin de la partie et son score
     *
     * @param aAfficher la partie à analyser
     */
    public static void afficherResultat(Partie aAfficher) {
        Joueur vainqueur = aAfficher.getVainqueur();
        int score = vainqueur.getScore(aAfficher.getDamier());

        System.out.println("\n" + " ".repeat(15) + "FIN DE LA PARTIE\n" +
                (aAfficher.isEgalite() ?
                 "Égalité pour les 2 joueurs avec " + score + " points" :
                 "Le gagnant est " + vainqueur + " ("
                     + vainqueur.getCouleur() + ") avec un score de " + score));
    }

    /**
     * Exécute le tour d'un joueur lors d'une partie. Le joueur a 3 solutions :
     * <ul>
     * <li>Jouer la manche en entrant le coordonnées d'une case</li>
     * <li>Passer son tour</li>
     * <li>Sauvegarder la partie en cours</li>
     * </ul>
     * Si le joueur/ordinateur n'a pas la possibilité de jouer, il passe
     * automatiquement son tour. Si une coordonnée saisie n'existe pas ou est
     * injouable, le joueur recommence la saisie.
     *
     * @param partie la partie à jouer
     * @param cmds   les commandes utilisables pendant une partie
     */
    public static void executerTour(Partie partie, CommandeConsole[] cmds) {
        final String SAISIE_MSG =
                "Saisissez des coordonnées ou une " + "commande : ";

        boolean aJouer;
        String chaine;

        String msg = "";
        CommandeConsole cmd = null;
        Position position = null;

        Joueur joueurActuel = partie.getTourJoueur();
        Plateau damier = partie.getDamier();
        // exécute la manche d'un joueur/ordinateur à tour de rôle
        do {
            aJouer = false;
            if (!joueurActuel.peutJouer(damier)) { // si le joueur peut jouer
                System.out.println("Manche injouable");
            } else {
                if (!(joueurActuel instanceof Ordinateur)) {
                    // si ce n'est pas une ordinateur, alors il pourra saisir
                    // une commande ou des coordonées
                    chaine = OutilSaisie.lireChaine(msg);

                    cmd = OutilSaisie.saisieCommande(chaine, cmds);
                    position = OutilSaisie.saisieCoordonnees(chaine);
                }
                // exécute la commande si elle a été saisie
                if (cmd == CommandeConsole.PASSER_TOUR) {
                    System.out.println("Vous avez passer votre tour");
                    break;
                } else if (cmd == CommandeConsole.SAUVEGARDER_PARTIE) {
                    sauvegardePartie(partie);
                    msg = SAISIE_MSG;
                } else { // si aucune commande n'a été saisie
                    aJouer = partie.jouer(position); // joue sur le plateau
                    if (joueurActuel instanceof Ordinateur) {
                        System.out.println(partie.getDernierePositionJouee());
                    }
                    if (!aJouer) {
                        System.out.println("La position de la case saisie " +
                                "n'est pas jouable");
                        msg = SAISIE_MSG;
                    }
                }
            }
        } while (joueurActuel.peutJouer(damier) && !aJouer);

        partie.prochainTour(); // passe au prochain tour
    }

    /**
     * Sauvegarde entièrement une partie. L'utilisateur saisie le nom de la
     * sauvegarde.
     *
     * @param aSauvegarder la partie à sauvegarder
     */
    public static void sauvegardePartie(Partie aSauvegarder) {
        String nomFichier = OutilSaisie
                .lireChaine("Entrez le nom " + "de la sauvegarde : ");
        if (OutilSauvegarde.sauvegardeExiste(nomFichier)) {
            System.out.println("Une sauvegarde possède déjà ce nom. " +
                    "Elle sera écrasée");
        }
        if (OutilSauvegarde.sauvegarder(aSauvegarder, nomFichier)) {
            System.out.println("Partie sauvegardée !");
        } else {
            System.out.println("Un problème est survenue.");
        }
    }

    /**
     * Affiche le déroulé d'une partie
     *
     * @param aAfficher la partie à afficher
     */
    public static void afficherPartie(Partie aAfficher) {
        final CommandeConsole[] CMD_EN_JEU = new CommandeConsole[]{
                CommandeConsole.PASSER_TOUR,
                CommandeConsole.SAUVEGARDER_PARTIE };
        Joueur joueurActuel;

        Plateau damier = aAfficher.getDamier();
        do { // exécute la partie tant que celle-ci est jouable
            joueurActuel = aAfficher.getTourJoueur();

            System.out.println("\n" + "=".repeat(60) + "\n");

            // si ce n'est pas une ordinateur, le joueur doit saisir une
            // coordonnée ou une commmande
            if (!(joueurActuel instanceof Ordinateur)) {
                System.out.println("Vous pouvez effectuer ces commandes-ci : ");
                afficherListeCommandes(CMD_EN_JEU);
                System.out.println("Ou saisir les coordonnés où vous " +
                        "souhaitez placer votre pion");
            }
            afficherPlateau(damier,
                    damier.getCasesJouables(joueurActuel.getCouleur()));
            afficherScore(aAfficher);

            System.out.printf("C'est au tour de %s (%s) de jouer : ",
                    joueurActuel.getPseudo(), joueurActuel.getCouleur());
            executerTour(aAfficher, CMD_EN_JEU);
        } while (aAfficher.isJouable());

        System.out.println("\nPlateau final :");
        afficherPlateau(damier, new LinkedList<>());
        afficherScore(aAfficher);

        afficherResultat(aAfficher);
        recommencerPartie();
    }

    /**
     * Affiche un menu indiquant à l'utilisateur s'il souhaite recommencer
     * directement une partie ou sinon, il est renvoyé au menu principal
     */
    public static void recommencerPartie() {
        final List<String> REPONSES_DISPO =
                Arrays.asList("oui", "o", "non", "n");
        String choix;
        do {
            choix = OutilSaisie.lireChaine("\nVoulez-vous recommencer " +
                    "une partie ? [oui/o, non/n] ");
            if (REPONSES_DISPO.contains(choix)) {
                if (choix.equals("oui") || choix.equals("o")) {
                    System.out.println("Début d'une nouvelle partie...");
                    afficherPartie(choisirModeJeu());
                } else {
                    System.out.println("Retour au menu principal...");
                    lancerOthello();
                }
            } else {
                System.out.println("Réponse invalide. Recommencez");
            }
        } while (!REPONSES_DISPO.contains(choix));
    }

    /**
     * Affiche une liste de commandes (avec leur description respective)
     * exécutables par l'utilisateur
     *
     * @param commandesDispo les commandes que l'utilisateur peut utiliser
     */
    private static void afficherListeCommandes(
            CommandeConsole[] commandesDispo) {
        for (CommandeConsole cmd : commandesDispo) {
            System.out.printf(" ".repeat(9) + "%s - %s\n", cmd,
                    cmd.getDescription());
        }
    }
}
