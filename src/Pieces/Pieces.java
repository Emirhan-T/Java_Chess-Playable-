package Pieces;

import javax.swing.*;

public abstract class Pieces extends JLabel {
    public String color;
    public boolean hasMoved = false;
    public Pieces(String color) {
        this.color = color;
        this.setHorizontalAlignment(JLabel.CENTER);
    }

    public abstract boolean isCanMove(JPanel[][] board, int oldX, int oldY, int newX, int newY);
}
