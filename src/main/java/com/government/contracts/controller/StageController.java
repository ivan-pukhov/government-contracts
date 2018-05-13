package com.government.contracts.controller;

import com.government.contracts.dto.ResponseDto;
import com.government.contracts.dto.stage.CombineStageDto;
import com.government.contracts.dto.stage.DivideStageDto;
import com.government.contracts.entity.Stage;
import com.government.contracts.service.StageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("stage")
public class StageController extends AbstractCrudController<Stage, Long>{
    @Autowired
    private StageService stageService;

    public StageController(StageService stageService) {
        super(stageService);
    }

    @RequestMapping("find/contract/{contractId}")
    public ResponseEntity<ResponseDto> findContractStages(@PathVariable Long contractId) {
        return createCorrectResponse(stageService.findContractStages(contractId));
    }

    @RequestMapping(value = "divide", method = RequestMethod.POST)
    public ResponseEntity<ResponseDto> divideStages(@RequestBody DivideStageDto divideStageDto) {
        return createCorrectResponse(stageService.divideStage(divideStageDto));
    }


    @RequestMapping(value = "combine", method = RequestMethod.POST)
    public ResponseEntity<ResponseDto> combineStages(@RequestBody CombineStageDto combineStageDto) {
        return createCorrectResponse(stageService.combineStage(combineStageDto));
    }
}
