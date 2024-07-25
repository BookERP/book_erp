package main.java.com.bookstore.service;

import main.java.com.bookstore.dao.RentalDAO;
import main.java.com.bookstore.model.Rental;

import java.util.List;

public class RentalService {
    private RentalDAO rentalDAO;

    public RentalService() {
        rentalDAO = new RentalDAO();
    }

    // 대여 정보 추가
    public void addRental(Rental rental) {
        // 대여 정보 유효성 검사
        if (rental.getBookId() == null || rental.getCustomerId() == null) {
            throw new IllegalArgumentException("유효하지 않은 대여 정보입니다.");
        }
        rentalDAO.create(rental);
    }

    // 대여 정보 수정
    public void updateRental(Rental rental) {
        // 대여 정보 유효성 검사
        if (rental.getRentalId() == null || rental.getBookId() == null || rental.getCustomerId() == null) {
            throw new IllegalArgumentException("유효하지 않은 대여 정보입니다.");
        }
        rentalDAO.update(rental);
    }

    // 대여 정보 삭제
    public void deleteRental(String rentalId) {
        rentalDAO.delete(rentalId);
    }

    // 대여 정보 조회
    public Rental getRental(String rentalId) {
        return rentalDAO.read(rentalId);
    }

    // 모든 대여 정보 조회
    public List<Rental> getAllRentals() {
        return rentalDAO.readAll();
    }

    // 고객의 대여 목록 조회
    public List<Rental> getCustomerRentals(String customerId) {
        return rentalDAO.readByCustomerId(customerId);
    }
}