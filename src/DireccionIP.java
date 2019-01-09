public class DireccionIP {
    private int[] octetos = new int[4];
    private char clase;
    private DireccionIP mascaraRed;
    private DireccionIP idRed;

    private static final DireccionIP MASCARAC = new DireccionIP("255.255.255.0",true);
    private static final DireccionIP MASCARAB = new DireccionIP("255.255.0.0",true);
    private static final DireccionIP MASCARAA = new DireccionIP("255.0.0.0",true);

    DireccionIP(String direccion) {
        this.octetos=obtenerOctetos(direccion);
        this.clase = obtenerClase(this);
        this.determinarMascaraRed();
        this.determinarIdRed();
    }

    DireccionIP(int primeroct, int segundoct, int terceroct, int cuartoct) {
        this.octetos[0] = primeroct;
        this.octetos[1] = segundoct;
        this.octetos[2] = terceroct;
        this.octetos[3] = cuartoct;
        this.clase = obtenerClase(this);
        this.determinarMascaraRed();
        this.determinarIdRed();
    }

    DireccionIP(int[] direccion) {
        this.octetos = direccion;
        this.clase = obtenerClase(this);
        this.determinarMascaraRed();
        this.determinarIdRed();
    }

    DireccionIP(String direccion, boolean casoEspecial){
        this.octetos=obtenerOctetos(direccion);
    }

    public void determinarIdRed(){
        this.idRed = new DireccionIP("0.0.0.0",true);
            for (int i = 0; i < 4; i++) {
                if (this.mascaraRed.octetos[i] != 0) {
                    this.idRed.octetos[i] = this.octetos[i];
                } else {
                    this.idRed.octetos[i] = 0;
                }
            }
    }

    public void determinarMascaraRed(){
            if (this.clase == 'C') {
                this.mascaraRed = MASCARAC;
            } else if (this.clase == 'B') {
                this.mascaraRed = MASCARAB;
            } else if (this.clase == 'A') {
                this.mascaraRed = MASCARAA;
            }
    }

    public static char obtenerClase(DireccionIP direccionIP) {
        char tipoClase='C';
        if(direccionIP.octetos[0]<128){
            tipoClase='A';
        } else if (direccionIP.octetos[0]>=128 && direccionIP.octetos[0]<192){
            tipoClase='B';
        }else if (direccionIP.octetos[0]>=192 && direccionIP.octetos[0]<224){
            tipoClase='C';
        }
        return tipoClase;
    }

    private int[] obtenerOctetos(String direccion) {
        int[] octetos = new int[4];
        int ultimoPunto=0;
        int contador=0;
        for (int i = 0; i < direccion.length(); i++) {
            if(direccion.charAt(i)=='.'){
                String octeto=direccion.substring(ultimoPunto,i);
                octetos[contador]=Integer.parseInt(octeto);
                ultimoPunto=i+1;
                contador++;
            }
        }
        String ultimoOcteto = direccion.substring(ultimoPunto, direccion.length());
        octetos[3]=Integer.parseInt(ultimoOcteto);
        return octetos;
    }

    public char getClase() {
        return clase;
    }

    public DireccionIP getMascaraRed() {
        return mascaraRed;
    }

    public DireccionIP getIdRed() {
        return idRed;
    }

    public String infoIP(){
        String salida="";

        salida+="Dirección:  "+this.toString()+"\n"+
                "Clase:      "+this.getClase()+"\n"+
                "Mascara Red:"+this.getMascaraRed()+"\n"+
                "Id de Red:  "+this.getIdRed();
        return salida;
    }

    @Override
    public String toString() {
        return "("+this.octetos[0]+"."+this.octetos[1]+"."+this.octetos[2]+"."+this.octetos[3]+")";
    }
}