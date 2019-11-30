package com.example.demo.interview.jcu;

import java.util.concurrent.*;
import java.util.concurrent.atomic.LongAdder;

public class ForkJoinTest extends RecursiveTask<Integer> {
    private final int threshold=10;
    private int first;
    private int last;

    public ForkJoinTest(int first,int last){
        this.first=first;
        this.last=last;
    }

    @Override
    protected Integer compute() {
        int result=0;
        if(last-first<=threshold){
            for(int i=first;i<=last;i++){
                result+=i;
            }
        }
        else{
            int middle=(first+last)/2;
            ForkJoinTest l=new ForkJoinTest(first,middle);
            ForkJoinTest r=new ForkJoinTest(middle+1,last);
            l.fork();
            r.fork();
            result=l.join()+r.join();
        }
        return result;
    }



    public static void main(String[] args){
        ForkJoinTest test=new ForkJoinTest(1,100);
        ForkJoinPool pool=new ForkJoinPool();
        Future result=pool.submit(test);
        try {
            System.out.println(result.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
