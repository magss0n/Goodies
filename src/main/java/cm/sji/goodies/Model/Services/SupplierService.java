package cm.sji.goodies.Model.Services;

import cm.sji.goodies.Model.Entities.Supplier;

import java.util.List;

public interface SupplierService {
    List<Supplier> getSuppliers();
    void addSupplier(Supplier supplier);
    Supplier getSupplier(Long id);
}
