package com.mycompany.app;


interface Packet {
    public int getID();
    public void setNum(int n);
    public int getNum();
    public Board getBoard();
    public Player getPlayer();
    Packet cloneDeep();
}
