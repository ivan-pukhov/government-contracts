package com.government.contracts.repository;


import com.government.contracts.model.StageStatus;
import com.government.contracts.utils.TestEntityFactory;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;

public class StageStatusRepositoryTest extends AbstractRepositoryTest<StageStatus, Long> {

    private static final String STAGE_STATUS_NAME = "stageStatusName";
    @Autowired
    private StageStatusRepository stageStatusRepository;

    @Override
    protected StageStatus createEntity() {
        StageStatus status = TestEntityFactory.createStageStatus(STAGE_STATUS_NAME);

        return status;
    }

    @Override
    protected CrudRepository<StageStatus, Long> getRepository() {
        return stageStatusRepository;
    }

    @Override
    protected void assertEntity(StageStatus entity) {
        Assert.assertEquals(STAGE_STATUS_NAME, entity.getStageName());
    }
}
