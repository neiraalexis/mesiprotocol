package hadware;


// <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
// #[regen=yes,id=DCE.E3DFE149-236A-327C-9FDD-D3439D59C789]
// </editor-fold> 
public class Memoria extends SetupModel {

    protected String dato;
    protected String direccion;
    protected int contador;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.7E5072D2-A1A4-B3C1-3A95-3703E23FE637]
    // </editor-fold> 
    public Memoria(int nroSockets) {
        super(nroSockets);

    }

    public String getDato() {
        return dato;
    }

    public void setDato(String dato) {
        this.dato = dato;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getContador() {
        return contador;
    }

    public void setContador(int contador) {
        this.contador = contador;
    }
}

