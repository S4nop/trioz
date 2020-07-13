package res;

import javax.swing.*;
import java.awt.*;

public enum ImageResource {
	
	UNIT_IMAGE("Unit.png"),
	TEMP_UNIT_100("TmpUnit100.jpg"),
	WP_720p("720p_wp.jpg"),
	MAP_1("Map1_1280x720.jpg"),
	BLOCK_1("TmpBlock1.jpg"),
	SHIELD("Shield.png"),
	GHOST_1("Ghost_type1.png"),
	GHOST_2("cuty_ghost.gif"),
	FIRE("bullet_type2_fire.png"),
	BULLET_ORB_1("bullet_type1_orb1.png"),
	BULLET_ORB_2("bullet_type1_orb2.png"),
	LASER("laser.jpg"),
	
	// Backgrounds
	START_BACKGROUND("Start_Bground.jpg"),
	START_BACKGROUND_EMPHASIZE("Start_Bground_emphasize.jpg"),
	PAUSE_OVERLAY("pause_overlay.png"),
	MAIN_BACKGROUND("main_background.jpg"),
	GAMEOVER("gameover.png"),
	HIT("hit.png"),

	// Buttons
	START_BUTTON("btn_play.jpg"),
	EXIT_BUTTON("btn_exit.jpg"),
	OPTION_BUTTON("btn_option.jpg"),
	BLANK_BUTTON("blankbtn.png"),
	SAMPLE_BUTTON("sample_button.png"),
	RESUME_BUTTON("resume_button.png"),
	RESTART_BUTTON("restart_button.png"),
	STOP_BUTTON("stop_button.png"),

	SHIELDICO("shieldil.png"),
	GHOSTTMP("cute_ghost_body.png"),
	FIREBALL("fireball.gif"),
	HPBAR("hpbar.png"),
	HP("hp.png"),
	LASER_R("laser_red.png"),
	
	// Other game effects
	GIGGLE_1("giggle_1.png"),
	GIGGLE_2("giggle_2.png"),
	GIGGLE_3("giggle_3.png"),
	GIGGLE_4("giggle_4.png"),
	LIGHT_OFF("LightOff.png");
	
	private ImageIcon imageIcon;
	
	ImageResource (String name) {
		try {
			imageIcon = new ImageIcon(getClass().getResource("images/" + name));
		} catch (Exception e) {
			e.printStackTrace();
			imageIcon = null;
		}
	}
	
	public ImageIcon getImageIcon () {
		return imageIcon;
	}
	public ImageIcon getImageIcon (int width, int height) {
		return new ImageIcon(imageIcon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH));
	}
	public ImageIcon getImageIcon (int width, int height, int hint) {
		return new ImageIcon(imageIcon.getImage().getScaledInstance(width, height, hint));
	}
}
