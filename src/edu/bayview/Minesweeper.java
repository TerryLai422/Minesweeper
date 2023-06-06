package edu.bayview;
import java.awt.EventQueue;

public class Minesweeper {

    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {

            var ex = new Window();
            ex.setVisible(true);
        });
    }
}
