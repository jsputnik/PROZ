package com.mycompany.app;


interface Packet {
    public void print();
    public String getData();
    public int getID();
    public void printNum();
    public void setNum(int n);
    public int getNum();
    public String[] getChannels();
    public void setChannels(String[] c);
    Packet cloneDeep();
}
