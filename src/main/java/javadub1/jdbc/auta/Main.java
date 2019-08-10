package javadub1.jdbc.auta;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Scanner;

public class Main {



    public static void main(String[] args) {

        CarDao dao = new CarDao();
        Scanner scanner = new Scanner(System.in);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");

        boolean isWorking = true;

        do {
            String komenda = scanner.nextLine();

            if (komenda.equalsIgnoreCase("dodaj")) {
                Car nowyCar = new Car();

                System.out.println("Podaj Nazwe: ");
                nowyCar.setNazwa(scanner.nextLine());

                System.out.println("Podaj Marke: ");
                nowyCar.setMarka(scanner.nextLine());

                System.out.println("Podaj Przebieg: ");
                nowyCar.setPrzebieg(Double.parseDouble(scanner.nextLine()));

                System.out.println("Podaj date zakupu: (ex. 2013/03/05)");
                LocalDate date = LocalDate.parse(scanner.nextLine(), formatter);
                nowyCar.setDate(date);

                dao.insertCar(nowyCar);

            } else if (komenda.equalsIgnoreCase("usun")) {
                Car nowyCar = new Car();
                System.out.println("Podaj Id do usuniecia: ");
                dao.deleteCar(Long.parseLong(scanner.nextLine()));

            } else if (komenda.equalsIgnoreCase("listuj")) {
                dao.getAllCars().forEach(System.out::println);

            } else if (komenda.equalsIgnoreCase("znajdz_marke")) {
                System.out.println("Podaj Marke: ");
                dao.getCarByNazwa(scanner.nextLine()).forEach(System.out::println);
            } else if (komenda.equalsIgnoreCase("znajdz_przebieg")){
                System.out.println("Podaj przebieg: ");
                dao.getCarByPrzebieg(Double.parseDouble(scanner.nextLine())).forEach(System.out::println);

            } else if (komenda.equalsIgnoreCase("quit")) {
                isWorking = false;
            }
        } while (isWorking);
    }
}
