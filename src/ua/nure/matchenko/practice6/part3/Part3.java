package ua.nure.matchenko.practice6.part3;

public class Part3 {
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        Parking parking = new Parking(n);
        parking.print();
        System.out.println(parking.arrive(2));
        parking.print();
        System.out.println(parking.arrive(3));
        parking.print();
        System.out.println(parking.arrive(2));
        parking.print();
        System.out.println(parking.arrive(2));
        parking.print();
        System.out.println(parking.arrive(2));
        parking.print();
        System.out.println(parking.depart(1));
        parking.print();
        System.out.println(parking.depart(1));
        parking.print();
    }
}
