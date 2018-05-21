package com.government.contracts.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.government.contracts.dto.ResponseCode;
import com.government.contracts.dto.ResponseDto;
import com.government.contracts.entity.Identifiable;
import com.government.contracts.service.CrudService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.Optional;

public abstract class AbstractCrudController<T extends Identifiable, ID extends Serializable> {

    @RequestMapping(method = RequestMethod.POST, value = "")
    public ResponseEntity<ResponseDto> save(@RequestBody T domain) {
        T entity = getCrudService().save(domain);

        ResponseDto dto = new ResponseDto();
        dto.setCode(ResponseCode.OK);
        dto.setMessage("saved");
        dto.setObject(entity);

        return ResponseEntity.ok(dto);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public ResponseEntity<ResponseDto> delete(@PathVariable("id") ID id) {
        getCrudService().deleteById(id);

        ResponseDto dto = new ResponseDto();

        dto.setCode(ResponseCode.OK);
        dto.setMessage("deleted");

        return ResponseEntity.ok(dto);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/{id}")
    public ResponseEntity<ResponseDto> update(@PathVariable("id") ID id, @RequestBody T domain) {
        ResponseDto dto = new ResponseDto();
        try {
            T savedDomain = getCrudService().update(id, domain);
            dto.setObject(savedDomain);
            dto.setCode(ResponseCode.OK);
            dto.setMessage("updated");
        } catch (IllegalArgumentException e) {
            dto.setCode(ResponseCode.FAIL);
            dto.setMessage("not found");

        }
        return ResponseEntity.ok(dto);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity<ResponseDto> getById(@PathVariable("id") ID id) {
        Optional<T> optional = getCrudService().findById(id);

        ResponseDto dto = new ResponseDto();
        dto.setMessage("getById");

        if (optional.isPresent()) {
            dto.setCode(ResponseCode.OK);
            dto.setObject(optional.get());
            return ResponseEntity.ok(dto);
        } else {
            dto.setCode(ResponseCode.FAIL);
            return new ResponseEntity<>(dto, HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "")
    public ResponseEntity<ResponseDto> getAll() {
        Iterable<T> all = getCrudService().findAll();

        ResponseDto dto = new ResponseDto();
        dto.setMessage("getAll");
        dto.setCode(ResponseCode.OK);
        dto.setObject(all);

        return ResponseEntity.ok(dto);
    }

    protected ResponseEntity<ResponseDto> createCorrectResponse(Object dto) {
        ResponseDto responseDto = new ResponseDto();
        responseDto.setObject(dto);
        responseDto.setCode(ResponseCode.OK);
        responseDto.setMessage("OK");
        return ResponseEntity.ok(responseDto);
    }

    protected abstract CrudService<T, ID> getCrudService();
}
