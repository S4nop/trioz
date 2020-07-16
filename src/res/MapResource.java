package res;

import com.sanop.music.AdvancedMusicPlayer;
import com.sanop.platformer.entity.Block;
import com.sanop.platformer.Map;
import com.sanop.platformer.event.EventBuffer;
import com.sanop.platformer.event.Formula;

import java.io.*;
import java.util.function.Function;

public enum MapResource {
	
	TestMap("TestMap.jmap");
	
	private Map map;
	
	MapResource (String name) {
		
		String line_splited[];
		map = new Map();
		try {
			File file = new File(getClass().getResource("maps/" + name).getPath());
			FileReader filereader = new FileReader(file);
			BufferedReader bufReader = new BufferedReader(filereader);
			String line;

			while ((line = bufReader.readLine()) != null) {
				if(line.equals("@Entities")) break;
				line_splited = line.split("::");
				if(line_splited[0].equals("BGM"))
					map.setBgm(new AdvancedMusicPlayer(SoundResource.valueOf(line_splited[1])));
				else if(line_splited[0].equals("BGI"))
					map.setBgImage(ImageResource.valueOf(line_splited[1]).getImageIcon().getImage());
			}
			while ((line = bufReader.readLine()) != null) {
				line_splited = line.split("::");
				EventBuffer.bufType type;

				if (line_splited[0].equals("Block"))  type = EventBuffer.bufType.BLOCK_EVENT;
				else if (line_splited[0].equals("F_Bullet"))  type = EventBuffer.bufType.F_BULLET_EVENT;
				else if (line_splited[0].equals("Bullet"))  type = EventBuffer.bufType.BULLET_EVENT;
				else if (line_splited[0].equals("Laser")) type = EventBuffer.bufType.LASER_EVENT;
				else if (line_splited[0].equals("Fireball")) type = EventBuffer.bufType.FIREBALL_EVENT;
				else if (line_splited[0].equals("Ghost")) type = EventBuffer.bufType.GHOST_EVENT;
				else continue;
					map.addEvent(type, line_splited);
			}
			
			bufReader.close();
			
		} catch (Exception e) {
			e.printStackTrace();
			map = null;
		}
	}

	public Map getMapData () {
		return map;
	}
}
