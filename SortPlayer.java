//Ying HU, 26/05/2017
//ID:811483
//This is to sort the players with ratio in asc order or username in alphabetical order

public class SortPlayer {
	private int count;
	private NimPlayer[] player;

	public SortPlayer(NimPlayer[] player,int count) {
		this.player = player;
		this.count = count;
	} // get the list of players

	public void sort(String mode, String order) {
		int indexMin;
		if (mode.equals("ratio")) {
			for (int i=0; i<=count; i++) {
				indexMin = indexSmallest(i,order);
				swap(i,indexMin);
			}
		}
		else {
			for (int i=0; i<=count; i++) {
				indexMin = indexSmallestName(i);
				swap(i,indexMin);
			}
		}
		
	} // mode ratio is to sort based on ratio; others is to sort based on userName
	
	private int indexSmallest(int initialIndex, String order) {
		double minimum = player[initialIndex].getRatio();
		int indexMin = initialIndex;
		for (int i=initialIndex+1; i<=count; i++) {
			if (player[i].getRatio()<minimum) {
				minimum = player[i].getRatio();
				indexMin = i;
			}
			else if (player[i].getRatio()==minimum) { //if two player has the same ratio, determine its order by asc or desc in alphabetical order
				if (order.equals("asc")) {
					if (player[i].getUserName().compareToIgnoreCase(player[indexMin].getUserName())<0) {
						minimum = player[i].getRatio();
						indexMin = i;
					}
				}
				else {
					if (player[i].getUserName().compareToIgnoreCase(player[indexMin].getUserName())>0) {
						minimum = player[i].getRatio();
						indexMin = i;
					}
				}
			}
		}
		return indexMin;
	} // find the player with the smallest ratio
	
	private int indexSmallestName(int initialIndex) {
		String minimum = player[initialIndex].getUserName();
		int indexMin = initialIndex;
		for (int i=initialIndex+1; i<=count; i++) {
			if (player[i].getUserName().compareToIgnoreCase(minimum)<0) {
				minimum = player[i].getUserName();
				indexMin = i;
			}
		}
		return indexMin;
	} // find the player with the foremost in alphabetical order
	
	private void swap(int initialIndex, int indexMin) {
		NimPlayer tempPosi;
		tempPosi = player[initialIndex];
		player[initialIndex] = player[indexMin];
		player[indexMin] = tempPosi;
	} //swap the player with indexMin to the current position

}
