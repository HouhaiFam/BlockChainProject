package blockchain;
import java.util.ArrayList;
import com.google.gson.GsonBuilder;


public class BlockChainCheck {
	
	public static ArrayList<Block> blockchain = new ArrayList<Block>();
	public static int difficulty = 5;
	
	public static void main(String[] args) {
		blockchain.add(new Block("Michael you've been hased boi", "0"));
		System.out.println("Trying to mine block 1...");
		blockchain.get(0).mineBlock(difficulty);
		blockchain.add(new Block("Michael I swear to god",blockchain.get(blockchain.size()-1).hash ));
		System.out.println("Trying to mine block 2....");
		blockchain.get(1).mineBlock(difficulty);
		blockchain.add(new Block("Pelata look here",blockchain.get(blockchain.size()-1).hash ));
		System.out.println("Trying to mine block 3....");
		blockchain.get(2).mineBlock(difficulty);
		
		System.out.println("\nBlockchain is Valid: " + isChainValid());
		
		String blockchainJson = new GsonBuilder().setPrettyPrinting().create().toJson(blockchain);
		System.out.println(blockchainJson);
	}


public static Boolean isChainValid() {
	Block currentBlock;
	Block previousBlock;
	String hashTarget = new String(new char[difficulty]).replace('\0', '0');
	
	//loop through blockchain to check hashes;
	for(int i=1; i < blockchain.size(); i++) {
		currentBlock = blockchain.get(i);
		previousBlock = blockchain.get(i-1);
		//compare registered hash and calculated hash:
		if(!previousBlock.hash.equals(currentBlock.calculateHash()) ) {
			System.out.println("Current Hases not Equal");
				return false;
		}
		
		if(!previousBlock.hash.equals(currentBlock.previousHash) ) {
			System.out.println("Previous Hashes not equal");
			return false;
		}
		
		if(!currentBlock.hash.substring(0, difficulty).equals(hashTarget)) {
			System.out.println("This block hasn't been mined");
			return false;
			
		}
	}
	return true;
 }

}