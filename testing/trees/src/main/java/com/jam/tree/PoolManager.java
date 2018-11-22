package com.jam.tree;

import org.springframework.stereotype.Component;

import oracle.ucp.admin.UniversalConnectionPoolManager;

@Component
public class PoolManager {

	private UniversalConnectionPoolManager universalPoolManager;

	
	public UniversalConnectionPoolManager getPoolManager() {
		return universalPoolManager;
	}

	public void setPoolManager(UniversalConnectionPoolManager poolManager) {
		this.universalPoolManager = poolManager;
	}
}
