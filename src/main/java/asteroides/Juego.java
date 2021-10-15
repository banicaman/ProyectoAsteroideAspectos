/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package asteroides;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import javax.sound.sampled.*;
import java.net.URL;
import java.io.*;
import java.net.MalformedURLException;

/**
 *
 * @author Hasser
 */
public class Juego extends JPanel {

    /**
     * @return the asteroides
     */
    public ArrayList<Asteroide> getAsteroides() {
        return asteroides;
    }

    private Nave nave;
    private ArrayList<Asteroide> asteroides;

    public Juego() {

        // Inicializar los objetos del juego
        nave = new Nave(this);
        asteroides = new ArrayList();
        // Agregar un asteroide
        anadeAsteroide();
        // Agregar un keyListener
        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                nave.keyPressed(e);
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
        setFocusable(true);

    }

    public static void main(String[] args) throws InterruptedException, MalformedURLException, UnsupportedAudioFileException, LineUnavailableException, IOException {

        Juego juego = new Juego();

        JFrame frame = new JFrame("Colision de Asteroides con Programacion Orientada a Aspecto");
        frame.add(juego);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        int iteraciones = 0;
        while (true) {
            juego.mover();
            juego.repaint();
            Thread.sleep(10);
            iteraciones++;
            if (iteraciones % 500 == 0) {
                juego.anadeAsteroide();
            }
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);//Necesario para limpiar la pantalla antes de volver a pintar
        for (Asteroide asteroide : getAsteroides()) {
            asteroide.paint(g);
        }
        nave.paint(g);
    }

    /**
     * Se encarga de mover los elementos del juego
     */
    private void mover() throws UnsupportedAudioFileException, IOException, MalformedURLException, LineUnavailableException {
        nave.mover();
        for (Asteroide asteroide : getAsteroides()) {
            asteroide.mover();
        }
    }

    private void anadeAsteroide() {
        Asteroide asteroide = new Asteroide(this);
        getAsteroides().add(asteroide);
    }

    void gameOver() throws MalformedURLException, UnsupportedAudioFileException, IOException, LineUnavailableException {
        URL file = new URL("https://www.wavsource.com/snds_2020-10-01_3728627494378403/sfx/applause_y.wav");
        //  URL file = new URL("https://drive.google.com/file/d/1QUIarzX2CzFGoIzjQ3mWjtH2NLl4W4bu/view");

//        URL file = new URL("./audio.wav");
        AudioInputStream ais = AudioSystem.getAudioInputStream(file);
        Clip clip = AudioSystem.getClip();
        clip.open(ais);
        clip.start();
        JOptionPane.showMessageDialog(this, "Game over", "Game over", JOptionPane.YES_NO_OPTION);

        System.exit(ABORT);
    }
}
