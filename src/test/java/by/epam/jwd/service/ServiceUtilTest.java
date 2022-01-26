package by.epam.jwd.service;

import org.junit.Test;

import static org.junit.Assert.*;

public class ServiceUtilTest {

    @Test
    public void paginationTest() {
        for (int i = 1; i <= 16; i++) {
            System.out.print("Page " + i);
            System.out.println( ServiceUtil.pagination(16, 10, i));
        }
    }
}