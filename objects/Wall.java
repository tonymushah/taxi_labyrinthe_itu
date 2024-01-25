package objects;

import javax.swing.ImageIcon;

import structures.Chunk;

public class Wall extends Obsct{

    public Wall(Chunk emplacement) {
        super(emplacement);
        this.setStr_sign("W");
        this.setImage(new ImageIcon("./frontend/images/Wall.png"));
        //TODO Auto-generated constructor stub
    }
    
}
