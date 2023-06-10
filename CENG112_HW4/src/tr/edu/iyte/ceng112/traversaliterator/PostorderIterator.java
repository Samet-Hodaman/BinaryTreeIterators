package tr.edu.iyte.ceng112.traversaliterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

import tr.edu.iyte.ceng112.stack.ArrayStack;
import tr.edu.iyte.ceng112.stack.StackInterface;
import tr.edu.iyte.ceng112.tree.BinaryNode;

public class PostorderIterator<T> implements Iterator<T> {
    private StackInterface<BinaryNode<T>> nodeStack;
    private BinaryNode<T> currentNode;
    private BinaryNode<T> lastVisitedNode;

    public PostorderIterator(BinaryNode<T> root) {
        nodeStack = new ArrayStack<>();
        currentNode = root;
        lastVisitedNode = null;
    }

    @Override
    public boolean hasNext() {
        return (currentNode != null || !nodeStack.isEmpty());
    }

    @Override
    public T next() {
        if (!hasNext())
            throw new NoSuchElementException();

        while (currentNode != null) {
            nodeStack.push(currentNode);
            currentNode = currentNode.getLeftChild();
        }

        BinaryNode<T> peekNode = nodeStack.peek();

        if (peekNode.getRightChild() != null && peekNode.getRightChild() != lastVisitedNode) {
            currentNode = peekNode.getRightChild();
        } else {
            BinaryNode<T> node = nodeStack.pop();
            lastVisitedNode = node;
            return node.getData();
        }

        return next();
    }

    public void remove() {
        throw new UnsupportedOperationException();
    }
}