package com.ordonnecement;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class ServiceExecutor {


    public List<Process> run(List<Process> processes) {
        AtomicInteger start = new AtomicInteger(0);
        List<Process> exectuedProcess = new ArrayList<>();
        processes.stream().forEach(proces -> {
            if (proces.start() <= start.get()) {
                List<Process> availableProcesses = availablesProcesses(processes, start.get());

                for (Process process : availableProcesses) {
                    Process process1 = process;
                    final int pid = process1.pid();
                    while (!process1.isDone()) {
                        process1 = process1.execute();
                    }

                    if(!exectuedProcess.stream().anyMatch(process2->process2.pid()==pid))
                    exectuedProcess.add(process);
                }
                start.getAndIncrement();
            } else {
                start.getAndIncrement();
            }
        });
        exectuedProcess.forEach(process -> System.out.println(process.format()));

        return exectuedProcess;
    }

    public List<Process> availablesProcesses(List<Process> processes, int start) {

        List<Process> availablesProcesses = new ArrayList<>();

        for (Process process : processes) {
            if (process.start() <= start && !process.isDone()) {
                availablesProcesses.add(process);
            }
        }
        availablesProcesses.sort(Comparator.comparing(p -> p.time()));
        return availablesProcesses;
    }
}
