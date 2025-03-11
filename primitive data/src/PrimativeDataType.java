
public class PrimativeDataType {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
    }

    //정수에서 1로 세팅된 비트의 개수를 찾는 프로그램
    //최하위 비트부터 시작해서 한번에 한 비트씩 테스트
    public static short countBits(int x){
        short numBits=0; // 1의 개수를 저장하는 역활
        while(x!=0){
            numBits += (x&1); //x의 가장 오른쪽 비트가 1인지 확인하는 연산
            x>>>=1; //x를 오른쪽으로 1비트 이동 (>>>는 부호 비트까지 이동시킴 음수일 경우에도 0을 채움)
           /* 

           >> (부호 유지 우측 시프트)
            왼쪽 빈 공간을 원래 부호 비트(MSB) 값으로 채움
            즉, 음수는 음수로 유지됨

            >>> (부호 없는 우측 시프트)
            왼쪽 빈 공간을 무조건 0으로 채움
            즉, 음수도 양수처럼 변할 수 있음      */
                
        } 
        return numBits;
    }


    /* 패리티 계산하기
        패리티(parity)는 1이 홀수 개이면 1, 짝수 개이면 0이 된다. 오류 검사 값으로
        전체 비트의 개수를 2로 나눈 나머지 값과 같음
     */
    public static short parity(long x){
        short result = 0;
        while(x!=0){
            result ^= (x&1); /* ^= 는 XOR(배타적 논리합)연산 서로 같으면 0,서로 다르면 1 
                                 즉 1의 개수가 홀수면 1, 짝수면 0이 됨*/
            x>>>=1;
        }
        return result;
    }


}

