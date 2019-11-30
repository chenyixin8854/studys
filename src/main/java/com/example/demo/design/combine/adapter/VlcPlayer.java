package com.example.demo.design.combine.adapter;

/**
 * @program: demo
 * @description:
 * @author: chenyixin
 * @create: 2019-06-21 00:53
 **/
public class VlcPlayer implements AdvancedMediaPlayer{
    @Override
    public void playVlc(String fileName) {
        System.out.println("Playing vlc file. Name: "+ fileName);
    }

    @Override
    public void playMp4(String fileName) {
        //什么也不做
    }
}
