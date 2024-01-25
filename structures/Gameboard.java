package structures;

import java.util.*;

import javax.swing.*;
import java.awt.*;
import utilities.*;
import objects.*;

public class Gameboard extends JPanel{
    protected int taille_x;
    protected int taille_y;
    protected Chunk[][] chunks;
    protected int crossend; // for line generation and all
    protected Laby_game curGame;
    protected Client client;
    public void setCrossend(int crossend) {
        this.crossend = crossend;
    }
    public int getCrossend() {
        return crossend;
    }
    public void setTaille_x(int taille_x) {
        this.taille_x = taille_x;
    }
    public void setTaille_y(int taille_y) {
        this.taille_y = taille_y;
    }
    public int getTaille_x() {
        return taille_x;
    }
    public int getTaille_y() {
        return taille_y;
    }
    public void setClient(Client client) {
        this.client = client;
    }
    public Client getClient() {
        return client;
    }
    public void setTaille(int taille_x, int taille_y){
        this.setTaille_x(taille_x);
        this.setTaille_y(taille_y);
    }
    public Chunk find_Chunk(int cordX, int cordY){
        if(cordX < 0){
            cordX = taille_x + cordX;
        }
        if(cordY < 0){
            cordY = taille_y + cordY;
        }
        return this.chunks[Math.abs(cordY % taille_y)][Math.abs(cordX % taille_x)];
    } 
    void _init_chunKs(){
        this.chunks = new Chunk[taille_y][taille_x];
        for(int cY = 0; cY < this.taille_y; cY++){
            for(int cX = 0; cX < this.taille_x; cX++){
                this.chunks[cY][cX] = new Chunk(cX, cY, this);
            }
        }
    }
    public void setCurGame(Laby_game curGame) {
        this.curGame = curGame;
    }
    public Laby_game getCurGame() {
        return curGame;
    }

    public Gameboard(int taille_x, int taille_y){
        this.crossend = 5;
        this.setTaille(taille_x, taille_y);
        this._init_chunKs();
    }
    String specific_y_str(int e_y){
        e_y = Math.abs(e_y);
        String returns = "";
        for(int ix = 0; ix < this.taille_x; ix++){
            String toadd = "";
            if(this.chunks[e_y % taille_y][ix].is_this_verge() == true){
                toadd = " ";
            }else{
                if(this.chunks[e_y % taille_y][ix].is_this_taxi() == true){
                    toadd = this.chunks[e_y % taille_y][ix].getTax_posit().getStr_repres();
                }if(this.chunks[e_y % taille_y][ix].is_this_obsctr() == true){
                    toadd = this.chunks[e_y % taille_y][ix].getObsct().getStr_sign();
                }if(this.chunks[e_y % taille_y][ix].is_this_client() == true){
                    toadd = this.chunks[e_y % taille_y][ix].getClient().getStr_rep();
                }
            }
            returns = returns + "[" + toadd + "]";
        }
        return returns;
    }
    
