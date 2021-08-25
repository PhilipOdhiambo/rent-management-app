package dao;

import models.Payment;

import java.util.List;

public interface PaymentDao {
    //create
    public void add(Payment payment);

    // read
    public List<Payment> getAll();
    public Payment getById (int id);


    // update
    public void update(Payment updatedPayment);

    //delete
    public void deleteById(int id);
    public void deleteAll();
}
