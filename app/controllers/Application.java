package controllers;

import play.*;
import play.mvc.*;
import zxing.ZxinQREncoderHandler;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.util.*;

import models.*;

public class Application extends Controller {

	public static void index() {
		render();
	}

	public static void generatorQR() {
		render();
	}

	public static void qrCode(String contents, int size, int margin, String on,
			String off) {
		ZxinQREncoderHandler hand = new ZxinQREncoderHandler();
		on = on == null ? "000000" : on;
		off = off == null ? "ffffff" : off;
		ByteArrayOutputStream os = hand.encode(contents, size, margin, on, off);
		BufferedInputStream bis = new BufferedInputStream(
				new ByteArrayInputStream(os.toByteArray()));
		renderBinary(bis);
	}

}