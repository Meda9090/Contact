package com.example.ddd;

import java.util.Random;

//adjectives - прилагательные
//nouns - существительные
public class RandomNameGenerator {

	public static void main(String[] args) {

		Random random = new Random();
		String[] adjectives = {"Stellar", "Dynamic", "Agile", "Smart",		"Swift",
								"Zenith", "Crystal", "Quantum", "Galactic", "Azure"};

		String[] nouns = {"Phone", "Contact", "Directory", "Manager", "Nexus",
						  "Network", "Hub", 	"Base", 	"System", "Core"};

		String name = adjectives[random.nextInt(adjectives.length)] + " " +
					  	   nouns[random.nextInt(nouns.length)];

		System.out.println("Рандомное название проекта: " + name);


	}

}
