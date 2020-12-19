import java.io.IOException;
import java.util.Random;

public class main {
	
	
	public static int[] rawMaterials = new int [110];
	
	public static void main(String[] args) throws IOException {
		
		
		int orders, inventory1,inventory2=0, incomingBeer=15, unfilledOrders=0;
		
		rawMaterials[0]=15;
		rawMaterials[1]=15;
		
		
		for(int x=2; x<102; x++) {
			
			inventory1=inventory2;
		
			orders=random();
			
			incomingBeer=rawMaterials[x-2];
			
			inventory2=inventory1-orders-unfilledOrders+incomingBeer;
			
			unfilledOrders=unfilledOrders+orders-inventory1-incomingBeer;
			
			if(unfilledOrders<0) {
				unfilledOrders=0;
			}
			System.out.println();
			inFunction(inventory2, unfilledOrders, x);
			
			if(inventory2<0) {
				inventory2=0;
			}
			
			System.out.format("week %d \nhas %d customers and %d inventory left\n", x-1, orders, inventory2 );
			System.out.println("unfilled "+unfilledOrders);
			System.out.println("ordered "+rawMaterials[x]);
		}
		
	}
	
	public static int random () {
		
		int orders=0;
		Random rand = new Random ();
		int randomInt, upperBound=101;
		
			randomInt=rand.nextInt(upperBound);
						
			if(randomInt<=7) {
				orders=9;
			}
			
			if(randomInt>7 && randomInt<=93) {
				orders=15;
			}
			
			if(randomInt>93) {
				orders=21;
			}
		
		return orders;
	}
	
	public static void inFunction (int inventory, int unfilled, int week) {
		
		
		
		if(inventory > 10 && inventory< 30 && unfilled == 0) { //too much, order less
			
			//for the next three weeks
			
			rawMaterials[week]=rawMaterials[week-1]-5;
		//	rawMaterials[week+1]=rawMaterials[week-1]-5;
		//	rawMaterials[week+2]=rawMaterials[week-1]-5;
			System.out.println("1");
			
		}
		
		else if(inventory >= 30 && unfilled == 0) { //too much, order less
			
			//for the next three weeks
			
			rawMaterials[week]=0;
		//	rawMaterials[week+1]=0;
		//	rawMaterials[week+2]=0;
			System.out.println("2");
			
		}
		
		else if (inventory < 10 && unfilled == 0) {
			rawMaterials[week]=15;
			System.out.println("3");
		}
		
		else if(unfilled > 0 && unfilled < 5)  { //back ordered. order more
			
			rawMaterials[week]=(int) (rawMaterials[week-1]+unfilled*0.25);
			//rawMaterials[week+1]=rawMaterials[week-1]+5;
			//rawMaterials[week+2]=rawMaterials[week-1]+5;
			System.out.println("4");
			
		}
		
		else if(unfilled > 4) { //very back ordered. order a lot more
			
			rawMaterials[week]=(int) (rawMaterials[week-1]+unfilled*0.5);
			//rawMaterials[week+1]=rawMaterials[week-1]+10;
			//rawMaterials[week+2]=rawMaterials[week-1]+10;
	
			System.out.println("5");
			
		}
		
		else{
			
			rawMaterials[week]=rawMaterials[week-1];
			System.out.println("6");
			
		}
		
		if(rawMaterials[week] < 0) {
			
			rawMaterials[week]=0;
			
		}
		
	}
	
	
	

}
