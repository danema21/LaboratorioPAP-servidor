package datatypes;

import java.util.List;

public class DtDataEspectaculo {
    DtEspectaculo espectaculo;
    List<DtPaquete> paquetes;

    public DtDataEspectaculo() {}

    public DtDataEspectaculo(DtEspectaculo espectaculo, List<DtPaquete> paquetes) {
          this.espectaculo = espectaculo;
          this.paquetes = paquetes;
    }

    public DtEspectaculo getEspectaculo() {
          return espectaculo;
    }

    public List<DtPaquete> getPaquetes() {
          return paquetes;
    }
}
