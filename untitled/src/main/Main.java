// mediul de dezvoltare folosit: IntelliJ IDEA

package main;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Main {

    // metoda de parcurgere folder cu imagini

    public static void readImagesPath(String dir) {
        File folder = new File(dir);
        double counter=0; //counter folosit pentru a numara cate imagini avem in folder
        File[] listOfFiles = folder.listFiles();

        for (File file : listOfFiles) {
            if (file.isFile()) {
                System.out.println(file.getName());
                counter++;
            }
        }
        System.out.println("Numar total de imagini: " + counter);
    }

    public static void main(String[] args) throws IOException{

        //citire o anumita imagine dupa calea data

        int width = 300; //latime
        int height = 225; //lungime
        BufferedImage image = null;
        File InputImage = null;
        File OutputImage = null;

        try {
            InputImage = new File("C:\\Users\\radui\\Desktop\\p2 - grigore\\baza de date animale\\cane\\OIF-e2bexWrojgtQnAPPcUfOWQ.jpeg");
            image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB); //argb inseamna "Alpha, Red, Green, Blue component"
            image = ImageIO.read(InputImage);
            System.out.println("Imaginea a fost citita.");
        }catch(IOException e){
            System.out.println("Eroare: "+e);
        }

        //scriere imagine

        try{
            OutputImage = new File("C:\\Users\\radui\\Desktop\\p2 - grigore\\baza de date animale\\OutputImages\\output.jpeg");
            ImageIO.write(image,"jpeg",OutputImage);
            System.out.println("Imaginea a fost scrisa");
        }catch(IOException e){
            System.out.println("Eroare: " + e);
        }

        //afisare imagine

        JFrame frame = new JFrame();
        ImageIcon icon = new ImageIcon(image);
        JLabel label = new JLabel(icon);
        frame.add(label); //adauga imaginea pe frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //intrerupe procesul odata cu inchiderea frame-ului
        frame.pack(); //dimensioneaza frame-ul in conformitate cu lungimea/latimea respectiva
        frame.setVisible(true);

        //citire si afisare denumirea imaginilor (si numarul acestora din folderul respectiv)

        System.out.println("Denumirile imaaginilor din folderul Cane");
        readImagesPath("C:\\Users\\radui\\Desktop\\p2 - grigore\\baza de date animale\\cane");
        System.out.println();

        System.out.println("Denumirile imaaginilor din folderul Cavallo");
        readImagesPath("C:\\Users\\radui\\Desktop\\p2 - grigore\\baza de date animale\\cavallo");
        System.out.println();

        System.out.println("Denumirile imaaginilor din folderul Elefante");
        readImagesPath("C:\\Users\\radui\\Desktop\\p2 - grigore\\baza de date animale\\elefante");
        System.out.println();

        System.out.println("Denumirile imaaginilor din folderul Farfalla");
        readImagesPath("C:\\Users\\radui\\Desktop\\p2 - grigore\\baza de date animale\\farfalla");
        System.out.println();

        System.out.println("Denumirile imaaginilor din folderul Gallina");
        readImagesPath("C:\\Users\\radui\\Desktop\\p2 - grigore\\baza de date animale\\gallina");
        System.out.println();

        System.out.println("Denumirile imaaginilor din folderul Gatto");
        readImagesPath("C:\\Users\\radui\\Desktop\\p2 - grigore\\baza de date animale\\gatto");
        System.out.println();

        System.out.println("Denumirile imaaginilor din folderul Mucca");
        readImagesPath("C:\\Users\\radui\\Desktop\\p2 - grigore\\baza de date animale\\mucca");
        System.out.println();

        System.out.println("Denumirile imaaginilor din folderul Pecora");
        readImagesPath("C:\\Users\\radui\\Desktop\\p2 - grigore\\baza de date animale\\pecora");
        System.out.println();

        System.out.println("Denumirile imaaginilor din folderul Ragno");
        readImagesPath("C:\\Users\\radui\\Desktop\\p2 - grigore\\baza de date animale\\ragno");
        System.out.println();

        System.out.println("Denumirile imaaginilor din folderul Scoiattolo");
        readImagesPath("C:\\Users\\radui\\Desktop\\p2 - grigore\\baza de date animale\\scoiattolo");
        System.out.println();

    }
}