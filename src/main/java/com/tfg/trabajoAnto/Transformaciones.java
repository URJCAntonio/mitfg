package com.tfg.trabajoAnto;

public class Transformaciones {
	public static boolean executeFunction(final String function, char a, char b, char c) {
        
		return new Object(){
            int length, index = 0;
            String cadena;

	        boolean match(String expect) {	
	            while (index < length && Character.isWhitespace(cadena.charAt(index))) //Elimina los espacios del parametro String
	                ++index;
	            if (index >= length)
	                return false;
	            if (cadena.startsWith(expect, index)) {
	                index += expect.length();
	                return true;
	            }
	            return false;
	        }
	
	        boolean element() {
	            if (match("1"))
	                return true;
	            else if (match("0"))
	                return false;
	            else if (match("(")) {
	                boolean result = expression();
	                if (!match(")"))
	                    throw new RuntimeException("')' expected");
	                return result;
	            } else
	                throw new RuntimeException("unknown token");
	        }
	
	        boolean term() { //NOT
	            if (match("!"))
	                return !element();
	            else
	                return element();
	        }
	
	        boolean factor() { // AND
	            boolean result = term();
	            while (match("*"))
	                result &= term();
	            return result;
	        }
	
	        boolean expression() { // OR
	            boolean result = factor();
	            while (match("+"))
	                result |= factor();
	            return result;
	        }
	
	        boolean parse() {
	        	cadena=function.replace('a', a).replace('b', b).replace('c', c);
	        	length = cadena.length();
	            boolean result = expression();
	            if (index < length)
	                throw new RuntimeException(
	                    "extra string '" + cadena.substring(index) + "'");
	            return result;
	        }
	        
	    }.parse();
    }
}
