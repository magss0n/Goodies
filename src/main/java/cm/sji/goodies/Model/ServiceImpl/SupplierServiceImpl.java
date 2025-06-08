package cm.sji.goodies.Model.ServiceImpl;

import cm.sji.goodies.Model.Entities.Supplier;
import cm.sji.goodies.Model.Repository.SupplierRepository;
import cm.sji.goodies.Model.Services.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupplierServiceImpl implements SupplierService {

    SupplierRepository supplierRepository;

    @Autowired
    public SupplierServiceImpl(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    @Override
    public List<Supplier> getSuppliers() {
        return supplierRepository.findAll();
    }

    @Override
    public void addSupplier(Supplier supplier) {
        supplierRepository.save(supplier);
    }

    @Override
    public Supplier getSupplier(Long id) {
        return supplierRepository.findById(id).orElse(null);
    }
}
