package blockchain;

import java.util.Date;

public class Block {
	
	public static void main(String[] args) {
		Block genesisBlock = new Block ("Michael you've been hased boi", "0");
		System.out.println("Hash for block 1 :" + genesisBlock.hash);
		
		Block secondBlock = new Block("Michael I swear to god", genesisBlock.hash);
		System.out.println("Hash for block 2 :" + secondBlock.hash);
		
		Block thirdBlock = new Block("Pelata look here", secondBlock.hash);
		System.out.println("Hash for Block 3 :" + thirdBlock.hash);
		
	}
	
	public String calculateHash() {
		String calculatedhash = StringUtil.applySha256(previousHash + Long.toString(timeStamp) + Integer.toString(nonce) + data);
		return calculatedhash;
	}
	
	public String hash;
	public String previousHash;
	private String data;
	private long timeStamp;
	private int nonce;

		public Block(String data, String previoushash) { 
			this.data = data;
			this.previousHash = previousHash;
			this.timeStamp = new Date().getTime();
			
			this.hash = calculateHash();
		
	}
		
		
		public void mineBlock(int difficulty) {
			String target = new String(new char[difficulty]).replace('\0', '0');
			while(!hash.substring(0, difficulty).equals(target)) {
				nonce ++;
				hash = calculateHash();
			}
			System.out.println("Block Mined!:" + hash);
		}

}



