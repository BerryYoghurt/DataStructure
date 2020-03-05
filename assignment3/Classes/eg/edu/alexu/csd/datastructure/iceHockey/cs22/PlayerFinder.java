package eg.edu.alexu.csd.datastructure.iceHockey.cs22;

import java.awt.Point;
import java.util.ArrayList;
import java.lang.Integer;
//IF YOU HAVE TIME, TRY TO IMPLEMENT FLOODFILL TOO

public class PlayerFinder implements IPlayerFinder{

	@Override
	public Point[] findPlayers(String[] photo, int team, int threshold) {
		//null photo, no stings or empty strings
		if(photo == null || photo.length == 0 || photo[0].length() == 0) //empty string
			return new Point[0];
		int i, j, columns = photo[0].length();
		short nRegions = 0;
		char target = Integer.toString(team).charAt(0);
		ArrayList<Region> regions = new ArrayList<Region>();
		LinkedListOfPoints points;
		Point currentBody;
		short[][] addresses = new short[photo.length][columns];
		
		//traversing the photo for regions
		for(j = 0; j < columns; j++) {
			for(i = 0; i < photo.length; i++) {
				
				if(photo[i].charAt(j) != target) {
					addresses[i][j] = -1;
					continue;
				}
				if(i == 0 || addresses[i-1][j] == -1) {
					if(j == 0 || addresses[i][j-1] == -1) {
						regions.add(new Region(j,i));
						addresses[i][j] = nRegions;
						nRegions++;
						continue;
					}
					else {
						addresses[i][j] = addresses[i][j-1];
						regions.get(addresses[i][j]).add(j,i);
						continue;
					}
				}
				else {
					addresses[i][j] = addresses[i-1][j];
					regions.get(addresses[i][j]).add(j,i);
					if(j==0 || addresses[i][j-1] == -1 || addresses[i][j] == addresses[i][j-1]) {//if they already belong to the same region
						continue;
					}
					Region largerRegion = regions.get(addresses[i][j-1]);
					largerRegion.add(regions.get(addresses[i][j]));
					regions.set(addresses[i][j], largerRegion);
				}
				
			}
		}
		
		points = new LinkedListOfPoints();
		for(Region r : regions) {
			if(!r.checked) {
				currentBody = r.getCentre(threshold);
				if(currentBody != null) { //can be added to the final list
					points.addSorted(currentBody);
				}
				r.checked = true;
			}
		}
		
		return points.toArray();
	}
	
}

class Region{
	private int area;
	private int[] xLimits = new int[2]; //0 is left, 1 is right
	private int[] yLimits = new int[2]; //0 is top, 1 is bottom
	public boolean checked = false;
	
	/**
	 * @param x column number
	 * @param y row number
	 * **/
	
	public Region(int x,int y) {
		this.xLimits[0] = x*2;
		this.yLimits[0] = y*2;
		this.xLimits[1] = this.xLimits[0] + 2;
		this.yLimits[1] = this.yLimits[0] + 2;
 		this.area = 4;
	}
	
	/**
	 * @param x column number
	 * @param y row number**/
	
	public void add(int x, int y) { //column major traversal
		x = x*2 + 2;
		y = y*2 + 2;
		if(x > xLimits[1]) {
			xLimits[1] = x;
		}
		if(y > yLimits[1]) {
			yLimits[1] = y;
		}
		this.area += 4;
	}
	
	/**
	 * adds region r to this region, the common pixel should have
	 * been added to r 
	 * **/
	public void add(Region r) {
		this.area += r.area; 
		if (r.xLimits[0] < this.xLimits[0]) {
			this.xLimits[0] = r.xLimits[0]; 
 		}
		if(r.xLimits[1]>this.xLimits[1]) {
			this.xLimits[1] = r.xLimits[1];
		}
		if(r.yLimits[0] < this.yLimits[0]) {
			this.yLimits[0] = r.yLimits[0];
		}
		if(r.yLimits[1] > this.yLimits[1]) {
			this.yLimits[1] = r.yLimits[1];
		}
		
	}
	
	public Point getCentre(int threshold) {
		if(this.area >= threshold) {
			return new Point((xLimits[0]+xLimits[1])/2, (yLimits[0]+yLimits[1])/2);
		}else
			return null;
	}
	
	
}

class LinkedListOfPoints{
	public Node<Point> head = null;
	public int size = 0;
	
	public void addSorted(Point nodeData) { //this does not check for empty space
		Node<Point> currentNode = head, pastNode = null;
		while(currentNode != null) {
			if(currentNode.data.getX() < nodeData.getX()) {
				pastNode = currentNode;
				currentNode = currentNode.next;
			}
			else if(currentNode.data.getX() == nodeData.getX()) { //then compare using y
				if(currentNode.data.getY() < nodeData.getY()) {
					pastNode = currentNode;
					currentNode = currentNode.next;
				}
				else {
					break;
				}
			}
			else {
				break;
			}
		}
		if(pastNode == null) {
			head = new Node<Point>();
			head.data = nodeData;
			head.next = currentNode;
		}else {
			pastNode.next = new Node<Point>();
			pastNode.next.data = nodeData;
			pastNode.next.next = currentNode;
		}
		size++;
	}
	
	public Point[] toArray() {
		Node<Point> currentNode = head;
		Point[] arr = new Point[size];
		for(int i = 0; i < size; i++, currentNode = currentNode.next) {
			arr[i] = currentNode.data;
		}
		return arr;
	}
}

class Node<T>{
	public T data;
	public Node<T> next;
}