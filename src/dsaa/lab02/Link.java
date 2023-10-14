package dsaa.lab02;

public class Link{
    public String ref;
    public Link(String ref) {
        this.ref=ref;
    }

    public boolean equals(Object link1) {
        if(!(link1 instanceof Link)) return false;
        return (((Link)link1).ref.equals(ref));
    }

    public String toString()
    {
        return ref;
    }
}