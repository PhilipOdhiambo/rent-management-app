package dao;

import models.Tenant;

import java.util.List;

public interface TenantDao {

    //create tenant
    void addTenant(Tenant tenant);

    //read tenants from database
    List<Tenant> getAllTenants();
    Tenant findTenantById(int id);
    //List<Tenant> getAllTenantsByPropertyId(int propertyId);

    //Update
    void updateTenantInfo(int id,String name,String phoneNumber,int propertyId);

    //delete tenant from database
    void deleteTenantById(int id);
    void clearAll();
}
