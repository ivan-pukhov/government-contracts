package com.government.contracts.repository.stage;


import com.government.contracts.enums.StageStatusEnum;
import com.government.contracts.entity.StageStatus;
import com.government.contracts.repository.AbstractRepositoryTest;
import com.government.contracts.utils.TestEntityFactory;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;

public class StageStatusRepositoryTest extends AbstractRepositoryTest<StageStatus, Long> {

    private static final String STAGE_STATUS_NAME = "stageStatusName";
    private static final String STAGE_STATUS_CODE = "stageStatusCode";

    private static final String WRONG_STAGE_STATUS = "wrongStageStatus";
    @Autowired
    private StageStatusRepository stageStatusRepository;

    @Test
    public void testFindByStageCode() {
        StageStatus stageStatusActive = stageStatusRepository.findByStageCode(StageStatusEnum.CURRENT.name());
        Assert.assertNotNull(stageStatusActive);

        StageStatus stageStatusClosed = stageStatusRepository.findByStageCode(StageStatusEnum.CLOSED.name());
        Assert.assertNotNull(stageStatusClosed);

        StageStatus stageStatusDeleted = stageStatusRepository.findByStageCode(StageStatusEnum.DELETED.name());
        Assert.assertNotNull(stageStatusDeleted);

        Assert.assertNull(stageStatusRepository.findByStageCode(WRONG_STAGE_STATUS));
    }

    @Override
    protected StageStatus createEntity() {
        return TestEntityFactory.createStageStatus(STAGE_STATUS_NAME, STAGE_STATUS_CODE);
    }

    @Override
    protected CrudRepository<StageStatus, Long> getRepository() {
        return stageStatusRepository;
    }

    @Override
    protected void assertEntity(StageStatus entity) {
        Assert.assertEquals(STAGE_STATUS_NAME, entity.getStageName());
        Assert.assertEquals(STAGE_STATUS_CODE, entity.getStageCode());
    }
}
