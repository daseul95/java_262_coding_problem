
import java.util.Collections;
import java.util.List;

/* 배열 */


public class Array {

    public static void evenOdd(List<Integer> A){
        int nextEven = 0, nextOdd = A.size() -1;
        while(nextEven<nextOdd){
            if(A.get(nextEven)%2==0){
                nextEven++;
            }else{
             // Collection.swap(A,nextEven,nextOdd--);
            }
        }
    }

    public static enum Color {RED,WHITE,BLUE}
                //1. 피벗보다 작은 값
                //2. 피벗과 같은 값
                //3. 피벗보다 큰 값
                // 열거형으로 정의 되어있으므로
                // RED = 0, WHILTE = 1, BLUE =2 의 값이 자동으로 할당당

    public static void dutchFlagPartition(int pivotIndex, List<Color> A){

        Color pivot = A.get(pivotIndex);        //피벗 값을 가져옴

            //첫 번째 단계 : 피벗보다 작은 원소의 그룹 만들기기.

        for(int i = 0;i<A.size();++i){
            for(int j=i+1;j<A.size();++j){
                if(A.get(j).ordinal()<pivot.ordinal()){
                    Collections.swap(A, i, j);
                    break;
                }
            }
        }
            /* 이중 루프를 이용해 피벗보다 작은 값을 앞으로 이동
             * i는 현재 위치 의미
             * j는 i+1부터 시작하여 더 작은 값이 나오면 교환(swqp)함
             * Collections.swap(A, i, j);을 통해 i 위치에 작은 값을 두고 반복
             * reak;를 통해 한 번 교환한 후에는 내부 루프를 빠져나옴
             */

             
             //두 번째 단계:피벗보다 큰 원소의 그룹을 구하기
    
        for(int i=A.size() -1; i>= 0 && A.get(i).ordinal()>=pivot.ordinal();--i){
            for(int j= i-1; j>=0 && A.get(j).ordinal() >= pivot.ordinal();--j){
                Collections.swap(A,i,j);
                break;
            }
        }
              /* 큰 원소 찾기. 피벗보다 작은 원소를 만나면 멈춤
               * 윗단계에서 피벗보다 작은 원소들은 이미 A의 앞쪽에 옮겨짐 
               */
    }

    //복잡도 줄인 코드
    public static void inproveDutchFlagPartition(int pivotIndex, List<Color> A){
        Color pivot = A.get(pivotIndex);
        
        //첫 번째 단계 : 피벗보다 작은 원소의 그룹 구하기

        int smaller =0 ;
        for(int i=0;i<A.size();++i){
            if(A.get(i).ordinal()<pivot.ordinal()){
                Collections.swap(A,smaller++,i);
            }
        }

        //두 번째 단계 : 피벗보다 큰 원소의 그룹 구하기

        int larger = A.size() -1 ;
        for(int i=A.size() - 1; i>=0&&A.get(i).ordinal() >= pivot.ordinal();--i){
            if(A.get(i).ordinal() > pivot.ordinal()){
                Collections.swap(A,larger--,i);
            }
        }
    }
    
}
