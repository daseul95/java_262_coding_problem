
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
    
    
}
