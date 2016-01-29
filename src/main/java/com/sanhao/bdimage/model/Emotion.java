package com.sanhao.bdimage.model;

/**
 * 表情
 */
public class Emotion {
	private FaceRectangle faceRectangle;
	private Scores scores;
	public FaceRectangle getFaceRectangle() {
		return faceRectangle;
	}
	public void setFaceRectangle(FaceRectangle faceRectangle) {
		this.faceRectangle = faceRectangle;
	}
	public Scores getScores() {
		return scores;
	}
	public void setScores(Scores scores) {
		this.scores = scores;
	}
	@Override
	public String toString() {
		return "Emotion [faceRectangle=" + faceRectangle + ", scores=" + scores + "]";
	}
	
	
	
}
