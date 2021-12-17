package datatypes;

public class DtPaquete {

	private String nombre;
	private String descripcion;
	private DtVigencia vigencia;
	private float descuento;


    public DtPaquete() {}

    public DtPaquete(String nombre, String descripcion, DtVigencia vigencia, float descuento) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.vigencia = vigencia;
        this.descuento = descuento;
    }

    /**
     * @return String return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @return String return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @return DtVigencia return the vigencia
     */
    public DtVigencia getVigencia() {
        return vigencia;
    }

    /**
     * @return float return the descuento
     */
    public float getDescuento() {
        return descuento;
    }

}
