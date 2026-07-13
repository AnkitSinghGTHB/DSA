class Solution {
    public int[] arrayRankTransform(int[] arr) {
        //first we sort
        // then we get a freq table
        //then count the numbers probably and so on add the elements since
        //no we first need the originl array positions, so maybe save a sorted one
        int[] arr1 = Arrays.copyOf(arr, arr.length);
        Arrays.sort(arr1);
        int rank = 1;
        HashMap<Integer, Integer> freq = new HashMap<>();
        for (int i=0;i<arr.length;i++){
            if (!freq.containsKey(arr1[i])){
                freq.put(arr1[i],rank);
                rank++;
            }
        }
        for (int i=0;i<arr.length;i++){
            arr1[i] = freq.get(arr[i]);
        }
        return arr1;
    }
}
