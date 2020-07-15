package res;

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
				line_splited = line.split("::");
				EventBuffer.bufType type;
				Function<Integer, Double[]> formula;

				if (line_splited[0].equals("Block"))  type = EventBuffer.bufType.BLOCK_EVENT;
				else if (line_splited[0].equals("F_Bullet"))  type = EventBuffer.bufType.F_BULLET_EVENT;
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
