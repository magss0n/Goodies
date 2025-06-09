package cm.sji.goodies.Model.Services;

import cm.sji.goodies.Model.Entities.Purchase;

import java.util.List;

public interface PurchaseService {
    List<Purchase> getPurchases();
    void savePurchase(Purchase purchase);
    Purchase getPurchase(Long id);
}
