package com.government.contracts.repository;

import com.government.contracts.model.PaymentType;
import com.government.contracts.utils.TestEntityFactory;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;


public class PaymentTypeRepositoryTest extends AbstractRepositoryTest<PaymentType, Long> {

    private static final String PAYMENT_TYPE_NAME = "testPaymentType";

    @Autowired
    private PaymentTypeRepository paymentTypeRepository;

    @Override
    protected PaymentType createEntity() {
        return TestEntityFactory.createPaymentType(PAYMENT_TYPE_NAME);
    }

    @Override
    protected CrudRepository<PaymentType, Long> getRepository() {
        return paymentTypeRepository;
    }

    @Override
    protected void assertEntity(PaymentType entity) {
        Assert.assertEquals(PAYMENT_TYPE_NAME, entity.getName());
    }
}
