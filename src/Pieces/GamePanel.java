package Pieces;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GamePanel extends JPanel {

    int boxSize = 50;
    JPanel [][]loc = new JPanel [8][8];
    boolean turnWhite = true;
    boolean Select=false;
    public static Pieces enPassantTarget = null;
    JPanel NextSelectedSquare= null;
    JPanel OldSelectedSquare= null;
    Color oldColor=null;
    int oldX,oldY;
    public GamePanel() {
        Table();
        Pieces();


    }
    void Pieces(){
        for (int i = 0; i < 8; i++) {
            loc[6][i].add(new Pawn("White"));
            loc[1][i].add(new Pawn("Black"));
        }
        for (int i = 0; i < 8; i=i+7) {
            for (int j = 0; j < 8; j=j+7) {
                if (j%2==0) {
                    loc[j][i].add(new Rook("Black"));
                }
                else {
                    loc[j][i].add(new Rook("White"));
                }
            }
        }
        for (int i = 1; i < 8; i=i+5) {
            for (int j = 0; j < 8; j=j+7) {
                if (j%2==0) {
                    loc[j][i].add(new Knight("Black"));
                }
                else {
                    loc[j][i].add(new Knight("White"));
                }
            }
        }
        for (int i = 2; i < 8; i=i+3) {
            for (int j = 0; j < 8; j=j+7) {
                if (j%2==0) {
                    loc[j][i].add(new Bishop("Black"));
                }
                else {
                    loc[j][i].add(new Bishop("White"));
                }
            }
        }
            loc[0][3].add(new Queen("Black"));
            loc[7][3].add(new Queen("White"));
            loc[0][4].add(new King("Black"));
            loc[7][4].add(new King("White"));
    }

    void Table(){
        int y_axis=50;
        this.setLayout(null);
        for(int i = 0; i<8;i++){

            int x_axis = 50;
            for(int j = 0; j<8;j++){
                JPanel panel = new JPanel();
                if((i+j) % 2 == 0){
                    Color light = new Color(248, 227, 176);
                    panel.setBackground(light);
                    panel.setBounds(x_axis, y_axis, boxSize, boxSize);
                }
                else {
                    Color dark = new Color(1, 204, 197);
                    panel.setBackground(dark);
                    panel.setBounds(x_axis, y_axis, boxSize, boxSize);
                }
                mouseMovment(panel, i , j);
                loc[i][j] = panel;
                x_axis = x_axis + boxSize;
                this.add(panel);
            }
            y_axis = y_axis + boxSize;
        }
    }
    void mouseMovment(JPanel p, int i, int j) {
        p.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e) {

                if(!Select){
                    if(p.getComponentCount() >0){
                        Pieces piece= (Pieces) p.getComponent(0);
                        if(turnWhite && piece.color.equals("Black")) return;
                        if(!turnWhite && piece.color.equals("White")) return;
                        oldColor = p.getBackground();
                        p.setBackground(new Color(255, 144, 73));
                        Select=true;
                        OldSelectedSquare = p;
                        oldX=i;
                        oldY=j;
                    }
                }
                else if(Select){
                    NextSelectedSquare = p;
                    if(OldSelectedSquare == NextSelectedSquare){
                        Select=false;
                        p.setBackground(oldColor);
                    }
                    else{
                        Pieces piece= (Pieces) OldSelectedSquare.getComponent(0);
                        boolean canEat=false;
                        if(p.getComponentCount() >0){
                            Pieces targetPiece = (Pieces) p.getComponent(0);
                            if(targetPiece.color.equals(piece.color)){
                                canEat = true;
                            }
                        }
                        if(piece.isCanMove(loc, oldX,oldY,i,j)==true && canEat==false ){

                            Pieces deadpiece=null;
                            if(p.getComponentCount() >0){
                                deadpiece = (Pieces) p.getComponent(0);
                            }

                            p.removeAll();
                            p.add(piece);

                            if(isKingUnderAttack(piece.color)){
                                OldSelectedSquare.add(piece);
                                if(deadpiece!=null){
                                    p.add(deadpiece);
                                }
                                OldSelectedSquare.setBackground(oldColor);
                            }
                            else {

                                if(piece instanceof Pawn && Math.abs(oldY-j)==1 && p.getComponentCount() == 0){
                                    loc[oldX][j].removeAll();
                                    loc[oldX][j].repaint();
                                    loc[oldX][j].revalidate();
                                }

                                OldSelectedSquare.setBackground(oldColor);
                                OldSelectedSquare.repaint();
                                NextSelectedSquare.repaint();
                                OldSelectedSquare.revalidate();
                                NextSelectedSquare.revalidate();
                                p.repaint();
                                p.revalidate();

                                enPassantTarget = null;

                                if (piece instanceof Pawn && Math.abs(oldX-i)==2){
                                    enPassantTarget = piece;
                                }

                                piece.hasMoved=true;
                                turnWhite = !turnWhite;

                                if(piece instanceof King && Math.abs(oldY - j) == 2){
                                    if(j > oldY){
                                        Pieces rook = (Pieces) loc[oldX][7].getComponent(0);
                                        loc[oldX][5].add(rook);
                                        loc[oldX][7].repaint(); loc[oldX][7].revalidate();
                                        loc[oldX][5].repaint(); loc[oldX][5].revalidate();
                                    }
                                    else{
                                        Pieces rook = (Pieces) loc[oldX][0].getComponent(0);
                                        loc[oldX][3].add(rook);
                                        loc[oldX][0].repaint(); loc[oldX][0].revalidate();
                                        loc[oldX][3].repaint(); loc[oldX][3].revalidate();
                                    }
                                }


                                if(piece instanceof Pawn){
                                    if((piece.color.equals("White") && i == 0) || (piece.color.equals("Black") && i == 7)){
                                        p.remove(piece);
                                        Object[] options = {"Queen", "Rook", "Bishop", "Knight"};
                                        int n = JOptionPane.showOptionDialog(null,
                                                "Change Your Pawn",
                                                "Promotion",
                                                JOptionPane.YES_NO_CANCEL_OPTION,
                                                JOptionPane.QUESTION_MESSAGE,
                                                null,
                                                options,
                                                options[0]);
                                        Pieces newPiece = null;
                                        if(n == 0 || n == -1) newPiece = new Queen(piece.color);
                                        else if(n == 1) newPiece = new Rook(piece.color);
                                        else if(n == 2) newPiece = new Bishop(piece.color);
                                        else if(n == 3) newPiece = new Knight(piece.color);
                                        newPiece.hasMoved = true;
                                        p.add(newPiece);
                                        p.repaint();
                                        p.revalidate();
                                    }
                                }
                                String nextColor = turnWhite ? "White" : "Black";

                                // Eğer sıradaki oyuncunun yapacak hamlesi yoksa
                                if(gameOver(nextColor)){
                                    if(isKingUnderAttack(nextColor)){
                                        JOptionPane.showMessageDialog(null, "ŞAH MAT! Kazanan: " + piece.color);
                                    }
                                    else {
                                        JOptionPane.showMessageDialog(null, "PAT! (Berabere)");
                                    }
                                    // Oyunu dondurmak için seçimi kapatıyoruz
                                    Select = true;
                                    // (Select=true yaparsak kullanıcı başka taşa tıklayamaz hale gelir,
                                    // tabii daha şık bir "restart" mantığı da kurulabilir)
                                }
                            }
                        }else{
                            OldSelectedSquare.setBackground(oldColor);
                        }
                        Select = false;
                    }
                }
            }
        });
    }
    boolean isKingUnderAttack(String myColor){
        int kingX=-1, kingY =-1;

        for(int i = 0; i<8;i++){
            for(int j = 0; j<8;j++){
            if(loc[i][j].getComponentCount()>0){
                Pieces piece = (Pieces) loc[i][j].getComponent(0);
                if(piece instanceof King && myColor.equals(piece.color)){
                    kingX=i;
                    kingY=j;
                    break;
                }
            }
            }
        }
        for(int i = 0; i<8; i++){
            for(int j = 0; j<8;j++){
                if(loc[i][j].getComponentCount()>0){
                    Pieces piece = (Pieces) loc[i][j].getComponent(0);
                    if(!piece.color.equals(myColor)){
                        if(piece.isCanMove(loc, i,j,kingX,kingY)==true){
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
    boolean gameOver(String myColor){
        for(int i = 0; i<8; i++){
            for(int j = 0; j<8;j++){
                if(loc[i][j].getComponentCount()>0){
                    Pieces piece = (Pieces) loc[i][j].getComponent(0);
                    if(piece.color.equals(myColor)){

                        for(int k = 0; k<8; k++){
                            for(int l = 0; l<8; l++){

                                if(piece.isCanMove(loc,i,j,k,l)){

                                    boolean isFriend=false;
                                    if(loc[k][l].getComponentCount()>0){
                                        Pieces target = (Pieces) loc[k][l].getComponent(0);
                                        if(target.color.equals(myColor)){
                                            isFriend=true;
                                        }
                                    }

                                    if(!isFriend){
                                        Pieces deadPiece = null;
                                        if(loc[k][l].getComponentCount()>0){
                                            deadPiece = (Pieces) loc[k][l].getComponent(0);
                                        }

                                        loc[k][l].removeAll();
                                        loc[k][l].add(piece);

                                        boolean isSafe = !isKingUnderAttack(myColor);

                                        loc[i][j].add(piece);
                                        if(deadPiece != null){
                                            loc[k][l].add(deadPiece);
                                        }
                                        loc[k][l].repaint();
                                        loc[i][j].repaint();

                                        if(isSafe){
                                            return false;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return true;
    }

}


