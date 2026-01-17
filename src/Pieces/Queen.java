package Pieces;
import javax.swing.*;
public class Queen extends Pieces {
    public Queen(String color) {
        super(color);
        if(color.equals("White")){
            this.setIcon(new ImageIcon("res/pieces/w_queen.png"));
        }
        else {
            this.setIcon(new ImageIcon("res/pieces/b_queen.png"));
        }
    }

    @Override
    public boolean isCanMove(JPanel[][] board,int oldX, int oldY, int newX, int newY) {
        if (oldY == newY) {
            for (int i = Math.min(oldX, newX) + 1; i < Math.max(oldX, newX); i++) {
                if (board[i][newY].getComponentCount() > 0) return false;
            }
            return true;
        }
        // Yatay Hareket
        if (oldX == newX) {
            for (int i = Math.min(oldY, newY) + 1; i < Math.max(oldY, newY); i++) {
                if (board[newX][i].getComponentCount() > 0) return false;
            }
            return true;
        }
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
