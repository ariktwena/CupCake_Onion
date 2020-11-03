package core;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Arrays;

public class User {

    //Password stuff
    private static final int PASSWORD_ITERATIONS = 65536;
    private static final int PASSWORD_LENGTH = 256; // 32 bytes
    private static final SecretKeyFactory PASSWORD_FACTORY;
    static {
        SecretKeyFactory factory = null;
        try {
            factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        PASSWORD_FACTORY = factory;
    }
    private final byte[] salt;
    private final byte[] secret;

    private final int user_id;
    private final String user_email;
    private String user_role;
    private double user_credit;

    public User(int user_id, String user_email, String user_role, double user_credit, byte[] salt, byte[] secret) {
        this.user_id = user_id;
        this.user_email= user_email;
        this.user_role = user_role;
        this.user_credit = user_credit;
        this.salt = salt;
        this.secret = secret;
    }

    public User(String user_email, String user_role, double user_credit, byte[] salt, byte[] secret) {
        this.user_id = -1;
        this.user_email= user_email;
        this.user_role = user_role;
        this.user_credit = user_credit;
        this.salt = salt;
        this.secret = secret;
    }

    //Is used when we edit a user
    public User(int user_id, String user_email, String user_role, double user_credit) {
        this.user_id = user_id;
        this.user_email= user_email;
        this.user_role = user_role;
        this.user_credit = user_credit;
        this.salt = null;
        this.secret = null;
    }

    //Is used when we get orders from the DB where we don't need all the users info's
    public User(String user_email) {
        this.user_id = -1;
        this.user_email= user_email;
        this.user_role = "";
        this.user_credit = 0;
        this.salt = null;
        this.secret = null;
    }

    public User withId (int user_id) {
        return new User(user_id, this.user_email, this.user_role, this.user_credit, this.salt, this.secret);
    }

    public int getId() {
        return user_id;
    }

    public String getUserEmail() {
        return user_email;
    }

    public String getUserRole() {
        return user_role;
    }

    public double getUser_credit() {
        return user_credit;
    }

    public byte[] getSalt() {
        return salt;
    }

    public byte[] getSecret() {
        return secret;
    }

    public void setUserRole(String user_role) {
        this.user_role = user_role;
    }

    public void setUser_credit(double user_credit) {
        this.user_credit = user_credit;
    }

    /**
     * Password stuff
     */

    public static byte[] generateSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        return salt;
    }

    public static byte[] calculateSecret(byte[] salt, String password) {
        KeySpec spec = new PBEKeySpec(password.toCharArray(), salt,
                PASSWORD_ITERATIONS,
                PASSWORD_LENGTH);
        try {
            return PASSWORD_FACTORY.generateSecret(spec).getEncoded();
        } catch (InvalidKeySpecException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean isPasswordCorrect(String password) {
        return Arrays.equals(this.secret, calculateSecret(salt, password));
    }

    // Thanks: https://stackoverflow.com/a/13006907
    public static String byteArrayToHex(byte[] a) {
        StringBuilder sb = new StringBuilder(a.length * 2);
        for(byte b: a)
            sb.append(String.format("%02x", b));
        return sb.toString();
    }
}
