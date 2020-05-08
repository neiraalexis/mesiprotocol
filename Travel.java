/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hadware;

import java.util.ArrayList;

/**
 *
 * @author aneira
 */
public class Travel {

    private float ellipseCoordinateX;
    private float ellipseCoordinateY;
    private boolean traveling;
    private float percentageAdvance;
    private float distanceX;
    private float distanceY;
    private float advanceX;
    private float advanceY;
    private ArrayList<Path> paths;
    private int nroTravel;
    private int processor;

    public Travel() {
        paths = new ArrayList<Path>(3);
        nroTravel = 0;
    }

    public float getPercentageAdvance() {
        return percentageAdvance;
    }

    public void setPercentageAdvance(float percentageAdvance) {
        this.percentageAdvance = percentageAdvance;
    }

    public float getEllipseCoordinateX() {
        return ellipseCoordinateX;
    }

    public void setEllipseCoordinateX(float ellipseCoordinateX) {
        this.ellipseCoordinateX = ellipseCoordinateX;
    }

    public float getEllipseCoordinateY() {
        return ellipseCoordinateY;
    }

    public void setEllipseCoordinateY(float ellipseCoordinateY) {
        this.ellipseCoordinateY = ellipseCoordinateY;
    }

    public boolean isTraveling() {
        return traveling;
    }

    public void setTraveling(boolean traveling) {
        this.traveling = traveling;
    }

    public void incrementAdvance(float increment, int processor) {
        this.percentageAdvance += increment;
        if (percentageAdvance >= 1f) {
            nroTravel++;
            percentageAdvance = 0f;
        } else {

            if (nroTravel == 3 && processor == 3) {
                distanceX = (paths.get(nroTravel).getBeginX() - paths.get(nroTravel).getEndX()) * -1f;
                distanceY = paths.get(nroTravel).getEndY() - paths.get(nroTravel).getBeginY();
            } else {
                distanceX = paths.get(nroTravel).getEndX() - paths.get(nroTravel).getBeginX();
                distanceY = paths.get(nroTravel).getEndY() - paths.get(nroTravel).getBeginY();
            }
            advanceX = percentageAdvance * distanceX;
            advanceY = percentageAdvance * distanceY;
            ellipseCoordinateX = advanceX + paths.get(nroTravel).getBeginX();
            ellipseCoordinateY = advanceY + paths.get(nroTravel).getBeginY();
        }

    }

    public float getDistanceX() {
        return distanceX;
    }

    public void setDistanceX(float distanceX) {
        this.distanceX = distanceX;
    }

    public float getDistanceY() {
        return distanceY;
    }

    public void setDistanceY(float distanceY) {
        this.distanceY = distanceY;
    }

    public float getAdvanceX() {
        return advanceX;
    }

    public void setAdvanceX(float advanceX) {
        this.advanceX = advanceX;
    }

    public float getAdvanceY() {
        return advanceY;
    }

    public void setAdvanceY(float advanceY) {
        this.advanceY = advanceY;
    }

    public void addPaths(float beginX, float beginY, float endX, float endY) {
        paths.add(new Path(beginX, beginY, endX, endY));
        paths.add(new Path(beginX, ellipseCoordinateY - 95, endX, 300f));
        if (processor == 1) {
            paths.add(new Path(beginX, paths.get(1).getEndY(), 450, 300f));
        } else if (processor == 2) {
            paths.add(new Path(beginX, paths.get(1).getEndY(), endX, 300f));
        } else if (processor == 3) {
            paths.add(new Path(beginX, paths.get(1).getEndY(), 450, 300f));
        }
        paths.add(new Path(450, paths.get(1).getEndY(), 450, 230f));

        //back
        paths.add(new Path(450, 230f, 450, paths.get(1).getEndY()));
        if (processor == 1) {
            paths.add(new Path(450, 300f, beginX, paths.get(1).getEndY()));
        } else if (processor == 2) {
            paths.add(new Path(endX, 300f, beginX, paths.get(1).getEndY()));
        } else if (processor == 3) {
            paths.add(new Path(450, 300f, beginX, paths.get(1).getEndY()));
        }
        paths.add(new Path(endX, 300f, beginX, ellipseCoordinateY - 95));
        paths.add(new Path(endX, endY, beginX, beginY));



    }

    public int getNroTravel() {
        return nroTravel;
    }

    public void setNroTravel(int nroTravel) {
        this.nroTravel = nroTravel;
    }

    public int getProcessor() {
        return processor;
    }

    public void setProcessor(int processor) {
        this.processor = processor;
    }
}
