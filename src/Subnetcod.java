public class Subnetcod {
    public static void main(String[] args) {

        int[] trabajo = {192,168,84,240};

        DireccionIP casa = new DireccionIP(trabajo);
        System.out.println(casa.infoIP());
    }
}
