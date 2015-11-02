class MyQueue {
    // Push element x to the back of queue.
    Stack<Integer> s1 = new Stack<>();
    Stack<Integer> s2 = new Stack<>();
    public void push(int x) {
         s1.push(x);
    }

    // Removes the element from in front of queue.
    //ȡs1ջ��Ԫ�أ������н�s2�����Ԫ��������ջs1
    public void pop() {
        Integer temp = null;
        while(!s1.empty()){
            temp = s1.pop();
            if(!s1.empty()){
                s2.push(temp);
            }
        }
        while(!s2.empty()){
            s1.push(s2.pop());
        }
    }

    // Get the front element.
    //ȡs1ջ��Ԫ�أ������н�s2�����Ԫ��������ջs1
    public int peek() {
        Integer temp = null;
        while(!s1.empty()){
            temp = s1.pop();
            s2.push(temp);
        }
        while(!s2.empty()){
            s1.push(s2.pop());
        }
        return temp;
    }

    // Return whether the queue is empty.
    //s1,s2��Ϊ�գ�����Ϊ��
    public boolean empty() {
        return s1.empty() && s2.empty();
    }
}