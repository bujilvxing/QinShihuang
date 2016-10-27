package com.bjlx.QinShihuang.model.im;

import org.mongodb.morphia.annotations.Embedded;

import com.bjlx.QinShihuang.model.misc.Audio;
import com.bjlx.QinShihuang.model.misc.ImageItem;
import com.bjlx.QinShihuang.model.misc.Position;

@Embedded
public class Content {

	private String text;
	
	/**
	 * 缩略图
	 */
	private ImageItem thumb;
	
	/**
	 * 完整图
	 */
	private ImageItem full;
	
	/**
	 * 原图
	 */
	private ImageItem origin;
	
	/**
	 * 音频信息
	 */
	private Audio audio;
	
	/**
	 * 位置信息
	 */
	private Position position;
	
	/**
	 * 动画表情
	 */
	private Emoticon emoticon;
	
	/**
	 * 视频
	 */
	private Video video;

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public ImageItem getThumb() {
		return thumb;
	}

	public void setThumb(ImageItem thumb) {
		this.thumb = thumb;
	}

	public ImageItem getFull() {
		return full;
	}

	public void setFull(ImageItem full) {
		this.full = full;
	}

	public ImageItem getOrigin() {
		return origin;
	}

	public void setOrigin(ImageItem origin) {
		this.origin = origin;
	}

	public Audio getAudio() {
		return audio;
	}

	public void setAudio(Audio audio) {
		this.audio = audio;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public Emoticon getEmoticon() {
		return emoticon;
	}

	public void setEmoticon(Emoticon emoticon) {
		this.emoticon = emoticon;
	}

	public Video getVideo() {
		return video;
	}

	public void setVideo(Video video) {
		this.video = video;
	}
	
	
}
