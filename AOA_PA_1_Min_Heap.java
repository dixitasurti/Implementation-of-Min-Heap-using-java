package university.suny;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

public class AOA_PA_1_Min_Heap {
	static int actualSize = 0;
	static int max = 200000;
	static int[] heap = new int[max];
	public static void main(String[] args) {
		InputStreamReader r=new InputStreamReader(System.in);    
		BufferedReader br=new BufferedReader(r);  
		try
		{
			FileOutputStream out = new FileOutputStream("myaoaoutput.txt");
			System.out.println("Enter elements : ");
			String input;
			while ((input = br.readLine()) != null)
			{
				if(input.length()>0)
				{
					if(input.startsWith("A"))
					{
						String str[] = input.split(" ");
						addToHeap(Integer.parseInt(str[1]),heap);
					}
					else if(input.startsWith("E"))
					{
						int min = extractMin();
						out.write((min+"").getBytes());
						out.write(10);
					}
				}
				else
				{
					break;
				}
			}
		    out.close();
		}catch(Exception e)
		{
			System.out.println("Exception occured "+e);
		}
	}

	private static int extractMin() {
		int minValue = heap[0];
		//replace root element with the last element of the tree
		heap[0] = heap[actualSize-1];
		actualSize--;
		heapifyDown(0);
		System.out.println(minValue);
		return minValue;
	}

	private static void heapifyDown(int heapifyFrom) {
		//check if its leaf node - because we cannot heapify down the leaf node
		int parent = heapifyFrom;
		int lefttChild =(2*heapifyFrom+1);
		int rightChild =(2*heapifyFrom+2);
		if(lefttChild<actualSize && rightChild<actualSize)
		{
			//check if parent node is smaller than either of its child
			if(heap[parent]>heap[lefttChild] || heap[parent]>heap[rightChild])
			{
				//check which is smaller - left or right, and swap with the smaller element
				if(heap[lefttChild]<heap[rightChild])
				{
					//left child is smaller - swap parent with left child and perform heapify down again
					int temp = heap[parent];
					heap[parent] = heap[lefttChild];
					heap[lefttChild] = temp;
					heapifyDown(lefttChild);
				}
				else
				{
					//right child is smaller - swap parent with right child and perform heapify down again
					int temp = heap[parent];
					heap[parent] = heap[rightChild];
					heap[rightChild] = temp;
					heapifyDown(rightChild);
				}

			}
		}
		else if(lefttChild<actualSize)
		{
			if(heap[parent]>heap[lefttChild])
			{

				//left child is smaller - swap parent with left child and perform heapify down again
				int temp = heap[parent];
				heap[parent] = heap[lefttChild];
				heap[lefttChild] = temp;
				heapifyDown(lefttChild);
			}
		}

	}

	private static void addToHeap(int parseInt,int[] heap) {
		int child = actualSize;
		int parent = (child-1)/2;
		int value = parseInt;
		heap[child] = value;
		while(child!=parent && heap[parent]>heap[child])
		{
			//swap parent child 
			int temp = heap[parent];
			heap[parent] = heap[child];
			heap[child] = temp;
			child = parent;
			parent = (child-1)/2;
		}
		actualSize++;
	}

}
