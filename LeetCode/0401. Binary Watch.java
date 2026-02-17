class Solution {
    public List<String> readBinaryWatch(int turnedOn) {
        List<String> result = new ArrayList<>();
        for (int h = 0; h<12; h++) { //for hour i put
            for (int m = 0; m<60; m++) { //for mins lol
                if (Integer.bitCount(h)+Integer.bitCount(m)==turnedOn) {
                    result.add(String.format("%d:%02d", h, m)); //stringformat
                }
            }
        }
        return result;
    }
}
