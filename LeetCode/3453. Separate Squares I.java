class Solution {
    public double separateSquares(int[][] squares) {
        //im having difficulty reading
        double totalArea = 0.0;
        double minY = Double.POSITIVE_INFINITY;
        double maxY = Double.NEGATIVE_INFINITY;
        for (int[] sq : squares) {
            int yi = sq[1];
            int li = sq[2];
            totalArea += (double) li * li;
            minY = Math.min(minY, yi);
            maxY = Math.max(maxY, yi + li);
        }
        double half = totalArea / 2.0;
        double lo = minY, hi = maxY;
        //80 iterations guarantee precision far beyond required 1e-5
        for (int iter = 0; iter < 80; ++iter) {
            double mid = (lo + hi) * 0.5;
            double areaBelow = 0.0;
            for (int[] sq : squares) {
                int yi = sq[1];
                int li = sq[2];
                if (mid <= yi) {
                    //no contribution
                }
                else if (mid >= yi + li) {
                    areaBelow += (double) li * li;
                } 
                else {
                    areaBelow += (double) li * (mid - yi);
                }
            }
            if (areaBelow < half) {
                lo = mid;
            } 
            else {
                hi = mid;
            }
        }
        return hi;
    }
}
