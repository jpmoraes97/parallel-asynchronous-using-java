package com.learnjava.parallelstreams;

import com.learnjava.util.DataSet;

import java.util.List;
import java.util.stream.Collectors;

import static com.learnjava.util.CommonUtil.*;
import static com.learnjava.util.LoggerUtil.log;

public class ParallelStreamsExample {

    public List<String> stringTransform(List<String> namesList) {
        return namesList
                //.stream()
                .parallelStream()
                .map(this::addNameLengthTransform)
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        List<String> namesList = DataSet.namesList();
        ParallelStreamsExample parallelStreamsExample = new ParallelStreamsExample();
<<<<<<< HEAD

=======
>>>>>>> 00c727c9fac4a989104f5b10945e2ac47bdd1541
        startTimer();
        List<String> resultList = parallelStreamsExample.stringTransform(namesList);
        log("ResultList: " + resultList);
        timeTaken();
    }

    private String addNameLengthTransform(String name) {
        delay(500);
<<<<<<< HEAD
        return name.length() + " - " + name;
=======
        return name.length() + " - ".concat(name);
>>>>>>> 00c727c9fac4a989104f5b10945e2ac47bdd1541
    }
}
