package dsaa.lab02;

import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class OneWayLinkedList<E> implements IList<E>{

    private class Element{
        public Element(E e) {
            this.object=e;
        }
        E object;
        Element next=null;
    }

    Element sentinel;

    private class InnerIterator implements Iterator<E>{
        Element last;
        public InnerIterator() {
            last = sentinel;
        }
        @Override
        public boolean hasNext() {
            return (last.next != null);
        }

        @Override
        public E next() throws NoSuchElementException{
            if(hasNext())
            {
                last = last.next;
                return last.object;
            }
            else throw new NoSuchElementException();
        }
    }

    public OneWayLinkedList() {
        sentinel = new Element(null);
    }

    @Override
    public Iterator<E> iterator() {
        return new InnerIterator();
    }

    @Override
    public ListIterator<E> listIterator() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean add(E e) {
        Element previous = sentinel;
        while(previous.next != null)
        {
            previous = previous.next;
        }
        previous.next = new Element(e);
        return true;
    }

    @Override
    public void add(int index, E element) throws NoSuchElementException {
        if(index < 0) throw new NoSuchElementException();
        if(sentinel.next == null)
        {
            if(index == 0) {
                sentinel.next = new Element(element);
                return;
            }
            else
                throw new NoSuchElementException();
        }
        Element current = sentinel;
        Element next = current.next;
        while(index > 0)
        {
            if(current.next == null) throw new NoSuchElementException();
            current = next;
            next = current.next;
            index--;
        }
        current.next = new Element(element);
        current.next.next = next;
    }

    @Override
    public void clear() {
        sentinel.next = null;
    }

    @Override
    public boolean contains(E element) {
        InnerIterator iterator = new InnerIterator();
        while (iterator.hasNext())
        {
            if(iterator.next().equals(element)) return true;
        }
        return false;
    }

    @Override
    public E get(int index) throws NoSuchElementException {
        if(index < 0) throw new NoSuchElementException();
        if(index == 0 && sentinel.next == null) throw new NoSuchElementException();
        Element element = sentinel.next;
        while(index > 0)
        {
            if(element.next == null) throw new NoSuchElementException();
            element = element.next;
            index--;
        }
        return element.object;
    }

    @Override
    public E set(int index, E element) throws NoSuchElementException {
        if(index < 0) throw new NoSuchElementException();
        Element current = sentinel.next;
        while(index > 0)
        {
            if(current.next == null) throw new NoSuchElementException();
            current = current.next;
            index--;
        }
        Element previous = new Element(current.object);
        current.object = element;
        return previous.object;
    }

    @Override
    public int indexOf(E element) {
        int index = 0;
        InnerIterator iterator = new InnerIterator();

        while(iterator.hasNext())
        {
            if(iterator.next().equals(element)) return index;
            index++;
        }
        return -1;
    }

    @Override
    public boolean isEmpty() {
        return sentinel.next == null;
    }

    @Override
    public E remove(int index) throws NoSuchElementException {
        if(sentinel.next == null || index < 0) throw new NoSuchElementException();
        Element current = sentinel;
        Element next = sentinel.next;
        while(index > 0)
        {
            if(current.next == null) throw new NoSuchElementException();
            current = next;
            next = current.next;
            index--;
        }
        if(next.next == null)
        {
            current.next = null;
        }
        else
        {
            current.next = next.next;
        }
        return next.object;
    }

    public void clearEven()
    {
        Element element = sentinel;
        while(element.next != null && element.next.next != null)
        {
            element.next = element.next.next;
            element = element.next;
        }
        element.next = null;
    }

    @Override
    public boolean remove(E e) {
        if(indexOf(e) == -1) return false;
        remove(indexOf(e));
        return true;
    }

    @Override
    public int size() {
        InnerIterator iterator = new InnerIterator();
        int size = 0;
        while(iterator.hasNext())
        {
            size++;
            iterator.next();
        }
        return size;
    }

}
