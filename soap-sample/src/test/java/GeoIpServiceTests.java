import com.lavasoft.GeoIPService;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;

public class GeoIpServiceTests {

    @Test
    public void testMyIp(){
        String ipLocation = new GeoIPService().getGeoIPServiceSoap12().getIpLocation("37.57.0.209");
        String country = new GeoIPService().getGeoIPServiceSoap12().getCountryISO2ByName("37.57.0.209");
        System.out.println(ipLocation + " " + country);
        //assertEquals(ipLocation, "UA");
    }

    @Test
    public void testMyIpInvalid(){
        String ipLocation = new GeoIPService().getGeoIPServiceSoap12().getIpLocation("37.57.0.qqq");
        System.out.println(ipLocation);
        assertEquals(ipLocation, "UA");
    }
}
