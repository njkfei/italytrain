package com.sanhao.bdimage.model;

import java.io.IOException;
import java.util.Map;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 表情
 */
public class Scores {
	int id;
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	private double anger;
	private double contempt;
	private double disgust;
	private double fear;
	private double happiness;
	private double neutral;
	private double sadness;
	private double surprise;
	
	
	public double getAnger() {
		return anger;
	}


	public void setAnger(double anger) {
		this.anger = anger;
	}


	public double getContempt() {
		return contempt;
	}


	public void setContempt(double contempt) {
		this.contempt = contempt;
	}


	public double getDisgust() {
		return disgust;
	}


	public void setDisgust(double disgust) {
		this.disgust = disgust;
	}


	public double getFear() {
		return fear;
	}


	public void setFear(double fear) {
		this.fear = fear;
	}


	public double getHappiness() {
		return happiness;
	}


	public void setHappiness(double happiness) {
		this.happiness = happiness;
	}


	public double getNeutral() {
		return neutral;
	}


	public void setNeutral(double neutral) {
		this.neutral = neutral;
	}


	public double getSadness() {
		return sadness;
	}


	public void setSadness(double sadness) {
		this.sadness = sadness;
	}


	public double getSurprise() {
		return surprise;
	}


	public void setSurprise(double surprise) {
		this.surprise = surprise;
	}


	@Override
	public String toString() {
		return "Scores [id=" + id + ", anger=" + anger + ", contempt=" + contempt + ", disgust=" + disgust + ", fear="
				+ fear + ", happiness=" + happiness + ", neutral=" + neutral + ", sadness=" + sadness + ", surprise="
				+ surprise + "]";
	}
	
}
