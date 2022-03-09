/*
 * CommandeConsole.java                            19 mai 2021
 * IUT info1 2020-2021, pas de copyright pas de droit d'auteur
 */
package othello.interfacejeu.console;

/**
 * Définition des commandes pouvant être exécutées par l'utilisateur
 *
 * @author SIMON LAUNAY
 *         YOHANN MAY
 *         THEO MICHELLON
 *         MAXIME MOSKALYK
 *         DAVID SIMONIN
 */
public enum CommandeConsole {
    /* partie */
    /** commande de lancer d'une partie */
    LANCER_PARTIE("lancer", "Lancer une partie"),
    /** commande de chargement d'une partie */
    CHARGER_PARTIE("charger", "Charger une partie existante"),
    /** commande de sauvegarde d'une partie */
    SAUVEGARDER_PARTIE("sauver", "Sauvegarder la partie"),
    /** commande pour passer le tour */
    PASSER_TOUR("passer", "Passer son tour"),

    /* modes de jeu pour l'ordinateur */
    /** commande de mode de jeu ordinateur vs joueur */
    MODE_JEU_JOUEUR_ORDI("jo", "Joueur VS Ordinateur"),
    /** commande de mode de jeu joueur vs joueur */
    MODE_JEU_JOUEUR_JOUEUR("jj", "Joueur VS Joueur"),
    /** commande de mode de jeu ordinateur vs ordinateur */
    MODE_JEU_ORDI_ORDI("oo", "Ordinateur VS Ordinateur"),

    /* difficultés ordinateur */
    /** commande pour la difficulté facile de l'ordinateur */
    DIFFICULTE_FACILE("facile", "Niveau facile"),
    /** commande pour la difficulté normale de l'ordinateur */
    DIFFICULTE_NORMALE("normale", "Niveau normal"),

    /** commande de retour au menu principal */
    RETOUR_MENU("menu", "Retourner au menu principal"),
    /** commande de fermeture du jeu */
    QUITTER_JEU("quitter", "Quitter l'application");

    /** nom de la commande */
    private final String cmd;

    /** description de la commande */
    private final String description;

    /**
     * Initialisation du nom de la commande
     *
     * @param cmd  le nom de la commande
     * @param desc la description de la commande
     */
    CommandeConsole(String cmd, String desc) {
        this.cmd = cmd;
        this.description = desc;
    }

    /**
     * @return description de la commande
     */
    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return cmd;
    }
}
