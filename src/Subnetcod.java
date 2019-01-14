import javax.swing.*;

public class Subnetcod {

    public static  DireccionIP[] Biblioteca = new DireccionIP[20];

    public static void main(String[] args) {
        menu();
    }

    public static void nuevaDireccionIP(){
        String direccion = JOptionPane.showInputDialog("Introduce la direccion IP separada por puntos");
        DireccionIP nuevadireccion = new DireccionIP(direccion);
        JOptionPane.showMessageDialog(null,nuevadireccion.infoIP());
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

    private static void mostrarInfoIP() {
        String direccion = JOptionPane.showInputDialog(mostrarDirecciones()+"Seleciona la direccion");
        JOptionPane.showMessageDialog(null, buscarDireccionIP(direccion).infoIP());
    }


    public static void conjuntoMismaRed (DireccionIP[] biblioteca){
        boolean mismaRed = false;
        for (int i = 0; i < DireccionIP.totalDirecciones; i++) {
            if (biblioteca[i].getIdRed().toString().equals(biblioteca[i+1].getIdRed().toString())){
                mismaRed = true;
            } else{
                mismaRed = false;
            }
        }
    }

    private static void pertenecenMismaRed() {
        String seleccionar = JOptionPane.showInputDialog(mostrarDirecciones()+"Introduce la primera dirección a comprobar");
        DireccionIP direccionEncotrada1 = buscarDireccionIP(seleccionar);
        seleccionar = JOptionPane.showInputDialog(mostrarDirecciones()+"Introduce la segunda direccion a comprobar");
        DireccionIP direccionEncontrada2 = buscarDireccionIP(seleccionar);

        String comprobación = JOptionPane.showInputDialog(mostrarDirecciones()+"\n("+direccionEncotrada1.toString()+
                                                                                ") ("+direccionEncontrada2.toString()+
                                                                                ") \nSon correctas las direcciones seleccionadas?");
        if(comprobación.charAt(0)=='y'){
            String salida="";

            salida+=direccionEncotrada1.infoIP() +"---------------------\n"+direccionEncontrada2.infoIP();

            if(direccionEncotrada1.getIdRed().toString().equals(direccionEncontrada2.getIdRed().toString())){
                salida+="\nLas dos direcciones pertenecen a la misma Red";
            } else {
                salida+="\nLas dos direcciones pertenecen a distinta Red";
            }
            JOptionPane.showMessageDialog(null,salida);
        } else {
            return;
        }
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
                "5. Dos IP's pertenecen a la misma red\n"+
                "6. Conjunto de IP's pertenecen a la misma red\n"+
                "9. Salir\n";

        int opcion=0;
        while(opcion!=9){
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

                case 5: pertenecenMismaRed();
                break;

                case 6: mismaRed();
            }
        }
    }


    private static void mismaRed() {

        DireccionIP[] arrayExterno = new DireccionIP[50];
        int contador=0;
        String respuesta = JOptionPane.showInputDialog("Introduzca las direcciones separadas por puntos. \nIntroduzca \'n\' para salir");
        while(respuesta.charAt(0)!='n'){
            arrayExterno[contador] = new DireccionIP(respuesta);
            contador++;
            respuesta = JOptionPane.showInputDialog("Introduzca las direcciones separadas por puntos. \nIntroduzca \'n\' para salir");
        }
        String direccionesAñadidas = "Direcciones añadidas\n";
        for (int i = 0; i < contador; i++) {
            direccionesAñadidas+=arrayExterno[i].toString()+"\n";
        }
        JOptionPane.showMessageDialog(null,direccionesAñadidas);
        System.out.println(contador);
        String añadirExistentes = JOptionPane.showInputDialog("Desea añadir las direcciones ya guardadas?");
        if(añadirExistentes.charAt(0) == 'y'){
            for (int i = 1; i <= DireccionIP.totalDirecciones; i++) {
                arrayExterno[contador] = new DireccionIP(Biblioteca[i-1]);
                contador++;
            }
            conjuntoMismaRed(Biblioteca);

        } else {
            conjuntoMismaRed(arrayExterno);
        }

    }

//    private static void crearBiblioteca() {
//        DireccionIP[] bibliotecaEjemplo = new DireccionIP[50];
//        int[] octetos = new int[100];
//
//        for(int i=0;i<bibliotecaEjemplo.length;i++){
//            octetos[i] = octetos[i+1]-octetos[i+4];
//            bibliotecaEjemplo[i] = new DireccionIP(octetos[i],octetos[i+1],octetos[i+2],octetos[i+3]);
//        }
//
//        for (int i = 0; i < bibliotecaEjemplo.length; i++) {
//            System.out.println(bibliotecaEjemplo[i].infoIP());
//        }
//
//    }

}
