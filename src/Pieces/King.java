package Pieces;
import javax.swing.*;
public class King extends Pieces {
    public King(String color) {
        super(color);
        if(color.equals("White")){
            this.setIcon(new ImageIcon("res/pieces/w_king.png"));
        }
        else {
            this.setIcon(new ImageIcon("res/pieces/b_king.png"));
        }
    }

    @Override
    public boolean isCanMove(JPanel[][] board,int oldX, int oldY, int newX, int newY) {
        if(Math.abs(newX-oldX)<=1 && Math.abs(newY-oldY)<=1) return true;
        return false;
    }
}
