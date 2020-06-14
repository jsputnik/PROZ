package com.mycompany.app;

import io.netty.channel.group.ChannelGroup;


interface TestClass {
    public void print();
    public String getData();
    public int getID();
    public void printNum();
    public String[] getChannels();
    public void setChannels(String[] c);
    TestClass cloneDeep();
}
