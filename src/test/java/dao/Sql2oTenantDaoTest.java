package dao;

import models.Payment;
import models.Tenant;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.sql2o.Sql2o;

import org.sql2o.Connection;

import static org.junit.jupiter.api.Assertions.*;

class Sql2oTenantDaoTest {
    private Connection conn;
    private Sql2oTenantDao tenantDao;
    private  Sql2oPaymentDao paymentDao;

    @BeforeEach
    void setUp() {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "issah", "issah9960");
        tenantDao= new Sql2oTenantDao(sql2o);
        paymentDao = new Sql2oPaymentDao(sql2o);
        conn = sql2o.open();
    }

    @AfterEach
    void tearDown() {
        conn.close();
        System.out.println("connection closed");
    }

    @Test
    void addingTenantSetsId() {
        Tenant testTenant = setUpTenant();
        int tenantId=testTenant.getId();
        tenantDao.addTenant(testTenant);
        assertNotEquals(tenantId,tenantDao.getAllTenants().size());
    }

    @Test
    void getAllTenants() {
        Tenant testTenant = setUpTenant();
        tenantDao.addTenant(testTenant);
        assertEquals(1,tenantDao.getAllTenants().size());
    }

    @Test
    void findTenantById() {
        Tenant testTenant = setUpTenant();
        tenantDao.addTenant(testTenant);
        Tenant otherTenant = setUpTenant();
        tenantDao.addTenant(otherTenant);

        assertEquals(otherTenant,tenantDao.findTenantById(otherTenant.getId()));
    }

    @Test
    void getPaymentDetailsById() {
        Payment payment = testPayment();
        paymentDao.add(payment);
        Payment payment1 = testPayment();
        paymentDao.add(payment1);
        int tenantId = payment.getTenantid();

        assertEquals(2,tenantDao.getPaymentDetailsById(tenantId).size());

    }

    @Test
    void deleteTenantById() {
        Tenant testTenant = setUpTenant();
        tenantDao.addTenant(testTenant);
        Tenant otherTenant = setUpTenant();
        tenantDao.addTenant(otherTenant);

        tenantDao.deleteTenantById(testTenant.getId());

        assertEquals(1,tenantDao.getAllTenants().size());
    }

    @Test
    void updateTenantInfo() {
        Tenant testTenant = setUpTenant();
        tenantDao.addTenant(testTenant);
        tenantDao.updateTenantInfo(testTenant.getId(),"issah","54565528",1547896);
        Tenant updatedTenant = tenantDao.findTenantById(testTenant.getId());
        assertEquals("54565528",updatedTenant.getPhoneNumber());
    }

    @Test
    void clearAll() {
        Tenant testTenant = setUpTenant();
        tenantDao.addTenant(testTenant);
        Tenant otherTenant = setUpTenant();
        tenantDao.addTenant(otherTenant);

        tenantDao.clearAll();
        assertEquals(0,tenantDao.getAllTenants().size());
    }



    //helpers
    public Tenant setUpTenant(){
        return new Tenant("issah","54789654",698574);
    };
    private Payment testPayment() {
        return new Payment(5,101,10000,"Mrs. Philip");
    }





}