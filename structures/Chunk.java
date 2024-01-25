package structures;

import objects.*;

import javax.swing.*;

public class Chunk extends JLabel{
    protected int cordX;
    protected int cordY;
    private Client client;
    private Obsct obsct;
    private Taxi tax_posit;
    private ImageIcon imagea;
    ImageIcon defaIcon = new ImageIcon("./frontend/images/verge.png");
    Gameboard gameboard;
    public void setGameboard(Gameboard gameboard) {
        this.gameboard = gameboard;
    }
    public Gameboard getGameboard() {
        return gameboard;
    }
    public void setCordX(int cordX) {
        this.cordX = cordX;
    }
    public void setCordY(int cordY) {
        this.cordY = cordY;
    }
    public void setClient(Client client) {
        this.client = client;
    }
    public void setObsct(Obsct obsct) {
        this.obsct = obsct;
    }
    public void setTax_posit(Taxi tax_posit) {
        this.tax_posit = tax_posit;
    }
    public Client getClient() {
        return client;
    }
    public int getCordX() {
        return cordX;
    }
    public int getCordY() {
        return cordY;
    }
    public Obsct getObsct() {
        return obsct;
    }
    public Taxi getTax_posit() {
        return tax_posit;
    }
    public ImageIcon getImage() {
        return imagea;
    }
    public void _updateChunk(){
        if(this.is_this_verge() == true){
            //this.image.removeAll();
            //this.image.setText("");
            
            this.imagea = defaIcon;
            this.setIcon(this.imagea);
        }else{
            if(this.is_this_taxi() == true){
                //this.image.setText(this.tax_posit.getStr_repres());
                this.imagea = this.tax_posit.getImage();
                this.setIcon(this.imagea);
            }if(this.is_this_obsctr() == true){
                //this.image.setText(this.obsct.getStr_sign());
                this.imagea = this.obsct.getImage();
                this.setIcon(this.imagea);
            }if(this.is_this_client() == true){
                //this.image.setText(this.client.getStr_rep());
                this.imagea = this.client.getImage();
                this.setIcon(this.imagea);
            }
        }
        
    }
    public int get_chunk_dist(Chunk client_empl){
        int dist = (int) Math.sqrt((Math.pow((client_empl.getCordX() - this.getCordX()), 2) + Math.pow((this.getCordY() - client_empl.getCordY()), 2)));
        //System.out.println(dist);
        return dist;
    }
    public void _init_graph(){
        this._updateChunk();
        this.setIcon(this.imagea);
    }
    public Chunk(int cordX, int cordY, Gameboard gameboard){
        this.setCordX(cordX);
        this.setCordY(cordY);
        this.setGameboard(gameboard);
        this._init_graph();
    }
    public boolean is_this_client(){
        if(this.client == null){
            return false;
        }else{
            return true;
        }
    }
    public boolean is_this_taxi(){
        if(this.tax_posit == null){
            return false;
        }else{
            return true;
        }
    }
    public boolean is_this_obsctr(){
        if(this.obsct == null){
            return false;
        }else{
            return true;
        }
    }
    public boolean is_this_verge(){
        if(this.is_this_client() == false && this.is_this_obsctr() == false && this.is_this_taxi() == false){
            return true;
        }else{
            return false;
        }
    }
    public void set_verge(){
        this.client = null;
        this.obsct = null;
        this.tax_posit = null;
    }

}
