package com.zuzya.chat.server;

import java.io.*;
import java.net.URL;

public class Utils {

	public static void setupIp() {
		String ipAddress = getIpAddress();
		resetLastGitCommit(ipAddress);
	}

	private static void resetLastGitCommit(String ipAddress) {

		try {
			File workingDir = new File("../SomeInfo");

			runCmdCommand(workingDir, "cmd.exe /c start /wait cmd.exe /k git pull -v");

			runCmdCommand(workingDir, "cmd.exe /c start /wait cmd.exe /k git reset HEAD~1");

			runCmdCommand(workingDir, "cmd.exe /c start /wait cmd.exe /k git push origin +HEAD");

			writeToFile(ipAddress);

			runCmdCommand(workingDir, "cmd.exe /c start /wait cmd.exe /k git add .");

			runCmdCommand(workingDir, "cmd.exe /c start /wait cmd.exe /k git commit -m \"new\"");

			runCmdCommand(workingDir, "cmd.exe /c start /wait cmd.exe /k git push origin master");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void runCmdCommand(File workingDir, String cmd) throws IOException, InterruptedException {
		Process p;
		p = Runtime.getRuntime().exec(cmd, null, workingDir);
		p.waitFor();
	}

	private static void writeToFile(String ipAddress) {
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
