package main.java.com.bookstore.service;

import main.java.com.bookstore.dao.PurchaseDAO;
import main.java.com.bookstore.model.Purchase;

import java.util.List;

public class PurchaseService {
    private PurchaseDAO purchaseDAO;

    public PurchaseService() {
        purchaseDAO = new PurchaseDAO();
    }

    // 구매 정보 추가
    public void addPurchase(Purchase purchase) {
        // 구매 정보 유효성 검사
        if (purchase.getBookId() == null || purchase.getCustomerId() == null) {
            throw new IllegalArgumentException("유효하지 않은 구매 정보입니다.");
        }
        purchaseDAO.create(purchase);
    }

    // 구매 정보 수정
    public void updatePurchase(Purchase purchase) {
        // 구매 정보 유효성 검사
        if (purchase.getPurchaseId() == null || purchase.getBookId() == null || purchase.getCustomerId() == null) {
            throw new IllegalArgumentException("유효하지 않은 구매 정보입니다.");
        }
        purchaseDAO.update(purchase);
    }

    // 구매 정보 삭제
    public void deletePurchase(String purchaseId) {
        purchaseDAO.delete(purchaseId);
    }

    // 구매 정보 조회
    public Purchase getPurchase(String purchaseId) {
        return purchaseDAO.read(purchaseId);
    }

    // 모든 구매 정보 조회
    public List<Purchase> getAllPurchases() {
        return purchaseDAO.readAll();
    }

    // 고객의 구매 목록 조회
    public List<Purchase> getCustomerPurchases(String customerId) {
        return purchaseDAO.readByCustomerId(customerId);
    }
}