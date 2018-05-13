package com.government.contracts.repository;

import com.government.contracts.entity.PaymentType;
import com.government.contracts.utils.TestEntityFactory;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;


public class PaymentTypeRepositoryTest extends AbstractRepositoryTest<PaymentType, Long> {

    private static final String PAYMENT_TYPE_NAME = "testPaymentType";
    private static final String PAYMENT_TYPE_CODE = "testPaymentType";
    @Autowired
    private PaymentTypeRepository paymentTypeRepository;

    @Override
    protected PaymentType createEntity() {
        return TestEntityFactory.createPaymentType(PAYMENT_TYPE_NAME, PAYMENT_TYPE_CODE);
    }

    @Override
    protected CrudRepository<PaymentType, Long> getRepository() {
        return paymentTypeRepository;
    }

    @Override
    protected void assertEntity(PaymentType entity) {
        Assert.assertEquals(PAYMENT_TYPE_NAME, entity.getName());
        Assert.assertEquals(PAYMENT_TYPE_CODE, entity.getCode());
    }
}
