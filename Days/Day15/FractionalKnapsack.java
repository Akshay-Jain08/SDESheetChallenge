import java.util.PriorityQueue;

public class FractionalKnapsack {

    // Items class that stores value, weight, unitVal(value per unit weight)
    private static class Items {
        int value;
        int weight;
        double unitVal;
        
        Items(int value, int weight, double unitVal) {
            this.value = value;
            this.weight = weight;
            this.unitVal = unitVal;
        }
}

    public double fractionalKnapsack(int[] val, int[] wt, int capacity) {
        // code here
        
        // TC - O(N logN) & SC - O(N)
        // This approach calculates unitVal and sorts according to it
        // in non-increasing order 
        // This is done to ensure that maximum worth of the item
        // is taken out of all the remaining items every time
        PriorityQueue<Items> maxHeap = new PriorityQueue<>(
                                            (a, b) -> Double.compare(
                                                b.unitVal, a.unitVal));
        
        int n = val.length;
        
        // Making the maxHeap for all the items
        for (int i = 0; i < n; i++) {
            maxHeap.offer(new Items(val[i], wt[i], (double) val[i] / wt[i]));
        }
        
        double maxVal = 0;
        
        // Traverse through all the items and get the max worth item every single iteration
        while (capacity > 0 && !maxHeap.isEmpty()) {
            Items item = maxHeap.poll();
            
            // check for when the current item's weight is greater than remaining capacity
            // then we can take the fraction of that item
            double fraction = Math.min(1, (double) capacity / item.weight);
            
            maxVal += fraction * item.value;
            capacity -= fraction * item.weight;
        }
        
        return maxVal;
    }
}