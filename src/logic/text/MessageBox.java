package logic.text;

import java.util.ArrayList;
import java.util.List;

public class MessageBox {

	public static int LIST_SIZE = 3;
	private final List<String> messageQueue;
	private final List<Integer> times;

	public MessageBox() {
		this.messageQueue = new ArrayList<>();
		this.times = new ArrayList<>();
	}

	public void addMessage(String text) {
		if (messageQueue.size()== LIST_SIZE) {
			this.messageQueue.remove(0);
			this.times.remove(0);
		}
		this.messageQueue.add(text);
		this.times.add(1000);
	}

	public List<String> getMessage() {
		try
		{
			for(int i=0; i<LIST_SIZE; ++i)
			{
				if(i< times.size())
					this.times.set(i, this.times.get(i)-1);
			}

			if(this.times.get(0) <= 0) {
				this.times.remove(0);
				this.messageQueue.remove(0);
			}

			return this.messageQueue;
		}
		catch(IndexOutOfBoundsException e)
		{
			return null;
		}
	}

}
