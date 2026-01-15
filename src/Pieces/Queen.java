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
        if (oldX == newX) {
            for (int i = Math.min(oldY, newY) + 1; i < Math.max(oldY, newY); i++) {
                if (board[newX][i].getComponentCount() > 0) return false;
            }
            return true;
        }
        if(Math.abs(newX-oldX)!=Math.abs(newY-oldY))return false;
        int dirX=(oldX < newX)? 1:-1;
        int dirY=(oldY < newY)? 1:-1;

        int currentX= oldX+dirX;
        int currentY= oldY+dirY;
        while (currentX!=newX && currentY!=newY){
            if(board[currentX][currentY].getComponentCount()>0){
                return false;
            }
            currentX += dirX;
            currentY += dirY;
        }
        return true;
    }
}
