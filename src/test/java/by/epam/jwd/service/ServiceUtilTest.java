package by.epam.jwd.service;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class ServiceUtilTest {

    @Test
    public void paginationTest() {
        List<Integer> pages = null;
        for (int i = 1; i <= 16; i++) {
//            System.out.print("Page " + i);
//            System.out.println( ServiceUtil.pagination(16, 10, i));
            pages = ServiceUtil.getPagesNumber(16, 10, i);
        }
        Assert.assertEquals((pages.get(0) + pages.get(1)), 31);
    }
}