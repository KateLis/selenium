import org.junit.Test;


import java.util.Random;

public class MailGeneration {

    @Test//
    public CharSequence generateEmail() {
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        String address = generatedString+"@gm.com";
        System.out.println(address);
        return address;
    }

}