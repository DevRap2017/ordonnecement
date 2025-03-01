package com.ordonnecement;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public record Process(int pid, int start, int time) {

    public Process execute() {
        if (!isDone()) {
            return new Process(pid, start, time - 1);
        }
        return null;
    }

    public boolean isDone() {
        return time <= 0;
    }

    public List<Process> run(List<Process> processes) {
        AtomicInteger start = new AtomicInteger(0);
        List<Process> exectuedProcess = new ArrayList<>();
        processes.stream().forEach(proces -> {
            if (proces.start() <= start.get()) {
                List<Process> availableProcesses = availablesProcesses(processes, start.get());

                for (Process process : availableProcesses) {
                    Process process1 = process;
                    while (!process1.isDone()) {
                        process1 = process1.execute();
                    }
                    exectuedProcess.add(process1);
                }
            } else {
                start.getAndIncrement();
            }
        });

        return exectuedProcess;
    }

    public List<Process> availablesProcesses(List<Process> processes, int start) {

        List<Process> availablesProcesses = new ArrayList<>();

            for (Process process : processes) {
                if (process.start() <= start && !process.isDone()) {
                    availablesProcesses.add(process);
                }
        }
        availablesProcesses.sort(Comparator.comparing(p -> p.time));
        return availablesProcesses;
    }

    public String format(){
        return "{pid:"+pid+", start: "+start+", time: "+time+"}";
    }
}


