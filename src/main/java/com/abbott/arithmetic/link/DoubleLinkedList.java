package com.abbott.arithmetic.link;

public class DoubleLinkedList {
    private int size;
    private Node head;

    public DoubleLinkedList() {
        size = 0;
    }

    public int size() {
        return size;
    }

    public void print() {
        if (size == 0) {
            System.out.println("empty list");
            return;
        }

        Node node = head;
        while (node != null) {
            System.out.println(node.getData());
            node = node.getNext();
        }
    }


    public void add(Node node) {
        if (node == null) {
            return;
        }



        if (head == null) {
            head = node;
        } else {
            Node temp = head;
            while (temp.getNext() != null) {
                temp = temp.getNext();
            }

            temp.setNext(node);
            node.setPre(temp);
        }


        size++;
    }


    public  void insert(int index ,Node node){
        if(index > size || index < 0){
            System.out.println("index out of array");
            return;
        }

        if(node == null){
            return;
        }

        size ++;

        if(head == null){
            head = node;
            return;
        }

        if(index == 0){

            node.setNext(head);
            head.setPre(node);

            head = node;

        }else{
            Node temp = head;
            for (int i = 0; i < index-1; i++) {
                temp = temp.getNext();
            }

            Node next = temp.getNext();

            temp.setNext(node);
            node.setPre(temp);

            if(next != null){
                next.setPre(node);
                node.setNext(next);
            }

        }
    }

    /**
     * 返回指定的node
     * @param index
     * @return
     */
    public Node get(int index){

        if(index >= size || index < 0){
            System.out.println("index out of array");
            throw new IndexOutOfBoundsException("index out of array");
        }

        Node temp = head;
        for (int i = 0; i < index; i++) {
            temp = temp.getNext();
        }

        return temp;


    }

}