    public void show_gBoard(){
        for(int iy = 0; iy < this.taille_y; iy++){
            System.out.println(this.specific_y_str(iy));
        }
    }
    Obsct alea_obsct(Chunk emplacement){
        Random rand = new Random();
        int choosed = rand.nextInt(5);
        if((choosed % 2) == 0){
            return new Wall(emplacement);
        }else{
            if(choosed == 1){
                return new Cone(emplacement);
            }else{
                return new Obsct(emplacement);
            }
        }
    }
    public void generate_line(Obsct to_use, int priority, int nb_obs){
        Alignmnt alignmnt = new Alignmnt();
        int[] direction = alignmnt.getAlign((priority % 2) * 2);
        Chunk next_Chunk = this.find_Chunk(to_use.getEmplacement().getCordX() + direction[0], to_use.getEmplacement().getCordY() + direction[1]);
        int limits = nb_obs;
        int itera = 0;
        while(next_Chunk.is_this_verge()){
            next_Chunk.setObsct(alea_obsct(next_Chunk));
            itera = itera + 1;
            next_Chunk = this.find_Chunk(next_Chunk.getCordX() + direction[0], next_Chunk.getCordY() + direction[1]);
            if(itera >= limits){
                return;
            }
        }
        /*direction = alignmnt.get_invAlign((priority % 2) * 2);
        next_Chunk = this.find_Chunk(to_use.getEmplacement().getCordX() + direction[0], to_use.getEmplacement().getCordY() + direction[1]);
        while(next_Chunk.is_this_verge()){
            next_Chunk.setObsct(new Obsct(next_Chunk));
            itera = itera + 1;
            next_Chunk = this.find_Chunk(next_Chunk.getCordX() + direction[0], next_Chunk.getCordY() + direction[1]);
            if(itera >= limits){
                return;
            }
        }*/
    }
    public void generate_line_obs(Obsct to_use){
        Random rand = new Random();
        this.generate_line(to_use, Math.abs(rand.nextInt(1)), rand.nextInt(4));
    }
    public void just_gener(int amont){
        Random rand = new Random();
        for(int mani = 0; mani < ((this.taille_x * this.taille_y) / amont); mani++){
            Chunk choosed = this.find_Chunk(rand.nextInt(this.taille_x), rand.nextInt(this.taille_y));
            if(choosed.is_this_verge()){
                choosed.setObsct(alea_obsct(choosed));
            }
        }
    }    
    public void generate_obstc(){
        Random rand = new Random();
        for(int mani = 0; mani < ((this.taille_x * this.taille_y) / 6); mani++){
            Chunk choosed = this.find_Chunk(rand.nextInt(this.taille_x), rand.nextInt(this.taille_y));
            choosed.setObsct(alea_obsct(choosed));
            int pouf = rand.nextInt(5);
            if(pouf % 2 == 0){
                this.generate_line_obs(choosed.getObsct());
            }else if(pouf == 5 || pouf == 1){
                choosed.getObsct().setEmplacement(null);
                choosed.setObsct(null);
            }
        }
        this.just_gener(7);
    }
    public void place_client(){
        Random rand = new Random();
        Chunk choosed = this.find_Chunk(rand.nextInt(this.taille_x), rand.nextInt(this.taille_y));
        while(choosed.is_this_verge() == false){
            choosed = this.find_Chunk(rand.nextInt(this.taille_x), rand.nextInt(this.taille_y));
        }
        this.client = new Client(choosed);
        choosed.setClient(this.client);
        Alignmnt alignmnt = new Alignmnt();
        int[] direction = alignmnt.getAlign((rand.nextInt(1) % 2) * 2);
        Chunk next_Chunk = this.find_Chunk(choosed.getCordX() + direction[0], choosed.getCordY() + direction[1]);
        next_Chunk.set_verge();

        direction = alignmnt.get_invAlign((rand.nextInt(1) % 2) * 2);
        next_Chunk = this.find_Chunk(choosed.getCordX() + direction[0], choosed.getCordY() + direction[1]);
        next_Chunk.set_verge();
    }
    public void place_taxi(Taxi l){
        Random rand = new Random();
        Chunk choosed = this.find_Chunk(rand.nextInt(this.taille_x), rand.nextInt(this.taille_y));
        while(choosed.is_this_verge() == false){
            choosed = this.find_Chunk(rand.nextInt(this.taille_x), rand.nextInt(this.taille_y));
        }
        choosed.setTax_posit(l);
        l.setEmplacement(choosed);
        Alignmnt alignmnt = new Alignmnt();
        int[] direction = alignmnt.getAlign((rand.nextInt(1) % 2) * 2);
        Chunk next_Chunk = this.find_Chunk(choosed.getCordX() + direction[0], choosed.getCordY() + direction[1]);
        next_Chunk.set_verge();

        direction = alignmnt.get_invAlign((rand.nextInt(1) % 2) * 2);
        next_Chunk = this.find_Chunk(choosed.getCordX() + direction[0], choosed.getCordY() + direction[1]);
        next_Chunk.set_verge();
    }
    public void place_taxis(){
        for(int i = 0; i < this.curGame.getPlayers().length; i++){
            this.place_taxi(this.curGame.getPlayers()[i]);
        }
    }
    public void generate_env(){
        this.generate_obstc();
        this.place_client();
        this.place_taxis();
    }
    JPanel y_linePanel(int e_y){
        e_y = Math.abs(e_y);
        JPanel returns = new JPanel();
        returns.setLayout(new BoxLayout(returns, BoxLayout.X_AXIS));
        for(int ix = 0; ix < this.taille_x; ix++){
            returns.add(chunks[e_y][ix]);
        }
        returns.doLayout();
        return returns;
    }
    public void _updateChunks(){
        for (Chunk[] chunks2 : chunks) {
            for (Chunk chunk : chunks2) {
                chunk._updateChunk();  
            }
        }
    }
    void _init(){
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        for(int iY = 0; iY < this.taille_y; iY++){
            this.add(this.y_linePanel(iY));
        }
        this.doLayout();
    }
    void _init_grid(){
        this.setLayout(new GridLayout(taille_x, taille_y));
        for (Chunk[] chunks2 : chunks) {
            for (Chunk chunk : chunks2) {
                this.add(chunk);
            }
        }
        this.doLayout();
    }
    public Gameboard(int taille_x, int taille_y, Laby_game gLgame){
        this.crossend = 5;
        this.setTaille(taille_x, taille_y);
        this.setCurGame(gLgame);
        this._init_chunKs();
        this.generate_env();
        this._init();
        this._updateChunks();
    }
    public Gameboard(int taille_x, int taille_y, Laby_game gLgame, int plk){
        this.crossend = 5;
        this.setTaille(taille_x, taille_y);
        this.setCurGame(gLgame);
        this._init_chunKs();
        this._init();   
        this._updateChunks();
    }
    public void place_client(int cX, int cY){
        Chunk to_change = this.find_Chunk(cX, cY);
        to_change.setClient(new Client(to_change));
    }
    public void place_obscatcle(int cX, int cY){
        Chunk to_change = this.find_Chunk(cX, cY);
        to_change.setObsct(this.alea_obsct(to_change));
    }
    public void place_taxi(int cX, int cY){
        Chunk to_change = this.find_Chunk(cX, cY);
        to_change.setTax_posit(this.curGame.getPlayer());
        this.curGame.getPlayer().setEmplacement(to_change);
    }
    public Int_tab get_taxi_urdl(Taxi to_presume){
        Chunk[] near_taxi = to_presume.near_taxi();
        int[] dists_NearTaxi = new int[near_taxi.length];
        for (int i = 0; i < dists_NearTaxi.length; i++) {
            dists_NearTaxi[i] = near_taxi[1].get_chunk_dist(this.client.getEmplacement());
        }
        return new Int_tab(dists_NearTaxi);
    }
}
