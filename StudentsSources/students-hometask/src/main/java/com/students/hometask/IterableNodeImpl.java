package com.students.hometask;

import java.util.Iterator;

/**
 * Created by kkolesnichenko on 10/23/2015.
 */
public class IterableNodeImpl<T> extends NodeImpl<T> implements Iterable<IterableNodeImpl<T>> {
    @Override
    public Iterator<IterableNodeImpl<T>> iterator() {
        return new NodeImplIterator(this);
    }

    private static class NodeImplIterator<T> implements Iterator<Node<T>> {

        private Node<T> node;

        public NodeImplIterator(Node<T> node){
            this.node=node;
        }

        @Override
        public boolean hasNext() {
            return node.next()!=null;
        }

        @Override
        public Node<T> next() {
            Node<T>  nodeTmp=node;
            node=node.next();
            return nodeTmp;
        }
    }
}
