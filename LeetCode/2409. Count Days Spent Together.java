class Solution {
    public int countDaysTogether(String arriveAlice, String leaveAlice, String arriveBob, String leaveBob) {
        //bruh
        //annoyign aah ques
        int[] daysInMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

        int arriveA = toDayOfYear(arriveAlice, daysInMonth);
        int leaveA  = toDayOfYear(leaveAlice,  daysInMonth);
        int arriveB = toDayOfYear(arriveBob,   daysInMonth);
        int leaveB  = toDayOfYear(leaveBob,    daysInMonth);

        int start = Math.max(arriveA, arriveB);
        int end   = Math.min(leaveA, leaveB);

        return Math.max(0, end - start + 1);
    }

    private int toDayOfYear(String date, int[] daysInMonth) {
        int month = Integer.parseInt(date.substring(0, 2));
        int day   = Integer.parseInt(date.substring(3, 5));

        int total = 0;
        for (int i = 0; i < month - 1; i++) {
            total += daysInMonth[i];
        }
        return total + day;
    }
}
