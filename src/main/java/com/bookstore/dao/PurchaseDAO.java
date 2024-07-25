package main.java.com.bookstore.dao;

import main.java.com.bookstore.model.Purchase;

import java.util.ArrayList;
import java.util.List;

public class PurchaseDAO {
    // ...

    public List<Purchase> readByCustomerId(String customerId) {
        List<Purchase> purchases = new ArrayList<>();
        // 고객 ID를 기반으로 구매 정보를 조회하는 쿼리 실행
        // 조회 결과를 Purchase 객체로 변환하여 리스트에 추가
        return purchases;
    }

    public void create(Purchase purchase) {

    }

    public void update(Purchase purchase) {
    }

    public void delete(String purchaseId) {
    }

    public Purchase read(String purchaseId) {
        return Purchase.;
    }

    public List<Purchase> readAll() {

    }
}