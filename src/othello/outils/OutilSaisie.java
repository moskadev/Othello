/*
 * OutilSaisie.java              7 mai 2021
 * IUT Rodez, pas de droits
 */
package othello.outils;

import othello.caracteristiques.Position;
import othello.interfacejeu.console.CommandeConsole;
import othello.interfacejeu.console.InterfaceConsole;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Contient les outils de saisies principaux pour des saisies simples, de
 * commandes et de coordonées
 *
 * @author SIMON LAUNAY
 *         YOHANN MAY
 *         THEO MICHELLON
 *         MAXIME MOSKALYK
 *         DAVID SIMONIN
 */
public class OutilSaisie {

    /** Objet Scanner pour effectuer des saisies */
    private static final Scanner clavier = new Scanner(System.in);

    /**
     * Saisie d'une chaîne lambda jusqu'à ce que la saisie ne soit pas nulle ou
     * vide. Lecture de 2 chaînes spéciales :
     * <ul>
     *     <li>'menu' permettant l'affichage du menu principal</li>
     *     <li>'quitter' quittant l'application</li>
     * </ul>
     * Ces commandes doivent être accessible partout durant les saisies même en
     * dehors même de la méthode de saisie de commandes basiques
     *
     * @param msg Message qui sera affiché lors de la saisie
     * @return la chaîne de caractères saisie par l'utilisateur
     */
    public static String lireChaine(String msg) {
        String chaineLue, lowerChaine;
        do {
            System.out.print(msg);
            chaineLue = clavier.nextLine();
            System.out.println();

            lowerChaine = chaineLue.toLowerCase();

            if (lowerChaine.equals(CommandeConsole.QUITTER_JEU.toString())) {
                System.out.println("Fermeture de l'application...");
                System.exit(0);

            } else if (lowerChaine
                    .equals(CommandeConsole.RETOUR_MENU.toString())) {
                InterfaceConsole.lancerOthello();
            }
            if (chaineLue.isEmpty()) {
                System.out.println("La saisie ne peut pas être nulle ou vide");
            }
        } while (chaineLue.isEmpty());

        return chaineLue;
    }

    /**
     * Saisie de coordonnées pour le placement de pion sur un plateau
     *
     * @return les coordonnées converties en Position
     */
    public static Position saisieCoordonnees() {
        Position coordonnees;
        do {
            coordonnees = saisieCoordonnees(lireChaine(""));
            if (coordonnees == null) {
                System.out.println(
                        "Les coordonnées saisies ne sont pas " + "correctes");
            }
        } while (coordonnees == null);

        return coordonnees;
    }

    /**
     * Lecture de coordonnées pour le placement de pion sur un plateau
     *
     * @param chaine les coordonées souhaités
     * @return les coordonnées converties en Position
     */
    public static Position saisieCoordonnees(String chaine) {
        char[] chars;
        // en ordre alphabétique (chiffres -> lettres)
        chars = (chaine.length() > 1 ? chaine.substring(0, 2) : chaine)
                .toUpperCase().toCharArray();
        Arrays.sort(chars);

        if (chars.length < 2 || !Character.isDigit(chars[0]) ||
                !Character.isLetter(chars[1])) {
            return null;
        }
        return Position.convertir(new String(chars));
    }

    /**
     * Saisie d'une commande à exécuter. Seules celles indiquées en paramètres
     * sont exécutables
     *
     * @param commandesDispo les commandes exécutables
     * @return la commande exécutée
     */
    public static CommandeConsole saisieCommande(
            CommandeConsole[] commandesDispo) {
        CommandeConsole cmd;
        do {
            cmd = saisieCommande(lireChaine("Entrez une commande : "),
                    commandesDispo);
            if (cmd == null) {
                System.out.println("La commande saisie n'existe pas");
            }
        } while (cmd == null);

        return cmd;
    }

    /**
     * Lecture d'une commande à exécuter. Seules celles indiquées en paramètres
     * sont exécutables
     *
     * @param chaine         la commande saisie
     * @param commandesDispo les commandes exécutables
     * @return la commande exécutée
     */
    public static CommandeConsole saisieCommande(String chaine,
                                                 CommandeConsole[] commandesDispo) {
        CommandeConsole cmdTrouvee = null;

        chaine = chaine.toLowerCase();
        for (CommandeConsole cmd : commandesDispo) {
            if (cmd.toString().equals(chaine)) {
                cmdTrouvee = cmd;
            }
        }

        return cmdTrouvee;
    }
}
