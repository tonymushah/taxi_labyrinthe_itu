package structures;

import java.util.Random;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;

import objects.Taxi;
import utilities.Alignmnt;
import utilities.Int_tab;
import frontend.listners.*;

public class Laby_game extends JFrame{
    protected int taille_x;
    protected int taille_y;
    Gameboard board;
    Taxi[] players;
    Taxi player;
    JPanel dJPanel = new JPanel();
    void setBoard(Gameboard board) {
        this.board = board;
    }
    public void setPlayer(Taxi player) {
        this.player = player;
    }
    public Taxi getPlayer() {
        return player;
    }
    public Taxi[] getPlayers() {
        return players;
    }
    public Gameboard getBoard() {
        return board;
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
    public void setPlayers(int nbplayers) {
        this.players = new Taxi[Math.abs(nbplayers)];
        for (int i = 0; i < players.length; i++) {
            this.players[i] = new Taxi();
        }
    }
    public Int_tab pl_cli_dist(){
        int[] players_dist = new int[players.length];
        for (int i = 0; i < players_dist.length; i++) {
            players_dist[i] = this.players[i].get_client_dist();
        }
        return new Int_tab(players_dist);
    }
    public int near_taxi(){
        Int_tab tab = this.pl_cli_dist();
        int[][] all_dist = tab.max_min();
        return all_dist[all_dist.length - 1][1];
    }
    public void _launch_graph(){
        this.setTitle("Taxi Labyrinthe");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //this.setLayout(null);
        JPanel top = new JPanel();
        top.setLayout(new BoxLayout(top, BoxLayout.X_AXIS));
            // TODO opt panel
            JPanel optPanel = new JPanel();
                optPanel.setLayout(new BoxLayout(optPanel, BoxLayout.Y_AXIS));
                optPanel.doLayout();

                // TODO Title 
                JPanel title = new JPanel();
                JLabel lTitle = new JLabel("TAXI LABYRINTHE");
                lTitle.setFont(new FontUIResource("Cambria", FontUIResource.BOLD, 25));
                title.add(lTitle);
                // TODO nombre de pas
                JPanel nbPas = new JPanel();
                BoxLayout boxLayoutPas = new BoxLayout(nbPas,BoxLayout.Y_AXIS);
                nbPas.setLayout(boxLayoutPas);    
                    // TODO Taxi N°1
                    JPanel taxi1 = new JPanel();
                    BoxLayout boxLayoutPas1 = new BoxLayout(taxi1,BoxLayout.Y_AXIS);
                    taxi1.setLayout(boxLayoutPas1);
                    taxi1.add(new JLabel("Nombre de pas pour Taxi1"));
                    JLabel nbpTaxi1 = this.players[0].getNbpas();
                    JButton swicth1 = new JButton("Taxi n°1");
                    swicth1.addMouseListener(new Selec_game(players[0]));
                    taxi1.add(nbpTaxi1);
                    taxi1.add(swicth1);
                    swicth1.addKeyListener(new MoveK(this));
                    taxi1.doLayout();
                    nbPas.add(taxi1);
                    // TODO Taxi N°2
                    nbPas.add(new JPanel());
                    JPanel taxi2 = new JPanel();
                    BoxLayout boxLayoutPas2 = new BoxLayout(taxi2, BoxLayout.Y_AXIS);
                    taxi2.setLayout(boxLayoutPas2);
                    taxi2.add(new JLabel("Nombre de pas pour Taxi2"));
                    JLabel nbpTaxi2 = this.players[1].getNbpas();
                    JButton switch2 = new JButton("Taxi n°2");
                    switch2.addMouseListener(new Selec_game(players[1]));
                    switch2.addKeyListener(new MoveK(this));
                    taxi2.add(nbpTaxi2);
                    taxi2.add(switch2);
                    taxi2.doLayout();
                    nbPas.add(taxi2);
                nbPas.doLayout();
                optPanel.add(title);
                optPanel.add(nbPas);
                

                // TODO command panel
                JPanel commPanel = new JPanel();
                commPanel.setLayout(new BoxLayout(commPanel, BoxLayout.Y_AXIS));
                    // TODO up command
                    JPanel commPanel1 = new JPanel();
                    commPanel1.setLayout(new BoxLayout(commPanel1, BoxLayout.X_AXIS));

                    commPanel1.add(new JPanel());
                    commPanel1.add(new JPanel());
                    JButton up = new JButton("UP");
                    up.addMouseListener(new MoveUp(this));
                    commPanel1.add(up);
                    JButton upright = new JButton("UP RIGHT");
                    upright.addMouseListener(new MoveUpRight(this));
                    commPanel1.add(upright);
                    commPanel1.add(new JPanel());
                    commPanel1.add(new JPanel());

                    commPanel1.doLayout();
                    commPanel.add(commPanel1);

                    // TODO rigth and left command
                    JPanel commPanel2 = new JPanel();
                    commPanel2.setLayout(new BoxLayout(commPanel2, BoxLayout.X_AXIS));

                    commPanel2.add(new JPanel());
                    JButton left = new JButton("LEFT");
                    left.addMouseListener(new MoveLeft(this));
                    commPanel2.add(left);
                    commPanel2.add(new JPanel());
                    JButton auto = new JButton("A");
                    auto.addMouseListener(new Automation(this));
                    commPanel2.add(auto);
                    commPanel2.add(new JPanel());
                    JButton right = new JButton("RIGHT");
                    right.addMouseListener(new MoveRight(this));
                    commPanel2.add(right);
                    commPanel2.add(new JPanel());

                    commPanel2.doLayout();
                    commPanel.add(commPanel2);
                    // TODO down command
                    JPanel commPanel3 = new JPanel();
                    commPanel3.setLayout(new BoxLayout(commPanel3, BoxLayout.X_AXIS));
                    
                    commPanel3.add(new JPanel());
                    commPanel3.add(new JPanel());
                    JButton down = new JButton("DOWN");
                    down.addMouseListener(new MoveDown(this));
                    commPanel3.add(down);
                    commPanel3.add(new JPanel());
                    commPanel3.add(new JPanel());   

                    commPanel3.doLayout();
                    commPanel.add(commPanel3);

                    

                up.addKeyListener(new MoveK(this));
                upright.addKeyListener(new MoveK(this));
                right.addKeyListener(new MoveK(this));
                left.addKeyListener(new MoveK(this));
                down.addKeyListener(new MoveK(this));
                auto.addKeyListener(new MoveK(this));

                // TODO new level
                JPanel newlev = new JPanel();
                newlev.setLayout(new BoxLayout(newlev, BoxLayout.X_AXIS));
                
                JButton opd = new JButton("New Level");
                opd.addMouseListener(new NewLevel(this));
                newlev.add(opd);
                newlev.doLayout();
                commPanel.doLayout();
                optPanel.add(commPanel);
                optPanel.add(new JPanel());
                opd.addKeyListener(new MoveK(this));
                optPanel.add(newlev);
            top.add(optPanel);

            // TODO Gameboard input
            
            this.dJPanel.add(board);
            top.add(this.dJPanel);
            
        top.doLayout();
        this.add(top);
        this.pack();
        //this.setSize(300, 300);
        
        this.setVisible(true);
    }

    public void new_level(){
        Random rand = new Random();
        this.dJPanel.removeAll();
        this.setBoard(new Gameboard(Math.abs(rand.nextInt(25)), Math.abs(rand.nextInt(25)), this));
        this.setPlayer(this.players[this.near_taxi()]);
        this.dJPanel.add(this.board);
    }
    public Laby_game(int taille_x, int taille_y, int nbtaxi){
        /*
        FINISHED make an auto_selection depending with the distances of the taxi to the client
        */
        this.setPlayers(nbtaxi);       
        this.setTaille_x(taille_x);
        this.setTaille_y(taille_y);
        this.setBoard(new Gameboard(this.taille_x, this.taille_y, this));
        this.setPlayer(this.players[this.near_taxi()]);
        this._launch_graph();
    }
    public Laby_game(int taille_x, int taille_y){
        this.setPlayer(new Taxi());
        this.setTaille_x(taille_x);
        this.setTaille_y(taille_y);
        this.setBoard(new Gameboard(this.taille_x, this.taille_y, this, 0));
        //this._launch_graph();
    }
    public void println_gme(){
        this.board.show_gBoard();
    }
    public void taxi_moveUP(){
        this.player.deplacement(Alignmnt.getUP());
        this.board._updateChunks();
    }
    public void taxi_moveRight(){
        this.player.deplacement(Alignmnt.getUP_RIGHT());
        this.board._updateChunks();
    }
    public void taxi_moveDOWN(){
        this.player.deplacement(Alignmnt.getRIGHT());
        this.board._updateChunks();
    }
    public void taxi_moveLEFT(){
        this.player.deplacement(Alignmnt.getDOWN_RIGHT());
        this.board._updateChunks();
    }
    public void taxi_moveUP_RIGHT(){
        this.player.deplacement(4);
        this.board._updateChunks();
    }
    public void automation(){
        Int_tab near_taxi_dists = this.board.get_taxi_urdl(this.player);
        int[][] dist_ = near_taxi_dists.max_min();
        for (int i = 0; i < dist_.length; i++) {
            if(player.get_prio_chunk(dist_[(dist_.length - 1) - i][1]).is_this_obsctr() == false){
                player.deplacement(dist_[(dist_.length - 1) - i][1]);
                break;
            }
        }
        this.board._updateChunks();
    }
}
