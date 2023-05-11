package gui;

import javax.swing.JFrame;

public class BibliotecaMunicipal extends JFrame {
	
    public static void main(String[] args) {
        JFrame ventanaPrincipal = new JFrame("Biblioteca Municipal");
        ventanaPrincipal.setSize(800, 600);
        ventanaPrincipal.setVisible(true);
        ventanaPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}
