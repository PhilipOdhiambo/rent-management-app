package dao;


import models.Payment;
import models.Tenant;

import org.sql2o.Connection;
import org.sql2o.Sql2o;
import java.util.List;
import org.sql2o.Sql2oException;

public class Sql2oTenantDao implements TenantDao{
    private final Sql2o sql2o;

    public Sql2oTenantDao(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public void addTenant(Tenant tenant) {
        String sql = "INSERT INTO tenants (name, phoneNumber,propertyId) VALUES (:name, :phoneNumber, :propertyId)";

        try (Connection con = sql2o.open()) {
            int id = (int) con.createQuery(sql, true)
                    .bind(tenant)
                    .executeUpdate()
                    .getKey();
            tenant.setId(id);
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public List<Tenant> getAllTenants() {
        try (Connection con = sql2o.open()) {
            return con.createQuery("SELECT * FROM tenants")
                    .executeAndFetch(Tenant.class);
        }
    }

    @Override
    public Tenant findTenantById(int id) {
        try (Connection con = sql2o.open()) {
            return con.createQuery("SELECT * FROM tenants WHERE id = :id")
                    .addParameter("id", id)
                    .executeAndFetchFirst(Tenant.class);
        }
    }

    @Override
    public List<Payment> getPaymentDetailsById(int id) {
        String sql ="SELECT * FROM payments WHERE tenantid = :id";
        try(Connection con = sql2o.open()) {
            return con.createQuery(sql)
                    .addParameter("id",id)
                    .executeAndFetch(Payment.class);
        }
    }

    @Override
    public void updateTenantInfo(int id, String name, String phoneNumber, int propertyId) {
        String sql = "UPDATE tenants SET (id, name, phoneNumber,propertyId) = (:id,:name, :phoneNumber, :propertyId) WHERE id=:id"; //CHECK!!!
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("id",id)
                    .addParameter("name",name)
                    .addParameter("phoneNumber", phoneNumber)
                    .addParameter("propertyId", propertyId)
                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }


    @Override
    public void deleteTenantById(int id) {
        String sql = "DELETE from tenants WHERE id=:id";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void clearAll() {
        String sql = "DELETE from tenants";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql).executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

}
