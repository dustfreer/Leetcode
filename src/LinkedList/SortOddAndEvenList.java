package LinkedList;

public class SortOddAndEvenList {		    
    static class ListNode {
        int val;
        ListNode next;
        ListNode (int val) {
            this.val = val;
        }
    }
    
    public ListNode sortListNode(ListNode head) {
        // corner case
        if (head == null || head.next == null) {
            return head;
        }
        // 1->8->2->7->3->6->4->5->null
        
        ListNode oddNode = new ListNode(-1);
        ListNode oddTemp = oddNode;
        ListNode evenNode = new ListNode(-1);
        ListNode evenTemp = evenNode;
        
        boolean isOdd = true;
        while (head != null) {
            if (isOdd) {
                oddNode.next = new ListNode(head.val); //1->2->3->4->null;
                oddNode = oddNode.next;
            }else {
                evenNode.next = new ListNode(head.val); //8->7->6->5->null;
                evenNode = evenNode.next;                
            }
            head = head.next;
            isOdd = !isOdd;
        }
        
        //reverse evenNode;
        evenNode = reverse(evenTemp.next);
        
        return mergeTwoListNode(oddTemp.next, evenNode);
    }
    
    public ListNode mergeTwoListNode(ListNode head1, ListNode head2) {
        ListNode resultNode = new ListNode(-1);
        ListNode temp = resultNode;
        while (head1 != null && head2 != null) {
            if (head1.val < head2.val) {
                temp.next = head1;
                head1 = head1.next;
            }else {
                temp.next = head2;
                head2 = head2.next;                
            }
            temp = temp.next;
        }
        if (head1 != null) {
            temp.next = head1;
        }
        if (head2 != null) {
            temp.next = head2;
        }
        return resultNode.next;
    }
    
    public ListNode reverse(ListNode head) {
        
        //      8->   7 ->6->5->null;
        // pre  cur temp
        // null <-pre<-cur<- temp;
        // null <-8<-7<-6
         
        ListNode pre = null;
        ListNode cur = head;
        while (cur != null) {
            ListNode temp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = temp;
        }
        return pre;
    }
    
    public static void main(String[] args) {
	    
	      ListNode test = new ListNode(1);
	      test.next = new ListNode(8);
	      test.next.next = new ListNode(2);
	      test.next.next.next = new ListNode(7);
	      test.next.next.next.next = new ListNode(3);
	      test.next.next.next.next.next = new ListNode(6);
	      test.next.next.next.next.next.next = new ListNode(4);      
	      test.next.next.next.next.next.next.next = new ListNode(5);
	      
	      SortOddAndEvenList sort = new SortOddAndEvenList();
	      test = sort.sortListNode(test);
	      
	      while (test != null) {
	          System.out.print(test.val+" ");
	          test = test.next;
	      }
	    
	  }
    
}
