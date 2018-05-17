package com.abbott.arithmetic.link;

public class LinkedList {
    private int size;
    private Node head;

    public LinkedList() {
        size = 0;
    }

    public void print() {
        if (size == 0) {
            System.out.println("list is empty");
        } else {
            System.out.println("size = " + size);
        }

        Node node = head;
        while (node != null) {
            System.out.println(node.getData());
            node = node.getNext();
        }
    }


    /**
     * 在末尾追加一个节点
     *
     * @param node
     */
    public void add(Node node) {
        if (node == null) {
            return;
        }
        //如果链表为空,那新增的这个node就作为head
        if (size == 0) {
            head = node;
        } else { //添加到末尾
            Node last = head;
            while (last.getNext() != null) {
                last = last.getNext();
            }
            last.setNext(node);
        }
        size++;
    }

    public void insert(int index, Node node) {
        if (index > size || index < 0) {
            System.out.println("index越界");
            return;
        }


        if (node == null) {
            return;
        }

        size++;
        //first
        if (index == 0) {

            node.setNext(head);
            head = node;

        } else {
            Node current = head;
            for (int i = 1; i < index; i++) {
                //get current index node
                current = current.getNext();
            }
            //real insert
            Node last = current.getNext();

            current.setNext(node);
            node.setNext(last);
        }


    }


    public void delete(int index) {
        if (index > size || index < 0) {
            System.out.println("index out");
            return;
        }

        if (size == 0) {
            System.out.println("size = 0 can not delete");
            return;
        }

        if (index == 0) {
            head = head.getNext();
        } else {
            //拿到当前index的前一个节点
            Node temNode = head;
            index = index - 1;
            for (int i = 1; i <= index; i++) {
                temNode = temNode.getNext();
            }

            if(temNode.getNext() == null){ 
                temNode.setNext(null);
            }else {
                temNode.setNext(temNode.getNext().getNext());
            }
        }


        size--;


    }
}
