package tr.edu.iyte.ceng112.traversaliterator;

import java.util.Iterator;

import java.util.NoSuchElementException;
import tr.edu.iyte.ceng112.queue.EmptyQueueException;
import tr.edu.iyte.ceng112.queue.*;
import tr.edu.iyte.ceng112.tree.BinaryNode;

public class LevelOrderIterator<T> implements Iterator<T> {
	private QueueInterface<BinaryNode<T>> nodeQueue;
	
	public LevelOrderIterator(BinaryNode<T> root) {
		nodeQueue = new ArrayQueue();
		nodeQueue.enqueue(root);
		
	}
	@Override
	public boolean hasNext() {
		return (!nodeQueue.isEmpty());
	}

	@Override
	public T next() {
		if (!hasNext()) 
			throw new NoSuchElementException();
		
		BinaryNode<T> nextNode = null;
		try {
			nextNode = nodeQueue.dequeue();
		} catch (EmptyQueueException e) {
			e.printStackTrace();
		}
		if (nextNode.hasLeftChild())
			nodeQueue.enqueue(nextNode.getLeftChild());
		if (nextNode.hasRightChild())
			nodeQueue.enqueue(nextNode.getRightChild());			
		return nextNode.getData();
	}

}



