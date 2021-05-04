# Sorting Algorithm

*2021학년도 1학기 컴퓨터알고리즘 9주차 과제*

> 이름: 최종민
>
> 학번: 202001677

## Sorting Algorithm(정렬 알고리즘)

정렬 알고리즘은 크게 **내부정렬(Internal Sort)**과 **외부정렬(External Sort)**로 분류한다.

**내부정렬**은 입력의 크기가 주기억 장치(main memory)의 공간보다 크지 않은 경우에 수행되는 정렬이다.

그러나 입력의 크기가 주기억 장치 공간보다 큰 경우에는, 보조 기억 장치에 있는 입력을 여러 번에 나누어 주기억 장치에 읽어 들인 후, 정렬하여 보조 기억 장치에 다시 저장하는 과정을 반복해야 한다. 이러한 정렬을 **외부정렬**이라고 한다.

이 문서에는 내부정렬 중에서 **버블 정렬(Bubble Sort)**, **선택 정렬(Selection Sort)**, **삽입 정렬(Insertion Sort)**, **쉘 정렬(Shell Sort)**에 대해 다룬다.

### Bubble Sort(버블 정렬)

**버블 정렬**은 이웃하는 숫자를 비교하여 작은 수를 앞쪽으로 이동시키는 과정을 반복하여 정렬하는 알고리즘이다.

#### *BubbleSort.java*

```java
public class BubbleSort extends Sort {

    @Override
    public int[] sort(int[] array) {
        System.out.println("\n=== Bubble Sort ===");
        int length = array.length;
        int[] result = array.clone();

        long startTime = System.currentTimeMillis();
        for (int i = 1; i <= length - 1; i++)
            for (int j = 1; j <= length - i; j++)
                if (result[j - 1] > result[j]) {
                    int temp = result[j - 1];
                    result[j - 1] = result[j];
                    result[j] = temp;
                }
        long endTime = System.currentTimeMillis();

        measureTime(startTime, endTime);
        return result;
    }
}
```

첫 번째 `for`루프가 배열의 길이인 `length`-1번 반복되고, 두 번째 `for`루프는 `length`-`i`번 반복되는데, 앞 부분은 이미 정렬된 상태이므로 비교할 필요가 없기 때문이다.

 만약 `result[j - 1]`이 `result[j]`보다 클 경우, 이 둘의 값을 바꿔준다.

`for`루프가 모두 끝나면 정렬이 끝났으므로, `result`를 리턴한다.

### Selection Sort(선택 정렬)

**선택 정렬**은 배열 전체에서 최솟값을 선택하여 `i`번째 원소와 자리를 바꾼다. 이 과정을 반복하다 마지막에 2개의 원소 중에서 작은 값을 선택해 자리를 바꿈으로써 정렬하는 알고리즘이다. 

#### *SelectionSort.java*

```java
public class SelectionSort extends Sort {

    @Override
    public int[] sort(int[] array) {
        System.out.println("\n=== Selection Sort ===");
        int length = array.length;
        int[] result = array.clone();

        long startTime = System.currentTimeMillis();
        for (int i = 0; i <= length - 2; i++) {
            int min = i;
            for (int j = i + 1; j <= length - 1; j++)
                if (result[j] < result[min])
                    min = j;
            int temp = result[i];
            result[i] = result[min];
            result[min] = temp;
        }
        long endTime = System.currentTimeMillis();

        measureTime(startTime, endTime);
        return result;
    }
}
```



### Insertion Sort(삽입 정렬)



#### *InsertionSort.java*

```java
public class InsertionSort extends Sort {

    @Override
    public int[] sort(int[] array) {
        System.out.println("\n=== Insertion Sort ===");
        int length = array.length;
        int[] result = array.clone();

        long startTime = System.currentTimeMillis();

        for (int i = 1; i <= length - 1; i++) {
            int CurrentElement = result[i];
            int j = i - 1;
            while (j >= 0 && result[j] > CurrentElement) {
                result[j + 1] = result[j];
                j--;
            }
            result[j + 1] = CurrentElement;
        }

        long endTime = System.currentTimeMillis();

        measureTime(startTime, endTime);
        return result;
    }
}
```



### Shell Sort(쉘 정렬)



```java
public class ShellSort extends Sort {

    @Override
    public int[] sort(int[] array) {
        System.out.println("\n=== Shell Sort ===");
        int length = array.length;
        int[] result = array.clone();

        long startTime = System.currentTimeMillis();

        for (int h = length / 3; h > 0; h = h / 3)
            for (int i = h; i <= length - 1; i++) {
                int CurrentElement = result[i];
                int j = i;
                while (j >= h && result[j - h] > CurrentElement) {
                    result[j] = result[j - h];
                    j = j - h;
                }
                result[j] = CurrentElement;
            }

        long endTime = System.currentTimeMillis();

        measureTime(startTime, endTime);
        return result;
    }
}
```



## 성능 분석

**버블 정렬(Bubble Sort)**의 시간복잡도는 $$n(n-1)/2 \times O(1)=(1/2n^2-1/2n) \times O(1)=O(n^2) \times O(1) = O(n^2)$$이다.

 **선택 정렬(Selection Sort)**

**삽입 정렬(Insertion Sort)**

**쉘 정렬(Shell Sort)**







---

## 참고 및 출처

- 알기 쉬운 알고리즘