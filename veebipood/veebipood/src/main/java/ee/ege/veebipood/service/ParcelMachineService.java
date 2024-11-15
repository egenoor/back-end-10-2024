package ee.ege.veebipood.service;

import ee.ege.veebipood.model.ParcelMachine;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ParcelMachineService {
    public List<ParcelMachine> getParcelMachines() {
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://www.omniva.ee/locations.json";
        ResponseEntity<ParcelMachine[]> response = restTemplate.exchange(
            url,
            HttpMethod.GET,
            null,
            ParcelMachine[].class
        );
        if (response.getBody() == null) {
            return new ArrayList<>();
        }
        return Arrays.asList(response.getBody());
    }

    public List<ParcelMachine> getParcelMachinesByCountry(String country) {
        String pmCountry = country.toUpperCase();
        List<ParcelMachine> parcelMachines = getParcelMachines();
//        List<ParcelMachine> parcelMachinesByCountry = new ArrayList<>();
//        for (ParcelMachine pm: parcelMachines) {
//            // country name = a0_NAME
//            if(pm.a0_NAME.equals(country)) {
//                parcelMachinesByCountry.add(pm);
//            }
//        }
        return parcelMachines.stream()
                .filter(pm -> pm.a0_NAME.equals(pmCountry))
                .toList();
    }
}
