package sliding.window;

import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;

public class MediansInDataStream {
	
	private PriorityQueue<Integer> maxHeap, minHeap;
	private int[] items;
	private int windowSize;
	
	public MediansInDataStream(int[] items, int windowSize) {
		this.items = items;
		this.windowSize = windowSize;
		maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
		minHeap = new PriorityQueue<>();
	}
	
	private void rebalanceHeap() {
		//Math.abs(maxHeapHead)
		if (Math.abs(maxHeap.size() - minHeap.size()) > 1) {
			//we need re-balancing
			if (maxHeap.size() > minHeap.size()) {
				minHeap.add(maxHeap.remove());
			}
			
			if (minHeap.size() > maxHeap.size()) {
				maxHeap.add(minHeap.remove());
			}
		}
	}
	
	private void addItemToHeap(int item) {
		if (maxHeap.size() == 0) {
			maxHeap.add(item);
		} else {
			int maxHeapHead = maxHeap.peek();
			
			if (item <= maxHeapHead) {
				maxHeap.add(item);
			} else {
				minHeap.add(item);
			}
			
			rebalanceHeap();
		}
	}
	
	private void removeItemFromHeap(int item) {
		if (maxHeap.contains(item)) {
			maxHeap.remove(item);
		} else {
			minHeap.remove(item);
		}
		
		rebalanceHeap();
	}
	
	public void doCompute() throws Exception {
		if (items == null || items.length == 0) {
			throw new Exception("invalid input. array is either null or empty");
		}
		
		if (items.length < windowSize) {
			throw new Exception("invalid input. arr length less than the window size");
		}
		
		if (windowSize < 1) {
			throw new Exception("invalid input. window size < 1");
		}
		
		//setup the initial window
		for (int i = 0 ; i < windowSize ; i++) {
			addItemToHeap(items[i]);
		}
		
		System.out.println("median:"+getMedian());
		
		int currWindowlPtr = 0, currWindowrPtr = windowSize - 1;
		while (true) {
			if (currWindowrPtr == items.length - 1) {
				break;
			}
			
			//remove left-most item
			removeItemFromHeap(items[currWindowlPtr]);
			currWindowlPtr++;
			currWindowrPtr++;
				
			addItemToHeap(items[currWindowrPtr]);
			System.out.println("median:"+getMedian());
		}
	}
	
	private double getMedian() {
		int size = maxHeap.size() +  minHeap.size();
		if (size == 1) {
			return maxHeap.size() > 0 ? maxHeap.peek() : minHeap.peek();
		}
		
		boolean isEven = size % 2 == 0 ? true : false;
		if (isEven) {
			return (Double.valueOf(maxHeap.peek()) + Double.valueOf(minHeap.peek())) / 2;
		} else {
			if (maxHeap.size() > minHeap.size()) {
				return maxHeap.peek();
			} else {
				return minHeap.peek();
			}
		}
	}

	public static void main(String[] args) {
		try {
			//testcase#1
			int[] arr = new int[5];
			arr[0] = -1;
			arr[1] = 2;
			arr[2] = 6;
			arr[3] = 4;
			arr[4] = 3;
			MediansInDataStream median = new MediansInDataStream(arr, 3);
			median.doCompute();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//testcase#2
		try {
			int[] arr = new int[3];
			arr[0] = 2;
			arr[1] = 3;
			arr[2] = 4;
			MediansInDataStream median = new MediansInDataStream(arr, 2);
			median.doCompute();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//testcase#3
		try {
			int[] arr = new int[3];
			arr[0] = 2;
			arr[1] = 3;
			arr[2] = 4;
			MediansInDataStream median = new MediansInDataStream(arr, 1);
			median.doCompute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}