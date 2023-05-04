package dev.lpa;

import java.util.LinkedList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        LinkedList<Place> places = new LinkedList<>();

        addPlace(places, new Place("Adelaide", 1374));
        addPlace(places, new Place("Alice Springs", 2771));
        addPlace(places, new Place("Brisbane", 917));
        addPlace(places, new Place("Darwin", 3972));
        addPlace(places, new Place("Melbourne", 877));
        addPlace(places, new Place("Perth", 3923));

        places.addFirst(new Place("Sydney", 0));

        printList(places);
        System.out.println();

        placesBrowser(places);

    }

    private static void addPlace(LinkedList<Place> list, Place place) {

        if (list.contains(place)) {
            System.out.println(place + " is already on the list");
            return;
        }

        for (Place p : list) {
            if (p.name().equalsIgnoreCase(place.name())) {
                System.out.println(place + " is already on the list");
                return;
            }
        }

        int matchedIndex = 0;
        for (var listPlace: list) {
            if (place.distance() < listPlace.distance()) {
                list.add(matchedIndex, place);
                return;
            }
            matchedIndex++;
        }

        list.add(place);

    }

    private static void printList(LinkedList<Place> places) {
        var iterator = places.listIterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next().name() + " is "
                    + iterator.previous().distance() + " km from Sydney");
            iterator.next();
        }
    }


    private static void placesBrowser(LinkedList<Place> places) {
        printMenu();
        Scanner action = new Scanner(System.in);
        var iterator = places.listIterator();
        boolean flag = false;
        boolean forward = true;
        while (!flag) {
            if (!iterator.hasPrevious()) {
                System.out.println(iterator.next());
                forward = true;
            }
            if (!iterator.hasNext()) {
                System.out.println(iterator.previous());
                forward = false;
            }
            System.out.println("Enter value: ");

            switch (action.nextLine().toLowerCase()) {
                default -> System.out.println("Invalid input. Try again");
                case "forward", "f" -> {
                    if (!forward) { // Reversing direction
                        forward = true;
                        if (iterator.hasNext()) {
                            iterator.next(); // Adjust position forward
                        }
                    }

                    if (iterator.hasNext()) {
                        System.out.println(iterator.next());
                    }
                }
                case "backward", "b" -> {
                    if (forward) { // Reversing direction
                        forward = false;
                        if (iterator.hasPrevious()) {
                            iterator.previous(); // Adjust position backward
                        }
                    }

                    if (iterator.hasPrevious()) {
                        System.out.println(iterator.previous());
                    }
                }
                case "list places", "list", "l" -> printList(places);
                case "menu", "m" -> printMenu();
                case "quit", "q" -> flag = true;
            }
        }
        System.out.println("Quitting");
    }

    private static void printMenu() {

        String actions = """
                Available actions (select word or letter):
                    (F)orward
                    (B)ackward
                    (L)ist Places
                    (M)enu
                    (Q)uit""";
        System.out.printf(actions + "%n");
    }

}
