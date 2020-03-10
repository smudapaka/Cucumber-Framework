import java.util.Random;


public class commanFunctions {
	
	 public String randomStringGenerator(int length) {
	        char[] chars = "abcdefghijklmnopqrstuvwxyz".toCharArray();
	        StringBuilder sb = new StringBuilder(length);
	        Random random = new Random();
	        for (int i = 0; i < length; i++) {
	            char c = chars[random.nextInt(chars.length)];
	            sb.append(c);
	        }
	        String output = sb.toString();
	        System.out.println(output);
	        return output;
	    }

	    public String numGeneration() {
	        String ssn=null;
	        try {
	        	Random rand = new Random();
	            int num = rand.nextInt(90000000) + 10000000;
	            ssn="7"+num;
	            System.out.println(ssn);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return ssn;
	    }

}
