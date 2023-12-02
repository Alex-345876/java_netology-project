package ru.netology.filatov_alexandr.controller;

import org.springframework.web.bind.annotation.*;
import ru.netology.filatov_alexandr.controller.dto.OperationsDTO;
import ru.netology.filatov_alexandr.controller.dto.OperationsGetReply;
import ru.netology.filatov_alexandr.domain.Customer;
import ru.netology.filatov_alexandr.domain.operation.Operation;
import ru.netology.filatov_alexandr.service.CustomerService;
import ru.netology.filatov_alexandr.service.StatementService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/api/operations/")
public class OperationsController {
    private final CustomerService customerService;
    private final StatementService statementService;

    public OperationsController(CustomerService customerService, StatementService statementService) {
        this.customerService = customerService;
        this.statementService = statementService;
    }

    @PostMapping()
    public OperationsDTO addOperation(int id, int sum, String currency, String merchant, Customer customer){
        Operation operation = new Operation(id, sum,currency,merchant,customer);
        statementService.saveOperation(operation);
        return new OperationsDTO(operation.getId(),operation.getSum(),operation.getCurrency(),operation.getMerchant());
    }

    @DeleteMapping("/delete/{id}")
    public void deleteOperation(@PathVariable("id") int id){
        statementService.delOperationsOnCustomerId(id);
    }

    @GetMapping("{id}")
    public OperationsGetReply checkOperationsByCustomerId(@PathVariable("id") int id){
        List<Operation> operations = statementService.getOperationOnId(id);
        List<OperationsDTO> operationsDTOS = operations.stream()
                .map(operation ->
                        new OperationsDTO(operation.getId(), operation.getSum(),operation.getCurrency(), operation.getMerchant())).collect(Collectors.toList());
        return new OperationsGetReply(operationsDTOS);
    }
}
