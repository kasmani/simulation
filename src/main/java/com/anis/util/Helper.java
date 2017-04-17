package com.anis.util;

import java.util.Random;
import java.util.UUID;

public class Helper {
	public static String generateUniqueString()
	{
	    return UUID.randomUUID().toString();
	}	

}
