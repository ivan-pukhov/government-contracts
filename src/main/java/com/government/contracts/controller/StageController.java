package com.government.contracts.controller;

import com.government.contracts.dto.ResponseDto;
import com.government.contracts.dto.stage.CombineStageDto;
import com.government.contracts.dto.stage.DivideStageDto;
import com.government.contracts.entity.Stage;
import com.government.contracts.service.CrudService;
import com.government.contracts.service.PaymentService;
import com.government.contracts.service.StageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("stage")
public class StageController extends AbstractCrudController<Stage, Long>{
    @Autowired
    private StageService stageService;
    @Autowired
    private PaymentService paymentService;

    @RequestMapping("find/contract/{contractId}")
    public ResponseEntity<ResponseDto> findContractStages(@PathVariable Long contractId) {
        return createCorrectResponse(stageService.findContractStages(contractId));
    }

    @RequestMapping("find/contract/{contractId}/current")
    public ResponseEntity<ResponseDto> findCurrentContractStages(@PathVariable Long contractId) {
        return createCorrectResponse(stageService.findCurrentContractStages(contractId));
    }

    @RequestMapping("find/agreement/{additionalAgreementId}")
    public ResponseEntity<ResponseDto> findAdditionalAgreementStages(@PathVariable Long additionalAgreementId) {
        return createCorrectResponse(stageService.findAdditionalAgreementStages(additionalAgreementId));
    }

    @RequestMapping(value = "divide", method = RequestMethod.POST)
    public ResponseEntity<ResponseDto> divideStages(@RequestBody DivideStageDto divideStageDto) {
        return createCorrectResponse(stageService.divideStage(divideStageDto));
    }


    @RequestMapping(value = "combine", method = RequestMethod.POST)
    public ResponseEntity<ResponseDto> combineStages(@RequestBody CombineStageDto combineStageDto) {
        return createCorrectResponse(stageService.combineStage(combineStageDto));
    }

    @Override
    protected CrudService<Stage, Long> getCrudService() {
        return stageService;
    }
}
