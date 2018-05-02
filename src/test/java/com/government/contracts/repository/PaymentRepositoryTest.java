package com.government.contracts.repository;

import com.government.contracts.model.Payment;
import com.government.contracts.model.PaymentType;
import com.government.contracts.utils.TestEntityFactory;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;


public class PaymentRepositoryTest extends AbstractRepositoryTest<Payment, Long> {

    private static final String TEST_STAGE_NUMBER = "testStageNumber";
    private static final String TEST_PAYMENT_TYPE = "testPaymentType";

    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private PaymentTypeRepository paymentTypeRepository;

    @Override
    protected Payment createEntity() {
        PaymentType paymentType = TestEntityFactory.createPaymentType(TEST_PAYMENT_TYPE);
        PaymentType storedType = paymentTypeRepository.save(paymentType);

        Assert.assertNotNull(storedType.getId());

        Payment payment = TestEntityFactory.createPayment(storedType.getId(), TEST_STAGE_NUMBER);
        return payment;
    }

    @Override
    protected CrudRepository<Payment, Long> getRepository() {
        return paymentRepository;
    }

    @Override
    protected void assertEntity(Payment entity) {
        Assert.assertEquals(TEST_STAGE_NUMBER, entity.getStageNumber());
        Assert.assertNotNull(entity.getPaymentDate());
        Assert.assertEquals(TestEntityFactory.TEST_PRICE, entity.getPaymentSum());
        Assert.assertNotNull(entity.getPaymentTypeId());
    }
}
