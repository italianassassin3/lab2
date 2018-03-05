package arrayIndexList;

import java.lang.reflect.Array;

import indexList.IndexList;

public class ArrayIndexList<E> implements IndexList<E> {
//	private static final int INITCAP = 5; 
//	private static final int CAPTOAR = 5; 
//	private static final int MAXEMPTYPOS = 10; 
	
	private static final int INITCAP = 1; 
	private static final int CAPTOAR = 1; 
	private static final int MAXEMPTYPOS = 2; 
	
	private E[] element; 
	private int size; 

	public ArrayIndexList() { 
		element = (E[]) new Object[INITCAP]; 
		size = 0; 
	} 
	

	public void add(int index, E e) throws IndexOutOfBoundsException {
		// ADD CODE AS REQUESTED BY EXERCISES
		if(index < 0 || index > this.size()) {
			throw new IndexOutOfBoundsException("add(int index, E e): " + index);
		}
		
		if(this.size() == element.length) {
			 this.changeCapacity(CAPTOAR);
		}
		this.moveDataOnePositionTR(index , this.size() -1);
		
		element[index] = e;
		size++;
		
		
	
	}


	public void add(E e) {
		// ADD CODE AS REQUESTED BY EXERCISES
		
		
		if(this.size() == element.length) {
			 this.changeCapacity(CAPTOAR);
		}
		
		
		
	element[size++] = e;
	
	}


	public E get(int index) throws IndexOutOfBoundsException {
		// ADD AND MODIGY CODE AS REQUESTED BY EXERCISES
		
		E tmp = element[index];
		
		if(index < 0 || index > this.size() -1) {
			throw new IndexOutOfBoundsException("get(int index): " + index);
		}
		
		
		return tmp; 
	}


	public boolean isEmpty() {
		return size == 0;
	}


	public E remove(int index) throws IndexOutOfBoundsException {
		// ADD AND MODIFY CODE AS REQUESTED BY EXERCISES
		if(index < 0 || index > this.size() -1) {
			throw new IndexOutOfBoundsException("remove(int index): " + index);
		}
		
		E tmp = element[index];
		
		
		if(element.length - this.size() > this.MAXEMPTYPOS) {
			this.changeCapacity(-this.CAPTOAR);
		
		
			
		
		
		
		}
		
		element[index] = null;
		size--;
		this.moveDataOnePositionTL(index +1, this.size() -1 );
		
	
	
		
		
		
		
		return tmp;
	}


	public E set(int index, E e) throws IndexOutOfBoundsException {
		// ADD AND MODIFY CODE AS REQUESTED BY EXERCISES
		if(index < 0 || index > this.size() -1) {
			throw new IndexOutOfBoundsException("set(int index, E e): " + index);
		}
		E tmp = element[index];
		
		element[index] = e;
		
		return tmp;
	}


	public int size() {
		return size;
	}	
	
	public int capacity() { 
		 return element.length; 
	}

	
	// private methods  -- YOU CAN NOT MODIFY ANY OF THE FOLLOWING
	// ... ANALYZE AND USE WHEN NEEDED
	
	// you should be able to decide when and how to use
	// following method.... BUT NEED TO USE THEM WHENEVER
	// NEEDED ---- THIS WILL BE TAKEN INTO CONSIDERATION WHEN GRADING
	
	private void changeCapacity(int change) { 
		int newCapacity = element.length + change; 
		E[] newElement = (E[]) new Object[newCapacity]; 
		for (int i=0; i<size; i++) { 
			newElement[i] = element[i]; 
			element[i] = null; 
		} 
		element = newElement; 
	}
	
	// useful when adding a new element with the add
	// with two parameters....
	private void moveDataOnePositionTR(int low, int sup) { 
		// pre: 0 <= low <= sup < (element.length - 1)
		for (int pos = sup; pos >= low; pos--)
			element[pos+1] = element[pos]; 
	}

	// useful when removing an element from the list...
	private void moveDataOnePositionTL(int low, int sup) { 
		// pre: 0 < low <= sup <= (element.length - 1)
		for (int pos = low; pos <= sup; pos++)
			element[pos-1] = element[pos]; 
	}


	// The following two methods are to be implemented as part of an exercise
	public Object[] toArray() {
		// TODO es in Exercise 3
	
		
		
		Object[] array = new Object[this.size()]; 
	    int i = 0;
	    for (E e: element) {
	        array[i] = e;
	        i++;
	    
	    }
	    return array;	
	}


	@Override
	public <T1> T1[] toArray(T1[] array) {
		// TODO as in Exercise 3
		 if (array.length < this.size()) { 
		        // if arr.length < this.size, allocate a new array of the same 
		    	// type as arr (components of the new array are set to be of equal
		    	// runtime type as components of the original array arr) 
		    	// and big enough to hold all the elements in this set. For 
		    	// this, we need to use the Array class....
		    	array = (T1[]) Array.newInstance(array.getClass().getComponentType(), this.size());
		    } else if (array.length > this.size()) 
		    	// Set to null all those positions of arr that won't be used. 
		    	for (int j=this.size(); j< array.length; j++) 
		    		array[j] = null;
		    
		    int i = 0;
		    for (E e: element) {
		    	if(i == array.length) {
		    		break;//done so i doesn't exceed internal array length to avoid IOOBE
		    	}
		    	array[i] = (T1) e;   // It is assumed E can be casted to T
		        i++;
		        
		    }
		    return array;	
	}

}
