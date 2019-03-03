package com.prashant.domainjpa.service;

import com.prashant.domainjpa.data.Patent;
import com.prashant.domainjpa.data.Status;
import com.prashant.domainjpa.repository.PatentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class PatentService {

    private Logger logger = LoggerFactory.getLogger(PatentService.class);

    PatentRepository patentRepository;

    @Autowired
    public PatentService(PatentRepository repo) {
        this.patentRepository = repo;
    }

    public List<Patent> getAllPatents() {
        return patentRepository.findAll();
    }

    public Patent getPatent(String guid) {
        return patentRepository.findById(guid)
                .orElseThrow(() -> new RuntimeException("Patent not found"));
    }

    public Patent patchPatent(String recordId, Map<String,String> params) {
        Patent patent = getPatent(recordId);

        List<Method> setters = getSetters(patent.getClass());

        params.forEach((fieldName, value) -> {
            setters.stream().filter(method -> method.getName().equalsIgnoreCase("set" + fieldName)).findFirst()
                    .ifPresent(method -> {
                        try {
                            if (fieldName.equalsIgnoreCase("Status")) {
                                method.invoke(patent, Status.getStatusEnum(value));
                            } else {
                                method.invoke(patent, value);
                            }
                        } catch (Exception ex) {
                            logger.error("Error calling setter for {}", fieldName, ex);
                        }
                    });
        });

        return patentRepository.save(patent);
    }

    private static List<Method> getSetters(Class aClass){
        return Arrays.stream(aClass.getMethods())
                .filter(m -> m.getName().startsWith("set") && m.getParameterTypes().length == 1)
                .collect(Collectors.toList());
        }
}
