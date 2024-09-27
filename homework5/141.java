/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public boolean hasCycle(ListNode head) {
        
        ListNode p1 = head;
        ListNode p2 = head;
       
        while(p1 != null && p1.next != null){
            if (p1.next == head) return true;
            p1 = p1.next;
            p2.next = head;
            p2 = p1;
        }
        return false;
    }
}