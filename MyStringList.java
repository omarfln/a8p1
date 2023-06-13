import java.util.Arrays;

public class MyStringList {
    int INITIAL_SIZE=2;
    private int size;
    private Person [] currentArray;

    MyStringList() {
        currentArray = new Person [INITIAL_SIZE];
        size = 0;
    }

    public void add(Person s){
        if (s==null) return;
        if (size == currentArray.length) resize();
        currentArray[size++] = s;
    }

    public void resize(){
        Person [] tmp = new Person [currentArray.length*2];
        System.arraycopy(currentArray,0, tmp, 0, size);
        currentArray=tmp;
    }

    public Person get(int i){
        if(i<0 || i > currentArray.length-1) return null;
        return currentArray[i];
    }

    public boolean find (String s){
        if (s==null) return false;
        for( Person test: currentArray){
            if (test!=null){
                if (test.getLast().equals(s)){
                    return true;
                }
            }
        }
        return false;
    }

    public void insert(Person s, int pos){
        if (pos> size || pos<0) return ;
        if (pos == currentArray.length || size+1 == currentArray.length){
            resize();
        }
        Person [] tmp = new Person [currentArray.length];
        System.arraycopy(currentArray, 0, tmp, 0, pos);
        tmp[pos] = s;
        System.arraycopy(currentArray, pos, tmp, pos+1, currentArray.length-pos-1 );
        currentArray = tmp;
        ++size;
    }

    public boolean remove(Person s){
        if(s==null|| size==0) return false;
        int index = -1;
        for(int i =0; i< currentArray.length;i++){
            if (currentArray[i].equals(s)){
                index=i;
                break;
            }
        }
        if (index==-1) return false;
        Person [] tmp = new Person[currentArray.length];
        System.arraycopy(currentArray, 0, tmp, 0, index);
        System.arraycopy(currentArray, index+1, tmp, index, currentArray.length-(index+1));
        currentArray = tmp;
        --size;
        return true;
    }

    @Override
    public  String toString(){
        StringBuilder sb = new StringBuilder("[");
        for (int i =0; i< size-1;++i){
            sb.append(currentArray[i] + ", ");
        }
        sb.append(currentArray[size-1] + "]");
        return sb.toString();
    }

    public int size(){return size;}
    public boolean isEmpty(){return size==0;}
    public Object clone(){
        Person[] tmp = Arrays.copyOf(currentArray,size);
        return tmp;
    }


    public static void main (String [] args){
        Person p = new Person("last1", "First1", 22);
        Person p2 = new Person("Last2", "First2", 32);
        Person p3 = new Person("Last3", "First3", 42);
        Person p4 = new Person("Last4", "First4", 52);
        Person p5 = new Person("Last5", "First5", 62);

        MyStringList l = new MyStringList();
        l.add(p);
        l.add(p2);
        l.add(p3);
        l.insert(p4, 3);
        l.insert(p5, 4);
        boolean ret = l.remove(p);
        System.out.println(ret);
        boolean found = l.find("Last4");
        if (found) System.out.println("Person with Last name Last4 found");
        boolean found2 = l.find("Last42");
        if (found2) System.out.println("Person with Last name Last42 found");
        System.out.println(l.size());
        System.out.println(l.currentArray.length);
        Person [] cl = (Person[]) l.clone();
        System.out.println("\nArray in string format:\n"+ l.toString());
        System.out.println("\n\nCloned array in string format:\n"+Arrays.toString(cl));

    }
}

