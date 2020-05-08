package hadware;

/**
 * 
 * @author aneira
 */
// <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
// #[regen=yes,id=DCE.6744EBAB-2CCB-61CE-FDD8-665EA635C775]
// </editor-fold> 
public class Bus {

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.83F2C942-FB0E-3A37-EAE3-F289EA150DD8]
    // </editor-fold> 
    private String tipo;
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.4559C8EF-4933-D1D3-512C-C385E43AAB35]
    // </editor-fold> 
    private int anchoBus;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.66E20445-3214-2C9C-7B8A-CBBF4268B612]
    // </editor-fold>
    private Travel[] travel;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.CCCC3F42-8C9F-98E0-FC79-2E0C0E0ECC37]
    // </editor-fold> 
    public Bus() {
        travel = new Travel[3];
        for (int i = 0; i < 3; i++) {
            travel[i] = new Travel();
            travel[i].setPercentageAdvance(0f);
            travel[i].setTraveling(false);
        }
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.956D612A-1A58-8BF9-B97E-C90FA1FF7C3F]
    // </editor-fold> 
    public int getAnchoBus() {
        return anchoBus;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.84414B37-1909-1875-4125-7FD57A4EBA7C]
    // </editor-fold> 
    public void setAnchoBus(int val) {
        this.anchoBus = val;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.9AEAAE73-3FC0-6372-9FF4-FBA6FF3EA124]
    // </editor-fold> 
    public String getTipo() {
        return tipo;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.84B1F328-CCFF-746A-8BDC-EF5AA4332625]
    // </editor-fold> 
    public void setTipo(String val) {
        this.tipo = val;
    }

    public Travel getTravel(int position) {
        return travel[position];
    }
}

