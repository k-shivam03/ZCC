package model;

public class PagePointer {

    private String prev;
    private String next;

    public PagePointer() {
        super();
    }

    public PagePointer(String prev, String next) {
        this.prev = prev;
        this.next = next;
    }

    public String getPrev() {
        return prev;
    }

    public void setPrev(String prev) {
        this.prev = prev;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }
}
