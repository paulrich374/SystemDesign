
// https://github.com/tdeitch/jrope/blob/master/Rope.java


/*
 * CharSequence Interface in Java
n Java 1.4, both the String and the StringBuffer classes implements java.lang.CharSequence interface, which is a standard interface for querying the length of and extracting characters and subsequences from a readable sequence of characters
A CharSequence is a readable sequence of char values. This interface provides uniform, read-only access to many different kinds of char sequences.
Examples

char charAt(int index)
int length()
CharSequence subSequence(int start, int end)
String toString()
 
 * */
public class Rope implements CharSequence {

	
    String data;
    Rope left;
    Rope right;
    int leftLen;

    
    public Rope(String data) {
        this.data = data;
        this.leftLen = data.length();
    }

    
    public Rope(Rope left, Rope right) {
        this.left = left;
        this.right = right;
        this.leftLen = length(left);
    }

    
    
    public int length() {
        return length(this);
    }
    public int length(Rope r) {
        int len = 0;
        for (; r != null; r = r.right) {
            len += r.leftLen;
        }
        return len;
    }

    
    
    public Rope concat(Rope r) {
       return concat(this, r);
    }
    public Rope concat(Rope one, Rope two) {
        if (one == null) {
            return two;
        } else if (two == null) {
            return one;
        }
        return new Rope(one, two);
    }


    
    public char charAt(int i) {
        return charAt(this, i);
    }    
    private char charAt(Rope node, int i) {
        if(node.left == null) {
            assert i >= 0 && i < node.leftLen;
            return node.data.charAt(i);
        }

        if(node.leftLen > i) {
            return charAt(node.left, i);
        }
        else {
            return charAt(node.right, i - node.leftLen);
        }
    }


    
    public Pair<Rope> split(int index) {
        return split(this, index);
    }
    public Pair<Rope> split(Rope nd, int index) {
        if (nd.left == null) {
            assert index >= 0 && index <= nd.leftLen;
            Pair<Rope> nodes = new Pair<Rope>();
            if (index == 0) {
                nodes.one = null;
                nodes.two = nd;
            } else if (index == nd.leftLen) {
                nodes.one = nd;
                nodes.two = null;
            } else {
                nodes.one = new Rope(nd.data.substring(0, index));
                nodes.two = new Rope(nd.data.substring(index, nd.leftLen));
            }
            return nodes;
        }
        else if (index == nd.leftLen) {
            return new Pair<Rope>(nd.left, nd.right);
        } else if (index < nd.leftLen) {
            Pair<Rope> pair = split(nd.left, index);
            return new Pair<Rope>(pair.one, concat(pair.two, nd.right));
        } else {
            Pair<Rope> pair = split(nd.right, index - nd.leftLen);
            return new Pair<Rope>(concat(nd.left, pair.one), pair.two);
        }
    }

    
    
    public Rope subSequence(int start, int end) {
        Pair<Rope> sp1 = split(start);
        Pair<Rope> sp2 = sp1.two.split(end - start);
        return sp2.one;
    }

    public Rope insert(Rope r, int index) {
        Pair<Rope> pair = this.split(index);
        return concat(concat(pair.one, r), pair.two);
    }

    public String toString() {
        if(left == null) return data;
        return left.toString() + right.toString();
    }
    
    
    public static void main(String[] args) {
        Rope r = new Rope("Hi rope!");
        Rope r2 = new Rope("Hi rope2!");
        Rope r3 = r.concat(r2);
        
        System.out.println("Concatenation");
        System.out.println("\t" + r3);
        System.out.print("charAt\n\t");
        for(int i = 0; i < r3.length(); i++) {
        	System.out.print(r3.charAt(i));
        }
        System.out.println("\nSplitting");
        for(int i = 0; i <= r3.length(); i++) {
            Pair<Rope> pair = r3.split(i);
            if (pair.one != null) {
                System.out.println("\t" + pair.one + "\t\tlen: " + pair.one.length());
            }
            if (pair.two != null) {
                System.out.println("\t" + pair.two + "\t\tlen: " + pair.two.length() + "\n");
            }
        }
        System.out.println("\nSub-sequence");
        for(int i = 0; i <= r3.length(); i++) {
            for (int j = i; j <= r3.length(); j++) {
                if (i != j) {
                    System.out.println("\t" + r3.subSequence(i,j));
                } else {
                    System.out.println("\t");
                }
            }
        }
        System.out.println("\nInsert");
        System.out.println(r2.insert(r, 3));
    }    
    
}
class Pair<T> {
    T one;
    T two;

    public Pair() {
        this.one = null;
        this.two = null;
    }

    public Pair(T one, T two) {
        this.one = one;
        this.two = two;
    }
}