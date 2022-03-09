/*
 * Othello.java                       27/4/2021
 * IUT de Rodez pas de droit d'auteur
 */
package othello;

import othello.interfacejeu.console.InterfaceConsole;

/**
 * Classe principale de l'exécution de la partie d'Othello
 *
 * @author SIMON LAUNAY
 *         YOHANN MAY
 *         THEO MICHELLON
 *         MAXIME MOSKALYK
 *         DAVID SIMONIN
 */
public class Othello {

    /**
     * Méthode lançant une partie d'Othello en initialisant les 2 joueurs et le
     * plateau
     *
     * @param args non utilisé
     */
    public static void main(String[] args) {
        InterfaceConsole.lancerOthello();
    }
}
