package com.government.contracts.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.government.contracts.dto.ResponseCode;
import com.government.contracts.dto.ResponseDto;
import com.government.contracts.model.Identifiable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.Serializable;
import java.util.Optional;

public abstract class AbstractCrudController<T extends Identifiable, ID extends Serializable> {
    private CrudRepository<T, ID> repository;
    private ObjectMapper mapper;

    public AbstractCrudController(CrudRepository repository) {
        this.repository = repository;
        this.mapper = new ObjectMapper();
    }

    @RequestMapping(method = RequestMethod.POST, value = "")
    public ResponseEntity<ResponseDto> save(@RequestBody T domain) {
        T entity = repository.save(domain);

        ResponseDto dto = new ResponseDto();
        dto.setCode(ResponseCode.OK);
        dto.setMessage("saved");
        dto.setDto(entity);

        return ResponseEntity.ok(dto);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public ResponseEntity<ResponseDto> delete(@PathVariable("id") ID id) {
        repository.deleteById(id);

        ResponseDto dto = new ResponseDto();

        dto.setCode(ResponseCode.OK);
        dto.setMessage("deleted");

        return ResponseEntity.ok(dto);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/{id}")
    public ResponseEntity<ResponseDto> update(@PathVariable("id") ID id, @RequestBody T domain) {
        ResponseDto dto = new ResponseDto();

        if (repository.existsById(id)) {
            domain.setId(id);
            T savedDomain = repository.save(domain);
            dto.setDto(savedDomain);
            dto.setCode(ResponseCode.OK);
            dto.setMessage("updated");
        } else {
            dto.setCode(ResponseCode.FAIL);
            dto.setMessage("not found");
        }

        return ResponseEntity.ok(dto);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity<ResponseDto> getById(@PathVariable("id") ID id) {
        Optional<T> optional = repository.findById(id);

        ResponseDto dto = new ResponseDto();
        dto.setMessage("getById");

        if (optional.isPresent()) {
            dto.setCode(ResponseCode.OK);
            dto.setDto(optional.get());
        } else {
            dto.setCode(ResponseCode.FAIL);
        }

        return new ResponseEntity<>(dto, HttpStatus.NOT_FOUND);
    }

    @RequestMapping(method = RequestMethod.GET, value = "")
    public ResponseEntity<ResponseDto> getAll() {
        Iterable<T> all = repository.findAll();

        ResponseDto dto = new ResponseDto();
        dto.setMessage("getAll");
        dto.setCode(ResponseCode.OK);
        dto.setDto(all);

        return ResponseEntity.ok(dto);
    }
}
