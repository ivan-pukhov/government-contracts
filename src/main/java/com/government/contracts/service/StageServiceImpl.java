package com.government.contracts.service;

import com.government.contracts.dto.stage.CombineStageDto;
import com.government.contracts.dto.stage.DivideStageDto;
import com.government.contracts.enums.StageStatusEnum;
import com.government.contracts.model.Stage;
import com.government.contracts.model.StageParentInfo;
import com.government.contracts.model.StageStatus;
import com.government.contracts.repository.stage.StageParentInfoRepository;
import com.government.contracts.repository.stage.StageRepository;
import com.government.contracts.repository.stage.StageStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StageServiceImpl extends CrudServiceImpl<Stage, Long> implements StageService {

    private static final String COMBINE_STAGE_DESCRIPTION = "Combine stage operation";
    private static final String DIVIDE_STAGE_DESCRIPTION = "Divide stage operation";

    @Autowired
    private StageStatusRepository stageStatusRepository;
    @Autowired
    private StageRepository stageRepository;
    @Autowired
    private StageParentInfoRepository stageParentInfoRepository;

    public StageServiceImpl(StageRepository stageRepository) {
        super(stageRepository);
    }

    @Override
    public Stage save(Stage domain) {
        StageStatus stageStatus = stageStatusRepository.findByStageCode(StageStatusEnum.CURRENT.name());
        domain.setStageStatus(stageStatus);
        return super.save(domain);
    }

    @Override
    public Iterable<Stage> findContractStages(Long contractId) {
        StageStatus stageStatus = stageStatusRepository.findByStageCode(StageStatusEnum.CURRENT.name());
        return ((StageRepository)getRepository()).findContractStages(contractId, stageStatus.getId());
    }

    @Override
    public Stage combineStage(CombineStageDto combineStageDto) {
        if(combineStageDto.getParentIds() == null || combineStageDto.getParentIds().isEmpty()) {
            throw new IllegalArgumentException("Parent stage ids list is empty");
        } else {
            Iterable<Stage> parentStages = stageRepository.findByIdIn(combineStageDto.getParentIds());
            StageStatus deletedStatus = stageStatusRepository.findByStageCode(StageStatusEnum.DELETED.name());
            StageStatus currentStatus = stageStatusRepository.findByStageCode(StageStatusEnum.CURRENT.name());

            parentStages.forEach(parentStage -> {
                parentStage.setStageStatus(deletedStatus);
            });

            Stage newStage = combineStageDto.getNewStage();
            newStage.setStageStatus(currentStatus);
            Stage storedNewStage = stageRepository.save(newStage);

            Iterable<Stage> deletedStages = stageRepository.saveAll(parentStages);
            saveCombineStageParentInfo(storedNewStage, deletedStages);
            return storedNewStage;
        }
    }

    @Override
    public Iterable<Stage> divideStage(DivideStageDto divideStageDto) {
        if(divideStageDto.getStages() == null || divideStageDto.getStages().isEmpty()) {
            throw new IllegalArgumentException("New stage list is empty");
        } else {
            StageStatus deletedStatus = stageStatusRepository.findByStageCode(StageStatusEnum.DELETED.name());
            StageStatus currentStatus = stageStatusRepository.findByStageCode(StageStatusEnum.CURRENT.name());
            List<Stage> newStages = divideStageDto.getStages();
            newStages.forEach(item -> item.setStageStatus(currentStatus));

            Optional<Stage> parentStatusOpt = stageRepository.findById(divideStageDto.getParentStageId());
            if(parentStatusOpt.isPresent()) {
                Stage parentStatus = parentStatusOpt.get();

                Iterable<Stage> newStagesSaved = stageRepository.saveAll(newStages);

                parentStatus.setStageStatus(deletedStatus);
                Stage storedParent = stageRepository.save(parentStatus);

                saveDivideStageParentInfo(newStagesSaved, storedParent);
                return newStagesSaved;
            } else {
                throw new IllegalArgumentException("Wrong parent stage status id: [" + divideStageDto.getParentStageId() + "]");
            }
        }
    }

    private Iterable<StageParentInfo> saveCombineStageParentInfo(Stage newStage, Iterable<Stage> parentStages) {
        List<StageParentInfo> parentInfoList = new ArrayList<>();
        for (Stage parentStage: parentStages) {
            StageParentInfo parentInfo = new StageParentInfo();
            parentInfo.setParentStageId(parentStage.getId());
            parentInfo.setStageId(newStage.getId());
            parentInfo.setDescription(COMBINE_STAGE_DESCRIPTION);
            parentInfoList.add(parentInfo);
        }
        return stageParentInfoRepository.saveAll(parentInfoList);

    }

    private Iterable<StageParentInfo> saveDivideStageParentInfo(Iterable<Stage> newStages, Stage parentStage) {
        List<StageParentInfo> parentInfoList = new ArrayList<>();
        for (Stage newStage: newStages) {
            StageParentInfo parentInfo = new StageParentInfo();
            parentInfo.setParentStageId(parentStage.getId());
            parentInfo.setStageId(newStage.getId());
            parentInfo.setDescription(DIVIDE_STAGE_DESCRIPTION);
        }
        return stageParentInfoRepository.saveAll(parentInfoList);
    }
}
