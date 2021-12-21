package service;


import entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reposetory.UserReposetory;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.List;


@Service
public class UserService
{


    @Autowired
    private UserReposetory userRepository;

    @Autowired
    public void saveUser(User user)
    {
        byte[] salt = generates();
        String saltString = ByteToStringDB(salt);
        String hashedPassword = SecureHashPass(user.getPassword(), salt);

        if (!hashedPassword.equals("")) {
            user.setSalt(saltString);
            user.setPassword(hashedPassword);
            userRepository.save(user);
        }
    }

    private String SecureHashPass(String password, byte[] salt)
    {

        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(salt);
            byte[] hashedPass = md.digest(password.getBytes());
            return ByteToStringDB(hashedPass);

        } catch (NoSuchAlgorithmException ex) {
            ex.printStackTrace();
        }
        return "";
    }

    private String ByteToStringDB(byte[] passhash)
    {

        return DatatypeConverter.printHexBinary(passhash).toLowerCase();
    }

    private byte[] convertStringToByteForDB(String dbPassword) {
        return DatatypeConverter.parseHexBinary(dbPassword);
    }


    private byte[] generates()
    {
        SecureRandom sr = new SecureRandom();
        byte[] hashedSalt = sr.generateSeed(12);
        return hashedSalt;
    }

    public boolean authUsr(String username, String password) {
        User dbUser = userRepository.findByUsername(username);
        if (dbUser == null) {
            System.out.println("incorrect username" + username);
            return false;
        }
        String passwordToCompare = SecureHashPass(password, convertStringToByteForDB(dbUser.getSalt()));
        return dbUser.getPassword().equals(passwordToCompare);
    }


    public void updateUser(User user)
    {
        User userDB = userRepository.findById(user.getId()).orElseThrow();
        userDB.setUsername(user.getUsername());
        userRepository.save(userDB);
    }



    public List<User> findAllUsers()
    {
        return userRepository.findAll();
    }

    public User findUserById(long id)
    {
        return userRepository.findById(id).orElseThrow();
    }



    public User findUserByUsername(String username)
    {
        return userRepository.findByUsername(username);
    }


    public void deleteUser(long id) {
        userRepository.deleteById(id);
    }

}
