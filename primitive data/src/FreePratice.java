/* 코드 연습장 */

public class FreePratice {
    public static void main(String[] args) {
        System.out.println("연습장입니다.");
        System.out.println(revserBits(1));
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

    public static short p2CountBits(int x){
        short numBits=0;
        while(x!=0){
            numBits+=(x&1); // 0001을 and(&) 함으로서 x가 무슨 값이든 최하위(맨오른쪽)값만 비교해서 0000또는 0001나옴
                            // 1이나올경우에는 원래 수에 1씩 증가해서 저장함
            x>>>=1;         // >>>연산자를 이용해서 비트를 오른쪽으로 한칸씩 이동
                            // 0110 이면 >>>= 연산자를 이용해서 0011로 이동되는 형식
        }
        return numBits;    // 1의 갯수를 반환함
    }
    

    /* 패리티 계산 */
    public static short parity(long x){
        short result = 0;
        while(x!=0){
            result ^= (x&1); /* ^= 는 XOR(배타적 논리합)연산 서로 같으면 0,서로 다르면 1 
                                 즉 1의 개수가 홀수면 1, 짝수면 0이 됨*/
            x>>>=1;
        }
        return result;
    }

    public static short pParity(long x){
        short result = 0;
        while(x!=0){
            result^=(x&1); // x의 최하위 비트(맨오른쪽)를 0001과 비교하기 
                           // result 는 0부터 시작함으로 x의 값이 1일때 1
                          //                             x의 값이 0일때 0 
                           // 다음 값으로는 result =1 일때 최하위 비트가 0일때 1
                          //                               최하위 비트가 1일때 0
                          // 식으로 이어짐 
                          // 전체적으로 패리티 값은 1의 갯수가 홀수일때 1이고
                          //                                   짝수일때 0인 특징을 가짐

            x>>>=1;
        }
        return result;       // x=7일경우 (0111)이므로 1의 개수가 홀수이므로 result 값은 1을 가질거임 
    }

    public static short p2Parity(long x){
        short result=0;
        while(x!=0){
            result^=(x&1);
            x>>>=1;
        }
        return result;
    }

        /* 비트 스왑  */
        public static long swapBits(long x,int i,int j){
            if(((x>>>i)&1)!=((x>>>j)&1)){   //i번째 비트와 j번째 비트가 다르다면, 각 비트를 뒤집어서 스왑을 구현0
                long bitMask = (1L<<i)|(1L<<j);   //bitMask을 사용해서 뒤집을 비트를 선택,
                x ^= bitMask;                     //x=1일때 x^=0을 만족하고
                                                  //x=0일때 x^=1을 만족하므로,
                                                  //XOR을 사용해서 비트를 뒤집을 수 있음      
            }
            return x;
        }

        public static long pSwapBits(long x ,int i, int j){ //값이 pSwapBits(7,1,3) 라면 7=(0111)이고
                                                            // 번호는 3 2 1 0 이런 순이다.   
            if(((x>>>i)&1)!=((x>>>j)&1))                    // x>>>1 이면 1이 최하위 비트로 놓이게 되고 이걸 1로 and 비교 = 1
                                                            // x>>>3 이면 0이 최하위 비트로 놓이게 되고 이걸 1로 and 비교 = 0 
                                                            // 1 != 0 은 true 이므로 1으로 if문 구동됨

            {long bitMask = (1L<<i)|(1L<<j);                // bitMask는 0001을 왼쪽으로 1만큼 그리고 왼쪽으로 3만큼 이동한걸
                                                            // or 계산해서 얻어낸다. 0010 + 1000 이므로 bitMask=1010임
            x^=bitMask;                                     //x=(0111) 이고 bitMask=(1010)을 XOR 계산해서 x에 대입하면
                                                            //x=(1101)이 됨 
            }   
            return x;                                      //x=13 이됨 
        } 

        public static long p2SwapBits(long x,int i,int j){
            if(((x>>>i)&1)!=((x>>>j)&1)){         // 같지 않으므로 스왑해야 한다. 
                long bitMask = (1L<<i)|(1L<<j);   // 결과는 i자리과 j자리에 1이 채워지고 나머지는 0이 채워짐
                x^=bitMask;                       // i와 j자리에 1이있는 곳에 XOR연산이므로 
                                                  //i와 j자리에 1이있으면 0으로 스왑(1,1->false)되고 
                                                  //            0이 있으면 1로 스왑(1,0->true)됨 
            } 

            return x;                              //스왑된 x를 반환함
        }

        public static long p3SwapBits(long x,int i,int j){
            if(((x>>>i)&1)!=((x>>>j)&1)){
                long bitMask = (1L<<i)|(1L<<j);
                x^=bitMask;
            }
            return x;
        }

            


          /*비트 뒤집기*/
            public static long revserBits(long x){    //x는 64비트임
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
            
          public static final int BIT_SIZE = 16;
          public static final int ARRAY_SIZE = 1 << BIT_SIZE; // 65536 자동 계산 , 2^16승
          
          public static int[] precomputedRevers = new int[ARRAY_SIZE]; //배열로 정의
                

            // precomputedRevers 배열을 직접 보여주면 길이가 65536개이므로, 이를 생성하는 코드  
            static{
                for (int i=0;i<ARRAY_SIZE;i++){
                    precomputedRevers[i]=reverse16Bits(i);
                }
            }

            private static int reverse16Bits(int x) {
                int result = 0;
                for (int i = 0; i < 16; i++) {
                    result |= ((x >>> i) & 1) << (15 - i);
                }
                return result;
            }

            private static int pReverse16Bits(int x){           
                int result = 0;
                for (int i =0;i<16;i++){
                    result |= ((x>>>i)&1) << (15-i);            //((x>>>i)&1) 은 i자리에 있는 비트를 최하위 비트로 만들고 and 계산으로 0또는 1로 솎아냄
                                                                // 그리고 그 비트를 가지고 15-i 자리로 옮김
                                                                // 만약에 x=(0001) 이면 (i=0,i<4,i++)일 경우 1 <<3 가 되므로 왼쪽으로 3칸이동하면 3번째 자리에 위치할수 있게됨
                                                                // 이 경우에는 x=(0010 00011 1110 1110)에서 (i=0,i<16,i++)일경우 0 << 15 가 되므로 왼쪽으로 15칸 이동해서 15번째 자리에위치 
                                                                // 인덱스가 오른쪽부터 0 부터 시작한다는 사실을 기억하자 
                                                                // or연산으로 숫자를 저장해놈
                }
                return result;                                  //for문으로 돈 결과를 반환한다
            }

            private static int p2Reverse16Bits(int x){
                int result = 0;
                for(int i=0;i<16;i++){
                    result |= ((x>>>i)&1)<<(15-i);
                }
                return result;
            }



            

    /* 같은 무게를 가진 가장 가까운 정수 찾기 (무게는 2진수로 표현했을때 1의 개수) */
    //연속 된 비트 중 서로 다른 두 개를 찾고, 이를 바꿔줌으로서 가장 작은 차이를 가지는
    //정수를 찾는 방식
    /*
     * 4비트를 예를 들었을때 4=(0100) 일때 2=(0010) 이 무게가 같으면서 가장 적은 차이를 가지는 정수가 됨 
     */
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

    public static long pClosestIntSameBitCount(long x){
        final int NUM_UNSIGNED_BITS = 63;

        for(int i=0;i<NUM_UNSIGNED_BITS-1;++i){  
            if(((x>>>i)&1)!=((x>>>(i+1))&1)){   // (x의 i번째 최하위 비트 != x의 그다음 비트의 최하위 비트) = 서로 다르다 
                                                // 무조건 하나는 1 무조건 하나는 0 
                x^=(1L<<i) | (1L<<(i+1));       //  x의 값에 비트 마스크를 XOR 시킴 -> 1일때 0 나오고 , 0일때 1나옴
                return x;                       // x갑을 반환시킴
            }
        }
        throw new IllegalArgumentException("All bits are 0 or 1");  //예외처리
    }

    public static long p2ClosestIntSameBitCount(long x){
        final int NUM_UNSIGNED_BITS=63;

        for(int i=0;i<NUM_UNSIGNED_BITS-1;++i){ 
            if(((x>>>i)&1)!=((x>>>(i+1))&1)){
                x^=(1L<<i) | (1L<<(i+1));
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

    public static long pMultiply(long x,long y){
        long sum=0;         
        while(x!=0){
            if((x&1)!=0){
                sum = add(sum,y);
            }
            x>>>=1;
            y<<=1;
        }
        return sum;
    }

    public static long p2Multiply(long x, long y){
        long sum=0;
        while(x!=0){
            if((x&1)!=0){
                sum = add(sum,y);
            }
            x>>>=1;
            x<<=1;
        }
        return sum;
    }

    /*
     * 곱셈을 수행하는 핵심 아이디어는 "이진수 곱셈"입니다.
     * 일반적인 곱셈 연산을 이진수로 표현하면 다음과 같습니다.
     * 예를 들어, 13 * 6을 계산한다고 가정하겠습니다.
     * x=13, y=6이 됌

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
