package org.inria.restlet.mta.application;

import java.util.Random;

public class test {

	public static void main(String[] args) {
		
		int dep_x = 0 ;
		int dep_y = 0 ;

		int arr_x = dep_x;
		int arr_y = dep_y;
		
		System.out.println("Coordonn�es d'origine : ("+dep_x+")("+dep_y+")");

		Random rand = new Random();
		int r = rand.nextInt(4);
		
		if (r == 0) {
			System.out.println("le requin veut se d�place � bas");
			arr_x = (dep_x + 1) % (5);
			System.out.println("Nouelles coordonn� : ("+arr_x+")("+arr_y+")");

		} else if (r == 1) {
			System.out.println("le requin veut se d�place � haut");
			if(dep_x == 0){
				arr_x = 4;
				System.out.println("Nouelles coordonn� : ("+arr_x+")("+arr_y+")");
			} else {
				arr_x = (dep_x - 1) % (4);
				System.out.println("Nouelles coordonn� : ("+arr_x+")("+arr_y+")");
			}

		} else if (r == 2) {
			System.out.println("le requin veut se d�place en droite");
			arr_y = (dep_y + 1) % (5);
			System.out.println("Nouelles coordonn� : ("+arr_x+")("+arr_y+")");

		} else if (r == 3) {
			System.out.println("le requin veut se d�place en gauche");
			if(dep_y == 0){
				arr_y = 4;
				System.out.println("Nouelles coordonn� : ("+arr_x+")("+arr_y+")");
			} else {
				arr_y = (dep_y - 1) % (4);
				System.out.println("Nouelles coordonn� : ("+arr_x+")("+arr_y+")");
			}
		}

	}

}
