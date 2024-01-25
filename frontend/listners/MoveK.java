package frontend.listners;

import java.awt.event.*;

import structures.*;

public class MoveK implements KeyListener{
    Laby_game dGame;
    @Override
    public void keyPressed(KeyEvent arg0) {
        // TODO Auto-generated method stub
        switch (arg0.getKeyCode()) {
            case KeyEvent.VK_UP:
                this.dGame.taxi_moveUP();
                break;
            case KeyEvent.VK_DOWN:
                this.dGame.taxi_moveDOWN();
                break;
            case KeyEvent.VK_RIGHT:
                this.dGame.taxi_moveRight();
                break;
            case KeyEvent.VK_LEFT:
                this.dGame.taxi_moveLEFT();
                break;
            
            default:
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent arg0) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void keyTyped(KeyEvent arg0) {
        // TODO Auto-generated method stub
        
    }
    public void setdGame(Laby_game dGame) {
        this.dGame = dGame;
    }
    public Laby_game getdGame() {
        return dGame;
    }
    public MoveK(Laby_game dGame){
        this.setdGame(dGame);
    }
}
