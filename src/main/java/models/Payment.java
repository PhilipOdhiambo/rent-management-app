package models;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;

public class Payment {
    int id;
    Timestamp date;
    int propertyid;
    int tenantid;
    int amount;
    String paidby;

    public Payment(int propertyid, int tenantid, int amount, String paidby) {
        this.date = new Timestamp(System.currentTimeMillis());
        this.propertyid = propertyid;
        this.tenantid = tenantid;
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public int getPropertyid() {
        return propertyid;
    }

    public void setPropertyid(int propertyid) {
        this.propertyid = propertyid;
    }

    public int getTenantid() {
        return tenantid;
    }

    public void setTenantid(int tenantid) {
        this.tenantid = tenantid;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getPaidby() {
        return paidby;
    }

    public void setPaidby(String paidby) {
        this.paidby = paidby;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Payment payment = (Payment) o;
        return id == payment.id && propertyid == payment.propertyid && tenantid == payment.tenantid && amount == payment.amount && Objects.equals(paidby, payment.paidby);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, propertyid, tenantid, amount, paidby);
    }
}
