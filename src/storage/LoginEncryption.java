package storage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;

public class LoginEncryption
{

    private String passwordForFile = "javaPass#";
    Scanner dataRecord = new Scanner(System.in);
    private String userName;
    private String password;
    private File file = new File("plainfile.txt");
    
    public LoginEncryption() throws Exception
    {
        userName = dataRecord.nextLine();
        password = dataRecord.nextLine();
        storeDetailsToFile( userName, password );
        encryptFile();
    }


    public void storeDetailsToFile( String user, String password ) throws IOException
    {

        BufferedWriter out = new BufferedWriter( new FileWriter( "plainfile.txt" ) );
        out.write( user + "#" + password );
        out.newLine();
        out.close();

    }


    public void encryptFile() throws Exception
    {

        FileInputStream inFile = new FileInputStream( "plainfile.txt" );
        FileOutputStream outFile = new FileOutputStream( "configurations.des" );

        PBEKeySpec pbeKeySpec = new PBEKeySpec( passwordForFile.toCharArray() );
        SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance( "PBEWithMD5AndTripleDES" );
        SecretKey secretKey = secretKeyFactory.generateSecret( pbeKeySpec );

        byte[] salt = new byte[8];
        Random random = new Random();
        random.nextBytes( salt );

        PBEParameterSpec pbeParameterSpec = new PBEParameterSpec( salt, 100 );
        Cipher cipher = Cipher.getInstance( "PBEWithMD5AndTripleDES" );
        cipher.init( Cipher.ENCRYPT_MODE, secretKey, pbeParameterSpec );
        outFile.write( salt );

        byte[] input = new byte[64];
        int bytesRead;
        while( (bytesRead = inFile.read( input )) != -1 )
        {
            byte[] output = cipher.update( input, 0, bytesRead );
            if( output != null )
                outFile.write( output );
        }

        byte[] output = cipher.doFinal();
        if( output != null )
            outFile.write( output );

        inFile.close();
        outFile.flush();
        outFile.close();
    }
    
    public void recordUsername() {
        
    }
    
    public void deletePlainTextFile() {
        file.delete();
    }
}
