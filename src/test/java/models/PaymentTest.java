package models;

import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class PaymentTest {
    @Test
    void instance_paymentInstancceCreated_payment() {
        Payment payment = new Payment(1,7,3000, "house help");
        assertTrue(payment instanceof Payment);
    }
}