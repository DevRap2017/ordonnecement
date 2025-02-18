package com.ordonnecement;

public record Process(int pid,int start,int time) {

    public Process execute(){
        if(!isDone())
            return new Process(pid,start,time-1);
        return null;
    }

    public boolean isDone(){
        return time<=0;
    }

    public Process run(Process process){
        Process process1 = process;
        while (!process1.isDone()){
           process1 =  process1.execute();
        }
        return process1;
    }
}


