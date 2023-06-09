package tr.edu.iyte.ceng112.traversaliterator;

import java.util.Iterator;

import java.util.NoSuchElementException;

import tr.edu.iyte.ceng112.stack.ArrayStack;
import tr.edu.iyte.ceng112.stack.StackInterface;
import tr.edu.iyte.ceng112.tree.BinaryNode;

public class PostorderIterator<T> implements Iterator<T> {
	private StackInterface<BinaryNode<T>> nodeStack;
	
	public PostorderIterator(BinaryNode<T> root) {
		nodeStack = new ArrayStack<>();
		addToStack(root);
	}

	@Override
	public boolean hasNext() {
		return (!nodeStack.isEmpty());
	}
	
    private void addToStack(BinaryNode<T> node){
        nodeStack.push(node);
        if (node.hasRightChild())
            addToStack(node.getRightChild());
        if (node.hasLeftChild())
            addToStack(node.getLeftChild());
    }

	@Override
	public T next() {
		if (!hasNext())
			throw new NoSuchElementException();
		BinaryNode<T> nextNode = nodeStack.pop();
		return nextNode.getData();
	}
}



