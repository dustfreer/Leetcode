package LinkedList;

public class CopyRandomList {
	class Node {
	    public int val;
	    public Node next;
	    public Node random;

	    public Node() {}

	    public Node(int _val,Node _next,Node _random) {
	        val = _val;
	        next = _next;
	        random = _random;
	    }
	}
	
	public Node copyRandomList(Node head) {            
    	if ( head == null) {
    		return null;    			
    	}
    	
		Node oriHead = head;
		while (oriHead != null) {
			Node add = new Node(oriHead.val, null, null);
			add.next = oriHead.next;
			oriHead.next = add;
			oriHead = oriHead.next.next;
		}

		Node tempHead = head;
		Node addHead = head;
		addHead = addHead.next;
		while (tempHead != null) {
            if ( tempHead.random != null){
			    addHead.random = tempHead.random.next;
            }else{
                addHead.random = null;
            }
                
			if ( addHead.next == null)
				break;
			addHead = addHead.next.next;
			tempHead = tempHead.next.next;
		}

		Node oriResult = head;
		Node result = head;
		result = result.next;
		Node result1 = result;
		while (oriResult != null) {
			oriResult.next = result.next;
			oriResult = oriResult.next;
			if ( oriResult != null) {
				result.next = oriResult.next;
				result = result.next;
			}
		}
		return result1;
    }
	
}
