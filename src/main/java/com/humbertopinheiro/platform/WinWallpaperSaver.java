package com.humbertopinheiro.platform;

import java.io.File;
import java.util.HashMap;

import com.sun.jna.Native;
import com.sun.jna.platform.win32.WinDef.UINT_PTR;
import com.sun.jna.win32.StdCallLibrary;
import com.sun.jna.win32.W32APIFunctionMapper;
import com.sun.jna.win32.W32APITypeMapper;

public class WinWallpaperSaver implements WallpaperSaver {
	private static final long SPI_SETDESKWALLPAPER = 20;
	private static final long SPIF_UPDATEINIFILE = 0x01;
	private static final long SPIF_SENDWININICHANGE = 0x02;

	private final SPI spi = SPI.INSTANCE;

	@Override
	public void setWallpaperBackground(File file) {
		spi.SystemParametersInfo(new UINT_PTR(SPI_SETDESKWALLPAPER),
				new UINT_PTR(0), file.getAbsolutePath(), new UINT_PTR(
						SPIF_UPDATEINIFILE | SPIF_SENDWININICHANGE));
	}

	public interface SPI extends StdCallLibrary {
		// from MSDN article

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
