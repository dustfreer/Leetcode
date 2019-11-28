package leetcode;

public class ListNode {
	int val;
	ListNode next;

	ListNode(int x) {
		val = x;
	}

	public static ListNode breakIntoHalf(ListNode head) {
		if (head == null)
			return null;
		if (head.next == null)
			return null;

		ListNode mid = head;
		ListNode temp = head;
		while (temp != null && temp.next != null) {
			temp = temp.next.next;
			mid = mid.next;				
		}
		return mid;
	}
	public static void PrintList(ListNode head){	
		ListNode temp = head;
	    while(temp != null){	    
	    	System.out.print(temp.val + "-> ");	            
	    	temp = temp.next;
	    }
	    System.out.println("NULL");	
	}
	
	
	public static ListNode partition(ListNode head, int x) {
        if( head == null || head.next == null)
            return head;
        
        ListNode temp = head;        
        temp = reverse(temp);  
        ListNode result = temp;
        while ( temp != null){
            if ( temp.val != x)
                temp = temp.next;
            else 
                break;
        }
            
        ListNode temp1 = temp;
        ListNode pre = new ListNode(-1);
        pre.next = result;
        ListNode result1 = pre;
        while ( result != null && result.val != x){
            if ( result.val < x){
                pre.next = pre.next.next;
                
                while( temp != null && temp.next != null){
                    if ( result.val > temp.next.val){
                        break;
                    }
                    temp = temp.next;
                }
                if ( temp.next != null) {               
                    result.next = temp.next;
                    temp.next = result;
                }else{
                    temp.next = result;
                    result.next =null;
                }
                result = pre.next;
                temp = temp1;
            }else{
                result = result.next;
                pre = pre.next;
            }
            
        }
        if ( result1.val == -1)
        	result1 = result1.next;
        result1 = reverse(result1);
        return result1;
        
        
    }
    
    public static ListNode reverse(ListNode head){
        ListNode back = null;
        ListNode front = head;
        ListNode mid;
        
        while( front != null){
            mid = front.next;
            front.next = back;
            back = front;
            front = mid;
        }
        
        return back;
    }
	
	
	public static void main(String[] args) {
	ListNode node1 = new ListNode(1);	    
	node1.next = new ListNode(4);	    
	node1.next.next = new ListNode(3);	    
	node1.next.next.next = new ListNode(2);
	node1.next.next.next.next = new ListNode(5);
	node1.next.next.next.next.next = new ListNode(2);
	
	ListNode node2 = new ListNode(1);	    
	node2.next = new ListNode(1);	    
	//node2.next.next = new ListNode(3);	    
	//node2.next.next.next = new ListNode(4);
	//node2.next.next.next.next = new ListNode(1);
	PrintList(partition(node2, 2));
	}
}
