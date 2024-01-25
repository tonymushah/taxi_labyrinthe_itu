package objects;

import utilities.*;

import structures.*;
import javax.swing.*;

import frontend.listners.Selec_game;

public class Taxi {
    private Chunk emplacement;
    private String str_repres = "T";
    private ImageIcon image = new ImageIcon("./frontend/images/taxi.png");
    private int pas = 0;
    private JLabel nbpas = new JLabel("" + pas);
    private Chunk last_position;
    public void setEmplacement(Chunk emplacement) {
        this.emplacement = emplacement;
    }   
    public JLabel getNbpas() {
        return nbpas;
    }
    void update_nbPas(){
        this.nbpas.setText("" + pas);
    }
    public int get_client_dist(){
        Chunk client_empl = this.emplacement.getGameboard().getClient().getEmplacement();
        int dist = client_empl.get_chunk_dist(emplacement);
        return dist;
    }
    public int getPas() {
        return pas;
    }
    public Chunk getEmplacement() {
        return emplacement;
    }
    public String getStr_repres() {
        return str_repres;
    }
    public Taxi(Chunk emplacement){
        this.setEmplacement(emplacement);
        this.emplacement.addMouseListener(new Selec_game(this));
    }
    public Taxi(){
    }
    public ImageIcon getImage() {
        return image;
    }
    public void setLast_position(Chunk last_position) {
        this.last_position = last_position;
    }
    public Chunk getLast_position() {
        return last_position;
    }
    public void setImage(ImageIcon image) {
        this.image = image;
    }
    public void change_currentPlayer(){
        this.emplacement.getGameboard().getCurGame().setPlayer(this);
    }
    public void deplacement(int priority){
        priority = Math.abs(priority) % 5;
        Alignmnt alignmnt = new Alignmnt();
        int[] direction;
        switch (priority) {
            case 0:
                direction = alignmnt.get_invAlign(0);
                this.setImage(new ImageIcon("./frontend/images/taxi_UP.png"));
                break;
            case 1:
                direction = alignmnt.getAlign(2);
                this.setImage(new ImageIcon("./frontend/images/taxi_RIGTH.png"));
                break;
            case 2 :
                direction = alignmnt.getAlign(0);
                this.setImage(new ImageIcon("./frontend/images/taxi_DOWN.png"));
                break;
            case 3:
                direction = alignmnt.get_invAlign(2);
                this.setImage(new ImageIcon("./frontend/images/taxi_LEFT.png"));
                break;
            case 4:
                direction = new int[2];
                direction[0] = 1;
                direction[1] = -1;
                this.setImage(new ImageIcon("./frontend/images/taxi_UP_RIGTH.png"));
                break;
            default:
                direction = new int[2];
                break;
        }
        Chunk current_position = this.emplacement;
        Chunk next_position = this.emplacement.getGameboard().find_Chunk(this.emplacement.getCordX() + direction[0], this.emplacement.getCordY() + direction[1]);
        if(!next_position.is_this_obsctr() && next_position != last_position){
            this.last_position = current_position;
            this.setEmplacement(next_position);
            current_position.setTax_posit(null);
            current_position.removeMouseListener(new Selec_game(this));
            next_position.setTax_posit(this);
            next_position.addMouseListener(new Selec_game(this));
            
            this.pas = this.pas + 1;
            this.update_nbPas();
        }
        
        if(next_position.is_this_client()){
            System.out.println("Vous avez gagnez");
            this.emplacement.getGameboard().getCurGame().new_level();
        }
        
    }
    public Chunk get_prio_chunk(int priority){
        priority = Math.abs(priority) % 5;
        Alignmnt alignmnt = new Alignmnt();
        int[] direction;
        switch (priority) {
            case 0:
                direction = alignmnt.get_invAlign(0);
                //this.setImage(new ImageIcon("./frontend/images/taxi_UP.png"));
                break;
            case 1:
                direction = alignmnt.getAlign(2);
                //this.setImage(new ImageIcon("./frontend/images/taxi_RIGTH.png"));
                break;
            case 2 :
                direction = alignmnt.getAlign(0);
                //this.setImage(new ImageIcon("./frontend/images/taxi_DOWN.png"));
                break;
            case 3:
                direction = alignmnt.get_invAlign(2);
                //this.setImage(new ImageIcon("./frontend/images/taxi_LEFT.png"));
                break;
            case 4:
                direction = new int[2];
                direction[0] = 1;
                direction[1] = -1;
                //this.setImage(new ImageIcon("./frontend/images/taxi_UP_RIGTH.png"));
                break;
            default:
                direction = new int[2];
                break;
        }
        
        return this.emplacement.getGameboard().find_Chunk(this.emplacement.getCordX() + direction[0], this.emplacement.getCordY() + direction[1]);
    }
    public Chunk[] near_taxi(){
        /* NOTE this is the order and direction
        [0] => UP
        [1] => RIGHT
        [2] => DOWN
        [3] => LEFT
        */
        Chunk[] near = new Chunk[4];
        for (int i = 0; i < near.length; i++) {
            near[i] = this.get_prio_chunk(i);
        }
        return near;
    }
}
