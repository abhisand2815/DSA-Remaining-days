
class MedianFinder {

    private PriorityQueue<Integer> small; // max heap
    private PriorityQueue<Integer> large; // min heap

    public MedianFinder() {

        small = new PriorityQueue<>(Collections.reverseOrder());
        large = new PriorityQueue<>();
    }

    public void addNum(int num) {

        // Step 1: add to max heap
        small.offer(num);

        // Step 2: largest of small goes to large
        large.offer(small.poll());

        // Step 3: keep small >= large
        if (large.size() > small.size()) {
            small.offer(large.poll());
        }
    }

    public double findMedian() {

        if (small.size() > large.size()) {
            return small.peek();
        }

        return ((double) small.peek() + large.peek()) / 2.0;
    }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */