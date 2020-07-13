package res;

import com.sanop.platformer.entity.Block;
import com.sanop.platformer.Map;

import java.io.*;

public enum MapResource {
	
	TestMap("TestMap.jmap");
	
	private Map mapData;
	
	MapResource (String name) {
		
		String tmpSplit[];
		mapData = new Map();
		
		try {
			File file = new File(getClass().getResource("maps/" + name).getPath());
			FileReader filereader = new FileReader(file);
			BufferedReader bufReader = new BufferedReader(filereader);
			String line;
			
			while ((line = bufReader.readLine()) != null) {
				if (line.charAt(0) == '/' || line.charAt(0) == '@') continue;
				
				tmpSplit = line.split("::");
				if (tmpSplit[0].equals("Block"))
					mapData.addBlock(new Block(Integer.parseInt(tmpSplit[1]),
												 Integer.parseInt(tmpSplit[2]),
												 Integer.parseInt(tmpSplit[3]),
												 Integer.parseInt(tmpSplit[4])));
			}
			
			bufReader.close();
			
		} catch (Exception e) {
			e.printStackTrace();
			mapData = null;
		}
	}
	
	public Map getMapData () {
		return mapData;
	}
}
