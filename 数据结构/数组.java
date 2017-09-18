public class Main{
    public static void main(String args[]){
        Array arr = new ReverseArray(3);
        System.out.println(arr.add(34));
        System.out.println(arr.add(5));
        System.out.println(arr.add(54));
        int [] temp = arr.getData();
        for(int x: temp){
            System.out.println(x);
        }
    }
}


class Array{
    private int data[]; //定义一个数组
    int foot;
    public Array(int len){ //大小
        if(len > 0){ //至少有元素
            this.data = new int[len];
        }else{
            this.data = new int[1]; //一个元素的大小
        }
    }
    public boolean add(int num){
        if(this.foot < this.data.length) { //有空间保存
            this.data[foot++] = num;
            return true;
        }
        return false; //保存失败
    }
    public int[] getData(){
        return this.data;
    }
}

//定义一个排序数组子类
class SortArray extends Array{
    public SortArray(int len){
        super(len);
    }
    public int[] getData(){ //核心
        java.util.Arrays.sort(super.getData());
        return super.getData();
    }

}



//反转类
class ReverseArray extends Array{
    public ReverseArray(int len){
        super(len);
    }
    public int[] getData(){
        int center = super.getData().length / 2;
        int head = 0;
        int tail = super.getData().length - 1;
        for(int x = 0;x<center;x++){
            int temp = super.getData()[head];
            super.getData()[head] = super.getData()[tail];
            super.getData()[tail] =temp;
            head ++ ;
            tail -- ;
        }
        return super.getData();
    }

}
