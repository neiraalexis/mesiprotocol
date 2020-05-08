// NOTE: You need to manually change the package name on the next line and in the line within the main function.
// This will be automated in future versions once my Netbeans powers have developed...
package sketchMesi;

import processing.core.PApplet;
import processing.core.PFont;
import hadware.Bus;
import hadware.Memoria;
import hadware.Procesador;

/**
 *@author Alexis Neira García
 */
public class Main extends PApplet {

    private Memoria memoriaPrincipal;
    private Procesador procesador1;
    private Procesador procesador2;
    private Procesador procesador3;
    private boolean modifyP1,  modifyP2,  modifyP3;
    private Bus bus;
    int nroSocketP1, nroSocketP2, nroSocketP3, colorProcessors, colorEllipse1, colorEllipse2, colorEllipse3;
    int dato1, dato2, dato3, dato4;
    float endY3 = 230f;//altura de la salida del procc;
    float step = 0.02f;    // Size of each step along the path
    PFont font;
    private boolean pressedP1,  pressedP2,  pressedP3;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        PApplet.main(new String[]{"sketchMesi.Main"});
    }

    @Override
    public void setup() {
        font = createFont("Arial", 12);
        textFont(font);
        bus = new Bus();
        setupProcessors();
        setupMemories();
        size(900, 700);
        colorProcessors = color(100, 100, 100);
        colorEllipse1 = color(118, 172, 254);
        colorEllipse2 = color(118, 172, 254);
        colorEllipse3 = color(118, 172, 254);
        background(color(226, 237, 205));
        noStroke();
        smooth();
    }

    @Override
    public void draw() {
        fill(color(118, 172, 254));
        rect(10, 15, width / 6, 15);
        fill(color(255, 147, 147));
        rect(10, 10, width / 6, 10);
        checkStatus();
        drawHadware();
        if (bus.getTravel(0).isTraveling()) {
            bus.getTravel(0).incrementAdvance(step, 1);
        }

        if (bus.getTravel(1).isTraveling()) {
            bus.getTravel(1).incrementAdvance(step, 2);
        }
        if (bus.getTravel(2).isTraveling()) {
            bus.getTravel(2).incrementAdvance(step, 3);
        }
        drawText();
        fill(colorEllipse1);
        ellipse(bus.getTravel(0).getEllipseCoordinateX(), bus.getTravel(0).getEllipseCoordinateY(), 8, 8);
        fill(colorEllipse2);
        ellipse(bus.getTravel(1).getEllipseCoordinateX(), bus.getTravel(1).getEllipseCoordinateY(), 8, 8);
        fill(colorEllipse3);
        ellipse(bus.getTravel(2).getEllipseCoordinateX(), bus.getTravel(2).getEllipseCoordinateY(), 8, 8);
    }

    @Override
    public void mousePressed() {

        if (!pressedP1) {
            nroSocketP1 = procesador1.buttonPressed(mouseX, mouseY);
        }
        if (!pressedP2) {
            nroSocketP2 = procesador2.buttonPressed(mouseX, mouseY);
        }
        if (!pressedP3) {
            nroSocketP3 = procesador3.buttonPressed(mouseX, mouseY);
        }

        if (procesador1.isActive() && !pressedP1) {
            pressedP1 = true;
            colorEllipse1 = color(118, 172, 254);
            if (nroSocketP1 == 1) {
                dato1 = Integer.parseInt(memoriaPrincipal.getSocket(1).getLabel()) + 1;
            } else if (nroSocketP1 == 3) {
                dato2 = Integer.parseInt(memoriaPrincipal.getSocket(3).getLabel()) + 1;
            } else if (nroSocketP1 == 5) {
                dato3 = Integer.parseInt(memoriaPrincipal.getSocket(5).getLabel()) + 1;
            } else if (nroSocketP1 == 7) {
                dato4 = Integer.parseInt(memoriaPrincipal.getSocket(7).getLabel()) + 1;
            }

            paintWay(0);
        } else if (procesador2.isActive() && !pressedP2) {
            pressedP2 = true;
            colorEllipse2 = color(118, 172, 254);
            if (nroSocketP2 == 1) {
                dato1 = Integer.parseInt(memoriaPrincipal.getSocket(1).getLabel()) + 1;
            } else if (nroSocketP2 == 3) {
                dato2 = Integer.parseInt(memoriaPrincipal.getSocket(3).getLabel()) + 1;
            } else if (nroSocketP2 == 5) {
                dato3 = Integer.parseInt(memoriaPrincipal.getSocket(5).getLabel()) + 1;
            } else if (nroSocketP2 == 7) {
                dato4 = Integer.parseInt(memoriaPrincipal.getSocket(7).getLabel()) + 1;
            }
            paintWay(1);
        } else if (procesador3.isActive() && !pressedP3) {
            pressedP3 = true;
            colorEllipse3 = color(118, 172, 254);
            if (nroSocketP3 == 1) {
                dato1 = Integer.parseInt(memoriaPrincipal.getSocket(1).getLabel()) + 1;
            } else if (nroSocketP3 == 3) {
                dato2 = Integer.parseInt(memoriaPrincipal.getSocket(3).getLabel()) + 1;
            } else if (nroSocketP3 == 5) {
                dato3 = Integer.parseInt(memoriaPrincipal.getSocket(5).getLabel()) + 1;
            } else if (nroSocketP3 == 7) {
                dato4 = Integer.parseInt(memoriaPrincipal.getSocket(7).getLabel()) + 1;
            }
            paintWay(2);
        }
    }

    private void paintWay(int travel) {
        bus.getTravel(travel).setTraveling(true);
    }

    private void drawHadware() {

        fill(colorProcessors);
        rect(procesador1.getCoordinateX(), procesador1.getCoordinateY(), procesador1.getSizeX(), procesador1.getSizeY());
        rect(procesador2.getCoordinateX(), procesador2.getCoordinateY(), procesador2.getSizeX(), procesador2.getSizeY());
        rect(procesador3.getCoordinateX(), procesador3.getCoordinateY(), procesador3.getSizeX(), procesador3.getSizeY());
        //   }
        fill(procesador1.getSocket(0).getColor());
        rect(procesador1.getSocket(0).getCoordinateX(), procesador1.getSocket(1).getCoordinateY(), procesador1.getSocket(0).getSizeX(), procesador1.getSocket(0).getSizeY());
        fill(procesador1.getSocket(1).getColor());
        rect(procesador1.getSocket(1).getCoordinateX(), procesador1.getSocket(1).getCoordinateY(), procesador1.getSocket(1).getSizeX(), procesador1.getSocket(1).getSizeY());
        fill(procesador1.getSocket(2).getColor());
        rect(procesador1.getSocket(2).getCoordinateX(), procesador1.getSocket(2).getCoordinateY(), procesador1.getSocket(2).getSizeX(), procesador1.getSocket(2).getSizeY());
        fill(procesador1.getSocket(3).getColor());
        rect(procesador1.getSocket(3).getCoordinateX(), procesador1.getSocket(3).getCoordinateY(), procesador1.getSocket(3).getSizeX(), procesador1.getSocket(3).getSizeY());
        fill(procesador1.getSocket(4).getColor());
        rect(procesador1.getSocket(4).getCoordinateX(), procesador1.getSocket(4).getCoordinateY(), procesador1.getSocket(4).getSizeX(), procesador1.getSocket(4).getSizeY());
        fill(procesador1.getSocket(5).getColor());
        rect(procesador1.getSocket(5).getCoordinateX(), procesador1.getSocket(5).getCoordinateY(), procesador1.getSocket(5).getSizeX(), procesador1.getSocket(5).getSizeY());
        fill(procesador1.getSocket(6).getColor());
        rect(procesador1.getSocket(6).getCoordinateX(), procesador1.getSocket(6).getCoordinateY(), procesador1.getSocket(6).getSizeX(), procesador1.getSocket(6).getSizeY());
        fill(procesador1.getSocket(7).getColor());
        rect(procesador1.getSocket(7).getCoordinateX(), procesador1.getSocket(7).getCoordinateY(), procesador1.getSocket(7).getSizeX(), procesador1.getSocket(7).getSizeY());

        fill(procesador2.getSocket(0).getColor());
        rect(procesador2.getSocket(0).getCoordinateX(), procesador2.getSocket(1).getCoordinateY(), procesador2.getSocket(0).getSizeX(), procesador2.getSocket(0).getSizeY());
        fill(procesador2.getSocket(1).getColor());
        rect(procesador2.getSocket(1).getCoordinateX(), procesador2.getSocket(1).getCoordinateY(), procesador2.getSocket(1).getSizeX(), procesador2.getSocket(1).getSizeY());
        fill(procesador2.getSocket(2).getColor());
        rect(procesador2.getSocket(2).getCoordinateX(), procesador2.getSocket(2).getCoordinateY(), procesador2.getSocket(2).getSizeX(), procesador2.getSocket(2).getSizeY());
        fill(procesador2.getSocket(3).getColor());
        rect(procesador2.getSocket(3).getCoordinateX(), procesador2.getSocket(3).getCoordinateY(), procesador2.getSocket(3).getSizeX(), procesador2.getSocket(3).getSizeY());
        fill(procesador2.getSocket(4).getColor());
        rect(procesador2.getSocket(4).getCoordinateX(), procesador2.getSocket(4).getCoordinateY(), procesador2.getSocket(4).getSizeX(), procesador2.getSocket(4).getSizeY());
        fill(procesador2.getSocket(5).getColor());
        rect(procesador2.getSocket(5).getCoordinateX(), procesador2.getSocket(5).getCoordinateY(), procesador2.getSocket(5).getSizeX(), procesador2.getSocket(5).getSizeY());
        fill(procesador2.getSocket(6).getColor());
        rect(procesador2.getSocket(6).getCoordinateX(), procesador2.getSocket(6).getCoordinateY(), procesador2.getSocket(6).getSizeX(), procesador2.getSocket(6).getSizeY());
        fill(procesador2.getSocket(7).getColor());
        rect(procesador2.getSocket(7).getCoordinateX(), procesador2.getSocket(7).getCoordinateY(), procesador2.getSocket(7).getSizeX(), procesador2.getSocket(7).getSizeY());

        fill(procesador3.getSocket(0).getColor());
        rect(procesador3.getSocket(0).getCoordinateX(), procesador3.getSocket(1).getCoordinateY(), procesador3.getSocket(0).getSizeX(), procesador3.getSocket(0).getSizeY());
        fill(procesador3.getSocket(1).getColor());
        rect(procesador3.getSocket(1).getCoordinateX(), procesador3.getSocket(1).getCoordinateY(), procesador3.getSocket(1).getSizeX(), procesador3.getSocket(1).getSizeY());
        fill(procesador3.getSocket(2).getColor());
        rect(procesador3.getSocket(2).getCoordinateX(), procesador3.getSocket(2).getCoordinateY(), procesador3.getSocket(2).getSizeX(), procesador3.getSocket(2).getSizeY());
        fill(procesador3.getSocket(3).getColor());
        rect(procesador3.getSocket(3).getCoordinateX(), procesador3.getSocket(3).getCoordinateY(), procesador3.getSocket(3).getSizeX(), procesador3.getSocket(3).getSizeY());
        fill(procesador3.getSocket(4).getColor());
        rect(procesador3.getSocket(4).getCoordinateX(), procesador3.getSocket(4).getCoordinateY(), procesador3.getSocket(4).getSizeX(), procesador3.getSocket(4).getSizeY());
        fill(procesador3.getSocket(5).getColor());
        rect(procesador3.getSocket(5).getCoordinateX(), procesador3.getSocket(5).getCoordinateY(), procesador3.getSocket(5).getSizeX(), procesador3.getSocket(5).getSizeY());
        fill(procesador3.getSocket(6).getColor());
        rect(procesador3.getSocket(6).getCoordinateX(), procesador3.getSocket(6).getCoordinateY(), procesador3.getSocket(6).getSizeX(), procesador3.getSocket(6).getSizeY());
        fill(procesador3.getSocket(7).getColor());
        rect(procesador3.getSocket(7).getCoordinateX(), procesador3.getSocket(7).getCoordinateY(), procesador3.getSocket(7).getSizeX(), procesador3.getSocket(7).getSizeY());
        drawMemories();
    }

    private void drawMemories() {
        fill(memoriaPrincipal.getSocket(0).getColor());
        rect(memoriaPrincipal.getSocket(0).getCoordinateX(), memoriaPrincipal.getSocket(0).getCoordinateY(), memoriaPrincipal.getSocket(0).getSizeX(), memoriaPrincipal.getSocket(0).getSizeY());
        fill(memoriaPrincipal.getSocket(1).getColor());
        rect(memoriaPrincipal.getSocket(1).getCoordinateX(), memoriaPrincipal.getSocket(1).getCoordinateY(), memoriaPrincipal.getSocket(1).getSizeX(), memoriaPrincipal.getSocket(1).getSizeY());
        fill(memoriaPrincipal.getSocket(2).getColor());
        rect(memoriaPrincipal.getSocket(2).getCoordinateX(), memoriaPrincipal.getSocket(2).getCoordinateY(), memoriaPrincipal.getSocket(2).getSizeX(), memoriaPrincipal.getSocket(2).getSizeY());
        fill(memoriaPrincipal.getSocket(3).getColor());
        rect(memoriaPrincipal.getSocket(3).getCoordinateX(), memoriaPrincipal.getSocket(3).getCoordinateY(), memoriaPrincipal.getSocket(3).getSizeX(), memoriaPrincipal.getSocket(3).getSizeY());
        fill(memoriaPrincipal.getSocket(4).getColor());
        rect(memoriaPrincipal.getSocket(4).getCoordinateX(), memoriaPrincipal.getSocket(4).getCoordinateY(), memoriaPrincipal.getSocket(4).getSizeX(), memoriaPrincipal.getSocket(4).getSizeY());
        fill(memoriaPrincipal.getSocket(5).getColor());
        rect(memoriaPrincipal.getSocket(5).getCoordinateX(), memoriaPrincipal.getSocket(5).getCoordinateY(), memoriaPrincipal.getSocket(5).getSizeX(), memoriaPrincipal.getSocket(5).getSizeY());
        fill(memoriaPrincipal.getSocket(6).getColor());
        rect(memoriaPrincipal.getSocket(6).getCoordinateX(), memoriaPrincipal.getSocket(6).getCoordinateY(), memoriaPrincipal.getSocket(6).getSizeX(), memoriaPrincipal.getSocket(6).getSizeY());
        fill(memoriaPrincipal.getSocket(7).getColor());
        rect(memoriaPrincipal.getSocket(7).getCoordinateX(), memoriaPrincipal.getSocket(7).getCoordinateY(), memoriaPrincipal.getSocket(7).getSizeX(), memoriaPrincipal.getSocket(7).getSizeY());

        fill(procesador1.getMemoriaCache().getSocket(0).getColor());
        rect(procesador1.getMemoriaCache().getSocket(0).getCoordinateX(), procesador1.getMemoriaCache().getCoordinateY(), procesador1.getMemoriaCache().getSocket(0).getSizeX(), procesador1.getMemoriaCache().getSocket(0).getSizeY());

        fill(procesador1.getMemoriaCache().getSocket(1).getColor());
        rect(procesador1.getMemoriaCache().getSocket(1).getCoordinateX(), procesador1.getMemoriaCache().getCoordinateY(), procesador1.getMemoriaCache().getSocket(1).getSizeX() + 10, procesador1.getMemoriaCache().getSocket(1).getSizeY());
        fill(procesador1.getMemoriaCache().getSocket(2).getColor());
        rect(procesador1.getMemoriaCache().getSocket(2).getCoordinateX() + 10, procesador1.getMemoriaCache().getCoordinateY(), procesador1.getMemoriaCache().getSocket(2).getSizeX() - 10, procesador1.getMemoriaCache().getSocket(2).getSizeY());
        fill(procesador1.getMemoriaCache().getSocket(3).getColor());
        rect(procesador1.getMemoriaCache().getSocket(3).getCoordinateX(), procesador1.getMemoriaCache().getSocket(3).getCoordinateY(), procesador1.getMemoriaCache().getSocket(3).getSizeX(), procesador1.getMemoriaCache().getSocket(3).getSizeY());
        fill(procesador1.getMemoriaCache().getSocket(4).getColor());
        rect(procesador1.getMemoriaCache().getSocket(4).getCoordinateX(), procesador1.getMemoriaCache().getSocket(4).getCoordinateY(), procesador1.getMemoriaCache().getSocket(4).getSizeX() + 10, procesador1.getMemoriaCache().getSocket(4).getSizeY());
        fill(procesador1.getMemoriaCache().getSocket(5).getColor());
        rect(procesador1.getMemoriaCache().getSocket(5).getCoordinateX() + 10, procesador1.getMemoriaCache().getSocket(5).getCoordinateY(), procesador1.getMemoriaCache().getSocket(5).getSizeX() - 10, procesador1.getMemoriaCache().getSocket(5).getSizeY());

        fill(procesador2.getMemoriaCache().getSocket(0).getColor());
        rect(procesador2.getMemoriaCache().getSocket(0).getCoordinateX(), procesador2.getMemoriaCache().getCoordinateY(), procesador2.getMemoriaCache().getSocket(0).getSizeX(), procesador2.getMemoriaCache().getSocket(0).getSizeY());
        fill(procesador2.getMemoriaCache().getSocket(1).getColor());
        rect(procesador2.getMemoriaCache().getSocket(1).getCoordinateX(), procesador2.getMemoriaCache().getCoordinateY(), procesador2.getMemoriaCache().getSocket(1).getSizeX() + 10, procesador2.getMemoriaCache().getSocket(1).getSizeY());
        fill(procesador2.getMemoriaCache().getSocket(2).getColor());
        rect(procesador2.getMemoriaCache().getSocket(2).getCoordinateX() + 10, procesador2.getMemoriaCache().getCoordinateY(), procesador2.getMemoriaCache().getSocket(2).getSizeX() - 10, procesador2.getMemoriaCache().getSocket(2).getSizeY());
        fill(procesador2.getMemoriaCache().getSocket(3).getColor());
        rect(procesador2.getMemoriaCache().getSocket(3).getCoordinateX(), procesador2.getMemoriaCache().getSocket(3).getCoordinateY(), procesador2.getMemoriaCache().getSocket(3).getSizeX(), procesador2.getMemoriaCache().getSocket(3).getSizeY());
        fill(procesador2.getMemoriaCache().getSocket(4).getColor());
        rect(procesador2.getMemoriaCache().getSocket(4).getCoordinateX(), procesador2.getMemoriaCache().getSocket(4).getCoordinateY(), procesador2.getMemoriaCache().getSocket(4).getSizeX() + 10, procesador2.getMemoriaCache().getSocket(4).getSizeY());
        fill(procesador2.getMemoriaCache().getSocket(5).getColor());
        rect(procesador2.getMemoriaCache().getSocket(5).getCoordinateX() + 10, procesador2.getMemoriaCache().getSocket(5).getCoordinateY(), procesador2.getMemoriaCache().getSocket(5).getSizeX() - 10, procesador2.getMemoriaCache().getSocket(5).getSizeY());

        fill(procesador3.getMemoriaCache().getSocket(0).getColor());
        rect(procesador3.getMemoriaCache().getSocket(0).getCoordinateX(), procesador3.getMemoriaCache().getCoordinateY(), procesador3.getMemoriaCache().getSocket(0).getSizeX(), procesador3.getMemoriaCache().getSocket(0).getSizeY());
        fill(procesador3.getMemoriaCache().getSocket(1).getColor());
        rect(procesador3.getMemoriaCache().getSocket(1).getCoordinateX(), procesador3.getMemoriaCache().getCoordinateY(), procesador3.getMemoriaCache().getSocket(1).getSizeX() + 10, procesador3.getMemoriaCache().getSocket(1).getSizeY());
        fill(procesador3.getMemoriaCache().getSocket(2).getColor());
        rect(procesador3.getMemoriaCache().getSocket(2).getCoordinateX() + 10, procesador3.getMemoriaCache().getCoordinateY(), procesador3.getMemoriaCache().getSocket(2).getSizeX() - 10, procesador3.getMemoriaCache().getSocket(2).getSizeY());
        fill(procesador3.getMemoriaCache().getSocket(3).getColor());
        rect(procesador3.getMemoriaCache().getSocket(3).getCoordinateX(), procesador3.getMemoriaCache().getSocket(3).getCoordinateY(), procesador3.getMemoriaCache().getSocket(3).getSizeX(), procesador3.getMemoriaCache().getSocket(3).getSizeY());
        fill(procesador3.getMemoriaCache().getSocket(4).getColor());
        rect(procesador3.getMemoriaCache().getSocket(4).getCoordinateX(), procesador3.getMemoriaCache().getSocket(4).getCoordinateY(), procesador3.getMemoriaCache().getSocket(4).getSizeX() + 10, procesador3.getMemoriaCache().getSocket(4).getSizeY());
        fill(procesador3.getMemoriaCache().getSocket(5).getColor());
        rect(procesador3.getMemoriaCache().getSocket(5).getCoordinateX() + 10, procesador3.getMemoriaCache().getSocket(5).getCoordinateY(), procesador3.getMemoriaCache().getSocket(5).getSizeX() - 10, procesador3.getMemoriaCache().getSocket(5).getSizeY());
    }

    private void drawText() {
        fill(0);
        textAlign(LEFT);
        text("This simulate the protocol  ", 10, height - 20);
        text(" memory coherence and cache coherency. ", 10, height - 5);



        fill(color(1, 54, 135));
        textAlign(LEFT);
        text("Desarrollado por : Alexis Neira G.", width - 250, height / 15);
        text("Profesor :           DARIO BERNAL", width - 250, height / 15 + 20);
        text("Asignatura :      Arquitectura del Computador", width - 250, height / 15 + 40);
        text("Versión :  1.0      Año-2009", width - 250, height / 15 + 60);
        text(" * Data Bus", width / 6, 35);
        text(" * Addres Bus", width / 6, 20);



        textAlign(CENTER);
        text(procesador1.getMemoriaCache().getSocket(0).getLabel(), procesador1.getMemoriaCache().getSocket(0).getCoordinateX() + procesador1.getMemoriaCache().getSocket(0).getSizeX() / 2, procesador1.getMemoriaCache().getSocket(0).getCoordinateY() + procesador1.getMemoriaCache().getSocket(0).getSizeY() / 2 + 5);
        text(procesador1.getMemoriaCache().getSocket(1).getLabel(), procesador1.getMemoriaCache().getSocket(1).getCoordinateX() + procesador1.getMemoriaCache().getSocket(1).getSizeX() / 2, procesador1.getMemoriaCache().getSocket(1).getCoordinateY() + procesador1.getMemoriaCache().getSocket(1).getSizeY() / 2 + 5);
        text(procesador1.getMemoriaCache().getSocket(2).getLabel(), procesador1.getMemoriaCache().getSocket(2).getCoordinateX() + procesador1.getMemoriaCache().getSocket(2).getSizeX() / 2, procesador1.getMemoriaCache().getSocket(2).getCoordinateY() + procesador1.getMemoriaCache().getSocket(2).getSizeY() / 2 + 5);
        text(procesador1.getMemoriaCache().getSocket(3).getLabel(), procesador1.getMemoriaCache().getSocket(3).getCoordinateX() + procesador1.getMemoriaCache().getSocket(3).getSizeX() / 2, procesador1.getMemoriaCache().getSocket(3).getCoordinateY() + procesador1.getMemoriaCache().getSocket(3).getSizeY() / 2 + 5);
        text(procesador1.getMemoriaCache().getSocket(4).getLabel(), procesador1.getMemoriaCache().getSocket(4).getCoordinateX() + procesador1.getMemoriaCache().getSocket(4).getSizeX() / 2, procesador1.getMemoriaCache().getSocket(4).getCoordinateY() + procesador1.getMemoriaCache().getSocket(4).getSizeY() / 2 + 5);
        text(procesador1.getMemoriaCache().getSocket(5).getLabel(), procesador1.getMemoriaCache().getSocket(5).getCoordinateX() + procesador1.getMemoriaCache().getSocket(5).getSizeX() / 2, procesador1.getMemoriaCache().getSocket(5).getCoordinateY() + procesador1.getMemoriaCache().getSocket(5).getSizeY() / 2 + 5);

        text(procesador2.getMemoriaCache().getSocket(0).getLabel(), procesador2.getMemoriaCache().getSocket(0).getCoordinateX() + procesador2.getMemoriaCache().getSocket(0).getSizeX() / 2, procesador2.getMemoriaCache().getSocket(0).getCoordinateY() + procesador2.getMemoriaCache().getSocket(0).getSizeY() / 2 + 5);
        text(procesador2.getMemoriaCache().getSocket(1).getLabel(), procesador2.getMemoriaCache().getSocket(1).getCoordinateX() + procesador2.getMemoriaCache().getSocket(1).getSizeX() / 2, procesador2.getMemoriaCache().getSocket(1).getCoordinateY() + procesador2.getMemoriaCache().getSocket(1).getSizeY() / 2 + 5);
        text(procesador2.getMemoriaCache().getSocket(2).getLabel(), procesador2.getMemoriaCache().getSocket(2).getCoordinateX() + procesador2.getMemoriaCache().getSocket(2).getSizeX() / 2, procesador2.getMemoriaCache().getSocket(2).getCoordinateY() + procesador2.getMemoriaCache().getSocket(2).getSizeY() / 2 + 5);
        text(procesador2.getMemoriaCache().getSocket(3).getLabel(), procesador2.getMemoriaCache().getSocket(3).getCoordinateX() + procesador2.getMemoriaCache().getSocket(3).getSizeX() / 2, procesador2.getMemoriaCache().getSocket(3).getCoordinateY() + procesador2.getMemoriaCache().getSocket(3).getSizeY() / 2 + 5);
        text(procesador2.getMemoriaCache().getSocket(4).getLabel(), procesador2.getMemoriaCache().getSocket(4).getCoordinateX() + procesador2.getMemoriaCache().getSocket(4).getSizeX() / 2, procesador2.getMemoriaCache().getSocket(4).getCoordinateY() + procesador2.getMemoriaCache().getSocket(4).getSizeY() / 2 + 5);
        text(procesador2.getMemoriaCache().getSocket(5).getLabel(), procesador2.getMemoriaCache().getSocket(5).getCoordinateX() + procesador2.getMemoriaCache().getSocket(5).getSizeX() / 2, procesador2.getMemoriaCache().getSocket(5).getCoordinateY() + procesador2.getMemoriaCache().getSocket(5).getSizeY() / 2 + 5);

        text(procesador3.getMemoriaCache().getSocket(0).getLabel(), procesador3.getMemoriaCache().getSocket(0).getCoordinateX() + procesador3.getMemoriaCache().getSocket(0).getSizeX() / 2, procesador3.getMemoriaCache().getSocket(0).getCoordinateY() + procesador3.getMemoriaCache().getSocket(0).getSizeY() / 2 + 5);
        text(procesador3.getMemoriaCache().getSocket(1).getLabel(), procesador3.getMemoriaCache().getSocket(1).getCoordinateX() + procesador3.getMemoriaCache().getSocket(1).getSizeX() / 2, procesador3.getMemoriaCache().getSocket(1).getCoordinateY() + procesador3.getMemoriaCache().getSocket(1).getSizeY() / 2 + 5);
        text(procesador3.getMemoriaCache().getSocket(2).getLabel(), procesador3.getMemoriaCache().getSocket(2).getCoordinateX() + procesador3.getMemoriaCache().getSocket(2).getSizeX() / 2, procesador3.getMemoriaCache().getSocket(2).getCoordinateY() + procesador3.getMemoriaCache().getSocket(2).getSizeY() / 2 + 5);
        text(procesador3.getMemoriaCache().getSocket(3).getLabel(), procesador3.getMemoriaCache().getSocket(3).getCoordinateX() + procesador3.getMemoriaCache().getSocket(3).getSizeX() / 2, procesador3.getMemoriaCache().getSocket(3).getCoordinateY() + procesador3.getMemoriaCache().getSocket(3).getSizeY() / 2 + 5);
        text(procesador3.getMemoriaCache().getSocket(4).getLabel(), procesador3.getMemoriaCache().getSocket(4).getCoordinateX() + procesador3.getMemoriaCache().getSocket(4).getSizeX() / 2, procesador3.getMemoriaCache().getSocket(4).getCoordinateY() + procesador3.getMemoriaCache().getSocket(4).getSizeY() / 2 + 5);
        text(procesador3.getMemoriaCache().getSocket(5).getLabel(), procesador3.getMemoriaCache().getSocket(5).getCoordinateX() + procesador3.getMemoriaCache().getSocket(5).getSizeX() / 2, procesador3.getMemoriaCache().getSocket(5).getCoordinateY() + procesador3.getMemoriaCache().getSocket(5).getSizeY() / 2 + 5);

        text(memoriaPrincipal.getSocket(0).getLabel(), memoriaPrincipal.getSocket(0).getCoordinateX() + memoriaPrincipal.getSocket(0).getSizeX() / 4, memoriaPrincipal.getSocket(0).getCoordinateY() + memoriaPrincipal.getSocket(0).getSizeY() / 2);
        text(memoriaPrincipal.getSocket(1).getLabel(), memoriaPrincipal.getSocket(1).getCoordinateX() + memoriaPrincipal.getSocket(1).getSizeX() / 4, memoriaPrincipal.getSocket(1).getCoordinateY() + memoriaPrincipal.getSocket(1).getSizeY() / 2);
        text(memoriaPrincipal.getSocket(2).getLabel(), memoriaPrincipal.getSocket(2).getCoordinateX() + memoriaPrincipal.getSocket(2).getSizeX() / 4, memoriaPrincipal.getSocket(2).getCoordinateY() + memoriaPrincipal.getSocket(2).getSizeY() / 2);
        text(memoriaPrincipal.getSocket(3).getLabel(), memoriaPrincipal.getSocket(3).getCoordinateX() + memoriaPrincipal.getSocket(3).getSizeX() / 4, memoriaPrincipal.getSocket(3).getCoordinateY() + memoriaPrincipal.getSocket(3).getSizeY() / 2);
        text(memoriaPrincipal.getSocket(4).getLabel(), memoriaPrincipal.getSocket(4).getCoordinateX() + memoriaPrincipal.getSocket(4).getSizeX() / 4, memoriaPrincipal.getSocket(4).getCoordinateY() + memoriaPrincipal.getSocket(4).getSizeY() / 2);
        text(memoriaPrincipal.getSocket(5).getLabel(), memoriaPrincipal.getSocket(5).getCoordinateX() + memoriaPrincipal.getSocket(5).getSizeX() / 4, memoriaPrincipal.getSocket(5).getCoordinateY() + memoriaPrincipal.getSocket(5).getSizeY() / 2);
        text(memoriaPrincipal.getSocket(6).getLabel(), memoriaPrincipal.getSocket(6).getCoordinateX() + memoriaPrincipal.getSocket(6).getSizeX() / 4, memoriaPrincipal.getSocket(6).getCoordinateY() + memoriaPrincipal.getSocket(6).getSizeY() / 2);
        text(memoriaPrincipal.getSocket(7).getLabel(), memoriaPrincipal.getSocket(7).getCoordinateX() + memoriaPrincipal.getSocket(7).getSizeX() / 4, memoriaPrincipal.getSocket(7).getCoordinateY() + memoriaPrincipal.getSocket(7).getSizeY() / 2);

        text(procesador1.getSocket(0).getLabel(), procesador1.getSocket(0).getCoordinateX() + procesador1.getSocket(0).getSizeX() / 2, procesador1.getSocket(0).getCoordinateY() + procesador1.getSocket(0).getSizeY() / 2 + 5);
        text(procesador1.getSocket(1).getLabel(), procesador1.getSocket(1).getCoordinateX() + procesador1.getSocket(1).getSizeX() / 2, procesador1.getSocket(1).getCoordinateY() + procesador1.getSocket(1).getSizeY() / 2 + 5);
        text(procesador1.getSocket(2).getLabel(), procesador1.getSocket(2).getCoordinateX() + procesador1.getSocket(2).getSizeX() / 2, procesador1.getSocket(2).getCoordinateY() + procesador1.getSocket(2).getSizeY() / 2 + 5);
        text(procesador1.getSocket(3).getLabel(), procesador1.getSocket(3).getCoordinateX() + procesador1.getSocket(3).getSizeX() / 2, procesador1.getSocket(3).getCoordinateY() + procesador1.getSocket(3).getSizeY() / 2 + 5);
        text(procesador1.getSocket(4).getLabel(), procesador1.getSocket(4).getCoordinateX() + procesador1.getSocket(4).getSizeX() / 2, procesador1.getSocket(4).getCoordinateY() + procesador1.getSocket(4).getSizeY() / 2 + 5);
        text(procesador1.getSocket(5).getLabel(), procesador1.getSocket(5).getCoordinateX() + procesador1.getSocket(5).getSizeX() / 2, procesador1.getSocket(5).getCoordinateY() + procesador1.getSocket(5).getSizeY() / 2 + 5);
        text(procesador1.getSocket(6).getLabel(), procesador1.getSocket(6).getCoordinateX() + procesador1.getSocket(6).getSizeX() / 2, procesador1.getSocket(6).getCoordinateY() + procesador1.getSocket(6).getSizeY() / 2 + 5);
        text(procesador1.getSocket(7).getLabel(), procesador1.getSocket(7).getCoordinateX() + procesador1.getSocket(7).getSizeX() / 2, procesador1.getSocket(7).getCoordinateY() + procesador1.getSocket(7).getSizeY() / 2 + 5);

        text(procesador2.getSocket(0).getLabel(), procesador2.getSocket(0).getCoordinateX() + procesador2.getSocket(0).getSizeX() / 2, procesador2.getSocket(0).getCoordinateY() + procesador2.getSocket(0).getSizeY() / 2 + 5);
        text(procesador2.getSocket(1).getLabel(), procesador2.getSocket(1).getCoordinateX() + procesador2.getSocket(1).getSizeX() / 2, procesador2.getSocket(1).getCoordinateY() + procesador2.getSocket(1).getSizeY() / 2 + 5);
        text(procesador2.getSocket(2).getLabel(), procesador2.getSocket(2).getCoordinateX() + procesador2.getSocket(2).getSizeX() / 2, procesador2.getSocket(2).getCoordinateY() + procesador2.getSocket(2).getSizeY() / 2 + 5);
        text(procesador2.getSocket(3).getLabel(), procesador2.getSocket(3).getCoordinateX() + procesador2.getSocket(3).getSizeX() / 2, procesador2.getSocket(3).getCoordinateY() + procesador2.getSocket(3).getSizeY() / 2 + 5);
        text(procesador2.getSocket(4).getLabel(), procesador2.getSocket(4).getCoordinateX() + procesador2.getSocket(4).getSizeX() / 2, procesador2.getSocket(4).getCoordinateY() + procesador2.getSocket(4).getSizeY() / 2 + 5);
        text(procesador2.getSocket(5).getLabel(), procesador2.getSocket(5).getCoordinateX() + procesador2.getSocket(5).getSizeX() / 2, procesador2.getSocket(5).getCoordinateY() + procesador2.getSocket(5).getSizeY() / 2 + 5);
        text(procesador2.getSocket(6).getLabel(), procesador2.getSocket(6).getCoordinateX() + procesador2.getSocket(6).getSizeX() / 2, procesador2.getSocket(6).getCoordinateY() + procesador2.getSocket(6).getSizeY() / 2 + 5);
        text(procesador2.getSocket(7).getLabel(), procesador2.getSocket(7).getCoordinateX() + procesador2.getSocket(7).getSizeX() / 2, procesador2.getSocket(7).getCoordinateY() + procesador2.getSocket(7).getSizeY() / 2 + 5);

        text(procesador3.getSocket(0).getLabel(), procesador3.getSocket(0).getCoordinateX() + procesador3.getSocket(0).getSizeX() / 2, procesador3.getSocket(0).getCoordinateY() + procesador3.getSocket(0).getSizeY() / 2 + 5);
        text(procesador3.getSocket(1).getLabel(), procesador3.getSocket(1).getCoordinateX() + procesador3.getSocket(1).getSizeX() / 2, procesador3.getSocket(1).getCoordinateY() + procesador3.getSocket(1).getSizeY() / 2 + 5);
        text(procesador3.getSocket(2).getLabel(), procesador3.getSocket(2).getCoordinateX() + procesador3.getSocket(2).getSizeX() / 2, procesador3.getSocket(2).getCoordinateY() + procesador3.getSocket(2).getSizeY() / 2 + 5);
        text(procesador3.getSocket(3).getLabel(), procesador3.getSocket(3).getCoordinateX() + procesador3.getSocket(3).getSizeX() / 2, procesador3.getSocket(3).getCoordinateY() + procesador3.getSocket(3).getSizeY() / 2 + 5);
        text(procesador3.getSocket(4).getLabel(), procesador3.getSocket(4).getCoordinateX() + procesador3.getSocket(4).getSizeX() / 2, procesador3.getSocket(4).getCoordinateY() + procesador3.getSocket(4).getSizeY() / 2 + 5);
        text(procesador3.getSocket(5).getLabel(), procesador3.getSocket(5).getCoordinateX() + procesador3.getSocket(5).getSizeX() / 2, procesador3.getSocket(5).getCoordinateY() + procesador3.getSocket(5).getSizeY() / 2 + 5);
        text(procesador3.getSocket(6).getLabel(), procesador3.getSocket(6).getCoordinateX() + procesador3.getSocket(6).getSizeX() / 2, procesador3.getSocket(6).getCoordinateY() + procesador3.getSocket(6).getSizeY() / 2 + 5);
        text(procesador3.getSocket(7).getLabel(), procesador3.getSocket(7).getCoordinateX() + procesador3.getSocket(7).getSizeX() / 2, procesador3.getSocket(7).getCoordinateY() + procesador3.getSocket(7).getSizeY() / 2 + 5);
    }

    private void setupMemories() {
        memoriaPrincipal = new Memoria(8);
        memoriaPrincipal.setCoordinateX(370);
        memoriaPrincipal.setCoordinateY(142);
        memoriaPrincipal.setSizeX(160);
        memoriaPrincipal.setSizeY(80);

        memoriaPrincipal.setupSocket(0, memoriaPrincipal.getCoordinateX(), memoriaPrincipal.getCoordinateY(), memoriaPrincipal.getSizeX() / 2, memoriaPrincipal.getSizeY() / 4);
        memoriaPrincipal.setSocket(0, color(164, 255, 255), "0x0");
        memoriaPrincipal.setupSocket(1, memoriaPrincipal.getCoordinateX() + memoriaPrincipal.getSizeX() / 2, memoriaPrincipal.getCoordinateY(), memoriaPrincipal.getSizeX() / 2, memoriaPrincipal.getSizeY() / 4);
        memoriaPrincipal.setSocket(1, color(164, 255, 255), "0");

        memoriaPrincipal.setupSocket(2, memoriaPrincipal.getCoordinateX(), memoriaPrincipal.getSocket(0).getCoordinateY() + memoriaPrincipal.getSocket(0).getSizeY(), memoriaPrincipal.getSizeX() / 2, memoriaPrincipal.getSizeY() / 4);
        memoriaPrincipal.setSocket(2, color(100, 255, 255), "0x1");
        memoriaPrincipal.setupSocket(3, memoriaPrincipal.getCoordinateX() + memoriaPrincipal.getSizeX() / 2, memoriaPrincipal.getSocket(0).getCoordinateY() + memoriaPrincipal.getSocket(0).getSizeY(), memoriaPrincipal.getSizeX() / 2, memoriaPrincipal.getSizeY() / 4);
        memoriaPrincipal.setSocket(3, color(100, 255, 255), "0");

        memoriaPrincipal.setupSocket(4, memoriaPrincipal.getCoordinateX(), memoriaPrincipal.getSocket(2).getCoordinateY() + memoriaPrincipal.getSocket(1).getSizeY(), memoriaPrincipal.getSizeX() / 2, memoriaPrincipal.getSizeY() / 4);
        memoriaPrincipal.setSocket(4, color(0, 221, 221), "0x2");
        memoriaPrincipal.setupSocket(5, memoriaPrincipal.getCoordinateX() + memoriaPrincipal.getSizeX() / 2, memoriaPrincipal.getSocket(2).getCoordinateY() + memoriaPrincipal.getSocket(2).getSizeY(), memoriaPrincipal.getSizeX() / 2, memoriaPrincipal.getSizeY() / 4);
        memoriaPrincipal.setSocket(5, color(0, 221, 221), "0");

        memoriaPrincipal.setupSocket(6, memoriaPrincipal.getCoordinateX(), memoriaPrincipal.getSocket(4).getCoordinateY() + memoriaPrincipal.getSocket(4).getSizeY(), memoriaPrincipal.getSizeX(), memoriaPrincipal.getSizeY() / 4);
        memoriaPrincipal.setSocket(6, color(0, 198, 198), "0x3");
        memoriaPrincipal.setupSocket(7, memoriaPrincipal.getCoordinateX() + memoriaPrincipal.getSizeX() / 2, memoriaPrincipal.getSocket(4).getCoordinateY() + +memoriaPrincipal.getSocket(4).getSizeY(), memoriaPrincipal.getSizeX() / 2, memoriaPrincipal.getSizeY() / 4);
        memoriaPrincipal.setSocket(7, color(0, 198, 198), "0");

        procesador1.getMemoriaCache().setCoordinateX(procesador1.getCoordinateX() + 10);
        procesador1.getMemoriaCache().setCoordinateY(405);
        procesador1.getMemoriaCache().setSizeX(80);
        procesador1.getMemoriaCache().setSizeY(40);
        procesador1.getMemoriaCache().setupSocket(0, procesador1.getMemoriaCache().getCoordinateX() + 1, procesador1.getMemoriaCache().getCoordinateY(), procesador1.getMemoriaCache().getSizeX() / 3, procesador1.getMemoriaCache().getSizeY() / 2);
        procesador1.getMemoriaCache().setSocket(0, color(164, 255, 255), "I");
        procesador1.getMemoriaCache().setupSocket(1, procesador1.getMemoriaCache().getCoordinateX() + procesador1.getMemoriaCache().getSizeX() / 3 + 1, procesador1.getMemoriaCache().getCoordinateY(), procesador1.getMemoriaCache().getSizeX() / 3, procesador1.getMemoriaCache().getSizeY() / 2);
        procesador1.getMemoriaCache().setSocket(1, color(100, 255, 255), "");
        procesador1.getMemoriaCache().setupSocket(2, procesador1.getMemoriaCache().getCoordinateX() + 2 * procesador1.getMemoriaCache().getSizeX() / 3, procesador1.getMemoriaCache().getCoordinateY(), procesador1.getMemoriaCache().getSizeX() / 3, procesador1.getMemoriaCache().getSizeY() / 2);
        procesador1.getMemoriaCache().setSocket(2, color(0, 198, 198), "");
        procesador1.getMemoriaCache().setupSocket(3, procesador1.getMemoriaCache().getCoordinateX() + 1, procesador1.getMemoriaCache().getCoordinateY() + procesador1.getMemoriaCache().getSizeY() / 2, procesador1.getMemoriaCache().getSizeX() / 3, procesador1.getMemoriaCache().getSizeY() / 2);
        procesador1.getMemoriaCache().setSocket(3, color(0, 198, 198), "I");
        procesador1.getMemoriaCache().setupSocket(4, procesador1.getMemoriaCache().getCoordinateX() + procesador1.getMemoriaCache().getSizeX() / 3 + 1, procesador1.getMemoriaCache().getCoordinateY() + procesador1.getMemoriaCache().getSizeY() / 2, procesador1.getMemoriaCache().getSizeX() / 3, procesador1.getMemoriaCache().getSizeY() / 2);
        procesador1.getMemoriaCache().setSocket(4, color(0, 221, 221), "");
        procesador1.getMemoriaCache().setupSocket(5, procesador1.getMemoriaCache().getCoordinateX() + 2 * procesador1.getMemoriaCache().getSizeX() / 3, procesador1.getMemoriaCache().getCoordinateY() + procesador1.getMemoriaCache().getSizeY() / 2, procesador1.getMemoriaCache().getSizeX() / 3, procesador1.getMemoriaCache().getSizeY() / 2);
        procesador1.getMemoriaCache().setSocket(5, color(100, 255, 255), "");

        procesador2.getMemoriaCache().setCoordinateX(procesador2.getCoordinateX() + 10);
        procesador2.getMemoriaCache().setCoordinateY(405);
        procesador2.getMemoriaCache().setSizeX(80);
        procesador2.getMemoriaCache().setSizeY(40);
        procesador2.getMemoriaCache().setupSocket(0, procesador2.getMemoriaCache().getCoordinateX() + 1, procesador2.getMemoriaCache().getCoordinateY(), procesador2.getMemoriaCache().getSizeX() / 3, procesador2.getMemoriaCache().getSizeY() / 2);
        procesador2.getMemoriaCache().setSocket(0, color(0, 198, 198), "I");
        procesador2.getMemoriaCache().setupSocket(1, procesador2.getMemoriaCache().getCoordinateX() + procesador2.getMemoriaCache().getSizeX() / 3 + 1, procesador2.getMemoriaCache().getCoordinateY(), procesador2.getMemoriaCache().getSizeX() / 3, procesador2.getMemoriaCache().getSizeY() / 2);
        procesador2.getMemoriaCache().setSocket(1, color(0, 221, 221), "");
        procesador2.getMemoriaCache().setupSocket(2, procesador2.getMemoriaCache().getCoordinateX() + 2 * procesador2.getMemoriaCache().getSizeX() / 3, procesador2.getMemoriaCache().getCoordinateY(), procesador2.getMemoriaCache().getSizeX() / 3, procesador2.getMemoriaCache().getSizeY() / 2);
        procesador2.getMemoriaCache().setSocket(2, color(100, 255, 255), "");
        procesador2.getMemoriaCache().setupSocket(3, procesador2.getMemoriaCache().getCoordinateX() + 1, procesador2.getMemoriaCache().getCoordinateY() + procesador2.getMemoriaCache().getSizeY() / 2, procesador2.getMemoriaCache().getSizeX() / 3, procesador2.getMemoriaCache().getSizeY() / 2);
        procesador2.getMemoriaCache().setSocket(3, color(164, 255, 255), "I");
        procesador2.getMemoriaCache().setupSocket(4, procesador2.getMemoriaCache().getCoordinateX() + procesador2.getMemoriaCache().getSizeX() / 3 + 1, procesador2.getMemoriaCache().getCoordinateY() + procesador2.getMemoriaCache().getSizeY() / 2, procesador2.getMemoriaCache().getSizeX() / 3, procesador2.getMemoriaCache().getSizeY() / 2);
        procesador2.getMemoriaCache().setSocket(4, color(100, 255, 255), "");
        procesador2.getMemoriaCache().setupSocket(5, procesador2.getMemoriaCache().getCoordinateX() + 2 * procesador2.getMemoriaCache().getSizeX() / 3, procesador2.getMemoriaCache().getCoordinateY() + procesador2.getMemoriaCache().getSizeY() / 2, procesador2.getMemoriaCache().getSizeX() / 3, procesador2.getMemoriaCache().getSizeY() / 2);
        procesador2.getMemoriaCache().setSocket(5, color(0, 198, 198), "");

        procesador3.getMemoriaCache().setCoordinateX(procesador3.getCoordinateX() + 10);
        procesador3.getMemoriaCache().setCoordinateY(405);
        procesador3.getMemoriaCache().setSizeX(80);
        procesador3.getMemoriaCache().setSizeY(40);
        procesador3.getMemoriaCache().setupSocket(0, procesador3.getMemoriaCache().getCoordinateX() + 1, procesador3.getMemoriaCache().getCoordinateY(), procesador3.getMemoriaCache().getSizeX() / 3, procesador3.getMemoriaCache().getSizeY() / 2);
        procesador3.getMemoriaCache().setSocket(0, color(164, 255, 255), "I");
        procesador3.getMemoriaCache().setupSocket(1, procesador3.getMemoriaCache().getCoordinateX() + procesador3.getMemoriaCache().getSizeX() / 3 + 1, procesador3.getMemoriaCache().getCoordinateY(), procesador3.getMemoriaCache().getSizeX() / 3, procesador3.getMemoriaCache().getSizeY() / 2);
        procesador3.getMemoriaCache().setSocket(1, color(100, 255, 255), "");
        procesador3.getMemoriaCache().setupSocket(2, procesador3.getMemoriaCache().getCoordinateX() + 2 * procesador3.getMemoriaCache().getSizeX() / 3, procesador3.getMemoriaCache().getCoordinateY(), procesador3.getMemoriaCache().getSizeX() / 3, procesador3.getMemoriaCache().getSizeY() / 2);
        procesador3.getMemoriaCache().setSocket(2, color(0, 198, 198), "");
        procesador3.getMemoriaCache().setupSocket(3, procesador3.getMemoriaCache().getCoordinateX() + 1, procesador3.getMemoriaCache().getCoordinateY() + procesador3.getMemoriaCache().getSizeY() / 2, procesador3.getMemoriaCache().getSizeX() / 3, procesador3.getMemoriaCache().getSizeY() / 2);
        procesador3.getMemoriaCache().setSocket(3, color(0, 198, 198), "I");
        procesador3.getMemoriaCache().setupSocket(4, procesador3.getMemoriaCache().getCoordinateX() + procesador3.getMemoriaCache().getSizeX() / 3 + 1, procesador3.getMemoriaCache().getCoordinateY() + procesador3.getMemoriaCache().getSizeY() / 2, procesador3.getMemoriaCache().getSizeX() / 3, procesador3.getMemoriaCache().getSizeY() / 2);
        procesador3.getMemoriaCache().setSocket(4, color(0, 221, 221), "");
        procesador3.getMemoriaCache().setupSocket(5, procesador3.getMemoriaCache().getCoordinateX() + 2 * procesador3.getMemoriaCache().getSizeX() / 3, procesador3.getMemoriaCache().getCoordinateY() + procesador3.getMemoriaCache().getSizeY() / 2, procesador3.getMemoriaCache().getSizeX() / 3, procesador3.getMemoriaCache().getSizeY() / 2);
        procesador3.getMemoriaCache().setSocket(5, color(100, 255, 255), "");
    }

    private void setupProcessors() {
        procesador1 = new Procesador(8);
        procesador1.setCoordinateX(200);
        procesador1.setCoordinateY(500);
        procesador1.setSizeX(100);
        procesador1.setSizeY(100);
        procesador1.setColor(color(0, 255, 255));

        procesador1.setupSocket(0, procesador1.getCoordinateX(), procesador1.getCoordinateY(), procesador1.getSizeX() / 2, procesador1.getSizeY() / 4);
        procesador1.setSocket(0, color(0, 255, 255), "R0x0");
        procesador1.setupSocket(1, procesador1.getCoordinateX() + procesador1.getSizeX() / 2, procesador1.getCoordinateY(), procesador1.getSizeX() / 2, procesador1.getSizeY() / 4);
        procesador1.setSocket(1, color(145, 255, 255), "W0x0");
        procesador1.setupSocket(2, procesador1.getCoordinateX(), procesador1.getCoordinateY() + procesador1.getSizeY() / 4, procesador1.getSizeX() / 2, procesador1.getSizeY() / 4);
        procesador1.setSocket(2, color(145, 255, 255), "R0x1");
        procesador1.setupSocket(3, procesador1.getCoordinateX() + procesador1.getSizeX() / 2, procesador1.getCoordinateY() + procesador1.getSizeY() / 4, procesador1.getSizeX() / 2, procesador1.getSizeY() / 4);
        procesador1.setSocket(3, color(0, 255, 255), "W0x1");
        procesador1.setupSocket(4, procesador1.getCoordinateX(), procesador1.getCoordinateY() + 2 * procesador1.getSizeY() / 4, procesador1.getSizeX() / 2, procesador1.getSizeY() / 4);
        procesador1.setSocket(4, color(0, 255, 255), "R0x2");
        procesador1.setupSocket(5, procesador1.getCoordinateX() + procesador1.getSizeX() / 2, procesador1.getCoordinateY() + 2 * procesador1.getSizeY() / 4, procesador1.getSizeX() / 2, procesador1.getSizeY() / 4);
        procesador1.setSocket(5, color(145, 255, 255), "W0x2");

        procesador1.setupSocket(6, procesador1.getCoordinateX(), procesador1.getCoordinateY() + 3 * procesador1.getSizeY() / 4, procesador1.getSizeX() / 2, procesador1.getSizeY() / 4);
        procesador1.setSocket(6, color(145, 255, 255), "R0x3");
        procesador1.setupSocket(7, procesador1.getCoordinateX() + procesador1.getSizeX() / 2, procesador1.getCoordinateY() + 3 * procesador1.getSizeY() / 4, procesador1.getSizeX() / 2, procesador1.getSizeY() / 4);
        procesador1.setSocket(7, color(0, 255, 255), "W0x3");

        bus.getTravel(0).setProcessor(1);
        bus.getTravel(0).setEllipseCoordinateX(procesador1.getCoordinateX() + procesador1.getSizeX() / 2);
        bus.getTravel(0).setEllipseCoordinateY(procesador1.getCoordinateY());
        //path one
        bus.getTravel(0).addPaths(bus.getTravel(0).getEllipseCoordinateX(), bus.getTravel(0).getEllipseCoordinateY(), bus.getTravel(0).getEllipseCoordinateX(), 450f);


        procesador2 = new Procesador(8);
        procesador2.setCoordinateX(400);
        procesador2.setCoordinateY(500);
        procesador2.setSizeX(100);
        procesador2.setSizeY(100);
        procesador2.setColor(color(255, 255, 255));

        procesador2.setupSocket(0, procesador2.getCoordinateX(), procesador2.getCoordinateY(), procesador2.getSizeX() / 2, procesador2.getSizeY() / 4);
        procesador2.setSocket(0, color(145, 255, 255), "R0x0");
        procesador2.setupSocket(1, procesador2.getCoordinateX() + procesador2.getSizeX() / 2, procesador2.getCoordinateY(), procesador2.getSizeX() / 2, procesador2.getSizeY() / 4);
        procesador2.setSocket(1, color(0, 255, 255), "W0x0");
        procesador2.setupSocket(2, procesador2.getCoordinateX(), procesador2.getCoordinateY() + procesador2.getSizeY() / 4, procesador2.getSizeX() / 2, procesador2.getSizeY() / 4);
        procesador2.setSocket(2, color(0, 255, 255), "R0x1");
        procesador2.setupSocket(3, procesador2.getCoordinateX() + procesador2.getSizeX() / 2, procesador2.getCoordinateY() + procesador2.getSizeY() / 4, procesador2.getSizeX() / 2, procesador2.getSizeY() / 3);
        procesador2.setSocket(3, color(145, 255, 255), "W0x1");
        procesador2.setupSocket(4, procesador2.getCoordinateX(), procesador2.getCoordinateY() + 2 * procesador2.getSizeY() / 4, procesador2.getSizeX() / 2, procesador2.getSizeY() / 4);
        procesador2.setSocket(4, color(145, 255, 255), "R0x2");
        procesador2.setupSocket(5, procesador2.getCoordinateX() + procesador2.getSizeX() / 2, procesador2.getCoordinateY() + 2 * procesador2.getSizeY() / 4, procesador2.getSizeX() / 2, procesador2.getSizeY() / 4);
        procesador2.setSocket(5, color(0, 255, 255), "W0x2");
        procesador2.setupSocket(6, procesador2.getCoordinateX(), procesador2.getCoordinateY() + 3 * procesador2.getSizeY() / 4, procesador2.getSizeX() / 2, procesador2.getSizeY() / 4);
        procesador2.setSocket(6, color(0, 255, 255), "R0x3");
        procesador2.setupSocket(7, procesador2.getCoordinateX() + procesador2.getSizeX() / 2, procesador2.getCoordinateY() + 3 * procesador2.getSizeY() / 4, procesador2.getSizeX() / 2, procesador2.getSizeY() / 4);
        procesador2.setSocket(7, color(145, 255, 255), "W0x3");

        bus.getTravel(1).setProcessor(2);
        bus.getTravel(1).setEllipseCoordinateX(procesador2.getCoordinateX() + procesador2.getSizeX() / 2);
        bus.getTravel(1).setEllipseCoordinateY(procesador2.getCoordinateY());
        bus.getTravel(1).addPaths(bus.getTravel(1).getEllipseCoordinateX(), bus.getTravel(1).getEllipseCoordinateY(), bus.getTravel(1).getEllipseCoordinateX(), 450f);



        procesador3 = new Procesador(8);
        procesador3.setCoordinateX(600);
        procesador3.setCoordinateY(500);
        procesador3.setSizeX(100);
        procesador3.setSizeY(100);
        procesador3.setColor(color(255, 255, 255));

        procesador3.setupSocket(0, procesador3.getCoordinateX(), procesador3.getCoordinateY(), procesador3.getSizeX() / 2, procesador3.getSizeY() / 4);
        procesador3.setSocket(0, color(0, 255, 255), "R0x0");
        procesador3.setupSocket(1, procesador3.getCoordinateX() + procesador3.getSizeX() / 2, procesador3.getCoordinateY(), procesador3.getSizeX() / 2, procesador3.getSizeY() / 4);
        procesador3.setSocket(1, color(145, 255, 255), "W0x0");
        procesador3.setupSocket(2, procesador3.getCoordinateX(), procesador3.getCoordinateY() + procesador3.getSizeY() / 4, procesador3.getSizeX() / 2, procesador3.getSizeY() / 4);
        procesador3.setSocket(2, color(145, 255, 255), "R0x1");
        procesador3.setupSocket(3, procesador3.getCoordinateX() + procesador3.getSizeX() / 2, procesador3.getCoordinateY() + procesador3.getSizeY() / 4, procesador3.getSizeX() / 2, procesador3.getSizeY() / 4);
        procesador3.setSocket(3, color(0, 255, 255), "W0x1");
        procesador3.setupSocket(4, procesador3.getCoordinateX(), procesador3.getCoordinateY() + 2 * procesador3.getSizeY() / 4, procesador3.getSizeX() / 2, procesador3.getSizeY() / 4);
        procesador3.setSocket(4, color(0, 255, 255), "R0x2");
        procesador3.setupSocket(5, procesador3.getCoordinateX() + procesador3.getSizeX() / 2, procesador3.getCoordinateY() + 2 * procesador3.getSizeY() / 4, procesador3.getSizeX() / 2, procesador3.getSizeY() / 4);
        procesador3.setSocket(5, color(145, 255, 255), "W0x2");
        procesador3.setupSocket(6, procesador3.getCoordinateX(), procesador3.getCoordinateY() + 3 * procesador3.getSizeY() / 4, procesador3.getSizeX() / 2, procesador3.getSizeY() / 4);
        procesador3.setSocket(6, color(145, 255, 255), "R0x3");
        procesador3.setupSocket(7, procesador3.getCoordinateX() + procesador3.getSizeX() / 2, procesador3.getCoordinateY() + 3 * procesador3.getSizeY() / 4, procesador3.getSizeX() / 2, procesador3.getSizeY() / 4);
        procesador3.setSocket(7, color(0, 255, 255), "W0x3");

        bus.getTravel(2).setProcessor(3);
        bus.getTravel(2).setEllipseCoordinateX(procesador3.getCoordinateX() + procesador3.getSizeX() / 2);
        bus.getTravel(2).setEllipseCoordinateY(procesador3.getCoordinateY());
        bus.getTravel(2).addPaths(bus.getTravel(2).getEllipseCoordinateX(), bus.getTravel(2).getEllipseCoordinateY(), bus.getTravel(2).getEllipseCoordinateX(), 450f);
    }

    private void checkStatus() {
        verifyReadMemoryP();
        verifyWayBack();

        if (bus.getTravel(0).getNroTravel() == 8) {
            procesador1.setActive(false);
            bus.getTravel(0).setTraveling(false);
            bus.getTravel(0).setNroTravel(0);
            pressedP1 = false;
        }
        if (bus.getTravel(1).getNroTravel() == 8) {
            procesador2.setActive(false);
            bus.getTravel(1).setTraveling(false);
            bus.getTravel(1).setNroTravel(0);
            pressedP2 = false;
        }
        if (bus.getTravel(2).getNroTravel() == 8) {
            procesador3.setActive(false);
            bus.getTravel(2).setTraveling(false);
            bus.getTravel(2).setNroTravel(0);
            pressedP3 = false;
        }


    }

    /**
     * verifica la lectura de la memoria principal.
     */
    private void verifyReadMemoryP() {
        if (bus.getTravel(0).getNroTravel() == 4 && (nroSocketP1 == 0 || nroSocketP1 == 1)) {
            memoriaPrincipal.setSocket(0, color(0, 230, 11), "0x0");//cambio
            memoriaPrincipal.setSocket(2, color(100, 255, 255), "0x1");
            memoriaPrincipal.setSocket(4, color(0, 221, 221), "0x2");
            memoriaPrincipal.setSocket(6, color(0, 198, 198), "0x3");
            colorEllipse1 = color(255, 147, 147);
        }
        if (bus.getTravel(0).getNroTravel() == 4 && (nroSocketP1 == 2 || nroSocketP1 == 3)) {
            memoriaPrincipal.setSocket(0, color(164, 255, 255), "0x0");
            memoriaPrincipal.setSocket(2, color(0, 230, 11), "0x1");//cambio
            memoriaPrincipal.setSocket(4, color(0, 221, 221), "0x2");
            memoriaPrincipal.setSocket(6, color(0, 198, 198), "0x3");
            colorEllipse1 = color(255, 147, 147);
        }
        if (bus.getTravel(0).getNroTravel() == 4 && (nroSocketP1 == 4 || nroSocketP1 == 5)) {
            memoriaPrincipal.setSocket(0, color(164, 255, 255), "0x0");
            memoriaPrincipal.setSocket(2, color(100, 255, 255), "0x1");
            memoriaPrincipal.setSocket(4, color(0, 230, 11), "0x2");//cambio
            memoriaPrincipal.setSocket(6, color(0, 198, 198), "0x3");
            colorEllipse1 = color(255, 147, 147);
        }
        if (bus.getTravel(0).getNroTravel() == 4 && (nroSocketP1 == 6 || nroSocketP1 == 7)) {
            memoriaPrincipal.setSocket(0, color(164, 255, 255), "0x0");
            memoriaPrincipal.setSocket(2, color(100, 255, 255), "0x1");
            memoriaPrincipal.setSocket(4, color(0, 221, 221), "0x2");
            memoriaPrincipal.setSocket(6, color(0, 230, 11), "0x3");//cambio
            colorEllipse1 = color(255, 147, 147);
        }

        if (bus.getTravel(1).getNroTravel() == 4 && (nroSocketP2 == 0 || nroSocketP2 == 1)) {
            memoriaPrincipal.setSocket(0, color(0, 230, 11), "0x0");
            memoriaPrincipal.setSocket(2, color(100, 255, 255), "0x1");
            memoriaPrincipal.setSocket(4, color(0, 221, 221), "0x2");
            memoriaPrincipal.setSocket(6, color(0, 198, 198), "0x3");
            colorEllipse2 = color(255, 147, 147);
        }
        if (bus.getTravel(1).getNroTravel() == 4 && (nroSocketP2 == 2 || nroSocketP2 == 3)) {
            memoriaPrincipal.setSocket(0, color(164, 255, 255), "0x0");
            memoriaPrincipal.setSocket(2, color(0, 230, 11), "0x1");
            memoriaPrincipal.setSocket(4, color(0, 221, 221), "0x2");
            memoriaPrincipal.setSocket(6, color(0, 198, 198), "0x3");
            colorEllipse2 = color(255, 147, 147);
        }

        if (bus.getTravel(1).getNroTravel() == 4 && (nroSocketP2 == 4 || nroSocketP2 == 5)) {
            memoriaPrincipal.setSocket(0, color(164, 255, 255), "0x0");
            memoriaPrincipal.setSocket(2, color(100, 255, 255), "0x1");
            memoriaPrincipal.setSocket(4, color(0, 230, 11), "0x2");
            memoriaPrincipal.setSocket(6, color(0, 198, 198), "0x3");
            colorEllipse2 = color(255, 147, 147);
        }
        if (bus.getTravel(1).getNroTravel() == 4 && (nroSocketP2 == 6 || nroSocketP2 == 7)) {
            memoriaPrincipal.setSocket(0, color(164, 255, 255), "0x0");
            memoriaPrincipal.setSocket(2, color(100, 255, 255), "0x1");
            memoriaPrincipal.setSocket(4, color(0, 221, 221), "0x2");
            memoriaPrincipal.setSocket(6, color(0, 230, 11), "0x3");
            colorEllipse2 = color(255, 147, 147);
        }


        if (bus.getTravel(2).getNroTravel() == 4 && (nroSocketP3 == 0 || nroSocketP3 == 1)) {
            memoriaPrincipal.setSocket(0, color(0, 230, 11), "0x0");
            memoriaPrincipal.setSocket(2, color(100, 255, 255), "0x1");
            memoriaPrincipal.setSocket(4, color(0, 221, 221), "0x2");
            memoriaPrincipal.setSocket(6, color(0, 198, 198), "0x3");
            colorEllipse3 = color(255, 147, 147);
        }
        if (bus.getTravel(2).getNroTravel() == 4 && (nroSocketP3 == 2 || nroSocketP3 == 3)) {
            memoriaPrincipal.setSocket(2, color(0, 230, 11), "0x1");
            memoriaPrincipal.setSocket(0, color(164, 255, 255), "0x0");
            memoriaPrincipal.setSocket(4, color(0, 221, 221), "0x2");
            memoriaPrincipal.setSocket(6, color(0, 198, 198), "0x3");
            colorEllipse3 = color(255, 147, 147);
        }
        if (bus.getTravel(2).getNroTravel() == 4 && (nroSocketP3 == 4 || nroSocketP3 == 5)) {
            memoriaPrincipal.setSocket(4, color(0, 230, 11), "0x2");
            memoriaPrincipal.setSocket(0, color(164, 255, 255), "0x0");
            memoriaPrincipal.setSocket(2, color(100, 255, 255), "0x1");
            memoriaPrincipal.setSocket(6, color(0, 198, 198), "0x3");
            colorEllipse3 = color(255, 147, 147);
        }
        if (bus.getTravel(2).getNroTravel() == 4 && (nroSocketP3 == 6 || nroSocketP3 == 7)) {
            memoriaPrincipal.setSocket(0, color(164, 255, 255), "0x0");
            memoriaPrincipal.setSocket(2, color(100, 255, 255), "0x1");
            memoriaPrincipal.setSocket(4, color(0, 221, 221), "0x2");
            memoriaPrincipal.setSocket(6, color(0, 230, 11), "0x3");
            colorEllipse3 = color(255, 147, 147);
        }


    }

    private void verifyWayBack() {

        validateP1();
        validateP2();
        validateP3();

    }

    private String upadateStatesMESI(int nroProcessor, int lineCache, int socket) {
        boolean p1, p2, p3;

        if (modifyP1) {
            modifyState(2, lineCache == 1 ? 0 : 3, procesador1.getMemoriaCache().getSocket(lineCache == 1 ? 1 : 4).getLabel(), procesador1.getMemoriaCache().getSocket(lineCache == 1 ? 0 : 3).getLabel());
            modifyState(3, lineCache == 1 ? 0 : 3, procesador1.getMemoriaCache().getSocket(lineCache == 1 ? 1 : 4).getLabel(), procesador1.getMemoriaCache().getSocket(lineCache == 1 ? 0 : 3).getLabel());
            modifyP1 = false;
            if (lineCache == 1) {
                if (procesador1.getMemoriaCache().getSocket(0).getLabel().equalsIgnoreCase("M")) {
                    return "M";
                }
            } else if (lineCache == 2) {
                if (procesador1.getMemoriaCache().getSocket(3).getLabel().equalsIgnoreCase("M")) {
                    return "M";
                }
            }
            return "E";

        } else if (modifyP2) {
            modifyState(1, lineCache == 1 ? 0 : 3, procesador2.getMemoriaCache().getSocket(lineCache == 1 ? 1 : 4).getLabel(), procesador2.getMemoriaCache().getSocket(lineCache == 1 ? 0 : 3).getLabel());
            modifyState(3, lineCache == 1 ? 0 : 3, procesador2.getMemoriaCache().getSocket(lineCache == 1 ? 1 : 4).getLabel(), procesador2.getMemoriaCache().getSocket(lineCache == 1 ? 0 : 3).getLabel());
            modifyP2 = false;
            if (lineCache == 1) {
                if (procesador2.getMemoriaCache().getSocket(0).getLabel().equalsIgnoreCase("M")) {
                    return "M";
                }
            } else if (lineCache == 2) {
                if (procesador2.getMemoriaCache().getSocket(3).getLabel().equalsIgnoreCase("M")) {
                    return "M";
                }
            }
            return "E";

        } else if (modifyP3) {
            modifyState(1, lineCache == 1 ? 0 : 3, procesador3.getMemoriaCache().getSocket(lineCache == 1 ? 1 : 4).getLabel(), procesador3.getMemoriaCache().getSocket(lineCache == 1 ? 0 : 3).getLabel());
            modifyState(2, lineCache == 1 ? 0 : 3, procesador3.getMemoriaCache().getSocket(lineCache == 1 ? 1 : 4).getLabel(), procesador3.getMemoriaCache().getSocket(lineCache == 1 ? 0 : 3).getLabel());
            modifyP3 = false;
            if (lineCache == 1) {
                if (procesador3.getMemoriaCache().getSocket(0).getLabel().equalsIgnoreCase("M")) {
                    return "M";
                }
            } else if (lineCache == 2) {
                if (procesador3.getMemoriaCache().getSocket(3).getLabel().equalsIgnoreCase("M")) {
                    return "M";
                }
            }
            return "E";
        } else if (nroProcessor == 1) {
            p2 = changeState(2, lineCache == 1 ? 0 : 3, procesador1.getMemoriaCache().getSocket(lineCache == 1 ? 1 : 4).getLabel(), procesador1.getMemoriaCache().getSocket(lineCache == 1 ? 0 : 3).getLabel());
            p3 = changeState(3, lineCache == 1 ? 0 : 3, procesador1.getMemoriaCache().getSocket(lineCache == 1 ? 1 : 4).getLabel(), procesador1.getMemoriaCache().getSocket(lineCache == 1 ? 0 : 3).getLabel());
            if (p2 || p3) {
                return "S";
            } else {
                return "E";
            }
        } else if (nroProcessor == 2) {
            p1 = changeState(1, lineCache == 1 ? 0 : 3, procesador2.getMemoriaCache().getSocket(lineCache == 1 ? 1 : 4).getLabel(), procesador2.getMemoriaCache().getSocket(lineCache == 1 ? 0 : 3).getLabel());
            p3 = changeState(3, lineCache == 1 ? 0 : 3, procesador2.getMemoriaCache().getSocket(lineCache == 1 ? 1 : 4).getLabel(), procesador2.getMemoriaCache().getSocket(lineCache == 1 ? 0 : 3).getLabel());
            if (p1 || p3) {
                return "S";
            } else {
                return "E";
            }
        } else if (nroProcessor == 3) {
            p1 = changeState(1, lineCache == 1 ? 0 : 3, procesador3.getMemoriaCache().getSocket(lineCache == 1 ? 1 : 4).getLabel(), procesador3.getMemoriaCache().getSocket(lineCache == 1 ? 0 : 3).getLabel());
            p2 = changeState(2, lineCache == 1 ? 0 : 3, procesador3.getMemoriaCache().getSocket(lineCache == 1 ? 1 : 4).getLabel(), procesador3.getMemoriaCache().getSocket(lineCache == 1 ? 0 : 3).getLabel());
            if (p1 || p2) {
                return "S";
            } else {
                return "E";
            }
        }
        return "a";

    }

    private boolean changeState(int processor, int cacheLine, String data, String estado) {
        boolean isModify = false;
        if (processor == 1) {
            if (procesador1.getMemoriaCache().getSocket(cacheLine + 1).getLabel().equalsIgnoreCase(data)) {
                if (estado.equalsIgnoreCase("E") || estado.equalsIgnoreCase("S") || estado.equalsIgnoreCase("M")) {
                    if (!procesador1.getMemoriaCache().getSocket(cacheLine).getLabel().equalsIgnoreCase("I")) {
                        procesador1.getMemoriaCache().getSocket(cacheLine).setLabel("S");
                    }
                    isModify = true;
                }
            }
        } else if (processor == 2) {
            if (procesador2.getMemoriaCache().getSocket(cacheLine + 1).getLabel().equalsIgnoreCase(data)) {
                if (estado.equalsIgnoreCase("E") || estado.equalsIgnoreCase("S") || estado.equalsIgnoreCase("M")) {
                    if (!procesador2.getMemoriaCache().getSocket(cacheLine).getLabel().equalsIgnoreCase("I")) {
                        procesador2.getMemoriaCache().getSocket(cacheLine).setLabel("S");
                    }
                    isModify = true;
                }
            }
        } else if (processor == 3) {
            if (procesador3.getMemoriaCache().getSocket(cacheLine + 1).getLabel().equalsIgnoreCase(data)) {
                if (estado.equalsIgnoreCase("E") || estado.equalsIgnoreCase("S") || estado.equalsIgnoreCase("M")) {
                    if (!procesador3.getMemoriaCache().getSocket(cacheLine).getLabel().equalsIgnoreCase("I")) {
                        procesador3.getMemoriaCache().getSocket(cacheLine).setLabel("S");
                    }
                    isModify = true;
                }
            }
        }
        return isModify;
    }

    private boolean modifyState(int processor, int cacheLine, String data, String estado) {
        boolean isModify = false;
        if (processor == 1) {
            if (procesador1.getMemoriaCache().getSocket(cacheLine + 1).getLabel().equalsIgnoreCase(data)) {
                procesador1.getMemoriaCache().getSocket(cacheLine).setLabel("I");
            }
        } else if (processor == 2) {
            if (procesador2.getMemoriaCache().getSocket(cacheLine + 1).getLabel().equalsIgnoreCase(data)) {
                procesador2.getMemoriaCache().getSocket(cacheLine).setLabel("I");
            }
        } else if (processor == 3) {
            if (procesador3.getMemoriaCache().getSocket(cacheLine + 1).getLabel().equalsIgnoreCase(data)) {
                procesador3.getMemoriaCache().getSocket(cacheLine).setLabel("I");
            }
        }
        return isModify;
    }

    private void validateP1() {
        // Procesador 1
        if (nroSocketP1 == 0 || nroSocketP1 == 2) {
            if (bus.getTravel(0).getNroTravel() == 1) {//el dato en cache
                if ((procesador1.getSocket(nroSocketP1).getLabel()).replace("R", "").equalsIgnoreCase(procesador1.getMemoriaCache().getSocket(1).getLabel()) && !procesador1.getMemoriaCache().getSocket(0).getLabel().equalsIgnoreCase("I")) {
                    procesador1.getMemoriaCache().getSocket(0).setColor(color(0, 190, 0));
                    procesador1.getMemoriaCache().getSocket(1).setColor(color(0, 190, 0) + 150);
                    procesador1.getMemoriaCache().getSocket(2).setColor(color(0, 190, 0) + 35);
                    bus.getTravel(0).setNroTravel(7);
                    colorEllipse1 = color(255, 147, 147);
                    procesador1.getMemoriaCache().getSocket(0).setLabel(upadateStatesMESI(1, 1, nroSocketP1));
                }
            } else if (bus.getTravel(0).getNroTravel() == 7) {
                procesador1.getMemoriaCache().getSocket(1).setLabel(memoriaPrincipal.getSocket(nroSocketP1 == 0 ? 0 : 2).getLabel());
                procesador1.getMemoriaCache().getSocket(0).setLabel(upadateStatesMESI(1, 1, nroSocketP1));
                procesador1.getMemoriaCache().getSocket(2).setLabel(memoriaPrincipal.getSocket(nroSocketP1 == 0 ? 1 : 3).getLabel());
            }

        } else if (nroSocketP1 == 4 || nroSocketP1 == 6) {
            if (bus.getTravel(0).getNroTravel() == 1) {//el dato en cache
                if ((procesador1.getSocket(nroSocketP1).getLabel()).replace("R", "").equalsIgnoreCase(procesador1.getMemoriaCache().getSocket(4).getLabel()) && !procesador1.getMemoriaCache().getSocket(3).getLabel().equalsIgnoreCase("I")) {
                    procesador1.getMemoriaCache().getSocket(3).setColor(color(0, 190, 0));
                    procesador1.getMemoriaCache().getSocket(4).setColor(color(0, 190, 0) + 150);
                    procesador1.getMemoriaCache().getSocket(5).setColor(color(0, 190, 0) + 35);
                    bus.getTravel(0).setNroTravel(7);
                    procesador1.getMemoriaCache().getSocket(3).setLabel(upadateStatesMESI(1, 2, nroSocketP1));
                    colorEllipse1 = color(255, 147, 147);
                }
            } else if (bus.getTravel(0).getNroTravel() == 7) {
                procesador1.getMemoriaCache().getSocket(4).setLabel(memoriaPrincipal.getSocket(nroSocketP1 == 4 ? 4 : 6).getLabel());
                procesador1.getMemoriaCache().getSocket(3).setLabel(upadateStatesMESI(1, 2, nroSocketP1));
                procesador1.getMemoriaCache().getSocket(5).setLabel(memoriaPrincipal.getSocket(nroSocketP1 == 4 ? 5 : 7).getLabel());
            }

        } else if (nroSocketP1 == 1 || nroSocketP1 == 3) {
            if (bus.getTravel(0).getNroTravel() == 1) {//el dato en cache
                if ((procesador1.getSocket(nroSocketP1).getLabel()).replace("W", "").equalsIgnoreCase(procesador1.getMemoriaCache().getSocket(1).getLabel()) && !procesador1.getMemoriaCache().getSocket(0).getLabel().equalsIgnoreCase("I")) {
                    procesador1.getMemoriaCache().getSocket(0).setColor(color(0, 190, 0));
                    procesador1.getMemoriaCache().getSocket(1).setColor(color(0, 190, 0) + 150);
                    procesador1.getMemoriaCache().getSocket(2).setColor(color(0, 190, 0) + 35);
                    bus.getTravel(0).setNroTravel(7);
                    colorEllipse1 = color(255, 147, 147);
                    modifyP1 = true;
                    procesador1.getMemoriaCache().getSocket(1).setLabel(memoriaPrincipal.getSocket(nroSocketP1 == 1 ? 0 : 2).getLabel());
                    procesador1.getMemoriaCache().getSocket(0).setLabel(upadateStatesMESI(1, 1, nroSocketP1));
                    procesador1.getMemoriaCache().getSocket(0).setLabel("M");
                    memoriaPrincipal.getSocket(nroSocketP1 == 1 ? 1 : 3).setLabel(String.valueOf((nroSocketP1 == 1 ? String.valueOf(dato1) : String.valueOf(dato2))));
                    procesador1.getMemoriaCache().getSocket(2).setLabel(memoriaPrincipal.getSocket(nroSocketP1 == 1 ? 1 : 3).getLabel());
                }
            } else if (bus.getTravel(0).getNroTravel() == 7) {
                modifyP1 = true;
                procesador1.getMemoriaCache().getSocket(1).setLabel(memoriaPrincipal.getSocket(nroSocketP1 == 1 ? 0 : 2).getLabel());
                procesador1.getMemoriaCache().getSocket(0).setLabel(upadateStatesMESI(1, 1, nroSocketP1));
                memoriaPrincipal.getSocket(nroSocketP1 == 1 ? 1 : 3).setLabel(String.valueOf((nroSocketP1 == 1 ? String.valueOf(dato1) : String.valueOf(dato2))));
                procesador1.getMemoriaCache().getSocket(2).setLabel(memoriaPrincipal.getSocket(nroSocketP1 == 1 ? 1 : 3).getLabel());
            }

        } else if (nroSocketP1 == 5 || nroSocketP1 == 7) {
            if (bus.getTravel(0).getNroTravel() == 1) {//el dato en cache
                if ((procesador1.getSocket(nroSocketP1).getLabel()).replace("W", "").equalsIgnoreCase(procesador1.getMemoriaCache().getSocket(4).getLabel()) && !procesador1.getMemoriaCache().getSocket(3).getLabel().equalsIgnoreCase("I")) {
                    procesador1.getMemoriaCache().getSocket(3).setColor(color(0, 190, 0));
                    procesador1.getMemoriaCache().getSocket(4).setColor(color(0, 190, 0) + 150);
                    procesador1.getMemoriaCache().getSocket(5).setColor(color(0, 190, 0) + 35);
                    bus.getTravel(0).setNroTravel(7);
                    colorEllipse1 = color(255, 147, 147);
                    modifyP1 = true;
                    procesador1.getMemoriaCache().getSocket(4).setLabel(memoriaPrincipal.getSocket(nroSocketP1 == 5 ? 4 : 6).getLabel());
                    procesador1.getMemoriaCache().getSocket(3).setLabel(upadateStatesMESI(1, 4, nroSocketP1));
                    procesador1.getMemoriaCache().getSocket(3).setLabel("M");
                    memoriaPrincipal.getSocket(nroSocketP1 == 5 ? 5 : 7).setLabel(String.valueOf((nroSocketP1 == 3 ? String.valueOf(dato3) : String.valueOf(dato4))));
                    procesador1.getMemoriaCache().getSocket(5).setLabel(memoriaPrincipal.getSocket(nroSocketP1 == 5 ? 5 : 7).getLabel());
                }
            } else if (bus.getTravel(0).getNroTravel() == 7) {
                modifyP1 = true;
                procesador1.getMemoriaCache().getSocket(4).setLabel(memoriaPrincipal.getSocket(nroSocketP1 == 5 ? 4 : 6).getLabel());
                procesador1.getMemoriaCache().getSocket(3).setLabel(upadateStatesMESI(1, 4, nroSocketP1));
                memoriaPrincipal.getSocket(nroSocketP1 == 5 ? 5 : 7).setLabel(String.valueOf((nroSocketP1 == 5 ? String.valueOf(dato3) : String.valueOf(dato4))));
                procesador1.getMemoriaCache().getSocket(5).setLabel(memoriaPrincipal.getSocket(nroSocketP1 == 5 ? 5 : 7).getLabel());
            }

        }
    }

    private void validateP2() {
        // Procesador 2
        if (nroSocketP2 == 0 || nroSocketP2 == 2) {
            if (bus.getTravel(1).getNroTravel() == 1) {//el dato en cache
                if ((procesador2.getSocket(nroSocketP2).getLabel()).replace("R", "").equalsIgnoreCase(procesador2.getMemoriaCache().getSocket(1).getLabel()) && !procesador2.getMemoriaCache().getSocket(0).getLabel().equalsIgnoreCase("I")) {
                    procesador2.getMemoriaCache().getSocket(0).setColor(color(0, 190, 0));
                    procesador2.getMemoriaCache().getSocket(1).setColor(color(0, 190, 0) + 150);
                    procesador2.getMemoriaCache().getSocket(2).setColor(color(0, 190, 0) + 35);
                    bus.getTravel(1).setNroTravel(7);
                    procesador2.getMemoriaCache().getSocket(0).setLabel(upadateStatesMESI(2, 1, nroSocketP2));
                    colorEllipse2 = color(255, 147, 147);
                }
            } else if (bus.getTravel(1).getNroTravel() == 7) {
                procesador2.getMemoriaCache().getSocket(1).setLabel(memoriaPrincipal.getSocket(nroSocketP2 == 0 ? 0 : 2).getLabel());
                procesador2.getMemoriaCache().getSocket(0).setLabel(upadateStatesMESI(2, 1, nroSocketP2));
                procesador2.getMemoriaCache().getSocket(2).setLabel(memoriaPrincipal.getSocket(nroSocketP2 == 0 ? 1 : 3).getLabel());
            }

        } else if (nroSocketP2 == 4 || nroSocketP2 == 6) {
            if (bus.getTravel(1).getNroTravel() == 1) {//el dato en cache
                if ((procesador2.getSocket(nroSocketP2).getLabel()).replace("R", "").equalsIgnoreCase(procesador2.getMemoriaCache().getSocket(4).getLabel()) && !procesador2.getMemoriaCache().getSocket(3).getLabel().equalsIgnoreCase("I")) {
                    procesador2.getMemoriaCache().getSocket(3).setColor(color(0, 190, 0));
                    procesador2.getMemoriaCache().getSocket(4).setColor(color(0, 190, 0) + 150);
                    procesador2.getMemoriaCache().getSocket(5).setColor(color(0, 190, 0) + 35);
                    bus.getTravel(1).setNroTravel(7);
                    procesador2.getMemoriaCache().getSocket(3).setLabel(upadateStatesMESI(2, 4, nroSocketP2));
                    colorEllipse2 = color(255, 147, 147);
                }
            } else if (bus.getTravel(1).getNroTravel() == 7) {
                procesador2.getMemoriaCache().getSocket(4).setLabel(memoriaPrincipal.getSocket(nroSocketP2 == 4 ? 4 : 6).getLabel());
                procesador2.getMemoriaCache().getSocket(3).setLabel(upadateStatesMESI(2, 4, nroSocketP2));
                procesador2.getMemoriaCache().getSocket(5).setLabel(memoriaPrincipal.getSocket(nroSocketP2 == 4 ? 5 : 7).getLabel());
            }

        } else if (nroSocketP2 == 1 || nroSocketP2 == 3) {
            if (bus.getTravel(1).getNroTravel() == 1) {//el dato en cache
                if ((procesador2.getSocket(nroSocketP2).getLabel()).replace("W", "").equalsIgnoreCase(procesador2.getMemoriaCache().getSocket(1).getLabel()) && !procesador2.getMemoriaCache().getSocket(0).getLabel().equalsIgnoreCase("I")) {
                    procesador2.getMemoriaCache().getSocket(0).setColor(color(0, 190, 0));
                    procesador2.getMemoriaCache().getSocket(1).setColor(color(0, 190, 0) + 150);
                    procesador2.getMemoriaCache().getSocket(2).setColor(color(0, 190, 0) + 35);
                    bus.getTravel(1).setNroTravel(7);
                    colorEllipse2 = color(255, 147, 147);
                    modifyP2 = true;
                    procesador2.getMemoriaCache().getSocket(1).setLabel(memoriaPrincipal.getSocket(nroSocketP2 == 1 ? 0 : 2).getLabel());
                    procesador2.getMemoriaCache().getSocket(0).setLabel(upadateStatesMESI(2, 1, nroSocketP2));
                    procesador2.getMemoriaCache().getSocket(0).setLabel("M");
                    memoriaPrincipal.getSocket(nroSocketP2 == 1 ? 1 : 3).setLabel(String.valueOf((nroSocketP2 == 1 ? String.valueOf(dato1) : String.valueOf(dato2))));
                    procesador2.getMemoriaCache().getSocket(2).setLabel(memoriaPrincipal.getSocket(nroSocketP2 == 1 ? 1 : 3).getLabel());
                }
            } else if (bus.getTravel(1).getNroTravel() == 7) {
                modifyP2 = true;
                procesador2.getMemoriaCache().getSocket(1).setLabel(memoriaPrincipal.getSocket(nroSocketP2 == 1 ? 0 : 2).getLabel());
                procesador2.getMemoriaCache().getSocket(0).setLabel(upadateStatesMESI(2, 1, nroSocketP2));
                memoriaPrincipal.getSocket(nroSocketP2 == 1 ? 1 : 3).setLabel(String.valueOf((nroSocketP2 == 1 ? String.valueOf(dato1) : String.valueOf(dato2))));
                procesador2.getMemoriaCache().getSocket(2).setLabel(memoriaPrincipal.getSocket(nroSocketP2 == 1 ? 1 : 3).getLabel());
            }

        } else if (nroSocketP2 == 5 || nroSocketP2 == 7) {
            if (bus.getTravel(1).getNroTravel() == 1) {//el dato en cache
                if ((procesador2.getSocket(nroSocketP2).getLabel()).replace("W", "").equalsIgnoreCase(procesador2.getMemoriaCache().getSocket(4).getLabel()) && !procesador2.getMemoriaCache().getSocket(3).getLabel().equalsIgnoreCase("I")) {
                    procesador2.getMemoriaCache().getSocket(3).setColor(color(0, 190, 0));
                    procesador2.getMemoriaCache().getSocket(4).setColor(color(0, 190, 0) + 150);
                    procesador2.getMemoriaCache().getSocket(5).setColor(color(0, 190, 0) + 35);
                    bus.getTravel(0).setNroTravel(7);
                    colorEllipse2 = color(255, 147, 147);
                    modifyP2 = true;
                    procesador2.getMemoriaCache().getSocket(4).setLabel(memoriaPrincipal.getSocket(nroSocketP2 == 5 ? 4 : 6).getLabel());
                    procesador2.getMemoriaCache().getSocket(3).setLabel(upadateStatesMESI(2, 2, nroSocketP2));
                    procesador2.getMemoriaCache().getSocket(3).setLabel("M");
                    memoriaPrincipal.getSocket(nroSocketP2 == 5 ? 5 : 7).setLabel(String.valueOf((nroSocketP2 == 3 ? String.valueOf(dato3) : String.valueOf(dato4))));
                    procesador2.getMemoriaCache().getSocket(5).setLabel(memoriaPrincipal.getSocket(nroSocketP2 == 5 ? 5 : 7).getLabel());
                }
            } else if (bus.getTravel(1).getNroTravel() == 7) {
                modifyP2 = true;
                procesador2.getMemoriaCache().getSocket(4).setLabel(memoriaPrincipal.getSocket(nroSocketP2 == 5 ? 4 : 6).getLabel());
                procesador2.getMemoriaCache().getSocket(3).setLabel(upadateStatesMESI(2, 2, nroSocketP2));
                memoriaPrincipal.getSocket(nroSocketP2 == 5 ? 5 : 7).setLabel(String.valueOf((nroSocketP2 == 5 ? String.valueOf(dato3) : String.valueOf(dato4))));
                procesador2.getMemoriaCache().getSocket(5).setLabel(memoriaPrincipal.getSocket(nroSocketP2 == 5 ? 5 : 7).getLabel());
            }

        }
    }

    private void validateP3() {

        // Procesador 3
        if (nroSocketP3 == 0 || nroSocketP3 == 2) {
            if (bus.getTravel(2).getNroTravel() == 1) {//el dato en cache
                if ((procesador3.getSocket(nroSocketP3).getLabel()).replace("R", "").equalsIgnoreCase(procesador3.getMemoriaCache().getSocket(1).getLabel()) && !procesador3.getMemoriaCache().getSocket(0).getLabel().equalsIgnoreCase("I")) {
                    procesador3.getMemoriaCache().getSocket(0).setColor(color(0, 190, 0));
                    procesador3.getMemoriaCache().getSocket(1).setColor(color(0, 190, 0) + 150);
                    procesador3.getMemoriaCache().getSocket(2).setColor(color(0, 190, 0) + 35);
                    bus.getTravel(2).setNroTravel(7);
                    procesador3.getMemoriaCache().getSocket(0).setLabel(upadateStatesMESI(3, 1, nroSocketP3));
                    colorEllipse3 = color(255, 147, 147);
                }
            } else if (bus.getTravel(2).getNroTravel() == 7) {
                procesador3.getMemoriaCache().getSocket(1).setLabel(memoriaPrincipal.getSocket(nroSocketP3 == 0 ? 0 : 2).getLabel());
                procesador3.getMemoriaCache().getSocket(0).setLabel(upadateStatesMESI(3, 1, nroSocketP3));
                procesador3.getMemoriaCache().getSocket(2).setLabel(memoriaPrincipal.getSocket(nroSocketP3 == 0 ? 1 : 3).getLabel());
            }

        } else if (nroSocketP3 == 4 || nroSocketP3 == 6) {
            if (bus.getTravel(2).getNroTravel() == 1) {//el dato en cache
                if ((procesador3.getSocket(nroSocketP3).getLabel()).replace("R", "").equalsIgnoreCase(procesador3.getMemoriaCache().getSocket(4).getLabel()) && !procesador3.getMemoriaCache().getSocket(3).getLabel().equalsIgnoreCase("I")) {
                    procesador3.getMemoriaCache().getSocket(3).setColor(color(0, 190, 0));
                    procesador3.getMemoriaCache().getSocket(4).setColor(color(0, 190, 0) + 150);
                    procesador3.getMemoriaCache().getSocket(5).setColor(color(0, 190, 0) + 35);
                    bus.getTravel(2).setNroTravel(7);
                    procesador3.getMemoriaCache().getSocket(3).setLabel(upadateStatesMESI(3, 4, nroSocketP3));
                    colorEllipse3 = color(255, 147, 147);
                }
            } else if (bus.getTravel(2).getNroTravel() == 7) {
                procesador3.getMemoriaCache().getSocket(4).setLabel(memoriaPrincipal.getSocket(nroSocketP3 == 4 ? 4 : 6).getLabel());
                procesador3.getMemoriaCache().getSocket(3).setLabel(upadateStatesMESI(3, 4, nroSocketP3));
                procesador3.getMemoriaCache().getSocket(5).setLabel(memoriaPrincipal.getSocket(nroSocketP3 == 4 ? 5 : 7).getLabel());
            }

        } else if (nroSocketP3 == 1 || nroSocketP3 == 3) {
            if (bus.getTravel(2).getNroTravel() == 1) {//el dato en cache
                if ((procesador3.getSocket(nroSocketP3).getLabel()).replace("W", "").equalsIgnoreCase(procesador3.getMemoriaCache().getSocket(1).getLabel()) && !procesador3.getMemoriaCache().getSocket(0).getLabel().equalsIgnoreCase("I")) {
                    procesador3.getMemoriaCache().getSocket(0).setColor(color(0, 190, 0));
                    procesador3.getMemoriaCache().getSocket(1).setColor(color(0, 190, 0) + 150);
                    procesador3.getMemoriaCache().getSocket(2).setColor(color(0, 190, 0) + 35);
                    bus.getTravel(2).setNroTravel(7);
                    colorEllipse3 = color(255, 147, 147);
                    modifyP3 = true;
                    procesador3.getMemoriaCache().getSocket(1).setLabel(memoriaPrincipal.getSocket(nroSocketP3 == 1 ? 0 : 2).getLabel());
                    procesador3.getMemoriaCache().getSocket(0).setLabel(upadateStatesMESI(3, 1, nroSocketP3));
                    procesador3.getMemoriaCache().getSocket(0).setLabel("M");
                    memoriaPrincipal.getSocket(nroSocketP3 == 1 ? 1 : 3).setLabel(String.valueOf((nroSocketP3 == 1 ? String.valueOf(dato1) : String.valueOf(dato2))));
                    procesador3.getMemoriaCache().getSocket(2).setLabel(memoriaPrincipal.getSocket(nroSocketP3 == 1 ? 1 : 3).getLabel());

                }
            } else if (bus.getTravel(2).getNroTravel() == 7) {
                modifyP3 = true;
                procesador3.getMemoriaCache().getSocket(1).setLabel(memoriaPrincipal.getSocket(nroSocketP3 == 1 ? 0 : 2).getLabel());
                procesador3.getMemoriaCache().getSocket(0).setLabel(upadateStatesMESI(3, 1, nroSocketP3));
                memoriaPrincipal.getSocket(nroSocketP3 == 1 ? 1 : 3).setLabel(String.valueOf((nroSocketP3 == 1 ? String.valueOf(dato1) : String.valueOf(dato2))));
                procesador3.getMemoriaCache().getSocket(2).setLabel(memoriaPrincipal.getSocket(nroSocketP3 == 1 ? 1 : 3).getLabel());
            }

        } else if (nroSocketP3 == 5 || nroSocketP3 == 7) {
            if (bus.getTravel(2).getNroTravel() == 1) {//el dato en cache
                if ((procesador3.getSocket(nroSocketP3).getLabel()).replace("W", "").equalsIgnoreCase(procesador3.getMemoriaCache().getSocket(4).getLabel()) && !procesador3.getMemoriaCache().getSocket(3).getLabel().equalsIgnoreCase("I")) {
                    procesador3.getMemoriaCache().getSocket(3).setColor(color(0, 190, 0));
                    procesador3.getMemoriaCache().getSocket(4).setColor(color(0, 190, 0) + 150);
                    procesador3.getMemoriaCache().getSocket(5).setColor(color(0, 190, 0) + 35);
                    bus.getTravel(2).setNroTravel(7);
                    colorEllipse3 = color(255, 147, 147);
                    modifyP3 = true;
                    procesador3.getMemoriaCache().getSocket(4).setLabel(memoriaPrincipal.getSocket(nroSocketP3 == 5 ? 4 : 6).getLabel());
                    procesador3.getMemoriaCache().getSocket(3).setLabel(upadateStatesMESI(3, 2, nroSocketP3));
                    procesador3.getMemoriaCache().getSocket(3).setLabel("M");
                    memoriaPrincipal.getSocket(nroSocketP3 == 5 ? 5 : 7).setLabel(String.valueOf((nroSocketP3 == 3 ? String.valueOf(dato3) : String.valueOf(dato4))));
                    procesador3.getMemoriaCache().getSocket(5).setLabel(memoriaPrincipal.getSocket(nroSocketP3 == 5 ? 5 : 7).getLabel());
                }
            } else if (bus.getTravel(2).getNroTravel() == 7) {
                modifyP3 = true;
                procesador3.getMemoriaCache().getSocket(4).setLabel(memoriaPrincipal.getSocket(nroSocketP3 == 5 ? 4 : 6).getLabel());
                procesador3.getMemoriaCache().getSocket(3).setLabel(upadateStatesMESI(3, 2, nroSocketP3));
                memoriaPrincipal.getSocket(nroSocketP3 == 5 ? 5 : 7).setLabel(String.valueOf((nroSocketP3 == 5 ? String.valueOf(dato3) : String.valueOf(dato4))));
                procesador3.getMemoriaCache().getSocket(5).setLabel(memoriaPrincipal.getSocket(nroSocketP3 == 5 ? 5 : 7).getLabel());
            }

        }

    }
}
