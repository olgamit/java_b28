package ru.stqa.b28.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTests {

    @Test
    public void testDistance1(){
        Point p1 = new Point (5,6);
        Point p2  = new Point (8,10);

        Assert.assertEquals(p1.distance(p1,p2), 5);

    }

    @Test
    public void testDistance2(){
        Point p1 = new Point (1,2);
        Point p2  = new Point (6,14);

        Assert.assertEquals(p1.distance(p1,p2), 13);

    }

    @Test
    public void testDistance3(){
        Point p1 = new Point (7, 9);
        Point p2  = new Point (15,24);

        Assert.assertEquals(p1.distance(p1,p2), 20);

    }
}
