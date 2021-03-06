import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;


public class Configuration {
	
	//common config
	private final int numberOfPreferredNeighbors;
	private final int unchokingInterval;
	private final int optimisticUnchokingInterval;
	private final String fileName;
	private final int fileSize;
	private final int pieceSize;
	
	private final int numPieces;
	
	//peer info
	private final ArrayList<Integer> IDs;
	private final ArrayList<String> addresses;
	private final ArrayList<Integer> downloadPorts;
	private final ArrayList<Boolean> flags;
	
	private final int numPeers;
	private final ArrayList<Integer> uploadPorts;
	private final ArrayList<Integer> havePorts;


	public int getNumPieces() {
		return numPieces;
	}


	public int getPieceSize() {
		return pieceSize;
	}

	public int getFileSize() {
		return fileSize;
	}

	public int getnumberOfPreferredNeighbors() {
		return numberOfPreferredNeighbors;
	}


	public int getUnchokingInterval() {
		return unchokingInterval;
	}


	public int getOptimisticUnchokingInterval() {
		return optimisticUnchokingInterval;
	}


	public String getFileName() {
		return fileName;
	}


	public int getNumPeers() {
		return numPeers;
	}

	
	public int getDownloadPort(int index) {
		return downloadPorts.get(index);
	}


	public int getUploadPort(int index) {
		return uploadPorts.get(index);
	}


	public int getHavePort(int index) {
		return havePorts.get(index);
	}


	public ArrayList<Integer> getIDs() {
		return IDs;
	}


	public ArrayList<String> getAddresses() {
		return addresses;
	}


	public ArrayList<Boolean> getFlags() {
		return flags;
	}


	public Config(String commonConfig, String peersInformation) throws FileNotFoundException {
		
		//Get the common configuration
		Scanner sc= new Scanner(new FileReader(commonConfig));
		this.numberOfPreferredNeighbors = Integer.parseInt(sc.nextLine().trim());
		this.unchokingInterval = Integer.parseInt(sc.nextLine().trim());
		this.optimisticUnchokingInterval = Integer.parseInt(sc.nextLine().trim());
		this.fileName = sc.nextLine().trim();
		this.fileSize = Integer.parseInt(sc.nextLine().trim());
		this.pieceSize = Integer.parseInt(sc.nextLine().trim());
		
		if (this.fileSize%this.pieceSize == 0) {
			this.numPieces = this.fileSize/this.pieceSize;
 		} else {
 			this.numPieces = this.fileSize/this.pieceSize + 1;
 		}
		
		sc.close();
		
		//Get peers information from the peersInformation file
		Scanner sc2 = new Scanner(new FileReader(peersInformation));
		
		IDs = new ArrayList<Integer>();
		addresses = new ArrayList<String>();
		downloadPorts = new ArrayList<Integer>();
		flags = new ArrayList<Boolean>();
		
		uploadPorts = new ArrayList<Integer>();
		havePorts = new ArrayList<Integer>();
		
		int count = 0;
		while (sc2.hasNextLine()) {

			String s = sc2.nextLine();
			String[] split = s.split(" ");
			this.IDs.add(Integer.parseInt(split[0].trim()));
			this.addresses.add(split[1].trim());
			this.downloadPorts.add(Integer.parseInt(split[2].trim()));
			this.uploadPorts.add(Integer.parseInt(split[2].trim()) + 1);
			this.havePorts.add(Integer.parseInt(split[2].trim()) + 2);
			if (split[3].trim().equals("1")) {
				this.flags.add(true);
			} else {
				this.flags.add(false);
			}
			count++;
		}
		
		this.numPeers = count;
		
	}
}