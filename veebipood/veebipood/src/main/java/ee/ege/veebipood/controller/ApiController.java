package ee.ege.veebipood.controller;

import ee.ege.veebipood.model.ParcelMachine;
import ee.ege.veebipood.model.supplier.SupplierProduct;
import ee.ege.veebipood.model.supplier.SupplierProductEscuela;
import ee.ege.veebipood.service.ParcelMachineService;
import ee.ege.veebipood.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ApiController {

    @Autowired
    SupplierService supplierService;

    @Autowired
    ParcelMachineService parcelMachineService;

    @GetMapping("supplier")
    public List<SupplierProduct> getSupplierProducts() {
        return supplierService.getProducts();
    }

    @GetMapping("parcel-machines")
    public List<ParcelMachine> getParcelMachines() {
        return parcelMachineService.getParcelMachines();
    }

    @GetMapping("parcel-machines/{country}")
    public List<ParcelMachine> getParcelMachinesByCountry(@PathVariable String country) {
        return parcelMachineService.getParcelMachinesByCountry(country);
    }

    @GetMapping("supplier-escuela")
    public List<SupplierProductEscuela> getEscuelaProducts() {
        return supplierService.getEscuelaProducts();
    }
}
