package com.codecrate.shard.ui.command;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.swing.JOptionPane;

import org.springframework.richclient.command.support.ApplicationWindowAwareCommand;

public class BrowserLauncherCommand extends ApplicationWindowAwareCommand {
	private String url;

	protected void doExecuteCommand() {
		new BareBonesBrowserLauncher().openURL(url);
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	/////////////////////////////////////////////////////////
	//  Bare Bones Browser Launch                          //
	//  Version 1.5                                        //
	//  December 10, 2005                                  //
	//  Supports: Mac OS X, GNU/Linux, Unix, Windows XP    //
	//  Example Usage:                                     //
	//     String url = "http://www.centerkey.com/";       //
	//     BareBonesBrowserLaunch.openURL(url);            //
	//  Public Domain Software -- Free to Use as You Like  //
	/////////////////////////////////////////////////////////
	public static class BareBonesBrowserLauncher {
		private static final String[] BROWSERS = new String[] {
			"firefox",
			"opera",
			"konqueror",
			"epiphany",
			"mozilla",
			"netscape" };

		private static final String PLATFORM_MAC = "Mac OS";
		private static final String PLATFORM_WINDOWS = "Windows";
		private static final String WINDOWS_EXEC_COMMAND = "rundll32 url.dll,FileProtocolHandler ";
		private static final String MAC_FILE_MANAGER_CLASS = "com.apple.eio.FileManager";
		private static final String errMsg = "Error attempting to launch web browser";

		public void openURL(String url) {
			String osName = System.getProperty("os.name");
			Runtime runtime = Runtime.getRuntime();
			try {
				if (osName.startsWith(PLATFORM_MAC)) {
					Class fileMgr = Class.forName(MAC_FILE_MANAGER_CLASS);
					Method openURL = fileMgr.getDeclaredMethod("openURL", new Class[] { String.class });
					openURL.invoke(null, new Object[] { url });
				} else if (osName.startsWith(PLATFORM_WINDOWS)) {
					runtime.exec(WINDOWS_EXEC_COMMAND + url);
				} else {
					String browser = getAvailableBrowser(runtime);
					runtime.exec(new String[] { browser, url });
				}
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, errMsg + ":\n" + e.getLocalizedMessage());
			}
		}

		private String getAvailableBrowser(Runtime runtime) throws InterruptedException, IOException {
			for (int count = 0; count < BROWSERS.length; count++) {
				String browser = BROWSERS[count];
				if (runtime.exec(new String[] { "which", browser }).waitFor() == 0) {
					return browser;
				}
			}
			throw new IllegalStateException("No supported browser found among: [" + stringify(BROWSERS) + "]");
		}

		private String stringify(String[] strings) {
			StringBuffer result = new StringBuffer();
			for (int x = 0; x < strings.length; x++) {
				String string = strings[x];
				result.append(string);

				if (x - 1 != strings.length) {
					result.append(", ");
				}
			}
			return result.toString();
		}
	}
}