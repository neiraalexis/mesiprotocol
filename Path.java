/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hadware;

/**
 *
 * @author aneira
 */
public class Path {

    private float beginX;
    private float beginY;
    private float endX;
    private float endY;

    public Path(float beginX, float beginY, float endX, float endY) {
        this.beginX = beginX;
        this.beginY = beginY;
        this.endX = endX;
        this.endY = endY;
    }


    
    public float getBeginX() {
        return beginX;
    }

    public void setBeginX(float beginX) {
        this.beginX = beginX;
    }

    public float getBeginY() {
        return beginY;
    }

    public void setBeginY(float beginY) {
        this.beginY = beginY;
    }

    public float getEndX() {
        return endX;
    }

    public void setEndX(float endX) {
        this.endX = endX;
    }

    public float getEndY() {
        return endY;
    }

    public void setEndY(float endY) {
        this.endY = endY;
    }

    
}