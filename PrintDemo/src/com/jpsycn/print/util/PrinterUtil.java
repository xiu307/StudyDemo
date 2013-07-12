package com.jpsycn.print.util;

import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;

public class PrinterUtil {

	
	public static final String packageName = "com.dynamixsoftware.printershare";
	private static final String pdfActivityName = "com.dynamixsoftware.printershare.ActivityPrintPDF";
	
	

	public static Intent sendPDFToPrintShare(Uri uri) {
		Intent intent = new Intent();
		intent.setComponent(new ComponentName(PrinterUtil.packageName,
				PrinterUtil.pdfActivityName));
		intent.setAction("android.intent.action.VIEW");
		intent.setType("application/pdf");
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		intent.setData(uri);
		return intent;

	}
	
}
