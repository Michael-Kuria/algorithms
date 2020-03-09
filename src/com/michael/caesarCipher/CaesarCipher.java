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
            if(character != ' '){
                // first find the position (from 'A') of the character in the alphabetical order
                // 'A' is index 65 'Z' is index 90 'a' index 97
                int newIndex = ((character - 'A') + offset) ;

                if(newIndex > 25 && newIndex < 32){
                    newIndex += (32 - newIndex);
                }

                encryptedMessage.append((char) ('A' + (newIndex % 58)) );

            }else{
                encryptedMessage.append(character);
            }

        }

        return encryptedMessage.toString();

    }

    /**
     *
     * This function will reverse the cipher method
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

            if(character != ' '){
                int originalIndex = ((character - 'A') - offset);

                if(originalIndex < 0){
                    originalIndex = ('z' - 'A') + originalIndex + 1;
                    //System.out.println((int) 'z' +" "+ originalIndex);
                }

                if(originalIndex > 25 && originalIndex < 32){
                    originalIndex -= (originalIndex - 25);

                }

                decryptedMessage.append((char) ('A' + originalIndex));
            }else{
                decryptedMessage.append(character);
            }

        }

        return decryptedMessage.toString();

    }


    public static void main(String [] args){

        CaesarCipher cipher = new CaesarCipher(1);
        String message = "MichaelZ Kuriaz";
        String encryptedMessage = cipher.cipher(message);
        String decryptedMessage = cipher.decipher(encryptedMessage);
        System.out.println("Original Message :" +message);
        System.out.println("Encrypted Message :" +encryptedMessage);
        System.out.println("Decrypted Message :" + decryptedMessage);
    }

}
