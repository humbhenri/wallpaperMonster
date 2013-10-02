package com.humbertopinheiro.application;

import static org.apache.commons.lang3.StringUtils.equalsIgnoreCase;
import static org.apache.commons.lang3.StringUtils.split;

import java.io.File;
import java.nio.file.Path;
import java.util.HashMap;

import org.apache.commons.lang3.StringUtils;

import com.sun.jna.Native;
import com.sun.jna.platform.win32.WinDef.UINT_PTR;
import com.sun.jna.win32.*;

public class WinWallpaperSaver extends WallpaperSaver {

	@Override
	protected String getUserPathImagesFolder() {
		return SystemProperties.INSTANCE.getUserHomePictures();
	}

	@Override
	protected void setWallpaperBackground(Path path) {
		File file = path.toFile();
		SPI.INSTANCE.SystemParametersInfo(
				new UINT_PTR(SPI.SPI_SETDESKWALLPAPER), new UINT_PTR(0),
				file.getAbsolutePath(), new UINT_PTR(SPI.SPIF_UPDATEINIFILE
						| SPI.SPIF_SENDWININICHANGE));
	}

	public interface SPI extends StdCallLibrary {
		// from MSDN article
		long SPI_SETDESKWALLPAPER = 20;
		long SPIF_UPDATEINIFILE = 0x01;
		long SPIF_SENDWININICHANGE = 0x02;

		@SuppressWarnings("serial")
		SPI INSTANCE = (SPI) Native.loadLibrary("user32", SPI.class,
				new HashMap<Object, Object>() {
					{
						put(OPTION_TYPE_MAPPER, W32APITypeMapper.UNICODE);
						put(OPTION_FUNCTION_MAPPER,
								W32APIFunctionMapper.UNICODE);
					}
				});

		boolean SystemParametersInfo(UINT_PTR uiAction, UINT_PTR uiParam,
				String pvParam, UINT_PTR fWinIni);
	}
}
