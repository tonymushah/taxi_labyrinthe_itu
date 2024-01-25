package objects;

import structures.*;
import javax.swing.*;

public class Obsct {
    Chunk emplacement;
    private String str_sign;
    private ImageIcon image = new ImageIcon("./frontend/images/obstacles.png");
    public void setImage(ImageIcon image) {
        this.image = image;
    }
    public ImageIcon getImage() {
        return image;
    }
    public Chunk getEmplacement() {
        return emplacement;
    }
    public void setEmplacement(Chunk emplacement) {
        this.emplacement = emplacement;
    }
    public Obsct(Chunk emplacement){
        this.str_sign = "O";
        this.setEmplacement(emplacement);
    }
    public String getStr_sign() {
        return str_sign;
    }
    void setStr_sign(String str_sign) {
        this.str_sign = str_sign;
    }
}
