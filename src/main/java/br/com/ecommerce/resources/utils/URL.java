package br.com.ecommerce.resources.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class URL {

	public static List<Integer> decodeIntList(String s) {
//		List<Integer> list = new ArrayList<Integer>();
//		String[] args = s.split(",");
//		for(String arg : args) {
//			list.add(Integer.parseInt(arg));
//		}
//		return list;
	// função lambda
		return Arrays.asList(s.split(",")).stream().map(x -> Integer.parseInt(x)).collect(Collectors.toList());
	}
	
	public static String decodeParam(String s) {
		try {
			return URLDecoder.decode(s, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			return "";
		}
	}
	
}
