package com.sanhao.bdimage.model;

/**
 * 表情
 */
public class TeacherEmotion {
	private int teacher_id;
	private Emotion emotion;
	public int getTeacher_id() {
		return teacher_id;
	}
	public void setTeacher_id(int teacher_id) {
		this.teacher_id = teacher_id;
	}
	public Emotion getEmotion() {
		return emotion;
	}
	public void setEmotion(Emotion emotion) {
		this.emotion = emotion;
	}
	@Override
	public String toString() {
		return "TeacherEmotion [teacher_id=" + teacher_id + ", emotion=" + emotion + "]";
	}

}
