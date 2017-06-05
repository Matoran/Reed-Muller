import reed_muller.Noise;
import reed_muller.ReedMuller;
import tools.PGM;
import tools.ParserPGM;

import java.io.*;
import java.util.*;
import java.math.*;

public class Main {

    public static void main(String[] args) throws IOException {
        // permet de prendre les entrées pour le menu
        // soit du clavier, d'un fichier ou de la ligne de commande
        Scanner in;
        switch (args.length) {
            case 0:
                in = new Scanner(System.in);
                break;
            case 1:
                in = new Scanner(new File(args[0]));
                break;
            default:
                String source = args[0];
                for (int i = 1; i < args.length; i++) source += " " + args[i];
                in = new Scanner(source);
        }

        // les impressions des menus sont envoyées sur le canal d'erreur
        // pour les différencier des sorties de l'application
        // lesquelles sont envoyées sur la sortie standard

        // choix des paramètres
        System.err.println("Mot en clair de (r+1)-bits à encoder sur (2^r)-bits.");
        System.err.println("Choisir la valeur de r: ");
        int r = in.nextInt();
        ReedMuller reedMuller = new ReedMuller(r);
        System.err.println("\nLe seuil de bruit est la probabilité d'inverser un bit.");
        System.err.println("Choisir un seuil de bruit (nombre entre 0.0 et 1.0): ");
        double seuil = in.nextDouble();

        // traiter un mot ou une image
        System.err.println("\nMenu initial");
        System.err.println("0: Quitter");
        System.err.println("1: Traiter un mot");
        System.err.println("2: Traiter une image");
        int mode = in.nextInt();

        // opération à effectuer sur le mot ou l'image
        String menu = "\nMenu opérations\n"
                + "0: Quitter\n"
                + "1: Encoder\n"
                + "2: Décoder\n"
                + "3: Bruiter\n"
                + "4: Débruiter\n"
                + "5: Réinitialiser\n"
                + "Opération choisie:";
        int choix = 5;
        BigInteger mot = new BigInteger("0");
        PGM pgm = null;
        if (mode == 1) {
            do {
                switch (choix) {
                    case 1:
                        mot = reedMuller.encode(mot);
                        // vos opérations pour l'encodage du mot courant,
                        // ne rien afficher sur la sortie standard
                        break;
                    case 2:
                        mot = reedMuller.decode(mot);
                        // vos opérations pour le décodage du mot courant,
                        // ne rien afficher sur la sortie standard
                        break;
                    case 3:
                        mot = Noise.add(mot, seuil, r + 1);
                        // vos opérations pour le bruitage du mot courant,
                        // ne rien afficher sur la sortie standard
                        break;
                    case 4:
                        //mot = reedMuller.denoise(mot);
                        // vos opérations pour le débruitage du mot courant,
                        // ne rien afficher sur la sortie standard
                        break;
                    case 5:
                        System.err.println("\nEntrer un mot (en décimal)");
                        mot = new BigInteger(in.next());
                        break;
                }
                if (choix != 5) {
                    System.err.println("Valeur du mot courant (en décimal):");
                    // imprimer la valeur du mot courant en décimal
                    // sur la sortie standard
                    System.out.println(mot);
                }
                System.err.println(menu);
                choix = in.nextInt();
            } while (choix != 0);
        } else if (mode == 2) {
            do {
                String fileName;
                switch (choix) {
                    case 1:
                        reedMuller.encode(pgm);
                        // vos opérations pour l'encodage de l'image courante,
                        // ne rien afficher sur la sortie standard
                        break;
                    case 2:
                        reedMuller.decode(pgm);
                        // vos opérations pour le décodage de l'image courante,
                        // ne rien afficher sur la sortie standard
                        break;
                    case 3:
                        Noise.add(pgm, seuil);
                        // vos opérations pour le bruitage de l'image courante,
                        // ne rien afficher sur la sortie standard
                        break;
                    case 4:
                        reedMuller.denoise(pgm);
                        // vos opérations pour le débruitage de l'image courante,
                        // ne rien afficher sur la sortie standard
                        break;
                    case 5:
                        System.err.println("Nom du fichier de l'image à charger (format png):");
                        fileName = in.next();
                        // lire le fichier contenant l'image pgm
                        pgm = ParserPGM.read(fileName);
                        break;
                }
                if (choix != 5) {
                    System.err.println("Nom du fichier où sauver l'image courante (format png):");
                    fileName = in.next();
                    // sauver l'image courante au format pgm
                    ParserPGM.save(fileName, pgm);
                }
                System.err.println(menu);
                choix = in.nextInt();
            } while (choix != 0);
        }
    }
}

//Encoder => Décoder ou Bruiter ou Réinitialiser
//Décoder => Encoder ou Réinitialiser
//Bruiter => Débruiter ou Bruiter ou Réinitialiser
//Débruiter => Bruiter ou Décoder ou Réinitialiser









