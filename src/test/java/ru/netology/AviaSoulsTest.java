package ru.netology;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Comparator;


public class AviaSoulsTest {


    private AviaSoulsManager soulsManager;

    Ticket ticket1 = new Ticket("Анапа", "Москва", 1000, 17, 21);
    Ticket ticket2 = new Ticket("Краснодар", "Сочи", 7000, 19, 22);
    Ticket ticket3 = new Ticket("Москва", "Геленджик", 2500, 10, 12);
    Ticket ticket4 = new Ticket("Анапа", "Москва", 8000, 12, 20);
    Ticket ticket5 = new Ticket("Анапа", "Москва", 5000, 06, 10);
    Ticket ticket6 = new Ticket("Адлер", "ЕКБ", 5000, 07, 10);

    @BeforeEach
    public void setUp() {
        soulsManager = new AviaSoulsManager();
        soulsManager.add(ticket1);
        soulsManager.add(ticket2);
        soulsManager.add(ticket3);
        soulsManager.add(ticket4);
        soulsManager.add(ticket5);
        soulsManager.add(ticket6);

    }

    @Test
    public void comparingTicketsWithEachOther() {    //тестируем метод сравнения
        Assertions.assertEquals(-1, ticket1.compareTo(ticket2));
        Assertions.assertEquals(1, ticket4.compareTo(ticket2));
        Assertions.assertEquals(0, ticket5.compareTo(ticket6));
    }

    @Test
    public void sortingTicketsInAscendingOrderOfPrice() {
        Ticket[] expected = {ticket1, ticket5, ticket4};
        Ticket[] actual = soulsManager.search("Анапа", "Москва");
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void comparisonOfTicketsByFlightTime() {
        TicketTimeComporator comporator = new TicketTimeComporator();
        Assertions.assertEquals(-1, comporator.compare(ticket2, ticket4));
        Assertions.assertEquals(1, comporator.compare(ticket5, ticket6));
        Assertions.assertEquals(0, comporator.compare(ticket5, ticket1));
    }

    @Test
    public void sortingTicketsByFlightTime() {
        TicketTimeComporator comporator = new TicketTimeComporator();
        Ticket[] expected = {ticket1, ticket5, ticket4};
        Ticket[] actual = soulsManager.searchAndSortBy("Анапа", "Москва", comporator);
        Assertions.assertArrayEquals(expected, actual);
    }
}
