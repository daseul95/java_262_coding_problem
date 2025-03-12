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

        




}
