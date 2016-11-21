package com.bjlx.QinShihuang.model.im;

import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Transient;

import com.bjlx.QinShihuang.model.misc.Audio;
import com.bjlx.QinShihuang.model.misc.ImageItem;
import com.bjlx.QinShihuang.model.misc.Position;

/**
 * im 消息内容
 * @author xiaozhi
 *
 */
@Embedded
public class Content {

	@Transient
	public final static String fd_text = "text";
	@Transient
	public final static String fd_thumb = "thumb";
	@Transient
	public final static String fd_full = "full";
	@Transient
	public final static String fd_origin = "origin";
	@Transient
	public final static String fd_audio = "audio";
	@Transient
	public final static String fd_position = "position";
	@Transient
	public final static String fd_emoticon = "emoticon";
	@Transient
	public final static String fd_video = "video";
	
	/**
	 * 文本
	 */
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
	
	public Content() {

	}
}
