/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hadware;

import java.util.ArrayList;
import sketchMesi.Socket;

/**
 *
 * @author Alexis Neira  Garcia
 */
public class SetupModel {

    protected int coordinateX;
    protected int coordinateY;
    protected int sizeX;
    protected int sizeY;
    protected int color;
    protected int colorActive;
    protected int nroSockets;
    protected ArrayList<Socket> sockets;
    protected boolean run;

    public SetupModel() {
    }

    public SetupModel(int nroSockets) {
        this.nroSockets = nroSockets;
        this.sockets = new ArrayList<Socket>(nroSockets);
        Socket socket = null;
        for (int i = 0; i < nroSockets; i++) {
            socket = new Socket();
            sockets.add(socket);
        }
    }

    public int getCoordinateY() {
        return coordinateY;
    }

    public void setCoordinateY(int coordinateY) {
        this.coordinateY = coordinateY;
    }

    public int getCoordinateX() {
        return coordinateX;
    }

    public void setCoordinateX(int coordinateX) {
        this.coordinateX = coordinateX;
    }

    public int getSizeX() {
        return sizeX;
    }

    public void setSizeX(int sizeX) {
        this.sizeX = sizeX;
    }

    public int getSizeY() {
        return sizeY;
    }

    public void setSizeY(int sizeY) {
        this.sizeY = sizeY;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public void setupSocket(int nroSocket, int coordinateX, int coordinateY, int sizeX, int sizeY) {
        sockets.get(nroSocket).setup(coordinateX, coordinateY, sizeX, sizeY);
    }

    public void setSocket(int nroSocket, int color, String label) {
        sockets.get(nroSocket).setColor(color);
        sockets.get(nroSocket).setLabel(label);
    }

    public Socket getSocket(int position) {
        return sockets.get(position);
    }

    public boolean isRun() {
        return run;
    }

    public void setRun(boolean run) {
        this.run = run;
    }

    public int getColorActive() {
        return colorActive;
    }

    public void setColorActive(int colorActive) {
        this.colorActive = colorActive;
    }
}
