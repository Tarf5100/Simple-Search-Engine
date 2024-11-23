public class MyArrayList<T>{
    private int maxSize;
    private int size;
    private int current;
    private Object[] nodes; // Using Object array to store generic elements

    public MyArrayList(int n) {
        maxSize = n;
        size = 0;
        current = -1;
        nodes = new Object[maxSize]; // Directly initialize as Object[]
    }
    public MyArrayList() {
        this.maxSize = 500; // default size if not specified
        this.size = 0;
        this.current = -1;
        this.nodes = new Object[maxSize];
    }


    public boolean full() {
        return size == maxSize;
    }


    public boolean empty() {
        return size == 0;
    }


    public boolean last() {
        return current == size - 1;
    }


    public void findFirst() {
        current = 0;
    }


    public void findNext() {
        current++;
    }

    public int size() {
        return size;
    }


    public T retrieve(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Invalid index: " + index);
        }
        return (T) nodes[index];
    }



    public T retrieve() {
        if (current < 0 || current >= size) {
            throw new IndexOutOfBoundsException("Invalid position: " + current);
        }
        return (T) nodes[current]; // Return element at the current position
    }



    public void update(T value, int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index " + index + " out of bounds for length " + size);
        }
        nodes[index] = value; // Update the element at the specified index with the new value
    }



    public void insert(T val) {
        if (full()) { // Check if the array is full
            resize(); // Dynamically resize the array if needed
        }
        for (int i = size - 1; i > current; --i) {
            nodes[i + 1] = nodes[i];
        }
        current++;
        nodes[current] = val;
        size++;
    }
    public void resize() {
        maxSize = maxSize * 2;  // Double the size
        Object[] newNodes = new Object[maxSize];  // Create a new array with the new size
        System.arraycopy(nodes, 0, newNodes, 0, nodes.length);  // Copy elements to new array
        nodes = newNodes;  // Replace old array with new array
    }

    public T remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }

        // Save the element to be removed
        T removedElement = (T) nodes[index];

        // Shift elements to the left, overwriting the element at `index`
        for (int i = index + 1; i < size; i++) {
            nodes[i - 1] = nodes[i];
        }
        // Decrease the size
        size--;

        // Adjust the `current` pointer if necessary
        if (size == 0) {
            current = -1; // If the list is empty
        } else if (current >= size) {
            current = size - 1; // Move `current` to the last valid element
        }
        // Return the removed element
        return removedElement;
    }
    public boolean contains(T element) {
        for (int i = 0; i < size; i++) {
            if (element == null && nodes[i] == null) { // Special case for null
                return true;
            } else if (element != null && element.equals(nodes[i])) { // Regular equality check
                return true;
            }
        }
        return false;
    }



}

