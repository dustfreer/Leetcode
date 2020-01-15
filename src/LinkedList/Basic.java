package LinkedList;

public class Basic {
	public int count(ListNode head) {
		if ( head == null ) return 0;
		
		int count = 0;
		while ( head != null ) {
			head = head.next;
			count ++;
		}
		return count;
	}
	
	public ListNode reverse(ListNode head) {
		if ( head == null || head.next == null ) return head;
		
		ListNode back = null;
		ListNode mid = head;
		ListNode front = head.next;
		while ( front != null) {
			mid.next = back;
			back = mid;
			mid = front;
			front = front.next;			
		}
		mid.next = back;
		return mid;
	}
	
	public void breakIntoHalf(ListNode head) {
		if ( head == null || head.next == null ) return ;
		
		ListNode dummy = new ListNode(-1);
		dummy.next = head;
		ListNode slow = dummy;
		ListNode quick = dummy;
		while ( quick != null && quick.next != null ) {
			quick = quick.next.next;
			slow = slow.next;	
		}
		ListNode secHead = slow.next;
		slow.next = null;	
	}
	
    public ListNode SortedMerge(ListNode node1, ListNode node2){

    	ListNode result = null;
        if(node1 == null)
            return node2;
        if(node2 == null)
            return node1;
        
        if(node1.val < node2.val){
            result = node1;
            result.next = SortedMerge(node1.next, node2);
        }else{
            result = node2;
            result.next = SortedMerge(node1, node2.next);
        }
        return result;
    }    
}
