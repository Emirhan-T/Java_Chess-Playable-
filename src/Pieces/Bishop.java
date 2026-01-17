package Pieces;

import javax.swing.*;

public class Bishop extends Pieces {
    public Bishop(String color) {
        super(color);
        if(color.equals("White")){
            this.setIcon(new ImageIcon("res/pieces/w_bishop.png"));
        }
        else {
            this.setIcon(new ImageIcon("res/pieces/b_bishop.png"));
        }
    }

    @Override
    public boolean isCanMove(JPanel[][] board, int oldX, int oldY, int newX, int newY) {

        if (Math.abs(oldX - newX) == Math.abs(oldY - newY)) {

            int xDir = (newX - oldX) > 0 ? 1 : -1;
            int yDir = (newY - oldY) > 0 ? 1 : -1;

            int i = oldX + xDir;
            int j = oldY + yDir;

            while (i != newX && j != newY) {
                if(i < 0 || i > 7 || j < 0 || j > 7) return false;

                if (board[i][j].getComponentCount() > 0) {
                    return false;
                }

                i += xDir;
                j += yDir;
            }
            return true;
        }
        return false;
    }
}