public class classwork
{
  public static void main(String[] args)
  {
    HashTable<Word> table = new HashTable<Word>();
    String[] data="one two dog cat chicken pig owl jump run hop shortcut ferret goat hootowl owl go alligator onimonapia food a".split(" ");
    for(String s : data) {
      table.add(new Word(s));
    } 
    
    System.out.println(table);
    
    System.out.println("Contains goat: "+table.contains(new Word("goat")));
    System.out.println("Contains car: "+table.contains(new Word("car")));
    
    table.remove(new Word("goat"));
    table.remove(new Word("jump"));
    
    System.out.println(table);

  }
}

class HashTable<E> {
  private DLList<E>[] table;
  
  @SuppressWarnings("unchecked")
  public HashTable() {
    table =  new DLList[10];
    for(int i = 0;i < 10;i++) {
      table[i] = new DLList<E>();
    }
  }
  public void add(E data)  {
    int index = data.hashCode()%10;
    if(!table[index].contains(data)) {
        table[index].add(data);
    }
  }
  
  public void remove(E a)
  {
    for(int i = 0;i < 10;i++)
    {
      if(table[i].indexOf(a) != -1)
      {
          for(int j = 0;j < table[i].size();j++) {
              if(table[i].get(j).toString().equals(a.toString()))
                  table[i].remove(j);
          }
      }
    }
  }
  
  public boolean contains(E data) {
    for(int i = 0;i < 10;i++) {
      if(table[i].contains(data))  {
        return true;
      }
    }
    return false;
  }
  public String toString() {
    String out = "";
    for(int i = 0;i < 10;i++) {
      out += "Bucket "+i+": "+table[i].toString()+"\n";
      
    }
    return out;
  }
}

class Word
{
  String data;
  char[] vowels;
  public Word(String s)
  {
    data=s;
    vowels= new char[]{'a','e','i','o','u'};
  }
  
  public int hashCode()
  {
    int numVowels=0;
    for(int i=0;i<data.length();i++)
    {
      for(int j = 0;j < vowels.length;j++) {
          if(vowels[j] == data.charAt(i)) {
              numVowels++;
            break;
        }
      }
     
    }
    int hash=(int)(numVowels*data.length())%10;
    return(hash);
  }
  public String toString()  {
      return data;
  }
}

class Node<E> {
	private E data;
	private Node<E> next;
	private Node<E> prev;

	public Node(E a)
	{
		data=a;
		next=null;
		prev=null;
	}

	public E get()
	{
		return(data);
	}

	public Node<E> next()
	{
		return(next);
	}

	public Node<E> prev()
	{
		return(prev);
	}

	public void setNext(Node<E> a)
	{
		next=a;
	}

	public void setPrev(Node<E> a)
	{
		prev=a;
	}
}

class DLList<E>
{
	private Node<E> head;
	private Node<E> tail;
	private int size;
	public DLList()
	{
		head=new Node<E>(null);
		tail=new Node<E>(null);
		
		head.setNext(tail);
		tail.setPrev(head);
		
		size=0;
	}
	
	private Node<E> getNode(int index)
	{
		int i=index;
		Node<E> curr=null;
		if(i<size)
		{
  		if(i<size/2)
  		{
  			curr=head.next();
  			while(i>0)
  			{
  				curr=curr.next();
  				i--;
  			}
  		}
  		else if(i>=size/2)
  		{
  			i=size-index;
  			curr=tail.prev();
  			while(i>1)
  			{
  				curr=curr.prev();
  				i--;
  			}
  		}
		}
		return(curr);
	}
	
	public void add(E a)
	{
		Node<E> newNode=new Node<E>(a);
        Node<E> before=tail.prev();
        before.setNext(newNode);
        tail.setPrev(newNode);
        newNode.setNext(tail);
        newNode.setPrev(before);
		size++;
	}
	
	public boolean contains(E a)
	{
	    Node<E> curr=head.next();
	    while(curr!=tail)
	    {
	        if(curr.get().toString().equals(a.toString()))
	        {
	            return(true);
	        }
	        curr=curr.next();
	    }
	    return(false);
	}
	
	public E get(int index)
	{
		return(this.getNode(index).get());
	}
	
	public int indexOf(E a)
	{
        if(size == 0)
            return -1;
		Node<E> curr=head.next();
		int index=0;
		while(index<size)
		{
		  if(this.get(index).equals(a))
		  {
			return(index);
		  }
		  index++;
		}
		return(index);
	}
	
	public void remove(E a)
	{
		this.remove(indexOf(a));
	}
  
	public void remove(int i)
	{
		Node<E> curr=this.getNode(i);
		//E data=curr.get();
		curr.prev().setNext(curr.next());
		curr.next().setPrev(curr.prev());
		size--;
		//return(data);
	}
  
	public void set(int i,E a)
	{
		Node<E> curr=this.getNode(i);
		Node<E> currNext=curr.next();
		Node<E> currPrev=curr.prev();
		curr=new Node<E>(a);
		curr.setNext(currNext);
		curr.setPrev(currPrev);
		curr.next().setPrev(curr);
		curr.prev().setNext(curr);
	}
  
	public int size()
	{
		return(size);
	}
	
	public String toString()
	{
		String msg="";
		
		Node<E> curr=head.next();
		while(curr!=tail)
		{
			msg+=curr.get()+" ";
			curr=curr.next();
		}
		
		if(size>0)
		{
			return(msg.substring(0,msg.length()-1));
		}
		return(msg.substring(0,msg.length()));
	}
}