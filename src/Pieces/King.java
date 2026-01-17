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
    public boolean isCanMove(JPanel[][] board, int oldX, int oldY, int newX, int newY) {
        if(Math.abs(newX-oldX)<=1 && Math.abs(newY-oldY)<=1) return true;
        if(!hasMoved && oldX == newX && Math.abs(newY - oldY) == 2){
            if(newY > oldY){
                if(board[oldX][7].getComponentCount() > 0){
                    Pieces rook = (Pieces) board[oldX][7].getComponent(0);
                    if(rook instanceof Rook && rook.color.equals(this.color) && !rook.hasMoved){
                        if(board[oldX][5].getComponentCount() > 0 || board[oldX][6].getComponentCount() > 0){
                            return false;
                        }
                        return true;
                    }
                }
            }
            else if(newY < oldY){
                if(board[oldX][0].getComponentCount() > 0){
                    Pieces rook = (Pieces) board[oldX][0].getComponent(0);
                    if(rook instanceof Rook && rook.color.equals(this.color) && !rook.hasMoved){
                        if(board[oldX][1].getComponentCount() > 0 ||
                                board[oldX][2].getComponentCount() > 0 ||
                                board[oldX][3].getComponentCount() > 0){
                            return false;
                        }
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
