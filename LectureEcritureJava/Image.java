import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

public class Image {
    private int width;
    private int height;
    // pixels[y][x][0=R,1=G,2=B]
    private int[][][] pixels; // pixels[y][x][0=R,1=G,2=B]

    public int getWidth() { return width; }
    public int getHeight() { return height; }

    /**
     * Constructeur : initialise une image vide.
     */
    public Image(int width, int height) {
        this.width = width;
        this.height = height;
        pixels = new int[height][width][3];
    }

    /**
     * Définit la couleur d'un pixel à la position (x, y)
     */
    public void setPixel(int x, int y, int r, int g, int b) {
        if (x >= 0 && x < width && y >= 0 && y < height) {
            pixels[y][x][0] = r;
            pixels[y][x][1] = g;
            pixels[y][x][2] = b;
        }
    }

    /**
     * Sauvegarde l'image au format texte PPM (P3)
     */
    public void save_txt(String filename) throws IOException {
        FileWriter writer = new FileWriter(filename);
        
        writer.write("P3\n");
        writer.write(width + " " + height +"\n");
        writer.write("255\n");

        int r,g,b;
        
        for (int y = 0; y < height; y++) { 
            for (int x = 0; x < width; x++) { 
                r = pixels[y][x][0];
                g = pixels[y][x][1];
                b = pixels[y][x][2];

                // Ecrit les couleurs sous forme texte
                writer.write(r + " " + g + " " + b + " ");
            }
            writer.write("\n");
        }
        writer.close();
    }

    /**
     * Sauvegarde l'image au binaire PPM (P6)
     */
    public void write_bin(String filename) throws IOException {
        FileOutputStream fileStream = new FileOutputStream(filename);
        
        byte[] couleur = new byte[3];

        fileStream.write(("P6\n").getBytes());
        fileStream.write((width + " " + height +"\n").getBytes());
        fileStream.write(("255\n").getBytes());
      
        for (int y = 0; y < this.height; y++) {
            for (int x = 0; x < this.width; x++) {      
                // Convertit les couleurs en octets 
                couleur[0] = (byte) pixels[y][x][0];
                couleur[1] = (byte) pixels[y][x][1];
                couleur[2] = (byte) pixels[y][x][2];

                // Ecrit les 3 octets du pixel
                fileStream.write(couleur);
            }
        }
        fileStream.close();
    }
    
}