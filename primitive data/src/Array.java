
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


/* 배열 */

public class Array {

    public static enum Color {RED,WHITE,BLUE}
    
    /* 네덜란드 국기 문제 */
    public static void dutchFlagPartition(int pivotIndex, List<Color> A){

        Color pivot = A.get(pivotIndex);

        for(int i= 0 ; i <A.size(); ++i){
            for(int j=i+1;i<A.size();++j)
            {
                if(A.get(j).ordinal() < pivot.ordinal()) {
                    Collections.swap(A,i,j);
                    break;
                }
                // ordinal()은 뭘까? 
                //해당 열거형 값이 선언된 순서를 정수(0부터 시작)로 반환하는 기능을 합니다.
                
                /* Collections 클래스는
                 * 컬렉션을 다룰 때 자주 사용하는 정렬, 검색, 변경, 동기화 처리 등의 기능을 제공합니다.
                 */

            }


        }
    }

    
    /* 임의의 정수값 증가시키기 */

    // 안좋은 방법은 배열로 들어온 값을 정수로 바꾼후 증가시킨후 다시 배열로 되돌리는것
    // 좋은 방법은 배열에 연산을 올림수를 사용하여 직접 적용하는것
    public static List<Integer> plusOne(List<Integer> A){
        int n = A.size() -1;                         // n은 A의 마지막 인덱스 숫자.
        A.set(n,A.get(n) +1 );                       // A의 n번째 요소를 +1하여 변경합니다.

        for(int i = n; i>0 && A.get(i) == 10; --i){   // 리스트 A에서 값이 10인 연속된 요소를 0으로 바꾸고,
            A.set(i,0);                       // 바로 앞 요소를 1 증가시키는 역할
            A.set(i-1,A.get(i-1)+1);
        }
        if(A.get(0)==10){
            A.set(0,1);                 // 리스트 A의 첫 번째 요소(A[0])가 10이면, 
                                                      // 해당 값을 1로 바꾸고
            A.add(0);                               // 리스트의 끝에 0을 추가
        }
        return A;
    }
    
    /* 임의의 두 정수값 곱하기 */

    public static List<Integer> multiply(List<Integer> num1, List<Integer> num2){
        //두 숫자의 부호가 다르면 -1, 같으면 1을 반환하는 표현식입니다.
        /* 자바에서 ^는 비트 단위 XOR 연산자입니다.
            하지만 피연산자가 boolean이라면 논리 XOR로 작동해요.
            이 두 조건이 다를 경우 (즉, 하나는 음수고 하나는 양수거나 0) → -1
            같을 경우 (둘 다 음수이거나, 둘 다 양수/0) → 1 */
        final int sign = num1.get(0) < 0 ^ num2.get(0) < 0 ? -1 : 1;
        num1.set(0,Math.abs(num1.get(0)));
        num2.set(0,Math.abs(num2.get(0)));
        
        // Collections.nCopies(n, obj) 는 같은 객체를 n번 복사해서 리스트를 만드는 메서드예요.
        // 이 메서드는 **불변 리스트(immutable list)**를 반환하지만, new ArrayList<>(...)를 씌우면
        // 수정 가능한 리스트로 변환할 수 있어요.
        List<Integer> result =  new ArrayList<>(Collections.nCopies(num1.size() + num2.size(), 0 ));


        for(int i = num1.size() - 1; i>= 0; --i){                               // num1의 마지막 자리(일의자리)부터 곱하기 시작
            for(int j = num2.size() -1; j >= 0; --j){                           // 끝에서부터 앞으로 이동동
                result.set(i+j+1, result.get(i+j+1) + num1.get(i) * num2.get(j)); // 현재 위치에서 값을 누적
                result.set(i+j, result.get(i+j) + result.get(i+j+1) / 10 );       // result[i+j+1] 값이 10 이상이면,
                                                                                  // 10으로 나눈 몫을 result[i+j]에 더함
                                                                                  // 즉, i+j 위치로 올림됨
                result.set(i+j+1, result.get(i+j+1) % 10);                       // result[i+j+1] 값이 10 이상이면 일의 자리만 남기고 
                                                                                 // 나머지는 올림
                                                                                 // result[i+j+1] % 10을 사용하여 한 자리 숫자로 만듦


            }
        }
        // 0ㅇ로 시작하는 부분을 제거한다.
        int first_not_zoro = 0;
        while( first_not_zoro < result.size() && result.get(first_not_zoro) == 0) {
            ++first_not_zoro;
        }
        result = result.subList(first_not_zoro, result.size());
        if(result.isEmpty()){
            return Arrays.asList(0);
        }
         result.set(0, result.get(0) * sign);
         return result; 
        }
    
}
