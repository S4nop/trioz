package res;

import javazoom.jl.decoder.Bitstream;
import javazoom.jl.decoder.Header;
import javazoom.jl.player.AudioDevice;
import javazoom.jl.player.Player;
import javazoom.jl.player.advanced.AdvancedPlayer;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;

public enum SoundResource {
	
	THE_GHOST("NIVIRO_The_Ghost.mp3"),
	THE_FLOOR_IS_LAVA("NIVIRO_The_Floor_Is_Lava.mp3"),
	LOOP_TEST("Loop_Test.mp3");
	
	private File file;
	private int length;
	private float msPerFrame;
	private int frames;
	
	SoundResource (String name) {
		file = new File(getClass().getResource("sounds/" + name).getPath());
		
		try {
			FileInputStream fis = new FileInputStream(file);
			Bitstream bitstream = new Bitstream(new FileInputStream(file));
			Header h = bitstream.readFrame();
			
			length = (int)h.total_ms((int)fis.getChannel().size());
			msPerFrame = h.ms_per_frame();
			
		} catch (Exception e) {
			e.printStackTrace();
			length = -1;
		}
	}
	
	public Player getPlayer () {
		try {
			return new Player(new BufferedInputStream(new FileInputStream(file)));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	public AdvancedPlayer getAdvancedPlayer (AudioDevice device) {
		try {
			return new AdvancedPlayer(new BufferedInputStream(new FileInputStream(file)), device);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public int getLength () {
		return length;
	}
	public float getMsPerFrame () {
		return msPerFrame;
	}
}
