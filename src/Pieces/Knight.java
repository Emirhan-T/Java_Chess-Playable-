package Pieces;
import javax.swing.*;
public class Knight extends Pieces {
    public Knight(String color) {
        super(color);
        if(color.equals("White")){
            this.setIcon(new ImageIcon("res/pieces/w_knight.png"));
        }
        else {
            this.setIcon(new ImageIcon("res/pieces/b_knight.png"));
        }
    }

    @Override
    public boolean isCanMove(JPanel[][] board, int oldX, int oldY, int newX, int newY) {
        if(Math.abs(newX-oldX)==2 && Math.abs(newY-oldY)==1) return true;
        else if(Math.abs(newX-oldX)==1 && Math.abs(newY-oldY)==2) return true;
        return false;

    }
}
