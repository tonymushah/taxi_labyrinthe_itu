package frontend.listners;

import java.awt.event.MouseEvent;

import javax.swing.*;
import javax.swing.event.MouseInputListener;

import structures.Laby_game;

public class NewLevel implements MouseInputListener{

    Laby_game dGame;
    @Override
    public void mouseClicked(MouseEvent arg0) {
        // TODO Auto-generated method stub
        this.dGame.new_level();
    }

    @Override
    public void mouseEntered(MouseEvent arg0) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseExited(MouseEvent arg0) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mousePressed(MouseEvent arg0) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseReleased(MouseEvent arg0) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseDragged(MouseEvent arg0) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseMoved(MouseEvent arg0) {
        // TODO Auto-generated method stub
        
    }
    public void setdGame(Laby_game dGame) {
        this.dGame = dGame;
    }
    public Laby_game getdGame() {
        return dGame;
    }
    public NewLevel(Laby_game dGame){
        this.setdGame(dGame);
    }
}
