package com.zuzya.chat.server;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class Utils {

	public static void setupIp() {
		String ipAddress = getIpAddress();
		try (FileOutputStream outputStream = new FileOutputStream("../SomeInfo/doc", false)) {//TODO normal path :>
			outputStream.write(ipAddress.getBytes());
			outputStream.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static String getIpAddress() {
		String ip = "error";
		URL url = null;
		try {
			url = new URL("http://checkip.amazonaws.com");

			BufferedReader in = new BufferedReader(new InputStreamReader(
					url.openStream()));


			ip = in.readLine();
			System.out.println(ip);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ip;
	}
}
