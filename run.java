

public class run {
    public static void main(String[] args) {
        String the_arr = readDictionary("dictionary.txt") ; 
        System.out.println(the_arr);
    }


       public static String[] readDictionary(String filename) {

		In in = new In(filename) ; //read the file dictionary 
        //String[] words_arr = new String[] ; //creat a new arr with all the words from the file

        String all_dictionary = in.readAll() ; 
        String[] words = all_dictionary.split("\\s+") ;
        int count = words.length; 
        String count1 = (string) count ; 

        // while (!in.isEmpty()){
        //     String the_words = in.readString(); //read the first word
        //     count ++ ; 
        // }
        return count;

    }

    
}
