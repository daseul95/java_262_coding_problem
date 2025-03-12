public class FreePratice {
    public static void main(String[] args) {
        System.out.println("연습장입니다.");
    }

    //p는 practice의 약자 
    //여러번 쓰면서 생각하면서 외우기 

    /*정수에서 1로 세팅된 비트의 개수를 찾는 프로그램 */
    public static short countBits(int x){
        short numBits=0; 
        while(x!=0){
            numBits += (x&1); 
            x>>>=1; 
        } 
        return numBits;
    }

    public static short pCountBits(int x){
        short numBits=0;
        while(x!=0){
            numBits+=(x&1);
            x>>>=1;
        }
        return numBits;
    }
    
}
