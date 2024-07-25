package main.java.com.bookstore.dao;

import main.java.com.bookstore.model.Purchase;
import main.java.com.bookstore.model.Rental;

import java.util.ArrayList;
import java.util.List;

public class RentalDAO {
    // ...

    public List<Rental> readByCustomerId(String customerId) {
        List<Rental> rentals = new ArrayList<>();
        // 고객 ID를 기반으로 대여 정보를 조회하는 쿼리 실행
        // 조회 결과를 Rental 객체로 변환하여 리스트에 추가
        return rentals;
    }

    public void create(Rental rental) {
    }

    public void update(Rental rental) {
    }

    public void delete(String rentalId) {
    }

    public Rental read(String rentalId) {

    }

    public List<Rental> readAll() {

    }
}

