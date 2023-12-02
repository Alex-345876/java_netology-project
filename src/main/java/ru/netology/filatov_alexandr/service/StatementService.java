package ru.netology.filatov_alexandr.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import ru.netology.filatov_alexandr.domain.operation.Operation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@Component
public class StatementService {
    private final Map<Integer, List<Operation>> storage = new HashMap<>();

    public String getOperations(){
        return storage.toString();
    }

    public List<Operation> getOperationOnId(int operationId){
        return storage.get(operationId);
    }

    public void delOperationsOnCustomerId(int id){
        for(int i: storage.keySet()){
            if (i == id){
                storage.remove(i);
            }
        }
    }

    public void saveOperation(Operation operation){
        List<Operation> operations = storage.get(operation.getCustomerId());
        if (operations == null){
            List<Operation> customerOperations = new ArrayList<>();
            customerOperations.add(operation);
            storage.put(operation.getCustomerId(), customerOperations);
        } else {
            operations.add(operation);
        }
    }
}
