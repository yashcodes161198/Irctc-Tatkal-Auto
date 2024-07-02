public class BitonicArray {
    public static int minOperationsToBitonic(int[] arr) {
        int n = arr.length;

        // Arrays to store the length of increasing and decreasing subsequences
        int[] inc = new int[n];
        int[] dec = new int[n];

        // Initialize increasing subsequence lengths
        for (int i = 0; i < n; i++) {
            inc[i] = 1;
        }

        // Fill increasing subsequence lengths
        for (int i = 1; i < n; i++) {
            if (arr[i] > arr[i - 1]) {
                inc[i] = inc[i - 1] + 1;
            }
        }

        // Initialize decreasing subsequence lengths
        for (int i = 0; i < n; i++) {
            dec[i] = 1;
        }

        // Fill decreasing subsequence lengths
        for (int i = n - 2; i >= 0; i--) {
            if (arr[i] > arr[i + 1]) {
                dec[i] = dec[i + 1] + 1;
            }
        }

        // Find the maximum length of the bitonic subarray
        int maxLength = 0;
        for (int i = 0; i < n; i++) {
            maxLength = Math.max(maxLength, inc[i] + dec[i] - 1);
        }

        // The minimum number of operations is the total elements minus the length of the bitonic subarray
        return n - maxLength;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 5, 2, 1};
        int n = arr.length;
        int count=0;
        int start=-1, end=-1;
        int sum=0;
        for(int i=0; i<n; i++){
            if(arr[i]==0){
                if(start==-1){
                    start=i;
                }
                else {
                    end=i;
                    int nn=(end-start)/2 + 1;
                    int sum1 = nn*(nn+1);
                    sum1/=2;
                    int sum2 = (nn-1)*nn;
                    sum2/=2;
                    int idealSum = sum1 + sum2;
                    count += sum-idealSum;
                    sum=0;
                    start=-1;
                    end=-1;
                }
            } else{
                sum += arr[i];
            }
        }
        System.out.println(count);


//        System.out.println(minOperationsToBitonic(arr)); // Output: 2
    }
}

