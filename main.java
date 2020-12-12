import java.io.IOException;
import java.util.Random;

public class main {
	
	
	
	
	public static void main(String[] args) throws IOException {
		
		
		int orders, inventory=15, incomingBeer=15, unfilledOrders=0, previousIN=0;
		
		
		for(int x=1; x<101; x++) {
		
			orders=random();
			
		
			previousIN=inventory;
			
			inventory=inventory-orders-unfilledOrders+incomingBeer;
			
			
			unfilledOrders=unfilledOrders+orders-previousIN;
			
			
			if(unfilledOrders<0) {
				unfilledOrders=0;
			}
			
			
			
			incomingBeer=inFunction(inventory, unfilledOrders, previousIN);
			
			if(inventory<0) {
				inventory=0;
			}
			
			System.out.format("WEEK %d has %d customers and %d inventory left\n", x, orders, inventory );
			System.out.println(unfilledOrders);
			
		}
		
	}
	
	public static int random () {
		
		int x, orders=0;
		Random rand = new Random ();
		int randomInt, upperBound=101;
		
			randomInt=rand.nextInt(upperBound);
						
			if(randomInt<=7) {
				//System.out.println(" 9");
				orders=9;
			}
			
			if(randomInt>7 && randomInt<93) {
				//System.out.println(" 15");
				orders=15;
			}
			
			if(randomInt>93) {
				//System.out.println(" 21");
				orders=21;
			}
		
		return orders;
	}
	
	public static int inFunction (int inventory, int unfilled, int previousIN) {
		
		int newOrder=15;
		
		if(inventory>=10 && unfilled==0) {
			newOrder=10;
		}
		
		if(unfilled>=10) {
			newOrder=25;
			
		}
		
		return newOrder;
	}
	
	
	

}
