package com.yizu.proj.sys.beans;

import com.yizu.proj.sys.beans.gen.CircletalkaboutBase;

/**
 * This class was generated by MyBatis Generator.
 * This class corresponds to the database table myvoteinfo
*/
public class Circletalkabout extends CircletalkaboutBase{
	private int visitCount;
	private Usertalk talk;

	public int getVisitCount() {
		return visitCount;
	}

	public void setVisitCount(int visitCount) {
		this.visitCount = visitCount;
	}

	public Usertalk getTalk() {
		return talk;
	}

	public void setTalk(Usertalk talk) {
		this.talk = talk;
	}
	
	
}