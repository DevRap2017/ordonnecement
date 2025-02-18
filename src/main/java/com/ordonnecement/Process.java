package com.ordonnecement;

public record Process(int pid,int start,int time) {

    public Process execute(){
        if(time >0)
            return new Process(pid,start,time-1);
        return null;
    }
}
