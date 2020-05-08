package hadware;


// <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
// #[regen=yes,id=DCE.3C7FD0CA-3DCC-FFA6-0608-78A2D184AAFB]
// </editor-fold> 
public class MemoriaCache extends Memoria {

    private String estado;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.3FE6FCFB-8415-8A78-5F6D-CF7E5EF82BE5]
    // </editor-fold> 
    public MemoriaCache(int nroSockets) {
        super(nroSockets);

    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getEstado() {
        return estado;
    }
}
