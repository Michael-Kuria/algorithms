package com.michael.caesarCipher;

/**
 * A java program to implement CaesarCipher
 */
public class CaesarCipher {

    public int offset;

    public CaesarCipher(int offset){
        this.offset = offset;
    }



    /**
     * This method offsets each letter of the given @param message +offset integers
     *
     * We will put into consideration capital letters
     *
     * @param message
     * @return the encrypted message
     */
    public String cipher(String message){

        StringBuilder encryptedMessage = null;

        if(message == null || message == ""){
            return message;
        }

        encryptedMessage = new StringBuilder();

        for(int i = 0; i < message.length(); i ++){
            char character = message.charAt(i);
            if(Character.isAlphabetic(character)){
                // first find the position (from 'A') of the character in the alphabetical order
                // 'A' is index 65, 'Z' is index 90, 'a' index 97 and 'z' index 122
                int newIndex;
                if(character >= 'a' ){
                    //a small letter
                    newIndex = ((character - 'a') + offset) % 26;
                    encryptedMessage.append((char) ('a' + (newIndex)) );

                }else{
                    //a Capital letter
                    newIndex = ((character - 'A') + offset) % 26;
                    encryptedMessage.append((char) ('A' + (newIndex)) );

                }

            }else{
                encryptedMessage.append(character);
            }

        }

        return encryptedMessage.toString();

    }

    /**
     *
     * This function will reverse the cipher method
     * {needs improvement check with an offset of 21 and 98}
     *
     * @param message String to be decrypted
     * @return the decrypted message
     */
    public String decipher(String message){

        StringBuilder decryptedMessage = null;

        if(message == null || message.equals("")){
            return message;
        }

        decryptedMessage = new StringBuilder();

        for(int i = 0; i < message.length(); i ++ ){
            char character = message.charAt(i);

            if(Character.isAlphabetic(character)){
                int originalIndex;

                if(character >= 'a'){

                    originalIndex = ((character - 'a') - offset) % 26;

                    if(originalIndex < 0){

                        originalIndex = 26 + originalIndex;
                    }
                    decryptedMessage.append((char) ('a' + originalIndex));

                }else{

                    originalIndex = ((character - 'A') - offset) % 26;

                    if(originalIndex < 0){
                        originalIndex = 26 + originalIndex;
                    }
                    decryptedMessage.append((char) ('A' + originalIndex));

                }

            }else{
                decryptedMessage.append(character);
            }

        }

        return decryptedMessage.toString();

    }


    public static void main(String [] args){

        CaesarCipher cipher = new CaesarCipher(25);
        String message = "The Quick Lazy Brown Foxes Jumped over the gate.";
        String encryptedMessage = cipher.cipher(message);
        String decryptedMessage = cipher.decipher(encryptedMessage);
        System.out.println("Original Message :" +message);
        System.out.println("Encrypted Message :" +encryptedMessage);
        System.out.println("Decrypted Message :" + decryptedMessage);
    }

}
