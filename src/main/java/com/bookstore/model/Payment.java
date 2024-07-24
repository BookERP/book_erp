package src.main.java.com.bookstore.model;

public class Payment {
    private String paymentId;
    private String method;

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    @Override
    public String toString() {
        return "Payment [paymentId=" + paymentId + ", method=" + method + "]";
    }
}
