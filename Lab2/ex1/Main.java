import java.lang.Math;
public class Main {
    public static void main(String[] args) {
        Location Iasi = new Location("Iasi",LocationType.CITY,47.163,27.58);
        Location Brasov = new Location("Brasov",LocationType.CITY,45.65,25.60);
        Location Ploiesti = new Location("Ploiesti",LocationType.CITY,44.93,26.03);
        Location Bucuresti = new Location("Bucuresti",LocationType.CITY,90.30,29.93);
        Road A1 = new Road(RoadType.HIGHWAY,80,90,Iasi,Brasov);
        Road A2 = new Road(RoadType.HIGHWAY,350,90,Iasi,Brasov);
        //Road A3 = new Road(RoadType.HIGHWAY,20,70,Ploiesti,Bucuresti);
        Road A4 = new Road(RoadType.HIGHWAY,80,130,Iasi,Bucuresti);
        System.out.println(Iasi.toString());
        System.out.println(Brasov.toString());
        System.out.println(Ploiesti.toString());
        System.out.println(Bucuresti.toString());
        System.out.println(A1.toString());
        System.out.println(A2.toString());
        //System.out.println(A3.toString());
        System.out.println(A4);
    }
}