package Pieces;

import javax.swing.*;

public class Rook extends Pieces {
    public Rook(String color) {
        super(color);
        if (color.equals("White")) {
            this.setIcon(new ImageIcon("res/pieces/w_rook.png"));

        } else {
            this.setIcon(new ImageIcon("res/pieces/b_rook.png"));
        }
    }

    @Override
    public boolean isCanMove(JPanel[][] board, int oldX, int oldY, int newX, int newY) {
        if (oldY == newY) {
            for (int i = Math.min(oldX, newX) + 1; i < Math.max(oldX, newX); i++) {
                if (board[i][newY].getComponentCount() > 0) return false;
            }
         return true;
        }
        if (oldX == newX) {
            for (int i = Math.min(oldY, newY) + 1; i < Math.max(oldY, newY); i++) {
                if (board[newX][i].getComponentCount() > 0) return false;
            }
         return true;
        }
        return false;
    }
}