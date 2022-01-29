package ru.stqa.b28.soap;

import com.lavasoft.GeoIPService;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

public class GeoIpServiceTests {

    @Test
    public void testMyIp() {
        String geoIP = new GeoIPService().getGeoIPServiceSoap12().getIpLocation("xx.xxx.x.xxx");
        assertTrue(geoIP.contains("RU"));

    }
}
