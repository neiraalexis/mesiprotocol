package hadware;
/**
 *
 * @author aneira
 */
// <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
// #[regen=yes,id=DCE.B9641DB5-6472-3063-02B3-367F56113F0D]
// </editor-fold> 
public class Procesador extends SetupModel {
/**
 *
 */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.4172DF7C-A1AB-76DB-DF49-3234BC306329]
    // </editor-fold> 
    private Bus Bus;
    private boolean active;
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.556FD232-8A0F-0622-3833-DC1ACD32B4F0]
    // </editor-fold> 
    private MemoriaCache memoriaCache;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.FD1065FD-C5AA-83DD-8C86-BF4E694B4D03]
    // </editor-fold> 
    public Procesador(int nroSockets) {
        super(nroSockets);
        memoriaCache = new MemoriaCache(6);
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.A9731109-B079-20B4-7FE0-A30367EF87F9]
    // </editor-fold> 
    public Bus getBus() {
        return Bus;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.A74BBFFC-1011-6411-CFB2-D6EA81EFC399]
    // </editor-fold> 
    public void setBus(Bus val) {
        this.Bus = val;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.AA3BF1CD-5C10-04E8-E986-86668348B772]
    // </editor-fold> 
    public MemoriaCache getMemoriaCache() {
        return memoriaCache;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.8E3C32E5-FA55-7CA8-12FA-B7CF84167F6D]
    // </editor-fold> 
    public void setMemoriaCache(MemoriaCache val) {
        this.memoriaCache = val;

    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public int buttonPressed(int x, int y) {
        return overRect(x, y);

    }

    private int overRect(int x, int y) {
        //read first data
        if (x >= getSocket(0).getCoordinateX() && y >= getSocket(0).getCoordinateY() && x <= getSocket(0).getCoordinateX() + getSocket(0).getSizeX() && y <= getSocket(0).getCoordinateY() + getSocket(0).getSizeY()) {
            this.active = true;
            return 0;
        } //write first data
        else if (x >= getSocket(1).getCoordinateX() && y >= getSocket(1).getCoordinateY() && x <= getSocket(1).getCoordinateX() + getSocket(1).getSizeX() && y <= getSocket(1).getCoordinateY() + getSocket(1).getSizeY()) {
            this.active = true;
            return 1;
        } //read second data
        else if (x >= getSocket(2).getCoordinateX() && y >= getSocket(2).getCoordinateY() && x <= getSocket(2).getCoordinateX() + getSocket(2).getSizeX() && y <= getSocket(2).getCoordinateY() + getSocket(2).getSizeY()) {
            this.active = true;
            return 2;
        } else//write second data
        if (x >= getSocket(3).getCoordinateX() && y >= getSocket(3).getCoordinateY() && x <= getSocket(3).getCoordinateX() + getSocket(3).getSizeX() && y <= getSocket(3).getCoordinateY() + getSocket(3).getSizeY()) {
            this.active = true;
            return 3;
        } //read third data
        else if (x >= getSocket(4).getCoordinateX() && y >= getSocket(4).getCoordinateY() && x <= getSocket(4).getCoordinateX() + getSocket(4).getSizeX() && y <= getSocket(4).getCoordinateY() + getSocket(4).getSizeY()) {
            this.active = true;
            return 4;
        }//write third data
        else if (x >= getSocket(5).getCoordinateX() && y >= getSocket(5).getCoordinateY() && x <= getSocket(5).getCoordinateX() + getSocket(5).getSizeX() && y <= getSocket(5).getCoordinateY() + getSocket(5).getSizeY()) {
            this.active = true;
            return 5;
        } else if (x >= getSocket(6).getCoordinateX() && y >= getSocket(6).getCoordinateY() && x <= getSocket(6).getCoordinateX() + getSocket(6).getSizeX() && y <= getSocket(6).getCoordinateY() + getSocket(6).getSizeY()) {
            this.active = true;
            return 6;
        } else if (x >= getSocket(7).getCoordinateX() && y >= getSocket(7).getCoordinateY() && x <= getSocket(7).getCoordinateX() + getSocket(7).getSizeX() && y <= getSocket(7).getCoordinateY() + getSocket(7).getSizeY()) {
            this.active = true;
            return 7;
        } else {
             this.active = false;
            return -9;
        }
    }
}
