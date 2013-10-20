package com.humbertopinheiro.application;

import static com.google.common.util.concurrent.MoreExecutors.listeningDecorator;
import static java.util.concurrent.Executors.newFixedThreadPool;

import com.google.common.util.concurrent.ListeningExecutorService;

public class ThreadPool {

	private static final int MAX_THREADS = 10;

	private final ListeningExecutorService pool = listeningDecorator(newFixedThreadPool(MAX_THREADS));

	public ListeningExecutorService pool() {
		return pool;
	}
}
