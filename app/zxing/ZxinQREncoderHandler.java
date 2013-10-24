package zxing;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.Hashtable;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageConfig;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

public class ZxinQREncoderHandler {

	/**
	 * 编码
	 * 
	 * @param contents
	 * @param width
	 * @param height
	 * @param imgPath
	 */
	public void encode(String contents, int width, int height, String imgPath) {
		Hashtable<EncodeHintType, Object> hints = new Hashtable<EncodeHintType, Object>();
		// 指定纠错等级
		hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
		// 指定编码格式
		hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
		try {
			BitMatrix bitMatrix = new MultiFormatWriter().encode(contents,
					BarcodeFormat.QR_CODE, width, height, hints);
			MatrixToImageWriter
					.writeToFile(bitMatrix, "png", new File(imgPath));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public ByteArrayOutputStream encode(String contents, int size,int margin,String onc,String offc){
		Hashtable<EncodeHintType, Object> hints = new Hashtable<EncodeHintType, Object>();
		// 指定纠错等级
		hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
		// 指定编码格式
		hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
		hints.put(EncodeHintType.MARGIN, margin);
		ByteArrayOutputStream os=new ByteArrayOutputStream();
		try {
			BitMatrix bitMatrix = new MultiFormatWriter().encode(contents,
					BarcodeFormat.QR_CODE, size, size, hints);
			int on=Integer.parseInt(onc,16);
			int off=Integer.parseInt(offc,16);
			MatrixToImageConfig mt=new MatrixToImageConfig(on,off);
			MatrixToImageWriter.writeToStream(bitMatrix, "png", os,mt);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return os;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String imgPath = "d:/zxing.png"; 
		String contents = "Hello Michael(大大),welcome to Zxing!"
				+ "\nMichael’s blog [ http://sjsky.iteye.com ]"
				+ "\nEMail [ sjsky007@gmail.com ]" + "\nTwitter [ @suncto ]";
		int width = 300, height = 300;
		ZxinQREncoderHandler handler = new ZxinQREncoderHandler();
		handler.encode(contents, width, height, imgPath);

		System.out.println("Michael ,you have finished zxing encode.");
	}

}
