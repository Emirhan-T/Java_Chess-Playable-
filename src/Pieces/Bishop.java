package Pieces;
import javax.swing.*;
public class Bishop extends Pieces {
    public Bishop(String color) {
        super(color);
        if(color.equals("White")) {
            this.setIcon(new ImageIcon("res/pieces/w_bishop.png"));
        }
        else {
            this.setIcon(new ImageIcon("res/pieces/b_bishop.png"));
        }
    }

    @Override
    public boolean isCanMove(JPanel[][] board,int oldX, int oldY, int newX, int newY) {
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
