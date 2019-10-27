package org.academiadecodigo.whiledlings.map;

import org.academiadecodigo.whiledlings.message.Message;

import java.util.Iterator;
import java.util.LinkedList;

public class MapInfoList implements Iterable<String> {

    private int size;
    private LinkedList<String> list;

    public MapInfoList(int size) {
        this.size = size;
        this.list = fillInitialList();
    }

    public MapInfoList(int size, String header) {
        this(size);
        this.list.add(0, header);
    }

    private LinkedList<String> fillInitialList() {
        LinkedList<String> list = new LinkedList<>();
        for (int i = 1; i <= size; i++) {
            list.add(Color.ANSI_RESET);
        }
        return list;
    }

    public String[] toArray() {
        String[] strings = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            strings[i] = list.get(i);
        }
        return strings;
    }

    public void add(String message){
        list.remove(1);
        list.add(message);
    }

    public void setHeader(String s){
        list.remove(0);
        list.add(0, s);
    }
    @Override
    public Iterator<String> iterator() {
        return list.iterator();
    }

    public String getHeader() {
        return list.get(0);
    }

    public int size() {
        return size;
    }
}
