/*
 * OutilSauvegarde.java                       27/4/2021
 * IUT de Rodez pas de droit d'auteur
 */
package othello.outils;

import othello.Partie;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;

/**
 * Gère les sauvegarddes du jeu permettant de restaurer une partie en fonction
 * du nom du fichier indiqué par l'utilisateur
 *
 * @author SIMON LAUNAY
 *         YOHANN MAY
 *         THEO MICHELLON
 *         MAXIME MOSKALYK
 *         DAVID SIMONIN
 */
public class OutilSauvegarde {

    /** chemin vers le dossier de sauvegardes */
    public final static String CHEMIN_SAUVEGARDES = "sauvegardes/";

    /**
     * Effectue la sauvegarde d'une partie en cours
     *
     * @param partie     la partie à sauvegarder
     * @param nomFichier le nom que possèdera le fichier
     * @return vrai si la sauvegarde s'est terminée avec succès
     */
    public static boolean sauvegarder(Partie partie, String nomFichier) {
        boolean effectue = true;  // true si la sauvegarde a eu lieu

        try {
            Path path = Paths.get(CHEMIN_SAUVEGARDES);
            if (!Files.exists(path)) {
                Files.createDirectories(path);
            }

            if (nomFichier == null) {
                effectue = false;
            } else {
                nomFichier = CHEMIN_SAUVEGARDES + nomFichier;
                FileOutputStream aSauvegarder =
                        new FileOutputStream(nomFichier);
                ObjectOutputStream sauvegarde =
                        new ObjectOutputStream(aSauvegarder);

                sauvegarde.writeObject(partie);
                sauvegarde.flush();
                sauvegarde.close();
            }
        } catch (IOException e) {
            effectue = false;
        }
        return effectue;
    }

    /**
     * Vérifie si la sauvegarde indiquée en paramètre existe
     *
     * @param nomFichier le nom de la sauvegarde à vérifier
     * @return vrai la sauvegarde existe
     */
    public static boolean sauvegardeExiste(String nomFichier) {
        return getSauvegardes().stream()
                .anyMatch(fichier -> fichier.getName().equals(nomFichier));
    }

    /**
     * Charge une sauvegarde à partir d'un fichier existant
     *
     * @param nomFichier le nom de la sauvegarde à charger
     * @return la sauvegarde représentant une partie à charger
     */
    public static Partie charger(String nomFichier) {
        Partie sauvegarde = null;

        try {
            if (sauvegardeExiste(nomFichier) && nomFichier != null) {
                nomFichier = CHEMIN_SAUVEGARDES + nomFichier;

                FileInputStream aCharger = new FileInputStream(nomFichier);
                sauvegarde =
                        (Partie) new ObjectInputStream(aCharger).readObject();
            }
        } catch (IOException | ClassNotFoundException ignored) {
            // ignoré
        }
        return sauvegarde;
    }

    /**
     * @return une liste de toute les sauvegardes
     */
    public static List<File> getSauvegardes() {
        List<File> nomSauvegardes = new LinkedList<>();
        try {
            Files.walk(Paths.get(CHEMIN_SAUVEGARDES)).forEach(chemin -> {
                File fichier = chemin.toFile();
                if (!fichier.isDirectory()) {
                    nomSauvegardes.add(fichier);
                }
            });
        } catch (IOException ignored) {
            // ignoré
        }
        return nomSauvegardes;
    }
}
