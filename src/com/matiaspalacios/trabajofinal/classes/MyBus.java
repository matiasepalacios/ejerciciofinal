package com.matiaspalacios.trabajofinal.classes;

import com.squareup.otto.Bus;

public class MyBus {
	 
	  private static final Bus BUS = new Bus();
	 
	  public static Bus getInstance() {
	    return BUS;
	  }
	}
