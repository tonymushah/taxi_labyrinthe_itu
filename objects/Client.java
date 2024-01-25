package objects;

import structures.Chunk;
import javax.swing.*;

public class Client {
    Chunk emplacement;
    private String str_rep;
    private ImageIcon image = new ImageIcon("./frontend/images/client.png");
    public void setEmplacement(Chunk emplacement) {
        this.emplacement = emplacement;
    }
    public void setImage(ImageIcon image) {
        this.image = image;
    }
    public ImageIcon getImage() {
        return image;
    }
    public Chunk getEmplacement() {
        return emplacement;
    }
    public String getStr_rep() {
        return str_rep;
    }
    public Client(Chunk emplacement){
        this.setEmplacement(emplacement);
        this.str_rep = "C";
    }
}
