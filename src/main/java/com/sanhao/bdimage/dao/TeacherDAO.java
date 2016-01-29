package com.sanhao.bdimage.dao;

import java.sql.Timestamp;
import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.sanhao.bdimage.model.FaceRectangle;
import com.sanhao.bdimage.model.Scores;
import com.sanhao.bdimage.model.ScoresMax;
import com.sanhao.bdimage.model.Teacher;

/**
 * 老师信息接口
 * @author sanhao
 *
 */
public interface TeacherDAO {
	@Select("SELECT `teacher_id`, `user_id`,  `teacher_pic`, `teacher_auth_url`, "
			+ "`teacher_auth_url2`, `teacher_seniority`, `teacher_title`, `teacher_award`, `teacher_desc`, `teacher_level`, `teacher_star`, "
			+ "`teacher_qq`, `teacher_session`, `teacher_map`, `teacher_gender`, `teacher_constellation`, `teacher_zodiac`, `teacher_character`, "
			+ "`teacher_hobby`, `teacher_feature`, `teacher_sanhao`, `teacher_ascendency`, `teacher_longitude`, `teacher_latitude`, `teacher_bgimg`,"
			+ " `teacher_bgcolor`, `teacher_realname_url`, `teacher_realname_auth`, `teacher_auth_time`, `teacher_qualification`, `teacher_title_auth`, "
			+ "`teacher_certification_url`, `teacher_certification_auth`, `teacher_student_num`, `teacher_hours_num`, `teacher_pageview`, `teacher_organization`,"
			+ " `teacher_identity`, `teacher_degree`, `teacher_info_step`, `sub_time`, `area_id`, `verify_time`, `update_time` FROM `ysyy_user_teacher` WHERE teacher_id=#{teacher_id}")
	Teacher getTeacher(@Param("teacher_id")int teacher_id);
	
	// 获取用户编号
	@Select("SELECT  `user_id `FROM `ysyy_user_teacher` WHERE teacher_id=#{teacher_id}")
	int getUserId(@Param("teacher_id")int teacher_id);
	
	@Select("SELECT `teacher_id`,`teacher_pic`,`update_time` FROM `ysyy_user_teacher` WHERE `update_time`>#{update_time}")
	List<Teacher> getTeacherByTimestame(@Param("update_time")Timestamp update_time);
	
	@Select("SELECT teacher_pic FROM ysyy_user_teacher WHERE teacher_id=#{teacher_id}")
	String getPic(@Param("teacher_id")int teacher_id);
	
	@Select("SELECT teacher_pic FROM ysyy_user_teacher")
	List<String> getPics();
	
	@Insert("INSERT INTO `face`(`id`, `height`, `left`, `top`, `width`) VALUES (#{id},#{height},#{left},#{top},#{width})")
	int insertFace(FaceRectangle face);
	
	@Insert("INSERT INTO `emotion`(`id`, `anger`, `contempt`, `disgust`, `fear`, `happiness`, `sadness`, `surprise`,`neutral`) VALUES (#{id}, #{anger},#{contempt},#{disgust},#{fear},#{happiness},#{sadness},#{surprise},#{neutral})")
	int insertEmotion(Scores scores);
	
	@Insert("INSERT INTO `emotionmax`(`id`, `type`, `score`) VALUES (#{id},#{type},#{score})")
	int insertEmotionMax(ScoresMax scoresmax);
	
	@Select("SELECT * FROM `face` WHERE id=#{id}")
	FaceRectangle exists(@Param("id")int id);

	@Update("UPDATE `emotionmax` SET `type`=#{type},`score`=#{score} WHERE id=#{id}")
	int updateFace(FaceRectangle faceRectangle);

	@Update("UPDATE `emotion` SET `anger`=#{anger},`contempt`=#{contempt},`disgust`=#{disgust},`fear`=#{fear},`happiness`=#{happiness},`sadness`=#{sadness},`surprise`=#{surprise},`neutral`=#{neutral} WHERE id=#{id}")
	int updateEmotion(Scores scores);

	@Update("UPDATE `emotionmax` SET `type`=#{type},`score`=#{score} WHERE `id`=#{id}")
	int updateEmotionMax(ScoresMax max);
}
