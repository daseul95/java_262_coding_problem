public class FreePratice {
    public static void main(String[] args) {
        System.out.println("연습장입니다.");
        System.out.println( pSwapBits(7,1,3) );
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

          public static final int BIT_SIZE = 16;
          public static final int ARRAY_SIZE = 1 << BIT_SIZE; // 65536 자동 계산 , 2^16승
          
          public static int[] precomputedRevers = new int[ARRAY_SIZE]; //배열로 정의
                
            public static long revserBits(long x){
                final int WORD_SIZE=16;               //16비트 단위 처리
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
            static {
                for (int i = 0; i < ARRAY_SIZE; i++) {
                    precomputedRevers[i] = reverse16Bits(i);
                }
            }
            
            private static int reverse16Bits(int x) {
                int result = 0;
                for (int i = 0; i < 16; i++) {
                    result |= ((x >>> i) & 1) << (15 - i);
                }
                return result;
            }

        

        




}
