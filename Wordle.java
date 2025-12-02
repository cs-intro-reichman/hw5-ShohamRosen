public class Wordle {

    // Reads all words from dictionary filename into a String array.
    public static String[] readDictionary(String filename) {

		In in = new In(filename) ; //read the file dictionary 
        //String[] words_arr = new String[] ; //creat a new arr with all the words from the file

        String all_dictionary = in.readAll() ; 
        String[] words = all_dictionary.split("\\s+") ;

        return words;

    }

    // Choose a random secret word from the dictionary. 
    // Hint: Pick a random index between 0 and dict.length (not including) using Math.random()
    public static String chooseSecretWord(String[] dict) {
        int dict_length = dict.length ; //the number of words in the dict 
        int index = (int) (Math.random() * dict_length + 1) ; //choose index 
        String the_word = dict[index] ; //return the word in the index

    return the_word ; 
    }


    // Simple helper: check if letter c appears anywhere in secret (true), otherwise
    // return false.
    public static boolean containsChar(String secret, char c) {

        int the_len = secret.length() ; 
        boolean the_ans = false ; 

		for(int i = 0; i < the_len; i++){
            char the_letter = secret.charAt(i); 
            if (c == the_letter){
                the_ans = true ; 

            }    
        }
    return the_ans; 
    }


    // Compute feedback for a single guess into resultRow.
    // G for exact match, Y if letter appears anywhere else, _ otherwise.
    public static void computeFeedback(String secret, String guess, char[] resultRow) {
        int the_length1 = secret.length() ; 
        int the_length2 = guess.length() ; 
        String the_answer = "_"; //the return value 

        for(int j = 0; j < the_length1; j++) {
            if (containsChar(secret, guess.charAt(j))){
                int the_index_real = secret.charAt(j) ; 
                int the_index_guess = guess.charAt(j) ; 
                if (the_index_guess == the_index_real) {
                    the_answer = "G" ;
                }
                else{
                    the_answer = "y"; 
                }
            }
        }
    }

	// you may want to use containsChar in your implementation
    // Store guess string (chars) into the given row of guesses 2D array.
    // For example, of guess is HELLO, and row is 2, then after this function 
    // guesses should look like:
    // guesses[2][0] // 'H'
	// guesses[2][1] // 'E'
	// guesses[2][2] // 'L'
	// guesses[2][3] // 'L'
	// guesses[2][4] // 'O'

    public static void storeGuess(String guess, char[][] guesses, int row) {
        
        int the_len = guess.length() ; 
        for(int i =0;i < the_len; i++ ){
            guesses[row][i] = guess.charAt(i);

        }
    }

    // Prints the game board up to currentRow (inclusive).
    public static void printBoard(char[][] guesses, char[][] results, int currentRow) {
        System.out.println("Current board:");
        for (int row = 0; row <= currentRow; row++) {
            System.out.print("Guess " + (row + 1) + ": ");
            for (int col = 0; col < guesses[row].length; col++) {
                System.out.print(guesses[row][col]);
            }
            System.out.print("   Result: ");
            for (int col = 0; col < results[row].length; col++) {
                System.out.print(results[row][col]);
            }
            System.out.println();
        }
        System.out.println();
    }

    // Returns true if all entries in resultRow are 'G'.
    public static boolean isAllGreen(char[] resultRow) {
        int the_len = resultRow.length ; 
        boolean the_ans = false ;
		for(int i = 0; i< the_len; i++){
            if (resultRow[i] == 'G'){
                the_ans = true ; 
            }
            else{
                return false ; 
            }
        }
    return the_ans ; 
    }


    public static void main(String[] args) {

        int WORD_LENGTH = 5;
        int MAX_ATTEMPTS = 6;
        
        // Read dictionary
        String[] dict = readDictionary("dictionary.txt");

        // Choose secret word
        String secret = chooseSecretWord(dict);

        // Prepare 2D arrays for guesses and results
        char[][] guesses = new char[MAX_ATTEMPTS][WORD_LENGTH] ; 
        char[][] results = new char[MAX_ATTEMPTS][WORD_LENGTH] ; 

        // Prepare to read from the standart input 
        In inp = new In();

        int attempt = 0;
        boolean won = false;

        while (attempt < MAX_ATTEMPTS && !won) {

            String guess = "";
            boolean valid = false;

            // Loop until you read a valid guess
            while (!valid) {
                System.out.print("Enter your guess (5-letter word): ");
                guess = inp.readString() ; 
                
                if (guess==null||guess.length() != WORD_LENGTH) {
                    System.out.println("Invalid word. Please try again.");
                } else {
                    valid = true;
                }
            }

            // Store guess and compute feedback
            // ... use storeGuess and computeFeedback

            // Print board
            printBoard(guesses, results, attempt);

            // Check win
            if (isAllGreen(results[attempt])) {
                System.out.println("Congratulations! You guessed the word in " + (attempt + 1) + " attempts.");
                won = true;
            }

            attempt++;
        }

        if (!won) {
            System.out.println("Sorry, you did not guess the word"); 
            System.out.println("The secret word was: RADIO"); // ... follow the assignment examples for how the printing should look like
        }

        inp.close();
    }
}
