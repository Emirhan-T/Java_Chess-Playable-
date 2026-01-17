package Pieces;
import javax.swing.*;
public class Pawn extends Pieces {
    public Pawn(String color) {
        super(color);
        if (color.equals("White")) {
            this.setIcon(new ImageIcon("res/pieces/w_pawn.png"));
        }
        else if (color.equals("Black")) {
            this.setIcon(new ImageIcon("res/pieces/b_pawn.png"));
        }
    }

    @Override
    public boolean isCanMove(JPanel[][] board,int oldX, int oldY, int newX, int newY) {
        if(color.equals("White")){
            if(oldX-newX==1){
                if(Math.abs(newY-oldY)==1&&board[newX][newY].getComponentCount()>0){
                   return true;
                }
                else if(newY==oldY){
                    if(board[newX][newY].getComponentCount()==0)return true;
                }
                else if(Math.abs(newY-oldY)==1 && board[newX][newY].getComponentCount()==0){
                    if(board[oldX][newY].getComponentCount()>0){
                   Pieces sidePiece = (Pieces) board[oldX][newY].getComponent(0);
                   if(sidePiece == GamePanel.enPassantTarget){
                       return true;
                   }
                }
                }

            }
            else if(oldX == 6 && oldX - newX == 2 && oldY == newY){
                if(board[newX][newY].getComponentCount() == 0 && board[oldX-1][oldY].getComponentCount() == 0)return true;
            }
        }
        if(color.equals("Black")){
            if(newX-oldX==1){
                if(Math.abs(newY-oldY)==1&&board[newX][newY].getComponentCount()>0){
                    return true;
                }
                else if(newY==oldY){
                    if(board[newX][newY].getComponentCount()==0)return true;
                }
                else if(Math.abs(newY-oldY)==1 && board[newX][newY].getComponentCount()==0){
                    if(board[oldX][newY].getComponentCount()>0){
                        Pieces sidePiece = (Pieces) board[oldX][newY].getComponent(0);
                        if(sidePiece == GamePanel.enPassantTarget){
                            return true;
                        }
                    }
                }
            }
            else if(oldX == 1 && newX - oldX == 2 && oldY == newY){
                if(board[newX][newY].getComponentCount() == 0 && board[oldX+1][oldY].getComponentCount() == 0)return true;
            }
        }
        return false;
    }
}
