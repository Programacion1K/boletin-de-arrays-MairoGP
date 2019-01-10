import javax.swing.*;

public class Subnetcod {

    public static  DireccionIP[] Biblioteca = new DireccionIP[20];
    public static void main(String[] args) {
        menu();
    }

    public static void nuevaDireccionIP(){
        String direccion = JOptionPane.showInputDialog("Introduce la direccion IP separada por puntos");
        DireccionIP nuevadireccion = new DireccionIP(direccion);
        JOptionPane.showMessageDialog(null,direccion.toString());
        Biblioteca[DireccionIP.totalDirecciones]=nuevadireccion;
    }
    
    public static String mostrarDirecciones(){
        String listadoDireciones="";

        for (int i = 0; i < DireccionIP.totalDirecciones; i++) {
            listadoDireciones+= Biblioteca[i].toString()+"\n";
        }
        return listadoDireciones;
    }

    public static void establecerMascara(){
        String direccion = JOptionPane.showInputDialog(mostrarDirecciones()+"Introduce la direccion a modificar: ");
        DireccionIP direccionEncontrada = buscarDireccionIP(direccion);
        String comprobacion = JOptionPane.showInputDialog(direccionEncontrada.infoIP()+"\nEs correcta la direccion? (y/n)");    ;
        if(comprobacion.equals("y")){
            String mascaraNueva = JOptionPane.showInputDialog("Antigua Mascara: "+direccionEncontrada.getMascaraRed()+"\nIntroduce la nueva mascara de red");
            cambiarMascara(direccionEncontrada,mascaraNueva);
            JOptionPane.showMessageDialog(null,direccionEncontrada.infoIP());
        } else {
            return;
        }
    }

    public static void cambiarMascara(DireccionIP direccionACambiar, String mascara){
        for (int i = 0; i < DireccionIP.totalDirecciones; i++) {
            if(Biblioteca[i].toString().equals(direccionACambiar.toString())){
                Biblioteca[i].setMascaraRed(mascara);
                direccionACambiar.setMascaraRed(mascara);
            }
        }
    }


    public static DireccionIP buscarDireccionIP(String direccionABuscar){
        for (int i = 0; i < Biblioteca.length; i++) {
            if(Biblioteca[i].toString().equals(direccionABuscar)){
                DireccionIP direccionEncontrada = new DireccionIP(Biblioteca[i]);
                return direccionEncontrada;
            }
        }
        return null;
    }

    public static DireccionIP clonarDireccion(DireccionIP direccionACopiar){
        DireccionIP direccionClonada = new DireccionIP(direccionACopiar);
        return direccionClonada;
    }

    private static void mostrarInfoIP() {
        String direccion = JOptionPane.showInputDialog(mostrarDirecciones()+"Seleciona la direccion");
        JOptionPane.showMessageDialog(null, buscarDireccionIP(direccion).infoIP());
    }

    private static void menu() {
        DireccionIP casa = new DireccionIP("192.168.1.1");
        DireccionIP trabajo = new DireccionIP(170,10,2,75);
        int[] playa = {10,0,20,1};
        DireccionIP costa = new DireccionIP(playa);

        String menu = "";
        menu+="1. Introducir nueva dirección IP.\n"+
                "2. Mostrar la informacion de una direccion\n"+
                "3. Mostrar direcciones guardadas.\n"+
                "4. Establecer mascara de Red\n"+
                "6. Salir\n";

        int opcion=0;
        while(opcion!=6){
            opcion = Integer.parseInt(JOptionPane.showInputDialog(menu+"Introduce el número de la instrucción:"));
            switch (opcion){
                case 1: nuevaDireccionIP();
                    break;

                case 2: mostrarInfoIP();
                    break;

                case 3: JOptionPane.showMessageDialog(null,mostrarDirecciones());
                break;

                case 4: establecerMascara();
                break;
            }
        }
    }
}
