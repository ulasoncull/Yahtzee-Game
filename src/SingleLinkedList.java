
public class SingleLinkedList {
	Node head;
	int countOfYahtzee;
	int countOfLarge;
	int score;
	String Sscore;
	public void setScore(int data) {
		this.score = data;
	}
	public void setSscore(int data) {
		this.Sscore = String.valueOf(data);
	}
	public void setYahtze(int data) {
		this.countOfYahtzee = data;
	}
	public void setLarge(int data) {
		this.countOfLarge = data;
	}
	public void add(Object data) {
		if (head == null) {
			Node newNode = new Node(data);
			head = newNode;
		} else {
			Node temp = head;
			while (temp.getLink() != null) {
				temp = temp.getLink();
			}
			Node newNode = new Node(data);
			temp.setLink(newNode);
		}
	}


	public int size() {
		if (head == null)
			return 0;
		else {
			int count = 0;
			Node temp = head;
			while (temp != null) {
				temp = temp.getLink();
				count++;
			}
			return count;
		}
	}

	public void display() {
		if (head == null)
			System.out.println(" ");
		else {
			Node temp = head;
			while (temp != null) {
				System.out.print(temp.getData() + " ");
				temp = temp.getLink();
			}
		}
	}
	public void displayTable() {
		if (head == null)
			System.out.println(" ");
		else {
			Node temp = head;
			while (temp != null) {
				System.out.println(temp.getData() + " ");
				temp = temp.getLink();
			}
		}
	}


	public void remove(Object dataToDelete) {
		if (head == null)
			System.out.println("Linked List is empty");
		else {
			while ((Integer) head.getData() == (Integer) dataToDelete) {
				head = head.getLink();
			}
			Node temp = head;
			Node previous = null;
			while (temp != null) {
				if ((Integer) temp.getData() == (Integer) dataToDelete) {
					previous.setLink(temp.getLink());
					temp = previous;
				}
				previous = temp;
				temp = temp.getLink();
			}
		}
	}

	public int findMax() {
		if (head == null) {
			System.out.println("The Linked List is empty");
			return Integer.MIN_VALUE;
		} else {
			int maxVal = Integer.MIN_VALUE;
			Node temp = head;
			while (temp != null) {
				if ((int) temp.getData() > maxVal) {
					maxVal = (int) temp.getData();
				}
				temp = temp.getLink();
			}
			return maxVal;
		}
	}

	public boolean search(Object data) {
		if (head == null) {
			System.out.println("List is empty");
			return false;
		} else {
			Node temp = head;
			while (temp != null) {
				if ((Integer) temp.getData() == (Integer) data)
					return true;
				temp = temp.getLink();
			}
			return false;
		}
	}
	public void removeJustOneElement(Object data) {
		if (head == null)
			System.out.println("Linked List is empty");
		else {
			boolean flag = true;
			while ((Integer) head.getData() == data) {
				head = head.getLink();
				flag = false;
				break;
			}
			if(flag) {
				Node temp = head;
				Node previous = null;
				while (temp != null) {
					if ((Integer) temp.getData() == (Integer) data) {
						previous.setLink(temp.getLink());
						temp = previous;
						flag = false;
					}
					if(!flag)
						break;
					previous = temp;
					temp = temp.getLink();
			 }
			
				
			}

		}
	}
	// Java program to sort a Linked List using Bubble Sort

	

}
