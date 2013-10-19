package com.humbertopinheiro.wallpaper;

import java.util.concurrent.Callable;

import com.google.common.util.concurrent.ListenableFuture;
import com.humbertopinheiro.application.Application;
import com.humbertopinheiro.base.Side;

public class FutureWallpaperProvider {

	private WallpaperProvider wallpaperProvider;

	public WallpaperProvider getWallpaperProvider() {
		return wallpaperProvider;
	}

	public void setWallpaperProvider(WallpaperProvider wallpaperProvider) {
		this.wallpaperProvider = wallpaperProvider;
	}

	public ListenableFuture<Wallpaper> getFutureWallpaper(Side side) {
		switch (side) {
		case LEFT:
			return previousWallpaper();
		case RIGHT:
			return nextWallpaper();
		case NONE:
		}
		return null;
	}

	private ListenableFuture<Wallpaper> nextWallpaper() {
		if (wallpaperProvider.hasNext()) {
			return Application.INSTANCE.pool().submit(
					new Callable<Wallpaper>() {
						@Override
						public Wallpaper call() {
							return wallpaperProvider.nextWallpaper();
						}
					});
		}
		return null;
	}

	private ListenableFuture<Wallpaper> previousWallpaper() {
		if (wallpaperProvider.hasPrevious()) {
			return Application.INSTANCE.pool().submit(
					new Callable<Wallpaper>() {
						@Override
						public Wallpaper call() {
							return wallpaperProvider.previousWallpaper();
						}
					});
		}
		return null;
	}
}
