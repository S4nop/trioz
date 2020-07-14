package res;

import com.sanop.platformer.entity.Block;
import com.sanop.platformer.Map;
import com.sanop.platformer.event.EventBuffer;

import java.io.*;

public enum MapResource {
	
	TestMap("TestMap.jmap");
	
	private Map map;
	
	MapResource (String name) {
		
		String tmpSplit[];
		map = new Map();
		try {
			File file = new File(getClass().getResource("maps/" + name).getPath());
			FileReader filereader = new FileReader(file);
			BufferedReader bufReader = new BufferedReader(filereader);
			String line;
			
			while ((line = bufReader.readLine()) != null) {
				if (line.charAt(0) == '/' || line.charAt(0) == '@') continue;
				
				tmpSplit = line.split("::");
				if (tmpSplit[0].equals("Block"))
					map.addEvent(EventBuffer.bufType.BLOCK_EVENT,
							Integer.parseInt(tmpSplit[1]),
							Integer.parseInt(tmpSplit[2]),
							Integer.parseInt(tmpSplit[3]),
							Integer.parseInt(tmpSplit[4]),
							Integer.parseInt(tmpSplit[5]),
							Integer.parseInt(tmpSplit[6]),
							(Integer integer) -> { return null; });
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
