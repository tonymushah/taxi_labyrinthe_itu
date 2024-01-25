package objects;

import structures.Chunk;
import javax.swing.*;

public class Cone extends Obsct{

    public Cone(Chunk emplacement) {
        super(emplacement);
        this.setStr_sign("P");
        this.setImage(new ImageIcon("./frontend/images/Cone.png"));
        //TODO Auto-generated constructor stub
    }
    
}
