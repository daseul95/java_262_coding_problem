
public class PrimativeDataType {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
    }

    /*정수에서 1로 세팅된 비트의 개수를 찾는 프로그램 */
    //한번에 한 비트씩 테스트
    public static short countBits(int x){
        short numBits=0; // 1의 개수를 저장하는 역활
        while(x!=0){
            numBits += (x&1); //x의 가장 오른쪽 비트가 1인지 확인하는 연산
            x>>>=1; //x를 오른쪽으로 1비트 이동 (>>>는 부호 비트까지 이동시킴 음수일 경우에도 0을 채움)
           /* 

           >> (부호 유지 우측 시프트)
            왼쪽 빈 공간을 원래 부호 비트(MSB)(맨왼쪽,최상위비트) 값으로 채움
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


    /* 비트 스왑  */
    public static long swapBits(long x,int i,int j){
        if(((x>>>i)&1)!=((x>>>j)&1)){   //i번째 비트와 j번째 비트가 다르다면, 각 비트를 뒤집어서 스왑을 구현
            long bitMask = (1L<<i)|(1L<<j);   //bitMask을 사용해서 뒤집을 비트를 선택, 1L을 십진수 1을 뜻함 (0001)
            x ^= bitMask;                     //x=1일때 x^=0을 만족하고
                                              //x=0일때 x^=1을 만족하므로,
                                              //XOR을 사용해서 비트를 뒤집을 수 있음      
        }

        return x;
    }

    /*비트 뒤집기*/
    //역순 연산을 필요로 하는 경우에 가장 효율적인 방법은 미리 16비트 숫자에 대한 룩업테이블 A를 만들어 놓는것
    //미리 계산된 16비트 반전값을 활용하여 64비트 숫자를 빠르게 반전하는 방식
    public static int[] precomputedRevers = new int[65536];

    public static long revserBits(long x){     // x는 64비트임
        final int WORD_SIZE=16;               //16비트씩 단위 처리
        final int BIT_MASK=0xFFFF;           //하위 16비트만 추출하는 마스크
        return precomputedRevers[(int)(x&BIT_MASK)]<<(3*WORD_SIZE)          // 64비트 숫자를 16비트 단위로 분할하여 변환
                                                                            // x & BIT_MASK → x의 하위 16비트를 추출 
                                                                            // precomputedRevers[...] → 해당 16비트의 반전값을 조회 
                                                                            // << (3 * WORD_SIZE) = << 48 → 맨 왼쪽(상위 16비트)로 이동

        | precomputedRevers[(int)((x>>>WORD_SIZE)&BIT_MASK)]<<(2*WORD_SIZE) // x >>> WORD_SIZE → 16비트 오른쪽으로 이동하여 두 번째 16비트를 추출
                                                                            // & BIT_MASK → 하위 16비트만 유지
                                                                            // precomputedRevers[...] → 해당 16비트의 반전값을 조회
                                                                            // << (2 * WORD_SIZE) = << 32 → 48비트 중 두 번째(32~47비트)에 배치

        | precomputedRevers[(int)((x>>>(2*WORD_SIZE))&BIT_MASK)]<<WORD_SIZE //같은 방식으로 나머지 두 블록도 처리.
        | precomputedRevers[(int)((x>>>(3*WORD_SIZE))&BIT_MASK)];
        
    }

    // precomputedRevers 배열을 직접 보여주면 길이가 65536개이므로, 이를 생성하는 코드
    static {                                                //static블록은 클래스가 로드될때 한번만 실행됨됨
        for (int i = 0; i < 65536; i++) {
            precomputedRevers[i] = reverse16Bits(i);        // 각 숫자의 비트를 반전한 값을 precomputedReverse 배열에 저장함
                                                            // 비트 반전 작업은 reverse16Bits(i)함수에서 수행됨
        }                                                   // precomputedRevers[i]는 i의 16비트 비트 반전 결과를 저장한 배열이 됨
    }
    
    private static int reverse16Bits(int x) {
        int result = 0;                                     //반전된 비트를 저장할 변수
        for (int i = 0; i < 16; i++) {
            result |= ((x >>> i) & 1) << (15 - i);          //맨 오른쪽(최하위비트)가 i번째 비트가 되도록함 
                                                            // & 연산을 통해 i번째 비트 값을 가져옴(0 또는 1)
                                                            // <<(15 - i) 가져온 비트를 반대쪽으로 이동
                                                            // 오른쪽에서 i번째 비트를 왼쪽에서 (15-i)번째 위치로 보냄
                                                            // result 변수에 반전된 비트를 or(|=)연산으로 저장

        }
        return result;                                      
    }



    /* 같은 무게를 가진 가장 가까운 정수 찾기 (무게는 2진수로 표현했을때 1의 개수) */
    //연속 된 비트 중 서로 다른 두 개를 찾고, 이를 바꿔줌으로서 가장 작은 차이를 가지는
    //정수를 찾는 방식
    public static long closestIntSameBitCount(long x){
        final int NUM_UNSIGNED_BITS = 63; //x가 음이 아닌 정수임을 가정해서 맨 앞 비트는 0이다.
                                          //그래서 63개의 최하위 비트만 비교하면 된다.

        for(int i=0;i<NUM_UNSIGNED_BITS-1;++i){ 
            if(((x>>>i)&1)!=((x>>>(i+1))&1)){   // 연속된 두 비트가 다르면 
                x^=(1L<<i) | (1L<<(i+1));       // 두 비트를 스왑(XOR 연산)
                return x;
            }
        }
        throw new IllegalArgumentException("All bits are 0 or 1");
    } 
    
    /*  곱셈과 덧셈 없이 x X y 계산하기 */
    // x * y 연산자를 사용하지 않고,
    // 비트 시프트(Shift)와 덧셈(Addition) 연산만을 이용하여 곱셈을 수행
    public static long multiply(long x,long y){
        long sum=0;
        while(x!=0){  
            if((x&1)!=0){          // x의 최하위 비트가 1이면
                sum = add(sum,y);  // y를 sum에 더함
            }
            x>>>=1;               // x를 오른쪽으로 한 비트 이동 (x를 2로 나눔)
            y<<=1;                // y를 왼쪽으로 한 비트 이동 (y를 2배로 증가)
        }
        return sum;
    }

    /*
     * 곱셈을 수행하는 핵심 아이디어는 "이진수 곱셈"입니다.
     * 일반적인 곱셈 연산을 이진수로 표현하면 다음과 같습니다.
     * 예를 들어, 13 * 6을 계산한다고 가정하겠습니다.
     * 
     *    1101    (13)
        x 0110    (6)
       ------------
           0000    (6의 0번째 비트)
        + 11010    (6의 1번째 비트, 13을 왼쪽으로 한 칸 이동)
       + 110100    (6의 2번째 비트, 13을 왼쪽으로 두 칸 이동)
       ------------
         011110   (78, 즉 13 * 6)
       즉, 각 비트가 1인 위치에서 y 값을 왼쪽으로 이동한 값을 더하는 방식으로 곱셈을 수행합니다.
     */


    private static long add(long a, long b){
        long sum=0, carryin=0, k=1, tempA=a, tempB=b;
        while(tempA!=0||tempB!=0){
            long ak=a&k,bk=b&k;
            long carryout=(ak&bk)|(ak&carryin)|(bk&carryin);
            sum |= (ak^bk^carryin);
            carryin = carryout<<1;
            k<<=1;
            tempA >>>=1;
            tempB>>>=1;
        }

        return sum|carryin;
    }

      /*
        비트 연산을 이용한 덧셈 원리       
            A	B	Carry-in	Sum (A ⊕ B ⊕ Carry-in)	Carry-out
            0	0	0	         0	                     0
            0	1	0	         1	                     0
            1	0	0	         1	                     0
            1	1	0	         0	                     1
            0	0	1	         1                       0
            0	1	1	         0                  	 1
            1	0	1	         0                     	 1
            1	1	1	         1                  	 1
        이 원리를 그대로 구현한 것이 add 함수입니다.
     */

 


    
}

