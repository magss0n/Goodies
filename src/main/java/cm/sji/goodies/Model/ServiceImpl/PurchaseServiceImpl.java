package cm.sji.goodies.Model.ServiceImpl;

import cm.sji.goodies.Model.Entities.Purchase;
import cm.sji.goodies.Model.Repository.PurchaseRepository;
import cm.sji.goodies.Model.Services.PurchaseService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PurchaseServiceImpl implements PurchaseService {


    private final PurchaseRepository purchaseRepository;

    public PurchaseServiceImpl(PurchaseRepository purchaseRepository) {
        this.purchaseRepository = purchaseRepository;
    }

    @Override
    public List<Purchase> getPurchases() {
        return purchaseRepository.findAll();
    }

    @Override
    public void savePurchase(Purchase purchase) {
        purchaseRepository.save(purchase);
    }

    @Override
    public Purchase getPurchase(Long id) {
        return purchaseRepository.findById(id).orElse(null);
    }
}
