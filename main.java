import java.io.IOException;
import java.util.Random;

public class main {
	
	
	public static int[] rawMaterials = new int [110];
	
	public static void main(String[] args) throws IOException {
		
		
		int orders, inventoryLastWeek,inventory2=0, incomingBeer=15, unfilledOrders=0;
		
		//at the start, we order 15 materials for the 2 weeks before we start selling
		rawMaterials[0]=15;
		rawMaterials[1]=15;
				
		//for loop runs so we have 99 weeks 
		for(int x=2; x<102; x++) {
			
			inventoryLastWeek=inventory2; //inventory last week is assigned its value
		
			orders=random(); //generating number of orders for the week - call random function
			
			//Incoming beer is equal to the raw materials ordered from 2 weeks ago
			incomingBeer=rawMaterials[x-2];
			
			//calculating remaining inventory 
			inventory2=inventoryLastWeek-orders-unfilledOrders+incomingBeer;
			
			//calculating unfilled orders
			unfilledOrders=unfilledOrders+orders-inventoryLastWeek-incomingBeer;
			
			//in unfilled orders or inventory are negative values, they're 0
			//must be non negative 
			if(unfilledOrders<0) {
				unfilledOrders=0;
			}
			
			if(inventory2<0) { 
				inventory2=0;
			}
			
			
			//call function to find how many raw materials we order this week
			inFunction(inventory2, unfilledOrders, x); 
			
			
			//Outputting week's info 
			System.out.format("\n\nWeek %d \n-------\nCustomers:         %d\nInventory left:    %d\n", x-1, orders, inventory2 );
			System.out.println("Unfilled orders:   "+unfilledOrders);
			System.out.println("Material order:    "+rawMaterials[x]);
			
		}
		
	}
	
	public static int random () {
		
		// this function generates the number of orders received each week
		// applying the predetermined probabilities and random number generator 
		// we return either 9 15 or 21 customers each week 
		
		
		int orders=0;
		Random rand = new Random ();
		int randomInt, upperBound=100; 	//random number between 0 and 100
		
			randomInt=rand.nextInt(upperBound);
						
			if(randomInt<=6) { 					// 7% are 9
				orders=9;
			}
			
			if(randomInt>6 && randomInt<=92) { 	// 85% are 15
				orders=15;
			}
			
			if(randomInt>92) { 					// 8% are 21
				orders=21;
			}
		
		return orders; //returns the number of orders 
	}
	
	public static void inFunction (int inventory, int unfilled, int week) {
		
		//	this function determines how many raw materials to order 
		//	determines that value based on whether we have a surplus in inventory
		//	or unfilled orders 
		
		
		// inventory is between 10-30, too high so we decrease our order by 6 
		if(inventory > 10 && inventory< 30 && unfilled == 0) { 
			
			rawMaterials[week]=rawMaterials[week-1]-6;
			
		}
		
		// if the inventory is above 30, there is a very large surplus
		//	set this weeks order to 0, to combat surplus 
		else if(inventory >= 30 && unfilled == 0) { //too much, order less
			
			rawMaterials[week]=0;
			
		}
		
		// if the inventory is less or equal to 10, the ideal inventory range 
		// and there are no unfilled orders we order 15, the mean customers per week
		else if (inventory <= 10 && unfilled == 0) {
			
			rawMaterials[week]=15;
			
		}
		
		// if there are 1-4 unfilled orders, we order more materials 
		// increase in order is a approx. a quarter of unfilled order plus 1
		else if(unfilled > 0 && unfilled < 5)  { //back ordered. order more
			
			rawMaterials[week]=(int) (rawMaterials[week-1]+unfilled*0.25+1);
			
		}
		
		// if there are 5 or greater unfilled orders, we order a larger 
		// amount of raw materials, the increase is approx. half of the unfilled orders
		else if(unfilled > 4) { //very back ordered. order a lot more
			
			rawMaterials[week]=(int) (rawMaterials[week-1]+unfilled*0.5);
	
		}
		
		// if it does not fit into any of these cases, 
		// order what we ordered the previous week
		else{
			
			rawMaterials[week]=rawMaterials[week-1];
			
		}
		
		// no negative orders, if negative just set to 0
		if(rawMaterials[week] < 0) {
			
			rawMaterials[week]=0;
			
		}
		
	}

}
