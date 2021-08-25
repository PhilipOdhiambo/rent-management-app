package dao;

import models.Payment;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static org.junit.jupiter.api.Assertions.*;

class Sql2oPaymentDaoTest {
    private Connection con;
    private  Sql2oPaymentDao paymentDao;

    @BeforeEach
    void setUp() {
        String connectionString =  "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        paymentDao = new Sql2oPaymentDao(sql2o);
        con = sql2o.open();
    }

    @AfterEach
    void tearDown() {
        con.close();
    }

    @Test
    void add() {
        Payment payment = testPayment();
        paymentDao.add(payment);
        assertNotEquals(0,payment.getId());
    }

    @Test
    void getAll() {
        Payment pay1 = testPayment();
        Payment pay2 = testPayment();
        paymentDao.add(pay1);
        paymentDao.add(pay2);
        assertEquals(2,paymentDao.getAll().size());
    }

    @Test
    void getById() {
        Payment payment = testPayment();
        paymentDao.add(payment);
        assertEquals(payment,paymentDao.getById(payment.getId()));
    }

    @Test
    void update() {
    }

    @Test
    void deleteById() {
    }

    @Test
    void deleteAll() {
    }

    // helper function
    private Payment testPayment() {
        return new Payment(5,101,10000,"Mrs. Philip");
    }
}