package lab07;

public class SubstitutionTech {

    //--------------------------ENCRYPT & DECRYPT METHOD------------------------------
    public static String encrypt(String text, int key) {
        // Create a StringBuilder to hold the encrypted result
        StringBuilder result = new StringBuilder();
        key = key % 26; // Normalize the key to ensure it's within the range of the alphabet (0-25)

        // Iterate over each character in the input text
        for (char ch : text.toCharArray()) {
            if (Character.isLowerCase(ch)) {

                // Shift the character by the key value
                char shift = (char) ((ch + key - 'a') % 26 + 'a');
                // Add the shifted character to the end of the result
                result.append(shift);

            } else if (Character.isUpperCase(ch)) {
                // If the character is uppercase, shift it similarly but with 'A' as the reference point
                char shift = (char) ((ch + key - 'A') % 26 + 'A');
                // Add the shifted character to the end of the result
                result.append(shift);
            } else {
                // If the character is not a letter, leave it unchanged
                result.append(ch);
            }
        }
        // Return the final encrypted text as a string
        return result.toString();
    }

    public static String decrypt(String text, int key) {
        StringBuilder result = new StringBuilder();
        key = key % 26;

        for (char ch : text.toCharArray()) {
            if (Character.isLowerCase(ch)) {
                char shifted = (char) ((ch - key - 'a' + 26) % 26 + 'a');
                result.append(shifted);
            } else if (Character.isUpperCase(ch)) {
                char shifted = (char) ((ch - key - 'A' + 26) % 26 + 'A');
                result.append(shifted);
            } else {
                result.append(ch);
            }
        }

        return result.toString();
    }
}
