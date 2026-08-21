class Solution {
    public long findKthSmallest(int[] coins, int k) {
        int n = coins.length;
        int totalMasks = 1 << n;

        long[] lcms = new long[totalMasks];
        int[] bits = new int[totalMasks];

        lcms[0] = 1;

        for (int mask = 1; mask < totalMasks; mask++) {
            int bit = Integer.numberOfTrailingZeros(mask);
            int prev = mask & (mask - 1);

            bits[mask] = bits[prev] + 1;

            long g = gcd(lcms[prev], coins[bit]);
            long value = lcms[prev] / g * coins[bit];

            if (value > 2_000_000_000L) {
                lcms[mask] = Long.MAX_VALUE;
            } else {
                lcms[mask] = value;
            }
        }

        long low = 1;
        long high = (long) k * coins[0];

        for (int coin : coins) {
            high = Math.min(high, (long) k * coin);
        }

        while (low < high) {
            long mid = low + (high - low) / 2;

            if (count(mid, lcms, bits) >= k) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }

        return low;
    }

    private long count(long x, long[] lcms, int[] bits) {
        long result = 0;

        for (int mask = 1; mask < lcms.length; mask++) {
            if (lcms[mask] > x) {
                continue;
            }

            long value = x / lcms[mask];

            if ((bits[mask] & 1) == 1) {
                result += value;
            } else {
                result -= value;
            }
        }

        return result;
    }

    private long gcd(long a, long b) {
        while (b != 0) {
            long temp = a % b;
            a = b;
            b = temp;
        }
        return a;
    }
}