public class DireccionIP {
    private int[] octetos = new int[4];
    private char clase;
    private boolean IdRed;

    DireccionIP(String direccion) {
        this.octetos=obtenerOctetos(direccion);
        this.clase=obtenerClase(this);
    }

    DireccionIP(int primeroct, int segundoct, int terceroct, int cuartoct) {
        this.octetos[0] = primeroct;
        this.octetos[1] = segundoct;
        this.octetos[2] = terceroct;
        this.octetos[3] = cuartoct;
    }

    DireccionIP(int[] direccion) {
        this.octetos = direccion;
    }

    public char getClase() {
        return clase;
    }

    private char obtenerClase(DireccionIP direccionIP) {
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

    @Override
    public String toString() {
        return "("+this.octetos[0]+"."+this.octetos[1]+"."+this.octetos[2]+"."+this.octetos[3]+")";
    }
}