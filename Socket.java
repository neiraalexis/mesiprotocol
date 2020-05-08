/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sketchMesi;

import hadware.SetupModel;

/**
 *
 * @author aneira
 */
public class Socket extends SetupModel {

    String label;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public void setup(int coordinateX, int coordinateY, int sizeX,int sizeY) {
        this.coordinateX = coordinateX;
        this.coordinateY = coordinateY;
        this.sizeX = sizeX;
        this.sizeY=sizeY;
    }
}
