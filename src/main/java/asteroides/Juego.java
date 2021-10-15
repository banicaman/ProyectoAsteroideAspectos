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
    
    public Juego(){
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
    
    public static void main(String[] args) throws InterruptedException {
        Juego juego = new Juego();
        
        JFrame frame = new JFrame("Colision de Asteroides con Programacion Orientada a Aspecto");
        frame.add(juego);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        int iteraciones = 0;
        while(true){
            juego.mover();
            juego.repaint();
            Thread.sleep(10);
            iteraciones++;
            if(iteraciones % 500 == 0){
                juego.anadeAsteroide();
            }
        }
    }
    
    @Override
    public void paint(Graphics g){
        super.paint(g);//Necesario para limpiar la pantalla antes de volver a pintar
        for(Asteroide asteroide:getAsteroides()){
            asteroide.paint(g);
        }
        nave.paint(g);
    }
    
    /**
     * Se encarga de mover los elementos del juego
     */
    private void mover(){
        nave.mover();
        for(Asteroide asteroide:getAsteroides()){
            asteroide.mover();
        }
    }

    private void anadeAsteroide() {
        Asteroide asteroide = new Asteroide(this);
        getAsteroides().add(asteroide);
    }

    void gameOver() {
        JOptionPane.showMessageDialog(this, "Game over", "Game over", JOptionPane.YES_NO_OPTION);
        System.exit(ABORT);
    }
}
