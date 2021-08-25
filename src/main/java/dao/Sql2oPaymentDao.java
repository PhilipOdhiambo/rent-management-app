package dao;

import models.Payment;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.sql.Timestamp;
import java.util.List;

public class Sql2oPaymentDao implements PaymentDao {
    private  Sql2o sql2o;

    public Sql2oPaymentDao(Sql2o sql2o) {
        this.sql2o = sql2o;

    }




//    int id;
//    Timestamp date;
//    int propertyid;
//    int tenantid;
//    int amount;


    @Override
    public void add(Payment payment) {
        String sql = "INSERT INTO payments (propertyid, tenantid, amount, paidby, date) VALUES (:propertyid, :tenantid, :amount, :paidby, now())";
        try(Connection con = sql2o.open()) {
            int id = (int) con.createQuery(sql)
                    .bind(payment)
                    .executeUpdate().getKey();
            payment.setId(id);
        } catch (Sql2oException exception) {
            System.out.println(exception);
        }
    }

    @Override
    public List<Payment> getAll() {
        String sql = "SELECT * FROM payments";
        try(Connection con = sql2o.open()){
            return con.createQuery(sql).executeAndFetch(Payment.class);
        }
    }

    @Override
    public Payment getById(int id) {
        String sql = "SELECT * FROM payments WHERE id = :id";
        try(Connection con = sql2o.open()) {
            return con.createQuery(sql)
                    .addParameter("id",id).executeAndFetchFirst(Payment.class);
        }
    }

    @Override
    public void update(Payment updatedPayment) {

    }

    @Override
    public void deleteById(int id) {

    }

    @Override
    public void deleteAll() {

    }

}
